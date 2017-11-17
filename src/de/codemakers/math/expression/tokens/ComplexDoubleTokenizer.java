package de.codemakers.math.expression.tokens;

import de.codemakers.math.complex.ComplexDouble;
import de.codemakers.math.expression.function.AbstractFunction;
import de.codemakers.math.expression.operator.AbstractOperator;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public final AbstractFunction<ComplexDouble> getStandardFunction(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
