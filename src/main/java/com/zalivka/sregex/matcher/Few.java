package com.zalivka.sregex.matcher;

public class Few extends Unary {
    public Few(Regex re) {
        super(false, re);
    }

    @Override protected boolean doShift(char c, boolean mark) {
        return child.shift(c, mark || marked());
    }

    @Override public String toString() {
        return "Few("+child.toString()+')';
    }

    @Override public Regex copy() {
        return new Few(child.copy());
    }
}
