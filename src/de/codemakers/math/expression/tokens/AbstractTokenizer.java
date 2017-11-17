package de.codemakers.math.expression.tokens;

import de.codemakers.math.expression.function.AbstractFunction;
import de.codemakers.math.expression.operator.AbstractOperator;
import java.util.Map;
import java.util.Set;

/**
 * AbstractTokenizer
 *
 * @author Paul Hagedorn
 */
public abstract class AbstractTokenizer<T> {

    private final char[] expression;
    private final int expressionLength;
    private final Map<String, AbstractFunction<T>> userFunctions;
    private final Map<String, AbstractOperator<T>> userOperators;
    private final Set<String> variableNames;
    private final boolean implicitMultiplication;
    private int pos = 0;
    private Token<T> lastToken;

    public AbstractTokenizer(String expression, Map<String, AbstractFunction<T>> userFunctions, Map<String, AbstractOperator<T>> userOperators, Set<String> variableNames) {
        this(expression.toCharArray(), expression.length(), userFunctions, userOperators, variableNames, true);
    }

    public AbstractTokenizer(String expression, Map<String, AbstractFunction<T>> userFunctions, Map<String, AbstractOperator<T>> userOperators, Set<String> variableNames, boolean implicitMultiplication) {
        this(expression.trim().toCharArray(), expression.trim().length(), userFunctions, userOperators, variableNames, implicitMultiplication);
    }

    public AbstractTokenizer(char[] expression, int expressionLength, Map<String, AbstractFunction<T>> userFunctions, Map<String, AbstractOperator<T>> userOperators, Set<String> variableNames, boolean implicitMultiplication) {
        this.expression = expression;
        this.expressionLength = expressionLength;
        this.userFunctions = userFunctions;
        this.userOperators = userOperators;
        this.variableNames = variableNames;
        this.implicitMultiplication = implicitMultiplication;
    }

    public final boolean hasNext() {
        return expression.length > pos;
    }

    public final Token<T> nextToken() {
        char c = expression[pos];
        while (Character.isWhitespace(c)) {
            c = expression[++pos];
        }
        if (Character.isDigit(c) || c == '.') {
            if (lastToken != null) {
                if (lastToken.getTokenType() == TokenType.NUMBER) {
                    throw new IllegalArgumentException(String.format("Unable to parse char '%s' (Code: %d) at [%d]", c, (int) c, pos));
                } else if (implicitMultiplication && (lastToken.getTokenType() != TokenType.OPERATOR && lastToken.getTokenType() != TokenType.OPEN_PARENTHESES && lastToken.getTokenType() != TokenType.FUNCTION && lastToken.getTokenType() != TokenType.ARGUMENT_SEPERATOR)) {
                    lastToken = new OperatorToken<>(getStandardOperator('*', 2));
                    return lastToken;
                }
            }
            return parseNumberToken(c);
        } else if (isArgumentSeparator(c)) {
            return parseArgumentSeparatorToken(c);
        } else if (isOpenParentheses(c)) {
            if (lastToken != null && implicitMultiplication && (lastToken.getTokenType() != TokenType.OPERATOR && lastToken.getTokenType() != TokenType.OPEN_PARENTHESES && lastToken.getTokenType() != TokenType.FUNCTION && lastToken.getTokenType() != TokenType.ARGUMENT_SEPERATOR)) {
                lastToken = new OperatorToken<>(getStandardOperator('*', 2));
                return lastToken;
            }
            return parseParentheses(true);
        } else if (isCloseParentheses(c)) {
            return parseParentheses(false);
        } else if (AbstractOperator.isAllowedOperatorChar(c)) {
            return parseOperatorToken(c);
        } else if (isAlphabetic(c) || c == '_') {
            if (lastToken != null && implicitMultiplication && (lastToken.getTokenType() != TokenType.OPERATOR && lastToken.getTokenType() != TokenType.OPEN_PARENTHESES && lastToken.getTokenType() != TokenType.FUNCTION && lastToken.getTokenType() != TokenType.ARGUMENT_SEPERATOR)) {
                lastToken = new OperatorToken<>(getStandardOperator('*', 2));
                return lastToken;
            }
            return parseFunctionOrVariable();
        } else {
            throw new IllegalArgumentException(String.format("Unable to parse char '%s' (Code: %d) at [%d]", c, (int) c, pos));
        }
    }

    private final Token<T> parseFunctionOrVariable() {
        final int offset = this.pos;
        int testPos;
        int lastValidLen = 1;
        Token lastValidToken = null;
        int len = 1;
        if (isEndOfExpression(offset)) {
            this.pos++;
        }
        testPos = offset + len - 1;
        while (!isEndOfExpression(testPos) && isVariableOrFunctionCharacter(expression[testPos])) {
            String name = new String(expression, offset, len);
            if (variableNames != null && variableNames.contains(name)) {
                lastValidLen = len;
                lastValidToken = new VariableToken(name);
            } else {
                final AbstractFunction<T> function = getFunction(name);
                if (function != null) {
                    lastValidLen = len;
                    lastValidToken = new FunctionToken(function);
                }
            }
            len++;
            testPos = offset + len - 1;
        }
        if (lastValidToken == null) {
            throw new UnknownFunctionOrVariableException(new String(expression), pos, len);
        }
        pos += lastValidLen;
        lastToken = lastValidToken;
        return lastToken;
    }

