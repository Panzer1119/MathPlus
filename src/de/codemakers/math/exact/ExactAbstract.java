package de.codemakers.math.exact;

/**
 * ExactAbstract
 *
 * @author Paul Hagedorn
 */
public abstract class ExactAbstract extends Number {

    public final ExactFunction acos() {
        return new ExactFunction(Function.ACOS, this.copy());
    }

    public ExactSum add(ExactAbstract number) {
        return new ExactSum(this.copy(), number.copy());
    }

    public final ExactFunction arg() {
        return new ExactFunction(Function.ARG, this.copy());
    }

    public final ExactFunction asec() {
        return new ExactFunction(Function.ASEC, this.copy());
    }

    public final ExactFunction asin() {
        return new ExactFunction(Function.ASIN, this.copy());
    }

    public final ExactFunction atan() {
        return new ExactFunction(Function.ATAN, this.copy());
    }

    public final ExactFunction ceil() {
        return new ExactFunction(Function.CEIL, this.copy());
    }

    public abstract ExactAbstract copy();

    public final ExactFunction cos() {
        return new ExactFunction(Function.COS, this.copy());
    }

    public final ExactFunction cosh() {
        return new ExactFunction(Function.COSH, this.copy());
    }

    public final ExactFunction cot() {
        return new ExactFunction(Function.COT, this.copy());
    }

    public ExactAbstract divide(ExactAbstract number) {
        return new ExactFraction(this.copy(), number.copy());
    }

    public final ExactPower exp() {
        return new ExactPower(ExactNumber.E, this.copy());
    }

    public final ExactPower expm1() {
        return new ExactPower(ExactNumber.E, this.copy());
    }

    public final ExactFunction floor() {
        return new ExactFunction(Function.FLOOR, this.copy());
    }

    public ExactAbstract inverse() {
        return new ExactFraction(ExactNumber.ONE, this.copy());
    }

    public final ExactFunction norm() {
        return new ExactFunction(Function.NORM, this.copy());
    }

    public final ExactFunction normSquared() {
        return new ExactFunction(Function.NORM_SQUARED, this.copy());
    }

    public final ExactAbstract log() {
        return log(ExactNumber.E);
    }

    public ExactAbstract log(ExactAbstract number) {
        return new ExactLogarithm(number.copy(), this.copy());
    }

    public final ExactAbstract log(double number) {
        return log(new ExactNumber(number));
    }

    public final ExactAbstract log(int number) {
        return log(new ExactNumber(number));
    }

    public final ExactAbstract log10() {
        return log(ExactNumber.TEN);
    }

    public final ExactAbstract log1p() {
        return log(ExactNumber.TEN);
    }

    public final ExactAbstract log2() {
        return log(ExactNumber.TWO);
    }

    public final ExactFunction mod(ExactAbstract number) {
        return new ExactFunction(Function.MOD, number.copy());
    }

    public ExactAbstract multiply(ExactAbstract number) {
        return new ExactProduct(this.copy(), number.copy());
    }

    public final ExactAbstract negate() {
        return new ExactProduct(ExactNumber.MINUS_ONE, this.copy());
    }

    public ExactPower pow(ExactAbstract number) {
        return new ExactPower(this.copy(), number.copy());
    }

    public final ExactPower pow(double number) {
        return pow(new ExactNumber(number));
    }

    public final ExactPower pow(int number) {
        return pow(new ExactNumber(number));
    }

    public abstract ExactAbstract reset();

    public ExactRoot root(ExactAbstract number) {
        return new ExactRoot(number.copy(), this.copy());
    }

    public final ExactRoot root(double number) {
        return root(new ExactNumber(number));
    }

    public final ExactRoot root(int number) {
        return root(new ExactNumber(number));
    }

    public final ExactFunction sec() {
        return new ExactFunction(Function.SEC, this.copy());
    }

    public final ExactFunction sgn() {
        return new ExactFunction(Function.SGN, this.copy());
    }

    public final ExactFunction sin() {
        return new ExactFunction(Function.SIN, this.copy());
    }

    public final ExactFunction sinh() {
        return new ExactFunction(Function.SINH, this.copy());
    }

    public final ExactRoot sqrt() {
        return root(ExactNumber.TWO);
    }

    public ExactSum subtract(ExactAbstract number) {
        return new ExactSum(this.copy(), number.negate());
    }

    public final ExactFunction tan() {
        return new ExactFunction(Function.TAN, this.copy());
    }

    public final ExactFunction tanh() {
        return new ExactFunction(Function.TANH, this.copy());
    }

    public abstract String toLaTeXString(Object... options);

    public boolean equals(ExactAbstract number) {
        if (number == null) {
            return false;
        }
        if (number == this) {
            return true;
        }
        return doubleValue() == number.doubleValue();
    }

}
