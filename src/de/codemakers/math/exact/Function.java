package de.codemakers.math.exact;

/**
 * Function
 *
 * @author Paul Hagedorn
 */
public enum Function {

    ACOS("\\arccos"),
    ARG("\\arg"),
    ASEC("\\arcsec"),
    ASIN("\\arcsin"),
    ATAN("\\arctan"),
    CEIL("\\ceil"),
    COS("\\cos"),
    COSH("\\cosh"),
    COT("\\cot"),
    FLOOR("\\floor"),
    MOD("\\mod"),
    NORM("\\norm"),
    NORM_SQUARED("\\norm^2"),
    SEC("\\sec"),
    SGN("\\sgn"),
    SIN("\\sin"),
    SINH("\\sinh"),
    TAN("\\tan"),
    TANH("\\tanh");

    private final String name_tex;

    private Function(String name_tex) {
        this.name_tex = name_tex;
    }

    public final String getLaTeXName() {
        return name_tex;
    }

    public final String getNormalName() {
        return name().toLowerCase();
    }

}
