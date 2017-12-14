package de.codemakers.math.matrix;

public class MatrixDouble {

  private final double[][] matrix; //Zeilen zuerst, Spalten spaeter
  
  public MatrixDouble(double[][] matrix) {
    if (matrix.length == 0 || matrix[0].length == 0) {
      throw new InvalidArgumentException(); //TODO richtige Exception suchen, oder eine selber machen?
    }
    this.matrix = matrix;
  }
  
  public final double[][] getMatrix() {
    return matrix;
  }
  
  public final double[] getRow(int row) {
    return matrix[row];
  }
  
  public final double[] getCol(int col) {
    final double[] temp = new double[getCols()];
    for (int i = 0; i < temp.length; i++) {
      temp[i] = get(i, col);
    }
    return temp;
  }
  
  public final double get(int row, int col) {
    return matrix[row][col];
  }
  
  public final MatrixDouble set(int row, int col, double value) {
    matrix[row][col] = value;
    return this;
  }
  
  public final int getRows() {
    return matrix.length;
  }
  
  public final int getCols() {
    return matrix[0].length;
  }

}