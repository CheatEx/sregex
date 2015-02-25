package com.zalivka.sregex.matcher;

/**
 * One or more repetitions of the sub-expression.
 */
public class Few extends Unary {
    public Few(Regex re) {
        super(false, re);
    }

    @Override protected boolean doShift(char c, boolean mark) {
        return re.shift(c, mark || marked());
    }

    @Override public String toString() {
        return "Few("+re.toString()+')';
    }

    @Override public Regex copy() {
        return new Few(re.copy());
    }
}
