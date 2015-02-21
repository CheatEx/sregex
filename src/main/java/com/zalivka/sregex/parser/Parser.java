package com.zalivka.sregex.parser;

import com.zalivka.sregex.ExpressionException;
import com.zalivka.sregex.matcher.Alternative;
import com.zalivka.sregex.matcher.Char;
import com.zalivka.sregex.matcher.Empty;
import com.zalivka.sregex.matcher.Regex;

import java.util.ArrayDeque;
import java.util.Deque;

public final class Parser {
    public static Regex parse(String in) throws ExpressionException {
        // Unify with read's sequencing.
        if (in.isEmpty())
            return new Empty();

        Deque<Regex> s = new ArrayDeque<>();
        read(in, 0, s);
        return s.pop();
    }

    private static void read(String in, int idx, Deque<Regex> s) {
        if (idx == in.length()) {
//            s.push(new Empty());
            return;
        }

        char c = in.charAt(idx);

        if (Character.isLetter(c)) {
            s.push(new Char(c));
            read(in, idx+1, s);
        } else if (c == '|') {
            read(in, idx+1, s);
            s.push(new Alternative(s.pop(), s.pop()));
        } else
            throw new ExpressionException("not supported yet");
    }
}
