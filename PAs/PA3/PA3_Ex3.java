package PA3;

import net.datastructures.LinkedPositionalList;
import net.datastructures.Position;

public class PA3_Ex3 {

    public static class TextEditor {
        private LinkedPositionalList<Character> list;
        private Position<Character> cursor; // Cursor points to the character AFTER the '|'

        public TextEditor(String s) {
            list = new LinkedPositionalList<>();
            for (char c : s.toCharArray()) {
                if (Character.isLetter(c) || c == ' ') {
                    list.addLast(c);
                }
            }
            cursor = null; // Default to end of string
        }

        public void display() {
            Position<Character> walk = list.first();
            while (walk != null) {
                if (walk == cursor) {
                    System.out.print("|");
                }
                System.out.print(walk.getElement());
                walk = list.after(walk);
            }
            if (cursor == null) {
                System.out.print("|");
            }
            System.out.println();
        }

        public void left() {
            if (cursor == null) {
                cursor = list.last();
            } else if (cursor != list.first()) {
                cursor = list.before(cursor);
            }
        }

        public void right() {
            if (cursor != null) {
                cursor = list.after(cursor);
            }
        }

        public void first() {
            cursor = list.first();
        }

        public void last() {
            cursor = null;
        }

        public void insert(char c) {
            if (Character.isLetter(c) || c == ' ') {
                if (cursor == null) {
                    list.addLast(c);
                } else {
                    list.addBefore(cursor, c);
                }
            }
        }

        public void remove() {
            if (list.isEmpty()) return;
            if (cursor == null) {
                list.remove(list.last());
            } else if (cursor != list.first()) {
                list.remove(list.before(cursor));
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Modified by: Aiden Wang\n");

        // 1. Create a TextEditor object with "HHello Word" (assume cursor at the end)
        TextEditor editor = new TextEditor("HHello Word");

        // 2. Display current information
        editor.display();

        // 3. Move cursor right (nothing should happen)
        editor.right();

        // 4. Move cursor left
        editor.left();

        // 5. Insert '1' (digit 1, ignore invalid character)
        editor.insert('1');

        // 6. Insert 'l' (letter l)
        editor.insert('l');

        // 7. Display current information (should be "HHello Worl|d")
        editor.display();

        // 8. Move cursor to the beginning
        editor.first();

        // 9. Move cursor right
        editor.right();

        // 10. Remove character (Removes the second 'H')
        editor.remove();

        // 11. Move cursor to the end
        editor.last();

        // 12. Display current information (should be "Hello World|")
        editor.display();
    }
}
