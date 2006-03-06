/*  Copyright (c) 2000-2006 hamcrest.org
 */
package org.hamcrest.core;

import org.hamcrest.Matcher;


/**
 * Is the value null?
 */
public class IsNull implements Matcher {
    public boolean match(Object o) {
        return o == null;
    }

    public void describeTo(StringBuffer buffer) {
        buffer.append("null");
    }
}

