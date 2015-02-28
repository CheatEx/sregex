package com.zalivka.sregex.matcher;

public abstract class Unary extends Regex {
    public final Regex child;

    protected Unary(boolean empty, Regex child) {
        super(empty);
        this.child = child;
    }

    @Override public void reset() {
        super.reset();
        child.reset();
    }

    @Override public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        Unary unary = (Unary)obj;
        return child.equals(unary.child);

    }
}
