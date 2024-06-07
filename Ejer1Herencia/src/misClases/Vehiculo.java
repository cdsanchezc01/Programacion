package misClases;

import java.io.BufferedWriter;
import java.io.IOException;

public abstract class Vehiculo {
	
	String matricula;
	String marca;
	String modelo;
	String combustible;
	String fechaSiguienteRevision;
	String emailContacto;
	String telefono;
	
	
	public Vehiculo() {
	
		this.matricula = matricula;
		this.marca = marca;
		this.modelo = modelo;
		this.combustible = combustible;
		this.fechaSiguienteRevision = fechaSiguienteRevision;
		this.emailContacto = emailContacto;
		this.telefono = telefono;
	}
	
	public Vehiculo(String matricula, String marca, String modelo, String combustible, String fechaSiguienteRevision,
			String emailContacto, String telefono) {
		this.matricula = matricula;
		this.marca = marca;
		this.modelo = modelo;
		this.combustible = combustible;
		this.fechaSiguienteRevision = fechaSiguienteRevision;
		this.emailContacto = emailContacto;
		this.telefono = telefono;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getCombustible() {
		return combustible;
	}
	public void setCombustible(String combustible) {
		this.combustible = combustible;
	}
	public String getFechaSiguienteRevision() {
		return fechaSiguienteRevision;
	}
	public void setFechaSiguienteRevision(String fechaSiguienteRevision) {
		this.fechaSiguienteRevision = fechaSiguienteRevision;
	}
	public String getEmailContacto() {
		return emailContacto;
	}
	public void setEmailContacto(String emailContacto) {
		this.emailContacto = emailContacto;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	
	  @Override
	    public String toString() {
	        return "Matricula: " + matricula + ", Marca: " + marca + ", Modelo: " + modelo + ", Combustible: " + combustible 
	        		+ ", Fecha Siguiente Revision: " + fechaSiguienteRevision + ", Email Contacto: " + emailContacto + ", Telefono: " + telefono;
	    }
	
	
	public abstract void introducirDatosGenerales();
	
	void VehiculoBuscarVehiculo(String matricula) {
	}
	
	abstract void introduceDatosEspecificos();
	abstract void mostrarFicha();
	abstract void cargarDatosLineaFichero(String linea);
	abstract  char queTipoVehiculoSoy();

	String mostrarDatos() {
		// TODO Auto-generated method stub
		return null;
	}
	 public abstract void leerDatoCsv(String linea, int separador);

	protected abstract String getNombre();
	 public abstract void guardarDatoCsv(BufferedWriter fichero, int separador) throws IOException;
	
	
	
	
	
	
	
	
	
	
	
	

}
