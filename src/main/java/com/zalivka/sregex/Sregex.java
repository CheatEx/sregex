package com.zalivka.sregex;

import com.zalivka.sregex.matcher.Matcher;
import com.zalivka.sregex.parser.Parser;

public final class Sregex {
    public static Pattern compile(CharSequence re) throws ExpressionException {
        return new Pattern(Parser.parse(re));
    }

    public static MatchResult match(Pattern p, CharSequence str) {
        return Matcher.match(p.r, str);
    }

//    /**
//     * Tries to match a string against a regular expression pattern.
//     *
//     * @param regex Regular expression.
//     * @param string String to match.
//     * @return The matched substring of {@code null} if match wasn't found.
//     */
    public static MatchResult match(CharSequence re, CharSequence str) throws ExpressionException {
        return Matcher.match(Parser.parse(re), str);
    }
}
