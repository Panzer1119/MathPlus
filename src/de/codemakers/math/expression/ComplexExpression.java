package de.codemakers.math.expression;

import de.codemakers.math.expression.tokens.Token;
import de.codemakers.math.expression.tokens.TokenType;
import de.codemakers.math.expression.tokens.VariableToken;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
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

    public ComplexExpression(List<Token> tokens) {
        this(tokens, Collections.emptySet());
    }

    public ComplexExpression(List<Token> tokens, Set<String> userFunctionNames) {
        this(tokens, createDefaultVariables(), userFunctionNames);
    }

    public ComplexExpression(List<Token> tokens, Map<String, BigComplex> variables, Set<String> userFunctionNames) {
        this.tokens = tokens;
        this.variables = variables;
        this.userFunctionNames = userFunctionNames;
    }

    public ComplexExpression(ComplexExpression complexExpression) {
        this.tokens = new ArrayList<>();
        this.tokens.addAll(complexExpression.tokens);
        this.variables = new HashMap<>();
        this.variables.putAll(complexExpression.variables);
        this.userFunctionNames = new HashSet<>(complexExpression.userFunctionNames);
    }

    public final ComplexExpression setVariable(String name, BigComplex value) {
        if (value == null) {
            return this;
        }
        this.checkVariableName(name);
        this.variables.put(name, value);
        return this;
    }

    private final void checkVariableName(String name) {
        if (this.userFunctionNames.contains(name)) {
            throw new IllegalArgumentException(String.format("The variable name \"%s\" is invalid. Since there exists a function with the same name", name));
        }
    }

    public final ComplexExpression setVariables(Map<String, BigComplex> variables) {
        if (variables == null || variables.isEmpty()) {
            return this;
        }
        variables.entrySet().stream().forEach(this::setVariable);
        return this;
    }

    private final ComplexExpression setVariable(Map.Entry<String, BigComplex> variable) {
        if (variable == null) {
            return this;
        }
        setVariable(variable.getKey(), variable.getValue());
        return this;
    }

    public final Set<String> getVariableNames() {
        final Set<String> variableNames = new HashSet<>();
        tokens.stream().filter((token) -> token.getTokenType() == TokenType.VARIABLE).map((token) -> ((VariableToken) token).getName()).forEach(variableNames::add);
        return variableNames;
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
