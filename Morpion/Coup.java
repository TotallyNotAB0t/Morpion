public class Coup {
	
	private int ligne;
	private int colonne;
	private Symbole symbole;
	
	public Coup(int uneLigne, int uneColonne, Symbole unSymbole) {
		this.ligne = uneLigne;
		this.colonne = uneColonne;
		this.symbole = unSymbole;
	}
	
	public int getLigne() {
		return this.ligne;
	}
	
	public int getColonne() {
		return this.colonne;
	}
	
	public Symbole getSymbole() {
		return this.symbole;
	}
	
	public String toString() {
		return "Le coup joué est " + this.getSymbole() + " en ligne " + this.getLigne() + ", colonne " + this.getColonne() + '.';
	}
}