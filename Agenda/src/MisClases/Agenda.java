package MisClases;

import java.util.Scanner;

public class Agenda {
//	ATRIBUTOS
	DatosPersona[] agenda;
	
	
	Agenda(int numEntradas){
		
		agenda=new DatosPersona[numEntradas];
	}
	
	
	int insertarEntrada() {
		
//		BUSCAMOS EL PRIMER HUECO VACIO EN LA AGENDA
		for (int i=0; i<agenda.length; i++) {
			if (agenda[i]==null) {
				break;
			}
		}
		if(encontrado==true) {
//			HE ECONTRADO EL ELEMENTO
//			LE PEDIMOS AL USUARIO LOS DATOS DEL NUEVO REGISTRO
			Scanner teclado=new Scanner(System.in);
			
			System.out.println("Introduce el nombre");
			String nombre=teclado.nextLine();
			System.out.println("Introduce la direccion");
			String direccion=teclado.nextLine();
			System.out.println("Introduce el número fijo");
			String fijo=teclado.nextLine();
			System.out.println("Introduce el número móvil");
			String móvil=teclado.nextLine();
			System.out.println("Introduce el email");
			String eMail=teclado.nextLine();
			
//			CREAMOS UN OBJETO DE LA CLASE DatosPersona
			DatosPersona nuevoRegistro=new DatosPersona(nombre,direccion,fijo,movil,eMail);
			
//			INSERTO EL OBJETO EN LA POSICION BUSCADA EN LA AGENDA
			agenda[i]=nuevoRegistro;
			
			
		}
		else {
//			LA AGENDA ESTABA LLENA 
		}
		
	}
	
	
//	METODOS
}
