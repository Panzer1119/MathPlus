package de.codemakers.math.exact;

/**
 * ExactNumber
 *
 * @author Paul Hagedorn
 */
public class ExactNumber extends ExactAbstract {

    public static final ExactNumber MINUS_TEN = new ExactNumber(-10);
    public static final ExactNumber MINUS_FIVE = new ExactNumber(-5);
    public static final ExactNumber MINUS_PI = new ExactNumber(-Math.PI);
    public static final ExactNumber MINUS_THREE = new ExactNumber(-3);
    public static final ExactNumber MINUS_E = new ExactNumber(-Math.E);
    public static final ExactNumber MINUS_TWO = new ExactNumber(-2);
    public static final ExactNumber MINUS_ONE = new ExactNumber(-1);
    public static final ExactNumber ZERO = new ExactNumber(0);
    public static final ExactNumber ONE = new ExactNumber(1);
    public static final ExactNumber TWO = new ExactNumber(2);
    public static final ExactNumber E = new ExactNumber(Math.E);
    public static final ExactNumber THREE = new ExactNumber(3);
    public static final ExactNumber PI = new ExactNumber(Math.PI);
    public static final ExactNumber FIVE = new ExactNumber(5);
    public static final ExactNumber TEN = new ExactNumber(10);

    private double value = 0.0;

    public ExactNumber(double value) {
        this.value = value;
    }

    @Override
    public final ExactNumber copy() {
        return new ExactNumber(value);
    }

    @Override
    public final ExactAbstract reset() {
        value = 0.0;
        return this;
    }

    @Override
    public final String toLaTeXString(Object... options) {
        return "" + value;
    }

    @Override
    public final int intValue() {
        return (int) value;
    }

    @Override
    public final long longValue() {
        return (long) value;
    }

    @Override
    public final float floatValue() {
        return (float) value;
    }

    @Override
    public final double doubleValue() {
        return value;
    }

}
