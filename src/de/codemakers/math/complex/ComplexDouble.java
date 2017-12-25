package de.codemakers.math.complex;

import de.codemakers.math.AdvancedDouble;
import de.codemakers.math.AdvancedNumber;
import de.codemakers.math.util.MathUtil;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ComplexDouble
 *
 * @author Paul Hagedorn
 */
public class ComplexDouble extends AdvancedNumber {

    public static final Pattern COMPLEX_NUMBER_PATTERN = Pattern.compile("((?:\\+|-)?(?:\\d+(?:.|,))?\\d+)((?:\\+|-)(?:\\d+(?:.|,))?\\d*)i");
    public static final Pattern REAL_NUMBER_PATTERN = Pattern.compile("((?:\\+|-)?(?:\\d+(?:.|,))?\\d+)");
    public static final Pattern IMAGINARY_NUMBER_PATTERN = Pattern.compile("((?:\\+|-)?(?:\\d+(?:.|,))?\\d*)i");

    public static final ComplexDouble MINUS_TEN = ofDouble(-10), MINUS_TEN_I = ofDouble(0, -10), MINUS_TEN_MINUS_TEN_I = ofDouble(-10, -10);
    public static final ComplexDouble MINUS_FIVE = ofDouble(-5), MINUS_FIVE_I = ofDouble(0, -5), MINUS_FIVE_MINUS_FIVE_I = ofDouble(-5, -5);
    public static final ComplexDouble MINUS_PI = ofDouble(-Math.PI), MINUS_PI_I = ofDouble(0, -Math.PI), MINUS_PI_MINUS_PI_I = ofDouble(-Math.PI, -Math.PI);
    public static final ComplexDouble MINUS_THREE = ofDouble(-3), MINUS_THREE_I = ofDouble(0, -3), MINUS_THREE_MINUS_THREE_I = ofDouble(-3, -3);
    public static final ComplexDouble MINUS_E = ofDouble(-Math.E), MINUS_E_I = ofDouble(0, -Math.E), MINUS_E_MINUS_E_I = ofDouble(-Math.E, -Math.E);
    public static final ComplexDouble MINUS_TWO = ofDouble(-2), MINUS_TWO_I = ofDouble(0, -2), MINUS_TWO_MINUS_TWO_I = ofDouble(-2, -2);
    public static final ComplexDouble MINUS_ONE = ofDouble(-1), MINUS_ONE_I = ofDouble(0, -1), MINUS_ONE_MINUS_ONE_I = ofDouble(-1, -1);
    public static final ComplexDouble MINUS_HALF = ofDouble(-0.5), MINUS_HALF_I = ofDouble(0, -0.5), MINUS_HALF_MINUS_HALF_I = ofDouble(-0.5, -0.5);
    public static final ComplexDouble MINUS_ZERO = ofDouble(-0), MINUS_ZERO_I = ofDouble(-0, -0), MINUS_ZERO_MINUS_ZERO_I = ofDouble(-0, -0);
    public static final ComplexDouble ZERO = ofDouble(0), ZERO_I = ofDouble(0, 0), ZERO_ZERO_I = ofDouble(0, 0);
    public static final ComplexDouble HALF = ofDouble(0.5), HALF_I = ofDouble(0, 0.5), HALF_HALF_I = ofDouble(0.5, 0.5);
    public static final ComplexDouble ONE = ofDouble(1), ONE_I = ofDouble(0, 1), ONE_ONE_I = ofDouble(1, 1);
    public static final ComplexDouble TWO = ofDouble(2), TWO_I = ofDouble(0, 2), TWO_TWO_I = ofDouble(2, 2);
    public static final ComplexDouble E = ofDouble(Math.E), E_I = ofDouble(0, Math.E), E_E_I = ofDouble(Math.E, Math.E);
    public static final ComplexDouble THREE = ofDouble(3), THREE_I = ofDouble(0, 3), THREE_THREE_I = ofDouble(3, 3);
    public static final ComplexDouble PI = ofDouble(Math.PI), PI_I = ofDouble(0, Math.PI), PI_PI_I = ofDouble(Math.PI, Math.PI);
    public static final ComplexDouble FIVE = ofDouble(5), FIVE_I = ofDouble(0, 5), FIVE_FIVE_I = ofDouble(5, 5);
    public static final ComplexDouble TEN = ofDouble(10), TEN_I = ofDouble(0, 10), TEN_TEN_I = ofDouble(10, 10);

