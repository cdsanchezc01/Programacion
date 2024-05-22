package misClases;

import java.time.LocalDate;

public  class NotificacionEmail extends Notificacion{
	    private String eMailDestino;
	    private String asunto;

	    public NotificacionEmail(String texto, String emisor, LocalDate fecha, String eMailDestino, String asunto) {
	        super(texto, emisor, fecha);
	        this.eMailDestino = eMailDestino;
	        this.asunto = asunto;
	    }

	    @Override
	    public void enviar() {
	        System.out.println("Enviando Email a " + eMailDestino + " con asunto \"" + asunto + "\": " + texto);
	    }
}



