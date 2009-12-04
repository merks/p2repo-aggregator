/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.b3.backend.evaluator.b3backend.impl;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Collection;
import org.eclipse.b3.backend.evaluator.b3backend.B3backendPackage;
import org.eclipse.b3.backend.evaluator.b3backend.BExecutionContext;
import org.eclipse.b3.backend.evaluator.b3backend.BFunction;
import org.eclipse.b3.backend.evaluator.b3backend.BGuard;

import org.eclipse.b3.backend.evaluator.b3backend.BParameterDeclaration;
import org.eclipse.b3.backend.evaluator.b3backend.ExecutionMode;
import org.eclipse.b3.backend.evaluator.b3backend.Visibility;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.osgi.internal.resolver.ComputeNodeOrder;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>BFunction</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.b3.backend.evaluator.b3backend.impl.BFunctionImpl#getVisibility <em>Visibility</em>}</li>
 *   <li>{@link org.eclipse.b3.backend.evaluator.b3backend.impl.BFunctionImpl#isFinal <em>Final</em>}</li>
 *   <li>{@link org.eclipse.b3.backend.evaluator.b3backend.impl.BFunctionImpl#getExecutionMode <em>Execution Mode</em>}</li>
 *   <li>{@link org.eclipse.b3.backend.evaluator.b3backend.impl.BFunctionImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.b3.backend.evaluator.b3backend.impl.BFunctionImpl#getGuard <em>Guard</em>}</li>
 *   <li>{@link org.eclipse.b3.backend.evaluator.b3backend.impl.BFunctionImpl#getParameterTypes <em>Parameter Types</em>}</li>
 *   <li>{@link org.eclipse.b3.backend.evaluator.b3backend.impl.BFunctionImpl#getExceptionTypes <em>Exception Types</em>}</li>
 *   <li>{@link org.eclipse.b3.backend.evaluator.b3backend.impl.BFunctionImpl#getTypeParameters <em>Type Parameters</em>}</li>
 *   <li>{@link org.eclipse.b3.backend.evaluator.b3backend.impl.BFunctionImpl#getParameterNames <em>Parameter Names</em>}</li>
 *   <li>{@link org.eclipse.b3.backend.evaluator.b3backend.impl.BFunctionImpl#isVarArgs <em>Var Args</em>}</li>
 *   <li>{@link org.eclipse.b3.backend.evaluator.b3backend.impl.BFunctionImpl#getParameters <em>Parameters</em>}</li>
 *   <li>{@link org.eclipse.b3.backend.evaluator.b3backend.impl.BFunctionImpl#getDocumentation <em>Documentation</em>}</li>
 *   <li>{@link org.eclipse.b3.backend.evaluator.b3backend.impl.BFunctionImpl#getReturnType <em>Return Type</em>}</li>
 *   <li>{@link org.eclipse.b3.backend.evaluator.b3backend.impl.BFunctionImpl#isCached <em>Cached</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class BFunctionImpl extends BExpressionImpl implements BFunction {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2009, Cloudsmith Inc and others.\nAll rights reserved. This program and the accompanying materials\nare made available under the terms of the Eclipse Public License v1.0\nwhich accompanies this distribution, and is available at\nhttp://www.eclipse.org/legal/epl-v10.html\n\rContributors:\n- Cloudsmith Inc - initial API and implementation.\r";

	/**
	 * The default value of the '{@link #getVisibility() <em>Visibility</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVisibility()
	 * @generated
	 * @ordered
	 */
	protected static final Visibility VISIBILITY_EDEFAULT = Visibility.PRIVATE;

	/**
	 * The cached value of the '{@link #getVisibility() <em>Visibility</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVisibility()
	 * @generated
	 * @ordered
	 */
	protected Visibility visibility = VISIBILITY_EDEFAULT;

	/**
	 * The default value of the '{@link #isFinal() <em>Final</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFinal()
	 * @generated
	 * @ordered
	 */
	protected static final boolean FINAL_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isFinal() <em>Final</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFinal()
	 * @generated
	 * @ordered
	 */
	protected boolean final_ = FINAL_EDEFAULT;

	/**
	 * The default value of the '{@link #getExecutionMode() <em>Execution Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExecutionMode()
	 * @generated
	 * @ordered
	 */
	protected static final ExecutionMode EXECUTION_MODE_EDEFAULT = ExecutionMode.SEQUENTIAL;

	/**
	 * The cached value of the '{@link #getExecutionMode() <em>Execution Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExecutionMode()
	 * @generated
	 * @ordered
	 */
	protected ExecutionMode executionMode = EXECUTION_MODE_EDEFAULT;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getGuard() <em>Guard</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGuard()
	 * @generated
	 * @ordered
	 */
	protected BGuard guard;

	/**
	 * The default value of the '{@link #getParameterTypes() <em>Parameter Types</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameterTypes()
	 * @generated
	 * @ordered
	 */
	protected static final Type[] PARAMETER_TYPES_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getParameterTypes() <em>Parameter Types</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameterTypes()
	 * @generated
	 * @ordered
	 */
	protected Type[] parameterTypes = PARAMETER_TYPES_EDEFAULT;

	/**
	 * The default value of the '{@link #getExceptionTypes() <em>Exception Types</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExceptionTypes()
	 * @generated
	 * @ordered
	 */
	protected static final Type[] EXCEPTION_TYPES_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getExceptionTypes() <em>Exception Types</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExceptionTypes()
	 * @generated
	 * @ordered
	 */
	protected Type[] exceptionTypes = EXCEPTION_TYPES_EDEFAULT;

	/**
	 * The default value of the '{@link #getTypeParameters() <em>Type Parameters</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypeParameters()
	 * @generated
	 * @ordered
	 */
	protected static final TypeVariable[] TYPE_PARAMETERS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTypeParameters() <em>Type Parameters</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypeParameters()
	 * @generated
	 * @ordered
	 */
	protected TypeVariable[] typeParameters = TYPE_PARAMETERS_EDEFAULT;

	/**
	 * The default value of the '{@link #getParameterNames() <em>Parameter Names</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameterNames()
	 * @generated
	 * @ordered
	 */
	protected static final String[] PARAMETER_NAMES_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getParameterNames() <em>Parameter Names</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameterNames()
	 * @generated
	 * @ordered
	 */
	protected String[] parameterNames = PARAMETER_NAMES_EDEFAULT;

	/**
	 * The default value of the '{@link #isVarArgs() <em>Var Args</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isVarArgs()
	 * @generated
	 * @ordered
	 */
	protected static final boolean VAR_ARGS_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isVarArgs() <em>Var Args</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isVarArgs()
	 * @generated
	 * @ordered
	 */
	protected boolean varArgs = VAR_ARGS_EDEFAULT;

	/**
	 * The cached value of the '{@link #getParameters() <em>Parameters</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<BParameterDeclaration> parameters;

	/**
	 * The default value of the '{@link #getDocumentation() <em>Documentation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDocumentation()
	 * @generated
	 * @ordered
	 */
	protected static final String DOCUMENTATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDocumentation() <em>Documentation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDocumentation()
	 * @generated
	 * @ordered
	 */
	protected String documentation = DOCUMENTATION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getReturnType() <em>Return Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReturnType()
	 * @generated
	 * @ordered
	 */
	protected Type returnType;

	/**
	 * The default value of the '{@link #isCached() <em>Cached</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCached()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CACHED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isCached() <em>Cached</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCached()
	 * @generated
	 * @ordered
	 */
	protected boolean cached = CACHED_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BFunctionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return B3backendPackage.Literals.BFUNCTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Visibility getVisibility() {
		return visibility;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVisibility(Visibility newVisibility) {
		Visibility oldVisibility = visibility;
		visibility = newVisibility == null ? VISIBILITY_EDEFAULT : newVisibility;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, B3backendPackage.BFUNCTION__VISIBILITY, oldVisibility, visibility));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isFinal() {
		return final_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFinal(boolean newFinal) {
		boolean oldFinal = final_;
		final_ = newFinal;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, B3backendPackage.BFUNCTION__FINAL, oldFinal, final_));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionMode getExecutionMode() {
		return executionMode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExecutionMode(ExecutionMode newExecutionMode) {
		ExecutionMode oldExecutionMode = executionMode;
		executionMode = newExecutionMode == null ? EXECUTION_MODE_EDEFAULT : newExecutionMode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, B3backendPackage.BFUNCTION__EXECUTION_MODE, oldExecutionMode, executionMode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, B3backendPackage.BFUNCTION__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BGuard getGuard() {
		return guard;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGuard(BGuard newGuard, NotificationChain msgs) {
		BGuard oldGuard = guard;
		guard = newGuard;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, B3backendPackage.BFUNCTION__GUARD, oldGuard, newGuard);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGuard(BGuard newGuard) {
		if (newGuard != guard) {
			NotificationChain msgs = null;
			if (guard != null)
				msgs = ((InternalEObject)guard).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - B3backendPackage.BFUNCTION__GUARD, null, msgs);
			if (newGuard != null)
				msgs = ((InternalEObject)newGuard).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - B3backendPackage.BFUNCTION__GUARD, null, msgs);
			msgs = basicSetGuard(newGuard, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, B3backendPackage.BFUNCTION__GUARD, newGuard, newGuard));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public Type getReturnType() {
		// NOTE: Must check if return type is an EObject
		if (returnType != null && returnType instanceof EObject && ((EObject)returnType).eIsProxy()) {
			InternalEObject oldReturnType = (InternalEObject)returnType;
			returnType = (Type)eResolveProxy(oldReturnType);
			if (returnType != oldReturnType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, B3backendPackage.BFUNCTION__RETURN_TYPE, oldReturnType, returnType));
			}
		}
		return returnType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetReturnType(Type newReturnType, NotificationChain msgs) {
		Type oldReturnType = returnType;
		returnType = newReturnType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, B3backendPackage.BFUNCTION__RETURN_TYPE, oldReturnType, newReturnType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * This method may be called with non EObject implementations of Type. If so, then no notification
	 * is done on change.
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setReturnType(Type newReturnType) {
		if(! (returnType instanceof EObject && newReturnType instanceof EObject) )
			returnType = newReturnType;
		else {
		if (newReturnType != returnType) {
			NotificationChain msgs = null;
			if (returnType != null)
				msgs = ((InternalEObject)returnType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - B3backendPackage.BFUNCTION__RETURN_TYPE, null, msgs);
			if (newReturnType != null)
				msgs = ((InternalEObject)newReturnType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - B3backendPackage.BFUNCTION__RETURN_TYPE, null, msgs);
			msgs = basicSetReturnType(newReturnType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, B3backendPackage.BFUNCTION__RETURN_TYPE, newReturnType, newReturnType));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isCached() {
		return cached;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCached(boolean newCached) {
		boolean oldCached = cached;
		cached = newCached;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, B3backendPackage.BFUNCTION__CACHED, oldCached, cached));
	}

	/**
	 * <!-- begin-user-doc -->
	 * Returns an array of the parameter types (if already set it is returned, if null, it is calculated
	 * from the list of parameter declarations).
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public Type[] getParameterTypes() {
		if(parameterTypes != null)
			return parameterTypes;
		EList<BParameterDeclaration> pList = getParameters();
		Type[] pTypes = new Type[pList.size()];
		int i = 0;
		for(BParameterDeclaration p : pList) {
			pTypes[i++] = p.getType();
		}
		setParameterTypes(pTypes);
		return parameterTypes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParameterTypes(Type[] newParameterTypes) {
		Type[] oldParameterTypes = parameterTypes;
		parameterTypes = newParameterTypes;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, B3backendPackage.BFUNCTION__PARAMETER_TYPES, oldParameterTypes, parameterTypes));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type[] getExceptionTypes() {
		return exceptionTypes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExceptionTypes(Type[] newExceptionTypes) {
		Type[] oldExceptionTypes = exceptionTypes;
		exceptionTypes = newExceptionTypes;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, B3backendPackage.BFUNCTION__EXCEPTION_TYPES, oldExceptionTypes, exceptionTypes));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public TypeVariable[] getTypeParameters() {
		return typeParameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTypeParameters(TypeVariable[] newTypeParameters) {
		TypeVariable[] oldTypeParameters = typeParameters;
		typeParameters = newTypeParameters;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, B3backendPackage.BFUNCTION__TYPE_PARAMETERS, oldTypeParameters, typeParameters));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String[] getParameterNames() {
		if(parameterNames == null)
			computeParameters();

		return parameterNames;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParameterNames(String[] newParameterNames) {
		String[] oldParameterNames = parameterNames;
		parameterNames = newParameterNames;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, B3backendPackage.BFUNCTION__PARAMETER_NAMES, oldParameterNames, parameterNames));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isVarArgs() {
		return varArgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVarArgs(boolean newVarArgs) {
		boolean oldVarArgs = varArgs;
		varArgs = newVarArgs;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, B3backendPackage.BFUNCTION__VAR_ARGS, oldVarArgs, varArgs));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<BParameterDeclaration> getParameters() {
		if (parameters == null) {
			parameters = new EObjectContainmentEList<BParameterDeclaration>(BParameterDeclaration.class, this, B3backendPackage.BFUNCTION__PARAMETERS);
		}
		return parameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDocumentation() {
		return documentation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDocumentation(String newDocumentation) {
		String oldDocumentation = documentation;
		documentation = newDocumentation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, B3backendPackage.BFUNCTION__DOCUMENTATION, oldDocumentation, documentation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object internalCall(BExecutionContext ctx, Object[] parameters, Type[] types) throws Throwable {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
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
			case B3backendPackage.BFUNCTION__GUARD:
				return basicSetGuard(null, msgs);
			case B3backendPackage.BFUNCTION__PARAMETERS:
				return ((InternalEList<?>)getParameters()).basicRemove(otherEnd, msgs);
			case B3backendPackage.BFUNCTION__RETURN_TYPE:
				return basicSetReturnType(null, msgs);
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
			case B3backendPackage.BFUNCTION__VISIBILITY:
				return getVisibility();
			case B3backendPackage.BFUNCTION__FINAL:
				return isFinal();
			case B3backendPackage.BFUNCTION__EXECUTION_MODE:
				return getExecutionMode();
			case B3backendPackage.BFUNCTION__NAME:
				return getName();
			case B3backendPackage.BFUNCTION__GUARD:
				return getGuard();
			case B3backendPackage.BFUNCTION__PARAMETER_TYPES:
				return getParameterTypes();
			case B3backendPackage.BFUNCTION__EXCEPTION_TYPES:
				return getExceptionTypes();
			case B3backendPackage.BFUNCTION__TYPE_PARAMETERS:
				return getTypeParameters();
			case B3backendPackage.BFUNCTION__PARAMETER_NAMES:
				return getParameterNames();
			case B3backendPackage.BFUNCTION__VAR_ARGS:
				return isVarArgs();
			case B3backendPackage.BFUNCTION__PARAMETERS:
				return getParameters();
			case B3backendPackage.BFUNCTION__DOCUMENTATION:
				return getDocumentation();
			case B3backendPackage.BFUNCTION__RETURN_TYPE:
				return getReturnType();
			case B3backendPackage.BFUNCTION__CACHED:
				return isCached();
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
			case B3backendPackage.BFUNCTION__VISIBILITY:
				setVisibility((Visibility)newValue);
				return;
			case B3backendPackage.BFUNCTION__FINAL:
				setFinal((Boolean)newValue);
				return;
			case B3backendPackage.BFUNCTION__EXECUTION_MODE:
				setExecutionMode((ExecutionMode)newValue);
				return;
			case B3backendPackage.BFUNCTION__NAME:
				setName((String)newValue);
				return;
			case B3backendPackage.BFUNCTION__GUARD:
				setGuard((BGuard)newValue);
				return;
			case B3backendPackage.BFUNCTION__PARAMETER_TYPES:
				setParameterTypes((Type[])newValue);
				return;
			case B3backendPackage.BFUNCTION__EXCEPTION_TYPES:
				setExceptionTypes((Type[])newValue);
				return;
			case B3backendPackage.BFUNCTION__TYPE_PARAMETERS:
				setTypeParameters((TypeVariable[])newValue);
				return;
			case B3backendPackage.BFUNCTION__PARAMETER_NAMES:
				setParameterNames((String[])newValue);
				return;
			case B3backendPackage.BFUNCTION__VAR_ARGS:
				setVarArgs((Boolean)newValue);
				return;
			case B3backendPackage.BFUNCTION__PARAMETERS:
				getParameters().clear();
				getParameters().addAll((Collection<? extends BParameterDeclaration>)newValue);
				return;
			case B3backendPackage.BFUNCTION__DOCUMENTATION:
				setDocumentation((String)newValue);
				return;
			case B3backendPackage.BFUNCTION__RETURN_TYPE:
				setReturnType((Type)newValue);
				return;
			case B3backendPackage.BFUNCTION__CACHED:
				setCached((Boolean)newValue);
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
			case B3backendPackage.BFUNCTION__VISIBILITY:
				setVisibility(VISIBILITY_EDEFAULT);
				return;
			case B3backendPackage.BFUNCTION__FINAL:
				setFinal(FINAL_EDEFAULT);
				return;
			case B3backendPackage.BFUNCTION__EXECUTION_MODE:
				setExecutionMode(EXECUTION_MODE_EDEFAULT);
				return;
			case B3backendPackage.BFUNCTION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case B3backendPackage.BFUNCTION__GUARD:
				setGuard((BGuard)null);
				return;
			case B3backendPackage.BFUNCTION__PARAMETER_TYPES:
				setParameterTypes(PARAMETER_TYPES_EDEFAULT);
				return;
			case B3backendPackage.BFUNCTION__EXCEPTION_TYPES:
				setExceptionTypes(EXCEPTION_TYPES_EDEFAULT);
				return;
			case B3backendPackage.BFUNCTION__TYPE_PARAMETERS:
				setTypeParameters(TYPE_PARAMETERS_EDEFAULT);
				return;
			case B3backendPackage.BFUNCTION__PARAMETER_NAMES:
				setParameterNames(PARAMETER_NAMES_EDEFAULT);
				return;
			case B3backendPackage.BFUNCTION__VAR_ARGS:
				setVarArgs(VAR_ARGS_EDEFAULT);
				return;
			case B3backendPackage.BFUNCTION__PARAMETERS:
				getParameters().clear();
				return;
			case B3backendPackage.BFUNCTION__DOCUMENTATION:
				setDocumentation(DOCUMENTATION_EDEFAULT);
				return;
			case B3backendPackage.BFUNCTION__RETURN_TYPE:
				setReturnType((Type)null);
				return;
			case B3backendPackage.BFUNCTION__CACHED:
				setCached(CACHED_EDEFAULT);
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
			case B3backendPackage.BFUNCTION__VISIBILITY:
				return visibility != VISIBILITY_EDEFAULT;
			case B3backendPackage.BFUNCTION__FINAL:
				return final_ != FINAL_EDEFAULT;
			case B3backendPackage.BFUNCTION__EXECUTION_MODE:
				return executionMode != EXECUTION_MODE_EDEFAULT;
			case B3backendPackage.BFUNCTION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case B3backendPackage.BFUNCTION__GUARD:
				return guard != null;
			case B3backendPackage.BFUNCTION__PARAMETER_TYPES:
				return PARAMETER_TYPES_EDEFAULT == null ? parameterTypes != null : !PARAMETER_TYPES_EDEFAULT.equals(parameterTypes);
			case B3backendPackage.BFUNCTION__EXCEPTION_TYPES:
				return EXCEPTION_TYPES_EDEFAULT == null ? exceptionTypes != null : !EXCEPTION_TYPES_EDEFAULT.equals(exceptionTypes);
			case B3backendPackage.BFUNCTION__TYPE_PARAMETERS:
				return TYPE_PARAMETERS_EDEFAULT == null ? typeParameters != null : !TYPE_PARAMETERS_EDEFAULT.equals(typeParameters);
			case B3backendPackage.BFUNCTION__PARAMETER_NAMES:
				return PARAMETER_NAMES_EDEFAULT == null ? parameterNames != null : !PARAMETER_NAMES_EDEFAULT.equals(parameterNames);
			case B3backendPackage.BFUNCTION__VAR_ARGS:
				return varArgs != VAR_ARGS_EDEFAULT;
			case B3backendPackage.BFUNCTION__PARAMETERS:
				return parameters != null && !parameters.isEmpty();
			case B3backendPackage.BFUNCTION__DOCUMENTATION:
				return DOCUMENTATION_EDEFAULT == null ? documentation != null : !DOCUMENTATION_EDEFAULT.equals(documentation);
			case B3backendPackage.BFUNCTION__RETURN_TYPE:
				return returnType != null;
			case B3backendPackage.BFUNCTION__CACHED:
				return cached != CACHED_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (visibility: ");
		result.append(visibility);
		result.append(", final: ");
		result.append(final_);
		result.append(", executionMode: ");
		result.append(executionMode);
		result.append(", name: ");
		result.append(name);
		result.append(", parameterTypes: ");
		result.append(parameterTypes);
		result.append(", exceptionTypes: ");
		result.append(exceptionTypes);
		result.append(", typeParameters: ");
		result.append(typeParameters);
		result.append(", parameterNames: ");
		result.append(parameterNames);
		result.append(", varArgs: ");
		result.append(varArgs);
		result.append(", documentation: ");
		result.append(documentation);
		result.append(", cached: ");
		result.append(cached);
		result.append(')');
		return result.toString();
	}
	protected void computeParameters() {
		if(parameterNames == null || parameterTypes == null) {
			EList<BParameterDeclaration> pList = getParameters();
			parameterNames = new String[pList.size()];
			parameterTypes = new Type[pList.size()];
			int i = 0;
			for(BParameterDeclaration p : pList) {
				parameterNames[i] = p.getName();
				parameterTypes[i++] = p.getType();
			}
		}
	}
	
} //BFunctionImpl