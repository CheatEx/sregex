package com.zalivka.sregex;

import com.zalivka.sregex.matcher.AlternativeTest;
import com.zalivka.sregex.matcher.CharTest;
import com.zalivka.sregex.matcher.RepetitionTest;
import com.zalivka.sregex.matcher.SequenceTest;
import junit.framework.Test;
import junit.framework.TestSuite;

public class SregexSuite extends TestSuite {
    public static Test suite() {
        TestSuite s = new TestSuite();
        s.addTestSuite(AlternativeTest.class);
        s.addTestSuite(CharTest.class);
        s.addTestSuite(RepetitionTest.class);
        s.addTestSuite(SequenceTest.class);
//        s.addTestSuite(BasicTest.class);
        return s;
    }
}
