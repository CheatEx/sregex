package com.zalivka.sregex;

import com.zalivka.sregex.matcher.*;
import com.zalivka.sregex.parser.Parser;
import junit.framework.TestCase;

public class ParserTest extends TestCase {
    public static final Regex A = new Sequence(new Char('a'), new Empty());
    public static final Regex AB =
        new Sequence(new Char('a'), new Sequence(new Char('b'), new Empty()));
    public static final Regex ABC =
        new Sequence(new Char('a'), new Sequence(new Char('b'), new Sequence(new Char('c'), new Empty())));

    public static final Regex AOB =
        new Alternative(new Char('a'), new Sequence(new Char('b'), new Empty()));
    public static final Regex AOBOC =
        new Alternative(new Char('a'), new Alternative(new Char('b'), new Sequence(new Char('c'), new Empty())));

    public void testSimpleBinary() {
        assertEquals(A, Parser.parse("a"));
        assertEquals(AB, Parser.parse("ab"));
        assertEquals(ABC, Parser.parse("abc"));

        assertEquals(AOB, Parser.parse("a|b"));
        assertEquals(AOBOC, Parser.parse("a|b|c"));
    }
}
