package classes;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class GestionDatos {

	final String FICHERO_PRODUCTOS =  "C:\\Ejemplos\\productos.dat";

	ArrayList<Producto> lista;

	public GestionDatos() {

		lista = new ArrayList<Producto>();
	}

/* **********************************************************
 * **********************************************************
 */

	public Producto buscarProducto(String codigo) {
		for(Producto producto : lista) {
			if(producto.getCodProducto().equalsIgnoreCase(codigo)) {
				// He encontrado el producto
				return producto;
			}
		}
		// Tengo la certeza de que el producto no existe
		return null;
		
	}

/* **********************************************************
 * **********************************************************
 */
	
	public void añadirProducto() {

		System.out.println("Indica el código del nuevo producto : ");
		String codigo = Main.sc.nextLine();
		
		// Chequeamos si el código elegido ya existe
		if(buscarProducto(codigo)!=null) {
			System.out.println("Ya existe un producto con ese código");
			return;
		}
		
		// Pido el resto de datos
		System.out.println("Indica el nombre del producto : ");
		String nombre = Main.sc.nextLine();
		
		System.out.println("Indica la descripción del producto : ");
		String descripcion = Main.sc.nextLine();
		
		System.out.println("Indica la categoría del producto : ");
		String categoria = Main.sc.nextLine();
		
		System.out.println("Indica el precio del producto : ");
		double precio = Main.sc.nextDouble();
		Main.sc.nextLine();
		
		// Creo el nuevo objeto producto y lo añado a la lista
		Producto nuevoProducto=new Producto(codigo,nombre,descripcion,categoria,precio);
		lista.add(nuevoProducto);
	}

/* **********************************************************
 * **********************************************************
 */
	
	public void mostrarDatosProducto() {
		System.out.println("Indica el código del producto : ");
		String codigo = Main.sc.nextLine();
		
		// Chequeamos si el código elegido ya existe
		Producto producto;
		if((producto=buscarProducto(codigo))!=null) {
			// Muestro los datos del producto
			System.out.println("*********************************************************");
			System.out.println("Código :" + producto.getCodProducto());
			System.out.println("Nombre :" + producto.getNombre());			
			System.out.println("Descripción :" + producto.getDescripcion());			
			System.out.println("Categoría :" + producto.getCategoria());			
			System.out.println("Precio actual :" + producto.getPrecioActual());			
			System.out.println("Precio máximo anual :" + producto.getPrecioMaximoAnual());			
			System.out.println("Precio mínimo anual :" + producto.getPrecioMinimoAnual());			
			System.out.println("*********************************************************");
		} else {
			System.out.println("No existe un producto con ese código");			
		}
	}

/* **********************************************************
 * **********************************************************
 */
	
	public void modificarPrecioProducto() {
		System.out.println("Indica el código del producto al que quieres modificar el precio : ");
		String codigo = Main.sc.nextLine();
		
		// Chequeamos si el código elegido ya existe
		Producto producto;
		if((producto=buscarProducto(codigo))!=null) {
			// Pido el nuevo precio
			System.out.println("Indica el nuevo precio del producto : ");
			double precio = Main.sc.nextDouble();
			Main.sc.nextLine();
			
			// Actualizo los datos
			producto.setPrecioActual(precio);
			// Chequeo si el nuevo precio es menor que el mínimo que había en ese momento
			if(precio < producto.getPrecioMinimoAnual())
				producto.setPrecioMinimoAnual(precio);
			// Chequeo si el nuevo precio es mayor que el máximo que había en ese momento
			if(precio > producto.getPrecioMaximoAnual())
				producto.setPrecioMaximoAnual(precio);
			// Establezco el momento en el que se fijó el nuevo precio
			producto.setMomentoPrecioActual(System.currentTimeMillis());

		} else System.out.println("No existe ningún producto con ese código");
	}

/* **********************************************************
 * **********************************************************
 */
	
	public void modificarRestoDatos() {
		System.out.println("Indica el código del producto al que quieres modificar el precio : ");
		String codigo = Main.sc.nextLine();
		
		// Chequeamos si el código elegido ya existe
		Producto producto;
		if((producto=buscarProducto(codigo))!=null) {

			// Muestro los datos actuales de los campos y pido los nuevos valores
			System.out.println("Establece el nombre del producto (" + producto.getNombre() +") : ");
			String nuevoNombre= Main.sc.nextLine();
			if(!nuevoNombre.isEmpty())
				producto.setNombre(nuevoNombre);
			
			System.out.println("Establece la descripción del producto (" + producto.getDescripcion() +") : ");
			String nuevaDescripcion= Main.sc.nextLine();
			if(!nuevaDescripcion.isEmpty())
				producto.setDescripcion(nuevaDescripcion);
			
			System.out.println("Establece la categoría del producto (" + producto.getCategoria() +") : ");
			String nuevaCategoria= Main.sc.nextLine();
			if(!nuevaCategoria.isEmpty())
				producto.setCategoria(nuevaCategoria);

		} else System.out.println("No existe ningún producto con ese código");
	}

/* **********************************************************
 * **********************************************************
 */
	
	public void borrarProducto() {
		System.out.println("Indica el código del producto a borrar : ");
		String codigo = Main.sc.nextLine();
		Producto producto;
		if((producto=buscarProducto(codigo))!=null) {
			// Borro el producto de la lista
			lista.remove(producto);
		} else System.out.println("No existe ningún producto con ese código");
	}

/* **********************************************************
 * **********************************************************
 */
	
	public void mostrarProductosEncarecimientoSuperior() {

		System.out.println("Indica el porcentaje que quieres tomar como referencia : ");
		double porcentajeReferencia = Main.sc.nextDouble();
		Main.sc.nextLine();
		
		// Recorro la lista de productos y voy chequeando cuáles exceden ese porcentaje
		for (Producto p : lista) {
			// Calculo el porcentaje de encarecimiento de ese producto
			// ( ( PrecioMaximo / PrecioMinimo ) – 1 ) * 100
			double porcentaje=((p.getPrecioMaximoAnual() / p.getPrecioMinimoAnual()) -1 ) * 100; 
			
			if(porcentaje>porcentajeReferencia) {
				// Muestro los datos del producto
				System.out.println(p.getCodProducto() + " - " +
								   p.getNombre() + " - " +	
								   p.getPrecioActual() + " - " +
								   p.getPrecioMinimoAnual() + " - " +						
								   p.getPrecioMaximoAnual() + " - " +
								   porcentaje );
			}
		}
	}

/* **********************************************************
 * **********************************************************
 */
	
	void listarProductosIntervalo() {

		// Le pedimos al usuario el rango de precios sobre el que quiere trabajar
		System.out.println("Indica el rango mínimo del precio : ");
		double rangoMinimo = Main.sc.nextDouble();
		Main.sc.nextLine();
		System.out.println("Indica el rango máximo del precio : ");
		double rangoMaximo = Main.sc.nextDouble();
		Main.sc.nextLine();
				
		// Ordenamos la lista por el criterio establecido por el usuario
		Collections.sort(lista);
		
		// A continuación, vamos recorriendo la lista y mostramos los datos que estén en ese intervalo
		for(Producto p : lista) {
			if(p.getPrecioActual()>=rangoMinimo && 
			   p.getPrecioActual()<=rangoMaximo) {
				// Mostramos los datos del producto
				System.out.println(p.getCodProducto() + " - " +
						   p.getCategoria() + " - " +	
						   p.getNombre() + " - " +
						   p.getPrecioActual());
			}
		}
	}

/* **********************************************************
 * **********************************************************
 */
	
	public void leerFichero() {
		// Se pueden utilizar distintas estrategias a la hora de guardar los datos en un fichero binario.
		// En este ejemplo vamos a optar por gestionar nuestro propio fichero binario
		try (DataInputStream ficheroBinario = 	new DataInputStream(new BufferedInputStream(new FileInputStream(FICHERO_PRODUCTOS)))) {
			
			// Vamos leyendo datos del registro
			while(ficheroBinario.available()>0) {
				Producto nuevoProducto=new Producto();
				nuevoProducto.setCodProducto(ficheroBinario.readUTF());
				nuevoProducto.setNombre(ficheroBinario.readUTF());
				nuevoProducto.setDescripcion(ficheroBinario.readUTF());
				nuevoProducto.setCategoria(ficheroBinario.readUTF());				
				nuevoProducto.setPrecioActual(ficheroBinario.readDouble());			
				nuevoProducto.setMomentoPrecioActual(ficheroBinario.readLong());				
				nuevoProducto.setPrecioMinimoAnual(ficheroBinario.readDouble());				
				nuevoProducto.setPrecioMaximoAnual(ficheroBinario.readDouble());
				
				// Una vez que tenemos los datos rellenos, añadimos el producto a la lista
				lista.add(nuevoProducto);
			}
		} catch (IOException e) {
			System.out.println("No se pudieron leer datos del fichero");		
		}
	}

/* **********************************************************
 * **********************************************************
 */
	
	public void guardarFichero() {
		// Se pueden utilizar distintas estrategias a la hora de guardar los datos en un fichero binario.
		// En este ejemplo vamos a optar por gestionar nuestro propio fichero binario
		try (DataOutputStream ficheroBinario = 	new DataOutputStream(new BufferedOutputStream(new FileOutputStream(FICHERO_PRODUCTOS)))) {
			
			// Vamos recorriendo la lista y guardando sus datos
			for(Producto p:lista) {
				ficheroBinario.writeUTF(p.getCodProducto());
				ficheroBinario.writeUTF(p.getNombre());				
				ficheroBinario.writeUTF(p.getDescripcion());
				ficheroBinario.writeUTF(p.getCategoria());				
				ficheroBinario.writeDouble(p.getPrecioActual());				
				ficheroBinario.writeLong(p.getMomentoPrecioActual());				
				ficheroBinario.writeDouble(p.getPrecioMinimoAnual());				
				ficheroBinario.writeDouble(p.getPrecioMaximoAnual());				
			}
			
		} catch (IOException e) {
			System.out.println("Hubo problemas escribiendo datos en el fichero");		
		}
	}
}


