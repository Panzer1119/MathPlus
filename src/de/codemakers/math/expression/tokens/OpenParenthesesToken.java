package de.codemakers.math.expression.tokens;

/**
 * OpenParenthesesToken
 *
 * @author Paul Hagedorn
 */
public class OpenParenthesesToken extends Token {

    public OpenParenthesesToken() {
        super(TokenType.OPEN_PARENTHESES);
    }

    @Override
    public final String getName() {
        return getClass().getSimpleName();
    }

}
