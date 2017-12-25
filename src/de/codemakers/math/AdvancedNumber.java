package de.codemakers.math;

/**
 * AdvancedNumber
 *
 * @author Paul Hagedorn
 */
public abstract class AdvancedNumber extends Number {

    public abstract AdvancedNumber acos();

    public abstract AdvancedNumber add(AdvancedNumber number);

    public abstract AdvancedNumber arg();

    public abstract AdvancedNumber asec();

    public abstract AdvancedNumber asin();

    public abstract AdvancedNumber atan();

    public abstract AdvancedNumber ceil();

    public abstract AdvancedNumber copy();

    public abstract AdvancedNumber cos();

    public abstract AdvancedNumber cosh();

    public abstract AdvancedNumber cot();

    public abstract AdvancedNumber divide(AdvancedNumber number);

    public abstract AdvancedNumber exp();

    public abstract AdvancedNumber expm1();

    public abstract AdvancedNumber floor();

    public abstract AdvancedNumber inverse();

    public abstract AdvancedNumber norm();

    public abstract AdvancedNumber normSquared();

    public abstract AdvancedNumber log();

    public abstract AdvancedNumber log(AdvancedNumber number);

    public abstract AdvancedNumber log(double number);

    public abstract AdvancedNumber log(int number);

    public abstract AdvancedNumber log10();

    public abstract AdvancedNumber log1p();

    public abstract AdvancedNumber log2();

    public abstract AdvancedNumber mod(AdvancedNumber number);

    public abstract AdvancedNumber multiply(AdvancedNumber number);

    public abstract AdvancedNumber negate();

    public abstract AdvancedNumber pow(AdvancedNumber number);

    public abstract AdvancedNumber pow(double number);

    public abstract AdvancedNumber pow(int number);

    public abstract AdvancedNumber reset();

    public abstract AdvancedNumber root(AdvancedNumber number);

    public abstract AdvancedNumber root(double number);

    public abstract AdvancedNumber root(int number);

    public abstract AdvancedNumber sec();

    public abstract AdvancedNumber sgn();

    public abstract AdvancedNumber sin();

    public abstract AdvancedNumber sinh();

    public abstract AdvancedNumber sqrt();

    public abstract AdvancedNumber subtract(AdvancedNumber number);

    public abstract AdvancedNumber tan();

    public abstract AdvancedNumber tanh();

    public abstract boolean equals(AdvancedNumber number);

    public abstract boolean isCompatible(AdvancedNumber number);

    public final void check(AdvancedNumber number) {
        if (!isCompatible(number)) {
            throw new IllegalArgumentException("Incompatible number types");
        }
    }

}
