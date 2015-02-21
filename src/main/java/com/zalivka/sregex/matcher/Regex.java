package com.zalivka.sregex.matcher;

/**
 * Base class for all expressions.
 * Defines both syntax [sub]tree of a regular expression and the matching algorithm.
 */
public abstract class Regex {
    /**
     * Whether this [sub]expression could match an empty string.
     */
    public final boolean empty;

    /**
     * Whether this sub-expression matched the input seen so far.
     */
    private boolean marked = false;

    public Regex(boolean empty) {
        this.empty = empty;
    }

    public boolean marked() {
        return marked;
    }

    public void reset() {
        marked = false;
    }

    public boolean shift(char c, boolean mark) {
        marked = doShift(c, mark);
        return marked;
    }

    protected abstract boolean doShift(char c, boolean mark);
}
