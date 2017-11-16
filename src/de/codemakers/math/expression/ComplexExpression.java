package de.codemakers.math.expression;

import de.codemakers.math.expression.tokens.Token;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.nevec.rjm.BigComplex;
import org.nevec.rjm.BigDecimalMath;

/**
 * ComplexExpression
 *
 * @author Paul Hagedorn
 */
public class ComplexExpression {
    
    private final List<Token> tokens;
    private final Map<String, BigComplex> variables;
    private final Set<String> userFunctionNames;

    public ComplexExpression(List<Token> tokens, Map<String, BigComplex> variables, Set<String> userFunctionNames) {
        this.tokens = tokens;
        this.variables = variables;
        this.userFunctionNames = userFunctionNames;
    }
    
    static final Map<String, BigComplex> createDefaultVariables() {
        final Map<String, BigComplex> variables = new HashMap<>(5);
        variables.put("pi", new BigComplex(BigDecimalMath.PI));
        variables.put("π", new BigComplex(BigDecimalMath.PI));
        variables.put("e", new BigComplex(BigDecimalMath.E));
        variables.put("φ", new BigComplex(BigDecimalMath.GOLDEN_RATIO));
        variables.put("γ", new BigComplex(BigDecimalMath.GAMMA));
        return variables;
    }
    
}
