package de.codemakers.math.util;

/**
 * MathUtil
 *
 * @author Paul Hagedorn
 */
public class MathUtil {

    public static final char[][] PARENTHESES = new char[][]{{'(', ')'}, {'[', ']'}, {'{', '}'}};

    /**
     * Removes the outer parentheses
     *
     * @param text Text
     * @return Text
     */
    public static final String removeOuterParentheses(String text) {
        if (text == null || text.length() == 0) {
            return text;
        }
        for (char[] p : PARENTHESES) {
            if (text.charAt(0) == p[0] && text.charAt(text.length() - 1) == p[1]) {
                return text.substring(1, text.length() - 1);
            }
        }
        return text;
    }

    /**
     * Checks if the other parentheses are the same
     *
     * @param text Text
     * @return <tt>true</tt> if the outer parentheses are the same
     */
    public static final boolean outerParenthesesEquals(String text) {
        if (text == null) {
            return false;
        } else if (text.length() == 0) {
            return true;
        }
        for (char[] p : PARENTHESES) {
            if (text.charAt(0) == p[0] && text.charAt(text.length() - 1) == p[1]) {
                return true;
            }
        }
        return false;
    }

    /**
     * Parses doubles e.g. "-" to "-1.0" or "+" to "+1.0"
     *
     * @param text String
     * @return Parsed double
     */
    public static final double parseDouble(String text) {
        if (text == null) {
            return 0.0;
        }
        if (text.equals("-") || text.equals("+")) {
            text += "1";
        }
        try {
            return Double.parseDouble(text);
        } catch (Exception ex) {
            return 0.0;
        }
    }

}
