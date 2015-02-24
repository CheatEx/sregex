package com.zalivka.sregex.matcher;

/**
 * Zero or one repetitions of the sub-expression.
 */
public class Optional extends Unary {
    public Optional(Regex re) {
        super(true, re);
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override protected boolean doShift(char c, boolean mark) {
        if (marked())
            return false;
        else
            return re.shift(c, mark);
    }

    @Override public String toString() {
        return "Optional("+re.toString()+')';
    }
}
