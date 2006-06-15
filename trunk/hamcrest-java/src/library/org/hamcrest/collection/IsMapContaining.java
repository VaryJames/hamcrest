package org.hamcrest.collection;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import static org.hamcrest.core.IsAnything.anything;
import static org.hamcrest.core.IsEqual.eq;

import java.util.Map;
import java.util.Map.Entry;

public class IsMapContaining<K,V> implements Matcher<Map<K, V>> {

    private final Matcher<K> keyMatcher;
    private final Matcher<V> valueMatcher;

    public IsMapContaining(Matcher<K> keyMatcher, Matcher<V> valueMatcher) {
        this.keyMatcher = keyMatcher;
        this.valueMatcher = valueMatcher;
    }

    public boolean match(Map<K, V> map) {
        if (map == null) {
            return false;
        }
        for (Entry<K, V> entry : map.entrySet()) {
            if (keyMatcher.match(entry.getKey()) && valueMatcher.match(entry.getValue())) {
                return true;
            }
        }
        return false;
    }

    public void describeTo(Description description) {
        description.appendText("map containing [");
        keyMatcher.describeTo(description);
        description.appendText("->");
        valueMatcher.describeTo(description);
        description.appendText("]");
    }

    @Factory
    public static <K,V> Matcher<Map<K,V>> mapContaining(Matcher<K> keyMatcher, Matcher<V> valueMatcher) {
        return new IsMapContaining<K,V>(keyMatcher, valueMatcher);
    }

    @Factory
    public static <K,V> Matcher<Map<K,V>> mapContaining(K key, V value) {
        return mapContaining(eq(key), eq(value));
    }

    @Factory
    public static <K,V> Matcher<Map<K,V>> mapWithKey(Matcher<K> keyMatcher) {
        return mapContaining(keyMatcher, (Matcher<V>)anything());
    }

    @Factory
    public static <K,V> Matcher<Map<K,V>> mapWithKey(K key) {
        return mapWithKey(eq(key));
    }

    @Factory
    public static <K,V> Matcher<Map<K,V>> mapWithValue(Matcher<V> valueMatcher) {
        return mapContaining((Matcher<K>)anything(), valueMatcher);
    }

    @Factory
    public static <K,V> Matcher<Map<K,V>> mapWithValue(V value) {
        return mapWithValue(eq(value));
    }

}
