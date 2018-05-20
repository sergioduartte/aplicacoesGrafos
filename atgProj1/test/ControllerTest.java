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
    private final String path1 = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "graph1.txt"; // connected simple graph
    private final String path2 = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "graph2.txt"; // disconnected simple graph
    private final String path3 = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "graph3.txt"; // connected weighted graph 123
    private final String path4 = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "graph4.txt"; // connected weighted graph abc
    private final String path5 = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "graph5.txt"; // connected simple graph abc

    @Before
    public void prepareSimpleGraphFile() {
        List<String> lines = Arrays.asList("5", "1 2", "2 5", "5 3", "4 5", "1 5");
        Path file = Paths.get(path1);
        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (IOException e) {
            Assert.fail();
        }
    }

    @Before
    public void prepareWeightedGraphFile() {
        List<String> lines = Arrays.asList("5", "1 2 0.1", "2 5 0.2", "5 3 5", "3 4 -9.5", "4 5 2.3", "1 5 1");
        Path file = Paths.get(path3);
        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (IOException e) {
            Assert.fail();
        }
    }

    @Before
    public void prepareSimpleDisconnectedGraphFile() {
        List<String> lines = Arrays.asList("10", "1 2", "2 5", "5 3", "4 5", "1 5", "6 7", "8 9", "9 10");
        Path file = Paths.get(path2);
        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (IOException e) {
            Assert.fail();
        }
    }

    @Before
    public void prepareWeightedGraphFileABC() {
        List<String> lines = Arrays.asList("5", "a b 0.1", "b e 0.2", "e c 5", "c d -9.5", "d e 2.3", "a e 1");
        Path file = Paths.get(path4);
        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (IOException e) {
            Assert.fail();
        }
    }

    @Before
    public void prepareSimpleGraphFileABC() {
        List<String> lines = Arrays.asList("5", "a b", "b e", "e c", "d e", "a e");
        Path file = Paths.get(path5);
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
            Assert.fail();
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


        controller = new Controller();
        try {
            controller.readGraph(path2);
        } catch (Exception e) {
            Assert.fail();
        }
        Assert.assertFalse(controller.connected(controller.getGraph()));

    }



    @Test
    public void testSimpleGraphRepresentations() {
        try {
            controller.readGraph(path1);
        } catch (Exception e) {
            Assert.fail();
        }

        String expected = "1 - 2 5" + System.getProperty("line.separator") +
                "2 - 1 5" + System.getProperty("line.separator") +
                "3 - 5" + System.getProperty("line.separator") +
                "4 - 5" + System.getProperty("line.separator") +
                "5 - 1 2 3 4";

        String expectedAM =
                "    1   2   3   4   5" + System.getProperty("line.separator") +
                        "1   0   1   0   0   1" + System.getProperty("line.separator") +
                        "2   1   0   0   0   1" + System.getProperty("line.separator") +
                        "3   0   0   0   0   1" + System.getProperty("line.separator") +
                        "4   0   0   0   0   1" + System.getProperty("line.separator") +
                        "5   1   1   1   1   0" + System.getProperty("line.separator");


        try {
            Assert.assertEquals(expected, controller.graphRepresentation(controller.getGraph(), "AL"));
        } catch (Exception e) {
            Assert.fail();
        }
        try {
            Assert.assertEquals(expectedAM, controller.graphRepresentation(controller.getGraph(), "AM"));
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void testWeightedGraphRepresentations() {
        try {
            controller.readWeightedGraph(path3);
        } catch (Exception e) {
            Assert.fail();
        }

        String expected = "1 - 2(0.1) 5(1)" + System.getProperty("line.separator") +
                "2 - 1(0.1) 5(0.2)" + System.getProperty("line.separator") +
                "3 - 4(-9.5) 5(5)" + System.getProperty("line.separator") +
                "4 - 3(-9.5) 5(2.3)" + System.getProperty("line.separator") +
                "5 - 1(1) 2(0.2) 3(5) 4(2.3)";

        String expectedAM =
                "    1   2   3   4   5" + System.getProperty("line.separator") +
                        "1   0   0.1   0   0   1" + System.getProperty("line.separator") +
                        "2   0.1   0   0   0   0.2" + System.getProperty("line.separator") +
                        "3   0   0   0   -9.5   5" + System.getProperty("line.separator") +
                        "4   0   0   -9.5   0   2.3" + System.getProperty("line.separator") +
                        "5   1   0.2   5   2.3   0" + System.getProperty("line.separator");


        try {
            Assert.assertEquals(expected, controller.graphRepresentation(controller.getGraph(), "AL"));
        } catch (Exception e) {
            Assert.fail();
        }

        try {
            Assert.assertEquals(expectedAM, controller.graphRepresentation(controller.getGraph(), "AM"));
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void testWeightedGraphRepresentationsABC() {
        try {
            controller.readWeightedGraph(path4);
        } catch (Exception e) {
            Assert.fail();
        }

        String expected = "a - b(0.1) e(1)" + System.getProperty("line.separator") +
                "b - a(0.1) e(0.2)" + System.getProperty("line.separator") +
                "c - d(-9.5) e(5)" + System.getProperty("line.separator") +
                "d - c(-9.5) e(2.3)" + System.getProperty("line.separator") +
                "e - a(1) b(0.2) c(5) d(2.3)";

        String expectedAM =
                "    a   b   c   d   e" + System.getProperty("line.separator") +
                        "a   0   0.1   0   0   1" + System.getProperty("line.separator") +
                        "b   0.1   0   0   0   0.2" + System.getProperty("line.separator") +
                        "c   0   0   0   -9.5   5" + System.getProperty("line.separator") +
                        "d   0   0   -9.5   0   2.3" + System.getProperty("line.separator") +
                        "e   1   0.2   5   2.3   0" + System.getProperty("line.separator");


        try {
            Assert.assertEquals(expected, controller.graphRepresentation(controller.getGraph(), "AL"));
        } catch (Exception e) {
            Assert.fail();
        }

        try {
            Assert.assertEquals(expectedAM, controller.graphRepresentation(controller.getGraph(), "AM"));
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void testShortestPath() {
        try {
            controller.readWeightedGraph(path3);
        } catch (Exception e) {
            Assert.fail();
        }

        String shortes = controller.shortestPath("1", "5");
        String expectedShortes = "1 2 5";

        Assert.assertEquals(expectedShortes, shortes);
    }

    @Test
    public void testShortestPathABC() {
        try {
            controller.readWeightedGraph(path4);
        } catch (Exception e) {
            Assert.fail();
        }

        String shortes = controller.shortestPath("a", "e");
        String expectedShortes = "a b e";

        Assert.assertEquals(expectedShortes, shortes);
    }

    @Test
    public void testMST() {
        try {
            controller.readGraph(path1);
        } catch (Exception e) {
            Assert.fail();
        }

        String expected = "1 - 0 -" + System.getProperty("line.separator") +
                "2 - 1 1" + System.getProperty("line.separator") +
                "3 - 2 5" + System.getProperty("line.separator") +
                "4 - 2 5" + System.getProperty("line.separator") +
                "5 - 1 1";

        try {
            Assert.assertEquals(expected, controller.mst(controller.getGraph()));
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void testMSTabc() {
        try {
            controller.readGraph(path5);
        } catch (Exception e) {
            Assert.fail();
        }

        String expected = "a - 0 -" + System.getProperty("line.separator") +
                "b - 1 a" + System.getProperty("line.separator") +
                "c - 2 e" + System.getProperty("line.separator") +
                "d - 2 e" + System.getProperty("line.separator") +
                "e - 1 a";

        try {
            Assert.assertEquals(expected, controller.mst(controller.getGraph()));
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void testMSTnotConnected() {
        try {
            controller.readGraph(path2);
        } catch (Exception e) {
            Assert.fail();
        }

        try {
            controller.mst(controller.getGraph());
            Assert.fail();
        } catch (Exception e) {
            Assert.assertEquals("Graph is not connected. Can't do MST.", e.getMessage());
        }
    }

    @Test
    public void testBFS() {
        try {
            controller.readGraph(path1);
        } catch (Exception e) {
            Assert.fail();
        }

        String expected = "1 - 0 -" + System.getProperty("line.separator") +
                "2 - 1 1" + System.getProperty("line.separator") +
                "3 - 2 5" + System.getProperty("line.separator") +
                "4 - 2 5" + System.getProperty("line.separator") +
                "5 - 1 1";

        Assert.assertEquals(expected, controller.BFS(controller.getGraph(), "1"));
    }

    @Test
    public void testDFS() {
        try {
            controller.readGraph(path1);
        } catch (Exception e) {
            Assert.fail();
        }

        String expected = "1 - 0 -" + System.getProperty("line.separator") +
                "2 - 1 1" + System.getProperty("line.separator") +
                "3 - 3 5" + System.getProperty("line.separator") +
                "4 - 3 5" + System.getProperty("line.separator") +
                "5 - 2 2";

        Assert.assertEquals(expected, controller.DFS(controller.getGraph(), "1"));
    }
}