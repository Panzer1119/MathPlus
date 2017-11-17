package de.codemakers.math.expression.shuntingyard;

import de.codemakers.math.complex.ComplexDouble;
import de.codemakers.math.expression.function.AbstractFunction;
import de.codemakers.math.expression.operator.AbstractOperator;
import de.codemakers.math.expression.tokens.Token;
import de.codemakers.math.expression.tokens.ComplexDoubleTokenizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * ShuntingYard
 *
 * @author Paul Hagedorn
 */
public class ShuntingYard {

    public static final List<Token<ComplexDouble>> convertToComplexDoubleRPN(String expression, Map<String, AbstractFunction<ComplexDouble>> userFunctions, Map<String, AbstractOperator<ComplexDouble>> userOperators, Set<String> variableNames, boolean implicitMultiplication) {
        final Stack<Token<ComplexDouble>> stack = new Stack<>();
        final List<Token<ComplexDouble>> output = new ArrayList<>();
        final ComplexDoubleTokenizer tokenizer = new ComplexDoubleTokenizer(expression, userFunctions, userOperators, variableNames, implicitMultiplication);
        return null;
    }

}
