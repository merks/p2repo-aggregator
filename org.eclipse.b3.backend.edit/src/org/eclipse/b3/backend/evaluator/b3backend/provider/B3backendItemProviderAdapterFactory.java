/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.b3.backend.evaluator.b3backend.provider;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.b3.backend.evaluator.b3backend.util.B3backendAdapterFactory;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.edit.provider.ChangeNotifier;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;

/**
 * This is the factory that is used to provide the interfaces needed to support Viewers.
 * The adapters generated by this factory convert EMF adapter notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}.
 * The adapters also support Eclipse property sheets.
 * Note that most of the adapters are shared among multiple instances.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class B3backendItemProviderAdapterFactory extends B3backendAdapterFactory implements ComposeableAdapterFactory, IChangeNotifier, IDisposable {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2009, Cloudsmith Inc and others.\nAll rights reserved. This program and the accompanying materials\nare made available under the terms of the Eclipse Public License v1.0\nwhich accompanies this distribution, and is available at\nhttp://www.eclipse.org/legal/epl-v10.html\n\rContributors:\n- Cloudsmith Inc - initial API and implementation.\r";

	/**
	 * This keeps track of the root adapter factory that delegates to this adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComposedAdapterFactory parentAdapterFactory;

	/**
	 * This is used to implement {@link org.eclipse.emf.edit.provider.IChangeNotifier}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IChangeNotifier changeNotifier = new ChangeNotifier();

	/**
	 * This keeps track of all the supported types checked by {@link #isFactoryForType isFactoryForType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Collection<Object> supportedTypes = new ArrayList<Object>();

	/**
	 * This constructs an instance.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public B3backendItemProviderAdapterFactory() {
		supportedTypes.add(IEditingDomainItemProvider.class);
		supportedTypes.add(IStructuredItemContentProvider.class);
		supportedTypes.add(ITreeItemContentProvider.class);
		supportedTypes.add(IItemLabelProvider.class);
		supportedTypes.add(IItemPropertySource.class);
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.b3.backend.evaluator.b3backend.BFileReference} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BFileReferenceItemProvider bFileReferenceItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.b3.backend.evaluator.b3backend.BFileReference}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createBFileReferenceAdapter() {
		if (bFileReferenceItemProvider == null) {
			bFileReferenceItemProvider = new BFileReferenceItemProvider(this);
		}

		return bFileReferenceItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.b3.backend.evaluator.b3backend.BLineReference} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BLineReferenceItemProvider bLineReferenceItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.b3.backend.evaluator.b3backend.BLineReference}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createBLineReferenceAdapter() {
		if (bLineReferenceItemProvider == null) {
			bLineReferenceItemProvider = new BLineReferenceItemProvider(this);
		}

		return bLineReferenceItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.b3.backend.evaluator.b3backend.BIfExpression} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BIfExpressionItemProvider bIfExpressionItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.b3.backend.evaluator.b3backend.BIfExpression}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createBIfExpressionAdapter() {
		if (bIfExpressionItemProvider == null) {
			bIfExpressionItemProvider = new BIfExpressionItemProvider(this);
		}

		return bIfExpressionItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.b3.backend.evaluator.b3backend.BSwitchExpression} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BSwitchExpressionItemProvider bSwitchExpressionItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.b3.backend.evaluator.b3backend.BSwitchExpression}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createBSwitchExpressionAdapter() {
		if (bSwitchExpressionItemProvider == null) {
			bSwitchExpressionItemProvider = new BSwitchExpressionItemProvider(this);
		}

		return bSwitchExpressionItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.b3.backend.evaluator.b3backend.BCase} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BCaseItemProvider bCaseItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.b3.backend.evaluator.b3backend.BCase}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createBCaseAdapter() {
		if (bCaseItemProvider == null) {
			bCaseItemProvider = new BCaseItemProvider(this);
		}

		return bCaseItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.b3.backend.evaluator.b3backend.BTryExpression} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BTryExpressionItemProvider bTryExpressionItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.b3.backend.evaluator.b3backend.BTryExpression}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createBTryExpressionAdapter() {
		if (bTryExpressionItemProvider == null) {
			bTryExpressionItemProvider = new BTryExpressionItemProvider(this);
		}

		return bTryExpressionItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.b3.backend.evaluator.b3backend.BCatch} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BCatchItemProvider bCatchItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.b3.backend.evaluator.b3backend.BCatch}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createBCatchAdapter() {
		if (bCatchItemProvider == null) {
			bCatchItemProvider = new BCatchItemProvider(this);
		}

		return bCatchItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.b3.backend.evaluator.b3backend.BOrExpression} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BOrExpressionItemProvider bOrExpressionItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.b3.backend.evaluator.b3backend.BOrExpression}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createBOrExpressionAdapter() {
		if (bOrExpressionItemProvider == null) {
			bOrExpressionItemProvider = new BOrExpressionItemProvider(this);
		}

		return bOrExpressionItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.b3.backend.evaluator.b3backend.BAndExpression} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BAndExpressionItemProvider bAndExpressionItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.b3.backend.evaluator.b3backend.BAndExpression}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createBAndExpressionAdapter() {
		if (bAndExpressionItemProvider == null) {
			bAndExpressionItemProvider = new BAndExpressionItemProvider(this);
		}

		return bAndExpressionItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.b3.backend.evaluator.b3backend.BChainedExpression} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BChainedExpressionItemProvider bChainedExpressionItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.b3.backend.evaluator.b3backend.BChainedExpression}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createBChainedExpressionAdapter() {
		if (bChainedExpressionItemProvider == null) {
			bChainedExpressionItemProvider = new BChainedExpressionItemProvider(this);
		}

		return bChainedExpressionItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.b3.backend.evaluator.b3backend.BThrowExpression} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BThrowExpressionItemProvider bThrowExpressionItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.b3.backend.evaluator.b3backend.BThrowExpression}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createBThrowExpressionAdapter() {
		if (bThrowExpressionItemProvider == null) {
			bThrowExpressionItemProvider = new BThrowExpressionItemProvider(this);
		}

		return bThrowExpressionItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.b3.backend.evaluator.b3backend.BUnaryOpExpression} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BUnaryOpExpressionItemProvider bUnaryOpExpressionItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.b3.backend.evaluator.b3backend.BUnaryOpExpression}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createBUnaryOpExpressionAdapter() {
		if (bUnaryOpExpressionItemProvider == null) {
			bUnaryOpExpressionItemProvider = new BUnaryOpExpressionItemProvider(this);
		}

		return bUnaryOpExpressionItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.b3.backend.evaluator.b3backend.BUnaryPostOpExpression} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BUnaryPostOpExpressionItemProvider bUnaryPostOpExpressionItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.b3.backend.evaluator.b3backend.BUnaryPostOpExpression}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createBUnaryPostOpExpressionAdapter() {
		if (bUnaryPostOpExpressionItemProvider == null) {
			bUnaryPostOpExpressionItemProvider = new BUnaryPostOpExpressionItemProvider(this);
		}

		return bUnaryPostOpExpressionItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.b3.backend.evaluator.b3backend.BBinaryOpExpression} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BBinaryOpExpressionItemProvider bBinaryOpExpressionItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.b3.backend.evaluator.b3backend.BBinaryOpExpression}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createBBinaryOpExpressionAdapter() {
		if (bBinaryOpExpressionItemProvider == null) {
			bBinaryOpExpressionItemProvider = new BBinaryOpExpressionItemProvider(this);
		}

		return bBinaryOpExpressionItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.b3.backend.evaluator.b3backend.BCachedExpression} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BCachedExpressionItemProvider bCachedExpressionItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.b3.backend.evaluator.b3backend.BCachedExpression}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createBCachedExpressionAdapter() {
		if (bCachedExpressionItemProvider == null) {
			bCachedExpressionItemProvider = new BCachedExpressionItemProvider(this);
		}

		return bCachedExpressionItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.b3.backend.evaluator.b3backend.BLiteralExpression} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BLiteralExpressionItemProvider bLiteralExpressionItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.b3.backend.evaluator.b3backend.BLiteralExpression}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createBLiteralExpressionAdapter() {
		if (bLiteralExpressionItemProvider == null) {
			bLiteralExpressionItemProvider = new BLiteralExpressionItemProvider(this);
		}

		return bLiteralExpressionItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.b3.backend.evaluator.b3backend.BLiteralListExpression} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BLiteralListExpressionItemProvider bLiteralListExpressionItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.b3.backend.evaluator.b3backend.BLiteralListExpression}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createBLiteralListExpressionAdapter() {
		if (bLiteralListExpressionItemProvider == null) {
			bLiteralListExpressionItemProvider = new BLiteralListExpressionItemProvider(this);
		}

		return bLiteralListExpressionItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.b3.backend.evaluator.b3backend.BLiteralMapExpression} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BLiteralMapExpressionItemProvider bLiteralMapExpressionItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.b3.backend.evaluator.b3backend.BLiteralMapExpression}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createBLiteralMapExpressionAdapter() {
		if (bLiteralMapExpressionItemProvider == null) {
			bLiteralMapExpressionItemProvider = new BLiteralMapExpressionItemProvider(this);
		}

		return bLiteralMapExpressionItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.b3.backend.evaluator.b3backend.BMapEntry} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BMapEntryItemProvider bMapEntryItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.b3.backend.evaluator.b3backend.BMapEntry}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createBMapEntryAdapter() {
		if (bMapEntryItemProvider == null) {
			bMapEntryItemProvider = new BMapEntryItemProvider(this);
		}

		return bMapEntryItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.b3.backend.evaluator.b3backend.BFeatureExpression} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BFeatureExpressionItemProvider bFeatureExpressionItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.b3.backend.evaluator.b3backend.BFeatureExpression}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createBFeatureExpressionAdapter() {
		if (bFeatureExpressionItemProvider == null) {
			bFeatureExpressionItemProvider = new BFeatureExpressionItemProvider(this);
		}

		return bFeatureExpressionItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.b3.backend.evaluator.b3backend.BAtExpression} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BAtExpressionItemProvider bAtExpressionItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.b3.backend.evaluator.b3backend.BAtExpression}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createBAtExpressionAdapter() {
		if (bAtExpressionItemProvider == null) {
			bAtExpressionItemProvider = new BAtExpressionItemProvider(this);
		}

		return bAtExpressionItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.b3.backend.evaluator.b3backend.BVariableExpression} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BVariableExpressionItemProvider bVariableExpressionItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.b3.backend.evaluator.b3backend.BVariableExpression}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createBVariableExpressionAdapter() {
		if (bVariableExpressionItemProvider == null) {
			bVariableExpressionItemProvider = new BVariableExpressionItemProvider(this);
		}

		return bVariableExpressionItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.b3.backend.evaluator.b3backend.BCallExpression} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BCallExpressionItemProvider bCallExpressionItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.b3.backend.evaluator.b3backend.BCallExpression}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createBCallExpressionAdapter() {
		if (bCallExpressionItemProvider == null) {
			bCallExpressionItemProvider = new BCallExpressionItemProvider(this);
		}

		return bCallExpressionItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.b3.backend.evaluator.b3backend.BLiteralAny} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BLiteralAnyItemProvider bLiteralAnyItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.b3.backend.evaluator.b3backend.BLiteralAny}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createBLiteralAnyAdapter() {
		if (bLiteralAnyItemProvider == null) {
			bLiteralAnyItemProvider = new BLiteralAnyItemProvider(this);
		}

		return bLiteralAnyItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.b3.backend.evaluator.b3backend.BCreateExpression} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BCreateExpressionItemProvider bCreateExpressionItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.b3.backend.evaluator.b3backend.BCreateExpression}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createBCreateExpressionAdapter() {
		if (bCreateExpressionItemProvider == null) {
			bCreateExpressionItemProvider = new BCreateExpressionItemProvider(this);
		}

		return bCreateExpressionItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.b3.backend.evaluator.b3backend.BGuardInstance} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BGuardInstanceItemProvider bGuardInstanceItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.b3.backend.evaluator.b3backend.BGuardInstance}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createBGuardInstanceAdapter() {
		if (bGuardInstanceItemProvider == null) {
			bGuardInstanceItemProvider = new BGuardInstanceItemProvider(this);
		}

		return bGuardInstanceItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.b3.backend.evaluator.b3backend.BGuardExpression} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BGuardExpressionItemProvider bGuardExpressionItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.b3.backend.evaluator.b3backend.BGuardExpression}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createBGuardExpressionAdapter() {
		if (bGuardExpressionItemProvider == null) {
			bGuardExpressionItemProvider = new BGuardExpressionItemProvider(this);
		}

		return bGuardExpressionItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.b3.backend.evaluator.b3backend.BSystemContext} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BSystemContextItemProvider bSystemContextItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.b3.backend.evaluator.b3backend.BSystemContext}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createBSystemContextAdapter() {
		if (bSystemContextItemProvider == null) {
			bSystemContextItemProvider = new BSystemContextItemProvider(this);
		}

		return bSystemContextItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.b3.backend.evaluator.b3backend.BContext} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BContextItemProvider bContextItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.b3.backend.evaluator.b3backend.BContext}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createBContextAdapter() {
		if (bContextItemProvider == null) {
			bContextItemProvider = new BContextItemProvider(this);
		}

		return bContextItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.b3.backend.evaluator.b3backend.BInnerContext} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BInnerContextItemProvider bInnerContextItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.b3.backend.evaluator.b3backend.BInnerContext}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createBInnerContextAdapter() {
		if (bInnerContextItemProvider == null) {
			bInnerContextItemProvider = new BInnerContextItemProvider(this);
		}

		return bInnerContextItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.b3.backend.evaluator.b3backend.BInvocationContext} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BInvocationContextItemProvider bInvocationContextItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.b3.backend.evaluator.b3backend.BInvocationContext}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createBInvocationContextAdapter() {
		if (bInvocationContextItemProvider == null) {
			bInvocationContextItemProvider = new BInvocationContextItemProvider(this);
		}

		return bInvocationContextItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.b3.backend.evaluator.b3backend.BUnaryPreOpExpression} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BUnaryPreOpExpressionItemProvider bUnaryPreOpExpressionItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.b3.backend.evaluator.b3backend.BUnaryPreOpExpression}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createBUnaryPreOpExpressionAdapter() {
		if (bUnaryPreOpExpressionItemProvider == null) {
			bUnaryPreOpExpressionItemProvider = new BUnaryPreOpExpressionItemProvider(this);
		}

		return bUnaryPreOpExpressionItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.b3.backend.evaluator.b3backend.BAssignmentExpression} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BAssignmentExpressionItemProvider bAssignmentExpressionItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.b3.backend.evaluator.b3backend.BAssignmentExpression}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createBAssignmentExpressionAdapter() {
		if (bAssignmentExpressionItemProvider == null) {
			bAssignmentExpressionItemProvider = new BAssignmentExpressionItemProvider(this);
		}

		return bAssignmentExpressionItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.b3.backend.evaluator.b3backend.B3Function} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected B3FunctionItemProvider b3FunctionItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.b3.backend.evaluator.b3backend.B3Function}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createB3FunctionAdapter() {
		if (b3FunctionItemProvider == null) {
			b3FunctionItemProvider = new B3FunctionItemProvider(this);
		}

		return b3FunctionItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.b3.backend.evaluator.b3backend.BJavaFunction} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BJavaFunctionItemProvider bJavaFunctionItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.b3.backend.evaluator.b3backend.BJavaFunction}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createBJavaFunctionAdapter() {
		if (bJavaFunctionItemProvider == null) {
			bJavaFunctionItemProvider = new BJavaFunctionItemProvider(this);
		}

		return bJavaFunctionItemProvider;
	}

	/**
	 * This returns the root adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComposeableAdapterFactory getRootAdapterFactory() {
		return parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory();
	}

	/**
	 * This sets the composed adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory) {
		this.parentAdapterFactory = parentAdapterFactory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object type) {
		return supportedTypes.contains(type) || super.isFactoryForType(type);
	}

	/**
	 * This implementation substitutes the factory itself as the key for the adapter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter adapt(Notifier notifier, Object type) {
		return super.adapt(notifier, this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object adapt(Object object, Object type) {
		if (isFactoryForType(type)) {
			Object adapter = super.adapt(object, type);
			if (!(type instanceof Class<?>) || (((Class<?>)type).isInstance(adapter))) {
				return adapter;
			}
		}

		return null;
	}

	/**
	 * This adds a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void addListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.addListener(notifyChangedListener);
	}

	/**
	 * This removes a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void removeListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.removeListener(notifyChangedListener);
	}

	/**
	 * This delegates to {@link #changeNotifier} and to {@link #parentAdapterFactory}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void fireNotifyChanged(Notification notification) {
		changeNotifier.fireNotifyChanged(notification);

		if (parentAdapterFactory != null) {
			parentAdapterFactory.fireNotifyChanged(notification);
		}
	}

	/**
	 * This disposes all of the item providers created by this factory. 
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void dispose() {
		if (bFileReferenceItemProvider != null) bFileReferenceItemProvider.dispose();
		if (bLineReferenceItemProvider != null) bLineReferenceItemProvider.dispose();
		if (bIfExpressionItemProvider != null) bIfExpressionItemProvider.dispose();
		if (bSwitchExpressionItemProvider != null) bSwitchExpressionItemProvider.dispose();
		if (bCaseItemProvider != null) bCaseItemProvider.dispose();
		if (bTryExpressionItemProvider != null) bTryExpressionItemProvider.dispose();
		if (bCatchItemProvider != null) bCatchItemProvider.dispose();
		if (bOrExpressionItemProvider != null) bOrExpressionItemProvider.dispose();
		if (bAndExpressionItemProvider != null) bAndExpressionItemProvider.dispose();
		if (bChainedExpressionItemProvider != null) bChainedExpressionItemProvider.dispose();
		if (bThrowExpressionItemProvider != null) bThrowExpressionItemProvider.dispose();
		if (bUnaryOpExpressionItemProvider != null) bUnaryOpExpressionItemProvider.dispose();
		if (bUnaryPostOpExpressionItemProvider != null) bUnaryPostOpExpressionItemProvider.dispose();
		if (bBinaryOpExpressionItemProvider != null) bBinaryOpExpressionItemProvider.dispose();
		if (bCachedExpressionItemProvider != null) bCachedExpressionItemProvider.dispose();
		if (bLiteralExpressionItemProvider != null) bLiteralExpressionItemProvider.dispose();
		if (bLiteralListExpressionItemProvider != null) bLiteralListExpressionItemProvider.dispose();
		if (bLiteralMapExpressionItemProvider != null) bLiteralMapExpressionItemProvider.dispose();
		if (bMapEntryItemProvider != null) bMapEntryItemProvider.dispose();
		if (bFeatureExpressionItemProvider != null) bFeatureExpressionItemProvider.dispose();
		if (bAtExpressionItemProvider != null) bAtExpressionItemProvider.dispose();
		if (bVariableExpressionItemProvider != null) bVariableExpressionItemProvider.dispose();
		if (bCallExpressionItemProvider != null) bCallExpressionItemProvider.dispose();
		if (bLiteralAnyItemProvider != null) bLiteralAnyItemProvider.dispose();
		if (bCreateExpressionItemProvider != null) bCreateExpressionItemProvider.dispose();
		if (bGuardInstanceItemProvider != null) bGuardInstanceItemProvider.dispose();
		if (bGuardExpressionItemProvider != null) bGuardExpressionItemProvider.dispose();
		if (bSystemContextItemProvider != null) bSystemContextItemProvider.dispose();
		if (bContextItemProvider != null) bContextItemProvider.dispose();
		if (bInnerContextItemProvider != null) bInnerContextItemProvider.dispose();
		if (bInvocationContextItemProvider != null) bInvocationContextItemProvider.dispose();
		if (bUnaryPreOpExpressionItemProvider != null) bUnaryPreOpExpressionItemProvider.dispose();
		if (bAssignmentExpressionItemProvider != null) bAssignmentExpressionItemProvider.dispose();
		if (b3FunctionItemProvider != null) b3FunctionItemProvider.dispose();
		if (bJavaFunctionItemProvider != null) bJavaFunctionItemProvider.dispose();
	}

}
