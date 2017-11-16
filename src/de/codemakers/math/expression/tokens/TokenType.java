package de.codemakers.math.expression.tokens;

/**
 * TokenType
 *
 * @author Paul Hagedorn
 */
public enum TokenType {
    ARGUMENT_SEPERATOR(0),
    CLOSE_PARENTHESES(1),
    FUNCTION(2),
    NUMBER(3),
    OPEN_PARENTHESES(4),
    OPERATOR(5),
    VARIABLE(6),
    COMPLEX_NUMBER(7);

    private final int type;

    TokenType(int type) {
        this.type = type;
    }

    public final int getType() {
        return type;
    }

    public static final TokenType ofType(int type) {
        for (TokenType tokenType : values()) {
            if (tokenType.getType() == type) {
                return tokenType;
            }
        }
        return null;
    }
}
