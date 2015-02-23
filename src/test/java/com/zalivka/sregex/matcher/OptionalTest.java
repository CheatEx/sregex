package com.zalivka.sregex.matcher;

import junit.framework.TestCase;

public class OptionalTest extends TestCase {
    private static final Regex OA = new Optional(new Char('a'));
    private static final Regex OA_B = new Sequence(new Optional(new Char('a')), new Char('b'));
    private static final Regex A_OB = new Sequence(new Char('a'), new Optional(new Char('b')));

    public void testOptional() {
        assertTrue(Matcher.match(OA, "a").succes());
        assertFalse(Matcher.match(OA, "aa").succes());
        assertFalse(Matcher.match(OA, "b").succes());
    }

    public void testOptionalInSequence() {
        assertTrue(Matcher.match(OA_B, "ab").succes());
        assertTrue(Matcher.match(OA_B, "b").succes());
        assertFalse(Matcher.match(OA_B, "a").succes());

        assertTrue(Matcher.match(A_OB, "ab").succes());
        assertTrue(Matcher.match(A_OB, "a").succes());
        assertFalse(Matcher.match(A_OB, "ac").succes());
        assertFalse(Matcher.match(A_OB, "cb").succes());

        assertFalse(Matcher.match(A_OB, "c").succes());
        assertFalse(Matcher.match(A_OB, "cc").succes());
    }

    public void testEmpty() {
        assertTrue(Matcher.match(OA, "").succes());
        assertFalse(Matcher.match(OA_B, "").succes());
        assertFalse(Matcher.match(OA_B, "").succes());
    }
}
