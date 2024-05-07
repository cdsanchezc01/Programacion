package misClases;

public class Examen {
	
	private int codigo;
	private String descripcion;
	private String evaluacion;
	private String asignatura;
	private String fecha;
	private String hora;
	private boolean realizado;
	private double nota;
	
	public Examen(int codigo, String descripcion, String evaluacion, String asignatura, String fecha, String hora,
			boolean realizado, double nota) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.evaluacion = evaluacion;
		this.asignatura = asignatura;
		this.fecha = fecha;
		this.hora = hora;
		this.realizado = realizado;
		this.nota = nota;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getEvaluacion() {
		return evaluacion;
	}
	public void setEvaluacion(String evaluacion) {
		this.evaluacion = evaluacion;
	}
	public String getAsignatura() {
		return asignatura;
	}
	public void setAsignatura(String asignatura) {
		this.asignatura = asignatura;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public boolean isRealizado() {
		return realizado;
	}
	public void setRealizado(boolean realizado) {
		this.realizado = realizado;
	}
	public double getNota() {
		return nota;
	}
	public void setNota(double nota) {
		this.nota = nota;
	}
	
	@Override
	public String toString() {
		// TODO Apéndice de método generado automáticamente
		return codigo + "-" + descripcion + "-" + evaluacion + "-" + asignatura + "-" + fecha 
				+ "-" + hora + "-" + realizado + "-" + nota + "-";
	}
	
	
	
	
	
	
	
	
}
