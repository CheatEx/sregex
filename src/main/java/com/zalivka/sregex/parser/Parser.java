package com.zalivka.sregex.parser;

import com.zalivka.sregex.ExpressionException;
import com.zalivka.sregex.matcher.*;

public final class Parser {
    public static Regex parse(CharSequence in) throws ExpressionException {
        Source s = new Source(in);
        return regex(s);
    }

    private static Regex regex(Source s) throws ExpressionException {
        Regex term = term(s);

        while(s.more() && s.peek()=='|') {
            s.drop('|');
            Regex nextTerm = term(s);
            term = new Alternative(term, nextTerm);
        }

        return term;
    }

    private static Regex term(Source s) throws ExpressionException {
        Regex factor = Regex.E;

        while (s.more() && s.peek() != ')' && s.peek() != '|') {
            Regex nextFactor = factor(s);
            factor = new Sequence(factor, nextFactor);
        }

        return factor;
    }

    private static Regex factor(Source s) throws ExpressionException {
        Regex base = base(s);

        fl: while (s.more()) {
            switch (s.peek()) {
                case '*':
                    s.drop('*');
                    base = new Repetition(base);
                    break;
                case '?':
                    s.drop('?');
                    base = new Optional(base);
                    break;
                case '+':
                    s.drop('+');
                    base = new Few(base);
                    break;
                case '{':
                    base = quantity(s, base);
                    break;
                default:
                    break fl;
            }
        }

        return base;
    }

    private static Regex base(Source s) throws ExpressionException {
        switch (s.peek()) {
            case '(':
                s.drop('(');
                Regex r = new Group(regex(s));
                s.drop(')');
                return r;
            case '[':
                return range(s);
            default:
                char c = s.pop();
                if (Character.isAlphabetic(c))
                    return new Char(c);
                else
                    throw new ExpressionException("Unsupported character :"+c);
        }
    }

    private static Regex quantity(Source s, Regex base) throws ExpressionException {
        s.drop('{');

        int lower;
        char lowerC = s.peek();
        if (Character.isDigit(lowerC)) {
            lower = lowerC-'0';
            s.drop(lowerC);
        } else
            lower = 0;

        s.drop(',');

        int upper;
        char upperC = s.peek();
        if (Character.isDigit(upperC)) {
            upper = upperC - '0';
            s.drop(upperC);
        } else
            upper = Quantified.UNBOUNDED;

        s.drop('}');

        return new Quantified(lower, upper, base);
    }

    private static Regex range(Source s) throws ExpressionException {
        boolean positive = true;
        s.drop('[');
        char from = s.pop();
        if (from == '^') {
            positive = false;
            from = s.pop();
        }
        s.drop('-');
        char to = s.pop();
        s.drop(']');

        if (positive && from > to)
            throw new ExpressionException("Empty character range detected ["+from+"-"+to+"]");

        if (!positive && from > to)
            throw new ExpressionException("Unbounded negative character range detected [^"+from+"-"+to+"]");

        return new CharRange(from, to, positive);
    }

    private static class Source {
        private final CharSequence input;
        private int idx;

        private Source(CharSequence input) {
            this.input = input;
            this.idx = 0;
        }

        public char peek() {
            return input.charAt(idx);
        }

        public char pop() {
            char c = peek();
            idx++;
            return c;
        }

        public void drop(char c) throws ExpressionException {
            if (peek() == c)
                idx++;
            else
                throw new ExpressionException("Wrong character at "+idx+" expected "+c+" but was "+peek());
        }

        boolean more() {
            return idx < input.length();
        }
    }
}
