package miClases;

import java.util.Random;

public class Numero {
	
	private int numeroLanzamientos;
	int[] numero = new int[1000];
	Numero(){
		numeroLanzamientos=0;
		numero=0;
			
	}

	
	private int numeroAleatorio() {
		Random numeroAleatorio=new Random();
		return numeroAleatorio.nextInt(1,1000);
	}
	
	public void lanzarDados() {
		numero=numeroAleatorio();
		numeroLanzamientos++;
	}
	
	

}
