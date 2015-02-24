package com.zalivka.sregex;

import com.zalivka.sregex.matcher.*;
import com.zalivka.sregex.parser.Parser;
import junit.framework.TestCase;

public class ParserTest extends TestCase {
    public static final Regex A = new Sequence(Regex.E, new Char('a'));
    public static final Regex AB =
        new Sequence(new Sequence(Regex.E, new Char('a')), new Char('b'));
    public static final Regex ABC =
        new Sequence(
            new Sequence(
                new Sequence(Regex.E, new Char('a')),
                new Char('b')),
            new Char('c'));

    public static final Regex AOB =
        new Alternative(
            new Sequence(Regex.E, new Char('a')),
            new Sequence(Regex.E, new Char('b')));
    public static final Regex AOBOC =
        new Alternative(
            new Alternative(
                new Sequence(Regex.E, new Char('a')),
                new Sequence(Regex.E, new Char('b'))),
            new Sequence(Regex.E, new Char('c')));

    private static final Regex AR = new Sequence(Regex.E, new Repetition(new Char('a')));
    private static final Regex AF = new Sequence(Regex.E, new Few(new Char('a')));
    private static final Regex AO = new Sequence(Regex.E, new Optional(new Char('a')));

    private static final Regex ABR =
        new Sequence(
            new Sequence(Regex.E, new Char('a')),
            new Repetition(new Char('b')));
    private static final Regex ARB =
        new Sequence(
            new Sequence(Regex.E, new Repetition(new Char('a'))),
            new Char('b'));
    private static final Regex ABRC =
        new Sequence(
            new Sequence(
                new Sequence(Regex.E, new Char('a')),
                new Repetition(new Char('b'))),
            new Char('c'));
    private static final Regex AOBROC =
        new Alternative(
            new Alternative(
                new Sequence(Regex.E, new Char('a')),
                new Sequence(Regex.E, new Repetition(new Char('b')))),
        new Sequence(Regex.E, new Char('c')));
    private static final Regex AOBR =
        new Alternative(
            new Sequence(Regex.E, new Char('a')),
            new Sequence(Regex.E, new Repetition(new Char('b'))));
    private static final Regex AROB =
        new Alternative(
            new Sequence(
                Regex.E,
                new Repetition(new Char('a'))),
            new Sequence(Regex.E, new Char('b')));

    private static final Regex ARBOCD =
        new Alternative(
            new Sequence(
                new Sequence(Regex.E,
                    new Repetition(new Char('a'))), new Char('b')),
            new Sequence(
                new Sequence(Regex.E, new Char('c')),
                new Char('d')));
    private static final Regex ABOCDR =
        new Alternative(
            new Sequence(
                new Sequence(Regex.E, new Char('a')),
                new Char('b')),
            new Sequence(
                new Sequence(Regex.E, new Char('c')),
                new Repetition(new Char('d'))));

    private static final Regex ABOCDOEF =
        new Alternative(
            new Alternative(
                new Sequence(
                    new Sequence(Regex.E, new Char('a')), new Char('b')),
                new Sequence(
                    new Sequence(Regex.E, new Char('c')),
                    new Char('d'))),
            new Sequence(
                new Sequence(Regex.E, new Char('e')), new Char('f')));
    private static final Regex ABROCRDOEFR =
        new Alternative(
            new Alternative(
                new Sequence(
                    new Sequence(Regex.E, new Char('a')),
                    new Repetition(new Char('b'))),
                new Sequence(
                    new Sequence(Regex.E, new Repetition(new Char('c'))),
                    new Char('d'))),
            new Sequence(
                new Sequence(Regex.E, new Char('e')),
                new Repetition(new Char('f'))));

    private static final Regex ABCGE =
        new Sequence(
            new Sequence(
                new Sequence(Regex.E, new Char('a')),
                new Group(
                    new Sequence(
                        new Sequence(Regex.E, new Char('b')),
                        new Char('c')))),
            new Char('e'));
    private static final Regex ABOCGE =
        new Sequence(
            new Sequence(
                new Sequence(Regex.E, new Char('a')),
                new Group(
                    new Alternative(
                        new Sequence(Regex.E, new Char('b')),
                        new Sequence(Regex.E, new Char('c'))))),
            new Char('e'));
    private static final Regex ABOCODE =
        new Sequence(
            new Sequence(
                new Sequence(Regex.E, new Char('a')),
                new Group(
                    new Alternative(
                        new Alternative(
                            new Sequence(Regex.E, new Char('b')),
                            new Sequence(Regex.E, new Char('c'))),
                        new Sequence(Regex.E, new Char('d'))))),
            new Char('e'));
    private static final Regex AOBGCODG =
        new Sequence(
            new Sequence(Regex.E,
                new Group(
                    new Alternative(
                        new Sequence(Regex.E, new Char('a')),
                        new Sequence(Regex.E, new Char('b'))))),
            new Group(
                new Alternative(
                    new Sequence(Regex.E, new Char('c')),
                    new Sequence(Regex.E, new Char('d')))));
    private static final Regex AOBRCODP =
        new Sequence(
            new Sequence(Regex.E,
                new Repetition(
                    new Group(new Alternative(
                        new Sequence(Regex.E, new Char('a')),
                        new Sequence(Regex.E, new Char('b')))))),
            new Optional(
                new Group(
                    new Alternative(
                        new Sequence(Regex.E, new Char('c')),
                        new Sequence(Regex.E, new Char('d'))))));

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

    public void testSimpleComposition() {
        assertEquals(ABR, Parser.parse("ab*"));
        assertEquals(ARB, Parser.parse("a*b"));
        assertEquals(ABRC, Parser.parse("ab*c"));

        assertEquals(AROB, Parser.parse("a*|b"));
        assertEquals(AOBR, Parser.parse("a|b*"));
        assertEquals(AOBROC, Parser.parse("a|b*|c"));
    }

    public void testRepeatedComposition() {
        assertEquals(ARBOCD, Parser.parse("a*b|cd"));
        assertEquals(ABOCDR, Parser.parse("ab|cd*"));

        assertEquals(ABOCDOEF, Parser.parse("ab|cd|ef"));
        assertEquals(ABROCRDOEFR, Parser.parse("ab*|c*d|ef*"));
    }

    public void testGroups() {
        assertEquals(ABCGE, Parser.parse("a(bc)e"));
        assertEquals(ABOCGE, Parser.parse("a(b|c)e"));
        assertEquals(ABOCODE, Parser.parse("a(b|c|d)e"));

        assertEquals(AOBGCODG, Parser.parse("(a|b)(c|d)"));
        assertEquals(AOBRCODP, Parser.parse("(a|b)*(c|d)?"));
    }
}
