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

    @Override public String toString() {
        return "Char('"+val+"')";
    }

    @Override public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        Char aChar = (Char)obj;
        return val == aChar.val;
    }

    @Override public Regex copy() {
        return new Char(val);
    }
}
