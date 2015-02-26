package com.zalivka.sregex;

import junit.framework.TestCase;

import java.util.Arrays;

public class BasicTest extends TestCase {
    public void testEmpty() throws ExpressionException {
        assertMatches("", "");
        assertNotMatches("a", "");
        assertNotMatches("a|b", "");
        assertNotMatches("ab", "");
    }

    public void testSimpleSequences() throws ExpressionException {
        assertMatches("a", "a");
        assertNotMatches("a", "b");
        assertNotMatches("a", "ab");

        assertMatches("ab", "ab");
        assertNotMatches("ab", "a");
        assertNotMatches("ab", "b");
        assertNotMatches("ab", "abc");

        assertMatches("abc", "abc");
        assertNotMatches("abc", "a");
        assertNotMatches("abc", "ab");
        assertNotMatches("abc", "abd");
        assertNotMatches("abc", "abcd");
    }

    public void testSimpleAlternation() throws ExpressionException {
        assertMatches("a|b", "a");
        assertMatches("a|b", "b");
        assertNotMatches("a|b", "c");

        assertMatches("a|b|c", "a");
        assertMatches("a|b|c", "b");
        assertMatches("a|b|c", "c");
        assertNotMatches("a|b|c", "d");
    }

    public void testExample() throws ExpressionException {
        assertMatches("((abc)*|(abcd))(d|e)", "abcabcabcd",
            "abcabcabc", "abc", null, "d");
        assertMatches("((abc)*|(abcd))(d|e)", "abcabcabce",
            "abcabcabc", "abc", null, "e");
        assertMatches("((abc)*|(abcd))(d|e)", "abcde",
            "abcd", null, "abcd", "e");

        assertNotMatches("((abc)*|(abcd))(d|e)", "abcabcabcdd");
        assertNotMatches("((abc)*|(abcd))(d|e)", "abcabcabcf");
        assertNotMatches("((abc)*|(abcd))(d|e)", "abcabcdabcd");
    }

    private void assertNotMatches(String pattern, String str) throws ExpressionException {
        MatchResult res = Sregex.match(pattern, str);
        assertFalse(res.success());
    }

    private void assertMatches(String pattern, String str, String... groups) throws ExpressionException {
        MatchResult res = Sregex.match(pattern, str);
        assertTrue(res.success());
        if (groups != null) {
            assertEquals("Unexpected number of groups [exp="+Arrays.toString(groups)+", res="+res.groups()+"]",
                groups.length, res.groups().size());
            for (int i=0; i<groups.length; i++) {
                assertEquals("Mismatch in group:"+i, groups[i], res.groups().get(i));
            }
        } else
            assertTrue(res.groups().isEmpty());
    }
}
