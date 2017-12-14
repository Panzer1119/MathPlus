package de.codemakers.math.matrix;

public abstract class AbstractMatrix<T> { //TODO vielleicht das hier nur "Matrix" nennen? Und dann nicht abstract machen und das einfach alle Matrizen die hier extenden? Und machen, dass das T vielleicht eine Klasse extended, die Grundrechenoperationen hat? Damit man hier schon die additionen und so weiter einbauen kann?

  private final T[][] matrix;
  
  public AbstractMatrix(T[][] matrix) {
    this.matrix = matrix;
  }

}