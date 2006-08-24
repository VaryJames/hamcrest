package org.hamcrest;

/**
 * A description of a Matcher. A Matcher will describe itself to a description which can later be used for reporting.
 *
 * @see Matcher#describeTo(Description)
 */
public interface Description {

    /**
     * Append some plain text to the description.
     */
    Description appendText(String text);
    
    /**
     * Append an arbitary value to the description.
     */
    Description appendValue(Object value);
    
    /**
     * Append a list of values to the description.
     */
    <T> Description appendValueList(String start, String separator, String end,
    							    T... values);

    /**
     * Append a list of values to the description.
     */
    <T> Description appendValueList(String start, String separator, String end,
    							    Iterable<T> values);
}
