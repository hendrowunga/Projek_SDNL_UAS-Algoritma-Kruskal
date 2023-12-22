package Projek_Akhir;

public class Edge  implements Comparable<Edge> {

    private String source;
    private String destination;
    private int weight;


    public Edge(String source, String destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public int getWeight() {
        return weight;
    }
     public int compareTo(Edge compareEdge) {
        return this.weight - compareEdge.weight;
    }
}
