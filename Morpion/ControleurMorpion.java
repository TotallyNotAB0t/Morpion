public class ControleurMorpion implements IControleurMorpion, IObservateur {
	
	private ModeleMorpion modele;
	private VueMorpion vue;
	
	public ControleurMorpion(ModeleMorpion unModeleMorpion) {
		this.modele = unModeleMorpion;
		modele.enregistrerObservateur(this);
	}
	
	public void associerVue(VueMorpion uneVueMorpion) {
		this.vue = uneVueMorpion;
	}
	
	public boolean gererChoixMenu() {
		if (this.vue.saisirChoixMenu() == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	public void gererSaisirCoup() {
		while(this.gererChoixMenu()) {
			this.modele.enregistrerCoupPropose(this.vue.saisirLigne(), this.vue.saisirColonne());
			if (this.modele.coupValide()) {
				this.modele.joueCoup();
				if (this.modele.partieFinie()) {
					break;
				} else {
					this.modele.changeJoueurActif();
					this.vue.afficherJoueurActif();
				}
			} else {
				this.vue.afficherErreurCoupPropose();
			}
		}
		this.gererFinPartie();
	}

	public void gererFinPartie() {
		this.vue.afficherFinPartie();
	}

	//Pas besoin d'actualiser des fonctions pour l'observateur controleur
	public void actualiser() {
		
	}
}