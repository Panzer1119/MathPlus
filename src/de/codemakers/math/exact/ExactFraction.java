package de.codemakers.math.exact;

import de.codemakers.math.util.MathUtil;
import java.util.Objects;

/**
 * FractionDouble
 *
 * @author Paul Hagedorn
 */
public class ExactFraction extends ExactAbstract {

    protected ExactAbstract numerator = null;
    protected ExactAbstract denominator = null;

    public ExactFraction(ExactAbstract numerator, ExactAbstract denominator) {
        Objects.requireNonNull(numerator);
        Objects.requireNonNull(denominator);
        this.numerator = numerator;
        this.denominator = denominator;
    }

    @Override
    public final ExactFraction copy() {
        return new ExactFraction(numerator.copy(), denominator.copy());
    }

    @Override
    public final ExactAbstract divide(ExactAbstract number) {
        if (numerator.equals(number)) {
            return denominator.inverse();
        }
        return new ExactFraction(numerator.copy(), denominator.multiply(number));
    }

    @Override
    public final ExactAbstract inverse() {
        if (numerator.equals(ExactNumber.ONE)) {
            return denominator.copy();
        } else if (numerator.equals(ExactNumber.ZERO)) {
            return null;
        } else if (numerator.equals(ExactNumber.MINUS_ONE)) {
            return denominator.negate();
        }
        return new ExactFraction(denominator.copy(), numerator.copy());
    }

    @Override
    public final ExactAbstract multiply(ExactAbstract number) {
        if (denominator.equals(number)) {
            return numerator.copy();
        }
        return new ExactFraction(numerator.multiply(number), denominator.copy());
    }

    @Override
    public final ExactAbstract reset() {
        numerator = ExactNumber.ONE;
        denominator = ExactNumber.ONE;
        return this;
    }

    @Override
    public final String toLaTeXString(Object... options) {
        String extra = "\frac{%s}{%s}";
        if (options != null && options.length > 0) {
            if (options[0] == ExactPower.class || options[0] == ExactRoot.class || options[0] == ExactLogarithm.class) {
                extra = "(\frac{%s}{%s})";
            }
        }
        return String.format(extra, numerator != null ? numerator.toLaTeXString(ExactFraction.class) : MathUtil.STRING_NAN, denominator != null ? denominator.toLaTeXString(ExactFraction.class) : MathUtil.STRING_NAN);
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
        return numerator.doubleValue() / denominator.doubleValue();
    }

}
