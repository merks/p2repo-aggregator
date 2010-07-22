package org.eclipse.b3.build.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;

import org.eclipse.b3.backend.core.B3Engine;
import org.eclipse.b3.backend.core.B3EngineException;
import org.eclipse.b3.backend.core.B3InternalError;
import org.eclipse.b3.backend.evaluator.IB3Engine;
import org.eclipse.b3.backend.evaluator.IB3Evaluator;
import org.eclipse.b3.backend.evaluator.b3backend.B3backendFactory;
import org.eclipse.b3.backend.evaluator.b3backend.BExecutionContext;
import org.eclipse.b3.backend.evaluator.b3backend.impl.AbstractB3Executor;
import org.eclipse.b3.build.BeeModel;
import org.eclipse.b3.build.core.runtime.DefaultB3Module;
import org.eclipse.b3.build.engine.AbstractB3EngineExecutor;
import org.eclipse.b3.build.engine.IB3EngineRuntime;
import org.eclipse.b3.build.engine.IB3Runnable;
import org.eclipse.b3.build.functions.BuildFunctions;
import org.eclipse.b3.build.internal.B3BuildActivator;
import org.eclipse.b3.build.repository.IBuildUnitResolver;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;

import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.util.Modules;

public class B3BuildEngine extends B3Engine implements IB3EngineRuntime {

	protected BExecutionContext engineTopOfStack;

	protected BExecutionContext engineBottomOfStack;

	/**
	 * Creates and initializes the engine with a default runtime guice module.
	 */
	public B3BuildEngine() throws B3EngineException {
		super(new DefaultB3Module());
		initialize();
	}

	/**
	 * Creates and initializes the engine with a an overriding guice module.
	 */
	public B3BuildEngine(Module overridingModule) throws B3EngineException {
		super(Modules.override(new DefaultB3Module()).with(overridingModule));
		initialize();
	}

	public Object callFunction(final String name, final Object[] parameters, final Type[] types) throws CoreException {
		try {
			return new AbstractB3Executor<Object>(getContext()) {

				@Override
				protected Object runb3(IProgressMonitor monitor) throws Throwable {
					return getContext().getInjector().getInstance(IB3Evaluator.class).callFunction(
						name, parameters, types, getContext());
				}
			}.run();
		}
		catch(InterruptedException e) {
			throw new CoreException(new Status(IStatus.CANCEL, B3BuildActivator.PLUGIN_ID, "Cancelled", e));
		}
		catch(InvocationTargetException e) {
			throw new CoreException(new Status(
				IStatus.ERROR, B3BuildActivator.PLUGIN_ID, "Exception while calling funcion: " + name, e));
		}
	}

	public void defineBeeModel(BeeModel model) throws B3EngineException {
		try {
			getInjector().getInstance(IB3Evaluator.class).doEvaluate(model, getBuildContext());
		}
		catch(Exception e) {
			throw new B3EngineException("Exception while loading b3 model", e);
		}
		catch(Throwable e) {
			throw (Error) e;
		}
	}

	/**
	 * Although this method is package private, it should only be used to set up the engine in a thread.
	 * 
	 * @return
	 */
	BExecutionContext getBuildContext() {
		return engineTopOfStack;
	}

	/**
	 * Returns the build context parented by the invocation context.
	 */
	@Override
	protected BExecutionContext getContext() {
		return engineTopOfStack;
	}

	/**
	 * Returns the initial injector configured for the engine. (Inner contexts may have created sub injectors).
	 * Always obtain the injector from a BExecutionContext if such a context is available.
	 * 
	 * @return
	 */
	public Injector getInjector() {
		return getBuildContext().getInjector();
	}

	private void initialize() throws B3EngineException {
		engineBottomOfStack = B3backendFactory.eINSTANCE.createBContext();
		engineBottomOfStack.setParentContext(invocationContext);
		engineTopOfStack = engineBottomOfStack;
		try {
			engineTopOfStack.defineFinalValue("b3", this, IB3Engine.class);
		}
		catch(B3EngineException e) {
			throw new B3InternalError("Initialization of B3BuildEngine failed with exception", e);
		}
		// // TODO: Fix this horrible crutch that creates a FunctionUtils instance via guice (to get things
		// // injected), and making it available statically to non guice code.
		// // Remove this construct when everything is moved off evaluation/calls in the model code.
		// //
		// FunctionUtils.instance = getInjector().getInstance(FunctionUtils.class);
		invocationContext.loadFunctions(BuildFunctions.class);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.b3.build.engine.IB3EngineRuntime#pop()
	 */
	// @Override
	public boolean pop() {
		if(engineTopOfStack == engineBottomOfStack)
			return false;
		engineTopOfStack = engineTopOfStack.getParentContext();
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.b3.build.engine.IB3EngineRuntime#printStackTrace()
	 */
	// @Override
	public void printStackTrace() {
		getBuildContext().printStackTrace();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.b3.build.engine.IB3EngineRuntime#push()
	 */
	// @Override
	public void push() {
		engineTopOfStack = engineTopOfStack.createOuterContext();
	}

	/**
	 * Can only be called when a resolution scope has been entered.
	 * 
	 */
	public IStatus resolveAllUnits() {
		final IBuildUnitResolver resolver = getInjector().getInstance(IBuildUnitResolver.class);
		try {
			return new AbstractB3Executor<IStatus>(getContext()) {

				@Override
				protected IStatus runb3(IProgressMonitor monitor) throws Throwable {
					return resolver.resolveAll(getBuildContext());
				}
			}.run();
		}
		catch(Exception e) {
			return new Status(
				IStatus.ERROR, B3BuildActivator.instance.getBundle().getSymbolicName(),
				"Running the resolver.ResolveAll ended with exception", e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.b3.backend.core.IB3EngineRuntime#run(org.eclipse.b3.backend.core.IB3Runnable)
	 */
	// @Override
	public IStatus run(IB3Runnable runnable) {
		return run(runnable, new NullProgressMonitor());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.b3.backend.core.IB3EngineRuntime#run(org.eclipse.b3.backend.core.IB3Runnable, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public IStatus run(final IB3Runnable runnable, IProgressMonitor monitor) {
		final BExecutionContext ctx = getBuildContext();
		ctx.setProgressMonitor(monitor);
		try {
			return new AbstractB3EngineExecutor(this, ctx) {

				@Override
				protected IStatus runb3(IB3EngineRuntime engine, IProgressMonitor monitor)
						throws InvocationTargetException, InterruptedException {
					return runnable.run(engine, monitor);
				}
			}.run();
		}
		catch(Throwable e) {
			throw new RuntimeException(
				"Should not happen - AbstractB3EngineExecutor should have baked exception into returned status", e);
		}
		finally {
			ctx.setProgressMonitor(null);
		}
	}
}
