# sregex

A simple regex library for Java.

The implementation based on the Carl Friedrich Bolz article [An Efficient and Elegant Regular Expression Matcher in Python](http://morepypy.blogspot.ru/2010/05/efficient-and-elegant-regular.html).

## Usage

The simplest case.

```java
String pattern = "pet((cat)|(dog))";
String string = "petdog";
MatchResult result = Sregex.match(pattern, string);

if (result.success()) {
    String pet = result.groups().get(0);
    System.out.println("Parsed pet: "+pet);
}
```

Pattern could be compiled and re-used across few strings.

```java
Pattern pattern = Sregex.compile("pet((cat)|(dog))");

for (String s : Arrays.asList("petdog", "petcat", "petnaix")) {
    MatchResult result = Sregex.match(pattern, s);
    if (result.success()) {
        String pet = result.groups().get(0);
        System.out.println("Parsed pet: "+pet);
    } else
        System.out.println("Unknown creature.");
}
```

Various shortcut methods are also available, see JavaDoc for the class `com.zalivka.sregex.Sregex` for more details.

## Limitations

Performs full string match. It means that pattern 'abc*' matches strings 'ab' and 'abccc', but doesn't match 'zab' and 'abcd'. See also the 'Supported syntax' section below.

Inside the supported syntax the following limitations are known:

1. Quantification (the `{x,y}` syntax) supports only single digit numbers. I.e `{2,3}` is correct, while `{11,12}` would cause an exception in pattern compilation.

1. Character ranges are compiled only if range isn't empty and isn't unbounded. Precisely lower bound should be lexicographically less or equal than the upper bound. Violating this limitation will cause an exception in pattern compilation.

1. Groups aren't supported under quantifications. For example the following expression isn't supported `((a|b){4,5})c`. Beware that this kind of error isn't detected in compile time. Putting groups under quantification would lead to unpredictable structure of `MatchResult.groups()` although the correctness of the `MatchResult.success` is guaranteed even in this case.

1. In case of alternative matching groups all matched alternatives are reported. For example given expression `(a)|(ab)|(abc)` and string 'abc' the result would be ("a", "ab", "abc").

## Supported syntax

### Overview

* Alternatives. Example: `a|b`
* Repetition. Example: `a*`
* Optional. Example: `b?`
* One ore more. Example: `c+`
* Groups. Example: `(ab)|(cd)`
* Character ranges. Example: `[a-c]`, `[^x-z]`
* Arbitrary quantification. Example `a{3,4}`, `(a|b){,5}`, `(qwe){2,}`

### Specification

```
<regex> ::=
    <term> '|' <regex> |
    <term>

<term> ::=
    <factor>*

<factor> ::=
    <base> |
    <base> <quantifier>*

<base> ::=
    <char> |
    <range> |
    '(' <regex> ')'

<range> ::=
    '[' '^'? <char> '-' <char> ']'

<quantifier> ::=
    '*' | '?' | '+' |
    <quantity>

<quantity> ::=
    '{' <number>? ',' <number>? '}'

<char> ::=
    '0'...'Z'

<number> ::=
    '0'...'9'
```

## Development notes

Quick export: `$ zip -r sregex.zip sregex -x \*.iml -x *.git* -x *.idea*`