package de.codemakers.math.exact;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * ExactSum
 *
 * @author Paul Hagedorn
 */
public class ExactSum extends ExactAbstract {

    protected final List<ExactAbstract> summands = new ArrayList<>();

    public ExactSum(ExactAbstract... summands) {
        Objects.requireNonNull(summands);
        this.summands.addAll(Arrays.asList(summands));
    }

    @Override
    public final ExactSum add(ExactAbstract number) {
        final ExactSum copy = copy();
        if (!copy.removeExisting(number)) {
            copy.summands.add(number.copy());
        }
        return copy;
    }

    @Override
    public final ExactSum copy() {
        return new ExactSum(summands.stream().map(ExactAbstract::copy).toArray(ExactAbstract[]::new));
    }

    @Override
    public final ExactSum reset() {
        summands.clear();
        return this;
    }

    @Override
    public final ExactSum subtract(ExactAbstract number) {
        final ExactSum copy = copy();
        if (!copy.removeExisting(number.negate())) {
            copy.summands.add(number.negate());
        }
        return copy;
    }
    
    private final boolean removeExisting(ExactAbstract number) {
        for (int i = 0; i < summands.size(); i++) {
            if (summands.get(i).negate().equals(number)) {
                summands.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public final String toLaTeXString(Object... options) {
        String extra = "%s";
        if (options != null && options.length > 0) {
            if (options[0] == ExactProduct.class || options[0] == ExactPower.class || options[0] == ExactRoot.class || options[0] == ExactLogarithm.class) {
                extra = "(%s)";
            }
        }
        return String.format(extra, summands.stream().map((number) -> number.toLaTeXString(ExactSum.class)).collect(Collectors.joining(" + ")));
    }

    @Override
    public final int intValue() {
        return summands.stream().mapToInt(ExactAbstract::intValue).sum();
    }

    @Override
    public final long longValue() {
        return summands.stream().mapToLong(ExactAbstract::longValue).sum();
    }

    @Override
    public final float floatValue() {
        return (float) doubleValue();
    }

    @Override
    public final double doubleValue() {
        return summands.stream().mapToDouble(ExactAbstract::doubleValue).sum();
    }

}
