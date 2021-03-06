package de.codemakers.math.expression.tokens;

/**
 * ArgumentSeperatorToken
 *
 * @author Paul Hagedorn
 */
public class ArgumentSeperatorToken extends Token {

    public ArgumentSeperatorToken() {
        super(TokenType.ARGUMENT_SEPERATOR);
    }

    @Override
    public final String getName() {
        return getClass().getSimpleName();
    }

}
