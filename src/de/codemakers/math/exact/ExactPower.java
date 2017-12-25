package de.codemakers.math.exact;

import java.util.Objects;

/**
 * ExactPower
 *
 * @author Paul Hagedorn
 */
public class ExactPower extends ExactAbstract {

    protected ExactAbstract base = null;
    protected ExactAbstract exponent = null;

    public ExactPower(ExactAbstract base, ExactAbstract exponent) {
        Objects.requireNonNull(base);
        Objects.requireNonNull(exponent);
        this.base = base;
        this.exponent = exponent;
    }

    @Override
    public final ExactPower copy() {
        return new ExactPower(base.copy(), exponent.copy());
    }

    @Override
    public final ExactAbstract divide(ExactAbstract number) {
        if (number instanceof ExactPower) {
            final ExactPower other = (ExactPower) number;
            if (base.equals(other.base)) {
                return new ExactPower(base.copy(), exponent.subtract(other.exponent));
            }
        } else if (number instanceof ExactRoot) {
            final ExactRoot other = (ExactRoot) number;
            if (base.equals(other.radicand)) {
                return new ExactPower(base, exponent.subtract(other.degree.inverse()));
            }
        }
        if (base.equals(number)) {
            return new ExactPower(base.copy(), exponent.subtract(ExactNumber.ONE));
        }
        return super.divide(number);
    }

    @Override
    public final ExactAbstract inverse() {
        return new ExactPower(base.copy(), exponent.negate());
    }

    @Override
    public final ExactAbstract log(ExactAbstract number) {
        if (base.equals(number)) {
            return exponent.copy();
        }
        return super.log(number);
    }

    @Override
    public final ExactAbstract multiply(ExactAbstract number) {
        if (number instanceof ExactPower) {
            final ExactPower other = (ExactPower) number;
            if (base.equals(other.base)) {
                return new ExactPower(base.copy(), exponent.add(other.exponent));
            }
        } else if (number instanceof ExactRoot) {
            final ExactRoot other = (ExactRoot) number;
            if (base.equals(other.radicand)) {
                return new ExactPower(base, exponent.add(other.degree.inverse()));
            }
        }
        if (base.equals(number)) {
            return new ExactPower(base.copy(), exponent.add(ExactNumber.ONE));
        }
        return super.multiply(number);
    }

    @Override
    public final ExactPower pow(ExactAbstract number) {
        return new ExactPower(base.copy(), exponent.multiply(number));
    }

    @Override
    public final ExactAbstract reset() {
        base = ExactNumber.ONE;
        exponent = ExactNumber.ONE;
        return this;
    }

    @Override
    public final ExactRoot root(ExactAbstract number) {
        return new ExactRoot(number.divide(exponent), base.copy());
    }

    @Override
    public final String toLaTeXString(Object... options) {
        String extra = "%s^{%s}";
        if (options != null && options.length > 0) {
            if (options[0] == ExactPower.class || options[0] == ExactRoot.class || options[0] == ExactLogarithm.class) {
                extra = "(%s^{%s})";
            }
        }
        return String.format(extra, base.toLaTeXString(ExactPower.class, true), exponent.toLaTeXString(ExactPower.class, false));
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
        return Math.pow(base.doubleValue(), exponent.doubleValue());
    }

}
