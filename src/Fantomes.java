import java.util.Random;

public class Fantomes extends Unite {
	private char direction;
	
	
//constructeur Fantomes
		public Fantomes(String nom,int x,int y){
			super(nom,5,1);
			Random rand = new Random();
			
			super.x=x;
			super.y=y;
			super.spawnX=x;
			super.spawnY=y;
			
			char[] lettres = {Constantes.up, Constantes.left,Constantes.down,Constantes.right};
			int index = rand.nextInt(lettres.length);
			direction=lettres[index];
		}

//accesseur en lecture
	/**
	 * accesseur d'une unitée sur ça position de spawn
	 * @return renvoie la coordonée Y du spawn d'une unité
	 */
	public int getDirection() {return direction;}
		
//accesseur en ecriture
	/**
	 * methode pour déplacer les fantomes
	 */
	public void mvUnite(char direction, Cellule [][] tab, Joueur hero){
		changeDirection(hero);
		super.mvUnite(this.direction,tab,hero);
	}
	
	/**
	 * methode pour changer la direction de fantomes
	 */
	public void changeDirection(Joueur hero){
		if(hero.getX()==this.getX()) {
			if(hero.getY()>=this.getY()) {
				this.direction=Constantes.down;
			}
			else {
				this.direction=Constantes.up;
			}
		}
		else if(hero.getY()==this.getY()) {
			if(hero.getX()>=this.getX()) {
				this.direction=Constantes.right;
			}
			else {
				this.direction=Constantes.left;
			}
		}
	}
	
	/**
	 * methode qui vérifie si le fantome ne marche pas sur une case interdite
	 */
    @Override
	protected boolean verifMv(int x, int y, Cellule [][] tab) {
    	return true;
	}
//
    
}
