package MisClases;

import java.util.Collections;
import java.util.Stack;

public class app {

	public static void main(String[] args) {
		
		Stack<Integer> pila=new Stack<Integer>();  // Se crea igual que los Arraylist
		
		pila.push(2);    //  Push se introducen datos
		pila.push(3);
		pila.push(6);
		pila.push(8);
		pila.push(11);
		pila.push(13);
		pila.push(18);
		pila.push(21);
		
		System.out.println(pila);
	
		separarNumero(pila);
	
	}
	
	
//	Metodo
	public static  Stack<Integer> separarNumero(Stack<Integer> pila) {  
		
//		Ordenamos la pila en orden asccendente
		Collections.sort(pila);
		
//		Creamos una pila temporar para los pares e impares
		Stack<Integer> pilaPares=new Stack<Integer>(); 
		Stack<Integer> pilaImpares=new Stack<Integer>(); 
		
		while(!pila.isEmpty()) {						// Pop sacas dato 
			Integer numero=pila.pop();
			if(numero%2==0)
				pilaPares.push(numero);
			else pilaImpares.push(numero);
		}
		
//		Creamos la pila que rertornaremos
		Stack<Integer> pilaResultado=new Stack<Integer>();
		
//		Añadimos los elementos  de la pila de pares
		while(!pilaPares.isEmpty()) {
			pilaResultado.push(pilaPares.pop());
		}
		
//		Añadimos los elementos  de la pila de impares
		while(!pilaImpares.isEmpty()) {
			pilaResultado.push(pilaImpares.pop());
		}
		return pilaResultado;
		

	}

}
