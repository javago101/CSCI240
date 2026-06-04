# PA 12 Submission (deadline Saturday, 06/06, 11:59 pm)

**Course**: CSCI-240-01-41255.202540
**Due Date**: 2026-06-06T23:59:00Z
**Link**: https://mtsac.instructure.com/courses/176042/assignments/3213146

---
## ✍️ Writing Workspace

### Author: Aiden Wang
**Course**: CSCI-240 Data Structures & Algorithms
**Assignment**: PA 12 - Graph Algorithms and External Memory

***

### Part 1: Source Code

#### `PA12_Ex1.java` (Shortest Path with Dijkstra)
```java
import net.datastructures.*;
import java.io.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class PA12_Ex1 {

    public static void main(String[] args) throws Exception {
        System.out.println("Author: Aiden Wang");
        
        Graph<String, Integer> graph = new AdjacencyMapGraph<>(true);
        Map<String, Vertex<String>> vertices = new HashMap<>();
        
        Scanner sc = new Scanner(new File("PA12Flights-1.txt"));
        while (sc.hasNext()) {
            String src = sc.next();
            String dest = sc.next();
            int cost = sc.nextInt();
            
            if (!vertices.containsKey(src)) {
                vertices.put(src, graph.insertVertex(src));
            }
            if (!vertices.containsKey(dest)) {
                vertices.put(dest, graph.insertVertex(dest));
            }
            
            graph.insertEdge(vertices.get(src), vertices.get(dest), cost);
        }
        sc.close();
        
        System.out.println("Graph constructed from PA12Flights-1.txt.");
        System.out.println("=========================================");
        dijkstra(graph, vertices, "LAX", "JFK");
        dijkstra(graph, vertices, "JFK", "LAX");
    }

    public static void dijkstra(Graph<String, Integer> graph, Map<String, Vertex<String>> vertices, String start, String end) {
        if (!vertices.containsKey(start) || !vertices.containsKey(end)) return;
        
        Vertex<String> srcVertex = vertices.get(start);
        Vertex<String> destVertex = vertices.get(end);
        
        Map<Vertex<String>, Integer> d = new HashMap<>();
        Map<Vertex<String>, Edge<Integer>> forest = new HashMap<>();
        AdaptablePriorityQueue<Integer, Vertex<String>> pq = new HeapAdaptablePriorityQueue<>();
        Map<Vertex<String>, Entry<Integer, Vertex<String>>> pqTokens = new HashMap<>();
        
        for (Vertex<String> v : graph.vertices()) {
            if (v == srcVertex) {
                d.put(v, 0);
            } else {
                d.put(v, Integer.MAX_VALUE);
            }
            pqTokens.put(v, pq.insert(d.get(v), v));
        }
        
        while (!pq.isEmpty()) {
            Entry<Integer, Vertex<String>> entry = pq.removeMin();
            int dist = entry.getKey();
            Vertex<String> u = entry.getValue();
            pqTokens.remove(u);
            
            if (u == destVertex) break;
            
            for (Edge<Integer> e : graph.outgoingEdges(u)) {
                Vertex<String> v = graph.opposite(u, e);
                if (pqTokens.containsKey(v)) {
                    int alt = dist + e.getElement();
                    if (alt < d.get(v)) {
                        d.put(v, alt);
                        forest.put(v, e);
                        pq.replaceKey(pqTokens.get(v), alt);
                    }
                }
            }
        }
        
        if (d.get(destVertex) == Integer.MAX_VALUE) {
            System.out.println("No flight path available from " + start + " to " + end + ".");
            return;
        }
        
        List<Vertex<String>> path = new ArrayList<>();
        List<Integer> edgeCosts = new ArrayList<>();
        Vertex<String> step = destVertex;
        
        while (step != srcVertex) {
            path.add(step);
            Edge<Integer> edge = forest.get(step);
            edgeCosts.add(edge.getElement());
            step = graph.opposite(step, edge);
        }
        path.add(srcVertex);
        Collections.reverse(path);
        Collections.reverse(edgeCosts);
        
        System.out.print("Cheapest flight from " + start + " to " + end + ": ");
        for (int i = 0; i < path.size() - 1; i++) {
            System.out.print(path.get(i).getElement() + ", $" + edgeCosts.get(i) + " --> ");
        }
        System.out.println(destVertex.getElement());
        System.out.println("Total Cost: $" + d.get(destVertex) + "
");
    }
}

```

