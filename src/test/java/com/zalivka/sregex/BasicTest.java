package com.zalivka.sregex;

import junit.framework.TestCase;

public class BasicTest extends TestCase {
    public void testEmpty() throws ExpressionException {
        assertTrue(Sregex.match("", "").succes());
        assertFalse(Sregex.match("a", "").succes());
        assertFalse(Sregex.match("a|b", "").succes());
        assertFalse(Sregex.match("ab", "").succes());
    }

    public void testSimpleSequences() throws ExpressionException {
        assertTrue(Sregex.match("a", "a").succes());
        assertFalse(Sregex.match("a", "b").succes());
        assertFalse(Sregex.match("a", "ab").succes());

        assertTrue(Sregex.match("ab", "ab").succes());
        assertFalse(Sregex.match("ab", "a").succes());
        assertFalse(Sregex.match("ab", "b").succes());
        assertFalse(Sregex.match("ab", "abc").succes());

        assertTrue(Sregex.match("abc", "abc").succes());
        assertFalse(Sregex.match("abc", "a").succes());
        assertFalse(Sregex.match("abc", "ab").succes());
        assertFalse(Sregex.match("abc", "abd").succes());
        assertFalse(Sregex.match("abc", "abcd").succes());
    }

    public void testSimpleAlternation() throws ExpressionException {
        assertTrue(Sregex.match("a|b", "a").succes());
        assertTrue(Sregex.match("a|b", "b").succes());
        assertFalse(Sregex.match("a|b", "c").succes());

        assertTrue(Sregex.match("a|b|c", "a").succes());
        assertTrue(Sregex.match("a|b|c", "b").succes());
        assertTrue(Sregex.match("a|b|c", "c").succes());
        assertFalse(Sregex.match("a|b|c", "d").succes());
    }

    public void testExample() throws ExpressionException {
        assertTrue(Sregex.match("((abc)*|(abcd))(d|e)", "abcabcabcd").succes());
        assertTrue(Sregex.match("((abc)*|(abcd))(d|e)", "abcabcabce").succes());

        assertFalse(Sregex.match("((abc)*|(abcd))(d|e)", "abcabcabcdd").succes());
        assertFalse(Sregex.match("((abc)*|(abcd))(d|e)", "abcabcabcf").succes());
        assertFalse(Sregex.match("((abc)*|(abcd))(d|e)", "abcabcdabcd").succes());
    }
}
