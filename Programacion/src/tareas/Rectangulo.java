package tareas;

public class Rectangulo {
	//ATRIBUTOS RECTANGULO
	
		double lado1;
		double lado2;
	//METODOS	
		//CONSTRUCTORES
		public Rectangulo() { 
			lado1 = 7.40;			
			lado2 = 2.55;
		}	

		public double area() {
			double area = lado1 * lado2;
			System.out.println("El resultado del rectangulo es ->" + area);				
			return area;
			
		}

}
