package com.zalivka.sregex.matcher;

import junit.framework.TestCase;

public class OptionalTest extends TestCase {
    private static final Regex OA = new Optional(new Char('a'));
    private static final Regex OA_B = new Sequence(new Optional(new Char('a')), new Char('b'));
    private static final Regex A_OB = new Sequence(new Char('a'), new Optional(new Char('b')));

    public void testOptional() {
        assertTrue(Matcher.match(OA, "a"));
        assertFalse(Matcher.match(OA, "aa"));
        assertFalse(Matcher.match(OA, "b"));
    }

    public void testOptionalInSequence() {
        assertTrue(Matcher.match(OA_B, "ab"));
        assertTrue(Matcher.match(OA_B, "b"));
        assertFalse(Matcher.match(OA_B, "a"));

        assertTrue(Matcher.match(A_OB, "ab"));
        assertTrue(Matcher.match(A_OB, "a"));
        assertFalse(Matcher.match(A_OB, "ac"));
        assertFalse(Matcher.match(A_OB, "cb"));

        assertFalse(Matcher.match(A_OB, "c"));
        assertFalse(Matcher.match(A_OB, "cc"));
    }

    public void testEmpty() {
        assertTrue(Matcher.match(OA, ""));
    }
}
