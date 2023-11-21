package MisClases;

public class hola {



		
//TENEMOS QUE HACER UN METODO PARA SUSTITUIR UN String CON GUIONES		
	public static String sustitucion(String cadena, char caracter) {
		
		
		char[] ar=cadena.toCharArray();
		
		for (int i=0; i<cadena.length();i++){
			if(caracter==ar[i])
				ar[i]='-';
		}
		return new String(ar);
	}
	public static void main(String[] args) {
		String cadena="Hola";
		System.out.println(sustitucion(cadena,'a'));
		

	}

}
