package misClases;

import java.io.BufferedWriter;
import java.io.IOException;

public class Motocicleta extends Vehiculo {
	
	int cilindrada;
	String tipoMoto;
	double peso;
	
	
	
	
	public Motocicleta( String marcaMoto, String modeloMoto, int cilindradaMoto,String matricula1) {
		this.cilindrada = cilindrada;
		this.tipoMoto = tipoMoto;
		this.peso = peso;
	}
	public Motocicleta(String matricula, String marca, String modelo, String combustible, String fechaSiguienteRevision,
			String emailContacto, String telefono,int cilindrada, String tipoMoto, double peso) {
		super(matricula,marca, modelo, combustible, fechaSiguienteRevision,
				 emailContacto,  telefono);
		this.cilindrada = cilindrada;
		this.tipoMoto = tipoMoto;
		this.peso = peso;
	}
	public int getCilindrada() {
		return cilindrada;
	}
	public void setCilindrada(int cilindrada) {
		this.cilindrada = cilindrada;
	}
	public String getTipoMoto() {
		return tipoMoto;
	}
	public void setTipoMoto(String tipoMoto) {
		this.tipoMoto = tipoMoto;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	
	@Override
	String mostrarDatos() {
		return " Cilindrada: " + cilindrada + ", Tipo de Moto: " + tipoMoto + " CV" + " Peso: " + peso;
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
		setCilindrada(Integer.parseInt(datos[8]));
		setTipoMoto(datos[9]);
		setPeso(Integer.parseInt(datos[8]));
	
		
	}

	@Override
	public void guardarDatoCsv(BufferedWriter fichero, int separador) throws IOException {
		String sep = Character.toString((char) separador);
		fichero.write(getMatricula() + sep + getMarca() + sep + getModelo() + sep + getCombustible() + sep + getFechaSiguienteRevision()
				+ sep + getEmailContacto() + sep + getTelefono() + sep + getCilindrada() + sep + getTipoMoto() + sep + getPeso());
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
