package com.zalivka.sregex;

import com.zalivka.sregex.matcher.*;
import junit.framework.Test;
import junit.framework.TestSuite;

public class SregexSuite extends TestSuite {
    public static Test suite() {
        TestSuite s = new TestSuite();
        s.addTestSuite(CharTest.class);
        s.addTestSuite(AlternativeTest.class);
        s.addTestSuite(SequenceTest.class);
        s.addTestSuite(RepetitionTest.class);
        s.addTestSuite(OptionalTest.class);
        s.addTestSuite(FewTest.class);

        s.addTestSuite(ParserTest.class);

        s.addTestSuite(BasicTest.class);

        return s;
    }
}
