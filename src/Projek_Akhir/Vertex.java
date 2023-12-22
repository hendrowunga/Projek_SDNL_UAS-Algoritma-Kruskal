
package Projek_Akhir;

import java.util.ArrayList;
import java.util.List;

public class Vertex {

    private String name;

    private List<Edge> edges;

    public Vertex(String name) {
        this.name = name;
        edges = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Edge> getEdges() {
        return edges;
    }
}
