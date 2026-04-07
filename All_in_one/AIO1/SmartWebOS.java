package AIO1;

import net.datastructures.*;
import java.io.File;

/**
 * SmartWebOS — Toy Project
 * Integrates: Stack, LinkedPositionalList, Array Scoreboard, Recursive Tree (Postorder)
 *
 * Fixes applied vs original:
 *  1. Added TextEditor.first() method (was called in main() but never defined)
 *  2. Scoreboard.add() guarded against empty-board NPE on first entry
 *  3. TextEditor.insert() broadened to accept digits and punctuation (not just letters+space)
 */
public class SmartWebOS {

    // ==========================================
    // Module 1: Web Browser (Stack)
    // ==========================================
    static class WebBrowser {
        private Stack<String> history;

        public WebBrowser() {
            history = new LinkedStack<>();
        }

        public void visit(String url) {
            history.push(url);
            System.out.println("[Browser] Visited: " + url);
        }

        public void back() {
            if (history.isEmpty()) {
                System.out.println("[Browser] No history to go back to.");
                return;
            }
            String url = history.pop();
            System.out.println("[Browser] Going back from: " + url);
            if (!history.isEmpty()) {
                System.out.println("[Browser] Current page: " + history.top());
            } else {
                System.out.println("[Browser] No more pages in history.");
            }
        }

        public String currentPage() {
            return history.isEmpty() ? null : history.top();
        }
    }

    // ==========================================
    // Module 2: Text Editor (LinkedPositionalList)
    // PA3-compatible: each node holds 1 character.
    // Cursor semantics: null = end of document.
    // ==========================================
    static class TextEditor {
        private LinkedPositionalList<Character> list;
        private Position<Character> cursor;

        public TextEditor(String initialText) {
            list = new LinkedPositionalList<>();
            cursor = null;
            for (char c : initialText.toCharArray()) {
                insert(c);
            }
        }

        /** Insert character before the cursor (or at end if cursor == null). */
        public void insert(char c) {
            // Accept letters, digits, spaces, and common punctuation
            if (Character.isLetterOrDigit(c) || " .,!?;:'\"()-".indexOf(c) >= 0) {
                if (cursor == null) {
                    list.addLast(c);
                } else {
                    list.addBefore(cursor, c);
                }
            }
        }

        /** Backspace: delete the character immediately before the cursor. */
        public void remove() {
            if (list.isEmpty()) return;
            if (cursor == null) {
                list.remove(list.last());
            } else if (cursor != list.first()) {
                list.remove(list.before(cursor));
            }
        }

        /** Move cursor one position to the left. */
        public void left() {
            if (cursor == null) {
                cursor = list.last();
            } else if (cursor != list.first()) {
                cursor = list.before(cursor);
            }
        }

        /** Move cursor one position to the right. */
        public void right() {
            if (cursor != null) {
                cursor = list.after(cursor);
            }
        }

        /** Jump cursor to the very beginning (before first character). */
        public void first() {                        // FIX: was missing in original
            if (!list.isEmpty()) {
                cursor = list.first();
            }
        }

        /** Jump cursor to the end (after last character). */
        public void end() {
            cursor = null;
        }

        /**
         * Display the document with '|' marking the cursor position.
         * Example: "Hel|lo World"
         */
        public void display() {
            System.out.print("[Editor] ");
            if (list.isEmpty()) {
                System.out.println("|");
                return;
            }
            Position<Character> walk = list.first();
            while (walk != null) {
                if (walk == cursor) System.out.print("|");
                System.out.print(walk.getElement());
                walk = list.after(walk);
            }
            if (cursor == null) System.out.print("|");
            System.out.println();
        }

        /** Return the document contents as a plain String (for testing). */
        public String getText() {
            StringBuilder sb = new StringBuilder();
            Position<Character> walk = list.first();
            while (walk != null) {
                sb.append(walk.getElement());
                walk = list.after(walk);
            }
            return sb.toString();
        }
    }

    // ==========================================
    // Module 3: Gaming Hub (Array-based Scoreboard)
    // Maintains top-N scores in descending order.
    // ==========================================
    static class GameEntry {
        private String name;
        private int score;

        public GameEntry(String n, int s) {
            name = n;
            score = s;
        }

        public String getName()  { return name; }
        public int    getScore() { return score; }

