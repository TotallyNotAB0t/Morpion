public class Test {

	public static void main(String[] args) {
		
		//Impl�mentation modele vue controleur
		//Il est possible de jouer avec des morpions de taille diff�rente !
		ModeleMorpion m = new ModeleMorpion(3);
		ControleurMorpion c = new ControleurMorpion(m);
		VueMorpion v = new VueMorpion(c, m);
		
		c.associerVue(v);
		v.activerVueMorpion();
	}
}