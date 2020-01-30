package test.data_structures;

import model.data_structures.ArregloDinamico;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestArregloDinamico {

	private ArregloDinamico arreglo;
	private static int TAMANO=100;
	
	@Before
	public void setUp1() {
		arreglo= new ArregloDinamico(TAMANO);
	}

	public void setUp2() {
		for(int i =0; i< TAMANO*2; i++){
			arreglo.agregar(i);
		}
	}

	@Test
	public void testArregloDinamico() {
		setUp2();
		assertEquals(200,arreglo.darTamano());
		// TODO
	}

	@Test
	public void testDarElemento() {
		setUp2();
		assertEquals(null,arreglo.buscar(1000));
		assertEquals(199,arreglo.buscar(199));
		
		// TODO
	}

}
