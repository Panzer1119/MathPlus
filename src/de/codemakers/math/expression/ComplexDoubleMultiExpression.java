package de.codemakers.math.expression;

import de.codemakers.math.complex.ComplexDouble;
import de.codemakers.math.expression.tokens.ComplexDoubleToken;
import de.codemakers.math.expression.tokens.FunctionToken;
import de.codemakers.math.expression.tokens.OperatorToken;
import de.codemakers.math.expression.tokens.Token;
import de.codemakers.math.expression.tokens.VariableToken;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * ComplexDoubleMultiExpression
 *
 * @author Paul Hagedorn
 */
public class ComplexDoubleMultiExpression extends AbstractMultiExpression<ComplexDouble> {

    public ComplexDoubleMultiExpression(List<Token<ComplexDouble>> tokens) {
        this(tokens, Collections.emptySet());
    }

    public ComplexDoubleMultiExpression(List<Token<ComplexDouble>> tokens, Set<String> userFunctionNames) {
        this(tokens, null, userFunctionNames);
    }

    public ComplexDoubleMultiExpression(List<Token<ComplexDouble>> tokens, Map<String, List<ComplexDouble>> variables, Set<String> userFunctionNames) {
        super(tokens, variables, userFunctionNames);
    }
    
    final ComplexDouble evaluteOne(Map<String, ComplexDouble> values) {
        final ArrayStack<ComplexDouble> output = new ArrayStack<>(ComplexDouble[]::new);
        for (int i = 0; i < tokens.size(); i++) {
            final Token<ComplexDouble> token = tokens.get(i);
            switch (token.getTokenType()) {
                case FUNCTION:
                    final FunctionToken<ComplexDouble> function = (FunctionToken<ComplexDouble>) token;
                    final int numArguments = function.getFunction().getNumArguments();
                    if (output.size() < numArguments) {
                        throw new IllegalArgumentException(String.format("Invalid number of arguments availabe for the function \"%s\"", function.getFunction().getName()));
                    }
                    final ComplexDouble[] args = new ComplexDouble[numArguments];
                    for (int j = numArguments - 1; j >= 0; j--) {
                        args[j] = output.pop();
                    }
                    output.push(function.getFunction().apply(args));
                    break;
                case OPERATOR:
                    final OperatorToken<ComplexDouble> operator = (OperatorToken<ComplexDouble>) token;
                    if (output.size() < operator.getOperator().getNumOperands()) {
                        throw new IllegalArgumentException(String.format("Invalid number of operands availabe for the operator '%s'", operator.getOperator().getSymbol()));
                    }
                    if (operator.getOperator().getNumOperands() == 2) {
                        final ComplexDouble rightArg = output.pop();
                        final ComplexDouble leftArg = output.pop();
                        output.push(operator.getOperator().apply(leftArg, rightArg));
                    } else if (operator.getOperator().getNumOperands() == 1) {
                        final ComplexDouble arg = output.pop();
                        output.push(operator.getOperator().apply(arg));
                    }
                    break;
                case VARIABLE:
                    final String name = ((VariableToken) token).getName();
                    final ComplexDouble value = values.get(name);
                    if (value == null) {
                        throw new IllegalArgumentException(String.format("No value has been set for the variable \"%s\"", name));
                    }
                    output.push(value);
                    break;
                case NUMBER:
                case COMPLEX_NUMBER:
                    output.push(((ComplexDoubleToken) token).getValue());
                    break;
                case ARGUMENT_SEPERATOR:
                case CLOSE_PARENTHESES:
                case OPEN_PARENTHESES:
                default:
                    throw new AssertionError(token.getTokenType().name());

            }
        }
        if (output.size() > 1) {
            throw new IllegalArgumentException("Invalid number of items on the output queue. Might be caused by an invalid number of arguments for a function");
        }
        return output.pop();
    }

    @Override
    final Map<String, List<ComplexDouble>> createDefaultVariables() {
        final Map<String, List<ComplexDouble>> variables = new HashMap<>(6);
        variables.put("i", Arrays.asList(ComplexDouble.ONE_I));
        variables.put("pi", Arrays.asList(ComplexDouble.PI));
        variables.put("π", Arrays.asList(ComplexDouble.PI));
        variables.put("e", Arrays.asList(ComplexDouble.E));
        variables.put("φ", Arrays.asList(ComplexDouble.GOLDEN_RATIO));
        variables.put("γ", Arrays.asList(ComplexDouble.GAMMA));
        return variables;
    }

}
