package de.codemakers.math;

/**
 * AdvancedDouble
 *
 * @author Paul Hagedorn
 */
public class AdvancedDouble extends AdvancedNumber {

    public static final AdvancedDouble MINUES_TEN = new AdvancedDouble(-10);
    public static final AdvancedDouble MINUES_PI = new AdvancedDouble(-Math.PI);
    public static final AdvancedDouble MINUES_THREE = new AdvancedDouble(-3);
    public static final AdvancedDouble MINUES_E = new AdvancedDouble(-Math.E);
    public static final AdvancedDouble MINUES_TWO = new AdvancedDouble(-2);
    public static final AdvancedDouble MINUES_ONE = new AdvancedDouble(-1);
    public static final AdvancedDouble ZERO = new AdvancedDouble(0);
    public static final AdvancedDouble ONE = new AdvancedDouble(1);
    public static final AdvancedDouble TWO = new AdvancedDouble(2);
    public static final AdvancedDouble E = new AdvancedDouble(Math.E);
    public static final AdvancedDouble THREE = new AdvancedDouble(3);
    public static final AdvancedDouble PI = new AdvancedDouble(Math.PI);
    public static final AdvancedDouble TEN = new AdvancedDouble(10);

    private double value = 0.0;

    public AdvancedDouble(double value) {
        this.value = value;
    }

    @Override
    public final AdvancedDouble copy() {
        return new AdvancedDouble(value);
    }

    @Override
    public final AdvancedDouble add(AdvancedNumber number) {
        check(number);
        return new AdvancedDouble(value + number.doubleValue());
    }

    @Override
    public final AdvancedDouble subtract(AdvancedNumber number) {
        check(number);
        return new AdvancedDouble(value - number.doubleValue());
    }

    @Override
    public final AdvancedDouble multiply(AdvancedNumber number) {
        check(number);
        return new AdvancedDouble(value * number.doubleValue());
    }

    @Override
    public final AdvancedDouble divide(AdvancedNumber number) {
        check(number);
        return new AdvancedDouble(value / number.doubleValue());
    }

    @Override
    public final AdvancedDouble mod(AdvancedNumber number) {
        check(number);
        return new AdvancedDouble(value % number.doubleValue());
    }

    @Override
    public final AdvancedDouble pow(AdvancedNumber number) {
        check(number);
        return new AdvancedDouble(Math.pow(value, number.doubleValue()));
    }

    @Override
    public final AdvancedDouble log() {
        return new AdvancedDouble(Math.log(value));
    }

    @Override
    public final AdvancedDouble log10() {
        return new AdvancedDouble(Math.log10(value));
    }

    @Override
    public final AdvancedDouble reset() {
        value = 0.0;
        return this;
    }

    @Override
    public final AdvancedDouble acos() {
        return new AdvancedDouble(Math.acos(value));
    }

    @Override
    public final AdvancedDouble arg() {
        throw new UnsupportedOperationException("This is not a complex number");
    }

    @Override
    public final AdvancedDouble asec() {
        return new AdvancedDouble(Math.sin(value)).inverse();
    }

    @Override
    public final AdvancedDouble asin() {
        return new AdvancedDouble(Math.asin(value));
    }

    @Override
    public final AdvancedDouble atan() {
        return new AdvancedDouble(Math.atan(value));
    }

    @Override
    public final AdvancedDouble ceil() {
        return new AdvancedDouble(Math.ceil(value));
    }

    @Override
    public final AdvancedDouble cos() {
        return new AdvancedDouble(Math.cos(value));
    }

    @Override
    public final AdvancedDouble cosh() {
        return new AdvancedDouble(Math.cosh(value));
    }

    @Override
    public final AdvancedDouble cot() {
        return new AdvancedDouble(Math.tan(value)).inverse();
    }

    @Override
    public final AdvancedDouble exp() {
        return new AdvancedDouble(Math.exp(value));
    }

    @Override
    public final AdvancedDouble expm1() {
        return new AdvancedDouble(Math.expm1(value));
    }

    @Override
    public final AdvancedDouble floor() {
        return new AdvancedDouble(Math.floor(value));
    }

    @Override
    public final AdvancedDouble inverse() {
        return ONE.divide(this);
    }

    @Override
    public final AdvancedDouble norm() {
        return new AdvancedDouble(Math.abs(value));
    }

    @Override
    public final AdvancedDouble normSquared() {
        return new AdvancedDouble(value * value);
    }

    @Override
    public final AdvancedDouble log(AdvancedNumber number) {
        return new AdvancedDouble(Math.log((value))).divide(number.log());
    }

    @Override
    public final AdvancedDouble log(double number) {
        return new AdvancedDouble(Math.log((value) / Math.log(number)));
    }

    @Override
    public final AdvancedDouble log(int number) {
        return new AdvancedDouble(Math.log((value) / Math.log(number)));
    }

    @Override
    public final AdvancedDouble log1p() {
        return new AdvancedDouble(Math.log1p(value));
    }

    @Override
    public final AdvancedDouble log2() {
        return new AdvancedDouble(Math.log(value));
    }

    @Override
    public final AdvancedDouble negate() {
        return new AdvancedDouble(-value);
    }

    @Override
    public final AdvancedDouble pow(double number) {
        return new AdvancedDouble(Math.pow(value, number));
    }

    @Override
    public final AdvancedDouble pow(int n) {
        return new AdvancedDouble(Math.pow(value, n));
    }

    @Override
    public final AdvancedDouble sec() {
        return new AdvancedDouble(Math.cos(value)).inverse();
    }

    @Override
    public final AdvancedDouble sgn() {
        return new AdvancedDouble(value == 0 ? 0 : (value > 1 ? 1 : -1));
    }

    @Override
    public final AdvancedDouble sin() {
        return new AdvancedDouble(Math.sin(value));
    }

    @Override
    public final AdvancedDouble sinh() {
        return new AdvancedDouble(Math.sinh(value));
    }

    @Override
    public final AdvancedDouble tan() {
        return new AdvancedDouble(Math.tan(value));
    }

    @Override
    public final AdvancedDouble tanh() {
        return new AdvancedDouble(Math.tanh(value));
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

    @Override
    public final boolean equals(AdvancedNumber number) {
        if (number == null) {
            return false;
        }
        if (number == this) {
            return true;
        }
        check(number);
        return value == number.doubleValue();
    }

    @Override
    public final String toString() {
        return "" + doubleValue();
    }

    @Override
    public final boolean isCompatible(AdvancedNumber number) {
        return number != null && number instanceof AdvancedDouble;
    }

}
