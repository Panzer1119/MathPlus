package de.codemakers.math;

/**
 * AdvancedDouble
 *
 * @author Paul Hagedorn
 */
public class AdvancedDouble extends AdvancedNumber {

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
    public final AdvancedDouble modulo(AdvancedNumber number) {
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
