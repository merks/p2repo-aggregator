/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.b3.build.build.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;

import org.eclipse.b3.backend.core.LValue;
import org.eclipse.b3.backend.evaluator.b3backend.BExecutionContext;
import org.eclipse.b3.backend.evaluator.b3backend.BExpression;
import org.eclipse.b3.backend.evaluator.b3backend.IFunction;

import org.eclipse.b3.build.build.B3BuildPackage;
import org.eclipse.b3.build.build.BuildContext;
import org.eclipse.b3.build.build.BuildUnit;
import org.eclipse.b3.build.build.Builder;
import org.eclipse.b3.build.build.BuilderConcernContext;
import org.eclipse.b3.build.build.Capability;
import org.eclipse.b3.build.build.IProvidedCapabilityContainer;
import org.eclipse.b3.build.build.IRequiredCapabilityContainer;
import org.eclipse.b3.build.build.ProvidesPredicate;
import org.eclipse.b3.build.build.RequiredCapability;
import org.eclipse.b3.build.build.RequiresPredicate;
import org.eclipse.b3.build.build.UnitConcernContext;
import org.eclipse.b3.build.build.VersionedCapability;
import org.eclipse.b3.build.core.BuildUnitProxyAdapterFactory;
import org.eclipse.b3.build.core.EffectiveUnitIterator;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Unit Concern Context</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.b3.build.build.impl.UnitConcernContextImpl#getRequiredCapabilities <em>Required Capabilities</em>}</li>
 *   <li>{@link org.eclipse.b3.build.build.impl.UnitConcernContextImpl#getProvidedCapabilities <em>Provided Capabilities</em>}</li>
 *   <li>{@link org.eclipse.b3.build.build.impl.UnitConcernContextImpl#getBuilderContexts <em>Builder Contexts</em>}</li>
 *   <li>{@link org.eclipse.b3.build.build.impl.UnitConcernContextImpl#getQuery <em>Query</em>}</li>
 *   <li>{@link org.eclipse.b3.build.build.impl.UnitConcernContextImpl#getRequiresRemovals <em>Requires Removals</em>}</li>
 *   <li>{@link org.eclipse.b3.build.build.impl.UnitConcernContextImpl#getProvidesRemovals <em>Provides Removals</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UnitConcernContextImpl extends BuildConcernContextImpl implements UnitConcernContext {
	/**
	 * The cached value of the '{@link #getRequiredCapabilities() <em>Required Capabilities</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequiredCapabilities()
	 * @generated
	 * @ordered
	 */
	protected EList<RequiredCapability> requiredCapabilities;

	/**
	 * The cached value of the '{@link #getProvidedCapabilities() <em>Provided Capabilities</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProvidedCapabilities()
	 * @generated
	 * @ordered
	 */
	protected EList<Capability> providedCapabilities;

	/**
	 * The cached value of the '{@link #getBuilderContexts() <em>Builder Contexts</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBuilderContexts()
	 * @generated
	 * @ordered
	 */
	protected EList<BuilderConcernContext> builderContexts;

	/**
	 * The cached value of the '{@link #getQuery() <em>Query</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQuery()
	 * @generated
	 * @ordered
	 */
	protected BExpression query;

	/**
	 * The cached value of the '{@link #getRequiresRemovals() <em>Requires Removals</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequiresRemovals()
	 * @generated
	 * @ordered
	 */
	protected EList<RequiresPredicate> requiresRemovals;

	/**
	 * The cached value of the '{@link #getProvidesRemovals() <em>Provides Removals</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProvidesRemovals()
	 * @generated
	 * @ordered
	 */
	protected EList<ProvidesPredicate> providesRemovals;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UnitConcernContextImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return B3BuildPackage.Literals.UNIT_CONCERN_CONTEXT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<RequiredCapability> getRequiredCapabilities() {
		if (requiredCapabilities == null) {
			requiredCapabilities = new EObjectContainmentEList<RequiredCapability>(RequiredCapability.class, this, B3BuildPackage.UNIT_CONCERN_CONTEXT__REQUIRED_CAPABILITIES);
		}
		return requiredCapabilities;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Capability> getProvidedCapabilities() {
		if (providedCapabilities == null) {
			providedCapabilities = new EObjectContainmentEList<Capability>(Capability.class, this, B3BuildPackage.UNIT_CONCERN_CONTEXT__PROVIDED_CAPABILITIES);
		}
		return providedCapabilities;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<BuilderConcernContext> getBuilderContexts() {
		if (builderContexts == null) {
			builderContexts = new EObjectContainmentEList<BuilderConcernContext>(BuilderConcernContext.class, this, B3BuildPackage.UNIT_CONCERN_CONTEXT__BUILDER_CONTEXTS);
		}
		return builderContexts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BExpression getQuery() {
		return query;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetQuery(BExpression newQuery, NotificationChain msgs) {
		BExpression oldQuery = query;
		query = newQuery;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, B3BuildPackage.UNIT_CONCERN_CONTEXT__QUERY, oldQuery, newQuery);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setQuery(BExpression newQuery) {
		if (newQuery != query) {
			NotificationChain msgs = null;
			if (query != null)
				msgs = ((InternalEObject)query).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - B3BuildPackage.UNIT_CONCERN_CONTEXT__QUERY, null, msgs);
			if (newQuery != null)
				msgs = ((InternalEObject)newQuery).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - B3BuildPackage.UNIT_CONCERN_CONTEXT__QUERY, null, msgs);
			msgs = basicSetQuery(newQuery, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, B3BuildPackage.UNIT_CONCERN_CONTEXT__QUERY, newQuery, newQuery));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<RequiresPredicate> getRequiresRemovals() {
		if (requiresRemovals == null) {
			requiresRemovals = new EObjectContainmentEList<RequiresPredicate>(RequiresPredicate.class, this, B3BuildPackage.UNIT_CONCERN_CONTEXT__REQUIRES_REMOVALS);
		}
		return requiresRemovals;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ProvidesPredicate> getProvidesRemovals() {
		if (providesRemovals == null) {
			providesRemovals = new EObjectContainmentEList<ProvidesPredicate>(ProvidesPredicate.class, this, B3BuildPackage.UNIT_CONCERN_CONTEXT__PROVIDES_REMOVALS);
		}
		return providesRemovals;
	}

	/**
	 * <!-- begin-user-doc -->
	 * Throws {@link UnsupportedOperationException} - call this method on advised units instead.
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList<RequiredCapability> getEffectiveRequirements(BExecutionContext ctx) throws Throwable {
		// should throw unsupported - not meaningful to call.
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case B3BuildPackage.UNIT_CONCERN_CONTEXT__REQUIRED_CAPABILITIES:
				return ((InternalEList<?>)getRequiredCapabilities()).basicRemove(otherEnd, msgs);
			case B3BuildPackage.UNIT_CONCERN_CONTEXT__PROVIDED_CAPABILITIES:
				return ((InternalEList<?>)getProvidedCapabilities()).basicRemove(otherEnd, msgs);
			case B3BuildPackage.UNIT_CONCERN_CONTEXT__BUILDER_CONTEXTS:
				return ((InternalEList<?>)getBuilderContexts()).basicRemove(otherEnd, msgs);
			case B3BuildPackage.UNIT_CONCERN_CONTEXT__QUERY:
				return basicSetQuery(null, msgs);
			case B3BuildPackage.UNIT_CONCERN_CONTEXT__REQUIRES_REMOVALS:
				return ((InternalEList<?>)getRequiresRemovals()).basicRemove(otherEnd, msgs);
			case B3BuildPackage.UNIT_CONCERN_CONTEXT__PROVIDES_REMOVALS:
				return ((InternalEList<?>)getProvidesRemovals()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case B3BuildPackage.UNIT_CONCERN_CONTEXT__REQUIRED_CAPABILITIES:
				return getRequiredCapabilities();
			case B3BuildPackage.UNIT_CONCERN_CONTEXT__PROVIDED_CAPABILITIES:
				return getProvidedCapabilities();
			case B3BuildPackage.UNIT_CONCERN_CONTEXT__BUILDER_CONTEXTS:
				return getBuilderContexts();
			case B3BuildPackage.UNIT_CONCERN_CONTEXT__QUERY:
				return getQuery();
			case B3BuildPackage.UNIT_CONCERN_CONTEXT__REQUIRES_REMOVALS:
				return getRequiresRemovals();
			case B3BuildPackage.UNIT_CONCERN_CONTEXT__PROVIDES_REMOVALS:
				return getProvidesRemovals();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case B3BuildPackage.UNIT_CONCERN_CONTEXT__REQUIRED_CAPABILITIES:
				getRequiredCapabilities().clear();
				getRequiredCapabilities().addAll((Collection<? extends RequiredCapability>)newValue);
				return;
			case B3BuildPackage.UNIT_CONCERN_CONTEXT__PROVIDED_CAPABILITIES:
				getProvidedCapabilities().clear();
				getProvidedCapabilities().addAll((Collection<? extends Capability>)newValue);
				return;
			case B3BuildPackage.UNIT_CONCERN_CONTEXT__BUILDER_CONTEXTS:
				getBuilderContexts().clear();
				getBuilderContexts().addAll((Collection<? extends BuilderConcernContext>)newValue);
				return;
			case B3BuildPackage.UNIT_CONCERN_CONTEXT__QUERY:
				setQuery((BExpression)newValue);
				return;
			case B3BuildPackage.UNIT_CONCERN_CONTEXT__REQUIRES_REMOVALS:
				getRequiresRemovals().clear();
				getRequiresRemovals().addAll((Collection<? extends RequiresPredicate>)newValue);
				return;
			case B3BuildPackage.UNIT_CONCERN_CONTEXT__PROVIDES_REMOVALS:
				getProvidesRemovals().clear();
				getProvidesRemovals().addAll((Collection<? extends ProvidesPredicate>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case B3BuildPackage.UNIT_CONCERN_CONTEXT__REQUIRED_CAPABILITIES:
				getRequiredCapabilities().clear();
				return;
			case B3BuildPackage.UNIT_CONCERN_CONTEXT__PROVIDED_CAPABILITIES:
				getProvidedCapabilities().clear();
				return;
			case B3BuildPackage.UNIT_CONCERN_CONTEXT__BUILDER_CONTEXTS:
				getBuilderContexts().clear();
				return;
			case B3BuildPackage.UNIT_CONCERN_CONTEXT__QUERY:
				setQuery((BExpression)null);
				return;
			case B3BuildPackage.UNIT_CONCERN_CONTEXT__REQUIRES_REMOVALS:
				getRequiresRemovals().clear();
				return;
			case B3BuildPackage.UNIT_CONCERN_CONTEXT__PROVIDES_REMOVALS:
				getProvidesRemovals().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case B3BuildPackage.UNIT_CONCERN_CONTEXT__REQUIRED_CAPABILITIES:
				return requiredCapabilities != null && !requiredCapabilities.isEmpty();
			case B3BuildPackage.UNIT_CONCERN_CONTEXT__PROVIDED_CAPABILITIES:
				return providedCapabilities != null && !providedCapabilities.isEmpty();
			case B3BuildPackage.UNIT_CONCERN_CONTEXT__BUILDER_CONTEXTS:
				return builderContexts != null && !builderContexts.isEmpty();
			case B3BuildPackage.UNIT_CONCERN_CONTEXT__QUERY:
				return query != null;
			case B3BuildPackage.UNIT_CONCERN_CONTEXT__REQUIRES_REMOVALS:
				return requiresRemovals != null && !requiresRemovals.isEmpty();
			case B3BuildPackage.UNIT_CONCERN_CONTEXT__PROVIDES_REMOVALS:
				return providesRemovals != null && !providesRemovals.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == IRequiredCapabilityContainer.class) {
			switch (derivedFeatureID) {
				case B3BuildPackage.UNIT_CONCERN_CONTEXT__REQUIRED_CAPABILITIES: return B3BuildPackage.IREQUIRED_CAPABILITY_CONTAINER__REQUIRED_CAPABILITIES;
				default: return -1;
			}
		}
		if (baseClass == IProvidedCapabilityContainer.class) {
			switch (derivedFeatureID) {
				case B3BuildPackage.UNIT_CONCERN_CONTEXT__PROVIDED_CAPABILITIES: return B3BuildPackage.IPROVIDED_CAPABILITY_CONTAINER__PROVIDED_CAPABILITIES;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == IRequiredCapabilityContainer.class) {
			switch (baseFeatureID) {
				case B3BuildPackage.IREQUIRED_CAPABILITY_CONTAINER__REQUIRED_CAPABILITIES: return B3BuildPackage.UNIT_CONCERN_CONTEXT__REQUIRED_CAPABILITIES;
				default: return -1;
			}
		}
		if (baseClass == IProvidedCapabilityContainer.class) {
			switch (baseFeatureID) {
				case B3BuildPackage.IPROVIDED_CAPABILITY_CONTAINER__PROVIDED_CAPABILITIES: return B3BuildPackage.UNIT_CONCERN_CONTEXT__PROVIDED_CAPABILITIES;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}
	
	/**
	 * Iterates over all BuildUnits defined in the current context, evaluates the predicates, and if matching,
	 * the unit is woven.
	 */
	@Override
	public Object evaluate(BExecutionContext ctx) throws Throwable {
		BExecutionContext ictx = ctx.createInnerContext();
		ictx.defineVariableValue("@test", null, BuildUnit.class);
		LValue lval = ictx.getLValue("@test");
		for(BuildUnit u : new EffectiveUnitIterator(ctx)) {
			lval.set(u);
			weaveIfMatching(u, ictx);
		}
		return this;
	}
	private boolean weaveIfMatching(BuildUnit u, BExecutionContext ctx) throws Throwable {
		Object result = getQuery().evaluate(ctx);
		if(result != Boolean.TRUE)
			return false;
		// weave by creating a copy an then advice it
		BuildUnit clone = BuildUnit.class.cast(EcoreUtil.copy(u));
		// modify the build unit, and store it
		BuildContext bctx = BuildContext.class.cast(ctx.getParentContext());
		// but only of the unit itself was advised (modifying builders does not affect the build unit)
		if(adviseUnit(clone, bctx))
			bctx.defineBuildUnit(clone, true);
		return true;
		
	}
	private boolean adviseUnit(BuildUnit u, BuildContext ctx) throws Throwable {
//		builderContexts
		boolean modified = false;
		
		// removal of provided capabilities
		ListIterator<Capability> pcItor = getProvidedCapabilities().listIterator();
		while(pcItor.hasNext()) {
			Capability pc = pcItor.next();
			for(ProvidesPredicate prem : getProvidesRemovals())
				if(pc instanceof VersionedCapability ? prem.matches((VersionedCapability.class.cast(pc))) : prem.matches(pc)) {
					pcItor.remove();
					modified = true;
				}
		}
		// addition of provided capabilities
		for(Capability pc : getProvidedCapabilities()) {
			pcItor.add(Capability.class.cast(EcoreUtil.copy(pc)));
			modified = true;
		}
		// removal of required capabilities
		ListIterator<RequiredCapability> rcItor = getRequiredCapabilities().listIterator();
		while(rcItor.hasNext()) {
			RequiredCapability rc = rcItor.next();
			for(RequiresPredicate rrem : getRequiresRemovals()) {
				if(rrem.matches(rc)) {
					rcItor.remove();
					modified = true;
				}
			}
		}
		// addition of required capabilities
		for(RequiredCapability rc : getRequiredCapabilities()) {
			rcItor.add(RequiredCapability.class.cast(EcoreUtil.copy(rc)));
			modified = true;
		}
		modified = adviseUnitBuilders(u, ctx) || modified ;
		
		// define additional builders
		// these builders are contained in a UnitConcernContext (no surprise) - they do not have a first parameter
		// set (they can't since it is not known which units they will be defined for in advance). Copies must
		// be made, and each copy installed for the unit that was matched.
		EList<IFunction> fList = getFunctions();
		Class<? extends BuildUnit> iFace = BuildUnitProxyAdapterFactory.eINSTANCE.adapt(u).getIface();
		for(IFunction f : fList) {
			Builder clone = Builder.class.cast(EcoreUtil.copy(f));
			clone.setUnitType(iFace);
			ctx.defineFunction(clone);
			modified = true;
		}
		return modified;
	}
	
	private boolean adviseUnitBuilders(BuildUnit u, BExecutionContext ctx) throws Throwable {
		boolean modified = false;
		BuildUnit proxy = BuildUnitProxyAdapterFactory.eINSTANCE.adapt(u).getProxy();
		Iterator<IFunction> fItor = ctx.getFunctionIterator(proxy.getClass(), Builder.class);
		while(fItor.hasNext()) {
			IFunction candidate = fItor.next();
			for(BuilderConcernContext bx : getBuilderContexts()) {
				if(bx.evaluateIfMatching(candidate, ctx))
					modified = true;
				}
			}
		return modified;
	}
} //UnitConcernContextImpl
