/**
 * Copyright (c) 2021 Eclipse contributors and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.cbi.p2repo.aggregator.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;

import org.eclipse.cbi.p2repo.aggregator.Aggregation;
import org.eclipse.cbi.p2repo.aggregator.AggregatorFactory;
import org.eclipse.cbi.p2repo.aggregator.AggregatorPackage;
import org.eclipse.cbi.p2repo.aggregator.Contribution;
import org.eclipse.cbi.p2repo.aggregator.CustomCategory;
import org.eclipse.cbi.p2repo.aggregator.EnabledStatusProvider;
import org.eclipse.cbi.p2repo.aggregator.Feature;
import org.eclipse.cbi.p2repo.aggregator.MappedRepository;
import org.eclipse.cbi.p2repo.aggregator.MappedUnit;
import org.eclipse.cbi.p2repo.aggregator.MavenDependencyMapping;
import org.eclipse.cbi.p2repo.aggregator.MavenMapping;
import org.eclipse.cbi.p2repo.aggregator.ValidationSet;
import org.eclipse.cbi.p2repo.aggregator.p2view.IUPresentation;
import org.eclipse.cbi.p2repo.aggregator.p2view.MetadataRepositoryStructuredView;
import org.eclipse.cbi.p2repo.aggregator.util.AddIUsToContributionCommand;
import org.eclipse.cbi.p2repo.aggregator.util.GeneralUtils;
import org.eclipse.cbi.p2repo.aggregator.util.ItemSorter;
import org.eclipse.cbi.p2repo.aggregator.util.ItemSorter.ItemGroup;
import org.eclipse.cbi.p2repo.aggregator.util.ItemUtils;
import org.eclipse.cbi.p2repo.aggregator.util.ResourceUtils;
import org.eclipse.cbi.p2repo.p2.InstallableUnit;
import org.eclipse.cbi.p2repo.p2.MetadataRepository;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.AdapterFactoryItemDelegator;
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
import org.eclipse.equinox.p2.metadata.IInstallableUnit;

/**
 * This is the item provider adapter for a {@link org.eclipse.cbi.p2repo.aggregator.Contribution} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ContributionItemProvider extends AggregatorItemProviderAdapter
		implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider,
		IItemLabelProvider, IItemPropertySource, IItemColorProvider, IItemFontProvider {
	static class DynamicItemPropertyDescriptor extends AggregatorItemPropertyDescriptor {

		public DynamicItemPropertyDescriptor(AdapterFactory adapterFactory, ResourceLocator resourceLocator,
				String displayName, String description, EStructuralFeature feature, boolean isSettable,
				boolean multiLine, boolean sortChoices, Object staticImage, String category, String[] filterFlags) {
			super(adapterFactory, resourceLocator, displayName, description, feature, isSettable, multiLine,
					sortChoices, staticImage, category, filterFlags);
		}

		@Override
		public boolean canSetProperty(Object object) {
			boolean result = super.canSetProperty(object);

			if (result) {
				if (object instanceof Contribution)
					result = ((Contribution) object).isEnabled()
							|| AggregatorPackage.Literals.ENABLED_STATUS_PROVIDER__ENABLED.getName()
									.equals(getId(object));
				else {
					MappedRepository mappedRepository = findMappedRepository(object);
					if (mappedRepository != null) {
						Contribution contribution = (Contribution) ((EObject) mappedRepository).eContainer();

						if (contribution.isEnabled()) {
							result = object instanceof MappedRepository
									&& AggregatorPackage.Literals.ENABLED_STATUS_PROVIDER__ENABLED.getName()
											.equals(getId(mappedRepository))
									|| mappedRepository.isEnabled();

							if (result && object instanceof MappedUnit)
								result = (AggregatorPackage.Literals.ENABLED_STATUS_PROVIDER__ENABLED.getName()
										.equals(getId(object)) || ((MappedUnit) object).isEnabled())
										&& !((MappedUnit) object).isMappedRepositoryBroken();
						} else
							result = false;
					}
				}
			}

			return result;
		}

		private MappedRepository findMappedRepository(Object object) {
			while (object != null) {
				if (object instanceof MappedRepository)
					return (MappedRepository) object;
				object = ((EObject) object).eContainer();
			}

			return null;
		}
	}

	private static Comparator<MappedRepository> mappedRepositoryComparator = new Comparator<>() {

		@Override
		public int compare(MappedRepository o1, MappedRepository o2) {
			String loc1 = o1.getLocation();
			String loc2 = o2.getLocation();
			if (loc1 == null)
				return loc2 == null ? 0 : 1;

			if (loc2 == null)
				return -1;

			return loc1.compareTo(loc2);
		}
	};

	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ContributionItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This adds a property descriptor for the Contacts feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addContactsPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_Contribution_contacts_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_Contribution_contacts_feature",
								"_UI_Contribution_type"),
						AggregatorPackage.Literals.CONTRIBUTION__CONTACTS, true, false, false, null, null, null));
	}

	/**
	 * This adds a property descriptor for the Description feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDescriptionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_DescriptionProvider_description_feature"),
				getString("_UI_PropertyDescriptor_description", "_UI_DescriptionProvider_description_feature",
						"_UI_DescriptionProvider_type"),
				AggregatorPackage.Literals.DESCRIPTION_PROVIDER__DESCRIPTION, true, true, false,
				ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Enabled feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addEnabledPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_EnabledStatusProvider_enabled_feature"),
				getString("_UI_PropertyDescriptor_description", "_UI_EnabledStatusProvider_enabled_feature",
						"_UI_EnabledStatusProvider_type"),
				AggregatorPackage.Literals.ENABLED_STATUS_PROVIDER__ENABLED, true, false, false,
				ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Label feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addLabelPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_Contribution_label_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_Contribution_label_feature",
								"_UI_Contribution_type"),
						AggregatorPackage.Literals.CONTRIBUTION__LABEL, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private void collectNewChildDescriptorsGen(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add(createChildParameter(AggregatorPackage.Literals.CONTRIBUTION__REPOSITORIES,
				AggregatorFactory.eINSTANCE.createMappedRepository()));

		newChildDescriptors.add(createChildParameter(AggregatorPackage.Literals.CONTRIBUTION__MAVEN_MAPPINGS,
				AggregatorFactory.eINSTANCE.createMavenMapping()));

		newChildDescriptors.add(createChildParameter(AggregatorPackage.Literals.CONTRIBUTION__MAVEN_DEPENDENCY_MAPPINGS,
				AggregatorFactory.eINSTANCE.createMavenDependencyMapping()));
	}

	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		collectNewChildDescriptorsGen(newChildDescriptors, object);

		addNewChildDescriptor(this::createChildParameter, newChildDescriptors,
				AggregatorPackage.Literals.CONTRIBUTION__MAVEN_MAPPINGS, createGenericMavenMapping());
		addNewChildDescriptor(this::createChildParameter, newChildDescriptors,
				AggregatorPackage.Literals.CONTRIBUTION__MAVEN_DEPENDENCY_MAPPINGS, createJavaPackageMapping());
	}

	static MavenMapping createGenericMavenMapping() {
		MavenMapping mapping = AggregatorFactory.eINSTANCE.createMavenMapping();
		mapping.setNamePattern(".*");
		mapping.setGroupId("\\$maven-groupId\\$");
		mapping.setArtifactId("\\$maven-artifactId\\$");
		mapping.setVersionPattern(".*");
		mapping.setVersionTemplate("\\$maven-version\\$");
		return mapping;
	}

	static MavenDependencyMapping createJavaPackageMapping() {
		MavenDependencyMapping mapping = AggregatorFactory.eINSTANCE.createMavenDependencyMapping();
		mapping.setNamespacePattern("java\\.package");
		mapping.setNamePattern(".*");
		mapping.setGroupId("*");
		mapping.setArtifactId("*");
		mapping.setVersionRangePattern(".*");
		return mapping;
	}

	static void addNewChildDescriptor(BiFunction<Object, Object, Object> childParameterCreator,
			Collection<Object> newChildDescriptors, EReference eReference, Object child) {
		ArrayList<Object> result = new ArrayList<>(newChildDescriptors);
		Object childDescriptor = childParameterCreator.apply(eReference, child);
		int index = 0;
		for (int i = 0; i < result.size(); i++) {
			Object object = result.get(i);
			if (object instanceof CommandParameter) {
				CommandParameter commandParameter = (CommandParameter) object;
				if (commandParameter.getEStructuralFeature() == eReference) {
					index = i;
				}
			}
		}

		result.add(index + 1, childDescriptor);
		newChildDescriptors.clear();
		newChildDescriptors.addAll(result);
	}

	@Override
	public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
		if (child instanceof MavenDependencyMapping || child instanceof MavenMapping) {
			return new AdapterFactoryItemDelegator(getRootAdapterFactory()).getText(child);
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

	@SuppressWarnings("unchecked")
	private Command createAddIUsToContributionCommand(Object owner, Collection<?> collection) {
		ItemSorter itemSorter = new ItemSorter(collection);

		if (((EnabledStatusProvider) owner).isEnabled() && itemSorter.getTotalItemCount() > 0
				&& (itemSorter
						.getTotalItemCount() == (itemSorter.getGroupItems(ItemGroup.MDR).size()
								+ itemSorter.getGroupItems(ItemGroup.IU).size())
						|| itemSorter.getTotalItemCount() == (itemSorter.getGroupItems(ItemGroup.MDR_STRUCTURED).size()
								+ itemSorter.getGroupItems(ItemGroup.IU_STRUCTURED).size()))) {
			List<MetadataRepository> mdrs = new ArrayList<>();
			List<IInstallableUnit> ius = new ArrayList<>();

			mdrs.addAll((List<MetadataRepository>) itemSorter.getGroupItems(ItemGroup.MDR));
			mdrs.addAll(ItemUtils.getMDRs(
					(List<MetadataRepositoryStructuredView>) itemSorter.getGroupItems(ItemGroup.MDR_STRUCTURED)));
			ius.addAll((List<InstallableUnit>) itemSorter.getGroupItems(ItemGroup.IU));
			ius.addAll(ItemUtils.getIUs((List<IUPresentation>) itemSorter.getGroupItems(ItemGroup.IU_STRUCTURED)));

			return new AddIUsToContributionCommand((Contribution) owner, mdrs, ius);
		}

		return null;
	}

	/**
	 * Supports DnD from MDRs and IUs to Contribution
	 */
	@Override
	protected Command createDragAndDropCommand(EditingDomain domain, Object owner, float location, int operations,
			int operation, Collection<?> collection) {
		Command command = createAddIUsToContributionCommand(owner, collection);

		if (command != null)
			return command.canExecute() ? command : UnexecutableCommand.INSTANCE;

		return super.createDragAndDropCommand(domain, owner, location, operations, operation, collection);
	}

	/**
	 * Creates a dynamic property descriptor which alters the readonly attribute according to the "enabled" flag
	 */
	@Override
	protected ItemPropertyDescriptor createItemPropertyDescriptor(AdapterFactory adapterFactory,
			ResourceLocator resourceLocator, String displayName, String description, EStructuralFeature feature,
			boolean isSettable, boolean multiLine, boolean sortChoices, Object staticImage, String category,
			String[] filterFlags) {
		return new ContributionItemProvider.DynamicItemPropertyDescriptor(adapterFactory, resourceLocator, displayName,
				description, feature, isSettable, multiLine, sortChoices, staticImage, category, filterFlags);
	}

	/**
	 * Allow deleting a child from mapped repository only if the contribution is enabled
	 */
	@Override
	@Deprecated
	protected Command createRemoveCommand(EditingDomain domain, EObject owner, EReference feature,
			Collection<?> collection) {
		if (((Contribution) owner).isEnabled())
			return new RemoveCommand(domain, owner, feature, collection);

		return UnexecutableCommand.INSTANCE;
	}

	/**
	 * Allow deleting a child from mapped repository only if the contribution is enabled
	 */
	@Override
	protected Command createRemoveCommand(EditingDomain domain, EObject owner, EStructuralFeature feature,
			Collection<?> collection) {
		if (feature instanceof EReference) {
			return createRemoveCommand(domain, owner, (EReference) feature, collection);
		}

		if (((Contribution) owner).isEnabled())
			return new RemoveCommand(domain, owner, feature, collection);

		return UnexecutableCommand.INSTANCE;
	}

	/**
	 * Supports copy&paste from IUs to COntribution
	 */
	@Override
	protected Command factorAddCommand(EditingDomain domain, CommandParameter commandParameter) {
		Command command = createAddIUsToContributionCommand(commandParameter.getOwner(),
				commandParameter.getCollection());

		if (command != null)
			return command;

		return super.factorAddCommand(domain, commandParameter);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(AggregatorPackage.Literals.CONTRIBUTION__REPOSITORIES);
			childrenFeatures.add(AggregatorPackage.Literals.CONTRIBUTION__MAVEN_MAPPINGS);
			childrenFeatures.add(AggregatorPackage.Literals.CONTRIBUTION__MAVEN_DEPENDENCY_MAPPINGS);
		}
		return childrenFeatures;
	}

	/**
	 * This returns Contribution.gif.
	 *
	 * @generated NOT
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator()
				.getImage("full/obj16/Contribution" + (((Contribution) object).isBranchEnabled() ? "" : "Disabled")));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected boolean shouldComposeCreationImage() {
		return true;
	}

	/**
	 * Allow adding children only if the contribution enabled
	 */
	@Override
	public Collection<?> getNewChildDescriptors(Object object, EditingDomain editingDomain, Object sibling) {
		if (!((Contribution) object).isEnabled())
			return Collections.emptySet();

		return super.getNewChildDescriptors(object, editingDomain, sibling);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addEnabledPropertyDescriptor(object);
			addDescriptionPropertyDescriptor(object);
			addLabelPropertyDescriptor(object);
			addContactsPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return AggregatorEditPlugin.INSTANCE;
	}

	/**
	 * This returns the label text for the adapted class.
	 *
	 * @generated NOT
	 */
	@Override
	public String getText(Object object) {
		Contribution self = (Contribution) object;
		String label = self.getLabel();
		StringBuilder bld = new StringBuilder(getString("_UI_Contribution_type")).append(" : ");
		if (label != null)
			bld.append(label);
		return bld.toString();
	}

	@Override
	protected Object getValue(EObject eObject, EStructuralFeature eStructuralFeature) {
		Object value = super.getValue(eObject, eStructuralFeature);
		if (eStructuralFeature.getFeatureID() == AggregatorPackage.CONTRIBUTION__REPOSITORIES
				&& value instanceof List) {
			@SuppressWarnings("unchecked")
			List<MappedRepository> notSorted = (List<MappedRepository>) value;
			if (notSorted.size() > 1) {
				ArrayList<MappedRepository> sorted = new ArrayList<>(notSorted);
				Collections.sort(sorted, mappedRepositoryComparator);
				value = sorted;
			}
		}
		return value;
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached children and by creating
	 * a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 *
	 * @generated NOT
	 */
	@Override
	public void notifyChanged(Notification notification) {
		notifyChangedGen(notification);
		int featureId = notification.getFeatureID(Contribution.class);
		switch (featureId) {
			case AggregatorPackage.CONTRIBUTION__ENABLED:
			case AggregatorPackage.CONTRIBUTION__STATUS:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));

				Set<Object> affectedNodeLabels = new HashSet<>();
				Set<Object> affectedNodes = new HashSet<>();

				// Go through all direct ancestors first, and add also the top resource
				EObject container = ((EObject) notification.getNotifier());
				affectedNodeLabels.add(container.eResource());
				while (container != null) {
					affectedNodeLabels.add(container);
					EObject parent = container.eContainer();
					if (container instanceof ValidationSet) {
						for (ValidationSet peer : ((Aggregation) parent).getValidationSets(true))
							if (peer != container && peer.isExtensionOf((ValidationSet) container))
								affectedNodeLabels.add(peer);
					}
					container = parent;
				}

				boolean newValue = true;
				if (featureId == AggregatorPackage.CONTRIBUTION__ENABLED) {
					newValue = notification.getNewBooleanValue();
					// Browse all mapped repositories which may have changed their virtual status (inherently enabled/disabled)
					for (MappedRepository mappedRepository : ((Contribution) notification.getNotifier())
							.getRepositories(!newValue)) {
						if (newValue)
							ResourceUtils.loadResourceForMappedRepository(mappedRepository);

						affectedNodes.add(mappedRepository);

						// Browse all mapped units which may have changed their virtual status (inherently enabled/disabled)
						for (MappedUnit unit : mappedRepository.getUnits(!notification.getNewBooleanValue())) {
							affectedNodes.add(unit);
							// And now, find all categories which may contain the feature just being enabled/disabled
							if (unit instanceof Feature)
								for (CustomCategory category : ((Feature) unit).getCategories())
									affectedNodes.add(category);
						}
					}
				}

				for (Object affectedNode : affectedNodes)
					fireNotifyChanged(new ViewerNotification(notification, affectedNode, true, true));
				for (Object affectedNode : affectedNodeLabels)
					fireNotifyChanged(new ViewerNotification(notification, affectedNode, false, true));

				if (!newValue)
					ResourceUtils.cleanUpResources(GeneralUtils.getAggregation((EObject) notification.getNotifier()));
				return;
		}

		// If a repository is removed, update possible warning overlays
		if (notification.getEventType() == Notification.REMOVE
				&& (notification.getOldValue() instanceof MappedRepository
						|| notification.getOldValue() instanceof MavenMapping)) {
			Set<Object> affectedNodes = new HashSet<>();

			// Go through all direct ancestors first, and add also the top resource
			EObject container = ((EObject) notification.getNotifier());
			affectedNodes.add(container.eResource());
			while (container != null) {
				affectedNodes.add(container);
				container = container.eContainer();
			}

			if (notification.getOldValue() instanceof MappedRepository) {
				for (Feature mappedFeature : ((MappedRepository) notification.getOldValue()).getFeatures())
					// And now, find all categories which may contain the feature or the repository just being removed
					for (CustomCategory category : mappedFeature.getCategories())
						affectedNodes.add(category);

				ResourceUtils.cleanUpResources(GeneralUtils.getAggregation((EObject) notification.getNotifier()));
			}

			for (Object affectedNode : affectedNodes)
				fireNotifyChanged(new ViewerNotification(notification, affectedNode, false, true));
		}
		// If a repository is added (e.g. Undo Delete), reload MDR
		else if (notification.getEventType() == Notification.ADD
				&& (notification.getNewValue() instanceof MappedRepository
						|| notification.getNewValue() instanceof MavenMapping)) {
			if (notification.getNewValue() instanceof MappedRepository)
				ResourceUtils.loadResourceForMappedRepository((MappedRepository) notification.getNewValue());

			Set<Object> affectedNodes = new HashSet<>();
			// Go through all ancestors to mark warnings
			EObject container = ((EObject) notification.getNotifier());
			affectedNodes.add(container.eResource());
			while (container != null) {
				affectedNodes.add(container);
				container = container.eContainer();
			}
			for (Object affectedNode : affectedNodes)
				fireNotifyChanged(new ViewerNotification(notification, affectedNode, false, true));
		}

	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void notifyChangedGen(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(Contribution.class)) {
			case AggregatorPackage.CONTRIBUTION__ENABLED:
			case AggregatorPackage.CONTRIBUTION__DESCRIPTION:
			case AggregatorPackage.CONTRIBUTION__STATUS:
			case AggregatorPackage.CONTRIBUTION__LABEL:
			case AggregatorPackage.CONTRIBUTION__CONTACTS:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case AggregatorPackage.CONTRIBUTION__REPOSITORIES:
			case AggregatorPackage.CONTRIBUTION__MAVEN_MAPPINGS:
			case AggregatorPackage.CONTRIBUTION__MAVEN_DEPENDENCY_MAPPINGS:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}
}
