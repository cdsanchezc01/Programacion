package misClases;

public class StaffTecnico extends DatosIntegrante {
	String cargo;
	boolean fueJugador;
	int añoComienzo;
	
	public StaffTecnico(int codIdentificador, String nombre, String nacionalidad, String lugarNacimiento,
			String fechaNacimiento,String cargo, boolean fueJugador, int añoComienzo) {
		super(codIdentificador, nombre,nacionalidad, lugarNacimiento,fechaNacimiento);
		
		this.cargo=cargo;
		this.fueJugador=fueJugador;
		this.añoComienzo=añoComienzo;
	
	}
}
