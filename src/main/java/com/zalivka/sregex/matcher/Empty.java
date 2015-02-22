package com.zalivka.sregex.matcher;

public class Empty extends Regex {
    public Empty() {
        super(true);
    }

    @Override protected boolean doShift(char c, boolean mark) {
        return false;
    }

    @Override public String toString() {
        return "<e>";
    }

    @Override public boolean equals(Object obj) {
        return this == obj || !(obj == null || getClass() != obj.getClass());
    }
}
