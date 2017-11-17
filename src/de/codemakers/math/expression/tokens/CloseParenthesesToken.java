package de.codemakers.math.expression.tokens;

/**
 * CloseParenthesesToken
 *
 * @author Paul Hagedorn
 */
public class CloseParenthesesToken extends Token {

    public CloseParenthesesToken() {
        super(TokenType.CLOSE_PARENTHESES);
    }

    @Override
    public final String getName() {
        return getClass().getSimpleName();
    }

}
