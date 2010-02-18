package org.eclipse.b3.build.core;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.b3.backend.core.B3EngineException;
import org.eclipse.b3.backend.core.B3InternalError;
import org.eclipse.b3.backend.evaluator.b3backend.BExecutionContext;
import org.eclipse.b3.backend.evaluator.b3backend.BExpression;
import org.eclipse.b3.backend.evaluator.b3backend.BInnerContext;
import org.eclipse.b3.backend.evaluator.b3backend.BParameter;
import org.eclipse.b3.backend.evaluator.b3backend.BParameterList;
import org.eclipse.b3.backend.evaluator.b3backend.BPropertySet;
import org.eclipse.b3.backend.evaluator.b3backend.ExecutionMode;
import org.eclipse.b3.build.build.B3BuildFactory;
import org.eclipse.b3.build.build.BuildResult;
import org.eclipse.b3.build.build.BuildResultContext;
import org.eclipse.b3.build.build.BuildUnit;
import org.eclipse.b3.build.build.BuilderReference;
import org.eclipse.b3.build.build.EffectiveBuilderReferenceFacade;
import org.eclipse.b3.build.build.IBuilder;
import org.eclipse.b3.build.build.PathVector;
import org.eclipse.b3.build.internal.B3BuildActivator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.EList;

/**
 * A Job that executes a Builder.
 * A call to an IBuilder should return an (unscheduled) instance of B3BuilderJob that is configured to
 * run the builder (the prepared context normally passed on the the "internalCall" should be passed to
 * the constructor of a B3BuilderJob, together with a reference to the builder).
 * 
 */
public class B3BuilderJob extends Job {

	private BExecutionContext ctx;
	private IBuilder builder;
	private BuildUnit unit;
	private B3BuilderJob parent;
	private List<String> aliases;

