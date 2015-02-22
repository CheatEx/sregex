package com.zalivka.sregex;

import com.zalivka.sregex.matcher.*;
import com.zalivka.sregex.parser.Parser;
import junit.framework.TestCase;

public class ParserTest extends TestCase {
    public static final Regex A = new Sequence(new Char('a'), Regex.E);
    public static final Regex AB =
        new Sequence(new Char('a'), new Sequence(new Char('b'), Regex.E));
    public static final Regex ABC =
        new Sequence(
            new Char('a'),
            new Sequence(
                new Char('b'),
                new Sequence(new Char('c'), Regex.E)));

    public static final Regex AOB =
        new Alternative(new Char('a'), new Sequence(new Char('b'), Regex.E));
    public static final Regex AOBOC =
        new Alternative(
            new Char('a'),
            new Alternative(
                new Char('b'),
                new Sequence(new Char('c'), Regex.E)));

    private static final Regex AR = new Sequence(new Repetition(new Char('a')), Regex.E);
    private static final Regex AF = new Sequence(new Few(new Char('a')), Regex.E);
    private static final Regex AO = new Sequence(new Optional(new Char('a')), Regex.E);

    private static final Regex ABR =
        new Sequence(
            new Char('a'),
            new Sequence(
                new Repetition(new Char('b')),
                Regex.E));
    private static final Regex ARB =
        new Sequence(
            new Repetition(new Char('a')),
            new Sequence(new Char('b'), Regex.E));
    private static final Regex ABRC =
        new Sequence(
            new Char('a'),
            new Sequence(
                new Repetition(new Char('b')),
                new Sequence(new Char('c'), Regex.E)));

    public void testSimple() {
        assertEquals(Regex.E, Parser.parse(""));
        assertEquals(A, Parser.parse("a"));
    }

    public void testBinaries() {
        assertEquals(AB, Parser.parse("ab"));
        assertEquals(ABC, Parser.parse("abc"));

        assertEquals(AOB, Parser.parse("a|b"));
        assertEquals(AOBOC, Parser.parse("a|b|c"));
    }

    public void testUnaries() {
        assertEquals(AR, Parser.parse("a*"));
        assertEquals(AF, Parser.parse("a+"));
        assertEquals(AO, Parser.parse("a?"));
    }

    public void testComposition() {
        assertEquals(ABR, Parser.parse("ab*"));
        assertEquals(ARB, Parser.parse("a*b"));
        assertEquals(ABRC, Parser.parse("ab*c"));
    }
}
