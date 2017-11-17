package de.codemakers.math.expression;

import de.codemakers.math.complex.ComplexDouble;
import de.codemakers.math.expression.function.ComplexDoubleFunctions;
import de.codemakers.math.expression.shuntingyard.ShuntingYard;

/**
 * ComplexDoubleExpressionBuilder
 *
 * @author Paul Hagedorn
 */
public class ComplexDoubleExpressionBuilder extends AbstractExpressionBuilder<ComplexDouble> {

    public ComplexDoubleExpressionBuilder(String expression) {
        super(expression);
    }

    @Override
    public final ComplexDoubleExpression build() {
        if (expression.length() == 0) {
            throw new IllegalArgumentException("The expression can not be empty");
        }
        addVariable("i");
        addVariable("pi");
        addVariable("π");
        addVariable("e");
        addVariable("φ");
        addVariable("γ");
        distinct();
        for (String variableName : variableNames) {
            if (ComplexDoubleFunctions.getStandardFunction(variableName) != null || userFunctions.containsKey(variableName)) {
                throw new IllegalArgumentException(String.format("A variable can not have the same name as a function [%s]", variableName));
            }
        }
        return new ComplexDoubleExpression(ShuntingYard.convertToComplexDoubleRPN(expression, userFunctions, userOperators, variableNames, implicitMultiplication), userFunctions.keySet());
    }

}
