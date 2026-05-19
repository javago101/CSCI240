package PA10;

import net.datastructures.HeapPriorityQueue;
import net.datastructures.LinkedBinaryTree;
import net.datastructures.Position;

import java.util.Scanner;

/**
 * PA10 - Exercise 3: Huffman Tree (Standard)
 * Author: Aiden Wang
 */
public class PA10_Ex3 {

    public static void main(String[] args) {
        System.out.println("Author: Aiden Wang\n");

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

    public HuffmanTree(String text) {
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
}
