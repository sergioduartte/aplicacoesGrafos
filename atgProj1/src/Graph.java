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


    protected String ALrepresentation() {
        String result = "";
        for (int key : graph.keySet()) {
            result += key + " - ";
            ArrayList<Integer> edges = new ArrayList<>();
            for (Edge edge : graph.get(key)) {
                edges.add(edge.getV2());
            }
            Collections.sort(edges);
            result += edges.toString().replaceAll("\\[", "").replaceAll("]", "");
            result += System.getProperty("line.separator");
        }
        return result.trim();
    }

    protected String AMrepresentation() {
        //TODO
        return "";
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