
public class Edge implements Comparable<Edge> {

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

    public int getV1() {
        return v1;
    }

    public int getV2() {
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

        if (edge.getV1() == this.getV1() && edge.getV2() == this.getV2() ||
                edge.getV1() == this.getV2() && edge.getV2() == this.getV1()) {

            return true;
        }

        return false;
    }

    @Override
    public int compareTo(Edge edge) {
        if (this.v1 < edge.getV1()) {
            return -1;
        } else if (this.v1 > edge.getV1()) {
            return 1;
        }
        return 0;
    }
}