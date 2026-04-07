package PA3;

import net.datastructures.LinkedPositionalList;
import net.datastructures.Position;
import java.util.Iterator;

public class PA3_Ex2 {
    public static void main(String[] args) {
        System.out.println("Modified by: Aiden Wang\n");

        LinkedPositionalList<String> list = new LinkedPositionalList<>();

        // 1. Add "Two" to the front
        list.addFirst("Two");
        // 2. Add "Three" to the rear
        list.addLast("Three");
        // 3. Add "One" to the front
        list.addFirst("One");
        // 4. Add "Four" to the rear
        list.addLast("Four");

        // 5. Use an iterator to print the list
        System.out.print("List contents: ");
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println();

        // 6. Set up an iterator at the begin and advance twice
        Position<String> cursor = list.first();
        cursor = list.after(cursor); // advance once
        cursor = list.after(cursor); // advance twice

        // 7. Insert your name before the iterator
        list.addBefore(cursor, "Aiden");

        // 8. Remove front
        list.remove(list.first());
        // 9. Remove back
        list.remove(list.last());

        // 10. Use an iterator to print the list
        System.out.print("After modifications: ");
        it = list.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println();
    }
}