    public static final ComplexDouble GOLDEN_RATIO = ofDouble(1.6180339887498948482045868343656);
    public static final ComplexDouble GAMMA = ofDouble(0.577215664901532860606512090082402431);

    private double real_part;
    private double imaginary_part;

    /**
     * Constructs a ComplexDouble
     *
     * @param real_part Real part
     * @param imaginary_part Imaginary part
     */
    public ComplexDouble(double real_part, double imaginary_part) {
        this.real_part = real_part;
        this.imaginary_part = imaginary_part;
    }

    /**
     * Returns the real part
     *
     * @return Real part of the number
     */
    public final double getRealPart() {
        return real_part;
    }

    /**
     * Sets the real part
     *
     * @param real_part Real part of the number
     * @return A reference to this ComplexDouble
     */
    public final ComplexDouble setRealPart(double real_part) {
        this.real_part = real_part;
        return this;
    }

    /**
     * Returns the imaginary part
     *
     * @return Imaginary part of the number
     */
    public final double getImaginaryPart() {
        return imaginary_part;
    }

    /**
     * Returns if this ComplexDouble is a real number
     *
     * @return <tt>true</tt> if this ComplexDouble is a real number
     */
    public final boolean isReal() {
        return imaginary_part == 0.0;
    }

    /**
     * Returns if this ComplexDouble is a imaginary number
     *
     * @return <tt>true</tt> if this ComplexDouble is a imaginary number
     */
    public final boolean isImaginary() {
        return real_part == 0.0;
    }

    /**
     * Sets the imaginary part
     *
     * @param imaginary_part Imaginary part of the number
     * @return A reference to this ComplexDouble
     */
    public final ComplexDouble setImaginaryPart(double imaginary_part) {
        this.imaginary_part = imaginary_part;
        return this;
    }

    /**
     * Returns a copy of this ComplexDouble
     *
     * @return Copy of this ComplexDouble
     */
    public final ComplexDouble copy() {
        return new ComplexDouble(real_part, imaginary_part);
    }

    /**
     * Returns this ComplexDouble negated
     *
     * @return A new ComplexDouble
     */
    public final ComplexDouble negate() {
        return multiply(MINUS_ONE);
    }

    /**
     * Adds a ComplexDouble to this
     *
     * @param complexDouble Complex double to add
     * @return A new ComplexDouble
     */
    public final ComplexDouble add(ComplexDouble complexDouble) {
        return new ComplexDouble(real_part + complexDouble.real_part, imaginary_part + complexDouble.imaginary_part);
    }

    /**
     * Subtracts a ComplexDouble from this
     *
     * @param complexDouble Complex double to subtract
     * @return A new ComplexDouble
     */
    public final ComplexDouble subtract(ComplexDouble complexDouble) {
        return new ComplexDouble(real_part - complexDouble.real_part, imaginary_part - complexDouble.imaginary_part);
    }

    /**
     * Multiplies a ComplexDouble to this
     *
     * @param complexDouble Complex double to multiply
     * @return A new ComplexDouble
     */
    public final ComplexDouble multiply(ComplexDouble complexDouble) {
        return new ComplexDouble((real_part * complexDouble.real_part) - (imaginary_part * complexDouble.imaginary_part), (real_part * complexDouble.imaginary_part) + (imaginary_part * complexDouble.real_part));
    }

