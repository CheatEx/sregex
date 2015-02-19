package com.zalivka.sregex.matcher;

public abstract class Binary extends Regex {
    protected final Regex left;
    protected final Regex right;

    public Binary(boolean empty, Regex left, Regex right) {
        super(empty);
        this.left = left;
        this.right = right;
    }

    @Override public void reset() {
        left.reset();
        right.reset();
        super.reset();
    }
}
