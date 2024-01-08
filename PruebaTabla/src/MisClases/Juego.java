package MisClases;

import java.util.Scanner;

public class Juego {
	
	//ATRIBUTOS
	final private int numFilas=6;
	final private int numColumnas=7;
	
	private int turno=1;
	
	private int numCasillasVacias;
	private char [][]tablero;
	
	//CONSTRUCTOR
	Juego(){
		tablero=new char[numFilas][numColumnas];
	}
	
	void vaciarTablero() {
		for (int i=0; i<numFilas; i++) {
			for (int j=0; j<numColumnas; j++) {
				tablero[i][j]=' ';
			}
		}
		numCasillasVacias=numFilas*numColumnas;
	}
	
	void pintarTablero(){
		for (int i=0; i<numFilas; i++) {
			for (int j=0; j<numColumnas; j++) {
				System.out.print("|" + tablero[i][j] + "|");
			}
			System.out.println(""); // PARA QUE HAGA EL SALTO DE LINEA
		}
		
	}
	
	boolean  introducirFicha(){
		if(numCasillasVacias==0)
			return false;
		
		Scanner teclado=new Scanner(System.in);
		
		boolean opcionValida=false;
		while (opcionValida==false) {
			System.out.print("Introduce el número de columna :");
			int columna=teclado.nextInt();
			
			//COMPROBAMOS QUE EL USUARIO ESTA EN LOS LIMITES DEL TABLERO
			if(columna>=0 && columna < numColumnas) {
				
				//RECORREMOS LAS POSICIONES DE LA TABLA DE ABAJO ARRIBA	
				for (int i=numFilas-1; i>=0; i--) {
					if (tablero [i][columna]==' ') {
						//HEMOS ECONTRADO POSICION VACIA
						opcionValida=true;  // PARA SALIRNOS DEL BUCLE WHILE 
					}	if(turno==1)
							tablero[i][columna]='X';
					   else tablero[i][columna]='O';
						break; //ME SALGO DEL BUCLE FOR
				}
			} 
		
		}
		
		return true;
		
	}
	
	void partida() {
		//VACIAMOS EL TABLER0
		vaciarTablero();
		//PIMTAMOS EL TABLERO PARA VER SU ESTADO
		pintarTablero();
		
		while (introducirFicha()==true) {
			//CHEQUEAMOS SI HAY GANADOR
			
			//CAMBIAMOS EL TURNO DEL JUGADOR 
			if (turno==1)
				turno=2;
			else turno=1;
			
			pintarTablero();
			
		}
		
	}

}










