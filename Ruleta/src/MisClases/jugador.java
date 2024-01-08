package MisClases;

public class jugador {
	
//	ATRIBUTOS
	int identificador;
	
	int dineroAcumulado;
	int dineroPanel;
	int numComodines;

//	CONSTRUCTOR
	jugador(int id){
		identificador=id;
		dineroAcumulado=0;
		dineroPanel=0;
		numComodines=0;
	}
	
	int getNumComodines() {
		return numComodines;
	}
	
	void incrementarComodines() {
		numComodines++;
		
	}
	
	void decrementarComodines() {
		numComodines--;
	}	
		
	void setDineroPanelCero() {
		dineroPanel=0;
	}
	
	int getDineroPanel() {
		return dineroPanel;
	}
	
	void incrementarDineroPanel(int cantidad) {
		dineroPanel+=cantidad;
	}
	

	void decrementarDineroPanel(int cantidad) {
		dineroPanel-=cantidad;
	}	
	
	int getDineroAcumulado() {
		return dineroAcumulado;
	}
	
	void incrementarDineroAcumulado(int cantidad) {
		dineroPanel+=cantidad;
	}
	
	int getIdentificador() {
		return identificador;
	}

}









