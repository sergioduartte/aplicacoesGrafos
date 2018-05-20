import org.junit.Assert;
import org.junit.Test;

public class GraphTest {


	@Test
	public void createVertex () {
		Graph graph = new Graph(2);
		try {
			graph.createVertex("1");
			graph.createVertex("1");
            Assert.fail();
        } catch (Exception e) {
            String message = "Vertex already exists.";
            Assert.assertEquals(message, e.getMessage());
        }
    }

	@Test
	public void connectSimpleVertex1 () {
		Graph graph = new Graph(2);
		try {
			String vertices = "a b c";
			graph.connectSimpleVertex(vertices);
            Assert.fail();
        } catch (Exception e) {
            String message = "Cannot create a connection. For input string: \"c\"";
            Assert.assertEquals(message, e.getMessage());
        }
    }

	@Test
	public void connectSimpleVertex2 () {
		Graph graph = new Graph(2);
		try {
			String vertices = "1 2 3 4";
			graph.connectSimpleVertex(vertices);
            Assert.fail();
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
            Assert.fail();
        } catch (Exception e) {
            String message = "Cannot create a connection. Missing arguments";
            Assert.assertEquals(true, e.getMessage().equals(message));
		}
	}

    @Test
    public void connectSimpleVertex4 () {
        Graph graph = new Graph(2);
        try {
            String vertices = "a b";
            graph.connectSimpleVertex(vertices);
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void connectSimpleVertex5() {
        Graph graph = new Graph(2);
        try {
            String vertices = "1 2";
            graph.connectSimpleVertex(vertices);
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void connectWeightedVertex1() {
        Graph graph = new Graph(2);
        try {
            String vertices = "a b c";
            graph.connectWeightedVertex(vertices);
            Assert.fail();
        } catch (Exception e) {
            String message = "Cannot create a connection. For input string: \"c\"";
            Assert.assertEquals(message, e.getMessage());
        }
    }

    @Test
    public void connectWeightedVertex2() {
        Graph graph = new Graph(2);
        try {
            String vertices = "1 2 3 4";
            graph.connectWeightedVertex(vertices);
            Assert.fail();
        } catch (Exception e) {
            String message = "Cannot create a connection. Exceeding arguments";
            Assert.assertEquals(true, e.getMessage().equals(message));
        }
    }

    @Test
    public void connectWeightedVertex3() {
        Graph graph = new Graph(2);
        try {
            String vertices = "";
            graph.connectWeightedVertex(vertices);
            Assert.fail();
        } catch (Exception e) {
            String message = "Cannot create a connection. Missing arguments";
            Assert.assertEquals(true, e.getMessage().equals(message));
        }
    }

    @Test
    public void connectWeightedVertex4() {
        Graph graph = new Graph(2);
        try {
            String vertices = "a b 4.6";
            graph.connectWeightedVertex(vertices);
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void connectWeightedVertex5() {
        Graph graph = new Graph(2);
        try {
            String vertices = "1 2 -6.6";
            graph.connectSimpleVertex(vertices);
        } catch (Exception e) {
            Assert.fail();
        }
    }
}