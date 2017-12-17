package de.codemakers.math.expression;

import de.codemakers.math.expression.function.AbstractFunction;
import de.codemakers.math.expression.operator.AbstractOperator;
import de.codemakers.math.expression.tokens.FunctionToken;
import de.codemakers.math.expression.tokens.OperatorToken;
import de.codemakers.math.expression.tokens.Token;
import de.codemakers.math.expression.tokens.TokenType;
import de.codemakers.math.expression.tokens.VariableToken;
import de.codemakers.math.util.Counter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * AbstractMultiExpression
 *
 * @author Paul Hagedorn
 */
public abstract class AbstractMultiExpression<T> {

    final List<Token<T>> tokens;
    final Map<String, List<T>> variables;
    final Set<String> userFunctionNames;

    public AbstractMultiExpression(List<Token<T>> tokens) {
        this(tokens, Collections.emptySet());
    }

    public AbstractMultiExpression(List<Token<T>> tokens, Set<String> userFunctionNames) {
        this(tokens, null, userFunctionNames);
    }

    public AbstractMultiExpression(List<Token<T>> tokens, Map<String, List<T>> variables, Set<String> userFunctionNames) {
        this.tokens = tokens;
        this.variables = variables == null ? createDefaultVariables() : variables;
        this.userFunctionNames = userFunctionNames;
    }

    public AbstractMultiExpression(AbstractMultiExpression abstractExpression) {
        this.tokens = new ArrayList<>();
        this.tokens.addAll(abstractExpression.tokens);
        this.variables = new HashMap<>();
        this.variables.putAll(abstractExpression.variables);
        this.userFunctionNames = new HashSet<>(abstractExpression.userFunctionNames);
    }

    public final AbstractMultiExpression setVariable(String name, List<T> value) {
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

    public final AbstractMultiExpression setVariables(Map<String, List<T>> variables) {
        if (variables == null || variables.isEmpty()) {
            return this;
        }
        variables.entrySet().stream().forEach(this::setVariable);
        return this;
    }

    private final AbstractMultiExpression setVariable(Map.Entry<String, List<T>> variable) {
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

    public final ValidationResult validate() {
        return validate(true);
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
                errors.add(String.format("Too many operators for \"%s\"", token.getName()));
                return new ValidationResult(false, errors);
            }
        }
        if (count > 1) {
            errors.add("Too many operators");
        }
        return errors.isEmpty() ? ValidationResult.SUCCESS : new ValidationResult(false, errors);
    }

    public final Future<Map<Map<String, T>, T>> evaluateAsync(ExecutorService executor) {
        return executor.submit(() -> evalute());
    }

    public final Map<Map<String, T>, T> evalute() {
        final Map<Map<String, T>, T> output = new HashMap<>();
        final List<Map.Entry<String, List<T>>> variables_ = new ArrayList<>(variables.entrySet());
        final List<List<Counter>> counters = Counter.permute(variables_.stream().map((entry) -> new Counter(0, 0, entry.getValue().size() - 1)).collect(Collectors.toList()));
        final List<Map<String, Counter>> counters_mapped = counters.stream().map((counters_) -> IntStream.range(0, variables_.size()).boxed().collect(Collectors.toMap((i) -> variables_.get(i).getKey(), (i) -> counters_.get(i)))).collect(Collectors.toList());
        counters_mapped.forEach((counters_map) -> {
            final Map<String, T> values = new HashMap<>();
            variables.entrySet().forEach((entry) -> values.put(entry.getKey(), entry.getValue().get(counters_map.get(entry.getKey()).getCount())));
            output.put(values, evaluteOne(values));
        });
        return output;
    }

    abstract T evaluteOne(Map<String, T> values);

    abstract Map<String, List<T>> createDefaultVariables();

}
