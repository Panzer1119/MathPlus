package de.codemakers.math.expression.tokens;

import de.codemakers.math.complex.ComplexDouble;

/**
 * ComplexDoubleToken
 *
 * @author Paul Hagedorn
 */
public class ComplexDoubleToken extends Token {

    private final ComplexDouble value;

    public ComplexDoubleToken(double value) {
        this(ComplexDouble.ofDouble(value));
    }

    public ComplexDoubleToken(ComplexDouble value) {
        super(TokenType.COMPLEX_NUMBER);
        this.value = value;
    }

    public final ComplexDouble getValue() {
        return value;
    }

    @Override
    public final String getName() {
        return getClass().getSimpleName();
    }

}
