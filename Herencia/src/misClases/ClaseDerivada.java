package misClases;

public class ClaseDerivada extends ClaseBase {
	
	public String textoPropio;
	
	public ClaseDerivada(String texto, int numero) {
		super(numero,texto);
		this.textoPropio=textoPropio;
	}
	
	@Override
	public void imprimirContenido() {
	 	System.out.println(textoPropio+ numero + numero);
	}

	

}
