package com.zalivka.sregex;

import junit.framework.TestCase;

public class BasicTest extends TestCase {
    public void testEmpty() {
        assertTrue(Sregex.match("", "").succes());
        assertFalse(Sregex.match("a", "").succes());
        assertFalse(Sregex.match("a|b", "").succes());
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
        assertTrue(Sregex.match("a|b", "a").succes());
        assertTrue(Sregex.match("a|b", "b").succes());
        assertFalse(Sregex.match("a|b", "c").succes());

        assertTrue(Sregex.match("a|b|c", "a").succes());
        assertTrue(Sregex.match("a|b|c", "b").succes());
        assertTrue(Sregex.match("a|b|c", "c").succes());
        assertFalse(Sregex.match("a|b|c", "d").succes());
    }
}
