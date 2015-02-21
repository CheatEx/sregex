package com.zalivka.sregex.matcher;

import junit.framework.TestCase;

public class AlternativeTest extends TestCase {
    private static final Alternative A_OR_B = new Alternative(new Char('a'), new Char('b'));
    private static final Alternative A_OR_B_OR_C =
        new Alternative(new Char('a'), new Alternative(new Char('b'), new Char('c')));

    public void testAlternative() {
        assertTrue(Matcher.match(A_OR_B, "a"));
        assertTrue(Matcher.match(A_OR_B, "b"));
        assertFalse(Matcher.match(A_OR_B, "c"));
        assertFalse(Matcher.match(A_OR_B, "ab"));
    }

    public void testNestedAlternative() {
        assertTrue(Matcher.match(A_OR_B_OR_C, "a"));
        assertTrue(Matcher.match(A_OR_B_OR_C, "b"));
        assertTrue(Matcher.match(A_OR_B_OR_C, "c"));
        assertFalse(Matcher.match(A_OR_B, "ab"));
        assertFalse(Matcher.match(A_OR_B, "d"));
        assertFalse(Matcher.match(A_OR_B, "da"));
    }

    public void testEmpty() {
        assertFalse(Matcher.match(A_OR_B, ""));
        assertFalse(Matcher.match(A_OR_B_OR_C, ""));
    }
}
