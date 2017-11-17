package de.codemakers.math.expression;

import de.codemakers.math.expression.function.AbstractFunction;
import de.codemakers.math.expression.operator.AbstractOperator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * AbstractExpressionBuilder
 *
 * @author Paul Hagedorn
 */
public abstract class AbstractExpressionBuilder<T> {

    final String expression;
    final Map<String, AbstractFunction<T>> userFunctions;
    final Map<String, AbstractOperator<T>> userOperators;
    final Set<String> variableNames;
    boolean implicitMultiplication = true;

    public AbstractExpressionBuilder(String expression) {
        if (expression == null || expression.trim().length() == 0) {
            throw new IllegalArgumentException("Expression can not be empty");
        }
        this.expression = expression;
        this.userFunctions = new HashMap<>(4);
        this.userOperators = new HashMap<>(4);
        this.variableNames = new HashSet<>(4);
    }

    public final AbstractExpressionBuilder addFunction(AbstractFunction function) {
        this.userFunctions.put(function.getName(), function);
        return this;
    }

    public final AbstractExpressionBuilder addFunctions(AbstractFunction... functions) {
        if (functions == null || functions.length == 0) {
            return this;
        }
        for (AbstractFunction function : functions) {
            addFunction(function);
        }
        return this;
    }

    public final AbstractExpressionBuilder addFunctions(List<AbstractFunction> functions) {
        if (functions == null || functions.isEmpty()) {
            return this;
        }
        functions.stream().forEach(this::addFunction);
        return this;
    }

    public final AbstractExpressionBuilder addVariable(String variableName) {
        this.variableNames.add(variableName);
        return this;
    }

    public final AbstractExpressionBuilder addVariables(String... variableNames) {
        if (variableNames == null || variableNames.length == 0) {
            return this;
        }
        for (String variableName : variableNames) {
            addVariable(variableName);
        }
        return this;
    }

    public final AbstractExpressionBuilder addVariables(Set<String> variableNames) {
        if (variableNames == null || variableNames.isEmpty()) {
            return this;
        }
        variableNames.stream().forEach(this::addVariable);
        return this;
    }

    public final AbstractExpressionBuilder setImplicitMultiplication(boolean enabled) {
        this.implicitMultiplication = enabled;
        return this;
    }

    public final AbstractExpressionBuilder addOperator(AbstractOperator operator) {
        this.checkOperatorSymbol(operator);
        this.userOperators.put(operator.getSymbol(), operator);
        return this;
    }

    public final AbstractExpressionBuilder addOperators(AbstractOperator... operators) {
        if (operators == null || operators.length == 0) {
            return this;
        }
        for (AbstractOperator operator : operators) {
            addOperator(operator);
        }
        return this;
    }

    public final AbstractExpressionBuilder addOperators(List<AbstractOperator> operators) {
        if (operators == null || operators.isEmpty()) {
            return this;
        }
        operators.stream().forEach(this::addOperator);
        return this;
    }

    private final void checkOperatorSymbol(AbstractOperator operator) {
        final String name = operator.getSymbol();
        for (char c : name.toCharArray()) {
            if (!AbstractOperator.isAllowedOperatorChar(c)) {
                throw new IllegalArgumentException(String.format("The operator symbol '%s' in \"%s\" is invalid", c, name));
            }
        }
    }

    public final AbstractExpressionBuilder distinct() {

        return this;
    }

    public abstract AbstractExpression build();

}
