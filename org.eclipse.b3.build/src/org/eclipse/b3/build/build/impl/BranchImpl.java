/**
 * Copyright (c) 2010, Cloudsmith Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 * - Cloudsmith Inc - initial API and implementation.
 * 
 */
package org.eclipse.b3.build.build.impl;

import java.util.Collection;

import org.eclipse.b3.backend.evaluator.b3backend.BNamePredicate;

import org.eclipse.b3.build.build.B3BuildPackage;
import org.eclipse.b3.build.build.Branch;
import org.eclipse.b3.build.build.BranchPointType;
import org.eclipse.b3.build.build.UpdateStrategy;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Branch</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.b3.build.build.impl.BranchImpl#getName <em>Name</em>}</li>
 * <li>{@link org.eclipse.b3.build.build.impl.BranchImpl#getUpdateStrategy <em>Update Strategy</em>}</li>
 * <li>{@link org.eclipse.b3.build.build.impl.BranchImpl#getBranchPointType <em>Branch Point Type</em>}</li>
 * <li>{@link org.eclipse.b3.build.build.impl.BranchImpl#getBranchPoint <em>Branch Point</em>}</li>
 * <li>{@link org.eclipse.b3.build.build.impl.BranchImpl#getInclude <em>Include</em>}</li>
 * <li>{@link org.eclipse.b3.build.build.impl.BranchImpl#getExclude <em>Exclude</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class BranchImpl extends EObjectImpl implements Branch {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getUpdateStrategy() <em>Update Strategy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getUpdateStrategy()
	 * @generated
	 * @ordered
	 */
	protected static final UpdateStrategy UPDATE_STRATEGY_EDEFAULT = UpdateStrategy.BRANCH_POINT_DEFAULT;

	/**
	 * The cached value of the '{@link #getUpdateStrategy() <em>Update Strategy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getUpdateStrategy()
	 * @generated
	 * @ordered
	 */
	protected UpdateStrategy updateStrategy = UPDATE_STRATEGY_EDEFAULT;

	/**
	 * The default value of the '{@link #getBranchPointType() <em>Branch Point Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getBranchPointType()
	 * @generated
	 * @ordered
	 */
	protected static final BranchPointType BRANCH_POINT_TYPE_EDEFAULT = BranchPointType.LATEST;

	/**
	 * The cached value of the '{@link #getBranchPointType() <em>Branch Point Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getBranchPointType()
	 * @generated
	 * @ordered
	 */
	protected BranchPointType branchPointType = BRANCH_POINT_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getBranchPoint() <em>Branch Point</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getBranchPoint()
	 * @generated
	 * @ordered
	 */
	protected static final String BRANCH_POINT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBranchPoint() <em>Branch Point</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getBranchPoint()
	 * @generated
	 * @ordered
	 */
	protected String branchPoint = BRANCH_POINT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getInclude() <em>Include</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getInclude()
	 * @generated
	 * @ordered
	 */
	protected EList<BNamePredicate> include;

	/**
	 * The cached value of the '{@link #getExclude() <em>Exclude</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getExclude()
	 * @generated
	 * @ordered
	 */
	protected EList<BNamePredicate> exclude;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected BranchImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch(featureID) {
			case B3BuildPackage.BRANCH__NAME:
				return getName();
			case B3BuildPackage.BRANCH__UPDATE_STRATEGY:
				return getUpdateStrategy();
			case B3BuildPackage.BRANCH__BRANCH_POINT_TYPE:
				return getBranchPointType();
			case B3BuildPackage.BRANCH__BRANCH_POINT:
				return getBranchPoint();
			case B3BuildPackage.BRANCH__INCLUDE:
				return getInclude();
			case B3BuildPackage.BRANCH__EXCLUDE:
				return getExclude();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch(featureID) {
			case B3BuildPackage.BRANCH__INCLUDE:
				return ((InternalEList<?>) getInclude()).basicRemove(otherEnd, msgs);
			case B3BuildPackage.BRANCH__EXCLUDE:
				return ((InternalEList<?>) getExclude()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch(featureID) {
			case B3BuildPackage.BRANCH__NAME:
				return NAME_EDEFAULT == null
						? name != null
						: !NAME_EDEFAULT.equals(name);
			case B3BuildPackage.BRANCH__UPDATE_STRATEGY:
				return updateStrategy != UPDATE_STRATEGY_EDEFAULT;
			case B3BuildPackage.BRANCH__BRANCH_POINT_TYPE:
				return branchPointType != BRANCH_POINT_TYPE_EDEFAULT;
			case B3BuildPackage.BRANCH__BRANCH_POINT:
				return BRANCH_POINT_EDEFAULT == null
						? branchPoint != null
						: !BRANCH_POINT_EDEFAULT.equals(branchPoint);
			case B3BuildPackage.BRANCH__INCLUDE:
				return include != null && !include.isEmpty();
			case B3BuildPackage.BRANCH__EXCLUDE:
				return exclude != null && !exclude.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch(featureID) {
			case B3BuildPackage.BRANCH__NAME:
				setName((String) newValue);
				return;
			case B3BuildPackage.BRANCH__UPDATE_STRATEGY:
				setUpdateStrategy((UpdateStrategy) newValue);
				return;
			case B3BuildPackage.BRANCH__BRANCH_POINT_TYPE:
				setBranchPointType((BranchPointType) newValue);
				return;
			case B3BuildPackage.BRANCH__BRANCH_POINT:
				setBranchPoint((String) newValue);
				return;
			case B3BuildPackage.BRANCH__INCLUDE:
				getInclude().clear();
				getInclude().addAll((Collection<? extends BNamePredicate>) newValue);
				return;
			case B3BuildPackage.BRANCH__EXCLUDE:
				getExclude().clear();
				getExclude().addAll((Collection<? extends BNamePredicate>) newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return B3BuildPackage.Literals.BRANCH;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch(featureID) {
			case B3BuildPackage.BRANCH__NAME:
				setName(NAME_EDEFAULT);
				return;
			case B3BuildPackage.BRANCH__UPDATE_STRATEGY:
				setUpdateStrategy(UPDATE_STRATEGY_EDEFAULT);
				return;
			case B3BuildPackage.BRANCH__BRANCH_POINT_TYPE:
				setBranchPointType(BRANCH_POINT_TYPE_EDEFAULT);
				return;
			case B3BuildPackage.BRANCH__BRANCH_POINT:
				setBranchPoint(BRANCH_POINT_EDEFAULT);
				return;
			case B3BuildPackage.BRANCH__INCLUDE:
				getInclude().clear();
				return;
			case B3BuildPackage.BRANCH__EXCLUDE:
				getExclude().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getBranchPoint() {
		return branchPoint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public BranchPointType getBranchPointType() {
		return branchPointType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public UpdateStrategy getEffectiveUpdateStrategy() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<BNamePredicate> getExclude() {
		if(exclude == null) {
			exclude = new EObjectContainmentEList<BNamePredicate>(
				BNamePredicate.class, this, B3BuildPackage.BRANCH__EXCLUDE);
		}
		return exclude;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<BNamePredicate> getInclude() {
		if(include == null) {
			include = new EObjectContainmentEList<BNamePredicate>(
				BNamePredicate.class, this, B3BuildPackage.BRANCH__INCLUDE);
		}
		return include;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public UpdateStrategy getUpdateStrategy() {
		return updateStrategy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setBranchPoint(String newBranchPoint) {
		String oldBranchPoint = branchPoint;
		branchPoint = newBranchPoint;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(
				this, Notification.SET, B3BuildPackage.BRANCH__BRANCH_POINT, oldBranchPoint, branchPoint));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setBranchPointType(BranchPointType newBranchPointType) {
		BranchPointType oldBranchPointType = branchPointType;
		branchPointType = newBranchPointType == null
				? BRANCH_POINT_TYPE_EDEFAULT
				: newBranchPointType;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(
				this, Notification.SET, B3BuildPackage.BRANCH__BRANCH_POINT_TYPE, oldBranchPointType, branchPointType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, B3BuildPackage.BRANCH__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setUpdateStrategy(UpdateStrategy newUpdateStrategy) {
		UpdateStrategy oldUpdateStrategy = updateStrategy;
		updateStrategy = newUpdateStrategy == null
				? UPDATE_STRATEGY_EDEFAULT
				: newUpdateStrategy;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(
				this, Notification.SET, B3BuildPackage.BRANCH__UPDATE_STRATEGY, oldUpdateStrategy, updateStrategy));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String toString() {
		if(eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(", updateStrategy: ");
		result.append(updateStrategy);
		result.append(", branchPointType: ");
		result.append(branchPointType);
		result.append(", branchPoint: ");
		result.append(branchPoint);
		result.append(')');
		return result.toString();
	}

} // BranchImpl