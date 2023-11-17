package MisClases;

public class Pruebas {

	public static void main(String[] args) {
		
		//DECLARAMOS UNA VARIABLE DE TIPO ARRAY A ENTEROS.
		// OJO EL ARRAY TODAVIA NO ESTA CREADO
		int[] arNumeros= {1,2,3,4,5,6,7,8,9,10};
		
		 // CREAMOS EL ARRAY Y SE LO ASIGNAMOS A LA VARIABLE ANTERIOR
		//arNumeros=new int[10]; objeto 
		// int[] arNumeros2=new int[10];
		/*int sumaPares=0;
		int sumaImpares=0; */
		// NOS RECORREMOS LAS DISTINTAS POSICIONES DEL ARRAY 
		mostarContenido(arNumeros);
		
	}
		public static void mostarContenido(int []arDatos) {
			
			for(int i=0; i< arDatos.length; i++)
			{	
				int valor=arDatos[i];
			    System.err.println(valor);
			
				
				
				
			}
			
				
	
	   }

}
