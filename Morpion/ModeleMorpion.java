import java.util.ArrayList;

public class ModeleMorpion implements IModeleMorpion, ISujet {
	
	public static int nb_cases;
	private Symbole[][] grille;
	private Coup coupPropose;
	private int nombreCoup;
	private Joueur joueurActif;
	public ArrayList<IObservateur> observateurs;
	
	public ModeleMorpion(int n) {
		nb_cases = n;
		this.grille = new Symbole[nb_cases][nb_cases];
		this.init_grille();
		this.setJoueurActif();
		this.observateurs = new ArrayList<IObservateur>();
	}
	
	public Symbole[][] grille() {
		return this.grille;
	}
	
	public char getSymboleGrille(int ligne, int colonne) {
		return this.grille[ligne][colonne].getLibelle();
	}
	
	private void init_grille() {
		for (int i = 0 ; i < nb_cases ; i++) {
			for (int j = 0; j < nb_cases; j++) {
				this.grille[i][j] = Symbole.VIDE;
			}
		}
	}
	
	private Symbole symboleActif() {
		if (this.joueurActif == Joueur.JOUEUR_CROIX) {
			return Symbole.CROIX;
		}
		else {
			return Symbole.ROND;
		}
	}
	
	public void enregistrerCoupPropose(int ligne, int colonne) {
		this.coupPropose = new Coup(ligne, colonne, this.symboleActif());
	}

	public Coup coupPropose() {
		return this.coupPropose;
	}

	public int dimensionGrille() {
		return nb_cases;
	}
	
	public void setJoueurActif() {
		this.joueurActif = Joueur.JOUEUR_CROIX;
	}


	public Joueur joueurActif() {
		return this.joueurActif;
	}

	public void changeJoueurActif() {
		
		if (this.joueurActif == Joueur.JOUEUR_ROND) {
			this.joueurActif = Joueur.JOUEUR_CROIX;
		}
		else {
			this.joueurActif = Joueur.JOUEUR_ROND;
		}
	}
	
	//Notification des observateurs a chaque coup joué, valide ou non.
	public void joueCoup() {
		this.grille[this.coupPropose.getLigne()][this.coupPropose.getColonne()] = this.coupPropose.getSymbole();
		this.notifierObservateur();
	}
	
	private static boolean coordonneeOk(int coordonnee) {
		return 0 <= coordonnee && coordonnee < nb_cases;
	}

	public boolean coupValide() {
		this.nombreCoup += 1;
		int ligne = this.coupPropose.getLigne();
		int colonne = this.coupPropose.getColonne();
		return ModeleMorpion.coordonneeOk(ligne) && ModeleMorpion.coordonneeOk(colonne) && this.grille[ligne][colonne] == Symbole.VIDE;
	}

	public boolean partieFinie() {
		
		int ligneFinie = 0;
		int colonneFinie = 0;
		int diagonaleDFinie = 0;
		int diagonaleGFinie = 0;
		
		for (int i = 0; i < ModeleMorpion.nb_cases-1;) {
			for (int j = 0; j < ModeleMorpion.nb_cases-1; j++) {
				
				//Vérification de la ligne
				if ((grille[i][j] == grille[i][j+1]) && (grille[i][j] != Symbole.VIDE)) {
					ligneFinie += 1;
				}
				
				//Vérification de la colonne
				if ((grille[j][i] == grille[j+1][i]) && (grille[j][i] != Symbole.VIDE)) {
					colonneFinie += 1;
				}
			}
			
			//Vérification de la diagonale \
			if ((grille[i][i] == grille[i+1][i+1]) && (grille[i][i] != Symbole.VIDE)) {
				diagonaleDFinie += 1;
			}
			
			
			//Vérification de la diagonale /
			if ((grille[i][nb_cases-1-i] == grille[i+1][(nb_cases-1)-i-1]) && (grille[i][nb_cases-1-i] != Symbole.VIDE)) {
				diagonaleGFinie += 1;
			}
			

			//Vérification après passage dans un i
			if( ligneFinie == nb_cases-1 || colonneFinie == nb_cases-1 || diagonaleDFinie == nb_cases-1 || diagonaleGFinie == nb_cases-1 ) {
				return true;
			} else {
				ligneFinie = colonneFinie =0;
				i++;
			}
		}
		
		if (egalite()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean egalite() {
		if (this.nombreCoup == nb_cases * nb_cases) {
			return true;
		} else {
			return false;
		}
	}

	public void enregistrerObservateur(IObservateur o) {
		this.observateurs.add(o);
	}

	
	public void supprimerObservateur(IObservateur o) {
		this.observateurs.remove(o);
	}

	public void notifierObservateur() {
		for (int i = 0; i < this.observateurs.size(); i++) {
			this.observateurs.get(i).actualiser();
		}
	}
}