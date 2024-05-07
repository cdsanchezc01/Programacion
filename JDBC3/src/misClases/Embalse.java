package misClases;

public class Embalse {
	
	private int codEmbalse ;
	private String nom;
	
	
	
	public Embalse() {
	
	}
	
	public int getCodEmbalse() {
		return codEmbalse;
	}

	public void setCodEmbalse(int codEmbalse) {
		this.codEmbalse = codEmbalse;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Embalse(int codEmbalse, String nom) {
		this.codEmbalse = codEmbalse;
		this.nom = nom;
	}
	

}
