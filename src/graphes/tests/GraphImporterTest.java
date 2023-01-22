package graphes.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import graphes.ihm.Arc;
import graphes.ihm.GraphImporter;

public class GraphImporterTest {

	@Test
	public void test() {
		Arc a = GraphImporter.parse("1 -5 3");
		assertEquals(1, a.getSource());
		assertEquals(3, a.getDestination());
		assertEquals(-5, a.getValuation());
		assertThrows( IllegalArgumentException.class, () -> {
	           GraphImporter.parse("a1 -5 3");
	  });

	}
	

}
