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

    @Override public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        Binary binary = (Binary)obj;
        return left.equals(binary.left) && right.equals(binary.right);
    }
}
