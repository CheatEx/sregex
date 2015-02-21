package com.zalivka.sregex.matcher;

public class Alternative extends Binary {
    public Alternative(Regex left, Regex right) {
        super(left.empty || right.empty, left, right);
    }

    @Override protected boolean doShift(char c, boolean mark) {
        boolean leftMarked = left.shift(c, mark);
        boolean rigthMarked = right.shift(c, mark);
        return leftMarked || rigthMarked;
    }

    @Override public String toString() {
        return left.toString()+'|'+right.toString();
    }
}
