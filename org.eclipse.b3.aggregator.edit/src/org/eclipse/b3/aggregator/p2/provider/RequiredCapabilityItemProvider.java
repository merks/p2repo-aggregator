/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.b3.aggregator.p2.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.b3.aggregator.p2.P2Package;
import org.eclipse.b3.aggregator.p2.RequiredCapability;
import org.eclipse.b3.aggregator.provider.AggregatorEditPlugin;
import org.eclipse.b3.aggregator.provider.AggregatorItemProviderAdapter;
import org.eclipse.b3.aggregator.util.CapabilityNamespaceImageProvider;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemColorProvider;
import org.eclipse.emf.edit.provider.IItemFontProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link org.eclipse.b3.aggregator.p2.RequiredCapability} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class RequiredCapabilityItemProvider extends AggregatorItemProviderAdapter implements
		IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider,
		IItemPropertySource, IItemColorProvider, IItemFontProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public RequiredCapabilityItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns RequiredCapability.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public Object getImage(Object object) {
		RequiredCapability rc = (RequiredCapability) object;

		Object image = CapabilityNamespaceImageProvider.getImage(rc.getNamespace());
		if(image == null)
			image = getResourceLocator().getImage("full/obj16/RequiredCapability");

		return overlayImage(object, image);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if(itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addFilterPropertyDescriptor(object);
			addMaxPropertyDescriptor(object);
			addMinPropertyDescriptor(object);
			addGreedyPropertyDescriptor(object);
			addNamePropertyDescriptor(object);
			addNamespacePropertyDescriptor(object);
			addRangePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return AggregatorEditPlugin.INSTANCE;
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((RequiredCapability) object).getName();
		return label == null || label.length() == 0
				? getString("_UI_RequiredCapability_type")
				: getString("_UI_RequiredCapability_type") + " " + label;
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached children and by creating
	 * a viewer notification, which it passes to {@link #fireNotifyChanged}. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch(notification.getFeatureID(RequiredCapability.class)) {
		case P2Package.REQUIRED_CAPABILITY__FILTER:
		case P2Package.REQUIRED_CAPABILITY__MAX:
		case P2Package.REQUIRED_CAPABILITY__MIN:
		case P2Package.REQUIRED_CAPABILITY__GREEDY:
		case P2Package.REQUIRED_CAPABILITY__NAME:
		case P2Package.REQUIRED_CAPABILITY__NAMESPACE:
		case P2Package.REQUIRED_CAPABILITY__RANGE:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
			return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds a property descriptor for the Filter feature.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addFilterPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_IRequirement_filter_feature"), getString("_UI_PropertyDescriptor_description",
						"_UI_IRequirement_filter_feature", "_UI_IRequirement_type"),
				P2Package.Literals.IREQUIREMENT__FILTER, true, false, false,
				ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Greedy feature.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addGreedyPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_IRequirement_greedy_feature"), getString("_UI_PropertyDescriptor_description",
						"_UI_IRequirement_greedy_feature", "_UI_IRequirement_type"),
				P2Package.Literals.IREQUIREMENT__GREEDY, true, false, false,
				ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Max feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addMaxPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_IRequirement_max_feature"), getString("_UI_PropertyDescriptor_description",
						"_UI_IRequirement_max_feature", "_UI_IRequirement_type"), P2Package.Literals.IREQUIREMENT__MAX,
				true, false, false, ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Min feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addMinPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_IRequirement_min_feature"), getString("_UI_PropertyDescriptor_description",
						"_UI_IRequirement_min_feature", "_UI_IRequirement_type"), P2Package.Literals.IREQUIREMENT__MIN,
				true, false, false, ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Name feature.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_IRequiredCapability_name_feature"), getString("_UI_PropertyDescriptor_description",
						"_UI_IRequiredCapability_name_feature", "_UI_IRequiredCapability_type"),
				P2Package.Literals.IREQUIRED_CAPABILITY__NAME, false, false, false,
				ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Namespace feature.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addNamespacePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_IRequiredCapability_namespace_feature"), getString("_UI_PropertyDescriptor_description",
						"_UI_IRequiredCapability_namespace_feature", "_UI_IRequiredCapability_type"),
				P2Package.Literals.IREQUIRED_CAPABILITY__NAMESPACE, false, false, false,
				ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Range feature.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addRangePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_IRequiredCapability_range_feature"), getString("_UI_PropertyDescriptor_description",
						"_UI_IRequiredCapability_range_feature", "_UI_IRequiredCapability_type"),
				P2Package.Literals.IREQUIRED_CAPABILITY__RANGE, false, false, false,
				ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);
	}

}
