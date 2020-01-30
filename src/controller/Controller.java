package controller;

import java.util.Scanner;

import model.logic.Modelo;
import view.View;

public class Controller {

	/* Instancia del Modelo*/
	private Modelo modelo;
	
	/* Instancia de la Vista*/
	private View view;
	
	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new View();
		modelo = new Modelo();
	}
		
	public void run() 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		Integer dato = null;
		String respuesta = "";

		while( !fin ){
			view.printMenu();

			int option = lector.nextInt();
			switch(option){
				case 1:
					System.out.println("--------- \nCrear Arreglo \nDar capacidad inicial del arreglo: ");
				    Integer capacidad = lector.nextInt();
				    modelo = new Modelo(capacidad); 
					System.out.println("Arreglo Dinamico creado");
					System.out.println("Numero actual de elementos " + modelo.darTamano() + "\n---------");						
					break;

				case 2:
					System.out.println("--------- \nDar cadena (simple) a ingresar: ");
					dato = lector.nextInt();
					modelo.agregar(dato);
					System.out.println("Dato agregado");
					System.out.println("Numero actual de elementos " + modelo.darTamano() + "\n---------");						
					break;

				case 3:
					System.out.println("--------- \nDar cadena (simple) a buscar: ");
					dato = lector.nextInt();
					respuesta = ""+ modelo.buscar(dato);
					if ( respuesta != null)
					{
						System.out.println("Dato encontrado: "+ respuesta);
					}
					else
					{
						System.out.println("Dato NO encontrado");
					}
					System.out.println("Numero actual de elementos " + modelo.darTamano() + "\n---------");						
					break;

				case 4:
					System.out.println("--------- \nDar cadena (simple) a eliminar: ");
					dato = lector.nextInt();
					respuesta = ""+ modelo.eliminar(dato);
					if ( respuesta != null)
					{
						System.out.println("Dato eliminado "+ respuesta);
					}
					else
					{
						System.out.println("Dato NO eliminado");							
					}
					System.out.println("Numero actual de elementos " + modelo.darTamano() + "\n---------");						
					break;

				case 5: 
					System.out.println("--------- \nContenido del Arreglo: ");
					view.printModelo(modelo);
					System.out.println("Numero actual de elementos " + modelo.darTamano() + "\n---------");						
					break;	
					
				case 6: 
					System.out.println("--------- \n Hasta pronto !! \n---------"); 
					lector.close();
					fin = true;
					break;	

				default: 
					System.out.println("--------- \n Opcion Invalida !! \n---------");
					break;
			}
		}
	}
	}	