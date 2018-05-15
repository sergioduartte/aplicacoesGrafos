import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class ControllerTest {

    private Controller controller;
    private final String path1 = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "graph1.txt"; // connected graph
    private final String path2 = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "graph2.txt"; // disconnected graph

    @Before
    public void prepareSimpleGraphFile() {
        List<String> lines = Arrays.asList("5", "2 2", "2 5", "5 3", "4 5", "1 5");
        Path file = Paths.get(path1);
        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (IOException e) {
            Assert.fail();
        }
    }

    @Before
    public void prepareSimpleDisconnectedGraphFile() {
        List<String> lines = Arrays.asList("10", "1 2", "2 5", "5 3", "4 5", "1 5");
        Path file = Paths.get(path2);
        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (IOException e) {
            Assert.fail();
        }
    }

    @Before
    public void createController() {
        controller = new Controller();
    }

    @Test
    public void testCreateSimpleGraphFromFile () {
        try {
            controller.readGraph(path1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetters() {
        try {
            controller.readGraph(path1);
        } catch (Exception e) {
            Assert.fail();
        }
        Assert.assertEquals(5, controller.getVertexNumber(controller.getGraph()));
        Assert.assertEquals(5, controller.getEdgeNumber(controller.getGraph()));
        Assert.assertEquals(2.0, controller.getMeanEdge(controller.getGraph()), 0.0);
    }


    @Test
    public void testConnection() {
        controller = new Controller();
        try {
            controller.readGraph(path1);
        } catch (Exception e) {
            Assert.fail();
        }
        Assert.assertTrue(controller.connected(controller.getGraph()));

        /*
        controller = new Controller();
        try {
            controller.readGraph(path2);
        } catch (Exception e) {
            Assert.fail();
        }
        Assert.assertFalse(controller.connected(controller.getGraph()));
        */
    }



    @Test
    public void testRepresentations() {
        try {
            controller.readGraph(path1);
        } catch (Exception e) {
            Assert.fail();
        }
        String expected = "1 - 2, 5" + System.getProperty("line.separator") +
                "2 - 1, 5" + System.getProperty("line.separator") +
                "3 - 5" + System.getProperty("line.separator") +
                "4 - 5" + System.getProperty("line.separator") +
                "5 - 1, 2, 3, 4";
        try {
            Assert.assertEquals(expected, controller.graphRepresentation(controller.getGraph(), "AL"));
        } catch (Exception e) {
            Assert.fail();
        }
    }
}