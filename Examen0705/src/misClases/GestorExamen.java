package misClases;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class GestorExamen {

	ArrayList<Examen> lista;

//	Constructor
	GestorExamen() {
		lista = new ArrayList<Examen>();
	}
//------------------------------------------------------
	void añadirExamen() {
//		Pedimos los datos del examen
		System.out.println(" Introduce el código: ");
		int codigo = App.teclado.nextInt();
		App.teclado.nextLine();
//		Buscamos si ya existe un examen con ese codigo 
		if (buscarExamen(codigo) == -1) {
//			No esta. Pedimos el resto de datos
			System.out.println(" Introduce la descripcion: ");
			String descripcion = App.teclado.nextLine();

			System.out.println(" Introduce la evaluacion: ");
			String evaluacion = App.teclado.nextLine();

			System.out.println(" Introduce la asignatura: ");
			String asignatura = App.teclado.nextLine();

			System.out.println(" Introduce la fecha con el formnato aaaa/mm/dd: ");
			String fecha = App.teclado.nextLine();

			System.out.println(" Introduce la hora con el formato hh:mm: ");
			String hora = App.teclado.nextLine();

			System.out.println(" Introduce si ha realizado el examen, true para si y false para no : ");
			boolean realizado = App.teclado.nextBoolean();

			System.out.println(" Introduce la nota que ha sacado: ");
			double nota = App.teclado.nextDouble();

//			Creamos el obejto examen con esos datos
			Examen nuevoExamen = new Examen(codigo, descripcion, evaluacion, asignatura, fecha, hora, realizado, nota);
			lista.add(nuevoExamen);

			System.out.println(" Examen añadido ");
		} else
			System.out.println(" Ya existe un examen con ese código ");
	}
//-----------------------------------------------------------------
//	Hacemos un metodo para buscar el examen por su codigo

	int buscarExamen(int codigo) {
//		Retorna -1 si no esta y si no, la posicion en la que esta
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getCodigo() == codigo)
				return i;
		}
		return -1;
	}
//----------------------------------------------------------------
	void econtrarExamen() {
//		Pedimos los datos del examen a borrar 
		System.out.println(" Introduce el código: ");
		int codigo = App.teclado.nextInt();
		App.teclado.nextLine();
//		Buscamos si ya existe un examen con ese codigo 
		int posicion;
		if ((posicion = buscarExamen(codigo)) != -1) {

			System.out.println(lista.get(posicion));
		} else
			System.out.println(" No existe nigun examen con ese código ");
	}
//--------------------------------------------------------------
	void borrarExamen() {
//		Pedimos los datos del examen
		System.out.println(" Introduce el código: ");
		int codigo = App.teclado.nextInt();
		App.teclado.nextLine();
//		Buscamos si ya existe un examen con ese codigo 
		int posicion;
		if ((posicion = buscarExamen(codigo)) != -1) {
			lista.remove(posicion);
			System.out.println(" Objeto borrado");
		} else
			System.out.println(" No existe nigun examen con ese código ");
	}
	
	
	void modificarNotaExamen() {

		System.out.print("Introduce el codigo del examen : ");
		int codigo = App.teclado.nextInt();

		Examen datosExamen = buscarExamen(codigo);
		if (datosExamen != null) {
			// El examen existe
			System.out.print("Introduce el nuevo examen. Vacío si lo quieres dejar como está " + "("
					+ datosExamen.getCodigo() + ") : ");
			String nuevoExamen = App.teclado.nextLine();
			if (!nuevoExamen.isEmpty()) {
				// Actualizamos los datos del objeto en memoria
				datosExamen.setCodigo(Integer.parseInt(nuevoExamen));

				// Rescribimos los datos en el fichero
				try {
					guardarDatosFichero();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else
			System.out.println("No existe ningún examen con ese codigo");

	}
		
//----------------Fichero-----------------------------------
	void guardarDatosFichero() throws Exception {
		DataOutputStream fichero = new DataOutputStream(
				new BufferedOutputStream(new FileOutputStream("c:\\Ejemplos\\Examen.dat")));

		for (int i = 0; i < lista.size(); i++) {
			Examen examen = lista.get(i);
			fichero.writeUTF(examen.getCodigo() + ";" + examen.getDescripcion() + ";" + examen.getEvaluacion() + ";" + examen.getAsignatura() + ";"
					+ examen.getFecha() + ";" + examen.getHora()
					+ ";" + examen.isRealizado() + ";" + examen.getNota() + "\n");
			fichero.writeInt(examen.getCodigo());
			fichero.writeUTF(examen.getDescripcion());
			fichero.writeUTF(examen.getEvaluacion());
			fichero.writeUTF(examen.getAsignatura());
			fichero.writeUTF(examen.getFecha());
			fichero.writeUTF(examen.getHora());
			fichero.writeBoolean(examen.isRealizado());
			fichero.writeDouble(examen.getNota());
		}
		

		fichero.close();
	}

	void cargarDatosFichero() throws Exception {
		DataInputStream fichero = new DataInputStream(
				new BufferedInputStream(new FileInputStream("c:\\Ejemplos\\Examen.dat")));

		while (fichero.available() > 0) {
			int codigo = fichero.readInt();
			String descripcion = fichero.readUTF();
			String evaluacion = fichero.readUTF();
			String asignatura = fichero.readUTF();
			String fecha = fichero.readUTF();
			String hora = fichero.readUTF();
			boolean realizado = fichero.readBoolean();
			double nota = fichero.readFloat();

//			Creamos el obejo examen y lo añadimos a la lista
			Examen nuevoExamen = new Examen(codigo, descripcion, evaluacion, asignatura, fecha, hora, realizado, nota);
			lista.add(nuevoExamen);
		}
		fichero.close();
	}

}
