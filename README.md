# sregex

A simple regex java library.

The implementation based on the Carl Friedrich Bolz article [An Efficient and Elegant Regular Expression Matcher in Python](http://morepypy.blogspot.ru/2010/05/efficient-and-elegant-regular.html).

## Limitations

Performs full string match. It means that pattern 'abc*' matches strings 'ab' and 'abccc', but doesn't match 'zab' and 'abcd'.

See also the 'Supported syntax' section below.

## Usage

> TODO Code here

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
    where the first character should be lexicographically less or equal than the second.

<quantifier> ::=
    '*' | '?' | '+'

<char> ::=
    '1'...'Z'
```