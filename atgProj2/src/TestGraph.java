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
	    public void testSimpleGraphRepresentations() {

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
	            Assert.assertEquals(expected, grafo.graphRepresentation(grafo.getGraph(), RepresentationType.AL));
	        } catch (Exception e) {
	            Assert.fail();
	        }
	        try {
	            Assert.assertEquals(expectedAM, grafo.graphRepresentation(grafo.getGraph(), RepresentationType.AM));
	        } catch (Exception e) {
	            Assert.fail();
	        }
	    }

}
