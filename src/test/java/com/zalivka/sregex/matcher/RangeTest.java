package com.zalivka.sregex.matcher;

import junit.framework.TestCase;

public class RangeTest extends TestCase {
    public void testPositive() {
        assertTrue(Matcher.match(new CharRange('d', 'f', true), "d").success());
        assertTrue(Matcher.match(new CharRange('d', 'f', true), "e").success());
        assertTrue(Matcher.match(new CharRange('d', 'f', true), "f").success());

        assertFalse(Matcher.match(new CharRange('d', 'f', true), "a").success());
        assertFalse(Matcher.match(new CharRange('d', 'f', true), "z").success());
    }

    public void testNegative() {
        assertTrue(Matcher.match(new CharRange('d', 'f', false), "a").success());
        assertTrue(Matcher.match(new CharRange('d', 'f', false), "c").success());
        assertTrue(Matcher.match(new CharRange('d', 'f', false), "g").success());

        assertFalse(Matcher.match(new CharRange('d', 'f', false), "d").success());
        assertFalse(Matcher.match(new CharRange('d', 'f', false), "e").success());
        assertFalse(Matcher.match(new CharRange('d', 'f', false), "f").success());
    }
}
