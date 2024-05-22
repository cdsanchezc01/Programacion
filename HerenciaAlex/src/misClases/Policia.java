package misClases;

public class Policia extends Persona implements FuncionesPersona{
	
	public Policia(int edad, double altura, String nacion, String color, Sexo tipoSexo, double salario , double longitud) {
		super(edad, altura, nacion, color, tipoSexo,salario,longitud);
		
	}

	@Override
	public void cobra() {
		if (getTipoSexo() == Sexo.HOMBRE) {
			if (getColor().equals("Negro")) {
				System.out.println(" Hombre y negro");
				setSalario(15.1);
				setLongitud(4.7);
				System.out.println(" Cobra :  " +  getSalario());
			} else {
				System.out.println(" Hombre y blanco ");
				setSalario(10.0);
				setLongitud(2.7);
				System.out.println(" Cobra :  " +  getSalario());
			}

		} else {
			System.out.println(" MUJER ");
			setSalario(0);
			setLongitud(0);
			System.out.println(" Cobra :  " +  getSalario() + "");
		}

		
		
	}

	@Override
	public void leMide() {
		System.out.println(" Le mide : " + getLongitud());
		
		
	}

}

















