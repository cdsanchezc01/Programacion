package Misclases;

public class MedidorTiempo {
	// ATRIBUTOS
	long puntoReferencia;
	long ultimaMedida;

	// METODOS

	// CONSTRUCTOR
	MedidorTiempo() {
		ultimaMedida = 0;
		puntoReferencia = System.currentTimeMillis();
	}

	void setPuntoReferencia() {
		puntoReferencia = System.currentTimeMillis();
	}

	long tomarMedida() {
		long tiempo;
		tiempo = System.currentTimeMillis() - puntoReferencia;

		ultimaMedida = tiempo;
		return tiempo;
	}

	long devolverUltimaMedia() {
		return ultimaMedida;
	}

	double devolverUltimaMedidaSegundos() {
		// OJO CON EL DETALLE DE LA DIVISION ENTERA.TIENE QUE SER 1000.0
		return ultimaMedida / 1000.0;

	}
}
