import java.util.*;

public class Graph {

    private HashMap<Integer, HashSet<Edge>> graph;
    private final int qtVertices;
    private HashMap<Integer, Boolean> visited;

    public Graph (int vertices) {
        this.graph = new HashMap<>();
        this.visited = new HashMap<>();
        qtVertices = vertices;
    }

    public void createVertex(Integer vertex) throws Exception {
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

        int out = Integer.parseInt(line[0]);
        int in = Integer.parseInt(line[1]);

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

        int out = Integer.parseInt(line[0]);
        int in = Integer.parseInt(line[1]);
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

    private void getOrCreate(int in, int out) throws Exception {

        if (in != out) {

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
    
    public HashSet getEdges(int vertex) throws Exception {
    	if (!this.graph.containsKey(vertex))
         	throw new Exception("Vertex not found.");
    	return this.graph.get(vertex);
    }

    /**
     * Check if graph is connected;
     * @return True if is connected, false otherwise.
     */
    public boolean connected() {
        final boolean[] connected = new boolean[1];
        connected[0] = true;
        graph.forEach((key, value) -> connected[0] = connected[0] && !(value.size() == 0) );
        return connected[0];
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

        int[] vertices = getVerticesAsOrderedArray();

        ArrayList<Integer> edges;
        for (int vertex : vertices) {
            result += vertex + " - ";
            edges = new ArrayList<>();
            for (Edge edge : graph.get(vertex)) {
                if (!edges.contains(edge)){
                    edges.add(edge.getV2());
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
        int[][] matrix = new int[this.getVertexNumber()][this.getVertexNumber()];

        int[] lines = getVerticesAsOrderedArray();
        int[] columns = getVerticesAsOrderedArray();

        String li = " ";

        for (int k = 0; k < lines.length; k++) {
            li += "   " + lines[k];
        }

        String output = li + System.getProperty("line.separator");

        for (int line = 0; line < lines.length; line++) {
            for (int col = 0; col < lines.length; col++) {
                for (Edge edge : graph.get(lines[line])) {
                    if (edge.isConnected(lines[col])) {
                        matrix[line][col]++;
                    }
                }
            }
        }

        for (int i = 0; i < lines.length; i++) {
            output += lines[i];
            for (int j = 0; j < matrix[i].length ; j++) {
                output += "   " + matrix[i][j];
            }
            output += System.getProperty("line.separator");
        }


        return output;
    }

    private int[] getVerticesAsOrderedArray() {
        ArrayList<Integer> vertices = new ArrayList<>();

        for (Integer v: graph.keySet()) {
            vertices.add(v);
        }
        Collections.sort(vertices);

        int[] output = new int[vertices.size()];
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
}