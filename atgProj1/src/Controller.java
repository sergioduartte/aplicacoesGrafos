import java.io.IOException;
import java.util.ArrayList;

/**
 * @author sergiosd
 *
 */


public class Controller {


    private Graph graph;

    /**
     * Read and generate a graph with weighted edges, based on text file
     * @param path The path .txt to file
     * @throws Exception cause of the problem
     */

    public void readGraph (String path) throws Exception {

        ArrayList<String> lines = FReader.readFile(path);

        int qLines = Integer.parseInt(lines.get(0));
        graph = new Graph();

        //a primeira linha informa o número
        // de vértices do grafo. Cada linha subsequente informa as arestas do mesmo.

        for (int i = 1; i < qLines; i++) {
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

        int qLines = Integer.parseInt(lines.get(0));
        graph = new Graph();

        for (int i = 1; i < qLines; i++) {
            graph.connectWeightedVertex(lines.get(i));
        }
    }


    public int getVertexNumber (Graph graph) {

        return graph.getVertexNumber();
    }

    public int getEdgeNumber (Graph graph) {

        return graph.getEdgeNumber();
    }

    public float getMeanEdge (Graph graph) {
        return 0;
    }

    public String graphRepresentation (Graph graph, String type) {
        return "";
    }

    public String BFS (Graph graph, int v) {
        return "";
    }

    public String DFS (Graph graph, int v) {
        return "";
    }

    public String SCC (Graph graph) {
        return "";
    }

    public String mst (Graph graph) {
        return "";
    }

}