    /**
     * Divides a ComplexDouble from this
     *
     * @param complexDouble Complex double to divide
     * @return A new ComplexDouble
     */
    public final ComplexDouble divide(ComplexDouble complexDouble) {
        return new ComplexDouble(((real_part * complexDouble.real_part) + (imaginary_part * complexDouble.imaginary_part)) / complexDouble.getNormSquared(), ((imaginary_part * complexDouble.real_part) - (real_part * complexDouble.imaginary_part)) / complexDouble.getNormSquared());
    }

    /**
     * Returns the sine of this ComplexDouble
     *
     * @return A new ComplexDouble
     */
    public final ComplexDouble sin() {
        final ComplexDouble this_i = multiply(ONE_I);
        return this_i.exp().subtract(this_i.negate().exp()).divide(TWO_I);
    }

    /**
     * Returns the hyperbolic sine of this ComplexDouble
     *
     * @return A new ComplexDouble
     */
    public final ComplexDouble sinh() {
        return exp().subtract(negate().exp()).divide(TWO);
    }

    /**
     * Returns the cosine of this ComplexDouble
     *
     * @return A new ComplexDouble
     */
    public final ComplexDouble cos() {
        final ComplexDouble this_i = multiply(ONE_I);
        return this_i.exp().add(this_i.negate().exp()).divide(TWO);
    }

    /**
     * Returns the hyperbolic cosine of this ComplexDouble
     *
     * @return A new ComplexDouble
     */
    public final ComplexDouble cosh() {
        return exp().add(negate().exp()).divide(TWO);
    }

    /**
     * Returns the tangent of this ComplexDouble
     *
     * @return A new ComplexDouble
     */
    public final ComplexDouble tan() {
        final ComplexDouble this_temp = multiply(ONE_I).multiply(TWO).exp();
        return this_temp.subtract(ONE).divide(ONE_I.multiply(this_temp.add(ONE)));
    }

    /**
     * Returns the hyperbolic tangent of this ComplexDouble
     *
     * @return A new ComplexDouble
     */
    public final ComplexDouble tanh() {
        final ComplexDouble this_1 = exp();
        final ComplexDouble this_2 = negate().exp();
        return this_1.subtract(this_2).divide(this_1.add(this_2));
    }

    /**
     * Returns the cotangent of this ComplexDouble
     *
     * @return A new ComplexDouble
     */
    public final ComplexDouble cot() {
        final ComplexDouble this_temp = multiply(ONE_I).multiply(TWO).exp();
        return ONE_I.multiply(this_temp.add(ONE)).divide(this_temp.subtract(ONE));
    }

    @Override
    public final AdvancedNumber root(AdvancedNumber number) {
        return pow(number.inverse());
    }

    @Override
    public final AdvancedNumber root(double number) {
        return root(ofDouble(number));
    }

    @Override
    public final AdvancedNumber root(int number) {
        return root(ofInt(number));
    }

    @Override
    public final AdvancedNumber sqrt() {
        return root(TWO);
    }

    /**
     * Returns the secant of this ComplexDouble
     *
     * @return A new ComplexDouble
     */
    public final ComplexDouble sec() {
        final ComplexDouble this_i = multiply(ONE_I);
        return TWO.divide(this_i.exp().add(this_i.negate().exp()));
    }

    /**
     * Returns the inverse sine of this ComplexDouble
     *
     * @return A new ComplexDouble
     */
    public final ComplexDouble asin() {
        return MINUS_ONE_I.multiply(multiply(ONE_I).add(ONE.subtract(pow(TWO)).pow(HALF)).log());
    }

    /**
     * Returns the inverse cosine of this ComplexDouble
     *
     * @return A new ComplexDouble
     */
    public final ComplexDouble acos() {
        return PI.multiply(HALF).add(ONE_I.multiply(multiply(ONE_I).add(ONE.subtract(pow(TWO)).pow(HALF)).log()));
    }

    /**
     * Returns the inverse tangent of this ComplexDouble
     *
     * @return A new ComplexDouble
     */
    public final ComplexDouble atan() {
        final ComplexDouble this_i = multiply(ONE_I);
        return HALF_I.multiply(ONE.subtract(this_i).log().subtract(ONE.add(this_i).log()));
    }

