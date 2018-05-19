import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
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

    public Graph auxReadWeightedGraph (ArrayList<String> lines) throws Exception {
        int qVertices = Integer.parseInt(lines.get(0));

        Graph graph = new Graph(qVertices);
        for (int i = 1; i <= lines.size() - 1; i++) {
            graph.connectWeightedVertex(lines.get(i));
        }
        return graph;
    }

    public Graph getGraph() {

        return graph;
    }

    public int getVertexNumber (Graph graph) {

        return graph.getVertexNumber();
    }

    public int getEdgeNumber (Graph graph) {
        
        return graph.getEdgeNumber();
    }

    public double getMeanEdge (Graph graph) {
        
        return graph.getMeanEdge();
    }

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

    public String BFS (Graph graph, String v) {
       return graph.BFS(v);
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

        return result;
    }
    
    public String shortestPath (String v1, String v2) {
        return graph.shortestPath(v1, v2);
    }

    public String mst (Graph graph) throws Exception {
        return graph.getMST();
    }

}
