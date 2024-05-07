package classes;

public class Producto   implements Comparable<Producto>{
	
	private String codProducto;
	private String nombre;
	private String descripcion;
	private String categoria;
	private double precioActual;
	private long momentoPrecioActual;
	private double precioMinimoAnual;
	private double precioMaximoAnual;

			
	// Generamos los constructores
	
	// Un constructor que deja todos los datos con los valores por defecto
	public Producto() {
		
	}
	
	// Otro constructor al que le pasamos ciertos datos del objeto y rellena automáticamente el resto
	public Producto(String codProducto, String nombre, String descripcion, String categoria, double precioActual) {
		this.codProducto = codProducto;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.categoria = categoria;
		this.precioActual = precioActual;
		
		this.momentoPrecioActual=System.currentTimeMillis();
		this.precioMinimoAnual=precioActual;
		this.precioMaximoAnual=precioActual;
	}
	
	
	// Métodos Get/Set
	public String getCodProducto() {
		return codProducto;
	}
	public void setCodProducto(String codProducto) {
		this.codProducto = codProducto;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public double getPrecioActual() {
		return precioActual;
	}
	public void setPrecioActual(double precioActual) {
		this.precioActual = precioActual;
	}
	public long getMomentoPrecioActual() {
		return momentoPrecioActual;
	}
	public void setMomentoPrecioActual(long momentoPrecioActual) {
		this.momentoPrecioActual = momentoPrecioActual;
	}
	public double getPrecioMinimoAnual() {
		return precioMinimoAnual;
	}
	public void setPrecioMinimoAnual(double precioMinimoAnual) {
		this.precioMinimoAnual = precioMinimoAnual;
	}
	public double getPrecioMaximoAnual() {
		return precioMaximoAnual;
	}
	public void setPrecioMaximoAnual(double precioMaximoAnual) {
		this.precioMaximoAnual = precioMaximoAnual;
	}


	@Override
	public int compareTo(Producto o) {
		
		if(Main.tipoOrdenacion==1) {
			// Esta ordenacion es por precio descendente y nombre
			if(precioActual==o.getPrecioActual())
				// Segundo criterio de ordenación
				return nombre.compareTo(o.getNombre()); 
			else {
				// OjO, le pongo el menos porque quiero que el orden sea el contrario
				return -((Double)precioActual).compareTo(o.getPrecioActual());
			}
		}
		else if(Main.tipoOrdenacion==2) {
			// Esta ordenacion es por categoria y nombre
			if(categoria.equalsIgnoreCase(o.getCategoria()))
				// Segundo criterio de ordenación
				return nombre.compareTo(o.getNombre());
			else {
				return categoria.compareTo(o.getCategoria()); 
			}
		}
		else return 0;
	}
	
	
	
}
