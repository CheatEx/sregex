package com.zalivka.sregex.matcher;

public abstract class Unary extends Regex {
    public final Regex re;

    protected Unary(boolean empty, Regex re) {
        super(empty);
        this.re = re;
    }

    @Override public void reset() {
        super.reset();
        re.reset();
    }

    @Override public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        Unary unary = (Unary)obj;
        return re.equals(unary.re);

    }
}
