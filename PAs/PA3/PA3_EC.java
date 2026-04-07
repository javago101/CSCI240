package PA3;

public class PA3_EC {

    public static class TextEditor1 {
        private StringBuilder text;
        private int cursor; // Represents index of the '|'

        public TextEditor1(String s) {
            text = new StringBuilder();
            for (char c : s.toCharArray()) {
                if (Character.isLetter(c) || c == ' ') {
                    text.append(c);
                }
            }
            cursor = text.length(); // Cursor at the end
        }

        public void display() {
            StringBuilder out = new StringBuilder(text);
            out.insert(cursor, "|");
            System.out.println(out.toString());
        }

        public void left() {
            if (cursor > 0) cursor--;
        }

        public void right() {
            if (cursor < text.length()) cursor++;
        }

        public void first() {
            cursor = 0;
        }

        public void last() {
            cursor = text.length();
        }

        public void insert(char c) {
            if (Character.isLetter(c) || c == ' ') {
                text.insert(cursor, c);
                cursor++; // Move cursor past the newly inserted character
            }
        }

        public void remove() {
            if (cursor > 0) {
                text.deleteCharAt(cursor - 1);
                cursor--;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Modified by: Aiden Wang\n");
        TextEditor1 editor = new TextEditor1("HHello Word");
        editor.display();
        editor.right();
        editor.left();
        editor.insert('1');
        editor.insert('l');
        editor.display();
        editor.first();
        editor.right();
        editor.remove();
        editor.last();
        editor.display();
    }
}
