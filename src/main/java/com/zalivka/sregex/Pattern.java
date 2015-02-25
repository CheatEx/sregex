package com.zalivka.sregex;

import com.zalivka.sregex.matcher.Regex;

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
