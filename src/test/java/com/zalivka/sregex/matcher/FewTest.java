package com.zalivka.sregex.matcher;

import junit.framework.TestCase;

public class FewTest extends TestCase {
    private static final Regex FA = new Few(new Char('a'));
    private static final Regex FA_B = new Sequence(new Few(new Char('a')), new Char('b'));
    private static final Regex A_FB = new Sequence(new Char('a'), new Few(new Char('b')));

    public void testFew() {
        assertTrue(Matcher.match(FA, "a").succes());
        assertTrue(Matcher.match(FA, "aa").succes());
        assertTrue(Matcher.match(FA, "aaa").succes());

        assertFalse(Matcher.match(FA, "b").succes());
        assertFalse(Matcher.match(FA, "bb").succes());
    }

    public void testFewInSequence() {
        assertTrue(Matcher.match(FA_B, "ab").succes());
        assertTrue(Matcher.match(FA_B, "aab").succes());
        assertFalse(Matcher.match(FA_B, "a").succes());
        assertFalse(Matcher.match(FA_B, "aa").succes());
        assertFalse(Matcher.match(FA_B, "b").succes());

        assertTrue(Matcher.match(A_FB, "ab").succes());
        assertTrue(Matcher.match(A_FB, "abb").succes());
        assertFalse(Matcher.match(A_FB, "a").succes());
        assertFalse(Matcher.match(A_FB, "ac").succes());
        assertFalse(Matcher.match(FA_B, "b").succes());
    }

    public void testEmpty() {
        assertFalse(Matcher.match(FA, "").succes());
        assertFalse(Matcher.match(FA_B, "").succes());
        assertFalse(Matcher.match(A_FB, "").succes());
    }
}
