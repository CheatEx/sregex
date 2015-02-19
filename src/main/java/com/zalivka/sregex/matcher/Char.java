package com.zalivka.sregex.matcher;

public class Char extends Regex {
    public final char val;

    public Char(char val) {
        super(false);
        this.val = val;
    }

    @Override protected boolean doShift(char c, boolean mark) {
        return mark && c == val;
    }
}
