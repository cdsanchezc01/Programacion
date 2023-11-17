package MisClases;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

public class EjerArray {
	
	public static int[] LanzarDado(int numVeces) {
		
		int[] resultado=new int[6];
		Random dado=new Random();
		
		for(int i=0; i<numVeces; i++)
		{
			int valor=dado.nextInt(1,7);
			resultado [valor-1]++;
			
		}
		return resultado;
		
		
		
	}

}
