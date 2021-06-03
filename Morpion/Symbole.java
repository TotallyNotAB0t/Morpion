public enum Symbole {
	
	 VIDE('-'), CROIX('X'), ROND('O');
	
	private final char libelle;
	
	private Symbole(char unLibelle) {
		this.libelle = unLibelle;
	}
	
	public char getLibelle() {
		return libelle;
	}
}