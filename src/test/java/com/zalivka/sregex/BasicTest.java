package com.zalivka.sregex;

import junit.framework.TestCase;

public class BasicTest extends TestCase {
    public void testEmpty() throws ExpressionException {
        assertTrue(Sregex.matches("", ""));
        assertFalse(Sregex.matches("a", ""));
        assertFalse(Sregex.matches("a|b", ""));
        assertFalse(Sregex.matches("ab", ""));
    }

    public void testSimpleSequences() throws ExpressionException {
        assertTrue(Sregex.matches("a", "a"));
        assertFalse(Sregex.matches("a", "b"));
        assertFalse(Sregex.matches("a", "ab"));

        assertTrue(Sregex.matches("ab", "ab"));
        assertFalse(Sregex.matches("ab", "a"));
        assertFalse(Sregex.matches("ab", "b"));
        assertFalse(Sregex.matches("ab", "abc"));

        assertTrue(Sregex.matches("abc", "abc"));
        assertFalse(Sregex.matches("abc", "a"));
        assertFalse(Sregex.matches("abc", "ab"));
        assertFalse(Sregex.matches("abc", "abd"));
        assertFalse(Sregex.matches("abc", "abcd"));
    }

    public void testSimpleAlternation() throws ExpressionException {
        assertTrue(Sregex.matches("a|b", "a"));
        assertTrue(Sregex.matches("a|b", "b"));
        assertFalse(Sregex.matches("a|b", "c"));

        assertTrue(Sregex.matches("a|b|c", "a"));
        assertTrue(Sregex.matches("a|b|c", "b"));
        assertTrue(Sregex.matches("a|b|c", "c"));
        assertFalse(Sregex.matches("a|b|c", "d"));
    }

    public void testExample() throws ExpressionException {
        assertTrue(Sregex.matches("((abc)*|(abcd))(d|e)", "abcabcabcd"));
        assertTrue(Sregex.matches("((abc)*|(abcd))(d|e)", "abcabcabce"));

        assertFalse(Sregex.matches("((abc)*|(abcd))(d|e)", "abcabcabcdd"));
        assertFalse(Sregex.matches("((abc)*|(abcd))(d|e)", "abcabcabcf"));
        assertFalse(Sregex.matches("((abc)*|(abcd))(d|e)", "abcabcdabcd"));
    }
}
