package PA3;

import java.util.ArrayList;

// Extending ArrayList to add custom functionality for the assignment
class MyArrayList extends ArrayList<String> {

    /**
     * Removes an item if it is in the list.
     * Does nothing if the item is not found.
     */
    public void removeElem(String str) {
        // The contains() method checks if the string exists
        if (this.contains(str)) {
            // The remove(Object) method removes the first occurrence
            this.remove(str);
        }
    }
}

public class PA3_Ex1 {
    public static void main(String[] args) {
        System.out.println("Modified by: Aiden Wang\n");

        // Assuming your ArrayList is implemented with a default capacity
        MyArrayList vector = new MyArrayList();

        // 1. Add "Two" to index 0
        vector.add(0, "Two");
        // 2. Add "Three" to index 1
        vector.add(1, "Three");
        // 3. Add "One" to index 0
        vector.add(0, "One");
        // 4. Add "Four" to index 3
        vector.add(3, "Four");

        // 5. Print the vector
        System.out.println("After additions: " + vector);

        // 6. Remove item at index 0
        vector.remove(0);
        // 7. Remove "Two"
        vector.removeElem("Two");
        // 8. Remove "four" (Case sensitive, nothing should happen)
        vector.removeElem("four");

        // 9. Insert your name at index 1
        vector.add(1, "Aiden");

        // 10. Print the vector
        System.out.println("After removals and inserting name: " + vector);
    }
}