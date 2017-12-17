package de.codemakers.math.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Counter
 *
 * @author Paul Hagedorn
 */
public class Counter {

    private int count = 0;
    private int min = 0;
    private int max = 0;

    /**
     * Max and Min are inclusive
     *
     * @param start Start value
     * @param min Minimum value
     * @param max Maximum value
     */
    public Counter(int start, int min, int max) {
        if (max < start) {
            throw new IllegalArgumentException("The maximum counter can not be below the start counter");
        } else if (max < min) {
            throw new IllegalArgumentException("The maximum counter can not be below the minimum counter");
        } else if (min > start) {
            throw new IllegalArgumentException("The minimum counter can not be above the start counter");
        }
        this.count = start;
        this.min = min;
        this.max = max;
    }

    public final Counter copy() {
        return new Counter(count, min, max);
    }

    public final int getCount() {
        return count;
    }

    public final Counter setCount(int count) {
        this.count = count;
        return this;
    }

    public final int getMin() {
        return min;
    }

    public final Counter setMin(int min) {
        this.min = min;
        return this;
    }

    public final int getMax() {
        return max;
    }

    public final Counter setMax(int max) {
        this.max = max;
        return this;
    }

    public final int increment() {
        count = Math.min(Math.max(min, count + 1), max);
        return count;
    }

    public final int decrement() {
        count = Math.min(Math.max(min, count - 1), max);
        return count;
    }

    @Override
    public final boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (object == this) {
            return true;
        }
        if (object instanceof Counter) {
            final Counter counter = (Counter) object;
            return count == counter.count && min == counter.min && max == counter.max;
        }
        return false;
    }

    public static final List<List<Counter>> permute(List<Counter> counters) {
        return permuteSingle(new ArrayList<>(), 0, counters.stream().map(Counter::copy).map((counter) -> counter.setCount(counter.getMin())).collect(Collectors.toList()));
    }

    private static final List<List<Counter>> permuteSingle(List<List<Counter>> output, int depth, List<Counter> counters) {
        if (counters == null || counters.isEmpty() || depth >= counters.size()) {
            return output;
        }
        final List<Counter> counters_temp = counters.stream().map(Counter::copy).collect(Collectors.toList());
        final Counter counter = counters_temp.get(depth);
        do {
            final List<Counter> temp = counters_temp.stream().map(Counter::copy).collect(Collectors.toList());
            if (!output.contains(temp)) {
                output.add(temp);
            }
            permuteSingle(output, depth + 1, counters_temp);
        } while (counter.getCount() != counter.increment());
        return output;
    }

}
