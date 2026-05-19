package PA10;

import net.datastructures.HeapPriorityQueue;
import net.datastructures.LinkedBinaryTree;
import net.datastructures.Position;

import java.util.Scanner;

/**
 * PA10 - Exercise 3: Huffman Tree (with Extra Credit Option 2)
 * Author: Aiden Wang
 */
public class PA10_Ex3 {

    public static void main(String[] args) {
        System.out.println("Modified by: Aiden Wang\n");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter text: ");
        String text = "more_money_needed"; // fallback
        if (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (!input.trim().isEmpty()) {
                text = input;
            }
        }

        System.out.println("\nText: \"" + text + "\"");
        System.out.println("Number of characters: " + text.length() + "\n");

        HuffmanTree ht = new HuffmanTree(text);
        System.out.println("Huffman coding tree:");
        ht.printTree();
        
        System.out.println("\n");
        ht.printEncoding(text);
    }
}

class FreqChar {
    int freq;
    char c;
    boolean isLeaf;

    public FreqChar(int freq, char c, boolean isLeaf) {
        this.freq = freq;
        this.c = c;
        this.isLeaf = isLeaf;
    }
}

class HuffmanTree {
    private LinkedBinaryTree<FreqChar> tree;
    private String[] prefixCodes;

    public HuffmanTree(String text) {
        // Using basic array instead of HashMap
        int[] freqMap = new int[128];
        for (int i = 0; i < text.length(); i++) {
            freqMap[text.charAt(i)]++;
        }

        HeapPriorityQueue<Integer, LinkedBinaryTree<FreqChar>> pq = new HeapPriorityQueue<>();
        for (int i = 0; i < 128; i++) {
            if (freqMap[i] > 0) {
                LinkedBinaryTree<FreqChar> t = new LinkedBinaryTree<>();
                t.addRoot(new FreqChar(freqMap[i], (char) i, true));
                pq.insert(freqMap[i], t);
            }
        }

        while (pq.size() > 1) {
            LinkedBinaryTree<FreqChar> t1 = pq.removeMin().getValue();
            LinkedBinaryTree<FreqChar> t2 = pq.removeMin().getValue();
            
            int freqSum = t1.root().getElement().freq + t2.root().getElement().freq;
            LinkedBinaryTree<FreqChar> t = new LinkedBinaryTree<>();
            t.addRoot(new FreqChar(freqSum, '*', false));
            t.attach(t.root(), t1, t2);
            
            pq.insert(freqSum, t);
        }

        if (!pq.isEmpty()) {
            tree = pq.removeMin().getValue();
        } else {
            tree = new LinkedBinaryTree<>();
        }
        
        // Using basic array instead of TreeMap
        prefixCodes = new String[128];
        if (!tree.isEmpty()) {
            buildCodes(tree.root(), "");
        }
    }

    private void buildCodes(Position<FreqChar> p, String code) {
        if (p == null) return;
        FreqChar fc = p.getElement();
        if (fc.isLeaf) {
            prefixCodes[fc.c] = code;
        } else {
            if (tree.left(p) != null) buildCodes(tree.left(p), code + "0");
            if (tree.right(p) != null) buildCodes(tree.right(p), code + "1");
        }
    }

    public void printTree() {
        if (tree == null || tree.isEmpty()) return;
        printTree(tree.root(), 0);
    }

    private void printTree(Position<FreqChar> p, int depth) {
        if (p == null) return;
        FreqChar fc = p.getElement();
        
        for (int i = 0; i < depth; i++) {
            System.out.print("    "); // 4 spaces per depth level
        }
        
        if (fc.isLeaf) {
            System.out.println(fc.freq + " " + fc.c);
        } else {
            System.out.println(fc.freq + " *");
        }
        
        if (tree.left(p) != null) printTree(tree.left(p), depth + 1);
        if (tree.right(p) != null) printTree(tree.right(p), depth + 1);
    }

    public void printEncoding(String text) {
        System.out.println("Char Frequency Encoded bits");
        
        int[] freqMap = new int[128];
        for (int i = 0; i < text.length(); i++) {
            freqMap[text.charAt(i)]++;
        }
        
        for (int i = 0; i < 128; i++) {
            if (freqMap[i] > 0) {
                System.out.printf("%-4c%-3d%s\n", (char)i, freqMap[i], prefixCodes[i]);
            }
        }
        
        String encodedText = "";
        for (int i = 0; i < text.length(); i++) {
            encodedText += prefixCodes[text.charAt(i)];
        }
        
        System.out.println("\nNumber of bits to encode message: " + encodedText.length());
        System.out.println(encodedText);
    }
}
