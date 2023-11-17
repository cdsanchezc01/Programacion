package Misclases;

public class Aplicacion {

	public static void main(String[] args) {
		
		MedidorTiempo crono1=new MedidorTiempo();
		
		for(int i=0;i<100;i++)
		{
			System.out.println("Hola");
		}
		
		long tiempo=crono1.tomarMedida();
		System.out.println("El tiempo empleado en hacer el bucle es -> " + tiempo);
		

		crono1.setPuntoReferencia();
		for(int i=0;i<10000;i++)
		{
			System.out.println("Hola");
		}
		crono1.tomarMedida();

		double tiempo2=crono1.devolverUltimaMedidaSegundos();
		System.out.println("El tiempo empleado en segundos es -> " + tiempo2);
		
	}
}
