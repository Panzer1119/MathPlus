package de.codemakers.math.expression.tokens;

/**
 * VariableToken
 *
 * @author Paul Hagedorn
 */
public class VariableToken extends Token {

    private final String name;

    public VariableToken(String name) {
        super(TokenType.VARIABLE);
        this.name = name;
    }

    public final String getName() {
        return name;
    }

}
