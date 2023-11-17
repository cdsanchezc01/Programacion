package MisClases;

import java.util.Random;

public class Juego {
	
	//ATRIBUTOS 
	private int dado1; 
	private int dado2;
	private int dado3;
	private int dado4;
	private int numeroLanzamientos;

	
	//METODOS
	
	//Constructor
	
	Juego(){
		numeroLanzamientos=0;
		dado1=1;
		dado2=1;
		dado3=1;
		dado4=1;		
	}

	
	private int lanzarDado() {
		Random numeroAleatorio=new Random();
		return numeroAleatorio.nextInt(1,7);
	}
	
	public void lanzarDados() {
		dado1=lanzarDado();
		dado2=lanzarDado();
		dado3=lanzarDado();
		dado4=lanzarDado();	
		numeroLanzamientos++;
	}
	
	public void getUltimaJugada(){
		System.out.println("La última tirada es -> :" + dado1);
		System.out.println("La última tirada es -> :" + dado2);
		System.out.println("La última tirada es -> :" + dado3);
		System.out.println("La última tirada es -> :" + dado4);
		
	}
	
	public int getNumeroLanzamientos() {
		return numeroLanzamientos;
	}
	
	
	
	public int calcularSumaUltimoLanzamiento() {
		 
		 return dado1+dado2+dado3+dado4;
	}
	
	public int comprobarResultadoUltimoLanzamiento() {
		
		int nDado;
		
		// Comprobar póker
		if(dado1==dado2 && dado1==dado3 && dado1==dado4) { 
			nDado=4; //ESTO ES UN POKER 
			return nDado;
		}
		
		
		// Comprobar trío
 		if(   (dado1==dado2 && dado1==dado3) ||				// 123
 			  (dado1==dado2 && dado1==dado4) ||				// 124
 			  (dado2==dado3 && dado2==dado4) ||				// 234 			  
 			  (dado1==dado3 && dado1==dado4) 				// 134 			  
 		  )
 		{
 			
				nDado=3; //ESTO ES UN TRIO
				return nDado;	
		}
 		
 		// Comprobar dobles parejas
 		if(   (dado1==dado2 && dado3==dado4) ||				// 12    34
 			  (dado1==dado3 && dado2==dado4) ||				// 13    24
 			  (dado1==dado4 && dado2==dado3)				// 14    23 			  
 		  )
 		{
 			
				nDado=2; //ESTO ES UNA DOBLE PAREJA
				return nDado;	
		}

 		// Comprobar pareja sencilla 		
 		if( dado1==dado2 ||  dado1==dado3 ||  dado1==dado4 ||  dado2==dado3 ||  dado2==dado4 ||  dado3==dado4 )
 		{
 	 			
				nDado=1; //ESTO ES UNA DOBLE PAREJA
				return nDado;	
		}
 		
 		
 		return 0;
			
		}
		
	
	public void estadisticasTiradas(int numero) {
		int numeroPoker=0;
		int numeroTrio=0;
		int numeroParejasDobles=0;
		int numeroDoble=0;
		
		for(int i=0;i<numero;i++) 
		{	
			//LANZAMOS LOS DADOS UNA VEZ
			lanzarDados();
			//COMPROBAMOS SI HAY ALGUNA JUGADA CON PUNTOS
			int resultados=comprobarResultadoUltimoLanzamiento();
			
			if(resultados==4)
				numeroPoker++;
			else if(resultados==3)
				numeroTrio++;
			else if(resultados==2)
				numeroParejasDobles++;
			else if(resultados==1)
				numeroDoble++;
		}
		
		System.out.println("Numero de tiradas total " + numero);
		System.out.println("Numero de poker " + numeroPoker);
		System.out.println("Numero de trios  " + numeroTrio);
		System.out.println("Numero de Parejas dobles " + numeroParejasDobles);
		System.out.println("Numero de tiradas Dobles " + numeroDoble );
	}
	
	public int tiradasHastaPoker()
	{
		int contador=0;
		boolean salir=false;
		while(salir==false)
		{
			contador++;
			
			lanzarDados();
			//COMPROBAMOS SI HAY ALGUNA JUGADA CON PUNTOS
			if(comprobarResultadoUltimoLanzamiento()==4)
				salir=true;
			
		}
		
		return contador;
	}
}	
		


















