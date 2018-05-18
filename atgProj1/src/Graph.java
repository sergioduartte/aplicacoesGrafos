import java.util.*;

public class Graph {

    private HashMap<String, HashSet<Edge>> graph;
    private final int qtVertices;
    private HashMap<Integer, Boolean> visited;
    private boolean weighted;


    public Graph (int vertices) {
        this.graph = new HashMap<>();
        this.visited = new HashMap<>();
        this.qtVertices = vertices;
        this.weighted = false;
    }

    public void createVertex(String vertex) throws Exception {
    	if( graph.containsKey(vertex) )
    		throw new Exception("Vertex already exists.");
    	if( this.qtVertices == graph.size() ) {
    	    throw new Exception("Vertex cannot be created. All the vertices already created");
        }
    	HashSet<Edge> edges = new HashSet<>();
        graph.put(vertex, edges);

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

        String out = line[0];
        String in = line[1];

        getOrCreate(in, out); //Create vertex if not exists.

        if (in != out) {

            Edge edgeOut = new Edge(out,in);
            Edge edgeIn = new Edge(in,out);

            this.graph.get(out).add(edgeOut);
            this.graph.get(in).add(edgeIn);
            return;

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

        Validator.validateCommands(vertices);
        String[] line = vertices.split(" ");

        this.weighted = true;

        String out = line[0];
        String in = line[1];
        double weight= Double.parseDouble(line[2]);
        getOrCreate(in, out); //Create vertex if not exists.


        if (in != out) {
            Edge edgeOut = new Edge(out, in, weight);
            Edge edgeIn = new Edge(in, out, weight);

            this.graph.get(out).add(edgeOut);
            this.graph.get(in).add(edgeIn);
            return;

        }

        Edge edgeOut = new Edge(out, in, weight);
        this.graph.get(out).add(edgeOut);

    }

    private void getOrCreate(String in, String out) throws Exception {

        if (!in.equals(out)) {

            if (!this.graph.containsKey(out)) {
                createVertex(out);
            }
            if (!this.graph.containsKey(in)) {
                createVertex(in);
            }
            return;
        }

        if (!this.graph.containsKey(out)) {
            createVertex(out);
        }
	}

    /**
     * Check if graph is connected;
     *
     * @return True if is connected, false otherwise.
     */
    public boolean connected() {
        String[] vertices = getVerticesAsOrderedArray();
        ArrayList<String> queue = new ArrayList<>();
        ArrayList<String> visited = new ArrayList<>();

        queue.add(vertices[0]);

        while (queue.size() != 0) {
            String vertex = queue.get(0);
            if (!visited.contains(vertex)) {
                visited.add(vertex);
                queue.addAll(getNeighbors(vertex));
            }
            queue.remove(0);
        }

        return vertices.length == visited.size();
    }

	/**
     * The number of vertex
     * @return The number of vertex
     */
    public int getVertexNumber() {
        return this.graph.size();
    }

    /**
     * The number of edges
     * @return The number of edges
     */
    public int getEdgeNumber() {
        return countEdges();
        // return getDoubledEdgeCount()/2;
    }

    private int countEdges() {
        ArrayList<Edge> aux = new ArrayList<>();
        for (HashSet<Edge> edges : graph.values()) {
            for (Edge edge: edges) {
                if (!aux.contains(edge)){
                    aux.add(edge);
                }
            }
            
        }

        return aux.size();
    }

    /**
     * The graph's average degree.
     * @return Graph's average degree.
     */
    public double getMeanEdge() {
        return 2 * this.countEdges()/ this.getVertexNumber();
    }


    /**
     * Returns a list representation from the graph, on the format <vertex - list of edges>
     * @return a String containing the representation.
     */
    protected String ALrepresentation() {

        String result = "";

        String[] vertices = getVerticesAsOrderedArray();

        ArrayList<String> edges;
        for (String vertex : vertices) {
            result += vertex + " - ";
            edges = new ArrayList<>();
            for (Edge edge : graph.get(vertex)) {
                if (!edges.contains(edge)) {
                    if (!weighted) {
                        edges.add(edge.getV2());
                    }
                    else {
                        edges.add(edge.getV2() + "(" + ((int) edge.getWeight() == edge.getWeight() ? String.valueOf((int) edge.getWeight()) : String.valueOf(edge.getWeight()))+ ")");
                    }
                }
            }

            Collections.sort(edges);

            for (int i = 0; i < edges.size(); i++) {
                result += edges.get(i) + " ";
            }

            result = result.trim();

            result += System.getProperty("line.separator");
        }
        return result.trim();
    }

    protected String AMrepresentation() {
        double[][] matrix = getGraphAsArray();

        String[] vertices = getVerticesAsOrderedArray();

        String li = " ";

        for (int k = 0; k < vertices.length; k++) {
            li += "   " + vertices[k];
        }

        String output = li + System.getProperty("line.separator");

        for (int i = 0; i < vertices.length; i++) {
            output += vertices[i];
            for (int j = 0; j < matrix[i].length ; j++) {
                output += "   ";
                output += (int) matrix[i][j] == matrix[i][j] ? String.valueOf((int) matrix[i][j]) : matrix[i][j];
            }
            output += System.getProperty("line.separator");
        }


        return output;
    }

    private double[][] getGraphAsArray() {
        double[][] matrix = new double[this.getVertexNumber()][this.getVertexNumber()];

        String[] vertices = getVerticesAsOrderedArray();

        for (int line = 0; line < vertices.length; line++) {
            for (int col = 0; col < vertices.length; col++) {
                for (Edge edge : graph.get(vertices[line])) {
                    if (edge.isConnected(vertices[col])) {
                        matrix[line][col] = edge.getWeight();
                    }
                }
            }
        }

        return matrix;
    }

    private String[] getVerticesAsOrderedArray() {
        ArrayList<String> vertices = new ArrayList<>();

        for (String v: graph.keySet()) {
            vertices.add(v);
        }
        Collections.sort(vertices);

        String[] output = new String[vertices.size()];
        for (int i = 0; i < output.length; i++) {
            output[i] = vertices.get(i);
        }
        return output;
    }

    public void setVertexStatus(Integer v){
        this.visited.put(v, true);
    }

    public boolean getVertexStatus(Integer v){
        return this.visited.containsKey(v) && this.visited.get(v);
    }

    public String shortestPath(int v1, int v2) {
        //TODO
        return "";
    }

    public HashSet<String> getNeighbors(String vertex) {
        HashSet<String> neighbors = new HashSet<>();
        for (Edge edge : graph.get(vertex)) {
            neighbors.add(edge.getV2());
        }
        return neighbors;
    }
}