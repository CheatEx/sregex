package com.zalivka.sregex;

import java.util.List;

public interface MatchResult {
    boolean succes();
    List<String> groups();
}
