package com.zalivka.sregex.matcher;

import junit.framework.TestCase;

public class QuantifiedTest extends TestCase {
    private static final Regex A23 = new Quantified(2, 3, new Char('a'));
    private static final Regex AU3 = new Quantified(0, 3, new Char('a'));
    private static final Regex A2U = new Quantified(2, Quantified.UNBOUNDED, new Char('a'));
    private static final Regex AB23 = new Quantified(2, 3,
        new Sequence(new Char('a'), new Char('b')));
    private static final Regex AOB23 = new Quantified(2, 3,
        new Alternative(new Char('a'), new Char('b')));

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

    public void testSubexpressions() {
        assertTrue(Matcher.match(AB23, "ababab").success());
        assertTrue(Matcher.match(AB23, "abab").success());
        assertFalse(Matcher.match(AB23, "ab").success());

        assertTrue(Matcher.match(AOB23, "aba").success());
        assertTrue(Matcher.match(AOB23, "aa").success());
        assertTrue(Matcher.match(AOB23, "bb").success());
        assertFalse(Matcher.match(AOB23, "abc").success());
    }
}
