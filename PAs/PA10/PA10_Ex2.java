package PA10;

/**
 * PA10 - Exercise 2: Pattern Matching (BF, BM, KMP)
 * Author: Aiden Wang
 */
public class PA10_Ex2 {
    static long comparisons = 0;

    public static void main(String[] args) {
        System.out.println("Modified by: Aiden Wang\n");

        String[] texts = {
            "aaaaaaaaaaaaaaaaabbbbbbbbbbbbbb",
            "a pattern matching algorithm",
            "GTTTATGTAGCTTACCTCCTCAAAGCAATACACTGAAAA"
        };

        String[][] patterns = {
            {"aaaab", "aabaa"},
            {"rithm", "rithn"},
            {"CTGA", "CTGG"}
        };

        for (int i = 0; i < texts.length; i++) {
            System.out.println("Test Case " + (i + 1));
            System.out.println("T: \"" + texts[i] + "\"");
            for (int pIndex = 0; pIndex < patterns[i].length; pIndex++) {
                String p = patterns[i][pIndex];
                System.out.println("  P: \"" + p + "\"");
                
                comparisons = 0;
                int indexBF = findBrute(texts[i], p);
                System.out.println("    BF  -> Found at: " + indexBF + ", Comparisons: " + comparisons);

                comparisons = 0;
                int indexBM = findBoyerMoore(texts[i], p);
                System.out.println("    BM  -> Found at: " + indexBM + ", Comparisons: " + comparisons);

                comparisons = 0;
                int indexKMP = findKMP(texts[i], p);
                System.out.println("    KMP -> Found at: " + indexKMP + ", Comparisons: " + comparisons);
                System.out.println();
            }
            System.out.println("--------------------------------------------------");
        }
    }

    public static int findBrute(String text, String pattern) {
        int n = text.length(), m = pattern.length();
        for (int i = 0; i <= n - m; i++) {
            int k = 0;
            while (k < m) {
                comparisons++;
                if (text.charAt(i + k) == pattern.charAt(k)) {
                    k++;
                } else {
                    break;
                }
            }
            if (k == m) return i;
        }
        return -1;
    }

    public static int findBoyerMoore(String text, String pattern) {
        int n = text.length(), m = pattern.length();
        if (m == 0) return 0;
        
        // Using basic array instead of Map for the Last-Occurrence function
        int[] last = new int[128];
        for (int i = 0; i < 128; i++) {
            last[i] = -1;
        }
        for (int k = 0; k < m; k++) {
            last[pattern.charAt(k)] = k;
        }

        int i = m - 1;
        int k = m - 1;
        while (i < n) {
            comparisons++;
            if (text.charAt(i) == pattern.charAt(k)) {
                if (k == 0) return i;
                i--;
                k--;
            } else {
                char c = text.charAt(i);
                int lastOcc = (c < 128) ? last[c] : -1;
                i += m - Math.min(k, 1 + lastOcc);
                k = m - 1;
            }
        }
        return -1;
    }

    public static int[] computeFailKMP(String pattern) {
        int m = pattern.length();
        int[] fail = new int[m];
        int j = 1;
        int k = 0;
        while (j < m) {
            if (pattern.charAt(j) == pattern.charAt(k)) {
                fail[j] = k + 1;
                j++;
                k++;
            } else if (k > 0) {
                k = fail[k - 1];
            } else {
                j++;
            }
        }
        return fail;
    }

    public static int findKMP(String text, String pattern) {
        int n = text.length(), m = pattern.length();
        if (m == 0) return 0;
        int[] fail = computeFailKMP(pattern);
        int j = 0, k = 0;
        while (j < n) {
            comparisons++;
            if (text.charAt(j) == pattern.charAt(k)) {
                if (k == m - 1) return j - m + 1;
                j++;
                k++;
            } else if (k > 0) {
                k = fail[k - 1];
            } else {
                j++;
            }
        }
        return -1;
    }
}
