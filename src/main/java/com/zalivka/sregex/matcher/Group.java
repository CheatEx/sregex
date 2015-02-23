package com.zalivka.sregex.matcher;

public class Group extends Unary {
    private StringBuilder match = new StringBuilder();

    protected Group(Regex re) {
        super(re.empty, re);
    }

    @Override protected boolean doShift(char c, boolean mark) {
        // Remember all characters that passed through
        // as intended for marking before match.
        if (mark && !marked())
            match.append(c);
        return re.shift(c, mark);
    }

    @Override public void reset() {
        super.reset();
        match.delete(0, match.length());
    }

    public String match() {
        return match.toString();
    }

    @Override public String toString() {
        return '('+re.toString()+')';
    }
}
