package de.codemakers.math.matrix;

import de.codemakers.math.AdvancedDouble;
import de.codemakers.math.AdvancedNumber;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class Matrix {

    private final AdvancedNumber[][] matrix; //Zeilen zuerst, Spalten spaeter
    
    public Matrix(int rows, int cols, Class<? extends AdvancedNumber> clazz) {
        this(rows, cols, false, clazz);
    }

    public Matrix(int rows, int cols, boolean identity, Class<? extends AdvancedNumber> clazz) {
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("A matrix has to be at least 1X1");
        } else if (clazz == null) {
            throw new IllegalArgumentException("A matrix has to have a Class");
        }
        this.matrix = (AdvancedNumber[][]) Array.newInstance(clazz, new int[]{rows, cols});
        if (clazz.isAssignableFrom(AdvancedDouble.class)) {
            applyFunction((row, col, value) -> new AdvancedDouble((row == col && identity) ? 1 : 0));
        }
    }

    public Matrix(AdvancedNumber[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            throw new IllegalArgumentException("A matrix have to be at least 1X1");
        }
        this.matrix = matrix;
    }

    public final Matrix copy() {
        return new Matrix(getRows(), getCols(), getClazz()).setMatrix(matrix);
    }

    public final AdvancedNumber[][] getMatrix() {
        return matrix;
    }

    public final Matrix setMatrix(AdvancedNumber[][] matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("A matrix must not be null");
        } else if (matrix.length == 0 || matrix[0].length == 0) {
            throw new IllegalArgumentException("A matrix have to be at least 1X1");
        } else if (this.matrix.length != matrix.length || this.matrix[0].length != matrix[0].length) {
            throw new IllegalArgumentException("The matrix have to be the same size");
        }
        final int rows = getRows();
        final int cols = getCols();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                set(row, col, matrix[row][col]);
            }
        }
        return this;
    }

    public final AdvancedNumber[] getRow(int row) {
        return matrix[row];
    }

    public final AdvancedNumber[] getCol(int col) {
        final AdvancedNumber[] temp = Arrays.copyOf(matrix[0], getRows());
        for (int i = 0; i < temp.length; i++) {
            temp[i] = get(i, col);
        }
        return temp;
    }

    public final AdvancedNumber get(int row, int col) {
        return matrix[row][col];
    }

    public final Matrix set(int row, int col, AdvancedNumber value) {
        matrix[row][col] = value;
        return this;
    }

    public final int getRows() {
        return matrix.length;
    }

    public final int getCols() {
        return matrix[0].length;
    }

    public final Matrix applyFunction(MatrixFunction<AdvancedNumber> function) {
        if (function == null) {
            return this;
        }
        final int rows = getRows();
        final int cols = getCols();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                set(row, col, function.apply(row, col, get(row, col)));
            }
        }
        return this;
    }

    public final Matrix multiply(AdvancedNumber value) {
        return copy().applyFunction((row, col, value_) -> value_.multiply(value));
    }

    public final Matrix divide(AdvancedNumber value) {
        return copy().applyFunction((row, col, value_) -> value_.divide(value));
    }

    public final Matrix add(Matrix matrix_other) {
        if (!checkSameSize(this, matrix_other)) {
            throw new IllegalArgumentException("The matrices does not have the same size");
        }
        return copy().applyFunction((row, col, value) -> value.add(matrix_other.get(row, col)));
    }

    public final Matrix subtract(Matrix matrix_other) {
        if (!checkSameSize(this, matrix_other)) {
            throw new IllegalArgumentException("The matrices does not have the same size");
        }
        return copy().applyFunction((row, col, value) -> value.subtract(matrix_other.get(row, col)));
    }

    public final Matrix multiply(Matrix matrix_other) {
        if (!checkSizeForMultiplication(this, matrix_other)) {
            throw new IllegalArgumentException("The matrices does not have the right size to be multiplied together");
        }
        return new Matrix(getRows(), matrix_other.getCols(), getClazz()).applyFunction((row, col, value) -> multiply(getRow(row), matrix_other.getCol(col)));
    }

    public final Matrix transpose() {
        return new Matrix(getCols(), getRows(), getClazz()).applyFunction((row, col, value) -> get(col, row));
    }

    public final Class<? extends AdvancedNumber> getClazz() {
        return (Class<? extends AdvancedNumber>) matrix[0][0].getClass();
    }

    @Override
    public final boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (object == this) {
            return true;
        }
        if (object instanceof Matrix) {
            final Matrix matrix = (Matrix) object;
            if (!checkSameSize(this, matrix)) {
                return false;
            }
            return Arrays.deepEquals(this.matrix, matrix.matrix);
        }
        return false;
    }

    @Override
    public final String toString() {
        return Arrays.asList(matrix).stream().filter(Objects::nonNull).map((temp) -> Arrays.asList(temp).stream().filter(Objects::nonNull).map(Object::toString).collect(Collectors.joining(", ", "[", "]"))).collect(Collectors.joining(",\n ", "[", "]"));
    }

    public static final <T extends AdvancedNumber> boolean checkSameSize(Matrix matrix_1, Matrix matrix_2) {
        if (matrix_1 == null || matrix_2 == null) {
            return false;
        }
        return matrix_1.getRows() == matrix_2.getRows() && matrix_1.getCols() == matrix_2.getCols();
    }

    public static final <T extends AdvancedNumber> boolean checkSizeForMultiplication(Matrix matrix_1, Matrix matrix_2) {
        if (matrix_1 == null || matrix_2 == null) {
            return false;
        }
        return matrix_1.getCols() == matrix_2.getRows();
    }

    public static final AdvancedNumber multiply(AdvancedNumber[] vector_1, AdvancedNumber[] vector_2) {
        if (vector_1 == null || vector_2 == null) {
            throw new IllegalArgumentException("A vector must not be null");
        } else if (vector_1.length != vector_2.length) {
            throw new IllegalArgumentException("The two vectors have to be the same length");
        }
        AdvancedNumber value = vector_1[0].copy().reset();
        for (int i = 0; i < vector_1.length; i++) {
            value = value.add(vector_1[i].multiply(vector_2[i]));
        }
        return value;
    }

}
