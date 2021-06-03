public interface IModeleMorpion {
	
	Symbole[][] grille();
	
	void enregistrerCoupPropose(int uneLigne, int uneColonne);
	
	Coup coupPropose();
	
	int dimensionGrille();
	
	void joueCoup();
	
	boolean coupValide();
	
	Joueur joueurActif();
	
	void changeJoueurActif();
	
	boolean partieFinie();
}