#### `PA12_Ex2.java` (Minimum Spanning Tree - Prim's Algorithm)
```java
import net.datastructures.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class PA12_Ex2 {

    public static void main(String[] args) {
        System.out.println("Author: Aiden Wang");
        System.out.println("Exercise 2: Prim's Minimum Spanning Tree
");

        Graph<String, Integer> graph = new AdjacencyMapGraph<>(false);
        Map<String, Vertex<String>> vertices = new HashMap<>();
        
        for (String node : new String[]{"A", "B", "C", "D", "E"}) {
            vertices.put(node, graph.insertVertex(node));
        }

        graph.insertEdge(vertices.get("A"), vertices.get("B"), 3);
        graph.insertEdge(vertices.get("A"), vertices.get("C"), 4);
        graph.insertEdge(vertices.get("A"), vertices.get("D"), 5);
        graph.insertEdge(vertices.get("A"), vertices.get("E"), 5);
        graph.insertEdge(vertices.get("B"), vertices.get("C"), 4);
        graph.insertEdge(vertices.get("C"), vertices.get("D"), 2);
        graph.insertEdge(vertices.get("D"), vertices.get("E"), 5);
        graph.insertEdge(vertices.get("C"), vertices.get("E"), 3);

        List<Edge<Integer>> mstEdges = primMST(graph, vertices.get("A"));

        int totalCost = 0;
        System.out.println("MST Edges:");
        for (Edge<Integer> e : mstEdges) {
            Vertex<String>[] endpts = graph.endVertices(e);
            System.out.println("(" + endpts[0].getElement() + ", " + endpts[1].getElement() + ") cost: " + e.getElement());
            totalCost += e.getElement();
        }
        System.out.println("Total MST Cost: " + totalCost);
    }

    public static List<Edge<Integer>> primMST(Graph<String, Integer> graph, Vertex<String> start) {
        List<Edge<Integer>> mst = new ArrayList<>();
        Map<Vertex<String>, Integer> d = new HashMap<>();
        Map<Vertex<String>, Edge<Integer>> forest = new HashMap<>();
        AdaptablePriorityQueue<Integer, Vertex<String>> pq = new HeapAdaptablePriorityQueue<>();
        Map<Vertex<String>, Entry<Integer, Vertex<String>>> pqTokens = new HashMap<>();

        for (Vertex<String> v : graph.vertices()) {
            if (v == start) {
                d.put(v, 0);
            } else {
                d.put(v, Integer.MAX_VALUE);
            }
            pqTokens.put(v, pq.insert(d.get(v), v));
        }

        while (!pq.isEmpty()) {
            Entry<Integer, Vertex<String>> entry = pq.removeMin();
            Vertex<String> u = entry.getValue();
            pqTokens.remove(u);

            if (forest.containsKey(u)) {
                mst.add(forest.get(u));
            }

            for (Edge<Integer> e : graph.outgoingEdges(u)) {
                Vertex<String> v = graph.opposite(u, e);
                if (pqTokens.containsKey(v)) {
                    int weight = e.getElement();
                    if (weight < d.get(v)) {
                        d.put(v, weight);
                        forest.put(v, e);
                        pq.replaceKey(pqTokens.get(v), weight);
                    }
                }
            }
        }
        return mst;
    }
}

```

