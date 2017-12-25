package de.codemakers.math.exact;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * ExactFunction
 *
 * @author Paul Hagedorn
 */
public class ExactFunction extends ExactAbstract {

    protected Function function = null;
    protected final List<ExactAbstract> values = new ArrayList<>();

    public ExactFunction(Function function, ExactAbstract... values) {
        Objects.requireNonNull(function);
        Objects.requireNonNull(values);
        this.function = function;
        this.values.addAll(Arrays.asList(values));
    }

    @Override
    public final ExactAbstract copy() {
        return new ExactFunction(function, values.stream().map(ExactAbstract::copy).toArray(ExactAbstract[]::new));
    }

    @Override
    public final ExactAbstract reset() {
        function = Function.NORM;
        values.clear();
        return this;
    }

    @Override
    public final String toLaTeXString(Object... options) {
        switch (function) {
            case MOD:
                return String.format("%s %% %s)", function.getLaTeXName(), values.get(0).toLaTeXString(ExactFunction.class, function), values.get(1).toLaTeXString(ExactFunction.class, function));
            case ACOS:
            case ARG:
            case ASEC:
            case ASIN:
            case ATAN:
            case CEIL:
            case COS:
            case COSH:
            case COT:
            case FLOOR:
            case NORM:
            case NORM_SQUARED:
            case SEC:
            case SGN:
            case SIN:
            case SINH:
            case TAN:
            case TANH:
            default:
                return String.format("%s(%s)", function.getLaTeXName(), values.get(0).toLaTeXString(ExactFunction.class, function));
        }
    }

    @Override
    public final int intValue() {
        return (int) doubleValue();
    }

    @Override
    public final long longValue() {
        return (long) doubleValue();
    }

    @Override
    public final float floatValue() {
        return (float) doubleValue();
    }

    @Override
    public final double doubleValue() {
        switch (function) {
            case ACOS:
                return Math.acos(values.get(0).doubleValue());
            case ARG:
                //return Math.arg(values.get(0).doubleValue()); //TODO ComplexNumbers
                return Double.NaN;
            case ASEC:
                return 1.0 / Math.sin(values.get(0).doubleValue());
            case ASIN:
                return Math.asin(values.get(0).doubleValue());
            case ATAN:
                return Math.atan(values.get(0).doubleValue());
            case CEIL:
                return Math.ceil(values.get(0).doubleValue());
            case COS:
                return Math.cos(values.get(0).doubleValue());
            case COSH:
                return Math.cosh(values.get(0).doubleValue());
            case COT:
                return 1.0 / Math.tan(values.get(0).doubleValue());
            case FLOOR:
                return Math.floor(values.get(0).doubleValue());
            case MOD:
                return values.get(0).doubleValue() % values.get(1).doubleValue();
            case NORM:
                return values.get(0).doubleValue();
            case NORM_SQUARED:
                return Math.pow(values.get(0).doubleValue(), 2);
            case SEC:
                return 1.0 / Math.cos(values.get(0).doubleValue());
            case SGN:
                return values.get(0).doubleValue() == 0 ? 0 : (values.get(0).doubleValue() > 0 ? 1 : -1);
            case SIN:
                return Math.sin(values.get(0).doubleValue());
            case SINH:
                return Math.sinh(values.get(0).doubleValue());
            case TAN:
                return Math.tan(values.get(0).doubleValue());
            case TANH:
                return Math.tanh(values.get(0).doubleValue());
            default:
                return Double.NaN;
        }
    }

}
