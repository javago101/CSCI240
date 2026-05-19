package PA10;

/**
 * PA10 - Exercise 4: LCS Algorithm
 * Author: Aiden Wang
 */
public class PA10_Ex4 {
    public static void main(String[] args) {
        System.out.println("Modified by: Aiden Wang\n");
        
        String X1 = "GTTCCTAATA";
        String Y1 = "CGATAATTGAGA";
        runLCS(X1, Y1);

        String X2 = "ACTIVE";
        String Y2 = "RELATIVELY";
        runLCS(X2, Y2);
    }

    public static void runLCS(String strX, String strY) {
        System.out.println("X = \"" + strX + "\"");
        System.out.println("Y = \"" + strY + "\"");

        char[] X = strX.toCharArray();
        char[] Y = strY.toCharArray();
        
        int[][] L = LCSLength(X, Y);
        
        System.out.println("Resulting Table:");
        printTable(L, X, Y);
        
        System.out.print("\nLongest Common Subsequence: ");
        reconstructLCS(X, Y, L);
        System.out.println("\n--------------------------------------------------");
    }

    public static int[][] LCSLength(char[] X, char[] Y) {
        int m = X.length;
        int n = Y.length;
        int[][] L = new int[m + 1][n + 1];
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (X[i - 1] == Y[j - 1]) {
                    L[i][j] = L[i - 1][j - 1] + 1;
                } else {
                    L[i][j] = Math.max(L[i - 1][j], L[i][j - 1]);
                }
            }
        }
        return L;
    }

    public static void printTable(int[][] L, char[] X, char[] Y) {
        int m = X.length;
        int n = Y.length;
        
        System.out.print("      ");
        for (int j = 0; j < n; j++) {
            System.out.printf("%3c", Y[j]);
        }
        System.out.println();
        
        for (int i = 0; i <= m; i++) {
            if (i == 0) {
                System.out.print("   ");
            } else {
                System.out.printf("%2c ", X[i - 1]);
            }
            for (int j = 0; j <= n; j++) {
                System.out.printf("%3d", L[i][j]);
            }
            System.out.println();
        }
    }

    public static void reconstructLCS(char[] X, char[] Y, int[][] L) {
        StringBuilder lcs = new StringBuilder();
        int i = X.length, j = Y.length;
        
        while (i > 0 && j > 0) {
            if (X[i - 1] == Y[j - 1]) {
                lcs.insert(0, X[i - 1]);
                i--; 
                j--;
            } else if (L[i - 1][j] >= L[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }
        System.out.println(lcs.toString());
    }
}
