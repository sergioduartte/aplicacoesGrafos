
public class Edge implements Comparable<Edge> {

    private String v1;
    private String v2;
    private double weight;
    private static final double DEFAULT_WEIGHT = 1.0;

    public Edge (String v1, String v2, double weight) {
        this.v1 = v1;
        this.v2 = v2;
        this.weight = weight;
    }

    public Edge (String v1, String v2) {
        this(v1,v2, DEFAULT_WEIGHT);
    }

    public String getV1() {
        return v1;
    }

    public String getV2() {
        return v2;
    }

    public double getWeight() {
        return weight;
    }



    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Edge)) {
            return false;
        }

        Edge edge = (Edge) o;

        if (edge.getV1().equals(this.getV1()) && edge.getV2().equals(this.getV2()) ||
                (edge.getV1().equals(this.getV2()) && edge.getV2().equals(this.getV1()))){
            return true;
        }

        return false;
    }

    @Override
    public int compareTo(Edge edge) {
        return this.v1.compareTo(edge.getV1());

    }

    public boolean isConnected(String vertex) {
        return vertex.equals(v2);
    }
}