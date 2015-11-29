package domaine;

public class Numero {

	private String code;
	private String valeur;
	private int idEntree;
	
	public Numero(String code, String valeur, int idEntree) {
		this.code = code;
		this.valeur = valeur;
		this.idEntree = idEntree;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValeur() {
		return valeur;
	}

	public void setValeur(String valeur) {
		this.valeur = valeur;
	}

	public int getIdEntree() {
		return idEntree;
	}

	public void setIdEntree(int idEntree) {
		this.idEntree = idEntree;
	}

	@Override
	public String toString() {
		return code + " : " + valeur;
	}
}
