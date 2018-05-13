import java.util.HashMap;
import java.util.HashSet;

public class Graph {

    private HashMap<Integer, HashSet<Edge>> graph;

    public Graph () {
        this.graph = new HashMap<>();
    }
    
    public void createVertex(Integer vertex) throws Exception {
    	if( graph.containsKey(vertex) )
    		throw new Exception("Vertex already exists.");
    	
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
        
        Edge edgeOut = new Edge(out,in);
        Edge edgeIn = new Edge(in,out);
        
        this.graph.get(out).add(edgeOut);
        this.graph.get(in).add(edgeIn);

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

        int out = Integer.parseInt(line[0]);
        int in = Integer.parseInt(line[1]);
        double weight= Double.parseDouble(line[2]);
        getOrCreate(in, out); //Create vertex if not exists.
        
        Edge edgeOut = new Edge(out,in, weight);
        Edge edgeIn = new Edge(in,out, weight);
        
        this.graph.get(out).add(edgeOut);
        this.graph.get(in).add(edgeIn);

    }

    private void getOrCreate(int in, int out) throws Exception {
    	 if (!this.graph.containsKey(out))
         	createVertex(out);
         if (!this.graph.containsKey(in))
             createVertex(in);
	}
    
    public HashSet getEdges(int vertex) throws Exception {
    	if (!this.graph.containsKey(vertex))
         	throw new Exception("Vertex not found.");
    	return this.graph.get(vertex);
    }

	/**
     * The number of vertices
     * @return The number of the vertices
     */
    public int getSizeVertex() {
        return this.graph.size();
    }

    public int getSizeEdge() {
        //TODO
        return 0;
    }

    public String shortestPath(int v1, int v2) {
        //TODO
        return "";
    }
}