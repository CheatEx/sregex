package com.zalivka.sregex.matcher;

public class Optional extends Regex {
    public final Regex re;

    public Optional(Regex re) {
        super(true);
        this.re = re;
    }

    @Override protected boolean doShift(char c, boolean mark) {
        if (marked())
            return false;
        else
            return re.shift(c, mark);
    }

    @Override public void reset() {
        super.reset();
        re.reset();
    }
}
