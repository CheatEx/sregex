package com.zalivka.sregex.matcher;

import junit.framework.TestCase;

public class ComplexTest extends TestCase {
    /// ((abc)*|(abcd))(d|e)
    private final Regex ARTICLE_EXAMPLE =
        new Sequence(
            new Alternative(
                new Repetition(
                    new Sequence(
                        new Sequence(
                            new Char('a'),
                            new Char('b')),
                        new Char('c'))),
                new Sequence(
                    new Sequence(
                        new Sequence(
                            new Char('a'),
                            new Char('b')),
                        new Char('c')),
                    new Char('d'))),
            new Alternative(
                new Char('d'),
                new Char('e')
            ));

    public void testExample() {
        assertTrue(Matcher.match(ARTICLE_EXAMPLE, "abcabcabcd").success());
        assertTrue(Matcher.match(ARTICLE_EXAMPLE, "abcabcabce").success());
        assertTrue(Matcher.match(ARTICLE_EXAMPLE, "abcdd").success());
        assertTrue(Matcher.match(ARTICLE_EXAMPLE, "abcde").success());

        assertFalse(Matcher.match(ARTICLE_EXAMPLE, "abcabcabcdd").success());
        assertFalse(Matcher.match(ARTICLE_EXAMPLE, "abcabcabcf").success());
        assertFalse(Matcher.match(ARTICLE_EXAMPLE, "abcabcdabcd").success());
    }
}
