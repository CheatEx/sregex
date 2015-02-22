package com.zalivka.sregex;

import com.zalivka.sregex.matcher.Matcher;
import com.zalivka.sregex.parser.Parser;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

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
    public static MatchResult match(String re, String str) throws ExpressionException {
        return Matcher.match(Parser.parse(re), str) ? SUCCESS : FAILURE;
    }

    private static final MatchResult SUCCESS = new MatchResult() {
        @Override public boolean succes() {
            return true;
        }

        @Override public List<String> groups() {
            return Collections.emptyList();
        }
    };

    private static final MatchResult FAILURE = new MatchResult() {
        @Override public boolean succes() {
            return false;
        }

        @Override public List<String> groups() {
            return Collections.emptyList();
        }
    };
}
