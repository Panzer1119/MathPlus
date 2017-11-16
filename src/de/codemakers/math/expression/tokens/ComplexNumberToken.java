package de.codemakers.math.expression.tokens;

import org.nevec.rjm.BigComplex;

/**
 * NumberToken
 *
 * @author Paul Hagedorn
 */
public class ComplexNumberToken extends Token {

    private final BigComplex value;

    public ComplexNumberToken(BigComplex value) {
        super(TokenType.COMPLEX_NUMBER);
        this.value = value;
    }

    public final BigComplex getValue() {
        return value;
    }

}
