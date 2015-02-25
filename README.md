# sregex

A simple regex java library.

The implementation based on the Carl Friedrich Bolz article [An Efficient and Elegant Regular Expression Matcher in Python](http://morepypy.blogspot.ru/2010/05/efficient-and-elegant-regular.html).

## Limitations

Performs full string match. It means that pattern 'abc*' matches strings 'ab' and 'abccc', but doesn't match 'zab' and 'abcd'.

See also the 'Supported syntax' section below.

## Usage

> TODO Code here

## Supported syntax

* Alternatives. Example: `a|b`
* Repetition. Example: `a*`
* Optional. Example: `b?`
* One ore more. Example: `c+`
* Groups. Example: `(ab)|(cd)`
* Character ranges. Example: `[a-c]`, `[^x-z]`
* Arbitrary quantification {n,m}, {,n} {m,}
