package de.codemakers.math.expression.shuntingyard;

import de.codemakers.math.expression.function.Function;
import de.codemakers.math.expression.operator.Operator;
import de.codemakers.math.expression.tokens.Token;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * ShuntingYard
 *
 * @author Paul Hagedorn
 */
public class ShuntingYard {
    
    public static final List<Token> convertToRPN(String expression, Map<String, Function> userFunctions, Map<String, Operator> userOperators, Set<String> variableNames, boolean implicitMultiplication) {
        return null;
    }

}
