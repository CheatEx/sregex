package com.zalivka.sregex;

import junit.framework.TestCase;

public class BasicTest extends TestCase {
    public void testEmpty() {
        assertTrue(Sregex.match("", ""));
        assertFalse(Sregex.match("a", ""));
        assertFalse(Sregex.match("a|b", ""));
//        assertFalse(Sregex.match("ab", ""));
    }

    public void testSimpleSequences() {
//        assertTrue(Sregex.match("a", "a"));
//        assertFalse(Sregex.match("a", "b"));
//        assertFalse(Sregex.match("a", "ab"));
//
//        assertTrue(Sregex.match("ab", "ab"));
//        assertFalse(Sregex.match("ab", "a"));
//        assertFalse(Sregex.match("ab", "b"));
//        assertFalse(Sregex.match("ab", "abc"));
//
//        assertTrue(Sregex.match("abc", "abc"));
//        assertFalse(Sregex.match("abc", "a"));
//        assertFalse(Sregex.match("abc", "ab"));
//        assertFalse(Sregex.match("abc", "abd"));
//        assertFalse(Sregex.match("abc", "abcd"));
    }

    public void testSimpleAlternation() {
        assertTrue(Sregex.match("a|b", "a"));
        assertTrue(Sregex.match("a|b", "b"));
        assertFalse(Sregex.match("a|b", "c"));

        assertTrue(Sregex.match("a|b|c", "a"));
        assertTrue(Sregex.match("a|b|c", "b"));
        assertTrue(Sregex.match("a|b|c", "c"));
        assertFalse(Sregex.match("a|b|c", "d"));
    }
}
