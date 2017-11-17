package de.codemakers.math.expression.tokens;

import de.codemakers.math.expression.operator.AbstractOperator;

/**
 * OperatorToken
 *
 * @author Paul Hagedorn
 */
public class OperatorToken<T> extends Token {

    private final AbstractOperator<T> operator;

    public OperatorToken(AbstractOperator<T> operator) {
        super(TokenType.OPERATOR);
        if (operator == null) {
            throw new IllegalArgumentException("Operator is unknown for token");
        }
        this.operator = operator;
    }

    public final AbstractOperator<T> getOperator() {
        return operator;
    }

    @Override
    public final String getName() {
        return operator.getSymbol();
    }

}
