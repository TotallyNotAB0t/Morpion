public interface ISujet {
	
	void enregistrerObservateur(IObservateur o);
	
	void supprimerObservateur(IObservateur o);
	
	void notifierObservateur();
}