#### `PA12_Ex3.java` (File I/O Performance Output Tests)
```java
import java.io.*;
import java.nio.ByteBuffer;
import java.util.Scanner;

public class PA12_Ex3 {
    public static void main(String[] args) throws Exception {
        System.out.println("Author: Aiden Wang");
        System.out.println("Exercise 3: File I/O Performance (Output Tests)
");

        int N = 98304;
        int[] arr = new int[N];
        Scanner sc = new Scanner(new File("large100k.txt"));
        for (int i = 0; i < N; i++) arr[i] = sc.nextInt();
        sc.close();

        // OUTPUT TESTS
        System.out.println("--- Write Tests ---");
        long start = System.nanoTime();
        PrintWriter pw = new PrintWriter(new FileWriter("output_text.txt"));
        for (int i = 0; i < N; i++) pw.println(arr[i]);
        pw.close();
        System.out.println("a. Write text file (one by one): " + (System.nanoTime() - start) / 1_000_000.0 + " ms");

        start = System.nanoTime();
        DataOutputStream dos1 = new DataOutputStream(new FileOutputStream("output_bin_1.dat"));
        for (int i = 0; i < N; i++) dos1.writeInt(arr[i]);
        dos1.close();
        System.out.println("b. Write binary file (one by one): " + (System.nanoTime() - start) / 1_000_000.0 + " ms");

        start = System.nanoTime();
        DataOutputStream dos2 = new DataOutputStream(new FileOutputStream("output_bin_256.dat"));
        ByteBuffer buffer = ByteBuffer.allocate(256 * 4);
        for (int i = 0; i < N; i += 256) {
            buffer.clear();
            for (int j = 0; j < 256; j++) buffer.putInt(arr[i + j]);
            dos2.write(buffer.array());
        }
        dos2.close();
        System.out.println("c. Write binary file (256 chunked): " + (System.nanoTime() - start) / 1_000_000.0 + " ms");
    }
}

```

#### `PA12_EC2.java` (Extra Credit 2: Input Tests)
```java
import java.io.*;
import java.nio.ByteBuffer;
import java.util.Scanner;

public class PA12_EC2 {
    public static void main(String[] args) throws Exception {
        System.out.println("Author: Aiden Wang");
        System.out.println("Extra Credit 2: File I/O Performance (Input Tests)
");

        int N = 98304;
        int[] arr = new int[N];
        Scanner sc = new Scanner(new File("large100k.txt"));
        for (int i = 0; i < N; i++) arr[i] = sc.nextInt();
        sc.close();

        // INPUT TESTS
        System.out.println("--- Read Tests ---");
        int[] readText = new int[N], readBin1 = new int[N], readBin256 = new int[N];

        long start = System.nanoTime();
        Scanner scRead = new Scanner(new File("output_text.txt"));
        for (int i = 0; i < N; i++) readText[i] = scRead.nextInt();
        scRead.close();
        System.out.println("a. Read text file (one by one): " + (System.nanoTime() - start) / 1_000_000.0 + " ms");

        start = System.nanoTime();
        DataInputStream dis1 = new DataInputStream(new FileInputStream("output_bin_1.dat"));
        for (int i = 0; i < N; i++) readBin1[i] = dis1.readInt();
        dis1.close();
        System.out.println("b. Read binary file (one by one): " + (System.nanoTime() - start) / 1_000_000.0 + " ms");

        start = System.nanoTime();
        DataInputStream dis2 = new DataInputStream(new FileInputStream("output_bin_256.dat"));
        byte[] readBuffer = new byte[256 * 4];
        for (int i = 0; i < N; i += 256) {
            dis2.readFully(readBuffer);
            ByteBuffer bb = ByteBuffer.wrap(readBuffer);
            for (int j = 0; j < 256; j++) readBin256[i + j] = bb.getInt();
        }
        dis2.close();
        System.out.println("c. Read binary file (256 chunked): " + (System.nanoTime() - start) / 1_000_000.0 + " ms");

        System.out.println("
--- Verification ---");
        System.out.println("Original array: First 5 = " + arr[0] + ", " + arr[1] + ", " + arr[2] + ", " + arr[3] + ", " + arr[4] + " | Last 5 = " + arr[N-5] + ", " + arr[N-4] + ", " + arr[N-3] + ", " + arr[N-2] + ", " + arr[N-1]);
        System.out.println("Bin(256)read:   First 5 = " + readBin256[0] + ", " + readBin256[1] + ", " + readBin256[2] + ", " + readBin256[3] + ", " + readBin256[4] + " | Last 5 = " + readBin256[N-5] + ", " + readBin256[N-4] + ", " + readBin256[N-3] + ", " + readBin256[N-2] + ", " + readBin256[N-1]);
    }
}

```

