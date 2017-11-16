package de.codemakers.math.expression.function;

import java.util.ArrayList;
import java.util.List;
import org.nevec.rjm.BigComplex;

/**
 * Functions
 *
 * @author Paul Hagedorn
 */
public class Functions {
    
    public static final List<Function> STANDARD_FUNCTIONS = new ArrayList<>(23);
    
    static {
        STANDARD_FUNCTIONS.add(new Function("sin") {
            @Override
            public final BigComplex apply(BigComplex... args) {
                
                return BigComplex.ZERO;
            }
        });
    }

}
