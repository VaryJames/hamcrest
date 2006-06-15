/*  Copyright (c) 2000-2006 hamcrest.org
 */
package org.hamcrest;

import org.hamcrest.core.And;
import org.hamcrest.core.IsAnything;
import org.hamcrest.core.IsCompatibleType;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsIn;
import org.hamcrest.core.IsInstanceOf;
import org.hamcrest.core.IsNot;
import org.hamcrest.core.IsNull;
import org.hamcrest.core.IsSame;
import org.hamcrest.core.Or;
import org.hamcrest.number.IsCloseTo;
import org.hamcrest.object.HasProperty;
import org.hamcrest.object.HasPropertyWithValue;
import org.hamcrest.object.HasToString;
import org.hamcrest.text.StringContains;
import org.hamcrest.text.StringEndsWith;
import org.hamcrest.text.StringStartsWith;
import org.hamcrest.internal.StringDescription;

import java.util.Collection;

public class Matchers {

    public static final Matcher ANYTHING = new IsAnything();
    public static final Matcher NULL = new IsNull();
    public static final Matcher NOT_NULL = new IsNot(NULL);
    public static final Matcher IS_TRUE = new IsEqual(Boolean.TRUE);
    public static final Matcher IS_FALSE = new IsEqual(Boolean.FALSE);

    public static <T> void assertThat(T value, Matcher<T> isMatchedBy) {
        if (!isMatchedBy.match(value)) {
            Description description = new StringDescription();
            description.appendText("\nExpected: ");
            isMatchedBy.describeTo(description);
            description.appendText("\n    got : ").appendValue(value).appendText("\n");
            throw new AssertionError(description.toString());
        }
    }

    public static IsEqual eq(Object operand) {
        return new IsEqual(operand);
    }

    public static IsEqual eq(boolean operand) {
        // Avoid newing Boolean objects.
        // Boolean.valueOf() not available on JDK 1.3.
        return eq(operand ? Boolean.TRUE : Boolean.FALSE);
    }

    public static IsEqual eq(byte operand) {
        return eq(new Byte(operand));
    }

    public static IsEqual eq(short operand) {
        return eq(new Short(operand));
    }

    public static IsEqual eq(char operand) {
        return eq(new Character(operand));
    }

    public static IsEqual eq(int operand) {
        return eq(new Integer(operand));
    }

    public static IsEqual eq(long operand) {
        return eq(new Long(operand));
    }

    public static IsEqual eq(float operand) {
        return eq(new Float(operand));
    }

    public static IsEqual eq(double operand) {
        return eq(new Double(operand));
    }

    public static IsCloseTo eq(double operand, double error) {
        return new IsCloseTo(operand, error);
    }

    public static IsSame same(Object operand) {
        return new IsSame(operand);
    }

    public static IsInstanceOf isA(Class operandClass) {
        return new IsInstanceOf(operandClass);
    }

    public static StringContains stringContains(String substring) {
        return new StringContains(substring);
    }

    public static StringContains contains(String substring) {
        return stringContains(substring);
    }

    public static StringStartsWith startsWith(String substring) {
        return new StringStartsWith(substring);
    }

    public static StringEndsWith endsWith(String substring) {
        return new StringEndsWith(substring);
    }

    public static IsNot not(Matcher c) {
        return new IsNot(c);
    }

    public static And and(Matcher left, Matcher right) {
        return new And(left, right);
    }

    public static Or or(Matcher left, Matcher right) {
        return new Or(left, right);
    }

    public static HasPropertyWithValue hasProperty(String propertyName, Matcher expectation) {
        return new HasPropertyWithValue(propertyName, expectation);
    }

    public static HasProperty hasProperty(String propertyName) {
        return new HasProperty(propertyName);
    }

    public static HasToString toString(Matcher toStringMatcher) {
        return new HasToString(toStringMatcher);
    }

    public static IsCompatibleType compatibleType(Class baseType) {
        return new IsCompatibleType(baseType);
    }

    public static IsIn isIn(Collection collection) {
        return new IsIn(collection);
    }

    public static IsIn isIn(Object[] array) {
        return new IsIn(array);
    }

    /**
     * Temporary Matcher... don't use this.. it will go away!
     */
    public static Matcher<String> isTwoXs() {
        return new Matcher<String>() {
            public boolean match(String string) {
                return string.equals("xx");
            }

            public void describeTo(Description description) {
                description.appendText("Two x's");
            }
        };
    }
}
