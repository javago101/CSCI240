# CSCI240 - Code Building Blocks & Boilerplates

This cheatsheet provides the exact, copy-pasteable Java snippets you need to construct functionality without worrying about syntax. Memorize these blocks ("积木代码").

## 1. Fast File Reading (Iterating text with Scanner)
This is the standard block for reading a massive file (like `large100k.txt`) line by line or token by token, ensuring the resources are closed correctly.

```java
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public void readData(String filename) {
    File dataFile = new File(filename);
    try (Scanner sc = new Scanner(dataFile)) {
        // hasNextInt() if you expect integers, hasNext() for Strings.
        while (sc.hasNextInt()) {
            int key = sc.nextInt();
            // Do something with 'key' here...
        }
    } catch (FileNotFoundException e) {
        System.err.println("Error: Cannot find file " + filename);
    }
}
```

## 2. Iterating through a Standard Hash Map
If you instantiated a standard `java.util.HashMap` and want to loop through every entry to print it.

```java
import java.util.HashMap;
import java.util.Map;

HashMap<Integer, String> map = new HashMap<>();

// ... populate map ...

// The golden loop for iterating maps:
for (Map.Entry<Integer, String> entry : map.entrySet()) {
    Integer key = entry.getKey();
    String value = entry.getValue();
    System.out.println("Key: " + key + " -> Value: " + value);
}
```

## 3. String Word Extraction (Using splits & regular expressions)
Frequently used in collision detection and polynomial hash inputs.

```java
try (Scanner sc = new Scanner(dataFile)) {
    while (sc.hasNext()) {
        String line = sc.nextLine();
        
        // This regex removes punctuation and empty spaces
        String[] words = line.split("[^a-zA-Z]+");
        
        for (String word : words) {
            if (!word.isEmpty()) {
                String cleanWord = word.toLowerCase();
                // Process cleanWord...
            }
        }
    }
}
```

## 4. Priority Queue Custom Comparators
If you want a Priority Queue to sort elements in DESCENDING order (Max-Heap) instead of the default ASCENDING order (Min-Heap).

```java
import java.util.Comparator;
import net.datastructures.HeapPriorityQueue;

// The Comparator block
class DescendingStringComparator implements Comparator<String> {
    @Override
    public int compare(String a, String b) {
        // To reverse order, do b.compareTo(a) instead of a.compareTo(b)
        return b.compareTo(a); 
    }
}

// Applying it to the Priority Queue
HeapPriorityQueue<String, Integer> pq = new HeapPriorityQueue<>(new DescendingStringComparator());
```

## 5. Tree Standard DFS Traversals (Recursive Templates)
How to recursively walk a Binary Tree.

```java
// Pre-Order: Root, Left, Right
public void preOrder(Node node) {
    if (node == null) return;
    System.out.print(node.element + " "); // Process Root
    preOrder(node.left);                  // Process Left
    preOrder(node.right);                 // Process Right
}

// In-Order: Left, Root, Right (Yields sorted output for BSTs)
public void inOrder(Node node) {
    if (node == null) return;
    inOrder(node.left);                   // Process Left
    System.out.print(node.element + " "); // Process Root
    inOrder(node.right);                  // Process Right
}
```
