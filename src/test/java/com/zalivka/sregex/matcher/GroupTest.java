package com.zalivka.sregex.matcher;

import com.zalivka.sregex.MatchResult;
import junit.framework.TestCase;

import java.util.Arrays;

public class GroupTest extends TestCase {
    /**
     * ((abc)*|(abcd))(d|e)
     */
    private final Regex ARTICLE_EXAMPLE =
        new Sequence(
            new Group(
                new Alternative(
                    new Repetition(
                        new Group(
                            new Sequence(
                                new Sequence(
                                    new Char('a'),
                                    new Char('b')),
                                new Char('c')))),
                    new Group(
                        new Sequence(
                            new Sequence(
                                new Sequence(
                                    new Char('a'),
                                    new Char('b')),
                                new Char('c')),
                            new Char('d'))))),
            new Group(
                new Alternative(
                    new Char('d'),
                    new Char('e'))));

    public void testSimple() {
        assertMatch(ARTICLE_EXAMPLE, "abcabcabcd", "abcabcabc", "abc", null, "d");
        assertMatch(ARTICLE_EXAMPLE, "abcabcabce", "abcabcabc", "abc", null, "e");

        assertFalse(Matcher.match(ARTICLE_EXAMPLE, "abcabcabcdd").success());
        assertFalse(Matcher.match(ARTICLE_EXAMPLE, "abcabcabcf").success());
        assertFalse(Matcher.match(ARTICLE_EXAMPLE, "abcabcdabcd").success());
    }

    private void assertMatch(Regex r, String str, String... groups) {
        MatchResult res = Matcher.match(r, str);
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
