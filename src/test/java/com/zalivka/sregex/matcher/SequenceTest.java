package com.zalivka.sregex.matcher;

import junit.framework.TestCase;

public class SequenceTest extends TestCase {
    private static final Sequence AB_SEQ = new Sequence(new Char('a'), new Char('b'));
    private static final Sequence ABC_SEQ = new Sequence(new Char('a'), new Sequence(new Char('b'), new Char('c')));

    public void testSequence() {
        assertTrue(Matcher.match(AB_SEQ, "ab"));
        assertTrue(Matcher.match(AB_SEQ, "abc"));

        assertFalse(Matcher.match(AB_SEQ, "ac"));
        assertFalse(Matcher.match(AB_SEQ, "cb"));
        assertFalse(Matcher.match(AB_SEQ, "a"));

        assertTrue(Matcher.match(ABC_SEQ, "abc"));
        assertTrue(Matcher.match(ABC_SEQ, "abcd"));

        assertFalse(Matcher.match(ABC_SEQ, "ab"));
    }

    public void testEmpty() {
        assertFalse(Matcher.match(AB_SEQ, ""));
    }
}
