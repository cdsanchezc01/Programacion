package misClases;

public class EquipoDirectivo extends DatosIntegrante {
	String cargo;

	public EquipoDirectivo(int codIdentificador, String nombre, String nacionalidad, String lugarNacimiento,
			String fechaNacimiento, String cargo) {
		super(codIdentificador, nombre,nacionalidad, lugarNacimiento,fechaNacimiento);
		this.cargo = cargo;
	}

}
