package com.zalivka.sregex.matcher;

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
}
