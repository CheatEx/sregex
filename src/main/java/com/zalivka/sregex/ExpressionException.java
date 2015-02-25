package com.zalivka.sregex;

/**
 * Indicates an error in expression's syntax.
 */
public class ExpressionException extends Exception {
    public ExpressionException(String message) {
        super(message);
    }
}
