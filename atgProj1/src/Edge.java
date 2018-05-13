public class Edge {

    private int v1;
    private int v2;
    private double weight;
    private static final double DEFAULT_WEIGHT = 1.0;

    public Edge (int v1, int v2, double weight) {
        this.v1 = v1;
        this.v2 = v2;
        this.weight = weight;
    }

    public Edge (int v1, int v2) {
        this(v1,v2, DEFAULT_WEIGHT);
    }
    
    @Override
    public boolean equals(Object o) {
    	if (o == null) 
    		return false;
        
    	Edge edge = (Edge) o;
        if (this.v1 != edge.v1 || this.v2 != edge.v2 || this.weight != edge.weight)
        	return false;
    	
    	return true;
    }
}