        @Override
        public String toString() { return "(" + name + ", " + score + ")"; }
    }

    static class Scoreboard {
        private int numEntries = 0;
        private GameEntry[] board;

        public Scoreboard(int capacity) {
            board = new GameEntry[capacity];
        }

        /**
         * Attempt to add a new entry.
         * FIX: original code evaluated board[numEntries-1] when numEntries==0,
         *      which would throw ArrayIndexOutOfBoundsException.
         *      Now we short-circuit on numEntries==0.
         */
        public void add(GameEntry e) {
            int newScore = e.getScore();
            boolean qualifies = (numEntries < board.length)
                    || (numEntries > 0 && newScore > board[numEntries - 1].getScore());

            if (qualifies) {
                if (numEntries < board.length) numEntries++;
                int j = numEntries - 1;
                while (j > 0 && board[j - 1].getScore() < newScore) {
                    board[j] = board[j - 1];
                    j--;
                }
                board[j] = e;
                System.out.println("[Scoreboard] Added: " + e);
            } else {
                System.out.println("[Scoreboard] " + e + " did not make the board.");
            }
        }

        public void printBoard() {
            System.out.print("[Gaming Hub] High scores: ");
            for (int i = 0; i < numEntries; i++) {
                System.out.print(board[i] + (i < numEntries - 1 ? ", " : ""));
            }
            System.out.println();
        }
    }

    // ==========================================
    // Module 4: File System (Recursive Tree — Postorder Traversal)
    // Mirrors Unix `du`: children are summed before the parent totals.
    // ==========================================
    static class FileSystem {
        /**
         * Returns total disk usage (bytes) of root and all descendants.
         * Uses postorder traversal: leaf sizes accumulate upward to the root.
         */
        public static long diskUsage(File root) {
            long total = root.length();          // direct bytes of this entry
            if (root.isDirectory()) {
                String[] children = root.list(); // may return null on I/O error
                if (children != null) {
                    for (String childName : children) {
                        File child = new File(root, childName);
                        total += diskUsage(child); // recurse (postorder: children first)
                    }
                }
            }
            System.out.println(total + "\t" + root);
            return total;
        }
    }

    // ==========================================
    // OS Main Driver
    // ==========================================
    public static void main(String[] args) {
        System.out.println("=== Booting SmartWebOS ===\n");

        // --- 1. Web Browser ---
        System.out.println("--- 1. Web Browser ---");
        WebBrowser browser = new WebBrowser();
        browser.visit("www.google.com");
        browser.visit("www.zybooks.com");
        browser.visit("canvas.mtsac.edu");
        browser.back();
        browser.back();
        browser.back();
        browser.back(); // empty stack — should print graceful message
        System.out.println();

        // --- 2. Text Editor ---
        System.out.println("--- 2. Text Editor ---");
        TextEditor editor = new TextEditor("HHello Word");
        editor.display();            // HHello Word|

        // Move to just after first 'H' and delete it
        editor.first();              // cursor at first 'H'
        editor.right();              // cursor at second 'H'
        editor.remove();             // delete first 'H'   → Hello Word
        editor.display();            // Hello Word|  (cursor still near start)

        // Move to end, then back past 'd' to insert missing 'l'
        editor.end();
        editor.left();               // cursor on 'd'
        editor.insert('l');          // insert 'l' before 'd' → Hello World
        editor.display();            // Hello Worl|d
        editor.end();
        editor.display();            // Hello World|
        System.out.println("[Editor] Final text: " + editor.getText());
        System.out.println();

        // --- 3. Gaming Hub ---
        System.out.println("--- 3. Gaming Hub ---");
        Scoreboard board = new Scoreboard(3);
        board.add(new GameEntry("Alice",   1050));
        board.add(new GameEntry("Bob",      750));
        board.add(new GameEntry("Aiden",   1200));
        board.add(new GameEntry("Charlie",  900)); // pushes Bob off
        board.add(new GameEntry("Zara",     400)); // too low
        board.printBoard();
        System.out.println();

        // --- 4. File System ---
        System.out.println("--- 4. File System ---");
        File currentDir = new File(".");
        long totalSpace = FileSystem.diskUsage(currentDir);
        System.out.println("[File System] Total disk usage: " + totalSpace + " bytes\n");

        System.out.println("=== Shutting down SmartWebOS ===");
    }
}
