package com.zalivka.sregex.matcher;

/**
 * Base class for all expressions.
 * Defines both syntax [sub]tree of a regular expression and the matching algorithm.
 */
public abstract class Regex {
    /**
     * The empty regex.
     * Implemented as a singleton instance as it doesn't maintain any state.
     */
    public static final Regex E = new Regex(true) {
        @Override protected boolean doShift(char c, boolean mark) {
            return false;
        }

        @Override public String toString() {
            return "<e>";
        }

        @Override public boolean equals(Object obj) {
            return this == obj || !(obj == null || getClass() != obj.getClass());
        }
    };

    /**
     * Whether this expression could match an empty string.
     */
    public final boolean empty;

    /**
     * Whether this expression matched the input seen so far.
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

    /**
     * Pushes a character through [sub]expression represented by this object.
     * @param c Next character in matched sequence.
     * @param mark Whether attempt to match should be made.
     * @return Whether this character has finished the current match of the expression.
     */
    public boolean shift(char c, boolean mark) {
        marked = doShift(c, mark);
        return marked;
    }

    protected abstract boolean doShift(char c, boolean mark);
}
