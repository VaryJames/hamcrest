package org.hamcrest.core;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Matcher;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.introspection.Combination;

import java.util.Arrays;

/**
 * Calculates the logical conjunction of two matchers. Evaluation is
 * shortcut, so that the second matcher is not called if the first
 * matcher returns <code>false</code>.
 */
public class AllOf<T> extends BaseMatcher<T> implements Combination {
    private final Iterable<? extends Matcher<? extends T>> matchers;

    public AllOf(Iterable<? extends Matcher<? extends T>> matchers) {
        this.matchers = matchers;
    }

    public boolean matches(Object o) {
        for (Matcher<? extends T> matcher : matchers) {
            if (!matcher.matches(o)) {
                return false;
            }
        }
        return true;
    }

    public void describeTo(Description description) {
    	description.appendList("(", " and ", ")", matchers);
    }
    
	public Iterable<? extends Matcher<?>> combined() {
		return matchers;
	}

	/**
     * Evaluates to true only if ALL of the passed in matchers evaluate to true.
     */
    @Factory
    public static <T> Matcher<T> allOf(Matcher<? extends T>... matchers) {
        return allOf(Arrays.asList(matchers));
    }

    /**
     * Evaluates to true only if ALL of the passed in matchers evaluate to true.
     */
    @Factory
    public static <T> Matcher<T> allOf(Iterable<? extends Matcher<? extends T>> matchers) {
        return new AllOf<T>(matchers);
    }
}
