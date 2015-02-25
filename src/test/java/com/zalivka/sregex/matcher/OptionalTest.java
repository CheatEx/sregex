package com.zalivka.sregex.matcher;

import junit.framework.TestCase;

public class OptionalTest extends TestCase {
    private static final Regex OA = new Optional(new Char('a'));
    private static final Regex OA_B = new Sequence(new Optional(new Char('a')), new Char('b'));
    private static final Regex A_OB = new Sequence(new Char('a'), new Optional(new Char('b')));
    private static final Regex OA_OB = new Sequence(new Optional(new Char('a')), new Optional(new Char('b')));
    private static final Regex OA_OB_OC =
        new Sequence(
            new Optional(new Char('a')),
            new Sequence(
                new Optional(new Char('b')),
                new Optional(new Char('c'))));
    private static final Regex OA_OA = new Sequence(new Optional(new Char('a')), new Optional(new Char('a')));

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

    public void testChains() {
        assertTrue(Matcher.match(OA_OB, "ab").success());
        assertTrue(Matcher.match(OA_OB, "a").success());
        assertTrue(Matcher.match(OA_OB, "b").success());
        assertFalse(Matcher.match(OA_OB, "c").success());
        assertFalse(Matcher.match(OA_OB, "cc").success());
        assertFalse(Matcher.match(OA_OB, "ccc").success());

        assertTrue(Matcher.match(OA_OA, "a").success());
        assertTrue(Matcher.match(OA_OA, "aa").success());
        assertFalse(Matcher.match(OA_OA, "aaa").success());
        assertFalse(Matcher.match(OA_OA, "b").success());
        assertFalse(Matcher.match(OA_OA, "bb").success());
        assertFalse(Matcher.match(OA_OA, "bbb").success());

        assertTrue(Matcher.match(OA_OB_OC, "a").success());
        assertTrue(Matcher.match(OA_OB_OC, "b").success());
        assertTrue(Matcher.match(OA_OB_OC, "c").success());
        assertTrue(Matcher.match(OA_OB_OC, "ab").success());
        assertTrue(Matcher.match(OA_OB_OC, "abc").success());
        assertFalse(Matcher.match(OA_OB_OC, "abcd").success());
    }

    public void testEmpty() {
        assertTrue(Matcher.match(OA, "").success());
        assertTrue(Matcher.match(OA_OB, "").success());
        assertTrue(Matcher.match(OA_OB_OC, "").success());
        assertFalse(Matcher.match(OA_B, "").success());
        assertFalse(Matcher.match(OA_B, "").success());
    }
}
