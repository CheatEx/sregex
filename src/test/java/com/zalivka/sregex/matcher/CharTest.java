package com.zalivka.sregex.matcher;

import junit.framework.TestCase;

public class CharTest extends TestCase {
    public void testChar() {
        assertTrue(Matcher.match(new Char('a'), "a").succes());
        assertFalse(Matcher.match(new Char('a'), "b").succes());
        assertFalse(Matcher.match(new Char('a'), "ab").succes());
    }
}
