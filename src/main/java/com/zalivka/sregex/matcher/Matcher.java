package com.zalivka.sregex.matcher;

import com.zalivka.sregex.MatchResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Matcher {
    /**
     * Performs full string matching.
     * E.g. Repetition(Char('a')) do match "aaa" but doesn't match "baaaa" and "aaaab".
     * @return Whether the string match the given pattern.
     */
    public static MatchResult match(Regex re, CharSequence str) {
        // TODO some nested groups?
        if (str.length() == 0)
            return re.empty ? new Success(Collections.emptyList()) : FAILURE;

        boolean succ = re.shift(str.charAt(0), true);
        for (int i = 1; i < str.length(); i++)
            succ = re.shift(str.charAt(i), false);

        MatchResult result = succ ?
            new Success(traverseGroups(re)) :
            FAILURE;

        re.reset();
        return result;
    }

    private static List<String> traverseGroups(Regex re) {
        List<String> res = new ArrayList<>();
        walk(re, res);
        return res;
    }

    private static void walk(Regex branch, List<String> groups) {
        if (branch instanceof Group) {
            Group g = (Group)branch;
            groups.add(g.match());
            walk(g.re, groups);
        } else if (branch instanceof Unary) {
            Unary u = (Unary)branch;
            walk(u.re, groups);
        } else if (branch instanceof Binary) {
            Binary b = (Binary)branch;
            walk(b.left, groups);
            walk(b.right, groups);
        }
    }

    private static class Success implements MatchResult {
        private final List<String> groups;

        Success(List<String> groups) {
            this.groups = Collections.unmodifiableList(groups);
        }

        @Override public boolean success() {
            return true;
        }

        @Override public List<String> groups() {
            return groups;
        }
    }

    private static final MatchResult FAILURE = new MatchResult() {
        @Override public boolean success() {
            return false;
        }

        @Override public List<String> groups() {
            return Collections.emptyList();
        }
    };
}
