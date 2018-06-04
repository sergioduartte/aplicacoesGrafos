import biblioteca.*;
import org.junit.*;

public class TestGraph {
	
	Biblioteca grafo = new Biblioteca();
	Biblioteca gPonderado = new Biblioteca();
	
	@Before
	public void init(){
		grafo.readGraph("./src/grafo.txt");
		gPonderado.readGraph("./src/grafoPonderado.txt");
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

}
