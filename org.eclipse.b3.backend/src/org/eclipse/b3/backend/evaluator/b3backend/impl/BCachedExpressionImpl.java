/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.b3.backend.evaluator.b3backend.impl;

import java.lang.reflect.Type;

import org.eclipse.b3.backend.core.B3EngineException;
import org.eclipse.b3.backend.evaluator.BackendHelper;
import org.eclipse.b3.backend.evaluator.b3backend.B3backendPackage;
import org.eclipse.b3.backend.evaluator.b3backend.BCachedExpression;
import org.eclipse.b3.backend.evaluator.b3backend.BExecutionContext;
import org.eclipse.b3.backend.evaluator.b3backend.BInvocationContext;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>BCached Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class BCachedExpressionImpl extends BUnaryExpressionImpl implements BCachedExpression {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2009, Cloudsmith Inc and others.\nAll rights reserved. This program and the accompanying materials\nare made available under the terms of the Eclipse Public License v1.0\nwhich accompanies this distribution, and is available at\nhttp://www.eclipse.org/legal/epl-v10.html\n\rContributors:\n- Cloudsmith Inc - initial API and implementation.\r";
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BCachedExpressionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return B3backendPackage.Literals.BCACHED_EXPRESSION;
	}
	@Override
	public Object evaluate(BExecutionContext ctx) throws Throwable {
		BInvocationContext bctx = null;
		try {
			bctx = ctx.getInvocationContext();
		} catch (B3EngineException e) {
			throw BackendHelper.createException(this, e, "Internal error - no invocation context found"); 
		}
		return bctx.evaluateIfNotCached(ctx, this);
	}
	@Override
	public Type getDeclaredType(BExecutionContext ctx) throws Throwable {
		return expr.getDeclaredType(ctx);
	}
} //BCachedExpressionImpl