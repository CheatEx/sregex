package com.zalivka.sregex;

import com.zalivka.sregex.matcher.Matcher;
import com.zalivka.sregex.parser.Parser;
import org.jetbrains.annotations.Nullable;

public final class Sregex {
//    /**
//     * Tries to match a string against a regular expression pattern written
//     * in limited subset of the language,
//     * TODO describe the subset.
//     *
//     * @param regex Regular expression.
//     * @param string String to match.
//     * @return The matched substring of {@code null} if match wasn't found.
//     */
//    @Nullable public static String match(String regex, String string) {
//        return null;
//    }

    public static boolean match(String re, String str) throws ExpressionException {
        return Matcher.match(Parser.parse(re), str);
    }
}
