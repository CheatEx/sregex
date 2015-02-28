package com.zalivka.sregex.matcher;

public class Quantified extends Unary {
    public static final int UNBOUNDED = -1;

    private final int lower;
    private final int upper;
    private final Regex target;

    public Quantified(int lower, int upper, Regex re) {
        super(lower==0, buildWrapper(lower, upper, re));
        target = re;
        this.lower = lower;
        this.upper = upper;
    }

    @Override protected boolean doShift(char c, boolean mark) {
        return child.shift(c, mark);
    }

    @Override public String toString() {
        return "Quantified("+lower+", "+
            (upper==UNBOUNDED ? "U": upper) +
            ", "+target+")";
    }

    @Override public Regex copy() {
        return new Quantified(lower, upper, target);
    }

    /**
     * Compiles quantified expression into compositions of simpler expressions.
     * The idea - given quantification X{lower, upper} it could be represented as
     * X[lower times]X?[upper-lower times].<br/>
     * This approach doesn't work well with the current match groups implementation,
     * so putting groups inside quantifications isn't supported.
     */
    private static Regex buildWrapper(int lower, int upper, Regex re) {
        return new Sequence(lower(lower, re), upper(lower, upper, re));
    }

    private static Regex lower(int lower, Regex re) {
        return repeat(re, lower);
    }

    private static Regex upper(int lower, int upper, Regex re) {
        if (upper==UNBOUNDED)
            return new Repetition(re.copy());
        else
            return repeat(new Optional(re.copy()), upper-lower);
    }

    private static Regex repeat(Regex re, int num) {
        if (num == 0)
            return Regex.E;
        else
            return new Sequence(re.copy(), repeat(re, num-1));
    }
}
