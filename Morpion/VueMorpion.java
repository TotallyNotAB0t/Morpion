import java.util.Scanner;

public class VueMorpion implements IVueMorpion, IObservateur {

	private ControleurMorpion controleur;
	private ModeleMorpion modele;
	
	public VueMorpion(ControleurMorpion unControleur, ModeleMorpion unModele) {
		this.controleur = unControleur;
		this.modele = unModele;
		modele.enregistrerObservateur(this);
	}
	
	//Actualisation automatique de la grille et du coup joué a chaque modification
	public void actualiser() {
		this.afficherCoupJoue();
		this.afficherGrille();
	}

	public void activerVueMorpion() {
		this.afficherGrille();
		this.saisirCoup();
	}

	public void saisirCoup() {
		this.controleur.gererSaisirCoup();
	}
	
	public void afficherJoueurActif() {
		System.out.println("C'est à " + this.modele.joueurActif() + " de jouer.");
	}
	
	public void afficherCoupJoue() {
		System.out.println(modele.coupPropose());
	}

	public void afficherGrille() {
		System.out.println("Grille courante :");
		System.out.println();
		
		//Affichage de l'en-tete
		System.out.print(" ");
		for (int i = 0; i < ModeleMorpion.nb_cases; i++) {
			System.out.print(" " + i);
		}
		System.out.println();
		
		//Affichage du plateau
		for (int i = 0; i < ModeleMorpion.nb_cases; i++) {
			System.out.print(i);
			for (int j = 0; j < ModeleMorpion.nb_cases; j++) {
				System.out.print("|" + modele.getSymboleGrille(i, j));
			}
			System.out.println("|");
		}
		System.out.println("");
	}

	public int saisirChoixMenu() {
		System.out.println("Pour jouer, entrez 1.");
		System.out.println("Pour sortir du jeu, entrez 2.");
		Scanner input = new Scanner(System.in);
		int unChoix = input.nextInt();
		return unChoix;
	}

	public int saisirLigne() {
		System.out.println("Veuillez saisir une ligne");
		Scanner input = new Scanner(System.in);
		int uneLigne = input.nextInt();
		return uneLigne;
	}

	public int saisirColonne() {
		System.out.println("Veuillez saisir une colonne");
		Scanner input = new Scanner(System.in);
		int uneColonne = input.nextInt();
		return uneColonne;
	}

	public void afficherErreurCoupPropose() {
		System.out.println("Le coup proposé est invalide.");
	}

	public void afficherFinPartie() {
		System.out.println("La partie est terminée !");
		System.out.println("Le gagnant est " + this.modele.joueurActif() + '.');
		this.afficherGrille();
	}
}