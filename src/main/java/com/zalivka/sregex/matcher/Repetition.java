package com.zalivka.sregex.matcher;

public class Repetition extends Unary {
    public Repetition(Regex re) {
        super(true, re);
    }

    @Override protected boolean doShift(char c, boolean mark) {
        return re.shift(c, mark || marked());
    }
}
