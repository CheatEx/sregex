package com.zalivka.sregex.matcher;

import junit.framework.TestCase;

public class OptionalTest extends TestCase {
    private static final Regex OA = new Optional(new Char('a'));
    private static final Regex OA_B = new Sequence(new Optional(new Char('a')), new Char('b'));
    private static final Regex A_OB = new Sequence(new Char('a'), new Optional(new Char('b')));

    public void testOptional() {
        assertTrue(Matcher.match(OA, "a").success());
        assertFalse(Matcher.match(OA, "aa").success());
        assertFalse(Matcher.match(OA, "b").success());
    }

    public void testOptionalInSequence() {
        assertTrue(Matcher.match(OA_B, "ab").success());
        assertTrue(Matcher.match(OA_B, "b").success());
        assertFalse(Matcher.match(OA_B, "a").success());

        assertTrue(Matcher.match(A_OB, "ab").success());
        assertTrue(Matcher.match(A_OB, "a").success());
        assertFalse(Matcher.match(A_OB, "ac").success());
        assertFalse(Matcher.match(A_OB, "cb").success());

        assertFalse(Matcher.match(A_OB, "c").success());
        assertFalse(Matcher.match(A_OB, "cc").success());
    }

    public void testEmpty() {
        assertTrue(Matcher.match(OA, "").success());
        assertFalse(Matcher.match(OA_B, "").success());
        assertFalse(Matcher.match(OA_B, "").success());
    }
}
