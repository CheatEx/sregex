package com.zalivka.sregex.matcher;

public abstract class Regex {
    /*
     * The empty regex.
     * Implemented as a singleton instance as it doesn't maintain any state.
     */
    public static final Regex E = new Regex(true) {
        @Override protected boolean doShift(char c, boolean mark) {
            return false;
        }

        @Override public String toString() {
            return "Regex.E";
        }

        @Override public boolean equals(Object obj) {
            return this == obj || !(obj == null || getClass() != obj.getClass());
        }

        @Override public Regex copy() {
            return E;
        }
    };

    /*
     * Whether this expression could match an empty string.
     */
    public final boolean empty;

    /*
     * Whether this expression matched the input seen since last {@code mark} signal.
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

    /*
     * Pushes a character through [sub]expression represented by this object.
     * `mark` param indicates whether an attempt to match should be made.
     * Returns Whether this character has finished the current match of the expression.
     */
    public boolean shift(char c, boolean mark) {
        marked = doShift(c, mark);
        return marked;
    }

    protected abstract boolean doShift(char c, boolean mark);

    public abstract Regex copy();
}
