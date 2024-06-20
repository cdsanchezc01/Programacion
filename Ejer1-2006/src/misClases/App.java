package misClases;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class App{
	
	static Scanner teclado=new Scanner(System.in);
	public static void main(String[] args) {
		
	}
	
	int[] resultado= crearArrayNumerosAleatorios(20);
		
		int[] mayores=devuelveArrayMayores(resultado,500);

		System.out.println(Arrays.toString(resultado));
		System.out.println(Arrays.toString(mayores));

	


	
	
	
	
	
	
	static int[] crearArrayNumeros (int numNumeros) {
		int[] resultado;
		// Creamos el array
		resultado= new int[numNumeros];
		Random numAleatorio=new Random();
		for(int i=0;i<numNumeros;i++) {
			resultado[i]=numAleatorio.nextInt(1, 300);
		}
		
		return resultado;
	}

	static int[] devuelveArrayMayores (int[] arNumeros, int valorReferencia) {
		
		// Miramos el n�mero de elementos que tendr� el array
		int contador=0;
		for(int i=0;i<arNumeros.length;i++) {
			if(arNumeros[i]>valorReferencia)
				contador++;
		}
		
		// Una vez que sabemos el n�mero de elementos del array, lo creamos
		int[] resultado=new int[contador];
		// Guardamos los valores mayores en el array
		int j=0;
		for(int i=0;i<arNumeros.length;i++) {
			if(arNumeros[i]>valorReferencia) {
				resultado[j]=arNumeros[i];
				j++;
			}
		}	
		
		// Ordenamos el array resultante
		Arrays.sort(resultado);
		
		return resultado;
		
		
	}
}
