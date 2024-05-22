package misClases;

import java.time.LocalDate;

public class App {

	public static void main(String[] args) {

		Notificacion[] notificaciones = new Notificacion[5];

		notificaciones[0] = new NotificacionSMS("aaaa aaaaaa", "Emisor 1", LocalDate.of(2024, 5, 21), 777777777);
		notificaciones[1] = new NotificacionEmail("bbbb bbbbb", "Emisor 1", LocalDate.of(2024, 5, 21), "fer@fer.es",
				"Asunto a");
		notificaciones[2] = new NotificacionEmail("cccc cccccc", "Emisor 2", LocalDate.of(2024, 5, 21),
				"julia@julia.es", "Asunto b");
		notificaciones[3] = new NotificacionSMS("Ddddd dddd", "Emisor 3", LocalDate.of(2024, 5, 21), 888888888);
		notificaciones[4] = new NotificacionSMS("eeee eeeeee", "Emisor 3", LocalDate.of(2024, 5, 21), 999999999);

		enviarNotificaciones(notificaciones);
	}

	public static void enviarNotificaciones(Notificacion[] notificaciones) {
		for (Notificacion notificacion : notificaciones) {
			notificacion.enviar();
		}
	}
}

