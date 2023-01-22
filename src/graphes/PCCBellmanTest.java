package graphes;

import java.util.ArrayList;

import org.junit.Test;

import graphes.types.GrapheLA;

public class PCCBellmanTest {

	@Test
	public void test() {
		IGraphe g = new GrapheLA(4);
		PCCBellman pb = new PCCBellman(4);

		/*g.ajouterArc(1, 5, 2);
		g.ajouterArc(1, 4, 3);
		g.ajouterArc(2, 3, 4);
		g.ajouterArc(4, 4, 2);
		g.ajouterArc(3, -6, 1);
		*/
		
		ArrayList<Integer> noeuds = new ArrayList<>();//Création de l'arraylist noeuds
		IBCC bj = new PCCBellman(g.getNbSommets());//interface
		int sommetInitiale = 1;//source
		int sommetFinale = 7;//Destination
		bj.NiveauBellman(g);//Définir les niveaux
		bj.bellman(g, sommetInitiale);//Algorithme de bellman
		int v = bj.chemin(sommetInitiale, sommetFinale, noeuds);//Réalisation du chemin entre la source et destination et de la valeur entre les 2 sommets
		if(v != 10000 && noeuds.size() > 1) {//si valeur different de 10000 et que la taille de l'arraylist noeuds est superieur à alors 
			System.out.println("Bellman sans circuit");//afficher ce message
			System.out.println(v);//Afficher la valeur entre les deux sommets
			for(int i = noeuds.size()-1; i > -1; --i) {
				System.out.print(noeuds.get(i) + " ");//Afficher la liste
			}
		}
		else if (v != 0 && v == 10000){//Sinon
			System.out.println("pas de chemin entre " + sommetInitiale + " et " + sommetFinale);//Afficher ce message
		}
		if (v == 0) {
			System.out.println("Bellman sans circuit");//afficher ce message
	    	System.out.println(v);//Afficher la valeur entre les deux sommet
	    	System.out.print(noeuds.get(0) + " ");//Afficher la liste
		}
	}

}
