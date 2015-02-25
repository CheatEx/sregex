package com.zalivka.sregex.matcher;

import junit.framework.TestCase;

public class FewTest extends TestCase {
    private static final Regex FA = new Few(new Char('a'));
    private static final Regex FA_B = new Sequence(new Few(new Char('a')), new Char('b'));
    private static final Regex A_FB = new Sequence(new Char('a'), new Few(new Char('b')));

    public void testFew() {
        assertTrue(Matcher.match(FA, "a").success());
        assertTrue(Matcher.match(FA, "aa").success());
        assertTrue(Matcher.match(FA, "aaa").success());

        assertFalse(Matcher.match(FA, "b").success());
        assertFalse(Matcher.match(FA, "bb").success());
    }

    public void testFewInSequence() {
        assertTrue(Matcher.match(FA_B, "ab").success());
        assertTrue(Matcher.match(FA_B, "aab").success());
        assertFalse(Matcher.match(FA_B, "a").success());
        assertFalse(Matcher.match(FA_B, "aa").success());
        assertFalse(Matcher.match(FA_B, "b").success());

        assertTrue(Matcher.match(A_FB, "ab").success());
        assertTrue(Matcher.match(A_FB, "abb").success());
        assertFalse(Matcher.match(A_FB, "a").success());
        assertFalse(Matcher.match(A_FB, "ac").success());
        assertFalse(Matcher.match(FA_B, "b").success());
    }

    public void testEmpty() {
        assertFalse(Matcher.match(FA, "").success());
        assertFalse(Matcher.match(FA_B, "").success());
        assertFalse(Matcher.match(A_FB, "").success());
    }
}
