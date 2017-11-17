package de.codemakers.math.expression.function;

import de.codemakers.math.complex.ComplexDouble;
import java.util.ArrayList;
import java.util.List;

/**
 * ComplexDoubleFunctions
 *
 * @author Paul Hagedorn
 */
public class ComplexDoubleFunctions {

    public static final List<AbstractFunction<ComplexDouble>> STANDARD_FUNCTIONS = new ArrayList<>(23);

    static {
        STANDARD_FUNCTIONS.add(new AbstractFunction<ComplexDouble>("sin") {
            @Override
            public final ComplexDouble apply(ComplexDouble... args) {
                return args[0].sin();
            }
        });
        STANDARD_FUNCTIONS.add(new AbstractFunction<ComplexDouble>("cos") {
            @Override
            public final ComplexDouble apply(ComplexDouble... args) {
                return args[0].cos();
            }
        });
        STANDARD_FUNCTIONS.add(new AbstractFunction<ComplexDouble>("tan") {
            @Override
            public final ComplexDouble apply(ComplexDouble... args) {
                return args[0].tan();
            }
        });
        STANDARD_FUNCTIONS.add(new AbstractFunction<ComplexDouble>("cot") {
            @Override
            public final ComplexDouble apply(ComplexDouble... args) {
                return args[0].cot();
            }
        });
        STANDARD_FUNCTIONS.add(new AbstractFunction<ComplexDouble>("log") {
            @Override
            public final ComplexDouble apply(ComplexDouble... args) {
                return args[0].log();
            }
        });
        STANDARD_FUNCTIONS.add(new AbstractFunction<ComplexDouble>("log2") {
            @Override
            public final ComplexDouble apply(ComplexDouble... args) {
                return args[0].log2();
            }
        });
        STANDARD_FUNCTIONS.add(new AbstractFunction<ComplexDouble>("log10") {
            @Override
            public final ComplexDouble apply(ComplexDouble... args) {
                return args[0].log10();
            }
        });
        STANDARD_FUNCTIONS.add(new AbstractFunction<ComplexDouble>("log1p") {
            @Override
            public final ComplexDouble apply(ComplexDouble... args) {
                return args[0].log1p();
            }
        });
        STANDARD_FUNCTIONS.add(new AbstractFunction<ComplexDouble>("abs") {
            @Override
            public final ComplexDouble apply(ComplexDouble... args) {
                return ComplexDouble.ofDouble(args[0].getNorm());
            }
        });
        STANDARD_FUNCTIONS.add(new AbstractFunction<ComplexDouble>("acos") {
            @Override
            public final ComplexDouble apply(ComplexDouble... args) {
                return args[0].acos();
            }
        });
        STANDARD_FUNCTIONS.add(new AbstractFunction<ComplexDouble>("asin") {
            @Override
            public final ComplexDouble apply(ComplexDouble... args) {
                return args[0].asin();
            }
        });
        STANDARD_FUNCTIONS.add(new AbstractFunction<ComplexDouble>("atan") {
            @Override
            public final ComplexDouble apply(ComplexDouble... args) {
                return args[0].atan();
            }
        });
        STANDARD_FUNCTIONS.add(new AbstractFunction<ComplexDouble>("cbrt") {
            @Override
            public final ComplexDouble apply(ComplexDouble... args) {
                return args[0].pow(ComplexDouble.ONE.divide(ComplexDouble.THREE));
            }
        });
        STANDARD_FUNCTIONS.add(new AbstractFunction<ComplexDouble>("floor") {
            @Override
            public final ComplexDouble apply(ComplexDouble... args) {
                return args[0].floor();
            }
        });
        STANDARD_FUNCTIONS.add(new AbstractFunction<ComplexDouble>("sinh") {
            @Override
            public final ComplexDouble apply(ComplexDouble... args) {
                return args[0].sinh();
            }
        });
        STANDARD_FUNCTIONS.add(new AbstractFunction<ComplexDouble>("sqrt") {
            @Override
            public final ComplexDouble apply(ComplexDouble... args) {
                return args[0].pow(ComplexDouble.HALF);
            }
        });
        STANDARD_FUNCTIONS.add(new AbstractFunction<ComplexDouble>("tanh") {
            @Override
            public final ComplexDouble apply(ComplexDouble... args) {
                return args[0].tanh();
            }
        });
        STANDARD_FUNCTIONS.add(new AbstractFunction<ComplexDouble>("cosh") {
            @Override
            public final ComplexDouble apply(ComplexDouble... args) {
                return args[0].cosh();
            }
        });
        STANDARD_FUNCTIONS.add(new AbstractFunction<ComplexDouble>("ceil") {
            @Override
            public final ComplexDouble apply(ComplexDouble... args) {
                return args[0].ceil();
            }
        });
        STANDARD_FUNCTIONS.add(new AbstractFunction<ComplexDouble>("pow", 2) {
            @Override
            public final ComplexDouble apply(ComplexDouble... args) {
                return args[0].pow(args[1]);
            }
        });
        STANDARD_FUNCTIONS.add(new AbstractFunction<ComplexDouble>("exp", 1) {
            @Override
            public final ComplexDouble apply(ComplexDouble... args) {
                return args[0].exp();
            }
        });
        STANDARD_FUNCTIONS.add(new AbstractFunction<ComplexDouble>("expm1", 1) {
            @Override
            public final ComplexDouble apply(ComplexDouble... args) {
                return args[0].expm1();
            }
        });
        STANDARD_FUNCTIONS.add(new AbstractFunction<ComplexDouble>("signum", 1) {
            @Override
            public final ComplexDouble apply(ComplexDouble... args) {
                return args[0].sgn();
            }
        });
    }

    public static final AbstractFunction<ComplexDouble> getStandardFunction(String name) {
        if (name == null || name.length() == 0) {
            return null;
        }
        return STANDARD_FUNCTIONS.stream().filter((function) -> name.equals(function.getName())).findFirst().orElse(null);
    }

}
