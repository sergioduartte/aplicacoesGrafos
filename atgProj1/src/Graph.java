import java.util.*;

public class Graph {

    private HashMap<String, HashSet<Edge>> graph;
    private final int qtVertices;
    private HashMap<Integer, Boolean> visited;
    private boolean weighted;
    private boolean hasNegativeWeighted;
    private HashMap<String, String> resultDfs;

    private HashMap<String, Boolean> visitedDFS;


    public Graph (int vertices) {
        this.graph = new HashMap<>();
        this.visited = new HashMap<>();
        this.resultDfs = new HashMap<>();
        this.qtVertices = vertices;
        this.weighted = false;
        this.hasNegativeWeighted = false;
        this.visitedDFS = new HashMap<>();
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

    private boolean hasNegativeWeighted() {
        return this.hasNegativeWeighted;
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
        if(weight < 0)
            this.hasNegativeWeighted = true;
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
        return getAllEdges().size();
    }

    private ArrayList<Edge> getAllEdges() {
        ArrayList<Edge> aux = new ArrayList<>();
        for (HashSet<Edge> edges : graph.values()) {
            for (Edge edge: edges) {
                if (!aux.contains(edge)){
                    aux.add(edge);
                }
            }

        }
        return aux;
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

    public String shortestPath(String v1, String v2) {
        if( hasNegativeWeighted())
            return floyd(v1,v2);
        else
            return dijsktra(v1, v2);
    }

    public String floyd(String v1, String v2) {
        double[][] dist = new double[this.getVertexNumber()][this.getVertexNumber()];
        String[][] next = new String[this.getVertexNumber()][this.getVertexNumber()];

        // Fill each row with 1.0
        for (double[] row: dist)
            Arrays.fill(row, Double.POSITIVE_INFINITY);

        // Fill each row with null
        for (String[] row: next)
            Arrays.fill(row, null);;

        String[] vertices = getVerticesAsOrderedArray();

        for (int line = 0; line < vertices.length; line++) {
            for (int col = 0; col < vertices.length; col++) {
                dist[line][col] = this.getWeight(vertices[line], vertices[col]);
                next[line][col] = vertices[col];
            }
        }

        for (int k = 0; k < vertices.length; k++) {
            for (int i = 0; i < vertices.length; i++) {
                for (int j = 0; j < vertices.length; j++) {
                    if( dist[i][j] > dist[i][k] + dist[k][j] ) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        next[i][j] = next[i][k];
                    }
                }
            }
        }

        int u = java.util.Arrays.binarySearch(vertices, v1);
        int v = java.util.Arrays.binarySearch(vertices, v2);
        if ( next[u][v] == null)
            return "";
        else {
            ArrayList path = new ArrayList<String>();
            path.add(vertices[u]);
            while ( u != v) {
                u = java.util.Arrays.binarySearch(vertices, next[u][v]);
                path.add(vertices[u]);
            }

            return path.toString()
                    .replace(",", "")  //remove the commas
                    .replace("[", "")  //remove the right bracket
                    .replace("]", "")  //remove the left bracket
                    .trim();           //remove trailing spaces from partially initialized arrays
        }
    }


    public String dijsktra(String v1, String v2) {
        ArrayList listVertex = new ArrayList<String>();
        auxDijsktra(v1, v2, 0.0, listVertex);

        return listVertex.toString()
                .replace(",", "")  //remove the commas
                .replace("[", "")  //remove the right bracket
                .replace("]", "")  //remove the left bracket
                .trim();           //remove trailing spaces from partially initialized arrays

    }

    private void auxDijsktra(String v1, String vf, Double x, ArrayList listVertex) {
        if(v1.equals(vf))
            listVertex.add(v1);
        else {
            listVertex.add(v1);
            HashSet<String> neighbors = getNeighbors(v1);
            Double lowerWeight = Double.POSITIVE_INFINITY;
            String vertexLower = v1;
            for (String neighbor : neighbors) {
                boolean condition1 = getWeight(v1, neighbor) < lowerWeight + x;
                boolean condition2 = ! listVertex.contains(neighbor);
                if( condition1 && condition2) {
                    lowerWeight = getWeight(v1, neighbor);
                    vertexLower = neighbor;
                }
            }
            auxDijsktra(vertexLower, vf, lowerWeight + x, listVertex);
        }
    }

    private Double getWeight(String v1, String v2) {
        if (v1.equals(v2))
            return 0.0;
        HashSet<Edge> edges = this.graph.get(v1);
        for (Edge edge : edges) {
            if(edge.getV2().equals(v2))
                return edge.getWeight();
        }
        return Double.POSITIVE_INFINITY;
    }

    public HashSet<String> getNeighbors(String vertex) {
        HashSet<String> neighbors = new HashSet<>();
        for (Edge edge : graph.get(vertex)) {
            neighbors.add(edge.getV2());
        }
        return neighbors;
    }


    public String getMST() throws Exception {
        if (!connected()) throw new Exception("Graph is not connected. Can't do MST.");
        ArrayList<Edge> result = new ArrayList<>();
        ArrayList<Edge> edges = getAllEdges();
        String[] vertices = getVerticesAsOrderedArray();
        UnionFind uf = new UnionFind(vertices.length);

        Collections.sort(edges, Comparator.comparingDouble(Edge::getWeight));

        for (Edge edge : edges) {
            if (uf.find(Arrays.binarySearch(vertices, edge.getV1())) != uf.find(Arrays.binarySearch(vertices, edge.getV2()))) {
                result.add(edge);
                uf.union(Arrays.binarySearch(vertices, edge.getV1()), Arrays.binarySearch(vertices, edge.getV2()));
            }
        }

        Collections.sort(result);

        String result2 = "";
        for (Edge edge : result) {
            result2 += edge.getV1() + " " + edge.getV2() + " ";
            result2 += ((int) edge.getWeight() == edge.getWeight() ? String.valueOf((int) edge.getWeight()) : String.valueOf(edge.getWeight()));
            result2 += System.getProperty("line.separator");
        }

        return result2.trim();

    }
    public String BFS(String root) {
        boolean visited[] = new boolean[getVertexNumber()];

        int level = 0;
        String dad = "-";
        String[] vertices = getVerticesAsOrderedArray();

        ArrayList<String> output = new ArrayList<>();
        ArrayList<ArrayList<String>>  listOut = new ArrayList<>();

        // Create a queue for BFS
        LinkedList<String> queue = new LinkedList<String>();

        // Mark the current node as visited and enqueue it
        visited[java.util.Arrays.binarySearch(vertices, root)]=true;

        queue.add(root);


        ArrayList<String[]> saida = new ArrayList<>();


        while (queue.size() != 0){
            // Dequeue a vertex from queue and print it
            String aux = root;
            root = queue.poll();
            if( queue.size() == 1)
                level++;


            saida.add(new String[] {root, "- "+ level,"" +dad});

            dad = aux;


            Iterator<String> i = getNeighbors(root).iterator();
            while (i.hasNext()){
                String n = i.next();
                if (!visited[java.util.Arrays.binarySearch(vertices, n)]){
                    visited[java.util.Arrays.binarySearch(vertices, n)] = true;
                    queue.add(n);
                }
            }

        }

        Collections.sort(saida, Comparator.comparing(o -> o[0]));

        String saida2 = "";

        for (int i = 0; i < saida.size() ; i++) {
            saida2 += saida.get(i)[0] +" ";
            saida2 += saida.get(i)[1] +" ";
            saida2 += saida.get(i)[2] +" ";
            saida2 += System.getProperty("line.separator");
        }

        return saida2;

    }

    /**
     *
     * @param s The initial vertex.
     * @param level The vertex's level.
     * @param parent The vertex used to get to the vertex 's'.
     * @return Result of the DFS in form of a HashMap, where the key will be a vertex and the value will be a String with the vertex's level and parent.
     */

    public HashMap<String, String > DFS(String s, Integer level, String parent){
        String[] vertices = getVerticesAsOrderedArray();
        HashSet<String> edges = getNeighbors(s);

        if(!visitedDFS.containsKey(s)){
            resultDfs.put(s, " - " + level + " " + parent + System.getProperty("line.separator"));
        }

        visitedDFS.put(s, true);
        for(String e: edges){
            if(!visitedDFS.containsKey(e)){
                DFS(e, level + 1, s);
            }
        }

        return resultDfs;
    }


}