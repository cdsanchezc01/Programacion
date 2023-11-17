package MisClases;

import java.util.Random;

public class Juego {
	
	//ATRIBUTOS
	int saldo;
	int dado1;
	int dado2;
	int dado3;
	
	//MÉTODOS
	void incrementarSaldo(int cantidad) {
		if(cantidad > 0)
			saldo=saldo+cantidad;
	}
	int  getSaldo() {
		return saldo;
	}
	void lanzarDados() {
		Random numAleatorio=new Random();
		dado1=numAleatorio.nextInt(1,7);
		dado2=numAleatorio.nextInt(1,7);
		dado3=numAleatorio.nextInt(1,7);
	}
	
	int comprobarResultado() {
		int resultado;
		//COMPROBACION DE LOS VALORES DE TIENEN LOS DADOS
	
		//COMPROBAMOS SI LOS TRES DADOS SON IGUALES
		if ((dado1==dado2) && (dado2==dado3))
			resultado=10;
		//COMPROBAMOS SI HAY DOS DADOS IGUALES
		else if ((dado1==dado2) || (dado2==dado3) || (dado1==dado3))
			resultado=2;
		else resultado=0;
	
		return resultado;
	}	
	
	//SI NO SE CUMPLEN NINGUNA DE LAS ANTERIORES RETORNAMOS 0
	int hacerApuesta(int cantidad) {
		 int retorno=1;
			 
		 if(saldo>=cantidad) {
			 
			 //LANZAMOS LOS DADOS
			 lanzarDados();
			 
			 System.out.println("Han salido los valores :" + dado1 + dado2 + dado3);
			 
			 int resultado=comprobarResultado();
			 if (resultado==0)
				 	System.out.println("Lo sentimos perdiste la apuesta");
			 else if (resultado==2)
				 System.out.println("Hay dos valores iguales. Has ganado " + resultado*cantidad);
			 
		 }
		 else retorno=-1;
		 
		 return retorno;
	 }
		 
}
