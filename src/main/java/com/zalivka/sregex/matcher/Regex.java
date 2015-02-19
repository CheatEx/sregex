package com.zalivka.sregex.matcher;

public abstract class Regex {
    public final boolean empty;

    private boolean marked = false;

    public Regex(boolean empty) {
        this.empty = empty;
    }

    public boolean marked() {
        return marked;
    }

    public void reset() {
        marked = false;
    }

    public boolean shift(char c, boolean mark) {
        marked = doShift(c, mark);
        return marked;
    }

    protected abstract boolean doShift(char c, boolean mark);
}
