package com.zalivka.sregex.matcher;

import junit.framework.TestCase;

public class RepetitionTest extends TestCase {
    private static final Repetition AA = new Repetition(new Char('a'));

    public void testAlternative() {
        assertTrue(Matcher.match(AA, "a").success());
        assertTrue(Matcher.match(AA, "aa").success());
        assertTrue(Matcher.match(AA, "aaa").success());
        assertFalse(Matcher.match(AA, "ab").success());
        assertFalse(Matcher.match(AA, "ba").success());
    }

    public void testEmpty() {
        assertTrue(Matcher.match(AA, "").success());
    }
}
