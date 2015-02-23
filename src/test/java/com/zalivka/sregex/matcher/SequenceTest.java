package com.zalivka.sregex.matcher;

import junit.framework.TestCase;

public class SequenceTest extends TestCase {
    private static final Sequence AB_SEQ = new Sequence(new Char('a'), new Char('b'));
    private static final Sequence ABC_SEQ = new Sequence(new Char('a'), new Sequence(new Char('b'), new Char('c')));

    public void testSequence() {
        assertTrue(Matcher.match(AB_SEQ, "ab").succes());
        assertFalse(Matcher.match(AB_SEQ, "abc").succes());
        assertFalse(Matcher.match(AB_SEQ, "ac").succes());
        assertFalse(Matcher.match(AB_SEQ, "cb").succes());
        assertFalse(Matcher.match(AB_SEQ, "a").succes());
    }

    public void testNestedSequence() {
        assertTrue(Matcher.match(ABC_SEQ, "abc").succes());
        assertFalse(Matcher.match(ABC_SEQ, "abcd").succes());
        assertFalse(Matcher.match(ABC_SEQ, "ab").succes());
        assertFalse(Matcher.match(ABC_SEQ, "cd").succes());
    }

    public void testEmpty() {
        assertFalse(Matcher.match(AB_SEQ, "").succes());
        assertFalse(Matcher.match(ABC_SEQ, "").succes());
    }
}
