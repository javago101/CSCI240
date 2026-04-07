package PA2;

// Author: Aiden Wang
class PA2_Ex3 {
    // Setup global/static variables
    static String[] arr = new String[4];
    static int numElems = 0;

    public static void insertRear(String name) {
        if (numElems < 10) {
            arr[numElems] = name;
            numElems++;
        }
    }

    public static void insertAt(int index, String name) {
        if (numElems < 10 && index >= 0 && index <= numElems) {
            for (int i = numElems; i > index; i--) {
                arr[i] = arr[i - 1];
            }
            arr[index] = name;
            numElems++;
        }
    }

    public static void removeRear() {
        if (numElems > 0) {
            numElems--;
            arr[numElems] = null;
        }
    }

    public static void removeAt(int index) {
        if (index >= 0 && index < numElems) {
            for (int i = index; i < numElems - 1; i++) {
                arr[i] = arr[i + 1];
            }
            numElems--;
            arr[numElems] = null;
        }
    }

    public static void printAll() {
        for (int i = 0; i < numElems; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("Author: Aiden Wang\n");

        insertRear("Jo");
        insertRear("Jane");
        insertAt(1, "John");
        insertRear("Kim");

        System.out.println("numElems: " + numElems);  // output 4

        removeAt(0);
        removeRear();

        System.out.print("List: ");
        printAll();    // output John Jane
    }
}
