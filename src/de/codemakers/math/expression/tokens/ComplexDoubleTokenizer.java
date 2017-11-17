package de.codemakers.math.expression.tokens;

import de.codemakers.math.complex.ComplexDouble;
import de.codemakers.math.expression.function.AbstractFunction;
import de.codemakers.math.expression.function.ComplexDoubleFunctions;
import de.codemakers.math.expression.operator.AbstractOperator;
import de.codemakers.math.expression.operator.ComplexDoubleOperators;
import java.util.Map;
import java.util.Set;

/**
 * ComplexDoubleTokenizer
 *
 * @author Paul Hagedorn
 */
public class ComplexDoubleTokenizer extends AbstractTokenizer<ComplexDouble> {

    public ComplexDoubleTokenizer(String expression, Map<String, AbstractFunction<ComplexDouble>> userFunctions, Map<String, AbstractOperator<ComplexDouble>> userOperators, Set<String> variableNames, boolean implicitMultiplication) {
        super(expression, userFunctions, userOperators, variableNames, implicitMultiplication);
    }

    @Override
    public final AbstractOperator<ComplexDouble> getStandardOperator(char symbol, int numArguments) {
        return ComplexDoubleOperators.getStandardOperator("" + symbol, numArguments);
    }

    @Override
    public final AbstractFunction<ComplexDouble> getStandardFunction(String name) {
        return ComplexDoubleFunctions.getStandardFunction(name);
    }

}
