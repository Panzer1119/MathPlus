package de.codemakers.math.matrix;

/**
 * MatrixFunction
 *
 * @author Paul Hagedorn
 */
public interface MatrixFunction<T extends Number> {

    public T apply(int row, int col, T value);

}
