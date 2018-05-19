import org.junit.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class GraphTest {


	@Test
	public void createVertex () {
		Graph graph = new Graph(2);
		try {
			graph.createVertex("1");
			graph.createVertex("1");
		} catch (Exception e) {
			String message = "Vertex already exists.";
			Assert.assertEquals(true, e.getMessage().equals(message));
		}
	}

	@Test
	public void connectSimpleVertex1 () {
		Graph graph = new Graph(2);
		try {
			String vertices = "a b c";
			graph.connectSimpleVertex(vertices);
		} catch (Exception e) {
			String message = "Cannot parse args. Connection dont created.";
			Assert.assertEquals(true, e.getMessage().equals(message));
		}
	}

	@Test
	public void connectSimpleVertex2 () {
		Graph graph = new Graph(2);
		try {
			String vertices = "1 2 3 4";
			graph.connectSimpleVertex(vertices);
		} catch (Exception e) {
			String message = "Cannot create a connection. Exceeding arguments";
			Assert.assertEquals(true, e.getMessage().equals(message));
		}
	}

	@Test
	public void connectSimpleVertex3 () {
		Graph graph = new Graph(2);
		try {
			String vertices = "";
			graph.connectSimpleVertex(vertices);
		} catch (Exception e) {
			String message = "Cannot create a connection. Missing arguments";
			Assert.assertEquals(true, e.getMessage().equals(message));
		}
	}
    
    /*@Test
    public void connectSimpleVertex4 () {
    	Graph graph = new Graph(2);
    	Edge edgeOut = new Edge("1","2");
        Edge edgeIn = new Edge("2","1");
    	try {
    		String vertices = "1 2";
    		graph.connectSimpleVertex(vertices);
    		HashSet<Edge> edgesOut = graph.getEdges(1);
    		HashSet<Edge> edgesIn = graph.getEdges(2);

    		for ( Iterator<Edge> i = edgesOut.iterator(); i.hasNext();) {
    			Edge e = i.next();
    			Assert.assertEquals(true, e.equals(edgeOut));
    		}

    		for ( Iterator<Edge> i = edgesIn.iterator(); i.hasNext();) {
    			Edge e = i.next();
    			Assert.assertEquals(true, e.equals(edgeIn));
    		}
		} catch (Exception e) {

		}
    }*/


}