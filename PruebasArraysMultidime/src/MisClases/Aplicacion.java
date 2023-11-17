package MisClases;

import java.util.Arrays;

public class Aplicacion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int [][] tabla = { {1,2,3}, {4,5,6}, {7,8,9}, {10,11,12} };
		
/*		int [][] tabla = new int [4][3];
		tabla[0][0]=1;
		tabla[0][1]=2;
		tabla[0][2]=3;
		
		tabla[1][0]=4;
*/		
		for (int i=0; i < tabla.length; i++) {
			for (int j=0; j < tabla[i].length; j++) {
				
				System.out.print("  " + tabla[i][j] + "  ");
			}
			System.out.println();
			
		}
		int suma=0;
		for (int i=0; i < tabla.length; i++) {
			for (int j=0; j < tabla[i].length; j++) {
				suma = suma + tabla[i][j];
				
			}			
		}
		
		
		for (int i=0; i < tabla.length; i++) {
			System.out.println(Arrays.toString(tabla[i]));
		}
		
	}
}


