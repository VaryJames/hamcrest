/*  Copyright (c) 2000-2006 hamcrest.org
 */
package org.hamcrest.core;

import org.hamcrest.Description;
import org.hamcrest.Matcher;


/**
 * Is the value the same object as another value?
 */
public class IsSame implements Matcher {
    private final Object object;

    /**
     * Creates a new instance of IsSame
     *
     * @param object The predicate evaluates to true only when the argument is
     *               this object.
     */
    public IsSame(Object object) {
        this.object = object;
    }

    public boolean match(Object arg) {
        return arg == object;
    }

    public void describeTo(Description description) {
        description.appendText("same(")
                .appendValue(object)
                .appendText(")");
    }
}
