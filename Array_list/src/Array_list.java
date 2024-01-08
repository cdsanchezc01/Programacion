import java.util.ArrayList;

public class Array_list {

	public static void main(String[] args) {
		
//		Array de tipo String, no es primitivo
		
		ArrayList<String> palabras=new ArrayList<String>();
		palabras.add(" Hola ");
		palabras.add(" buenos días ");
		palabras.add(0," Feliz año ");
//		Añadiendo el "0", indicas la posición

//		Como no se puede meter primitivos, su utiliza "Integer"
		
		ArrayList<Integer> numeros=new ArrayList<Integer>();
		numeros.add(125);
		numeros.add(307);
		numeros.add(100);
//		El add sirve para introducir un solo elemento
		
		ArrayList<Integer> numeros2=new ArrayList<Integer>();
		numeros.add(10);
		numeros.add(20);
		
		numeros.addAll(numeros2);  //Sirve para insertar un grupo de elementos
	
		System.out.println(" Los elelmento " + palabras);
		
		
		
	}

}
