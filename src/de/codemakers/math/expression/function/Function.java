package de.codemakers.math.expression.function;

import org.nevec.rjm.BigComplex;

/**
 * Function
 *
 * @author Paul Hagedorn
 */
public abstract class Function {

    protected final String name;
    protected final int numArguments;
    
    public Function(String name) {
        this(name, 1);
    }

    public Function(String name, int numArguments) {
        if (numArguments < 0) {
            throw new IllegalArgumentException(String.format("The number of function arguments can not be less than 0 for \"%s\"", name));
        }
        if (!isValidFunctionName(name)) {
            throw new IllegalArgumentException(String.format("The function name \"%s\" is invalid", name));
        }
        this.name = name;
        this.numArguments = numArguments;
    }

    public final String getName() {
        return name;
    }
    
    public final int getNumArguments() {
        return numArguments;
    }
    
    public abstract BigComplex apply(BigComplex... args);
    
    public static final boolean isValidFunctionName(String name) {
        if (name == null) {
            return false;
        }
        final int size = name.length();
        if (size == 0) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            final char c = name.charAt(i);
            if (!(Character.isLetter(c) || c == '_') && !(Character.isDigit(c) && i > 0)) {
                return false;
            }
        }
        return true;
    }
    
}
