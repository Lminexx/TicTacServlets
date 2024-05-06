package org.example.tic_tac;

public enum Sign {
    EMPTY(' '),
    CROSS('X'),
    NOUGHT('0');

    private final char sign;

    Sign(char sign) {
        this.sign = sign;
    }

    public char getSign() {
        return sign;
    }
}