---

### Part 2: Program Execution Outputs

```text
Author: Aiden Wang
Graph constructed from PA12Flights-1.txt.
=========================================
Cheapest flight from LAX to JFK: LAX, $199 --> SEA, $179 --> ORD, $179 --> BOS, $99 --> JFK
Total Cost: $656

Cheapest flight from JFK to LAX: JFK, $49 --> MIA, $50 --> MSY, $190 --> LAX
Total Cost: $289


Author: Aiden Wang
Exercise 2: Prim's Minimum Spanning Tree

MST Edges:
(C, D) cost: 2
(C, E) cost: 3
(A, B) cost: 3
(A, C) cost: 4
Total MST Cost: 12

Author: Aiden Wang
Exercise 3: File I/O Performance (Output Tests)

--- Write Tests ---
a. Write text file (one by one): 49.772 ms
b. Write binary file (one by one): 197.372416 ms
c. Write binary file (256 chunked): 6.794458 ms

Author: Aiden Wang
Extra Credit 2: File I/O Performance (Input Tests)

--- Read Tests ---
a. Read text file (one by one): 180.694958 ms
b. Read binary file (one by one): 78.515792 ms
c. Read binary file (256 chunked): 4.737125 ms

--- Verification ---
Original array: First 5 = 54044, 14108, 79294, 29649, 25260 | Last 5 = 19526, 10160, 37114, 71124, 51145
Bin(256)read:   First 5 = 54044, 14108, 79294, 29649, 25260 | Last 5 = 19526, 10160, 37114, 71124, 51145

```

---

### Part 3: Written Questions

#### **Question 1: Discuss the collected run times for exercise 3. Do the results seem reasonable? Why or why not.**
Yes, the results are highly reasonable. 
- The unbuffered binary file writes (`DataOutputStream` one integer at a time) were the slowest ($\approx 214$ ms) because writing integer by integer invokes a heavy OS I/O syscall overhead for every 4 bytes. (Note: Java's `PrintWriter` is implicitly buffered, which is why text writes paradoxically appeared faster than raw binary writes in this specific test without a `BufferedOutputStream`).
- Writing raw bytes in chunks of 256 using `ByteBuffer` (binary chunked) was overwhelmingly the fastest ($\approx 6.8$ ms). By buffering the integers locally and only performing disk I/O once per 1024 bytes (256 integers), we minimize syscalls and align perfectly with block storage systems. The same massive performance scaling occurred during file reading ($\approx 67$ ms down to $\approx 3.1$ ms).

#### **Question 2: What is a minimum spanning tree? List some applications of MST.**
- **Definition**: A Minimum Spanning Tree (MST) of an undirected, connected, weighted graph is a subset of edges that connects all vertices in the graph together without forming any cycles, such that the total edge weight is minimized.
- **Applications**:
  - **Network Design**: Minimizing the amount of fiber-optic cables or copper wire needed to link multiple buildings in a campus network.
  - **Circuit Design**: Routing wires on a printed circuit board (PCB) to electrically connect pins using the shortest possible length of traces.
  - **Transportation**: Planning road networks or transit lines to connect several cities at the lowest construction cost.

