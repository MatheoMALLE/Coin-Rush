/**
 * classe qui gère les unitées
 */
public class Unite {
	/**
	 * nom d'une unitée
	 */
	protected final String nom;
	/**
	 * vie d'une unitée
	 */
	protected int vie;
	/**
	 * dégat d'une unitée
	 */
	protected final int degat;
	
	/**
	 * abscice d'une unitée
	 */
	protected int x;
	/**
	 * ordonner d'une unitée
	 */
	protected int y;
	/**
	 * spawn x d'une unitée
	 */
	protected int spawnX;
	/**
	 * spawn y d'une unitée
	 */
	protected int spawnY;

//constructeur Unite
	public Unite(String nom,int vie,int degat){
		this.nom=nom;
		this.vie=vie;
		this.degat=degat;
		x=0;
		y=0;
	}
	
//accesseur en lecture
	/**
	 * accesseur d'une unitée sur le nom
	 * @return renvoie le nom de l'unitée
	 */
	public String getNom() {return nom;}
	
	/**
	 * accesseur d'une unitée sur la vie
	 * @return renvoie la vie d'une unitée
	 */
	public int getVie() {return vie;}
	
	/**
	 * accesseur d'une unitée sur la position
	 * @return renvoie la coordonée x de l'unitée
	 */
	public int getX() {return x;}
	
	/**
	 * accesseur d'une unitée sur la position
	 * @return renvoie la coordonée y de l'unitée
	 */
	public int getY() {return y;}
	/**
	 * accesseur d'une unitée sur ça position de spawn
	 * @return renvoie la coordonée X du spawn d'une unité
	 */
	public int getSpawnX() {return spawnX;}
	/**
	 * accesseur d'une unitée sur ça position de spawn
	 * @return renvoie la coordonée Y du spawn d'une unité
	 */
	public int getSpawnY() {return spawnY;}
	
	
//accesseur en ecriture
	/**
	 * modifie la vie de l'unité
	 * la vie ne peux pas être négatif
	 * @param nbr de point 
	 */
	public void ajouterVie(int point){
		vie+=point;
		if (vie < 0) {
			vie=0;
		}
	}

	/**
	 * modifie le coordonnée x d'une unitée
	 * @param point déplacer l'unitée
	 */
	public void mvX(int point){
		this.x+=point;
	}
	
	/**
	 * modifie le coordonnée y d'une unitée
	 * @param point déplacer l'unitée
	 */
	public void mvY(int point){
		this.y+=point;
	}
	
	/**
	 * set la coordonnée x d'une unitée
	 * @param valeur coordonnée d'une unitée
	 */
	public void setX(int valeur){
		this.x=valeur;
	}

	/**
	 * set la coordonnée y d'une unitée
	 * @param valeur coordonnée d'une unitée
	 */
	public void setY(int valeur){
		this.y=valeur;
	}
	
	/**
	 * set le spawn d'une Unitée
	 * @param int position de l'unitée x et y
	 */
	public void setSpawn(int x,int y){
		spawnX=x;
		spawnY=y;
	}

	/**
	 * methode pour déplacer le joueur
	 */
	public void mvUnite(char direction,Cellule [][] tab, Joueur hero){
	    int newX = getX();
	    int newY = getY();

	    if(direction == 'z'){
	        newY = (newY - 1 + tab.length) % tab.length;
	    }
	    else if(direction == 's'){
	        newY = (newY + 1) % tab.length;
	    }
	    else if(direction == 'd'){
	        newX = (newX + 1) % tab[0].length;
	    }
	    else if(direction == 'q'){
	        newX = (newX - 1 + tab[0].length) % tab[0].length;
	    }

	    // Vérification après calcul
	    if(verifMv(newX,newY,tab)){
	        setX(newX);
	        setY(newY);
	    }
	}

	/**
	 * methode qui vérifie si les unitées ne marche pas sur des cases interdites
	 */
	protected boolean verifMv(int x, int y, Cellule [][] tab) {
	    if(tab[y][x].getTuileCrosable()){
	    	return true;
	    }
		return false;
	}

//
	/**
	 * affiche toute les infos d'une unitée
	 * @return String
	 */
	@Override
	public String toString() {
		return this.getNom() +" : "+this.getVie();
	}

	/**
	 * verifie si l'object est égal à une unitée
	 * @param Object Un joueur
	 * @return boolean
	 */
	@Override
	public boolean equals(Object object){
		if(object instanceof Unite && this.getNom().toUpperCase().equals(((Unite) object).getNom().toUpperCase())){
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
