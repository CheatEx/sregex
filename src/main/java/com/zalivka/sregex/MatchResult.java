package com.zalivka.sregex;

import java.util.List;

/**
 * Result of matching an input against a pattern.
 */
public interface MatchResult {
    /**
     * @return Whether the match was successful.
     */
    boolean success();

    /**
     * List of matched groups. Groups are numbered by their opening parenthesis
     * from left to right, see README for more details and examples.
     * Depending on the original pattern the result could be empty even if the match
     * was successful and the result is guaranteed to be empty if the match failed.
     *
     * @return An immutable list of matched groups.
     */
    List<String> groups();
}
