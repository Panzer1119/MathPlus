package de.codemakers.math;

/**
 * AdvancedNumber
 *
 * @author Paul Hagedorn
 */
public abstract class AdvancedNumber extends Number {

    public abstract AdvancedNumber copy();

    public abstract AdvancedNumber add(AdvancedNumber number);

    public abstract AdvancedNumber subtract(AdvancedNumber number);

    public abstract AdvancedNumber multiply(AdvancedNumber number);

    public abstract AdvancedNumber divide(AdvancedNumber number);

    public abstract AdvancedNumber modulo(AdvancedNumber number);

    public abstract AdvancedNumber pow(AdvancedNumber number);

    public abstract AdvancedNumber log();

    public abstract AdvancedNumber log10();

    public abstract AdvancedNumber reset();

    public abstract boolean equals(AdvancedNumber number);

    public abstract boolean isCompatible(AdvancedNumber number);

    public final void check(AdvancedNumber number) {
        if (!isCompatible(number)) {
            throw new IllegalArgumentException("Incompatible number types");
        }
    }

}
