package de.codemakers.math.expression;

import de.codemakers.math.complex.ComplexDouble;
import de.codemakers.math.expression.tokens.Token;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * ComplexDoubleExpression
 *
 * @author Paul Hagedorn
 */
public class ComplexDoubleExpression extends AbstractExpression<ComplexDouble> {

    public ComplexDoubleExpression(List<Token<ComplexDouble>> tokens) {
        this(tokens, Collections.emptySet());
    }

    public ComplexDoubleExpression(List<Token<ComplexDouble>> tokens, Set<String> userFunctionNames) {
        this(tokens, null, userFunctionNames);
    }

    public ComplexDoubleExpression(List<Token<ComplexDouble>> tokens, Map<String, ComplexDouble> variables, Set<String> userFunctionNames) {
        super(tokens, variables, userFunctionNames);
    }

    @Override
    final Map<String, ComplexDouble> createDefaultVariables() {
        final Map<String, ComplexDouble> variables = new HashMap<>(5);
        variables.put("pi", ComplexDouble.PI);
        variables.put("π", ComplexDouble.PI);
        variables.put("e", ComplexDouble.E);
        variables.put("φ", ComplexDouble.GOLDEN_RATIO);
        variables.put("γ", ComplexDouble.GAMMA);
        return variables;
    }

}
