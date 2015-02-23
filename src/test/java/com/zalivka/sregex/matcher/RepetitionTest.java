package com.zalivka.sregex.matcher;

import junit.framework.TestCase;

public class RepetitionTest extends TestCase {
    private static final Repetition AA = new Repetition(new Char('a'));

    public void testAlternative() {
        assertTrue(Matcher.match(AA, "a").succes());
        assertTrue(Matcher.match(AA, "aa").succes());
        assertTrue(Matcher.match(AA, "aaa").succes());
        assertFalse(Matcher.match(AA, "ab").succes());
        assertFalse(Matcher.match(AA, "ba").succes());
    }

    public void testEmpty() {
        assertTrue(Matcher.match(AA, "").succes());
    }
}
