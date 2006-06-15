package org.hamcrest.collection;

import org.hamcrest.AbstractMatcherTest;
import static org.hamcrest.collection.IsCollectionContaining.collectionContaining;
import static org.hamcrest.core.IsEqual.eq;

import java.util.ArrayList;
import static java.util.Arrays.asList;

public class IsCollectionContainingTest extends AbstractMatcherTest {

    public void testMatchesACollectionThatContainsAnElementMatchingTheGivenMatcher() {
        assertMatches("should match list that contains 'a'",
                collectionContaining(eq("a")), asList("a", "b", "c"));
    }

    public void testDoesNotMatchCollectionThatDoesntContainAnElementMatchingTheGivenMatcher() {
        assertDoesNotMatch("should not match list that doesn't contain 'a'",
                collectionContaining(eq("a")), asList("b", "c"));
        assertDoesNotMatch("should not match empty list",
                collectionContaining(eq("a")), new ArrayList<String>());
    }

    public void testDoesNotMatchNull() {
        assertDoesNotMatch("should not match null", collectionContaining(eq("a")), null);
    }

    public void testHasAReadableDescription() {
        assertDescription("a collection containing eq(\"a\")", collectionContaining(eq("a")));
    }

    // Remaining code no longer compiles, thanks to generics. I think that's a good thing, but
    // I still need to investigate how this behaves with code that doesn't use generics.
    // I expect ClassCastExceptions will be thrown.
    // -Joe.

//    public void testDoesNotMatchObjectThatIsNotCollections() {
//        assertDoesNotMatch("should not match empty list", collectionContaining(eq("a")), "not a collection");
//    }

}
