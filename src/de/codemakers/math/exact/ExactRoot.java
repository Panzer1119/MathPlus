package de.codemakers.math.exact;

import java.util.Objects;

/**
 * ExactRoot
 *
 * @author Paul Hagedorn
 */
public class ExactRoot extends ExactAbstract {

    protected ExactAbstract degree = null;
    protected ExactAbstract radicand = null;

    public ExactRoot(ExactAbstract degree, ExactAbstract radicand) {
        Objects.requireNonNull(degree);
        Objects.requireNonNull(radicand);
        this.degree = degree;
        this.radicand = radicand;
    }

    @Override
    public final ExactAbstract copy() {
        return new ExactRoot(degree.copy(), radicand.copy());
    }

    @Override
    public ExactAbstract divide(ExactAbstract number) {
        if (number instanceof ExactRoot) {
            final ExactRoot other = (ExactRoot) number;
            if (radicand.equals(other.radicand)) {
                return new ExactRoot(degree.inverse().add(number.inverse()).inverse(), radicand.copy());
            }
        } else if (number instanceof ExactPower) {
            final ExactPower other = (ExactPower) number;
            if (radicand.equals(other.base)) {
                return new ExactRoot(degree.add(other.exponent.inverse()), radicand.copy());
            }
        }
        if (radicand.equals(number)) {
            return new ExactRoot(radicand.copy(), degree.divide(degree.subtract(ExactNumber.ONE)).negate());
        }
        return super.divide(number);
    }

    @Override
    public ExactAbstract inverse() {
        return new ExactRoot(degree.negate(), radicand.copy());
    }

    @Override
    public ExactAbstract log(ExactAbstract number) {
        if (radicand.equals(number)) {
            return degree.inverse();
        }
        return super.log(number);
    }

    @Override
    public ExactAbstract multiply(ExactAbstract number) {
        if (number instanceof ExactRoot) {
            final ExactRoot other = (ExactRoot) number;
            if (radicand.equals(other.radicand)) {
                return new ExactRoot(degree.inverse().add(number.inverse()).inverse(), radicand.copy());
            }
        } else if (number instanceof ExactPower) {
            final ExactPower other = (ExactPower) number;
            if (radicand.equals(other.base)) {
                return new ExactRoot(degree.add(other.exponent.inverse()), radicand.copy());
            }
        }
        if (number.equals(this)) {
            return radicand.copy();
        }
        if (radicand.equals(number)) {
            return new ExactRoot(radicand.copy(), degree.divide(degree.add(ExactNumber.ONE)));
        }
        return super.multiply(number);
    }

    @Override
    public ExactPower pow(ExactAbstract number) {
        return new ExactPower(number.divide(degree), radicand.copy());
    }

    @Override
    public final ExactAbstract reset() {
        degree = ExactNumber.ONE;
        radicand = ExactNumber.ONE;
        return this;
    }

    @Override
    public ExactRoot root(ExactAbstract number) {
        return new ExactRoot(degree.multiply(number), radicand.copy());
    }

    @Override
    public final String toLaTeXString(Object... options) {
        if (degree.equals(ExactNumber.TWO)) {
            return String.format("\\sqrt{%s}", radicand.toLaTeXString(ExactRoot.class));
        }
        return String.format("\\sqrt[%s]{%s}", degree.toLaTeXString(ExactRoot.class), radicand.toLaTeXString(ExactRoot.class));
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
        return Math.pow(radicand.doubleValue(), degree.inverse().doubleValue());
    }

}
