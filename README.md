# sregex

A simple regex java library.

The implementation based on the Carl Friedrich Bolz article [An Efficient and Elegant Regular Expression Matcher in Python](http://morepypy.blogspot.ru/2010/05/efficient-and-elegant-regular.html).

## Limitations

Performs full string match. It means that pattern 'abc*' matches strings 'ab' and 'abccc', but doesn't match 'zab' and 'abcd'.

See also the 'Supported syntax' section below.

## Usage

> TODO Code here

## Supported syntax

* Alternatives
        a|b
* Repetition
        a*
* Optional
        b?
* One ore more
        c+
* Groups
        (ab)|(cd)
* Character ranges
        '[a-c]'
        '[^x-z]'.
* Arbitrary quantification {n,m}, {,n} {m,}
