package com.zalivka.sregex.matcher;

/**
 * Zero or one repetitions of the sub-expression.
 */
public class Optional extends Unary {
    private final Regex target;

    public Optional(Regex re) {
        super(true, wrapper(re));
        target = re;
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override protected boolean doShift(char c, boolean mark) {
        return re.shift(c, mark);
    }

    @Override public String toString() {
        return "Optional("+target.toString()+')';
    }

    @Override public Regex copy() {
        return new Optional(re.copy());
    }

    private static Regex wrapper(Regex re) {
        return new Alternative(Regex.E, re);
    }
}
