/**
 * classe qui gère le joueur
 */
public class Joueur extends Unite{
	/**
	 * score du joueur
	 */
	private int score;
	/**
	 * nombre de joueur
	 */
	private static int nbrJoueur=0;
//constructeur Joueur
	/**
	 * initialise le Joueur avec un nom et sont score a zero
	 * @param le nom 
	 */
	public Joueur(String nom){
		super(nom,5,0);
		Joueur.nbrJoueur+=1;
		score=0;
	}

	/**
	 * initialise le Joueur avec un nom par défaut et sont score a zero
	 */
	public Joueur(){
		this("Joueur"+getNbrJoueur());
	}

//accesseur en lecture
	/**
	 * accesseur de Joueur sur le score
	 * @return renvoie le score du joueur
	 */
	public int getScore() {return score;}
	
	/**
	 * static method de Joueur pour afficher nbrJoueur 
	 * @return renvoie le nombre de Joueur crée
	 */
	public static int getNbrJoueur() {return Joueur.nbrJoueur;}
	
//accesseur en ecriture
	/**
	 * modifie le score du joueur
	 * le score ne peux pas être négatif
	 * @param nbr de point 
	 */
	public void ajouterScore(int point){
		score+=point;
		if (score < 0) {
			score=0;
		}
	}
	
	/**
	 * methode pour déplacer le Joueur
	 */
	public void mvUnite(char direction,Cellule [][] tab,Joueur hero){
		super.mvUnite(direction,tab,hero);
	}
//
	/**
	 * affiche toute les infos du Joueur
	 * @return String
	 */
	@Override
	public String toString() {
		return this.getNom() +" : "+this.getScore()+" pt"+(this.getScore() > 1 ? "s" : "");
	}

	/**
	 * verifie si l'object est égal au Joueur
	 * @param Object Un joueur
	 * @return boolean
	 */
	@Override
	public boolean equals(Object object){
		if(object instanceof Joueur && this.getNom().toUpperCase().equals(((Joueur) object).getNom().toUpperCase())){
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
	    return this.nom.hashCode();
	}
}
