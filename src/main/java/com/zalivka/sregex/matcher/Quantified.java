package com.zalivka.sregex.matcher;

public class Quantified extends Unary {
    public static final int UNBOUNDED = -1;

    private final int lower;
    private final int upper;
    private final Regex target;

    public Quantified(Regex re, int lower, int upper) {
        super(lower==0, buildWrapper(lower, upper, re));
        target = re;
        this.lower = lower;
        this.upper = upper;
    }

    @Override protected boolean doShift(char c, boolean mark) {
        return re.shift(c, mark);
    }

    @Override public String toString() {
        return "Quantified("+lower+", "+upper+", "+target+")";
    }

    @Override public Regex copy() {
        return new Quantified(target, lower, upper);
    }

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
