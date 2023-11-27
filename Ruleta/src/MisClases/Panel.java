package MisClases;

public class Panel {
	
	String pista;
	String textoAdivinar;
	char[] estadoCasilla;
	
//	CONSTRUCTOR
	Panel(String p,String texto){
		pista=p;
		textoAdivinar=texto;
		
		estadoCasilla=textoAdivinar.toCharArray();
//		SUSTITUIMOS POR '-' TODOS LOS CARACTERES QUE NO SEAN
		for(int i=0; i<estadoCasilla.length; i++) {
			char caracter=estadoCasilla[i];
			
			if(caracter!=' ' && caracter!= '\n' )
				estadoCasilla[i]='-';	
		}
		
	}
	
	void mostrarEstadoActual(){
		System.out.println(pista);
		System.out.println("_________________________\n");
		System.out.println(estadoCasilla);
	}
	
	int comprobarLetra(char letra){
		int numAciertos=0;
		for(int i=0; i<textoAdivinar.length(); i++) {
			char caracter=textoAdivinar.charAt(i);
			
			if(caracter==letra) {
				numAciertos++;
				estadoCasilla[i]=caracter;
			}
		}
		return numAciertos;
	}

}
