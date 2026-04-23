package PA8;

import net.datastructures.TreeMap;
import net.datastructures.AVLTreeMap;
import java.io.File;
import java.util.Scanner;

/**
 * PA8 - Extra Credit Option 1
 * Analyzes average number of operations (nodes examined) for BST vs AVL insertions.
 * Author: Aiden Wang
 */
public class PA8_EC1 {

    public static void main(String[] args) {
        System.out.println("Author: Aiden Wang");
        System.out.println("PA8 Extra Credit - Search Tree Performance Analysis\n");

        String filename = "PAs/PA8/Data/popSmall.txt";
        
        runBSTAnalysis(filename);
        System.out.println();
        runAVLAnalysis(filename);
    }

    private static void runBSTAnalysis(String filename) {
        TreeMap<Integer, String> bst = new TreeMap<>();
        long totalExamined = 0;
        int count = 0;

        try (Scanner sc = new Scanner(new File(filename))) {
            if (sc.hasNextInt()) {
                count = sc.nextInt();
                sc.nextLine();
                for (int i = 0; i < count && sc.hasNextLine(); i++) {
                    String line = sc.nextLine();
                    int comma = line.indexOf(',');
                    if (comma != -1) {
                        int code = Integer.parseInt(line.substring(0, comma).trim());
                        String data = line.substring(comma + 1).trim();

                        int before = bst.nodesExamined;
                        bst.put(code, data);
                        totalExamined += (bst.nodesExamined - before);
                    }
                }
            }
            double avg = (double) totalExamined / count;
            System.out.println(">> BST Performance (TreeMap)");
            System.out.println("Nodes inserted: " + count);
            System.out.println("Total node examinations: " + totalExamined);
            System.out.printf("Average nodes examined per insertion: %.2f\n", avg);
        } catch (Exception e) {
            System.out.println("BST Error: " + e.getMessage());
        }
    }

    private static void runAVLAnalysis(String filename) {
        AVLTreeMap<Integer, String> avl = new AVLTreeMap<>();
        long totalExamined = 0;
        int count = 0;

        try (Scanner sc = new Scanner(new File(filename))) {
            if (sc.hasNextInt()) {
                count = sc.nextInt();
                sc.nextLine();
                for (int i = 0; i < count && sc.hasNextLine(); i++) {
                    String line = sc.nextLine();
                    int comma = line.indexOf(',');
                    if (comma != -1) {
                        int code = Integer.parseInt(line.substring(0, comma).trim());
                        String data = line.substring(comma + 1).trim();

                        int before = avl.nodesExamined;
                        avl.put(code, data);
                        totalExamined += (avl.nodesExamined - before);
                    }
                }
            }
            double avg = (double) totalExamined / count;
            System.out.println(">> AVL Performance (AVLTreeMap)");
            System.out.println("Nodes inserted: " + count);
            System.out.println("Total node examinations: " + totalExamined);
            System.out.printf("Average nodes examined per insertion: %.2f\n", avg);
        } catch (Exception e) {
            System.out.println("AVL Error: " + e.getMessage());
        }
    }
}
