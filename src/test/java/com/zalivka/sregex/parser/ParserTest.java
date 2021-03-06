package com.zalivka.sregex.parser;

import com.zalivka.sregex.ExpressionException;
import com.zalivka.sregex.matcher.*;
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

    private static final Regex CTF =
        new Sequence(Regex.E, new CharRange('c', 'f', true));
    private static final Regex CTC =
        new Sequence(Regex.E, new CharRange('c', 'c', true));
    private static final Regex CNF =
        new Sequence(Regex.E, new CharRange('c', 'f', false));
    private static final Regex CNC =
        new Sequence(Regex.E, new CharRange('c', 'c', false));

    private static final Regex ATCR =
        new Sequence(
            Regex.E,
            new Repetition(new CharRange('a', 'c', true)));
    private static final Regex ATCOXTZ =
        new Alternative(
            new Sequence(Regex.E, new CharRange('a', 'c', true)),
            new Sequence(Regex.E, new CharRange('x', 'z', true)));
    private static final Regex ATCGD =
        new Sequence(
            new Sequence(
                Regex.E,
                new Group(
                    new Sequence(Regex.E, new CharRange('a', 'c', true)))),
            new Char('d'));

    private static final Regex A23 =
        new Sequence(
            Regex.E,
            new Quantified(2, 3, new Char('a')));
    private static final Regex A2 =
        new Sequence(
            Regex.E,
            new Quantified(2, Quantified.UNBOUNDED, new Char('a')));
    private static final Regex A03 =
        new Sequence(
            Regex.E,
            new Quantified(0, 3, new Char('a')));
    private static final Regex A23OB45 =
        new Alternative(
            new Sequence(
                Regex.E,
                new Quantified(2, 3, new Char('a'))),
            new Sequence(
                Regex.E,
                new Quantified(4, 5, new Char('b'))));
    private static final Regex AB23 =
        new Sequence(
            new Sequence(Regex.E, new Char('a')),
            new Quantified(2, 3, new Char('b')));

    public void testSimple() throws ExpressionException {
        assertEquals(Regex.E, Parser.parse(""));
        assertEquals(A, Parser.parse("a"));
    }

    public void testBinaries() throws ExpressionException {
        assertEquals(AB, Parser.parse("ab"));
        assertEquals(ABC, Parser.parse("abc"));

        assertEquals(AOB, Parser.parse("a|b"));
        assertEquals(AOBOC, Parser.parse("a|b|c"));
    }

    public void testUnaries() throws ExpressionException {
        assertEquals(AR, Parser.parse("a*"));
        assertEquals(AF, Parser.parse("a+"));
        assertEquals(AO, Parser.parse("a?"));
    }

    public void testSimpleComposition() throws ExpressionException {
        assertEquals(ABR, Parser.parse("ab*"));
        assertEquals(ARB, Parser.parse("a*b"));
        assertEquals(ABRC, Parser.parse("ab*c"));

        assertEquals(AROB, Parser.parse("a*|b"));
        assertEquals(AOBR, Parser.parse("a|b*"));
        assertEquals(AOBROC, Parser.parse("a|b*|c"));
    }

    public void testRepeatedComposition() throws ExpressionException {
        assertEquals(ARBOCD, Parser.parse("a*b|cd"));
        assertEquals(ABOCDR, Parser.parse("ab|cd*"));

        assertEquals(ABOCDOEF, Parser.parse("ab|cd|ef"));
        assertEquals(ABROCRDOEFR, Parser.parse("ab*|c*d|ef*"));
    }

    public void testGroups() throws ExpressionException {
        assertEquals(ABCGE, Parser.parse("a(bc)e"));
        assertEquals(ABOCGE, Parser.parse("a(b|c)e"));
        assertEquals(ABOCODE, Parser.parse("a(b|c|d)e"));

        assertEquals(AOBGCODG, Parser.parse("(a|b)(c|d)"));
        assertEquals(AOBRCODP, Parser.parse("(a|b)*(c|d)?"));
    }

    public void testSimpleRanges() throws ExpressionException {
        assertEquals(CTF, Parser.parse("[c-f]"));
        assertEquals(CTC, Parser.parse("[c-c]"));
        assertEquals(CNF, Parser.parse("[^c-f]"));
        assertEquals(CNC, Parser.parse("[^c-c]"));
    }

    public void testRangesCover() throws ExpressionException {
        try {
            Parser.parse("[d-c]");
            fail("Empty range passed");
        } catch (ExpressionException e) {}
        try {
            Parser.parse("[e-c]");
            fail("Empty range passed");
        } catch (ExpressionException e) {}
        try {
            Parser.parse("[^d-c]");
            fail("Unbound range passed");
        } catch (ExpressionException e) {}
        try {
            Parser.parse("[^e-c]");
            fail("Unbound range passed");
        } catch (ExpressionException e) {}
    }

    public void testRangesComposition() throws ExpressionException {
        assertEquals(ATCR, Parser.parse("[a-c]*"));
        assertEquals(ATCOXTZ, Parser.parse("[a-c]|[x-z]"));
        assertEquals(ATCGD, Parser.parse("([a-c])d"));
    }

    public void testQuantity() throws ExpressionException {
        assertEquals(A23, Parser.parse("a{2,3}"));
        assertEquals(A2, Parser.parse("a{2,}"));
        assertEquals(A03, Parser.parse("a{,3}"));

        assertEquals(A23OB45, Parser.parse("a{2,3}|b{4,5}"));
        assertEquals(AB23, Parser.parse("ab{2,3}"));
    }
}
