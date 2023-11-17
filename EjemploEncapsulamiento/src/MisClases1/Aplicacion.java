package MisClases1;

import MisClases2.ClaseTipo2;

public class Aplicacion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ClaseTipo1 ob1=new ClaseTipo1();
		
		//ACCESO A ATRIBUTOS Y METODOS PUBLIC
		ob1.atPublic=100;
		ob1.metodoPublic();
		
		//ACCESO A ATRIBUTOS Y METODOS PROTECTED
	    ob1.atProtected=100;
	    ob1.metodoProtected();
	    
	  //ACCESO A ATRIBUTOS Y METODOS DEFAULT 
	    ob1.atDefault=100;
	    ob1.metodoDefault();
	    
	  //ACCESO A ATRIBUTOS Y METODOS PRIVATE
	    //ob1.atPrivate=100;   //NO SE PUEDE ACCEDER PORQUE ES PRIVATE
	    //ob1.metodoPrivate(); //NO SE PUEDE ACCEDER PORQUE ES PRIVATE
	    
	    //PARA PODER INVOCAR AL CONSTRUCTOR, TENGO DE HACERLE PUBLIC
	    //POR ESTAR EN PAQUETES DISTINTOS
	    ClaseTipo2 ob2=new ClaseTipo2();
	    
	
		//ACCESO A ATRIBUTOS Y METODOS PUBLIC
		ob2.atPublic=100;
		ob2.metodoPublic();
		
		//ACCESO A ATRIBUTOS Y METODOS PROTECTED
//	    ob2.atProtected=100; //NO PUEDO ACCEDER POR ESTAR EN PAQUETES DISTINTOS Y NO TENER HERENCIA ENTRE OBJETOS
//		ob2.metodoProtected(); //NO PUEDO ACCEDER POR ESTAR EN PAQUETES DISTINTOS Y NO TENER HERENCIA ENTRE OBJETOS 
	    
	  //ACCESO A ATRIBUTOS Y METODOS DEFAULT 
//      ob2.atDefault=100;	//NO PUEDO ACCEDER POR ESTAR EN PAQUETES DISTINTOS
//      ob2.metodoDefault(); //NO PUEDO ACCEDER POR ESTAR EN PAQUETES DISTINTOS
	    
	  //ACCESO A ATRIBUTOS Y METODOS PRIVATE
	    //ob2.atPrivate=100;   //NO SE PUEDE ACCEDER PORQUE ES PRIVATE
	    //ob2.metodoPrivate(); //NO SE PUEDE ACCEDER PORQUE ES PRIVATE
	    
	    


	}

}