	/**
	 * Creates a B3BuilderJob that will run a builder for a unit identified by the value of "unit"
	 * found in the context passed as an argument.
	 * @param ctx
	 * @param builder
	 * @throws B3EngineException if the value "unit" is not defined in the context.
	 */
	public B3BuilderJob(BExecutionContext ctx, IBuilder builder) throws B3EngineException {
		super("builder job"); // dummy name, replaced below
		if (ctx == null)
			throw new IllegalArgumentException("Context can not be null when creating a B3BuilderJob");
		if (builder == null)
			throw new IllegalArgumentException("Builder can not be null when creating a B3BuilderJob");
		this.ctx = ctx;
		this.builder = builder;
		unit = (BuildUnit) ctx.getValue("unit");
		if (unit == null)
			throw new IllegalArgumentException("Context must have an instance of BuildUnit bound to context value 'unit'");
		unit = BuildUnitProxyAdapterFactory.eINSTANCE.adapt(unit).getProxy();
		this.aliases = null;
		setName("building: " + unit.getName() + "#" + builder.getName());
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		try {
			// set the UNIT DEFAULT PROPERTIES in a context visible downstream
			// (but only if there are default properties to evaluate).
			BPropertySet properties = unit.getDefaultProperties();
			if(properties != null) {
				BInnerContext ictx = ctx.createWrappedInnerContext();
				properties.evaluateDefaults(ictx.getOuterContext(), true);
				ctx = ictx;
			}

			// PRECONDITION
			// just evaluate, supposed to throw exception if not acceptable
			// The precondition sees the input context, and unit default properties
			// but not 'output', 'input', and the builder's default properties.
			//
			BExpression tmp = builder.getPrecondExpr();
			if(tmp != null)
				tmp.evaluate(ctx);

			// Iterate over all builder references, and call each builder to produce a build job.
			// Collect all build jobs to be executed.
			//
			List<B3BuilderJob> jobsToRun = new ArrayList<B3BuilderJob>();
			Iterator<EffectiveBuilderReferenceFacade> rItor = builder.getEffectiveBuilderReferences(ctx);
			while(rItor.hasNext()) {
				EffectiveBuilderReferenceFacade ebref = rItor.next();
				BuilderReference bref = ebref.getBuilderReference();
				String builderName = bref.getBuilderName();
				BExecutionContext ctxToUse = ebref.getContext();
				BParameterList parameters = bref.getParameters();
				int size = 1 + (parameters == null ? 0 : parameters.getParameters().size());
				Object[] values = new Object[size];
				Type[] types = new Type[size];
				int idx = 1;
				if(parameters != null)
					for(BParameter p : parameters.getParameters()) {
						values[idx] = p.getExpr().evaluate(ctxToUse);
						types[idx++] = p.getExpr().getDeclaredType(ctxToUse);
					}
				values[0] = unit;
				types[0] = BuildUnitProxyAdapterFactory.eINSTANCE.adapt(unit).getProxy().getClass();
				Object buildJobObject = ctxToUse.callFunction(builderName, values, types);
				if(!(buildJobObject instanceof B3BuilderJob)) 
					throw new B3InternalError("Builder did not return a B3BuilderJob: "+builderName);
				B3BuilderJob buildJob = (B3BuilderJob)buildJobObject;
				buildJob.setAliases(ebref.getAliases());
				jobsToRun.add(buildJob);
			}
			// EXECUTE JOB ARRAY observing the execution mode of the builder and the unit
			if(builder.getExecutionMode()  == ExecutionMode.PARALLEL && unit.getExecutionMode() == ExecutionMode.PARALLEL)
				runParallel(jobsToRun, monitor);
			else
				runSequential(jobsToRun, monitor);

			// COLLECT INPUT RESULT
			// Merge the input into "input" BuildResult, and all other aliased groups
			// 
			IStatus status = collectResult(jobsToRun);
			if(!status.isOK())
				return status;

			// OUTPUT (if stated) should be evaluated at this point to make it available in
			// the post input condition. Even if there is no output, assign an empty build result to "output"
			// (for consistency, and user code may modify this instance).
			//
			BuildResult output = B3BuildFactory.eINSTANCE.createBuildResult();
			EffectivePathVectorIterator pvItor = new EffectivePathVectorIterator(ctx, builder.getOutput());
			EList<PathVector> outputPaths = output.getPathVectors();
			while(pvItor.hasNext())
				outputPaths.add(pvItor.next());

			ctx.defineFinalValue("output", output, BuildResult.class);

			// POST INPUT CONDITION
			// just evaluate, supposed to throw exception if not acceptable
			// context has all input defined at this point and output refers to effective Build Result
			// (but without default annotations).
			//
			tmp = builder.getPostinputcondExpr();
			if(tmp != null)
				tmp.evaluate(ctx);


			// EVALUATE THE FUNCTION BODY, OR PERFORM THE DEFAULT
			// TODO: using "internalCall" ONLY WORKS FOR B3 FUNCTIONS 
			// (A B3 Function simply calls its funcExpr
			// and relies on the context to hold the parameters. A Java function makes use of the
			// parameters. The context prepared for the call (by BContext) knows about the parameters,
			// so, when a Java Based Builder is supported, more work is required here (alternatively
			// refactor the internalCall to always make used of the context for parameters).
			// 
			Object br = builder.internalCall(ctx, new Object[]{}, new Type[]{});

			// A return of null means there was no funcExpression (or that the funcExpression returned
			// null explicitly). In both cases - this means that the default "output" should be returned
			// (if specified), and
			// lastly "input" (if specified). If neither produces any result, an empty BuildResult is returned.
			//
			if(br == null)
				br = ctx.getValue((builder.getOutput() == null) ? "input" : "output");

			if(br == null)
				throw new B3InternalError("Should not happen. Evaluation of builder ended with null result.");

			if(!(br instanceof BuildResult))
				throw new B3WrongBuilderReturnType(builder.getName(), br.getClass());

			BuildResult buildResult = (BuildResult)br;

			// PROCESS DEFAULT ANNOTATIONS
			// If the returned value is the result of processing output (and there was output declared)
			// then, evaluate the default annotation properties. In all other scenarios, it is the user's
			// responsibility to set/process these.
			//
			if(buildResult == output && builder.getOutput() != null) {
				BPropertySet propertySet = builder.getOutput().getAnnotations();
				if(propertySet != null) {
					// BuildResultContext is specially constructed for the purpose of collecting
					// property values as used here.
					BuildResultContext specialContext = B3BuildFactory.eINSTANCE.createBuildResultContext();
					specialContext.setParentContext(ctx); // current scope is visible.
					// if calls are made when evaluating properties the correct outer context is needed
					specialContext.setOuterContext(ctx instanceof BInnerContext
							? ((BInnerContext)ctx).getOuterContext()
									: ctx);

					// Make sure special context is initialized with the values from the produced 
					// output (if any were set). (Can not simply use setValueMap, as the values would then
					// be missing from the BuildResult (both include a ValueMap by containment), and
					// user code may refer to values in the output...)
					specialContext.getValueMap().merge(output.getValueMap());

					// Evaluate in an inner context wrapper to ensure the special context's value map
					// does not get polluted with local variables (since the special context is a
					// property scope, it will only have properties set in its value map).
					// Evaluate default properties with allVisible == false to ensure that
					// only the special context is consulted for already set values (i.e. to determine
					// if the default should be used or not, as opposed to evaluating properties and values
					// visible in the context).
					propertySet.evaluateDefaults(specialContext.createInnerContext(), false);

					// Steal the value map from the special context and use it in the result
					// (the context is forgotten at this point and does not need its values).
					output.setValueMap(specialContext.getValueMap());
				}
			}

			// POST CONDITION
			// just evaluate, supposed to throw exception if not acceptable
			tmp = builder.getPostcondExpr();
			if(tmp != null)
				tmp.evaluate(ctx);

			// All done, return an OK status with the result set. (Partial grouped/aliased results are not visible
			// to caller).
			return new B3BuilderStatus(buildResult);

		} catch (OperationCanceledException e) {
			return B3BuilderStatus.CANCEL_STATUS;

		} catch (Throwable t) {
			return B3BuilderStatus.error("Builder Job Failed - see details", t);
		}
	}

