/*  Copyright (c) 2000-2006 hamcrest.org
 */
package org.hamcrest.beans;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.hamcrest.core.IsAnything.anything;
import static org.hamcrest.core.IsEqual.eq;
import static org.hamcrest.core.IsNot.not;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

import org.hamcrest.AbstractMatcherTest;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;

/**
 * @author Iain McGinniss
 * @author Nat Pryce
 * @author Steve Freeman
 * @since 1.1.0
 */
public class HasPropertyWithValueTest extends AbstractMatcherTest {

    private final BeanWithoutInfo shouldMatch = new BeanWithoutInfo("is expected");
    private final BeanWithoutInfo shouldNotMatch = new BeanWithoutInfo("not expected");
    private final BeanWithInfo beanWithInfo = new BeanWithInfo("with info");

    public void testMatchesInfolessBeanWithMatchedNamedProperty() {
        assertThat(shouldMatch, hasProperty("property", eq("is expected")));
        assertThat(shouldNotMatch, not(hasProperty("property", eq("is expected"))));
    }

    public void testMatchesBeanWithInfoWithMatchedNamedProperty() {
        assertThat(beanWithInfo, hasProperty("property", eq("with info")));
    }

    public void testDoesNotMatchInfolessBeanWithoutMatchedNamedProperty() {
        assertThat(shouldNotMatch, not(hasProperty("nonExistentProperty", anything())));
    }

    public void testDoesNotMatchWriteOnlyProperty() {
        assertThat(shouldNotMatch, not(hasProperty("writeOnlyProperty", anything())));
    }

    public void testDescribeTo() {
        Matcher matcher = eq(true);
        Description isEqualDescription = new StringDescription();
        matcher.describeTo(isEqualDescription);

        assertDescription("hasProperty(\"property\", " + isEqualDescription + ")",
                hasProperty("property", matcher));
    }

    public static class BeanWithoutInfo {
        private String property;

        public BeanWithoutInfo(String property) {
            this.property = property;
        }

        public String getProperty() {
            return property;
        }

        public void setProperty(String property) {
            this.property = property;
        }

        public void setWriteOnlyProperty(float property) {
        }

        public String toString() {
            return "[Person: " + property + "]";
        }
    }

    public static class BeanWithInfo {
        private String propertyValue;

        public BeanWithInfo(String propertyValue) {
            this.propertyValue = propertyValue;
        }

        public String property() {
            return propertyValue;
        }
    }

    public static class BeanWithInfoBeanInfo extends SimpleBeanInfo {
        public PropertyDescriptor[] getPropertyDescriptors() {
            try {
                return new PropertyDescriptor[]{
                        new PropertyDescriptor("property", BeanWithInfo.class, "property", null)
                };
            } catch (IntrospectionException e) {
                throw new RuntimeException("Introspection exception: " + e.getMessage());
            }
        }
    }
}