    /**
     * Returns the inverse secant of this ComplexDouble
     *
     * @return A new ComplexDouble
     */
    public final ComplexDouble asec() {
        //return PI.multiply(HALF).add(ONE_I.multiply(ONE.subtract(ONE.divide(pow(TWO))).pow(HALF).add(ONE_I.divide(this))).log());
        return ONE.divide(this).acos();
    }

    /**
     * Returns the inverse tangent of this and another ComplexDouble
     *
     * @param complexDouble Second ComplexDouble
     * @return A new ComplexDouble
     */
    private final ComplexDouble atan2(ComplexDouble complexDouble) {
        return MINUS_ONE_I.multiply(complexDouble.divide(complexDouble));
    }

    /**
     * Returns the length (indirectly) squared
     *
     * @return Length of this ComplexDouble (squared)
     */
    public final double getNormSquared() {
        return (real_part * real_part) + (imaginary_part * imaginary_part);
    }

    /**
     * Returns the length
     *
     * @return Length of this ComplexDouble
     */
    public final double getNorm() {
        return Math.sqrt(getNormSquared());
    }

    /**
     * Returns the complex argument
     *
     * @return Complex argument of this ComplexDouble
     */
    public final AdvancedDouble arg() {
        return new AdvancedDouble(Math.atan2(imaginary_part, real_part));
    }

    /**
     * Returns the modulo
     *
     * @param complexDouble ComplexDouble
     * @return The remainder
     */
    public final ComplexDouble mod(ComplexDouble complexDouble) {
        return ofDouble(getNorm() % complexDouble.getNorm());
    }

    /**
     * Takes this ComplexDouble to the power of a real integer
     *
     * @param n Real power
     * @return A new ComplexDouble
     */
    public final ComplexDouble pow(int n) {
        return pow(ofInt(n));
    }

    /**
     * Takes this ComplexDouble to the power of a real double
     *
     * @param r Real power
     * @return A new ComplexDouble
     */
    public final ComplexDouble pow(double r) {
        return pow(ofDouble(r));
    }

    /**
     * Takes this ComplexDouble to the power of another ComplexDouble
     *
     * @param complexDouble Power
     * @return A new ComplexDouble
     */
    public final ComplexDouble pow(ComplexDouble complexDouble) {
        return new ComplexDouble(Math.cos((complexDouble.real_part * arg().doubleValue()) + (0.5 * complexDouble.imaginary_part * Math.log(getNormSquared()))), Math.sin((complexDouble.real_part * arg().doubleValue()) + (0.5 * complexDouble.imaginary_part * Math.log(getNormSquared())))).multiply(ofDouble(Math.pow(getNormSquared(), complexDouble.real_part * 0.5))).multiply(ofDouble(Math.pow(Math.E, -1.0 * complexDouble.imaginary_part * arg().doubleValue())));
    }

    /**
     * Takes "e" to the power of this ComplexDouble
     *
     * @return A new ComplexDouble
     */
    public final ComplexDouble exp() {
        return new ComplexDouble(Math.cos(imaginary_part), Math.sin(imaginary_part)).multiply(ofDouble(Math.pow(Math.E, real_part)));
    }

    /**
     * Takes "e" to the power of this ComplexDouble
     *
     * @return A new ComplexDouble
     */
    public final ComplexDouble expm1() {
        return new ComplexDouble(Math.cos(imaginary_part), Math.sin(imaginary_part)).multiply(ofDouble(Math.pow(Math.E, real_part)));
    }

    /**
     * Returns the natural logarithm of this ComplexDouble
     *
     * @return A new ComplexDouble
     */
    public final ComplexDouble log() {
        return new ComplexDouble(Math.log(getNorm()), arg().doubleValue());
    }

    /**
     * Returns the logarithm for a complex base
     *
     * @param base Complex base
     * @return A new ComplexDouble
     */
    public final ComplexDouble log(ComplexDouble base) {
        return log().divide(base.log());
    }

    /**
     * Returns the logarithm for the base 2
     *
     * @return A new ComplexDouble
     */
    public final ComplexDouble log2() {
        return log().divide(TWO.log());
    }

