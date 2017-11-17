package de.codemakers.math.expression;

import java.util.EmptyStackException;
import java.util.function.IntFunction;

/**
 * ArrayStack
 *
 * @author Paul Hagedorn
 */
public class ArrayStack<T> {

    private final IntFunction<T[]> generator;
    private T[] data;
    private int idx;

    public ArrayStack(IntFunction<T[]> generator) {
        this(5, generator);
    }

    public ArrayStack(int initialCapacity, IntFunction<T[]> generator) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Stack's capacity must be positive");
        }
        this.generator = generator;
        this.data = generator.apply(initialCapacity);
        idx = -1;
    }

    public final ArrayStack push(T value) {
        if (idx + 1 == data.length) {
            T[] temp = generator.apply((int) (data.length * 1.2) + 1);
            System.arraycopy(data, 0, temp, 0, data.length);
            data = temp;
        }
        data[++idx] = value;
        return this;
    }

    public final T peek() {
        if (idx == -1) {
            throw new EmptyStackException();
        }
        return data[idx];
    }

    public final T pop() {
        if (idx == -1) {
            throw new EmptyStackException();
        }
        return data[idx--];
    }

    public final boolean isEmpty() {
        return idx == -1;
    }

    public final int size() {
        return idx + 1;
    }
}
