package de.codemakers.math.expression.shuntingyard;

import de.codemakers.math.complex.ComplexDouble;
import de.codemakers.math.expression.function.AbstractFunction;
import de.codemakers.math.expression.operator.AbstractOperator;
import de.codemakers.math.expression.tokens.Token;
import de.codemakers.math.expression.tokens.ComplexDoubleTokenizer;
import de.codemakers.math.expression.tokens.OperatorToken;
import de.codemakers.math.expression.tokens.TokenType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * ShuntingYard
 *
 * @author Paul Hagedorn
 */
public class ShuntingYard {

    public static final List<Token<ComplexDouble>> convertToComplexDoubleRPN(String expression, Map<String, AbstractFunction<ComplexDouble>> userFunctions, Map<String, AbstractOperator<ComplexDouble>> userOperators, Set<String> variableNames, boolean implicitMultiplication) {
        final Stack<Token<ComplexDouble>> stack = new Stack<>();
        final List<Token<ComplexDouble>> output = new ArrayList<>();
        final ComplexDoubleTokenizer tokenizer = new ComplexDoubleTokenizer(expression, userFunctions, userOperators, variableNames, implicitMultiplication);
        while (tokenizer.hasNext()) {
            final Token<ComplexDouble> token = tokenizer.nextToken();
            switch (token.getTokenType()) {
                case ARGUMENT_SEPERATOR:
                    while (!stack.isEmpty() && stack.peek().getTokenType() != TokenType.OPEN_PARENTHESES) {
                        output.add(stack.pop());
                    }
                    if (stack.isEmpty() || stack.peek().getTokenType() != TokenType.OPEN_PARENTHESES) {
                        throw new IllegalArgumentException("Misplaced function seperator ',' or mismatched parentheses");
                    }
                    break;
                case CLOSE_PARENTHESES:
                    while (stack.peek().getTokenType() != TokenType.OPEN_PARENTHESES) {
                        output.add(stack.pop());
                    }
                    stack.pop();
                    if (!stack.isEmpty() && stack.peek().getTokenType() == TokenType.FUNCTION) {
                        output.add(stack.pop());
                    }
                    break;
                case FUNCTION:
                    stack.add(token);
                    break;
                case OPEN_PARENTHESES:
                    stack.push(token);
                    break;
                case OPERATOR:
                    while (!stack.isEmpty() && stack.peek().getTokenType() == TokenType.OPERATOR) {
                        final OperatorToken<ComplexDouble> op_1 = (OperatorToken<ComplexDouble>) token;
                        final OperatorToken<ComplexDouble> op_2 = (OperatorToken<ComplexDouble>) stack.peek();
                        if (op_1.getOperator().getNumOperands() == 1 && op_2.getOperator().getNumOperands() == 2) {
                            break;
                        } else if ((op_1.getOperator().isLeftAssociative() && op_1.getOperator().getPrecedence() <= op_2.getOperator().getPrecedence()) || (op_1.getOperator().getPrecedence() < op_2.getOperator().getPrecedence())) {
                            output.add(stack.pop());
                        } else {
                            break;
                        }
                    }
                    stack.push(token);
                    break;
                case NUMBER:
                case VARIABLE:
                case COMPLEX_NUMBER:
                    output.add(token);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown TokenType encountered. This should not happen");
            }
        }
        while (!stack.isEmpty()) {
            final Token<ComplexDouble> token = stack.pop();
            if (token.getTokenType() == TokenType.CLOSE_PARENTHESES || token.getTokenType() == TokenType.OPEN_PARENTHESES) {
                throw new IllegalArgumentException("Mismatched parentheses detected. Please check the expression");
            } else {
                output.add(token);
            }
        }
        return output;
    }

}
