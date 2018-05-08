import java.util.ArrayList;

public class Graph {

    private ArrayList<Edge> edges;
    private String name;

    public Graph (String name, ArrayList<Edge> edges) {
        this.name = name;
        this.edges = edges;
    }

    public Graph (String name) {
        this(name, new ArrayList<>());
    }

    public String shortestPath(int v1, int v2) {
        return "";
    }
}