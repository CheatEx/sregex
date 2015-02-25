package com.zalivka.sregex;

import com.zalivka.sregex.matcher.Regex;

/**
 * Represents pre-compiled pattern that can be re-used to match multiple strings.
 * <br/>
 * Instances of this class are not meant to be used concurrently.
 * It is recommended to create separate instances for each thread or externally
 * synchronize access.
 */
public class Pattern {
    final Regex r;

    Pattern(Regex r) {
        this.r = r;
    }

    @Override public String toString() {
        // TODO use usual syntax.
        return r.toString();
    }
}
