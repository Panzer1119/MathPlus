package de.codemakers.math.expression;

import de.codemakers.math.expression.function.Function;
import de.codemakers.math.expression.operator.Operator;
import de.codemakers.math.expression.shuntingyard.ShuntingYard;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * ComplexExpressionBuilder
 *
 * @author Paul Hagedorn
 */
public class ComplexExpressionBuilder {

    private final String expression;
    private final Map<String, Function> userFunctions;
    private final Map<String, Operator> userOperators;
    private final Set<String> variableNames;
    private boolean implicitMultiplication = true;

    public ComplexExpressionBuilder(String expression) {
        if (expression == null || expression.trim().length() == 0) {
            throw new IllegalArgumentException("Expression can not be empty");
        }
        this.expression = expression;
        this.userFunctions = new HashMap<>(4);
        this.userOperators = new HashMap<>(4);
        this.variableNames = new HashSet<>(4);
    }

    public final ComplexExpressionBuilder addFunction(Function function) {
        this.userFunctions.put(function.getName(), function);
        return this;
    }

    public final ComplexExpressionBuilder addFunctions(Function... functions) {
        if (functions == null || functions.length == 0) {
            return this;
        }
        for (Function function : functions) {
            addFunction(function);
        }
        return this;
    }

    public final ComplexExpressionBuilder addFunctions(List<Function> functions) {
        if (functions == null || functions.isEmpty()) {
            return this;
        }
        functions.stream().forEach(this::addFunction);
        return this;
    }

    public final ComplexExpressionBuilder addVariable(String variableName) {
        this.variableNames.add(variableName);
        return this;
    }

    public final ComplexExpressionBuilder addVariables(String... variableNames) {
        if (variableNames == null || variableNames.length == 0) {
            return this;
        }
        for (String variableName : variableNames) {
            addVariable(variableName);
        }
        return this;
    }

    public final ComplexExpressionBuilder addVariables(Set<String> variableNames) {
        if (variableNames == null || variableNames.isEmpty()) {
            return this;
        }
        variableNames.stream().forEach(this::addVariable);
        return this;
    }

    public final ComplexExpressionBuilder setImplicitMultiplication(boolean enabled) {
        this.implicitMultiplication = enabled;
        return this;
    }

    public final ComplexExpressionBuilder addOperator(Operator operator) {
        this.checkOperatorSymbol(operator);
        this.userOperators.put(operator.getSymbol(), operator);
        return this;
    }

    public final ComplexExpressionBuilder addOperators(Operator... operators) {
        if (operators == null || operators.length == 0) {
            return this;
        }
        for (Operator operator : operators) {
            addOperator(operator);
        }
        return this;
    }

    public final ComplexExpressionBuilder addOperators(List<Operator> operators) {
        if (operators == null || operators.isEmpty()) {
            return this;
        }
        operators.stream().forEach(this::addOperator);
        return this;
    }

    private final void checkOperatorSymbol(Operator operator) {
        final String name = operator.getSymbol();
        for (char c : name.toCharArray()) {
            if (!Operator.isAllowedOperatorChar(c)) {
                throw new IllegalArgumentException(String.format("The operator symbol '%s' in \"%s\" is invalid", c, name));
            }
        }
    }
    
    public final ComplexExpressionBuilder distinct() {
        
        return this;
    }
    
    public final ComplexExpression build() {
        if (expression.length() == 0) {
            throw new IllegalArgumentException("The expression can not be empty");
        }
        addVariable("pi");
        addVariable("π");
        addVariable("e");
        addVariable("φ");
        addVariable("γ");
        distinct();
        for (String variableName : variableNames) {
            
        }
        return new ComplexExpression(ShuntingYard.convertToRPN(expression, userFunctions, userOperators, variableNames, implicitMultiplication), userFunctions.keySet());
    }

}
