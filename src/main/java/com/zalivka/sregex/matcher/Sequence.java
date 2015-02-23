package com.zalivka.sregex.matcher;

public class Sequence extends Binary {
    public Sequence(Regex left, Regex right) {
        super(left.empty && right.empty, left, right);
    }

    @Override protected boolean doShift(char c, boolean mark) {
        boolean oldLeftMarked = left.marked();
        boolean leftMarked = left.shift(c, mark);
        boolean rightMarked = right.shift(
            c, oldLeftMarked || (mark && left.empty));
        return (leftMarked && right.empty) || rightMarked;
    }

    @Override public String toString() {
        return '['+left.toString()+right.toString()+']';
    }
}
