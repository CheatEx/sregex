package com.zalivka.sregex;

import junit.framework.TestCase;

public class BasicTest extends TestCase {
    public void testEmpty() throws ExpressionException {
        assertTrue(Sregex.match("", "").success());
        assertFalse(Sregex.match("a", "").success());
        assertFalse(Sregex.match("a|b", "").success());
        assertFalse(Sregex.match("ab", "").success());
    }

    public void testSimpleSequences() throws ExpressionException {
        assertTrue(Sregex.match("a", "a").success());
        assertFalse(Sregex.match("a", "b").success());
        assertFalse(Sregex.match("a", "ab").success());

        assertTrue(Sregex.match("ab", "ab").success());
        assertFalse(Sregex.match("ab", "a").success());
        assertFalse(Sregex.match("ab", "b").success());
        assertFalse(Sregex.match("ab", "abc").success());

        assertTrue(Sregex.match("abc", "abc").success());
        assertFalse(Sregex.match("abc", "a").success());
        assertFalse(Sregex.match("abc", "ab").success());
        assertFalse(Sregex.match("abc", "abd").success());
        assertFalse(Sregex.match("abc", "abcd").success());
    }

    public void testSimpleAlternation() throws ExpressionException {
        assertTrue(Sregex.match("a|b", "a").success());
        assertTrue(Sregex.match("a|b", "b").success());
        assertFalse(Sregex.match("a|b", "c").success());

        assertTrue(Sregex.match("a|b|c", "a").success());
        assertTrue(Sregex.match("a|b|c", "b").success());
        assertTrue(Sregex.match("a|b|c", "c").success());
        assertFalse(Sregex.match("a|b|c", "d").success());
    }

    public void testExample() throws ExpressionException {
        assertTrue(Sregex.match("((abc)*|(abcd))(d|e)", "abcabcabcd").success());
        assertTrue(Sregex.match("((abc)*|(abcd))(d|e)", "abcabcabce").success());

        assertFalse(Sregex.match("((abc)*|(abcd))(d|e)", "abcabcabcdd").success());
        assertFalse(Sregex.match("((abc)*|(abcd))(d|e)", "abcabcabcf").success());
        assertFalse(Sregex.match("((abc)*|(abcd))(d|e)", "abcabcdabcd").success());
    }
}
