/**
 * Copyright (c) 2006-2016, Cloudsmith Inc. and others
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 */
package org.eclipse.cbi.p2repo.aggregator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Version Format</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.cbi.p2repo.aggregator.AggregatorPackage#getVersionFormat()
 * @model
 * @generated
 */
public enum VersionFormat implements Enumerator {
	/**
	 * The '<em><b>Normal</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NORMAL_VALUE
	 * @generated
	 * @ordered
	 */
	NORMAL(0, "Normal", "Normal"),

	/**
	 * The '<em><b>Strict Maven</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #STRICT_MAVEN_VALUE
	 * @generated
	 * @ordered
	 */
	STRICT_MAVEN(1, "StrictMaven", "StrictMaven"),

	/**
	 * The '<em><b>Maven Release</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MAVEN_RELEASE_VALUE
	 * @generated
	 * @ordered
	 */
	MAVEN_RELEASE(2, "MavenRelease", "MavenRelease"),
	/**
	* The '<em><b>Maven Snapshot</b></em>' literal object.
	* <!-- begin-user-doc -->
	* <!-- end-user-doc -->
	* @see #MAVEN_SNAPSHOT_VALUE
	* @generated
	* @ordered
	*/
	MAVEN_SNAPSHOT(3, "MavenSnapshot", "MavenSnapshot");

	/**
	 * The '<em><b>Normal</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Normal</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NORMAL
	 * @model name="Normal"
	 * @generated
	 * @ordered
	 */
	public static final int NORMAL_VALUE = 0;

	/**
	 * The '<em><b>Strict Maven</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Strict Maven</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #STRICT_MAVEN
	 * @model name="StrictMaven"
	 * @generated
	 * @ordered
	 */
	public static final int STRICT_MAVEN_VALUE = 1;

	/**
	 * The '<em><b>Maven Release</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Maven Release</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MAVEN_RELEASE
	 * @model name="MavenRelease"
	 * @generated
	 * @ordered
	 */
	public static final int MAVEN_RELEASE_VALUE = 2;

	/**
	 * The '<em><b>Maven Snapshot</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Maven Snapshot</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MAVEN_SNAPSHOT
	 * @model name="MavenSnapshot"
	 * @generated
	 * @ordered
	 */
	public static final int MAVEN_SNAPSHOT_VALUE = 3;

	/**
	 * An array of all the '<em><b>Version Format</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final VersionFormat[] VALUES_ARRAY = new VersionFormat[] { NORMAL, STRICT_MAVEN, MAVEN_RELEASE,
			MAVEN_SNAPSHOT, };

	/**
	 * A public read-only list of all the '<em><b>Version Format</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<VersionFormat> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Version Format</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static VersionFormat get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			VersionFormat result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Version Format</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static VersionFormat getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			VersionFormat result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Version Format</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static VersionFormat get(int value) {
		switch (value) {
			case NORMAL_VALUE:
				return NORMAL;
			case STRICT_MAVEN_VALUE:
				return STRICT_MAVEN;
			case MAVEN_RELEASE_VALUE:
				return MAVEN_RELEASE;
			case MAVEN_SNAPSHOT_VALUE:
				return MAVEN_SNAPSHOT;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private VersionFormat(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getValue() {
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getLiteral() {
		return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}

} //VersionFormat
