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

/**
 * AbstractExpression
 *
 * @author Paul Hagedorn
 */
public abstract class AbstractExpression<T> {

    private final List<Token<T>> tokens;
    private final Map<String, T> variables;
    private final Set<String> userFunctionNames;

    public AbstractExpression(List<Token<T>> tokens) {
        this(tokens, Collections.emptySet());
    }

    public AbstractExpression(List<Token<T>> tokens, Set<String> userFunctionNames) {
        this(tokens, null, userFunctionNames);
    }

    public AbstractExpression(List<Token<T>> tokens, Map<String, T> variables, Set<String> userFunctionNames) {
        this.tokens = tokens;
        this.variables = variables == null ? createDefaultVariables() : variables;
        this.userFunctionNames = userFunctionNames;
    }

    public AbstractExpression(AbstractExpression abstractExpression) {
        this.tokens = new ArrayList<>();
        this.tokens.addAll(abstractExpression.tokens);
        this.variables = new HashMap<>();
        this.variables.putAll(abstractExpression.variables);
        this.userFunctionNames = new HashSet<>(abstractExpression.userFunctionNames);
    }

    public final AbstractExpression setVariable(String name, T value) {
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

    public final AbstractExpression setVariables(Map<String, T> variables) {
        if (variables == null || variables.isEmpty()) {
            return this;
        }
        variables.entrySet().stream().forEach(this::setVariable);
        return this;
    }

    private final AbstractExpression setVariable(Map.Entry<String, T> variable) {
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

    abstract Map<String, T> createDefaultVariables();

}
