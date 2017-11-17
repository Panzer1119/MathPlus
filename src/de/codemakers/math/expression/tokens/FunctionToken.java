package de.codemakers.math.expression.tokens;

import de.codemakers.math.expression.function.AbstractFunction;

/**
 * FunctionToken
 *
 * @author Paul Hagedorn
 */
public class FunctionToken<T> extends Token {

    private final AbstractFunction<T> function;

    public FunctionToken(AbstractFunction<T> function) {
        super(TokenType.FUNCTION);
        this.function = function;
    }

    public final AbstractFunction<T> getFunction() {
        return function;
    }

    @Override
    public final String getName() {
        return function == null ? getClass().getSimpleName() : function.getName();
    }

}
