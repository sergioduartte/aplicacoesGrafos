import java.util.HashMap;
import java.util.HashSet;

public class Graph {

    private HashMap<Integer, HashSet<Edge>> graph;

    public Graph () {
        this.graph = new HashMap<>();
    }


    /**
     * Connect a new vertex, if the old vertex exists, add the edge from the new to the old vertex.
     * Otherwise, add the new pair of connected vertices.
     * @param vertices vertices at the format "a b" where a is the out vertex and b is the in vertex.
     * @throws Exception if the commands are wrong, with the cause.
     */
    public void connectSimpleVertex(String vertices) throws Exception {

        // valida se a linha esta ok, vai pra outro lugar depois do refatoramento
        Validator.validateCommands(vertices);

        String[] line = vertices.split(" ");

        Edge e;
        int out = Integer.parseInt(line[0]);
        int in = Integer.parseInt(line[1]);

        if (!graph.containsKey(out)){
            HashSet<Edge> set = new HashSet<>();
            e = new Edge(out,in);
            set.add(e);
            graph.put(out, set);
        } else {
            e = new Edge(out,in);
            graph.get(out).add(e);
        }

        if (!graph.containsKey(in)){
            HashSet<Edge> set = new HashSet<>();
            e = new Edge(in,out);
            set.add(e);
            graph.put(in, set);
        } else {
            e = new Edge(in,out);

            graph.get(in).add(e);
        }
    }

    /**
     * Connect a new vertex, if the old vertex exists, add the edge from the new to the old vertex.
     * Otherwise, add the new pair of connected vertices.
     * @param vertices vertices at the format "a b c" where a is the out vertex, b is the in vertex and
     *                 c is the weight of the edge.
     * @throws Exception if the commands are wrong, with the cause.
     */
    public void connectWeightedVertex(String vertices) throws Exception {

        String[] line = vertices.split(" ");

        Validator.validateCommands(vertices);

        Edge e;
        int out = Integer.parseInt(line[0]);
        int in = Integer.parseInt(line[1]);
        double weight= Double.parseDouble(line[2]);

        if (!graph.containsKey(out)){
            HashSet<Edge> set = new HashSet<>();
            e = new Edge(out,in, weight);
            set.add(e);
            graph.put(out, set);
        } else {
            e = new Edge(out,in, weight);
            graph.get(out).add(e);
        }

        if (!graph.containsKey(in)){
            HashSet<Edge> set = new HashSet<>();
            e = new Edge(in,out, weight);
            set.add(e);
            graph.put(in, set);
        } else {
            e = new Edge(in,out, weight);

            graph.get(in).add(e);
        }
    }

    /**
     * The number of vertices
     * @return The number of the vertices
     */
    public int getVertexNumber() {
        return this.graph.size();
    }

    public int getEdgeNumber() {
        //TODO
        return 0;
    }

    public String shortestPath(int v1, int v2) {
        //TODO
        return "";
    }
}