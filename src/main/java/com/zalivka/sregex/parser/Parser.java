package com.zalivka.sregex.parser;

import com.zalivka.sregex.ExpressionException;
import com.zalivka.sregex.matcher.*;

import java.util.ArrayDeque;
import java.util.Deque;

public final class Parser {
    public static Regex parse(String in) throws ExpressionException {
        Deque<Regex> s = new ArrayDeque<>();
        read(in, 0, s);
        return s.pop();
    }

    /**
     * @return Whether implicit sequencing should be applied to the result of this call.
     */
    private static boolean read(String in, int idx, Deque<Regex> s) {
        if (idx == in.length()) {
            s.push(Regex.E);
            return true;
        }

        char c = in.charAt(idx);

        if (Character.isLetter(c)) {
            s.push(new Char(c));

            if (read(in, idx+1, s)) {
                // Re-ordering, left child lays deeper in the stack.
                Regex right = s.pop();
                if (s.isEmpty())
                    s.push(right);
                else {
                    Regex left = s.pop();
                    s.push(new Sequence(left, right));
                }
            }
            return true;
        } else if (c == '|') {
            Regex left = s.pop();
            read(in, idx+1, s);
            s.push(new Alternative(left, s.pop()));
            return false;
        } else if (c == '*') {
            s.push(new Repetition(s.pop()));
            read(in, idx+1, s);
            return true;
        } else if (c == '+') {
            s.push(new Few(s.pop()));
            read(in, idx+1, s);
            return true;
        } else if (c == '?') {
            s.push(new Optional(s.pop()));
            read(in, idx+1, s);
            return true;
        } else
            throw new ExpressionException("not supported yet");
    }
}
