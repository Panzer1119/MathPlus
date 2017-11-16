package de.codemakers.math.expression.operator;

import org.nevec.rjm.BigComplex;

/**
 * Operator
 *
 * @author Paul Hagedorn
 */
public abstract class Operator {

    public static final char[] ALLOWED_OPERATOR_CHARS = {'+', '-', '*', '/', '%', '^', '!', '#', '§', '$', '&', ';', ':', '~', '<', '>', '|', '='};

    protected final int numOperands;
    protected final boolean leftAssociative;
    protected final String symbol;
    protected final int precedence;

    public Operator(int numOperands, boolean leftAssociative, String symbol, int precedence) {
        this.numOperands = numOperands;
        this.leftAssociative = leftAssociative;
        this.symbol = symbol;
        this.precedence = precedence;
    }

    public final int getNumOperands() {
        return numOperands;
    }

    public final boolean isLeftAssociative() {
        return leftAssociative;
    }

    public final String getSymbol() {
        return symbol;
    }

    public final int getPrecedence() {
        return precedence;
    }

    public abstract BigComplex apply(BigComplex... args);

    public static final boolean isAllowedOperatorChar(char c) {
        for (char c_ : ALLOWED_OPERATOR_CHARS) {
            if (c_ == c) {
                return true;
            }
        }
        return false;
    }

}
