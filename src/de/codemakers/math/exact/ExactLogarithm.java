package de.codemakers.math.exact;

import java.util.Objects;

/**
 * ExactLogarithm
 *
 * @author Paul Hagedorn
 */
public class ExactLogarithm extends ExactAbstract {

    protected ExactAbstract base = null;
    protected ExactAbstract antilogarithm = null;

    public ExactLogarithm(ExactAbstract base, ExactAbstract antilogarithm) {
        Objects.requireNonNull(base);
        Objects.requireNonNull(antilogarithm);
        this.base = base;
        this.antilogarithm = antilogarithm;
    }

    @Override
    public final ExactAbstract copy() {
        return new ExactLogarithm(base.copy(), antilogarithm.copy());
    }

    @Override
    public final ExactAbstract reset() {
        base = ExactNumber.TEN;
        antilogarithm = ExactNumber.ZERO;
        return this;
    }

    @Override
    public final String toLaTeXString(Object... options) {
        String extra = "\\log_%s %s";
        if (options != null && options.length > 0) {
            if ((options[0] == ExactPower.class && ((boolean) options[1])) || options[0] == ExactRoot.class || options[0] == ExactLogarithm.class) {
                extra = "(\\log_%s %s)";
            }
        }
        return String.format(extra, base.toLaTeXString(ExactLogarithm.class, true), antilogarithm.toLaTeXString(ExactLogarithm.class, false));
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
        return Math.log(antilogarithm.doubleValue()) / Math.log(base.doubleValue());
    }

}
