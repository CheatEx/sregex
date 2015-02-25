package com.zalivka.sregex.matcher;

public class CharRange extends Regex {
    private final char from;
    private final char to;
    private final boolean positive;

    public CharRange(char from, char to, boolean positive) {
        super(false);
        this.from = from;
        this.to = to;
        this.positive = positive;
    }

    @Override protected boolean doShift(char c, boolean mark) {
        return mark &&
            positive ? (c>=from && c<=to) : (c<from || c>to);
    }

    @Override public String toString() {
        return "CharRange('"+from+", "+to+", "+positive+"')";
    }

    @Override public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        CharRange range = (CharRange)obj;
        return from==range.from && to==range.to && positive ==range.positive;
    }
}
