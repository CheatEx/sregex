package com.zalivka.sregex.matcher;

public final class Matcher {
    public static boolean match(Regex re, String str) {
        if (str.isEmpty())
            return re.empty;

        // TODO performs full-string match. Do we need substring?
        boolean res = re.shift(str.charAt(0), true);
        for (int i = 1; i < str.length(); i++) {
            if (res)
                break;

            char next = str.charAt(i);
            res = re.shift(next, false);
        }

        re.reset();

        return res;
    }
}
