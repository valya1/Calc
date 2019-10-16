import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class CalculationLogic {

    CalculationLogic() {
    }

    public float calculate(String calcString) throws UnknownExpressionException {
        List<String> reversedNotationExpr = toReverseNotation(calcString);

        return calculateExpr(reversedNotationExpr);
    }


    private List<String> toReverseNotation(String calcStr) throws UnknownExpressionException {

        List<String> reversedNotation = new ArrayList<>();

        Stack<Character> operationStack = new Stack<>();
        String currDigit = "";

        for (int i = 0; i < calcStr.length(); i++) {
            char currChar = calcStr.charAt(i);

            if (allowDigit(currChar, currDigit)) {
                currDigit += currChar;
            } else {

                reversedNotation.add(currDigit);
                currDigit = "";

                String operations = proceedOperation(currChar, operationStack);
                if (!operations.isEmpty())
                    reversedNotation.add(proceedOperation(currChar, operationStack));
            }
        }

        if (!currDigit.isEmpty())
            reversedNotation.add(currDigit);

        while (!operationStack.isEmpty()) {
            reversedNotation.add(String.valueOf(operationStack.pop()));
        }

        return reversedNotation;
    }

    private float calculateExpr(List<String> exprItems) throws NumberFormatException, UnknownExpressionException {

        float result = 0f;
        Stack<Float> digitsBuffer = new Stack<>();

        for (String exprItem : exprItems) {
            if (!isOperation(exprItem)) {
                digitsBuffer.push(Float.valueOf(exprItem));
            } else {
                float digit2 = digitsBuffer.pop();
                float digit1 = digitsBuffer.pop();
                result += calc(digit1, digit2, exprItem);
            }
        }
        return result;
    }


    private String proceedOperation(char operation, Stack<Character> opStack) throws UnknownExpressionException {

        if (!isOperation(String.valueOf(operation)))
            throw new UnknownExpressionException();

        StringBuilder outString = new StringBuilder();

        if (opStack.isEmpty() || getOpPriority(operation) > getOpPriority(opStack.peek())) {
            opStack.push(operation);
        } else while (getOpPriority(operation) <= getOpPriority(opStack.peek())) {
            outString.append(opStack.pop());
        }

        return outString.toString();
    }

    private boolean allowDigit(char c, String currDigit) {
        return Character.isDigit(c) || (c == '.' && !currDigit.isEmpty() && !currDigit.contains("."));
    }

    private boolean isOperation(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals("^");
    }

    private float calc(float a, float b, String op) throws UnknownExpressionException {
        if (!isOperation(String.valueOf(op)))
            throw new UnknownExpressionException();

        float res = 0f;

        switch (op) {
            case "+":
                res = a + b;
                break;
            case "-":
                res = a - b;
                break;
            case "*":
                res = a * b;
                break;
            case "/":
                res = a / b;
                break;
            case "^":
                res = (float) Math.pow(a, b);
                break;
        }
        return res;
    }

    private int getOpPriority(char op) {
        int priority = 0;

        switch (op) {
            case '^':
                priority = 2;
                break;
            case '*':
            case '/':
                priority = 1;
                break;
        }
        return priority;
    }

}
