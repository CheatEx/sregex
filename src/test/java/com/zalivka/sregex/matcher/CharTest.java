package com.zalivka.sregex.matcher;

import junit.framework.TestCase;

public class CharTest extends TestCase {
    public void testChar() {
        assertTrue(Matcher.match(new Char('a'), "a"));
        assertFalse(Matcher.match(new Char('a'), "b"));

        assertTrue(Matcher.match(new Char('a'), "abc"));
    }
}