    /**
     * Returns the logarithm for the base 10
     *
     * @return A new ComplexDouble
     */
    public final ComplexDouble log10() {
        return log().divide(TEN.log());
    }

    /**
     * Returns the logarithm for the base 10
     *
     * @return A new ComplexDouble
     */
    public final ComplexDouble log1p() {
        return add(ONE).log();
    }

    /**
     * Returns the floored ComplexDouble
     *
     * @return A new ComplexDouble
     */
    public final ComplexDouble floor() {
        return new ComplexDouble(Math.floor(real_part), Math.floor(imaginary_part));
    }

    /**
     * Returns the ceiled ComplexDouble
     *
     * @return A new ComplexDouble
     */
    public final ComplexDouble ceil() {
        return new ComplexDouble(Math.ceil(real_part), Math.ceil(imaginary_part));
    }

    /**
     * Returns the signum of the ComplexDouble
     *
     * @return A new ComplexDouble
     */
    public final ComplexDouble sgn() {
        if (getNormSquared() == 0) {
            return ZERO;
        }
        return copy().divide(ofDouble(getNorm()));
    }

    @Override
    public final ComplexDouble add(AdvancedNumber number) {
        number = correct(number);
        return add((ComplexDouble) number);
    }

    @Override
    public final ComplexDouble divide(AdvancedNumber number) {
        number = correct(number);
        return divide((ComplexDouble) number);
    }

    @Override
    public final ComplexDouble inverse() {
        return new ComplexDouble(real_part / getNormSquared(), -imaginary_part / getNormSquared());
    }

    @Override
    public final AdvancedDouble norm() {
        return new AdvancedDouble(getNorm());
    }

    @Override
    public final AdvancedDouble normSquared() {
        return new AdvancedDouble(getNormSquared());
    }

    @Override
    public final ComplexDouble log(AdvancedNumber number) {
        number = correct(number);
        return log((ComplexDouble) number);
    }

    @Override
    public final AdvancedNumber log(double number) {
        return log(new ComplexDouble(number, 0));
    }

    @Override
    public final AdvancedNumber log(int number) {
        return log(new ComplexDouble(number, 0));
    }

    @Override
    public final ComplexDouble mod(AdvancedNumber number) {
        number = correct(number);
        return mod((ComplexDouble) number);
    }

    @Override
    public final ComplexDouble multiply(AdvancedNumber number) {
        number = correct(number);
        return multiply((ComplexDouble) number);
    }

    @Override
    public final ComplexDouble pow(AdvancedNumber number) {
        number = correct(number);
        return pow((ComplexDouble) number);
    }

    @Override
    public final ComplexDouble reset() {
        real_part = 0;
        imaginary_part = 0;
        return this;
    }

    @Override
    public final ComplexDouble subtract(AdvancedNumber number) {
        number = correct(number);
        return subtract((ComplexDouble) number);
    }

    private final ComplexDouble correct(AdvancedNumber number) {
        if (number instanceof AdvancedDouble) {
            return new ComplexDouble(((AdvancedDouble) number).doubleValue(), 0);
        }
        return (ComplexDouble) number;
    }

    @Override
    public final boolean equals(AdvancedNumber number) {
        if (number == null) {
            return false;
        }
        if (number == this) {
            return true;
        }
        check(number);
        number = correct(number);
        final ComplexDouble number_ = (ComplexDouble) number;
        return real_part == number_.real_part && imaginary_part == number_.imaginary_part;
    }

    @Override
    public final boolean isCompatible(AdvancedNumber number) {
        return number != null && (number instanceof ComplexDouble || number instanceof AdvancedDouble);
    }

    @Override
    public final int intValue() {
        return (int) getNorm();
    }

    @Override
    public final long longValue() {
        return (long) getNorm();
    }

    @Override
    public final float floatValue() {
        return (float) getNorm();
    }

    @Override
    public final double doubleValue() {
        return (double) getNorm();
    }

