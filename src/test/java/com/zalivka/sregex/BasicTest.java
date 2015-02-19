package com.zalivka.sregex;

import junit.framework.TestCase;

public class BasicTest extends TestCase {
    public void testEmpty() {
        assertEquals("", Sregex.match("", ""));
        assertNull(Sregex.match("a", ""));
    }
}
