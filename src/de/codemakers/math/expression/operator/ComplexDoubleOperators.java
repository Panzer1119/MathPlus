package de.codemakers.math.expression.operator;

import de.codemakers.math.complex.ComplexDouble;
import java.util.ArrayList;
import java.util.List;

/**
 * ComplexDoubleOperators
 *
 * @author Paul Hagedorn
 */
public class ComplexDoubleOperators {

    public static final List<AbstractOperator<ComplexDouble>> STANDARD_OPERATORS = new ArrayList<>(9);

    static {
        STANDARD_OPERATORS.add(new AbstractOperator<ComplexDouble>(2, true, "+", AbstractOperator.PRECEDENCE_ADDITION) {
            @Override
            public final ComplexDouble apply(ComplexDouble... args) {
                return args[0].add(args[1]);
            }
        });
        STANDARD_OPERATORS.add(new AbstractOperator<ComplexDouble>(2, true, "-", AbstractOperator.PRECEDENCE_SUBTRACTION) {
            @Override
            public final ComplexDouble apply(ComplexDouble... args) {
                return args[0].subtract(args[1]);
            }
        });
        STANDARD_OPERATORS.add(new AbstractOperator<ComplexDouble>(1, false, "+", AbstractOperator.PRECEDENCE_UNARY_PLUS) {
            @Override
            public final ComplexDouble apply(ComplexDouble... args) {
                return args[0];
            }
        });
        STANDARD_OPERATORS.add(new AbstractOperator<ComplexDouble>(1, false, "-", AbstractOperator.PRECEDENCE_UNARY_MINUS) {
            @Override
            public final ComplexDouble apply(ComplexDouble... args) {
                return args[0].negate();
            }
        });
        STANDARD_OPERATORS.add(new AbstractOperator<ComplexDouble>(2, true, "*", AbstractOperator.PRECEDENCE_MULTIPLICATION) {
            @Override
            public final ComplexDouble apply(ComplexDouble... args) {
                return args[0].multiply(args[1]);
            }
        });
        STANDARD_OPERATORS.add(new AbstractOperator<ComplexDouble>(2, true, "/", AbstractOperator.PRECEDENCE_DIVISION) {
            @Override
            public final ComplexDouble apply(ComplexDouble... args) {
                return args[0].divide(args[1]);
            }
        });
        STANDARD_OPERATORS.add(new AbstractOperator<ComplexDouble>(2, false, "^", AbstractOperator.PRECEDENCE_POWER) {
            @Override
            public final ComplexDouble apply(ComplexDouble... args) {
                return args[0].pow(args[1]);
            }
        });
        STANDARD_OPERATORS.add(new AbstractOperator<ComplexDouble>(2, true, "%", AbstractOperator.PRECEDENCE_MODULO) {
            @Override
            public final ComplexDouble apply(ComplexDouble... args) {
                return args[0].mod(args[1]);
            }
        });
        STANDARD_OPERATORS.add(new AbstractOperator<ComplexDouble>(2, false, "∠", AbstractOperator.PRECEDENCE_POWER) {
            @Override
            public final ComplexDouble apply(ComplexDouble... args) {
                return args[0].multiply(ComplexDouble.E.pow(ComplexDouble.ONE_I.multiply(args[1]).multiply(ComplexDouble.PI.divide(ComplexDouble.ofInt(180)))));
            }
        });
    }

    public static final AbstractOperator<ComplexDouble> getStandardOperator(String symbol, int numArguments) {
        if (symbol == null || symbol.length() == 0 || numArguments < 0) {
            return null;
        }
        return STANDARD_OPERATORS.stream().filter((operator) -> symbol.equals(operator.getSymbol())).filter((operator) -> numArguments == operator.getNumOperands()).findFirst().orElse(null);
    }

}
