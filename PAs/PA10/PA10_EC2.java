package PA10;

import net.datastructures.HeapPriorityQueue;
import net.datastructures.LinkedBinaryTree;
import net.datastructures.Position;

import java.util.Scanner;

/**
 * PA10 - Extra Credit Option 2: Huffman Tree Bit Encoding
 * Author: Aiden Wang
 */
public class PA10_EC2 {

    public static void main(String[] args) {
        System.out.println("Author: Aiden Wang (Extra Credit 2)\n");

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

        HuffmanTreeEC ht = new HuffmanTreeEC(text);
        System.out.println("Huffman coding tree:");
        ht.printTree();
        
        System.out.println("\n");
        ht.printEncoding(text);
    }
}

class FreqCharEC {
    int freq;
    char c;
    boolean isLeaf;

    public FreqCharEC(int freq, char c, boolean isLeaf) {
        this.freq = freq;
        this.c = c;
        this.isLeaf = isLeaf;
    }
}

class HuffmanTreeEC {
    private LinkedBinaryTree<FreqCharEC> tree;
    private String[] prefixCodes;

    public HuffmanTreeEC(String text) {
        int[] freqMap = new int[128];
        for (int i = 0; i < text.length(); i++) {
            freqMap[text.charAt(i)]++;
        }

        HeapPriorityQueue<Integer, LinkedBinaryTree<FreqCharEC>> pq = new HeapPriorityQueue<>();
        for (int i = 0; i < 128; i++) {
            if (freqMap[i] > 0) {
                LinkedBinaryTree<FreqCharEC> t = new LinkedBinaryTree<>();
                t.addRoot(new FreqCharEC(freqMap[i], (char) i, true));
                pq.insert(freqMap[i], t);
            }
        }

        while (pq.size() > 1) {
            LinkedBinaryTree<FreqCharEC> t1 = pq.removeMin().getValue();
            LinkedBinaryTree<FreqCharEC> t2 = pq.removeMin().getValue();
            
            int freqSum = t1.root().getElement().freq + t2.root().getElement().freq;
            LinkedBinaryTree<FreqCharEC> t = new LinkedBinaryTree<>();
            t.addRoot(new FreqCharEC(freqSum, '*', false));
            t.attach(t.root(), t1, t2);
            
            pq.insert(freqSum, t);
        }

        if (!pq.isEmpty()) {
            tree = pq.removeMin().getValue();
        } else {
            tree = new LinkedBinaryTree<>();
        }
        
        prefixCodes = new String[128];
        if (!tree.isEmpty()) {
            buildCodes(tree.root(), "");
        }
    }

    private void buildCodes(Position<FreqCharEC> p, String code) {
        if (p == null) return;
        FreqCharEC fc = p.getElement();
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

    private void printTree(Position<FreqCharEC> p, int depth) {
        if (p == null) return;
        FreqCharEC fc = p.getElement();
        
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
