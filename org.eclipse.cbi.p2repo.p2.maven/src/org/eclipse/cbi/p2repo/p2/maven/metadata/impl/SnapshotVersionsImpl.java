/**
 */
package org.eclipse.cbi.p2repo.p2.maven.metadata.impl;

import java.util.Collection;

import org.eclipse.cbi.p2repo.p2.maven.metadata.MetadataPackage;
import org.eclipse.cbi.p2repo.p2.maven.metadata.SnapshotVersion;
import org.eclipse.cbi.p2repo.p2.maven.metadata.SnapshotVersions;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Snapshot Versions</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.cbi.p2repo.p2.maven.metadata.impl.SnapshotVersionsImpl#getSnapshotVersions <em>Snapshot Versions</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SnapshotVersionsImpl extends EObjectImpl implements SnapshotVersions {
	/**
	 * The cached value of the '{@link #getSnapshotVersions() <em>Snapshot Versions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSnapshotVersions()
	 * @generated
	 * @ordered
	 */
	protected EList<SnapshotVersion> snapshotVersions;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SnapshotVersionsImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MetadataPackage.Literals.SNAPSHOT_VERSIONS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<SnapshotVersion> getSnapshotVersions() {
		if (snapshotVersions == null) {
			snapshotVersions = new EObjectContainmentEList<>(SnapshotVersion.class, this,
					MetadataPackage.SNAPSHOT_VERSIONS__SNAPSHOT_VERSIONS);
		}
		return snapshotVersions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case MetadataPackage.SNAPSHOT_VERSIONS__SNAPSHOT_VERSIONS:
				return ((InternalEList<?>) getSnapshotVersions()).basicRemove(otherEnd, msgs);
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
			case MetadataPackage.SNAPSHOT_VERSIONS__SNAPSHOT_VERSIONS:
				return getSnapshotVersions();
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
			case MetadataPackage.SNAPSHOT_VERSIONS__SNAPSHOT_VERSIONS:
				getSnapshotVersions().clear();
				getSnapshotVersions().addAll((Collection<? extends SnapshotVersion>) newValue);
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
			case MetadataPackage.SNAPSHOT_VERSIONS__SNAPSHOT_VERSIONS:
				getSnapshotVersions().clear();
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
			case MetadataPackage.SNAPSHOT_VERSIONS__SNAPSHOT_VERSIONS:
				return snapshotVersions != null && !snapshotVersions.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //SnapshotVersionsImpl