    /**
     * Returns the "toString()"
     *
     * @return String represenation of this ComplexDouble
     */
    @Override
    public final String toString() {
        if (real_part != 0.0 && imaginary_part != 0.0) {
            return toCompleteString();
        } else if (real_part != 0.0) {
            return real_part + "";
        } else if (imaginary_part != 0.0) {
            return imaginary_part + "i";
        } else {
            return "";
        }
    }

    public final String toCompleteString() {
        return String.format(Locale.ENGLISH, "(%f %s %fi)", real_part, ((imaginary_part < 0.0) ? "-" : "+"), Math.abs(imaginary_part));
    }

    /**
     * Returns a ComplexDouble of a String
     *
     * @param text ComplexDouble as String
     * @return A new ComplexDouble
     */
    public static final ComplexDouble ofString(String text) {
        text = MathUtil.removeOuterParentheses(text.trim().replaceAll("\\s", ""));
        Matcher matcher = COMPLEX_NUMBER_PATTERN.matcher(text);
        if (!matcher.matches()) {
            matcher = IMAGINARY_NUMBER_PATTERN.matcher(text);
            if (!matcher.matches()) {
                matcher = REAL_NUMBER_PATTERN.matcher(text);
                if (!matcher.matches()) {
                    return null;
                }
                try {
                    return new ComplexDouble(MathUtil.parseDouble(matcher.group(1)), 0.0);
                } catch (Exception ex) {
                    return null;
                }
            }
            try {
                return new ComplexDouble(0.0, MathUtil.parseDouble(matcher.group(1)));
            } catch (Exception ex) {
                return null;
            }
        }
        try {
            return new ComplexDouble(MathUtil.parseDouble(matcher.group(1)), MathUtil.parseDouble(matcher.group(2)));
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * Returns a ComplexDouble of one integer
     *
     * @param real_part Real part
     * @return A new ComplexDouble
     */
    public static final ComplexDouble ofInt(int real_part) {
        return ofInt(real_part, 0);
    }

    /**
     * Returns a ComplexDouble of two integers
     *
     * @param real_part Real part
     * @param imaginary_part Imaginary part
     * @return A new ComplexDouble
     */
    public static final ComplexDouble ofInt(int real_part, int imaginary_part) {
        return new ComplexDouble(real_part, imaginary_part);
    }

    /**
     * Returns a ComplexDouble of one long
     *
     * @param real_part Real part
     * @return A new ComplexDouble
     */
    public static final ComplexDouble ofLong(long real_part) {
        return ofLong(real_part, 0L);
    }

    /**
     * Returns a ComplexDouble of two longs
     *
     * @param real_part Real part
     * @param imaginary_part Imaginary part
     * @return A new ComplexDouble
     */
    public static final ComplexDouble ofLong(long real_part, long imaginary_part) {
        return new ComplexDouble(real_part, imaginary_part);
    }

    /**
     * Returns a ComplexDouble of one float
     *
     * @param real_part Real part
     * @return A new ComplexDouble
     */
    public static final ComplexDouble ofFloat(float real_part) {
        return ofFloat(real_part, 0.0F);
    }

    /**
     * Returns a ComplexDouble of two floats
     *
     * @param real_part Real part
     * @param imaginary_part Imaginary part
     * @return A new ComplexDouble
     */
    public static final ComplexDouble ofFloat(float real_part, float imaginary_part) {
        return new ComplexDouble(real_part, imaginary_part);
    }

    /**
     * Returns a ComplexDouble of one double
     *
     * @param real_part Real part
     * @return A new ComplexDouble
     */
    public static final ComplexDouble ofDouble(double real_part) {
        return ofDouble(real_part, 0.0);
    }

    /**
     * Returns a ComplexDouble of two doubles
     *
     * @param real_part Real part
     * @param imaginary_part Imaginary part
     * @return A new ComplexDouble
     */
    public static final ComplexDouble ofDouble(double real_part, double imaginary_part) {
        return new ComplexDouble(real_part, imaginary_part);
    }

}
