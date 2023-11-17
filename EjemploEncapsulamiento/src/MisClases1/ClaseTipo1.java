package MisClases1;

public class ClaseTipo1 {
	//ATRIBUTOS
	
	private int atPrivado;
	public int atPublic;
	protected int atProtected;
	int atDefault;
	
	//METODOS
	
	//CONSTRUCTOR 
	ClaseTipo1(){
		atPrivado=10;
		atPublic=11;
		atProtected=12;
		atDefault=13;
	}
	
	private void metodoPrivado() {
		System.out.println("Valor de atPrivado es : " + atPrivado);
		System.out.println("Valor de atPublic es : " + atPublic);
		System.out.println("Valor de atProtected es : " + atProtected);
		System.out.println("Valor de atDefault es : " + atDefault);
	}
	
	public void metodoPublic() {
		System.out.println("Valor de atPrivado es : " + atPrivado);
		System.out.println("Valor de atPublic es : " + atPublic);
		System.out.println("Valor de atProtected es : " + atProtected);
		System.out.println("Valor de atDefault es : " + atDefault);
	}
	
	protected void metodoProtected() {
		System.out.println("Valor de atPrivado es : " + atPrivado);
		System.out.println("Valor de atPublic es : " + atPublic);
		System.out.println("Valor de atProtected es : " + atProtected);
		System.out.println("Valor de atDefault es : " + atDefault);
	}
	
	void metodoDefault() {
		System.out.println("Valor de atPrivado es : " + atPrivado);
		System.out.println("Valor de atPublic es : " + atPublic);
		System.out.println("Valor de atProtected es : " + atProtected);
		System.out.println("Valor de atDefault es : " + atDefault);
	}
}
