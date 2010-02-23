/**
 * Copyright (c) 2006-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 */

package org.eclipse.b3.aggregator.legacy;

import org.eclipse.b3.aggregator.util.InstallableUnitUtils;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.equinox.internal.p2.metadata.VersionedId;
import org.eclipse.equinox.p2.metadata.VersionRange;

/**
 * Transforms aggregator model instance from version 0.9.0 to 1.0.0
 * 
 * @author Karel Brezina
 */
public class AggregatorTransformer_090_2_100 extends ResourceTransformer {

	private static final String FEATURE_NODE = "Feature";

	private static final String BUNDLE_NODE = "Bundle";

	private static final String PRODUCT_NODE = "Product";

	private static final String CATEGORY_NODE = "Category";

	private static final String EXCLUSION_RULE_NODE = "ExclusionRule";

	private static final String VALID_CONFIGURATIONS_RULE_NODE = "ValidConfigurationsRule";

	private static final String INSTALLABLE_UNIT_REF = "installableUnit";

	private static final String NAME_ATTR = "name";

	private static final String VERSIONRANGE_ATTR = "versionRange";

	@Override
	protected void transform(EObject srcEObject, TreePath trgtParentTreePath) {
		EClass scrEClass = srcEObject.eClass();

		if(FEATURE_NODE.equals(scrEClass.getName()))
			transformIUNode(srcEObject, trgtParentTreePath);
		else if(BUNDLE_NODE.equals(scrEClass.getName()))
			transformIUNode(srcEObject, trgtParentTreePath);
		else if(PRODUCT_NODE.equals(scrEClass.getName()))
			transformIUNode(srcEObject, trgtParentTreePath);
		else if(CATEGORY_NODE.equals(scrEClass.getName()))
			transformIUNode(srcEObject, trgtParentTreePath);
		else if(EXCLUSION_RULE_NODE.equals(scrEClass.getName()))
			transformIUNode(srcEObject, trgtParentTreePath);
		else if(VALID_CONFIGURATIONS_RULE_NODE.equals(scrEClass.getName()))
			transformIUNode(srcEObject, trgtParentTreePath);
		else
			super.transform(srcEObject, trgtParentTreePath);
	}

	private void transformIUNode(EObject srcEObject, TreePath trgtParentTreePath) {
		EObject srcIU = (EObject) getFeatureValue(srcEObject, INSTALLABLE_UNIT_REF);
		if(srcIU == null)
			return;

		VersionedId versionedId = InstallableUnitUtils.getVersionedNameFromProxy((InternalEObject) srcIU);
		if(versionedId == null || versionedId.getId() == null)
			return;

		EObject iuEObject = createTrgtEObject(srcEObject.eClass().getName(), srcEObject);
		trgtParentTreePath.addToLastSegmentContainer(iuEObject);
		copyAttributes(srcEObject, iuEObject);

		EAttribute trgtNameEAttr = (EAttribute) iuEObject.eClass().getEStructuralFeature(NAME_ATTR);
		iuEObject.eSet(trgtNameEAttr, versionedId.getId());

		EAttribute trgtVersionRangeEAttr = (EAttribute) iuEObject.eClass().getEStructuralFeature(VERSIONRANGE_ATTR);

		if(versionedId.getVersion() != null) {
			VersionRange vr = new VersionRange(versionedId.getVersion(), true, null, true);
			iuEObject.eSet(trgtVersionRangeEAttr, vr);
		}
	}
}
