package de.codemakers.math.test;

import de.codemakers.math.complex.ComplexDouble;
import de.codemakers.math.expression.ComplexDoubleExpression;
import de.codemakers.math.expression.ComplexDoubleExpressionBuilder;
import de.codemakers.math.expression.ValidationResult;

/**
 * Test
 *
 * @author Paul Hagedorn
 */
public class Test {

    public static final void main(String[] args) {
        System.out.println("Start");
        System.out.println(ComplexDouble.HALF_I.sin());
        System.out.println(ComplexDouble.HALF_I.sinh());
        System.out.println(ComplexDouble.HALF_I.cos());
        System.out.println(ComplexDouble.HALF_I.cosh());
        System.out.println(ComplexDouble.HALF_I.tan());
        System.out.println(ComplexDouble.HALF_I.tanh());
        System.out.println(ComplexDouble.HALF_I.cot());
        System.out.println(ComplexDouble.HALF_I.sec());
        System.out.println(ComplexDouble.HALF_I.log());
        System.out.println(ComplexDouble.HALF_I.log(ComplexDouble.TWO));
        System.out.println(ComplexDouble.HALF_I.add(ComplexDouble.TWO).sgn());
        System.out.println(ComplexDouble.HALF_I.add(ComplexDouble.TWO).pow(0.5));
        System.out.println(ComplexDouble.HALF_I.add(ComplexDouble.TWO).asin());
        System.out.println(ComplexDouble.HALF_I.add(ComplexDouble.TWO).acos());
        System.out.println(ComplexDouble.HALF_I.add(ComplexDouble.TWO).atan());
        System.out.println(ComplexDouble.HALF_I.add(ComplexDouble.TWO).asec());
        final String expression_string = "log(1 + 2i)^e";
        System.err.println(expression_string);
        final ComplexDoubleExpressionBuilder builder = new ComplexDoubleExpressionBuilder(expression_string);
        final ComplexDoubleExpression expression = builder.build();
        final ValidationResult result = expression.validate();
        System.err.println("Valid: " + result.isValid() + (result.isValid() ? "" : "\n" + result));
        System.err.println(expression.evalute());
        System.out.println("End");
    }

}
