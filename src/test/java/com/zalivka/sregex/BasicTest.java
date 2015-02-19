package com.zalivka.sregex;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class BasicTest extends TestCase {
    public void testEmpty() {
        assertEquals("", Sregex.match("", ""));
        assertNull(Sregex.match("a", ""));
    }

    public static Test suite() {
        return new TestSuite( BasicTest.class );
    }
}
