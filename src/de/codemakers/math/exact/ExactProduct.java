package de.codemakers.math.exact;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * ExactProduct
 *
 * @author Paul Hagedorn
 */
public class ExactProduct extends ExactAbstract {

    protected final List<ExactAbstract> factors = new ArrayList<>();

    public ExactProduct(ExactAbstract... factors) {
        Objects.requireNonNull(factors);
        if (factors.length == 0) {
            this.factors.add(ExactNumber.ONE);
        } else {
            this.factors.addAll(Arrays.asList(factors));
        }
    }

    @Override
    public final ExactProduct copy() {
        return new ExactProduct(factors.stream().map(ExactAbstract::copy).toArray(ExactAbstract[]::new));
    }

    @Override
    public final ExactAbstract divide(ExactAbstract number) {
        final ExactProduct copy = copy();
        if (number instanceof ExactProduct) {
            final ExactProduct other = (ExactProduct) number.copy();
            final Iterator<ExactAbstract> it = other.factors.iterator();
            while (it.hasNext()) {
                final ExactAbstract temp = it.next();
                final int index = copy.factors.indexOf(temp);
                if (index != -1) {
                    copy.factors.remove(index);
                    it.remove();
                }
            }
            if (other.factors.isEmpty()) {
                return copy;
            } else {
                return new ExactFraction(copy, other);
            }
        } else if (number instanceof ExactFraction) {
            return number.divide(copy);
        } if (!copy.removeExisting(number.inverse())) {
            return new ExactFraction(copy, number.copy());
        }
        return copy;
    }

    @Override
    public final ExactAbstract multiply(ExactAbstract number) {
        final ExactProduct copy = copy();
        if (number instanceof ExactProduct) {
            copy.factors.addAll(((ExactProduct) number).copy().factors);
        } else if (number instanceof ExactFraction) {
            return number.multiply(copy);
        } else if (!copy.removeExisting(number)) {
            copy.factors.add(number.copy());
        }
        return copy;
    }

    private final boolean removeExisting(ExactAbstract number) {
        for (int i = 0; i < factors.size(); i++) {
            if (factors.get(i).inverse().equals(number)) {
                factors.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public final ExactProduct reset() {
        factors.clear();
        factors.add(ExactNumber.ONE);
        return this;
    }

    @Override
    public final String toLaTeXString(Object... options) {
        String extra = "%s";
        if (options != null && options.length > 0) {
            if (options[0] == ExactSum.class || options[0] == ExactPower.class || options[0] == ExactRoot.class || options[0] == ExactLogarithm.class) {
                extra = "(%s)";
            }
        }
        return String.format(extra, factors.stream().map((number) -> number.toLaTeXString(ExactProduct.class)).collect(Collectors.joining(" * ")));
    }

    @Override
    public final int intValue() {
        return factors.stream().mapToInt(ExactAbstract::intValue).reduce(1, Math::multiplyExact);
    }

    @Override
    public final long longValue() {
        return factors.stream().mapToLong(ExactAbstract::longValue).reduce(1, Math::multiplyExact);
    }

    @Override
    public final float floatValue() {
        return (float) doubleValue();
    }

    @Override
    public final double doubleValue() {
        return factors.stream().mapToDouble(ExactAbstract::doubleValue).reduce(1, (a, b) -> a * b);
    }

}
