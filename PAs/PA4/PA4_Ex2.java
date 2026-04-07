package PA4;

public class PA4_Ex2 {
    // Author: Aiden Wang

    // Custom Array Stack
    static class MyArrayStack {
        private int[] data;
        private int t = -1;

        public MyArrayStack(int capacity) {
            data = new int[capacity];
        }
        public boolean isEmpty() { return t == -1; }
        public boolean isFull() { return t == data.length - 1; }

        public void push(int e) throws Exception {
            if (isFull()) throw new Exception("stack overflow");
            data[++t] = e;
        }
        public int pop() throws Exception {
            if (isEmpty()) throw new Exception("stack underflow");
            return data[t--];
        }
    }

    public static void evaluatePostfix(String exp) {
        // Capacity set to 5 to trigger the expected stack overflow on the 5th test case
        MyArrayStack stack = new MyArrayStack(5);
        String[] tokens = exp.split(" ");

        try {
            for (String token : tokens) {
                if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                    int val2 = stack.pop(); // Note: Second operand is popped first
                    int val1 = stack.pop();
                    int res = 0;

                    if (token.equals("+")) res = val1 + val2;
                    else if (token.equals("-")) res = val1 - val2;
                    else if (token.equals("*")) res = val1 * val2;
                    else if (token.equals("/")) res = val1 / val2;

                    stack.push(res);
                } else {
                    stack.push(Integer.parseInt(token)); // Convert to int and push
                }
            }
            System.out.println(exp + " -> " + stack.pop());
        } catch (Exception e) {
            System.out.println(exp + " -> " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        System.out.println("Modified by: Aiden Wang\n");

        // Required test cases
        evaluatePostfix("17 2 3 + / 13 -");
        evaluatePostfix("5 2 3 + *");
        evaluatePostfix("2 3 2 / *");
        evaluatePostfix("-23 123 + 2 *");
        evaluatePostfix("17 2 3 4 5 * 6 7 * + / + * -");
    }
}