package com.zalivka.sregex;

import com.zalivka.sregex.matcher.Matcher;
import com.zalivka.sregex.parser.Parser;

/**
 * Entry point for the library API.
 */
public final class Sregex {
    /**
     * Parse the given expression and prepare it to be applied
     * on input strings.
     *
     * @param pattern String representation of the pattern.
     * @return Parsed pattern.
     * @throws ExpressionException If pattern contained a syntax error.
     */
    public static Pattern compile(CharSequence pattern) throws ExpressionException {
        return new Pattern(Parser.parse(pattern));
    }

    /**
     * Try to match an input string against a pattern.
     *
     * @param pattern Compiled pattern.
     * @param input Input string.
     * @return Match result.
     *
     * @see MatchResult
     */
    public static MatchResult match(Pattern pattern, CharSequence input) {
        return Matcher.match(pattern.tree, input);
    }

    /**
     * Shortcut method, exactly equivalent to
     * {@code match(compile(pattern), input)}.
     */
    public static MatchResult match(CharSequence pattern, CharSequence input) throws ExpressionException {
        return match(compile(pattern), input);
    }

    /**
     * Boolean-valued shortcut method, exactly equivalent to
     * {@code match(compile(pattern), input).success()}.
     */
    public static boolean matches(CharSequence pattern, CharSequence input) throws ExpressionException {
        return match(compile(pattern), input).success();
    }

    /**
     * Boolean-valued shortcut method, exactly equivalent to
     * {@code match(pattern, input).success()}.
     */
    public static boolean matches(Pattern pattern, CharSequence input) {
        return match(pattern, input).success();
    }

    private Sregex() {}
}
