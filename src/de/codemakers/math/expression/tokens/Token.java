package de.codemakers.math.expression.tokens;

/**
 * Token
 *
 * @author Paul Hagedorn
 */
public abstract class Token<T> {

    private final TokenType tokenType;

    public Token(TokenType tokenType) {
        this.tokenType = tokenType;
    }

    public final TokenType getTokenType() {
        return tokenType;
    }

    public abstract String getName();

}
