import net.datastructures.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class PA12_Ex2 {

    public static void main(String[] args) {
        System.out.println("Author: Aiden Wang");
        System.out.println("Exercise 2: Prim's Minimum Spanning Tree\n");

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
