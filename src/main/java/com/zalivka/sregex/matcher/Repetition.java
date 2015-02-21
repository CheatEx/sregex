package com.zalivka.sregex.matcher;

public class Repetition extends Regex {
    public final Regex re;

    public Repetition(Regex re) {
        super(true);
        this.re = re;
    }

    @Override protected boolean doShift(char c, boolean mark) {
        return re.shift(c, mark || marked());
    }

    @Override public void reset() {
        super.reset();
        re.reset();
    }
}
