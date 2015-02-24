package com.zalivka.sregex.parser;

import com.zalivka.sregex.ExpressionException;
import com.zalivka.sregex.matcher.*;

public final class Parser {
    public static Regex parse(String in) throws ExpressionException {
        Source s = new Source(in);
        return regex(s);
    }

    private static Regex regex(Source s) {
        Regex term = term(s);

        while(s.more() && s.peek()=='|') {
            s.drop('|');
            Regex nextTerm = term(s);
            term = new Alternative(term, nextTerm);
        }

        return term;
    }

    private static Regex term(Source s) {
        Regex factor = Regex.E;

        while (s.more() && s.peek() != ')' && s.peek() != '|') {
            Regex nextFactor = factor(s);
            factor = new Sequence(factor, nextFactor);
        }

        return factor;
    }

    private static Regex factor(Source s) {
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
                default:
                    break fl;
            }
        }

        return base;
    }

    private static Regex base(Source s) {
        switch (s.peek()) {
            case '(':
                s.drop('(');
                Regex r = new Group(regex(s));
                s.drop(')');
                return r;
            // case '[': // TODO range here.
            default:
                char c = s.pop();
                if (Character.isAlphabetic(c))
                    return new Char(c);
                else
                    throw new ExpressionException("Unsupported character :"+c);
        }
    }

    private static class Source {
        private final String input;
        private int idx;

        private Source(String input) {
            this.input = input;
            this.idx = 0;
        }

        public char peek() {
            return input.charAt(idx);
        }

        public char pop() {
            char c = peek();
            drop(c);
            return c;
        }

        public void drop(char c) {
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
