public class Cellule {
	private enum Case{
	    FOND(Constantes.fond,true),
	    MUR(Constantes.mur,false),
	    PIEGE(Constantes.piege,true),
		PORTE(Constantes.porteVerouiller,false);

	    private final char symbole;
	    private final boolean crosable;

	    private Case(char symbole,boolean crosable){
	        this.symbole = symbole;
	        this.crosable= crosable;
	    }
	    
	    public static Case fromSymbole(char c) {
	        for (Case type : Case.values()) {
	            if (type.symbole == c) {
	                return type;
	            }
	        }
	        throw new IllegalArgumentException("Symbole inconnu : " + c);
	    }

	    public char getSymbole(){
	        return symbole;
	    }
	    
	    public boolean getCrosable(){
	        return crosable;
	    }
	}
	
	/**
	 * numéro de ligne
	 */
	private final int ligne;
	
	/**
	 * numéro de colomne
	 */
	private final int colomne;
	
	/**
	 * contient le type de la cellule
	 */
	private Case tuile;
	
	/**
	 * la cellule à une pièce
	 */
	private boolean aUnePiece;

//constructeur Cellule
	/**
	 * initialise le Cellule avec sa position et sont contenur
	 * @param ligne colomne char 
	 */
	public Cellule(int ligne, int colomne, char symbole, boolean piece){
		this.ligne=ligne;
		this.colomne=colomne;
		aUnePiece=piece;
		tuile=Case.fromSymbole(symbole);
	}
	
//accesseur en lecture
	/**
	 * accesseur de cellule
	 * @return renvoie le numéro de ligne de la case
	 */
	public int getLigne() {return ligne;}
	
	/**
	 * accesseur de cellule
	 * @return renvoie le numéro de colomne de la case
	 */
	public int getColomne() {return colomne;}

	/**
	 * accesseur de cellule
	 * @return renvoie le type de case
	 */
	public char getTuile() {return tuile.getSymbole();}
	
	/**
	 * accesseur de cellule
	 * @return renvoie le type de case
	 */
	public boolean getTuileCrosable() {return tuile.getCrosable();}
	
	/**
	 * accesseur de cellule
	 * @return renvoie si la case possède une pièce
	 */
	public boolean getAPiece() {return aUnePiece;}
	

//accesseur en ecriture
	/**
	 * diminue le nombre de pièces dans le niveaux
	 */
	public void recupAPiece(){
		aUnePiece=false;
	}
	
	/**
	 * change la case d'une cellule
	 */
	public void setTuile(char symbole){
		tuile=Case.fromSymbole(symbole);
	}
	

//
	/**
	 * affiche la cellule
	 * @return String
	 */
	@Override
	public String toString(){
		return ""+this.getTuile();
	}

	/**
	 * verifie si deux cellule sont identique
	 * @param object
	 * @return boolean
	 */
	@Override
	public boolean equals(Object object){
		if(object instanceof Cellule &&
		ligne==((Cellule) object).getLigne() &&
		colomne==((Cellule) object).getColomne() &&
		this.getTuile() == ((Cellule) object).getTuile() &&
		aUnePiece == ((Cellule) object).getAPiece()){
			return true;
			}
		return false;
	}
	
	/**
	 * renvoie une valeur unique pour chaque élément
	 * @return int
	 */
	@Override
	public int hashCode() {
	    int result = 1;
	    result = 31 * result + ligne;
	    result = 31 * result + colomne;
	    result = 31 * result + (this.getTuile() == '\0' ? 0 : System.identityHashCode(getTuile()));
	    result = 31 * result + (aUnePiece ? 1 : 0);
	    return result;
	}
}
