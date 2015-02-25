package com.zalivka.sregex.matcher;

import junit.framework.TestCase;

public class QuantifiedTest extends TestCase {
    private static final Regex A23 = new Quantified(new Char('a'), 2, 3);
    private static final Regex AU3 = new Quantified(new Char('a'), 0, 3);
    private static final Regex A2U = new Quantified(new Char('a'), 2, Quantified.UNBOUNDED);

    public void testSimple() {
        assertTrue(Matcher.match(A23, "aa").success());
        assertTrue(Matcher.match(A23, "aaa").success());
        assertFalse(Matcher.match(A23, "").success());
        assertFalse(Matcher.match(A23, "a").success());
        assertFalse(Matcher.match(A23, "aaaa").success());
    }

    public void testUnbounded() {
        assertTrue(Matcher.match(AU3, "").success());
        assertTrue(Matcher.match(AU3, "a").success());
        assertTrue(Matcher.match(AU3, "aa").success());
        assertTrue(Matcher.match(AU3, "aaa").success());
        assertFalse(Matcher.match(AU3, "aaaa").success());
        assertFalse(Matcher.match(AU3, "aaaaa").success());

        assertFalse(Matcher.match(A2U, "").success());
        assertFalse(Matcher.match(A2U, "a").success());
        assertTrue(Matcher.match(A2U, "aa").success());
        assertTrue(Matcher.match(A2U, "aaa").success());
        assertTrue(Matcher.match(A2U, "aaaaa").success());
    }
}
