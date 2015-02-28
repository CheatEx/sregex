package com.zalivka.sregex.matcher;

public class Optional extends Unary {
    private final Regex target;

    public Optional(Regex re) {
        super(true, wrapper(re));
        target = re;
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override protected boolean doShift(char c, boolean mark) {
        return child.shift(c, mark);
    }

    @Override public String toString() {
        return "Optional("+target.toString()+')';
    }

    @Override public Regex copy() {
        return new Optional(child.copy());
    }

    private static Regex wrapper(Regex re) {
        return new Alternative(Regex.E, re);
    }
}
