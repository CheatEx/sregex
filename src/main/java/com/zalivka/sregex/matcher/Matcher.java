package com.zalivka.sregex.matcher;

public final class Matcher {
    /**
     * Performs full string matching.
     * E.g. Repetition(Char('a')) do match "aaa" but doesn't match "baaaa".
     * @return Whether the string match the given pattern.
     */
    public static boolean match(Regex re, String str) {
        if (str.isEmpty())
            return re.empty;

        boolean res = re.shift(str.charAt(0), true);
        for (int i = 1; i < str.length(); i++)
            res = re.shift(str.charAt(i), false);

        re.reset();

        return res;
    }
}
