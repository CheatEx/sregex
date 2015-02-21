package com.zalivka.sregex.matcher;

/**
 * Any number of repetitions of the sub-expression.
 */
public class Repetition extends Unary {
    public Repetition(Regex re) {
        super(true, re);
    }

    @Override protected boolean doShift(char c, boolean mark) {
        return re.shift(c, mark || marked());
    }
}
