/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Projek_Akhir;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

class Graph {

    int V;
    ArrayList<Edge> graph;

    Graph() {

    }

    public Graph(int vertices) {
        V = vertices;
        graph = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            graph.add(new Edge(null, null, 0)); // Tambahkan edge kosong sebagai placeholder
        }
    }

    public void addEdge(String u, String v, int w) {
        Edge edge = new Edge(u, v, w);
        graph.add(edge);
    }

    private String find(HashMap<String, String> parent, String i) {
        if (parent.get(i).equals(i)) {
            return i;
        }
        return find(parent, parent.get(i));
    }

    private void applyUnion(HashMap<String, String> parent, HashMap<String, Integer> rank, String x, String y) {
        String xroot = find(parent, x);
        String yroot = find(parent, y);

        if (rank.get(xroot) < rank.get(yroot)) {
            parent.put(xroot, yroot);
        } else if (rank.get(xroot) > rank.get(yroot)) {
            parent.put(yroot, xroot);
        } else {
            parent.put(yroot, xroot);
            rank.put(xroot, rank.get(xroot) + 1);
        }
    }

    public void kruskalAlgorithm() {
        ArrayList<Edge> result = new ArrayList<>();
        int i = 0, e = 0;
        Collections.sort(graph);

        HashMap<String, String> parent = new HashMap<>();
        HashMap<String, Integer> rank = new HashMap<>();

        for (Edge edge : graph) {
            parent.put(edge.getSource(), edge.getSource());
            parent.put(edge.getDestination(), edge.getDestination());
            rank.put(edge.getSource(), 0);
            rank.put(edge.getDestination(), 0);
        }

        while (e < V - 1) {
            Edge nextEdge = graph.get(i++);
            String x = find(parent, nextEdge.getSource());
            String y = find(parent, nextEdge.getDestination());

            if (!x.equals(y)) {
                e++;
                result.add(nextEdge);
                applyUnion(parent, rank, x, y);
            }
        }

        for (Edge edge : result) {
            System.out.println(edge.getSource() + " - " + edge.getDestination() + ": " + edge.getWeight());
        }
    }

    public ArrayList<Edge> calculateMST(ArrayList<Edge> edges) {
        ArrayList<Edge> mst = new ArrayList<>(edges.size());
        Collections.sort(edges); // Urutkan edges berdasarkan weight

        HashMap<String, String> parent = new HashMap<>();
        HashMap<String, Integer> rank = new HashMap<>();

        // Inisialisasi rank setiap node sebagai 0
        for (Edge edge : edges) {
            parent.put(edge.getSource(), edge.getSource());
            parent.put(edge.getDestination(), edge.getDestination());
            rank.put(edge.getSource(), 0);
            rank.put(edge.getDestination(), 0);
        }

        int edgeCount = 0;
        int i = 0;

        while (edgeCount < edges.size() - 1 && i < edges.size()) {
            Edge currentEdge = edges.get(i++);
            String srcParent = find(parent, currentEdge.getSource());
            String destParent = find(parent, currentEdge.getDestination());

            if (!srcParent.equals(destParent)) {
                mst.add(currentEdge);
                applyUnion(parent, rank, srcParent, destParent);
                edgeCount++;
            }
        }

        return mst;
    }

    public int calculateTotalWeight(ArrayList<Edge> mst) {
        int totalWeight = 0;
        for (Edge edge : mst) {
            totalWeight += edge.getWeight();
        }
        return totalWeight;
    }

    public int calculateTotalCost(ArrayList<Edge> mst) {
        int totalWeight = 0;
        for (Edge edge : mst) {
            totalWeight += edge.getWeight();
        }
        int pricePerKm = 3000;
        return totalWeight * pricePerKm;
    }

}
