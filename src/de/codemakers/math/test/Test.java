package de.codemakers.math.test;

import de.codemakers.math.complex.ComplexDouble;

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
        System.out.println("End");
    }

}
