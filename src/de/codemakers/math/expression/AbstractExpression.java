package de.codemakers.math.expression;

import de.codemakers.math.expression.function.AbstractFunction;
import de.codemakers.math.expression.operator.AbstractOperator;
import de.codemakers.math.expression.tokens.FunctionToken;
import de.codemakers.math.expression.tokens.OperatorToken;
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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * AbstractExpression
 *
 * @author Paul Hagedorn
 */
public abstract class AbstractExpression<T> {

    final List<Token<T>> tokens;
    final Map<String, T> variables;
    final Set<String> userFunctionNames;

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

    public final ValidationResult validate(boolean checkVariableSet) {
        final List<String> errors = new ArrayList<>(0);
        if (checkVariableSet) {
            tokens.stream().filter((token) -> token.getTokenType() == TokenType.VARIABLE).map((token) -> ((VariableToken) token).getName()).filter((variableName) -> !variables.containsKey(variableName)).forEach((variableName) -> errors.add(String.format("The variable \"%s\" has not been set", variableName)));
        }
        int count = 0;
        for (Token<T> token : tokens) {
            switch (token.getTokenType()) {
                case FUNCTION:
                    final AbstractFunction<T> function = ((FunctionToken<T>) token).getFunction();
                    final int argsNum = function.getNumArguments();
                    if (argsNum > count) {
                        errors.add(String.format("Not enough arguments for \"%s\"", function.getName()));
                    }
                    if (argsNum > 1) {
                        count -= argsNum - 1;
                    } else if (argsNum == 0) {
                        count++;
                    }
                    break;
                case OPERATOR:
                    final AbstractOperator<T> operator = ((OperatorToken<T>) token).getOperator();
                    if (operator.getNumOperands() == 2) {
                        count--;
                    }
                    break;
                case NUMBER:
                case VARIABLE:
                case COMPLEX_NUMBER:
                    count++;
                    break;
                case ARGUMENT_SEPERATOR:
                case CLOSE_PARENTHESES:
                case OPEN_PARENTHESES:
                default:
                    break;
            }
            if (count < 1) {
                errors.add("Too many operators");
                return new ValidationResult(false, errors);
            }
        }
        if (count > 1) {
            errors.add("Too many operators");
        }
        return errors.isEmpty() ? ValidationResult.SUCCESS : new ValidationResult(false, errors);
    }

    public final ValidationResult validate() {
        return validate(true);
    }

    public final Future<T> evaluateAsync(ExecutorService executor) {
        return executor.submit(() -> evalute());
    }

    public abstract T evalute();

    abstract Map<String, T> createDefaultVariables();

}
