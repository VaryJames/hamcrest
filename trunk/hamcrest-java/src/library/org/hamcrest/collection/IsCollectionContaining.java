package org.hamcrest.collection;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import java.util.Collection;
import java.util.Iterator;

public class IsCollectionContaining implements Matcher {
    private final Matcher elementMatcher;

    public IsCollectionContaining(Matcher elementMatcher) {
        this.elementMatcher = elementMatcher;
    }

    public boolean match(Object o) {
        return o != null
                && o instanceof Collection
                && collectionContainsMatchingElement((Collection) o);
    }

    private boolean collectionContainsMatchingElement(Collection collection) {
        Iterator i = collection.iterator();
        while (i.hasNext()) {
            if (elementMatcher.match(i.next())) return true;
        }
        return false;
    }

    public void describeTo(Description description) {
        description.appendText("a collection containing ");
        elementMatcher.describeTo(description);
    }
}
