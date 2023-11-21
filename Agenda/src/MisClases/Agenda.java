package MisClases;

import java.util.Scanner;

public class Agenda {
//	ATRIBUTOS
	DatosPersona[] agenda;
	Scanner teclado;
	
	Agenda(int numEntradas){
		
		agenda=new DatosPersona[numEntradas];
	    teclado=new Scanner(System.in);
	}
	
//	METODOS 
	
	int insertarEntrada() {
		
		boolean encontrado=false;
		int i=0;
//		BUSCAMOS EL PRIMER HUECO VACIO EN LA AGENDA
		for (i=0; i<agenda.length; i++) {
			if (agenda[i]==null) {
				encontrado=true;
				break;
			}
		}
		
		if(encontrado==true) {
//			HE ECONTRADO EL ELEMENTO
//			LE PEDIMOS AL USUARIO LOS DATOS DEL NUEVO REGISTRO
			
			System.out.println("Introduce el nombre");
			String nombre=teclado.nextLine();
			System.out.println("Introduce la direccion");
			String direccion=teclado.nextLine();
			System.out.println("Introduce el número fijo");
			String fijo=teclado.nextLine();
			System.out.println("Introduce el número móvil");
			String movil=teclado.nextLine();
			System.out.println("Introduce el email");
			String eMail=teclado.nextLine();
			
//			CREAMOS UN OBJETO DE LA CLASE DatosPersona
			DatosPersona nuevoRegistro = new DatosPersona(nombre,direccion,fijo,movil,eMail);
			
//			INSERTO EL OBJETO EN LA POSICION BUSCADA EN LA AGENDA
			agenda[i]=nuevoRegistro;
			return i;	
		}
		else {
//			LA AGENDA ESTABA LLENA 
			return -1;
		}
		
	}
	
	
	public void compactarAgenda() {
		
		DatosPersona[] arAuxiliar=new DatosPersona[agenda.length];
		
		int j=0;
		
		for (int i=0; i<agenda.length; i++) {
			if(agenda[i]!=null) {
				arAuxiliar[i]=agenda[i];
				
				j++;
			}
		}
		agenda=arAuxiliar;
	}
	
	
//	-------------------------------------
//		ASI PODEMOS PONER COMENTARIOS E INDICAR ACCIONES
//	-------------------------------------
	public void buscarAgenda() {
		
		System.out.println("Introduce el término de búsqueda : ");
		String termino=teclado.nextLine();
		for(int i=0; i<agenda.length;i++) {
			if(agenda[i]!=null) {
				boolean deboMostrar=false;
				if(agenda[i].getNombre().toUpperCase().contains(termino))
					deboMostrar=true;
				else if(agenda[i].getNumMovil().toUpperCase().contains(termino))
					deboMostrar=true;
				else if(agenda[i].getNumFijo().toUpperCase().contains(termino))
					deboMostrar=true;
				else if(agenda[i].geteMail().toUpperCase().contains(termino))
					deboMostrar=true;
				
				if(deboMostrar==true);
				
			}
		}
	}
	
	
	
	public	void mostrarEntradasAgenda() {
//    	RECORREMOS LAS DIFERENTES ENTRADAS DE LA AGENDA
    	System.out.println("-------------------------------");
    	for(int i=0; i<agenda.length;  i++) {
    		if(agenda[i]!=null) {
//    			MUESTRA LOS DATOS DE LA ENTRADA
    			System.out.println(i + " - " + agenda[i].formatoCorto());
    		}
    		
    	}
    	System.out.println("-------------------------------");
    }
	
	
	
	
	  public int borrarEntrada() {
    	int entradaBorrada=-1;
    	System.out.println("Introduce el índice de la entrada a borrar : ");
    	int numero=teclado.nextInt();
    	teclado.nextLine();
    	
    	if(agenda[numero]!=null) {
    		agenda[numero]=null;
    		entradaBorrada=numero;
    		System.out.println("La entrada" + numero + "se borró correctamente.");
    	}
    	else System.out.println("La entrada seleccionada no tiene datos");
    	return entradaBorrada;
    }
	  
	  
	  
    public int modificarEntrada() {
    	int entradaModificada=-1;
    	System.out.println("Introduce el índice de la entrada a modificar : ");
    	int numero=teclado.nextInt();
    	teclado.nextLine();
    	
    	if(agenda[numero]!=null) {
    		System.out.println("Introduce el nombre :(" + agenda[numero].getNombre() + ")");
			String nombre=teclado.nextLine();
			if (!nombre.isEmpty())
				agenda[numero].setNombre(nombre);
			System.out.println("Introduce la direccion: (" + agenda[numero].getDireccion() + ")");
			String direccion=teclado.nextLine();
			if (!nombre.isEmpty())
				agenda[numero].setDireccion(direccion);	
			
			System.out.println("Introduce el número fijo: (" + agenda[numero].getNumFijo() + ")");
			String fijo=teclado.nextLine();
			if (!nombre.isEmpty())
				agenda[numero].setNumFijo(fijo);		
			
			System.out.println("Introduce el número movil: (" + agenda[numero].getNumMovil() + ")");
			String movil=teclado.nextLine();
			if (!nombre.isEmpty())
				agenda[numero].setNumMovil(movil);		
			
			System.out.println("Introduce el número fijo: (" + agenda[numero].geteMail() + ")");
			String eMail=teclado.nextLine();
			if (!nombre.isEmpty())
				agenda[numero].seteMail(eMail);		
			
    	}
    	else System.out.println("La entrada seleccionada no tiene datos");
    	
    	return entradaModificada;
   
    }

}



















