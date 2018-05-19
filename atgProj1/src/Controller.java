import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * The class who represents the manipulation of a graph with implementations of popular algorithms
 *
 * @author sergiosd
 *
 */


public class Controller {

    private Graph graph;
    private String resultDsf;
    private int vertexLevel;

    public Controller() {
        this.resultDsf = "";
        this.vertexLevel = 0;
    }

    /**
     * Read and generate a graph with weighted edges, based on text file.
     * @param path The path .txt to file
     * @throws Exception cause of the problem.
     */
    public void readGraph (String path) throws Exception {

        ArrayList<String> lines = FReader.readFile(path);

        int qVertices = Integer.parseInt(lines.get(0));

        // a primeira linha informa o número
        // de vértices do grafo. Cada linha subsequente informa as arestas do mesmo.
        graph = new Graph(qVertices);

        for (int i = 1; i <= lines.size() - 1; i++) {
            graph.connectSimpleVertex(lines.get(i));
        }
    }

    /**
     * Read and generate a graph with weighted edges, based on text file
     * @param path The path .txt to file
     * @throws Exception cause of the problem
     */
    public void readWeightedGraph (String path) throws Exception {

        ArrayList<String> lines = FReader.readFile(path);

        int qVertices = Integer.parseInt(lines.get(0));

        graph = new Graph(qVertices);
        for (int i = 1; i <= lines.size() - 1; i++) {
            graph.connectWeightedVertex(lines.get(i));
        }
    }

    /**
     * Returns the Graph
     * @return the graph
     */
    public Graph getGraph() {

        return graph;
    }

    /**
     * Returns the quantity of the vertices
     * @param graph the graph who wants tho know the number of the vertices
     * @return the number of vertices
     */
    public int getVertexNumber (Graph graph) {

        return graph.getVertexNumber();
    }

    /**
     * Returns the quantity of the edges
     * @param graph the graph who wants tho know the number of the edges
     * @return the number of edges
     */
    public int getEdgeNumber (Graph graph) {

        return graph.getEdgeNumber();
    }

    /**
     * Returns the mean edge of the graph
     * @param graph the graph who wants tho know the mean edge
     * @return the mean edge
     */
    public double getMeanEdge (Graph graph) {

        return graph.getMeanEdge();
    }


    /**
     * Returns true if the graph is connected, false otherwise
     * @param graph the graph who wants tho know if it's connected
     * @return true if it's connected, false otherwise
     */
    public boolean connected(Graph graph) {
        return graph.connected();
    }

    /**
     * String representation of the graph based on type given.
     * @param graph The graph to be represented in a String.
     * @param type The type of representation.
     * @return String of the representation.
     * @throws Exception If type is invalid.
     */
    public String graphRepresentation (Graph graph, String type) throws Exception {
        if (type.toUpperCase().equals("AL")) {
            return graph.ALrepresentation();
        } else if (type.toUpperCase().equals("AM")) {
            return graph.AMrepresentation();
        } else throw new Exception("Invalid representation.");
    }

    /**
     * Return the Breadth-First Search representation of the graph
     * @param graph who wants to search
     * @param v the vertex who wants to find
     * @return the string representation of it
     */
    public String BFS (Graph graph, String v) {
        HashMap<String, String> graphBFS = graph.BFS(v);
        String result = "";

        Iterator it = graphBFS.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            result += pair.getKey() + "" + pair.getValue();
            it.remove();
        }

        int truncateIndex = result.length();
        truncateIndex = result.lastIndexOf('\n', truncateIndex - 1);
        return result.substring(0, truncateIndex);
    }

    /**
     * String representation of DFS (Depth-First Search) Algorithm. 
     * The algorithm visits all the vertices of the graph and shows its 
     * connection levels in the order of discovery.
     * @param graph The graph where will be made the search.
     * @param v the search's initial vertex.
     * @return String with the result for the DFS.
     */
    public String DFS (Graph graph, String v){
        HashMap<String, String> graphDFS = graph.DFS(v, 0, "-");
        String result = "";

        Iterator it = graphDFS.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            result += pair.getKey() + "" + pair.getValue();
            it.remove();
        }

        int truncateIndex = result.length();
        truncateIndex = result.lastIndexOf('\n', truncateIndex - 1);
        return result.substring(0, truncateIndex);
    }

    /**
     * Returns the shortest path between two vertices
     * @param v1 the first vertex
     * @param v2 the second vertex
     * @return the string representation of the shortest path
     */
    public String shortestPath (String v1, String v2) {
        return graph.shortestPath(v1, v2);
    }

    /**
     * Returns a minimum spanning tree representation of the graph
     * @param graph who wants to generate the mst
     * @return the string representation of the mst
     * @throws Exception if the graph is not connected
     */
    public String mst (Graph graph) throws Exception {
        HashMap<String, String> graphMST = graph.getMST();
        String result = "";

        Iterator it = graphMST.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            result += pair.getKey() + "" + pair.getValue();
            it.remove();
        }

        int truncateIndex = result.length();
        truncateIndex = result.lastIndexOf('\n', truncateIndex - 1);
        return result.substring(0, truncateIndex);
    }

}
