import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.concurrent.ExecutionException;

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
     * Read and generate a graph with weighted edges, based on text file
     * @param path The path .txt to file
     * @throws Exception cause of the problem
     */

    public void readGraph (String path) throws Exception {

        ArrayList<String> lines = FReader.readFile(path);

        int qVertices = Integer.parseInt(lines.get(0));

        //a primeira linha informa o número
        // de vértices do grafo. Cada linha subsequente informa as arestas do mesmo.
        graph = new Graph(qVertices);

        for (int i = 1; i <= qVertices; i++) {
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
        for (int i = 1; i <= qVertices; i++) {
            graph.connectWeightedVertex(lines.get(i));
        }
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

    public String BFS (Graph graph, int v) {
        return "";
    }

    public String DFS (Graph graph, int v) throws Exception {
        HashSet<Edge> edges = graph.getEdges(v);

        this.resultDsf = "";

        if(!graph.getVertexStatus(v)) this.resultDsf = this.resultDsf + v + " - " + this.vertexLevel + " - " + System.getProperty("line.separator");

        graph.setVertexStatus(v);

        this.vertexLevel += 1;

        for (Edge e : edges) {

            if(!graph.getVertexStatus(e.getV2())){
                graph.setVertexStatus(e.getV2());
                this.resultDsf = this.resultDsf + e.getV2() + " - " + this.vertexLevel + " " + v + System.getProperty("line.separator") + DFS(graph, e.getV2());
            }
        }

        return this.resultDsf;
    }

    public String SCC (Graph graph) {
        return "";
    }

    public String mst (Graph graph) {
        return "";
    }

}