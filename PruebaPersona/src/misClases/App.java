package misClases;

import java.io.RandomAccessFile;

public class App {

	public static void main(String[] args) throws Exception{
		
		Persona persona1=new Persona("Fernando", "Garcia Catillo", (float) 1.80 , 48);
		Persona persona2=new Persona("Ana", "Martin Martin", (float) 1.60 , 30);
		Persona persona3=new Persona("Maria", "Jose Jose", (float) 1.55 , 50);
		
//		Guardamos los datos de las personas en un fichero
		RandomAccessFile fichero=new RandomAccessFile("c:\\Ejemplos\\personas.dat", "rw");
		
//		Guardamos los datos de la primera persona
		StringBuffer sb;
		sb=new StringBuffer(persona1.nombre);
		sb.setLength(30);  // limitas los campos
		fichero.writeChars(sb.toString());
		fichero.writeUTF(persona1.apellido);
		fichero.writeFloat(persona1.altura);
		fichero.write(persona1.edad);
		
//		Guardamos los datos de la segunda persona
		fichero.writeChars(persona2.nombre);
		fichero.writeUTF(persona2.apellido);
		fichero.writeFloat(persona2.altura);
		fichero.write(persona2.edad);
		
//		Guardamos los datos de la tercera persona
		fichero.writeChars(persona3.nombre);
		fichero.writeUTF(persona3.apellido);
		fichero.writeFloat(persona3.altura);
		fichero.write(persona3.edad);

		fichero.close();

		fichero=new RandomAccessFile("c:\\Ejemplos\\personas.dat", "r");
		
//		Leemos los datos del primer registro
		String nombre=fichero.readUTF();
		String apellidos=fichero.readUTF();
		float altura=fichero.readFloat();
		int edad=fichero.readInt();
		
		System.out.println(nombre + "-" + apellidos + "-" + altura + "-" + edad);

		
	
	}

}
























