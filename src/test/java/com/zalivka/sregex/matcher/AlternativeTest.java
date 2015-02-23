package com.zalivka.sregex.matcher;

import junit.framework.TestCase;

public class AlternativeTest extends TestCase {
    private static final Alternative A_OR_B = new Alternative(new Char('a'), new Char('b'));
    private static final Alternative A_OR_B_OR_C =
        new Alternative(new Char('a'), new Alternative(new Char('b'), new Char('c')));

    public void testAlternative() {
        assertTrue(Matcher.match(A_OR_B, "a").succes());
        assertTrue(Matcher.match(A_OR_B, "b").succes());
        assertFalse(Matcher.match(A_OR_B, "c").succes());
        assertFalse(Matcher.match(A_OR_B, "ab").succes());
    }

    public void testNestedAlternative() {
        assertTrue(Matcher.match(A_OR_B_OR_C, "a").succes());
        assertTrue(Matcher.match(A_OR_B_OR_C, "b").succes());
        assertTrue(Matcher.match(A_OR_B_OR_C, "c").succes());
        assertFalse(Matcher.match(A_OR_B, "ab").succes());
        assertFalse(Matcher.match(A_OR_B, "d").succes());
        assertFalse(Matcher.match(A_OR_B, "da").succes());
    }

    public void testEmpty() {
        assertFalse(Matcher.match(A_OR_B, "").succes());
        assertFalse(Matcher.match(A_OR_B_OR_C, "").succes());
    }
}
