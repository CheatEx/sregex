package com.zalivka.sregex.matcher;

import junit.framework.TestCase;

public class FewTest extends TestCase {
    private static final Regex FA = new Few(new Char('a'));
    private static final Regex FA_B = new Sequence(new Few(new Char('a')), new Char('b'));
    private static final Regex A_FB = new Sequence(new Char('a'), new Few(new Char('b')));

    public void testFew() {
        assertTrue(Matcher.match(FA, "a"));
        assertTrue(Matcher.match(FA, "aa"));
        assertTrue(Matcher.match(FA, "aaa"));

        assertFalse(Matcher.match(FA, "b"));
        assertFalse(Matcher.match(FA, "bb"));
    }

    public void testFewInSequence() {
        assertTrue(Matcher.match(FA_B, "ab"));
        assertTrue(Matcher.match(FA_B, "aab"));
        assertFalse(Matcher.match(FA_B, "a"));
        assertFalse(Matcher.match(FA_B, "aa"));
        assertFalse(Matcher.match(FA_B, "b"));

        assertTrue(Matcher.match(A_FB, "ab"));
        assertTrue(Matcher.match(A_FB, "abb"));
        assertFalse(Matcher.match(A_FB, "a"));
        assertFalse(Matcher.match(A_FB, "ac"));
        assertFalse(Matcher.match(FA_B, "b"));
    }

    public void testEmpty() {
        assertFalse(Matcher.match(FA, ""));
        assertFalse(Matcher.match(FA_B, ""));
        assertFalse(Matcher.match(A_FB, ""));
    }
}