    private final AbstractFunction<T> getFunction(String name) {
        AbstractFunction<T> function = null;
        if (this.userFunctions != null) {
            function = this.userFunctions.get(name);
        }
        if (function == null) {
            function = getStandardFunction(name);
        }
        return function;
    }

    private final Token<T> parseOperatorToken(char firstChar) {
        final int offset = this.pos;
        int len = 1;
        final StringBuilder symbol = new StringBuilder();
        AbstractOperator<T> lastValid = null;
        symbol.append(firstChar);
        while (!isEndOfExpression(offset + len) && AbstractOperator.isAllowedOperatorChar(expression[offset + len])) {
            symbol.append(expression[offset + len++]);
        }
        while (symbol.length() > 0) {
            AbstractOperator op = getOperator(symbol.toString());
            if (op == null) {
                symbol.setLength(symbol.length() - 1);
            } else {
                lastValid = op;
                break;
            }
        }
        pos += symbol.length();
        lastToken = new OperatorToken(lastValid);
        return lastToken;
    }

    private final AbstractOperator<T> getOperator(String symbol) {
        AbstractOperator<T> op = null;
        if (this.userOperators != null) {
            op = this.userOperators.get(symbol);
        }
        if (op == null && symbol.length() == 1) {
            int argc = 2;
            if (lastToken == null) {
                argc = 1;
            } else {
                TokenType lastTokenType = lastToken.getTokenType();
                if (lastTokenType == TokenType.OPEN_PARENTHESES || lastTokenType == TokenType.ARGUMENT_SEPERATOR) {
                    argc = 1;
                } else if (lastTokenType == TokenType.OPERATOR) {
                    final AbstractOperator<T> lastOp = ((OperatorToken) lastToken).getOperator();
                    if (lastOp.getNumOperands() == 2 || (lastOp.getNumOperands() == 1 && !lastOp.isLeftAssociative())) {
                        argc = 1;
                    }
                }

            }
            op = getStandardOperator(symbol.charAt(0), argc);
        }
        return op;
    }

    private final Token<T> parseArgumentSeparatorToken(char ch) {
        this.pos++;
        this.lastToken = new ArgumentSeperatorToken();
        return lastToken;
    }

    private final Token<T> parseParentheses(final boolean open) {
        if (open) {
            this.lastToken = new OpenParenthesesToken();
        } else {
            this.lastToken = new CloseParenthesesToken();
        }
        this.pos++;
        return lastToken;
    }

    private final Token<T> parseNumberToken(final char firstChar) {
        final int offset = this.pos;
        int len = 1;
        this.pos++;
        if (isEndOfExpression(offset + len)) {
            lastToken = new ComplexDoubleToken(Double.parseDouble(String.valueOf(firstChar)));
            return lastToken;
        }
        while (!isEndOfExpression(offset + len) && isNumeric(expression[offset + len], expression[offset + len - 1] == 'e' || expression[offset + len - 1] == 'E')) {
            len++;
            this.pos++;
        }
        // check if the e is at the end
        if (expression[offset + len - 1] == 'e' || expression[offset + len - 1] == 'E') {
            // since the e is at the end it's not part of the number and a rollback is necessary
            len--;
            pos--;
        }
        lastToken = new ComplexDoubleToken(Double.parseDouble(String.valueOf(expression, offset, len)));
        return lastToken;
    }

    private final boolean isArgumentSeparator(char ch) {
        return ch == ',';
    }

    private final boolean isOpenParentheses(char ch) {
        return ch == '(' || ch == '{' || ch == '[';
    }

    private final boolean isCloseParentheses(char ch) {
        return ch == ')' || ch == '}' || ch == ']';
    }

    private final static boolean isNumeric(char c, boolean lastCharE) {
        return Character.isDigit(c) || c == '.' || c == 'e' || c == 'E' || (lastCharE && (c == '-' || c == '+'));
    }

    private final boolean isEndOfExpression(int offset) {
        return this.expressionLength <= offset;
    }

    public abstract AbstractOperator<T> getStandardOperator(char symbol, int numArguments);

    public abstract AbstractFunction<T> getStandardFunction(String name);

    public static final boolean isAlphabetic(int codePoint) {
        return Character.isLetter(codePoint);
    }

    public static final boolean isVariableOrFunctionCharacter(int codePoint) {
        return isAlphabetic(codePoint) || Character.isDigit(codePoint) || codePoint == '_' || codePoint == '.';
    }

}
