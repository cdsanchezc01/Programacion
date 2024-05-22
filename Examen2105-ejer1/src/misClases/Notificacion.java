package misClases;

import java.time.LocalDate;

public abstract class Notificacion {

	 String texto;
	 String emisor;
	 LocalDate fecha;

	public Notificacion(String texto, String emisor, LocalDate fecha) {
		this.texto = texto;
		this.emisor = emisor;
		this.fecha = fecha;
	}
	

    @Override
    public String toString() {
        return "Notificación de " + emisor + " el " + fecha + ": " + texto;
    }

    public abstract void enviar();
}

