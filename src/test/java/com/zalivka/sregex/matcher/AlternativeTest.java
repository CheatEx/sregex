package com.zalivka.sregex.matcher;

import junit.framework.TestCase;

public class AlternativeTest extends TestCase {
    private static final Alternative A_OR_B = new Alternative(new Char('a'), new Char('b'));
    private static final Alternative A_OR_B_OR_C =
        new Alternative(new Char('a'), new Alternative(new Char('b'), new Char('c')));

    public void testAlternative() {
        assertTrue(Matcher.match(A_OR_B, "a").success());
        assertTrue(Matcher.match(A_OR_B, "b").success());
        assertFalse(Matcher.match(A_OR_B, "c").success());
        assertFalse(Matcher.match(A_OR_B, "ab").success());
    }

    public void testNestedAlternative() {
        assertTrue(Matcher.match(A_OR_B_OR_C, "a").success());
        assertTrue(Matcher.match(A_OR_B_OR_C, "b").success());
        assertTrue(Matcher.match(A_OR_B_OR_C, "c").success());
        assertFalse(Matcher.match(A_OR_B, "ab").success());
        assertFalse(Matcher.match(A_OR_B, "d").success());
        assertFalse(Matcher.match(A_OR_B, "da").success());
    }

    public void testEmpty() {
        assertFalse(Matcher.match(A_OR_B, "").success());
        assertFalse(Matcher.match(A_OR_B_OR_C, "").success());
    }
}
