package MisClases;

public class Aplicacion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Juego juego=new Juego();
		
		juego.lanzarDados();
		juego.getUltimaJugada();
		juego.estadisticasTiradas(1000);
		System.out.println("Numero de tiradas hasta poker en esta ocasión : " + juego.tiradasHastaPoker());
		
	}

}
