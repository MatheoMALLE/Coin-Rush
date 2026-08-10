import java.util.Random;

/**
 * classe qui gère les ennemi
 */
public class Ennemi extends Unite {
	
//constructeur Ennemi
	public Ennemi(String nom,int x,int y){
		super(nom,5,1);
		super.x=x;
		super.y=y;
		super.spawnX=x;
		super.spawnY=y;
	}

//accesseur en lecture

//accesseur en ecriture
	/**
	 * methode pour déplacer les ennemis
	 */
	public void mvUnite(char direction,Cellule [][] tab, Joueur hero){
		Random rand = new Random();
		char[] lettres = {Constantes.up, Constantes.left,Constantes.down,Constantes.right};
		int index = rand.nextInt(lettres.length);
		direction=lettres[index];
		
		super.mvUnite(direction,tab,hero);
	}
	
    @Override
	protected boolean verifMv(int x, int y, Cellule [][] tab) {
	    if(tab[y][x].getTuileCrosable() && tab[y][x].getTuile()!=Constantes.piege){
	    	return true;
	    }
		return false;
	}
}

//