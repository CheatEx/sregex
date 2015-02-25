package com.zalivka.sregex.matcher;

import junit.framework.TestCase;

public class SequenceTest extends TestCase {
    private static final Sequence AB_SEQ = new Sequence(new Char('a'), new Char('b'));
    private static final Sequence ABC_SEQ = new Sequence(new Char('a'), new Sequence(new Char('b'), new Char('c')));

    public void testSequence() {
        assertTrue(Matcher.match(AB_SEQ, "ab").success());
        assertFalse(Matcher.match(AB_SEQ, "abc").success());
        assertFalse(Matcher.match(AB_SEQ, "ac").success());
        assertFalse(Matcher.match(AB_SEQ, "cb").success());
        assertFalse(Matcher.match(AB_SEQ, "a").success());
    }

    public void testNestedSequence() {
        assertTrue(Matcher.match(ABC_SEQ, "abc").success());
        assertFalse(Matcher.match(ABC_SEQ, "abcd").success());
        assertFalse(Matcher.match(ABC_SEQ, "ab").success());
        assertFalse(Matcher.match(ABC_SEQ, "cd").success());
    }

    public void testEmpty() {
        assertFalse(Matcher.match(AB_SEQ, "").success());
        assertFalse(Matcher.match(ABC_SEQ, "").success());
    }
}
