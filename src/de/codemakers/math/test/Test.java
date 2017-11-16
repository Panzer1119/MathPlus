package de.codemakers.math.test;

import ch.obermuhlner.math.big.BigDecimalMath;
import java.math.BigDecimal;
import java.math.MathContext;
import org.nevec.rjm.BigComplex;
import org.nevec.rjm.BigComplexMath;

/**
 * Test
 *
 * @author Paul Hagedorn
 */
public class Test {
    
    public static final void main(String[] args) {
        System.out.println("Start");
        System.out.println(BigComplexMath.pow(BigComplex.TWO_I, BigComplex.I));
        System.out.println(BigComplexMath.powe(BigComplex.I));
        System.err.println(BigDecimalMath.sin(BigDecimal.ONE, MathContext.UNLIMITED));
        System.err.println(BigComplexMath.sin(BigComplex.I));
        System.err.println(BigComplexMath.cos(BigComplex.I));
        System.out.println("End");
    }

}
