package com.zalivka.sregex.matcher;

public class Few extends Unary {
    public Few(Regex re) {
        super(false, re);
    }

    @Override protected boolean doShift(char c, boolean mark) {
        return re.shift(c, mark || marked());
    }
}
