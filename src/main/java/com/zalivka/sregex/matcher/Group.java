package com.zalivka.sregex.matcher;

import org.jetbrains.annotations.Nullable;

public class Group extends Unary {
    private @Nullable String match;
    /**
     * This attribute encodes state of the group match,
     * when non-{@code null} nested expression match is in progress.
     */
    private @Nullable StringBuilder matchBuf;

    protected Group(Regex re) {
        super(re.empty, re);
    }

    /**
     * From the algorithm perspective this class is only forwarding calls
     * to the underlying expression.
     * To remember the match group shall record every char between
     * {@code true} going down the expression tree in the {@code mark} argument
     * and {@code true} going up in the method's result.
     * If multiple {@code true} go up - match keeps growing.
     */
    @Override protected boolean doShift(char c, boolean mark) {
        boolean reMarked = re.shift(c, mark);

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
        return '('+re.toString()+')';
    }
}
