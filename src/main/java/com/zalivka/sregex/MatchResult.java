package com.zalivka.sregex;

import java.util.List;

public interface MatchResult {
    boolean success();
    List<String> groups();
}
