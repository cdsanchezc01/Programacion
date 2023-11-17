package MisClases;

import java.util.Random;
import java.util.Scanner;

public class JuegoTiempo {
	
	//ATRIBUTOS
	
	private long momentoInicial;
	private int numSegundosMinimo;
	private int numSegundosMaximo;
	private int numSegundosMargen;
	
	
	//MÉTODOS
	//CONSTRUCTOR 
	JuegoTiempo(){
		numSegundosMinimo=15;
		numSegundosMaximo=30;
		numSegundosMargen=3;
	}
	JuegoTiempo(int numMinimo, int numMaximo, int numMargen){
		numSegundosMinimo=15;
		numSegundosMaximo=30;
		numSegundosMargen=3;
	}
	
	public int getNumSegundosMinimo() {
		return numSegundosMinimo;
	}
	public void setNumSegundosMinimo(int numSegundosMinimo) {
		this.numSegundosMinimo = numSegundosMinimo;
	}
	public int getNumSegundosMaximo() {
		return numSegundosMaximo;
	}
	public void setNumSegundosMaximo(int numSegundosMaximo) {
		this.numSegundosMaximo = numSegundosMaximo;
	}
	public int getNumSegundosMargen() {
		return numSegundosMargen;
	}
	public void setNumSegundosMargen(int numSegundosMargen) {
		this.numSegundosMargen = numSegundosMargen;
	}
	public void setMomentoInicial() {
		
		momentoInicial=System.currentTimeMillis();
	}
	
	public double  medirDiferenciaTiempo() {
		
		long tiempoActual=System.currentTimeMillis();
		long diferencia=tiempoActual-momentoInicial;
		return diferencia/1000.0;
	}
	public void jugarPartida() {
		//GENERAMOS NUMERO ALEATORIO
		Random numeroAleatorio=new Random();
		int numSegundos=numeroAleatorio.nextInt(numSegundosMinimo,numSegundosMaximo);
		//IDICAMOS POR PANTALLA INSTRUCCIONES
		System.out.println("El número de segundos a adivinar es :" + numSegundos);
		System.out.println("Pulsa enter cuando quieras que comience la cuenta atrás");
		Scanner teclado=new Scanner(System.in);
		teclado.nextLine();
		
		//EMPEZAMOS A COMTAR  EL TIEMPO DESDE ESE MOMENTO
		setMomentoInicial();
		
		System.out.println("Pulsa enter cuando creas que han pasado los segundos ");
		teclado.nextLine();
		//MIDE LA DIFERENCIA DE TIEMPO DE LA PRIMERA Y ULTIMA PULSACION 
		double tiempoEnSegundos=medirDiferenciaTiempo();
		
		//CALCULAMOS LA DIFERENCIA DE TIEMPO QUE HA HABIDO
		double diferencia= tiempoEnSegundos - numSegundos;
		
		// SI EL USUARIO SE HA QUEDADO CORTO (DIEFERTENCIA NEGATIVA) LA PASAMOS A POSITIVO
		if(diferencia < 0 )
			diferencia=-diferencia;
		
		if (diferencia < 1)
			//HA GANADO
			System.out.println("¡Enorabuena has ganado! La dierecia ha sido " + diferencia);
		else
			//HA PERDIDO
			System.out.println(" Lo siento, has perdido La dierecia ha sido " + diferencia);
		
	}
	
	public void jugarPartidaV2() {
		//GENERAMOS NUMERO ALEATORIO
		Random numeroAleatorio=new Random();
		int numSegundos=numeroAleatorio.nextInt(numSegundosMinimo,numSegundosMaximo+1);
		
		boolean salir=false;
		while (salir==false)
			
		//IDICAMOS POR PANTALLA INSTRUCCIONES
		System.out.println("El número de segundos a adivinar es :" + numSegundos);
		System.out.println("Pulsa enter cuando quieras que comience la cuenta atrás");
		Scanner teclado=new Scanner(System.in);
		teclado.nextLine();
		
		//EMPEZAMOS A COMTAR  EL TIEMPO DESDE ESE MOMENTO
		setMomentoInicial();
		
		System.out.println("Pulsa enter cuando creas que han pasado los segundos ");
		teclado.nextLine();
		//MIDE LA DIFERENCIA DE TIEMPO DE LA PRIMERA Y ULTIMA PULSACION 
		double tiempoEnSegundos=medirDiferenciaTiempo();
		
		//CALCULAMOS LA DIFERENCIA DE TIEMPO QUE HA HABIDO
		double diferencia= tiempoEnSegundos - numSegundos;
		
		// SI EL USUARIO SE HA QUEDADO CORTO (DIEFERTENCIA NEGATIVA) LA PASAMOS A POSITIVO
		if(diferencia < 0 )
			diferencia=-diferencia;
		
		if (diferencia < 1)
			//HA GANADO
			System.out.println("¡Enorabuena has ganado! Calculaste. La dierecia ha sido " + diferencia);
			salir=true;
		
		

			
			//HA PERDIDO
			System.out.println(" Lo siento, has perdido La dierecia ha sido " + diferencia);
	
	}
}


























