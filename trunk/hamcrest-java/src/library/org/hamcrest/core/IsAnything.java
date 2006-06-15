/*  Copyright (c) 2000-2006 hamcrest.org
 */
package org.hamcrest.core;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Factory;


/**
 * A matcher that always returns <code>true</code>.
 */
public class IsAnything<T> implements Matcher<T> {

    private final String description;

    public IsAnything() {
        this("ANYTHING");
    }

    public IsAnything(String description) {
        this.description = description;
    }

    public boolean match(T o) {
        return true;
    }

    public void describeTo(Description description) {
        description.appendText(this.description);
    }

    @Factory
    public static <T> Matcher<T> anything() {
        return new IsAnything<T>();
    }

    @Factory
    public static <T> Matcher<T> anything(String description) {
        return new IsAnything<T>(description);
    }
}
