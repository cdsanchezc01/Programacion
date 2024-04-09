package misClases;

public class ClaseBase {

	int numero;
	String texto;

	public ClaseBase(int numero, String texto) {

		this.numero = numero;
		this.texto = texto;

	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	
	public void imprimirContenido() {
		System.out.println(texto + numero);
	}



}
	