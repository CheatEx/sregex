package com.zalivka.sregex.matcher;

import junit.framework.TestCase;

public class RepetitionTest extends TestCase {
    private static final Repetition AA = new Repetition(new Char('a'));

    public void testAlternative() {
        assertTrue(Matcher.match(AA, "a"));
        assertTrue(Matcher.match(AA, "aa"));
        assertTrue(Matcher.match(AA, "aaa"));
        assertFalse(Matcher.match(AA, "ab"));
        assertFalse(Matcher.match(AA, "ba"));
    }

    public void testEmpty() {
        assertTrue(Matcher.match(AA, ""));
    }
}
