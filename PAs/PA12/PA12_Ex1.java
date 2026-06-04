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
        System.out.println("Total Cost: $" + d.get(destVertex) + "\n");
    }
}
