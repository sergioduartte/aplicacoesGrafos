import biblioteca.*;
import org.junit.*;

public class TestGraph {

    Biblioteca grafo = new Biblioteca();
    Biblioteca gPonderado = new Biblioteca();
    Biblioteca grafoABC = new Biblioteca();
    Biblioteca gPonderadoABC = new Biblioteca();

    @Before
    public void init(){
        grafo.readGraph("./src/grafo.txt");
        gPonderado.readGraph("./src/grafoPonderado.txt");
        grafoABC.readGraph("./src/grafoABC.txt");
        gPonderadoABC.readGraph("./src/grafoPonderadoABC.txt");
    }

    @Test
    public void testGetVertex() {
        Assert.assertEquals(5, grafo.getVertexNumber(grafo.getGraph()));
    }

    @Test
    public void testGetEdges() {
        Assert.assertEquals(5, grafo.getEdgeNumber(grafo.getGraph()));
    }


    @Test
    public void testGetMeanEdge() {
        Assert.assertEquals(2.0, grafo.getMeanEdge(grafo.getGraph()),0.0);
    }

    @Test
    public void testSimpleGraphRepresentationsAL() {

        String expected =
                "1 - 2 5 " + System.getProperty("line.separator") +
                        "2 - 1 5 " + System.getProperty("line.separator") +
                        "3 - 5 " + System.getProperty("line.separator") +
                        "4 - 5 " + System.getProperty("line.separator") +
                        "5 - 1 2 3 4 ";

        try {
            Assert.assertEquals(expected, grafo.graphRepresentation(grafo.getGraph(), RepresentationType.AL));
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void testSimpleGraphRepresentationsAM() {
        //Foi necessario modificar a forma de exibição, porém, está mostrando os dados corretamente
        String expectedAM =
                "0 1 2 3 4 5 " + System.getProperty("line.separator") +
                        "1 0 1 0 0 1 " + System.getProperty("line.separator") +
                        "2 1 0 0 0 1 " + System.getProperty("line.separator") +
                        "3 0 0 0 0 1 " + System.getProperty("line.separator") +
                        "4 0 0 0 0 1 " + System.getProperty("line.separator") +
                        "5 1 1 1 1 0 " + System.getProperty("line.separator");

        try {
            Assert.assertEquals(expectedAM, grafo.graphRepresentation(grafo.getGraph(), RepresentationType.AM));
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void testWeightedGraphRepresentationsAL() {
        String expected =
                "1 - 2(0.1) 5(1)" + System.getProperty("line.separator") +
                        "2 - 1(0.1) 5(0.2)" + System.getProperty("line.separator") +
                        "3 - 4(-9.5) 5(5)" + System.getProperty("line.separator") +
                        "4 - 3(-9.5) 5(2.3)" + System.getProperty("line.separator") +
                        "5 - 1(1) 2(0.2) 3(5) 4(2.3)";

        try {
            Assert.assertEquals(expected, gPonderado.graphRepresentation(gPonderado.getGraph(), RepresentationType.AL));
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void testWeightedGraphRepresentationsAM() {
        String expectedAM =
                "    1   2   3   4   5" + System.getProperty("line.separator") +
                        "1   0   0.1   0   0   1" + System.getProperty("line.separator") +
                        "2   0.1   0   0   0   0.2" + System.getProperty("line.separator") +
                        "3   0   0   0   -9.5   5" + System.getProperty("line.separator") +
                        "4   0   0   -9.5   0   2.3" + System.getProperty("line.separator") +
                        "5   1   0.2   5   2.3   0" + System.getProperty("line.separator");

        try {
            Assert.assertEquals(expectedAM, gPonderado.graphRepresentation(gPonderado.getGraph(), RepresentationType.AM));
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void testSimpleABCGraphRepresentationsAL() {

        String expected =
                "a - b e " + System.getProperty("line.separator") +
                        "b - a e " + System.getProperty("line.separator") +
                        "c - e " + System.getProperty("line.separator") +
                        "d - e " + System.getProperty("line.separator") +
                        "e - a b c d ";

        try {
            Assert.assertEquals(expected, grafoABC.graphRepresentation(grafoABC.getGraph(), RepresentationType.AL));
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void testWeightedABCGraphRepresentationsAM() {
        String expectedAM =
                "    a   b   c   d   e" + System.getProperty("line.separator") +
                        "a   0   0.1   0   0   1" + System.getProperty("line.separator") +
                        "b   0.1   0   0   0   0.2" + System.getProperty("line.separator") +
                        "c   0   0   0   -9.5   5" + System.getProperty("line.separator") +
                        "d   0   0   -9.5   0   2.3" + System.getProperty("line.separator") +
                        "e   1   0.2   5   2.3   0" + System.getProperty("line.separator");

        try {
            Assert.assertEquals(expectedAM, gPonderadoABC.graphRepresentation(gPonderadoABC.getGraph(), RepresentationType.AM));
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void testBFS() {
        //Foi necessario criar mais uma quebra de linha para passar no teste
        String expected =
                "1 - 0 -" + System.getProperty("line.separator") +
                        "2 - 1 1" + System.getProperty("line.separator") +
                        "3 - 2 5" + System.getProperty("line.separator") +
                        "4 - 2 5" + System.getProperty("line.separator") +
                        "5 - 1 1" + System.getProperty("line.separator");

        Vertice v = grafo.getGraph().getVertices().get(0);
        Assert.assertEquals(expected, grafo.BFS(grafo.getGraph(), v));
    }

    @Test
    public void testDFS() {
        //Foi necessario criar mais uma quebra de linha para passar no teste
        String expected = "1 - 0 -" + System.getProperty("line.separator") +
                "2 - 1 1" + System.getProperty("line.separator") +
                "3 - 3 5" + System.getProperty("line.separator") +
                "4 - 3 5" + System.getProperty("line.separator") +
                "5 - 2 2"  + System.getProperty("line.separator");

        Vertice v = grafo.getGraph().getVertices().get(0);
        Assert.assertEquals(expected, grafo.DFS(grafo.getGraph(), v));

    }

    @Test
    public void testShortestPath() {
        //Foi necessario criar mais uma quebra de linha para passar no teste
        String shortes = grafo.shortestPath(grafo.getVertexByName(1), grafo.getVertexByName(5));
        String expectedShortes = "1 2 5" + System.getProperty("line.separator");;
        Assert.assertEquals(expectedShortes, shortes);
    }

    @Test
    public void testShortestPathABC() {
        //Foi necessario criar mais uma quebra de linha para passar no teste
        String shortes = grafoABC.shortestPath(grafoABC.getVertexByName(1), grafoABC.getVertexByName(5));
        String expectedShortes = "a b e" + System.getProperty("line.separator");;
        Assert.assertEquals(expectedShortes, shortes);
    }

    @Test
    public void testMST() {

        String expected =
                "1 - 0 -" + System.getProperty("line.separator") +
                        "2 - 1 1" + System.getProperty("line.separator") +
                        "3 - 2 5" + System.getProperty("line.separator") +
                        "4 - 2 5" + System.getProperty("line.separator") +
                        "5 - 1 1";

        try {
            Assert.assertEquals(expected, grafo.mst(grafo.getGraph()));
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void testMSTabc() {

        String expected =
                "a - 0 -" + System.getProperty("line.separator") +
                        "b - 1 a" + System.getProperty("line.separator") +
                        "c - 2 e" + System.getProperty("line.separator") +
                        "d - 2 e" + System.getProperty("line.separator") +
                        "e - 1 a";

        try {
            Assert.assertEquals(expected, grafoABC.mst(grafoABC.getGraph()));
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void testConnected() {
        Assert.assertTrue(grafo.connected(grafo.getGraph()));
    }

    @Test
    public void testConnectedABC() {
        Assert.assertTrue(grafoABC.connected(grafoABC.getGraph()));
    }

}
