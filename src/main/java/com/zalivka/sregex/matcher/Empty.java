package com.zalivka.sregex.matcher;

public class Empty extends Regex {
    public Empty() {
        super(true);
    }

    @Override protected boolean doShift(char c, boolean mark) {
        return false;
    }
}
