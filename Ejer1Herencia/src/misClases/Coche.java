package misClases;

import java.io.BufferedWriter;
import java.io.IOException;

public class Coche extends Vehiculo {
	int caballos;
	String segmento;
	
	
	

	public Coche(String marcaCoche, String modeloCoche, int caballosCoche,String matricula) {
		this.caballos = caballos;
		this.segmento = segmento;
	}

	public Coche(String matricula, String marca, String modelo, String combustible, String fechaSiguienteRevision,
		String emailContacto, String telefono, int caballos, String segmento) {
		
		super(matricula,marca, modelo, combustible, fechaSiguienteRevision,
				 emailContacto,  telefono);
		this.caballos = caballos;
		this.segmento = segmento;
	}

	public int getCaballos() {
		return caballos;
	}

	public void setCaballos(int caballos) {
		this.caballos = caballos;
	}

	public String getSegmento() {
		return segmento;
	}

	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}

	@Override
	String mostrarDatos() {
		return " Segmento: " + segmento + ", Caballos: " + caballos + " CV";
	}
	
	@Override
	public void leerDatoCsv(String linea, int separador) {
		String[] datos = linea.split(Character.toString((char) separador));
		setMatricula(datos[1]);
		setMarca(datos[2]);
		setModelo(datos[3]);
		setCombustible(datos[4]);
		setFechaSiguienteRevision(datos[5]);
		setEmailContacto(datos[6]);
		setTelefono(datos[7]);
		setCaballos(Integer.parseInt(datos[8]));
		setSegmento(datos[9]);
	
		
	}

	@Override
	public void guardarDatoCsv(BufferedWriter fichero, int separador) throws IOException {
		String sep = Character.toString((char) separador);
		fichero.write(getMatricula() + sep + getMarca() + sep + getModelo() + sep + getCombustible() + sep + getFechaSiguienteRevision()
				+ sep + getEmailContacto() + sep + getTelefono() + sep + getCaballos() + sep + getSegmento());
		fichero.newLine();
	}

	@Override
	public void introducirDatosGenerales() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void introduceDatosEspecificos() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void mostrarFicha() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void cargarDatosLineaFichero(String linea) {
		// TODO Auto-generated method stub
		
	}

	@Override
	char queTipoVehiculoSoy() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected String getNombre() {
		// TODO Auto-generated method stub
		return null;
	}

}
