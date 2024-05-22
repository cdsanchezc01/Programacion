package misClases;

import java.time.LocalDate;

public class NotificacionSMS extends Notificacion{
	    private Integer teléfonoDestino;

	    public NotificacionSMS(String texto, String emisor, LocalDate fecha, Integer teléfonoDestino) {
	        super(texto, emisor, fecha);
	        this.teléfonoDestino = teléfonoDestino;
	    }
	    

	    @Override
	    public void enviar() {
	        System.out.println("Enviando SMS a " + teléfonoDestino + ": " + texto);
	    }
}




