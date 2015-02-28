package com.zalivka.sregex.matcher;

import org.jetbrains.annotations.Nullable;

public class Group extends Unary {
    private @Nullable String match;
    /*
     * This attribute encodes state of the group match,
     * when non-{@code null} the nested expression's match is in progress.
     */
    private @Nullable StringBuilder matchBuf;

    public Group(Regex re) {
        super(re.empty, re);
    }

    /*
     * From the algorithm perspective this class is totally transparent.<br/>
     * Group shall record every char between {@code true} going down the expression tree
     * in the {@code mark} argument and {@code true} going up in the method's result.
     * That char sequence is the the match.
     * If multiple {@code true}s go up - match keeps growing.
     */
    @Override protected boolean doShift(char c, boolean mark) {
        boolean reMarked = child.shift(c, mark);

        if (mark)
            matchBuf = new StringBuilder();
        if (matchBuf != null)
            matchBuf.append(c);
        if (reMarked && matchBuf!=null)
            match = matchBuf.toString();

        return reMarked;
    }

    @Override public void reset() {
        super.reset();
        matchBuf = null;
        match = null;
    }

    public @Nullable String match() {
        return match;
    }

    @Override public String toString() {
        return "Group("+child.toString()+')';
    }

    @Override public Regex copy() {
        return new Group(child.copy());
    }
}
