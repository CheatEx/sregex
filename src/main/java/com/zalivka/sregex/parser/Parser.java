package com.zalivka.sregex.parser;

import com.zalivka.sregex.ExpressionException;
import com.zalivka.sregex.matcher.*;

import java.util.ArrayDeque;
import java.util.Deque;

public final class Parser {
    public static Regex parse(String in) throws ExpressionException {
        if (in.isEmpty())
            return new Empty();

        Deque<Regex> s = new ArrayDeque<>();
        read(in, 0, s);
        return s.pop();
    }

    /**
     * @return Whether implicit sequencing should be applied to the result of this call.
     */
    private static boolean read(String in, int idx, Deque<Regex> s) {
        if (idx == in.length()) {
            s.push(new Empty());
            return true;
        }

        char c = in.charAt(idx);

        if (Character.isLetter(c)) {
            s.push(new Char(c));

            if (read(in, idx+1, s)) {
                // Re-ordering, left child lays deeper in the stack.
                Regex right = s.pop();
                Regex left = s.pop();
                s.push(new Sequence(left, right));
            }
            return true;
        } else if (c == '|') {
            read(in, idx+1, s);
            Regex right = s.pop();
            Regex left = s.pop();
            s.push(new Alternative(left, right));

            return false;
        } else
            throw new ExpressionException("not supported yet");
    }

    private static boolean isControl(char c) {
        return c=='|' || c=='?' || c=='*' || c=='+';
    }
}
