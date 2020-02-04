package model.logic;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import model.data_structures.DobleListaEncadenada;

import model.data_structures.InterfazLista;
import model.data_structures.Node;

/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo {
	/**
	 * Atributos del modelo del mundo
	 */
	private InterfazLista datos;
	private Comparendos comparendo;
	private int size=0;

	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public Modelo()
	{
		datos = new DobleListaEncadenada();
		comparendo = new Comparendos(null, -1, null, null, null, null, null, null, null, null);
	}

	/**
	 * Servicio de consulta de numero de elementos presentes en el modelo 
	 * @return numero de elementos presentes en el modelo
	 */
	public int darTamano()
	{
		return datos.getSize();
	}

	/**
	 * Requerimiento de agregar dato
	 * @param dato
	 */
	public void agregar(Object i)
	{	
		datos.agregarAlFinal((Comparable) i);
	}

	/**
	 * Requerimiento eliminar dato
	 * @param dato Dato a eliminar
	 * @return dato eliminado
	 */

	public Integer eliminar(Integer dato)
	{
		int datoE = (Integer) datos.darElemento(dato);
		datos.eliminar(dato);

		return datoE;
	}

	public void loadComparendos (String comparendosFile)
	{
		JSONParser parser = new JSONParser();

		try {     
			Object obj = parser.parse(new FileReader(comparendosFile));

			JSONObject jsonObject =  (JSONObject) obj;
			JSONArray jsArray = (JSONArray) jsonObject.get("features");


			for(Object o: jsArray) {
				
				Object objetoP =  o;
				JSONObject prueba = (JSONObject) objetoP;
				
				String name = (String) prueba.get("type");
				comparendo.setTYPE(name);

				JSONObject properties =  (JSONObject) prueba.get("properties");

				comparendo.setOBJECTID(Integer.parseInt(properties.get("OBJECTID").toString()));

				comparendo.setFECHA_HORA((String)properties.get("FECHA_HORA"));
				comparendo.setCLASE_VEHI((String) properties.get("CLASE_VEHI"));
				comparendo.setTIPO_SERVI((String) properties.get("TIPO_SERVI"));
				comparendo.setINFRACCION((String) properties.get("INFRACCION"));
				comparendo.setDES_INFRAC((String) properties.get("DES_INFRAC"));
				comparendo.setLOCALIDAD((String) properties.get("LOCALIDAD"));

				JSONObject geometry =  (JSONObject) prueba.get("geometry");
				comparendo.setTYPE_GEO((String) geometry.get("type"));

				JSONArray coordinates = (JSONArray) geometry.get("coordinates");
				comparendo.setCOORDINATES(coordinates.toString());

				datos.agregarAlInicio(comparendo);
				
		}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e){
			e.printStackTrace();
		}
	}

	/**
	 * Requerimiento buscar dato
	 * @param dato Dato a buscar
	 * @return dato encontrado
	 */
	public String buscarID(int OBJECTID)
	{	
		Comparendos comparendo = null;
		
		Iterator <Comparendos> iter = datos.iterator();

		while(iter.hasNext()) {
			
			Comparendos comparador = iter.next();	
			System.out.println(comparador.getOBJECTID());
			
			if(comparendo.getOBJECTID()==OBJECTID)
			{
				comparendo=comparador;
		
			}

		}	
		return comparendo.getOBJECTID() + comparendo.getFECHA_HORA() + comparendo.getINFRACCION() 
		+ comparendo.getCLASE_VEHI() + comparendo.getTIPO_SERVI() + comparendo.getLOCALIDAD();	
	}

}