	/**
	 * Collect result, merges results according to aliases/groups, and assigns these as 
	 * unmodifiable values in the context.
	 * @param jobsToRun
	 * @return MultiStatus if result was not ok, otherwise B3BuilderStatus
	 */
	private IStatus collectResult(List<B3BuilderJob> jobsToRun) throws B3EngineException {
		MultiStatus ms = new MultiStatus(B3BuildActivator.instance.getBundle().getSymbolicName(), 0, "", null);
		for(B3BuilderJob job : jobsToRun) {
			IStatus s = job.getResult();
			if(s == null) 
				s = Status.CANCEL_STATUS; // unfinished, never scheduled etc...
			ms.add(s);
		}
		if(! ms.isOK())
			return ms;
		
		// collection of all as "input", and collect per "alias"
		//
		// create the resulting map, and make sure there is at least an empty BuildResult
		// (i.e. when no effective input was declared).
		Map<String, BuildResult> resultMap = new HashMap<String, BuildResult>();
		resultMap.put("input", B3BuildFactory.eINSTANCE.createBuildResult());

		for(B3BuilderJob job : jobsToRun) {
			BuildResult r = job.getBuildResult();
			for(String alias : job.getAliases())
				mergeResult(alias, r, resultMap);
			mergeResult("input", r, resultMap); // all are added to "input"
		}
		// define all the BuildResults in the context per respective name
		for(String key : resultMap.keySet())
			ctx.defineFinalValue(key, resultMap.get(key), BuildResult.class);

		return Status.OK_STATUS;
	}
	/**
	 * Merges result per key
	 * @param key
	 * @param add
	 * @param resultMap
	 * @throws B3EngineException - if merging values causes type or immutable violation
	 */
	private void mergeResult(String key, BuildResult add, Map<String, BuildResult> resultMap) throws B3EngineException {
		BuildResult buildResult = resultMap.get(key);
		if(buildResult == null)
			resultMap.put(key, buildResult = B3BuildFactory.eINSTANCE.createBuildResult());
		
		// merge the job result to add into the buildResult
		buildResult.merge(add);
	}
	private void runSequential(List<B3BuilderJob> jobsToRun, IProgressMonitor monitor) {
		for(B3BuilderJob job : jobsToRun) {
			if(monitor.isCanceled()) 
				throw new OperationCanceledException();
			job.schedule();
			try {
				job.join();
			} catch (InterruptedException e) {
				// TODO What to do on interrupted? There should be no interruptions...
				// Maybe have some watch dog that times out if a job takes too long (? hours?)
				e.printStackTrace();
			}
			if(job.getResult() == null || !job.getResult().isOK())
				return; // stop scheduling, let collect result deal with what is wrong
		}
		
	}

	private void runParallel(List<B3BuilderJob> jobsToRun, IProgressMonitor monitor) {
		for(B3BuilderJob job : jobsToRun) {
			if(monitor.isCanceled()) 
				throw new OperationCanceledException();
			job.setFamily(this);
			job.schedule();
		}
		
		try {
			// wait for all of the scheduled jobs.
			getJobManager().join(this, monitor);
			
		} catch (InterruptedException e) {
			// TODO What to do on interrupted? There should be no interruptions...
			// Maybe have some watch dog that times out if a job takes too long (? hours?)
			e.printStackTrace();
		}
		// in case the wait was canceled
		if(monitor.isCanceled())
			throw new OperationCanceledException();
		
	}
	private void setFamily(B3BuilderJob parent) {
		this.parent = parent;
	}
	private void setAliases(List<String> aliases) {
		this.aliases = aliases;
	}
	private List<String> getAliases() {
		if(aliases == null) {
			return Collections.emptyList();
		}
		return aliases;
	}
	
	@Override
	public boolean belongsTo(Object family) {
		if(family instanceof B3BuilderJob && ((B3BuilderJob)family) == parent)
			return true;
		return super.belongsTo(family);
	}
	/**
	 * Obtains the result of the job, and the BuildResult from the returned status.
	 * If the job state is not OK, an IllegalStateException is thrown.
	 * @return the BuildResult
	 * @throws IllegalStateException (if this method is called when state is not OK).
	 */
	public BuildResult getBuildResult() {
		IStatus r = getResult();
		if(r != null && r.isOK())
			return ((B3BuilderStatus)r).getBuildResult();
		throw new IllegalStateException("Can not obtain result when job state is not OK");
	}
}