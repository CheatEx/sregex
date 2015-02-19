package com.zalivka.sregex.matcher;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class MatcherTest extends TestCase {
    public void testChar() {
        assertTrue(Matcher.match(new Char('a'), "a"));
        assertFalse(Matcher.match(new Char('a'), "b"));
    }

    public static Test suite() {
        return new TestSuite( MatcherTest.class );
    }
}
