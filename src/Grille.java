import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * classe qui gère le terrain
 */
public class Grille {
	/**
	 * tableau contenant le sol de la pièce
	 */
	private Cellule [][] tab;
	
	/**
	 * object qui gère le joueur
	 */
	private Joueur perso;
	
	private ArrayList<Unite> unites;
	
	/**
	 * le nombre de pièce dans le niveau
	 */
	private int nbrPiece;
	
	/**
	 * lister les ennemis présent dans le niveau
	 */
	private static HashSet<String> set = new HashSet<>();

//constructeur grille
	/**
	 * initialise le Joueur avec un nom et sont score a zero
	 * @param le nom 
	 */
	public Grille(String fichier, Joueur perso){
		try (FileReader reader = new FileReader(fichier);) {
		    int longueur=0;
		    int largeur=0;
		    this.unites= new ArrayList<>();
		    char charactere;
		    
		    for(int i=0;i<2;i++) {
			    charactere=(char)reader.read();
			    String nbr="";
			    while(charactere!=Constantes.separateur){
			    	nbr+=charactere;
			    	charactere=(char)reader.read();
			    }
			    if(i==0) {
				    longueur= Integer.parseInt(nbr);
			    }
			    else {
			    	largeur= Integer.parseInt(nbr);
			    }
			}
		    
		    int nbrPiece=0;
		    if(longueur>0 && largeur>0) {
		    	tab= new Cellule [largeur][longueur];
			    for (int i=0; i<tab.length; i++) {
			    	for (int j=0; j<tab[0].length; j++) {
			    		
			    		charactere=(char)reader.read();
			    		boolean aPiece=false;
			    		
			    		if(charactere==Constantes.piece) {
			    			nbrPiece+=1;
			    			charactere=Constantes.fond;
			    			aPiece=true;
			    		}
			    		else if(charactere==Constantes.hero){
			    			charactere=Constantes.fond;
			    			perso.setY(i);
			    			perso.setX(j);
			    			perso.setSpawn(j, i);
			    		}
			    		else if(charactere==Constantes.ennemi) {
			    			charactere=Constantes.fond;
			    			unites.add(new Ennemi("soldat"+unites.size(),j,i));
			    			//ajouterEnnemi();
			    		}
			    		else if(charactere==Constantes.fantome) {
			    			charactere=Constantes.fond;
			    			unites.add(new Fantomes("soldat"+unites.size(),j,i));
			    			//ajouterEnnemi();
			    		}
			    		tab[i][j]=new Cellule(j,i,charactere,aPiece);
				    }
			    }
			    this.nbrPiece=nbrPiece;
		    }
		    reader.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		this.perso=perso;
	}

//accesseur en lecture
	/**
	 * accesseur de Grille sur le tableau 2D
	 * @return renvoie le grille 2D, la maps
	 */
	public Cellule[][] getGrille() {return tab;}
	
	/**
	 * accesseur de Grille sur le Joueur
	 * @return renvoie l'Object Joueur
	 */
	public Joueur getJoueur() {return perso;}
	
	/**
	 * accesseur de Grille sur le nombre de pièces
	 * @return renvoie l'entier nombre de pièces
	 */
	public int getNbrPiece() {return nbrPiece;}
	
	/**
	 * accesseur de Grille sur l'ensemble des ennemis
	 * @return affiche les différent type d'ennemis
	 */
	public static void afficherEnnemis() {
	    for (String e : set) {
	        System.out.println(e);
	    }
	}

//accesseur en ecriture
	/**
	 * methode pour déplacer le joueur
	 */
	public void Jeu(char direction){
		this.tab[this.perso.getY()][this.perso.getX()].setTuile(Constantes.fond);
		
		perso.mvUnite(direction, tab, null);
		if(this.tab[this.perso.getY()][this.perso.getX()].getAPiece()){
			recupererPiece();
		}
		for (Unite e : unites) {
			e.mvUnite('\0',tab,this.perso);
			if(e.getX()==this.perso.getX() && e.getY()==this.perso.getY()) {
				System.out.printf(this.toString());
				this.perso.ajouterVie(-1);
				playerTouche();
				break;
			}
		}
		if(this.tab[this.perso.getY()][this.perso.getX()].getTuile()==Constantes.piege){
			this.perso.ajouterVie(-2);
			this.tab[this.perso.getY()][this.perso.getX()].setTuile(Constantes.fond);
			playerTouche();
		}
	}
	
	private void playerTouche() {
		this.perso.setX(this.perso.getSpawnX());
		this.perso.setY(this.perso.getSpawnY());
		
		for (Unite e : unites) {
			e.setX(e.getSpawnX());
			e.setY(e.getSpawnY());
		}
	}

	/**
	 * diminue le nombre de pièces dans le niveaux
	 */
	public void recupererPiece(){
		this.tab[this.perso.getY()][this.perso.getX()].recupAPiece();
		nbrPiece-=1;
		perso.ajouterScore(10);
	}
	
	/**
	 * ajouter un ennemi dans la liste des ennemis
	 */
	public static void ajouterEnnemi(String ennemi) {
	    set.add(ennemi);
	}
	
	/**
	 * suprimmer un ennemi de la liste des ennemis
	 */	
	public void supprimerEnnemi(Unite méchant) {
	    for (String e : set) {
	    	if(e.hashCode() == méchant.hashCode()) {
	    		set.remove(e);
	    		break;
	    	}
	    }
	}

//
	/**
	 * affiche le donjon
	 * @return string
	 */
	@Override
	public String toString(){
		Cellule [][] donjon=getGrille();
		String res="%n";
		String charactere;
		for (int i=0; i<donjon.length; i++) {
			for(int j=0; j<donjon[i].length; j++) {
				charactere=donjon[i][j].toString();
				if(donjon[i][j].getAPiece()){
					charactere=""+Constantes.piece;
				}
				else if(this.perso.getX()==j && this.perso.getY()==i){
					charactere=""+Constantes.hero;
				}
				for (Unite e : unites) {
					if(e.getX()==j && e.getY()==i) {
						if(e instanceof Fantomes) {
							charactere=""+Constantes.fantome;
						}
						else if(e instanceof Ennemi) {
							charactere=""+Constantes.ennemi;
						}
					}
				}
				res+=charactere;
			}
			res+="%n";
		}
		return res;
	}

	/**
	 * verifie si deux grille sont égal
	 * @param object
	 * @return boolean
	 */
	@Override
	public boolean equals(Object object){
		if(object instanceof Grille){
			Cellule [][] tabObject=((Grille) object).getGrille();
			for (int i=0; i<this.tab.length; i++) {
				for(int j=0; j<this.tab[i].length; j++) {
					if(!(tab[i][j].equals(tabObject[i][j]))){
						return false;
					}
				}
			}
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

	    for (int i = 0; i < tab.length; i++) {
	        for (int j = 0; j < tab[i].length; j++) {
	            result = 31 * result + (tab[i][j] == null ? 0 : tab[i][j].hashCode());
	        }
	    }

	    return result;
	}
}
