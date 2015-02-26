package com.zalivka.sregex;

import junit.framework.TestCase;

import java.util.Arrays;

public class ExamplesTest extends TestCase {
    public void testExample() throws ExpressionException {
        String pattern = "pet((cat)|(dog))";
        String string = "petdog";
        MatchResult result = Sregex.match(pattern, string);

        if (result.success()) {
            String pet = result.groups().get(0);
            System.out.println("Parsed pet: "+pet);
        }
    }

    public void testCompiledExample() throws ExpressionException {
        Pattern pattern = Sregex.compile("pet((cat)|(dog))");

        for (String s : Arrays.asList("petdog", "petcat", "petnaix")) {
            MatchResult result = Sregex.match(pattern, s);
            if (result.success()) {
                String pet = result.groups().get(0);
                System.out.println("Parsed pet: "+pet);
            } else
                System.out.println("Unknown creature.");
        }
    }
}
