package de.codemakers.math.expression.operator;

/**
 * AbstractOperator
 *
 * @author Paul Hagedorn
 */
public abstract class AbstractOperator<T> {

    /**
     * The precedence value for the addition operation
     */
    public static final int PRECEDENCE_ADDITION = 500;
    /**
     * The precedence value for the subtraction operation
     */
    public static final int PRECEDENCE_SUBTRACTION = PRECEDENCE_ADDITION;
    /**
     * The precedence value for the multiplication operation
     */
    public static final int PRECEDENCE_MULTIPLICATION = 1000;
    /**
     * The precedence value for the division operation
     */
    public static final int PRECEDENCE_DIVISION = PRECEDENCE_MULTIPLICATION;
    /**
     * The precedence value for the modulo operation
     */
    public static final int PRECEDENCE_MODULO = PRECEDENCE_DIVISION;
    /**
     * The precedence value for the power operation
     */
    public static final int PRECEDENCE_POWER = 10000;
    /**
     * The precedence value for the over power operation
     */
    public static final int PRECEDENCE_OVER_POWER = 15000;
    /**
     * The precedence value for the unary minus operation
     */
    public static final int PRECEDENCE_UNARY_MINUS = 5000;
    /**
     * The precedence value for the unary plus operation
     */
    public static final int PRECEDENCE_UNARY_PLUS = PRECEDENCE_UNARY_MINUS;

    public static final char[] ALLOWED_OPERATOR_CHARS = {'+', '-', '*', '/', '%', '^', '!', '#', '§', '$', '&', ';', ':', '~', '<', '>', '|', '='};

    protected final int numOperands;
    protected final boolean leftAssociative;
    protected final String symbol;
    protected final int precedence;

    public AbstractOperator(int numOperands, boolean leftAssociative, String symbol, int precedence) {
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

    public abstract T apply(T... args);

    public static final boolean isAllowedOperatorChar(char c) {
        for (char c_ : ALLOWED_OPERATOR_CHARS) {
            if (c_ == c) {
                return true;
            }
        }
        return false;
    }

}
