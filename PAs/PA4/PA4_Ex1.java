package PA4;

import java.util.Stack;

public class PA4_Ex1 {
    // Author: Aiden Wang

    public static void checkBalance(String exp) {
        Stack<String> stack = new Stack<>();
        String[] tokens = exp.split(" ");

        for (String token : tokens) {
            // Push opening symbols
            if (token.equals("(") || token.equals("[") || token.equals("<") || token.equals("{")) {
                stack.push(token);
            }
            // Check closing symbols
            else if (token.equals(")") || token.equals("]") || token.equals(">") || token.equals("}")) {
                if (stack.isEmpty()) {
                    System.out.println(exp + " \n   -> invalid, no matching opening symbol");
                    return;
                }
                String open = stack.pop();
                if (!matches(open, token)) {
                    System.out.println(exp + " \n   -> invalid, closing symbol does not match opening symbol");
                    return;
                }
            }
        }

        if (!stack.isEmpty()) {
            System.out.println(exp + " \n   -> invalid, no matching closing symbol");
        } else {
            System.out.println(exp + " \n   -> valid");
        }
    }

    private static boolean matches(String open, String close) {
        return (open.equals("(") && close.equals(")")) ||
                (open.equals("[") && close.equals("]")) ||
                (open.equals("<") && close.equals(">")) ||
                (open.equals("{") && close.equals("}"));
    }

    public static void main(String[] args) {
        System.out.println("Modified by: Aiden Wang\n");

        // Required test cases
        checkBalance("{ ( a + b ) * c1 }");
        checkBalance("{ ( a + b ) * c1 ]");
        checkBalance("( < a + b > * c1 ) / 15 )");
        checkBalance("( ( ( a + b ) * c1 ) / 15 )");
        checkBalance("( < 12 % 3 > + 4 --");
    }
}

