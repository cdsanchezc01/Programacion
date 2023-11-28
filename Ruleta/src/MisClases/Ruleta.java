package MisClases;

import java.util.Random;

public class Ruleta {
	
	String[] ruleta= {"100","75","25","Quiebra","25","50","Pierde turno","0","Comodín","100","50","Pierde turno"};
	int indicador;
	
	
	Ruleta(){
		indicador=0; // LA RULETA APUNTA A LA PRIMERA POSICION
	}
	
	
	String getValorActual() {
		return ruleta[indicador];
	}
	
	void lanzarRuleta() {
//		FORMA 1
//		Random numAleatorio=new Random();
//		indicador=numAleatorio.nextInt(0,ruleta.length);
		
//		FORMA 2
		int tiempoFuerza;  // REPRESENTA EL TIEMPO QUE TARDA EN PASAR DE UN CELDA A OTRA
		Random numAleatorio=new Random();
		tiempoFuerza=numAleatorio.nextInt(100,300);
		
		System.out.println("La ruleta empieza a girar");
		System.out.print(getValorActual() + " ");
		
		while(tiempoFuerza<800){
			
			
			
//			HACEMOS UNA PAUSA  DEL NUMERO DE MILISENGUNDOS QUE MAQUE LA VARIABLE TIEMPOFUERZA
			try {
				Thread.sleep(tiempoFuerza);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			indicador++;
			if(indicador>= ruleta.length)
				indicador=0;
			System.out.print(getValorActual() + " ");
//			INCREMENTAMOS TIEMPO DE PASO EN 40 MILISEGUNDOS
			tiempoFuerza+=40;
		}
		System.out.println("\n");
	}

}
