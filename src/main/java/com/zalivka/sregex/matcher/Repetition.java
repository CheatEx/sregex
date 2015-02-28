package com.zalivka.sregex.matcher;

public class Repetition extends Unary {
    public Repetition(Regex re) {
        super(true, re);
    }

    @Override protected boolean doShift(char c, boolean mark) {
        return child.shift(c, mark || marked());
    }

    @Override public String toString() {
        return "Repetition("+child.toString()+')';
    }

    @Override public Regex copy() {
        return new Repetition(child.copy());
    }
}
