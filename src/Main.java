import java.util.Scanner;

/**
 * main
 */
public class Main {
	public static void main(String[] args) {
/*
        if (args.length != 2) {
            System.out.println("Usage : java -jar MonProgramme.jar <niveau>");
            System.out.println("Exemple : java -jar monde_2.jar 'niveaux 2.txt'");
            return;
        }
*/
/*
		System.out.println("Hello Word");

		Joueur j1 = new Joueur("alice");
		Joueur j2 = new Joueur("Bob");
		System.out.printf(j1.toString());
		System.out.printf(j2.toString());
		j1.ajouterScore(8);
		j2.ajouterScore(-5);
		System.out.printf(j1.toString());
		System.out.printf(j2.toString());
		
		System.out.print(j1.equals(j2));
		System.out.print(j1.equals(j1));
		System.out.print(Joueur.getNbrJoueur());
		
		Joueur j3 = new Joueur(null);
		System.out.printf(j3.toString());

		Grille n1= new Grille(7,7,j1);
		System.out.printf(n1.toString());
		
		Grille n2= new Grille(7,7,j2);
		System.out.printf(n1.equals(n2)+"%n");
		
		n1.mvJoueur('d');
		System.out.printf(n1.toString());
		n1.mvJoueur('r');
		System.out.printf(n1.toString());
		n1.mvJoueur('u');
		System.out.printf(n1.toString());

		Scanner sc= new Scanner(System.in);
		System.out.print("quelle est votre nom: ");
		String test=sc.nextLine();
		Joueur j4 = new Joueur(test);
		Grille n3= new Grille(2,2,j4);
		
		System.out.printf(n3.toString());
		
		boolean enJeux=true;
		while(enJeux) {
			sc= new Scanner(System.in);
			System.out.print("veuillez choisir une direction (ZQSD): ");
			test=sc.nextLine();
			
			if("zqsd".contains(test)) {
				n3.mvJoueur(test.charAt(0));
				System.out.printf(n3.toString());
			}
			else if(test.toLowerCase().equals("exit")){
				enJeux=false;
			}
		}

		System.out.print(Joueur.getNbrJoueur());
*/
		boolean enJeux=true;
		boolean enPartie=false;
		
		Scanner sc= new Scanner(System.in);
		System.out.print("quelle est le nom du joueur: ");
		String test=sc.nextLine();
		
		Joueur j5=new Joueur(test);
		Grille n4=null;
		int level=0;
		
		while(enJeux) {
			sc= new Scanner(System.in);
			if(level==0) {
				System.out.print("voullez vous lancer une parti (Y/N): ");
			}
			else {
				System.out.printf("voullez vous lancer le %dème niveaux (Y/N): ",level+1);
			}
			test=sc.nextLine();
			
			if(test.toLowerCase().equals("y")) {
				enPartie=true;
				n4=new Grille(args[level],j5);
			}
			else{
				enJeux=false;
				enPartie=false;
			}
			
			while(enPartie) {
				System.out.printf(n4.toString());
				sc= new Scanner(System.in);
				System.out.print("veuillez choisir une direction (ZQSD): ");
				test=sc.nextLine();
				
				if(!test.equals("") && "zqsd".contains(test)) {
					n4.Jeu(test.charAt(0));
				}
				else if(test.toLowerCase().equals("exit")){
					enPartie=false;
				}
				
				if(n4.getNbrPiece()==0) {
					System.out.print(level);
					
					if(level+1<args.length) {
						System.out.printf("bravo pour avoir terminer ce niveau %nvotre score est de: %d %n",j5.getScore());				
						level++;
						enPartie=false;
					}
					else {
						System.out.printf("bravo pour avoir terminer cette partie %nvotre score est de: %d %n",j5.getScore());				
						level=0;
						enPartie=false;
					}
				}
				else if(n4.getJoueur().getVie()==0) {
					System.out.printf("Game Over%n");
					enPartie=false;
					
					j5=new Joueur(test);
					level=0;
				}
			}
		}
	}
}
