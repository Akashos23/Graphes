package graphes;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import graphes.types.GrapheLA;

public class PCDDjikstraTest{

	@Test
	public void test() {
		IGraphe g = new GrapheLA(7);
		PCDDjikstra dj = new PCDDjikstra(7);
		/*Exercice 3.1
		g.ajouterArc(1,1,4);
		g.ajouterArc(1,2,3);
		g.ajouterArc(2,3,7);
		g.ajouterArc(3,2,8);
		g.ajouterArc(4,3,2);
		g.ajouterArc(4,3,5);
		g.ajouterArc(5,7,8);
		g.ajouterArc(5,1,3);
		g.ajouterArc(5,5,6);
		g.ajouterArc(5,3,7);
		g.ajouterArc(7,2,2);
		g.ajouterArc(8,2,7);
		g.ajouterArc(8,4,6);
		g.ajouterArc(9,10,8);
		*/
		/*Exercice 3.2
		g.ajouterArc(1,8,2);
		g.ajouterArc(1,3,4);
		g.ajouterArc(2,5,5);
		g.ajouterArc(2,4,3);
		g.ajouterArc(3,1,6);
		g.ajouterArc(3,5,9);
		g.ajouterArc(4,1,10);
		g.ajouterArc(4,2,5);
		g.ajouterArc(5,3,7);
		g.ajouterArc(5,2,9);
		g.ajouterArc(6,5,8);
		g.ajouterArc(7,4,8);
		g.ajouterArc(9,2,8);
		g.ajouterArc(10,6,6);
		g.ajouterArc(10,6,7);
		*/
		/*Exercice 3.6.1
		g.ajouterArc(1,7,2);
		g.ajouterArc(1,1,3);
		g.ajouterArc(3,5,2);
		g.ajouterArc(3,7,6);
		g.ajouterArc(3,2,5);
		g.ajouterArc(2,4,4);
		g.ajouterArc(2,2,5);
		g.ajouterArc(2,-3,6);
		g.ajouterArc(4,4,6);
		g.ajouterArc(5,10,7);
		g.ajouterArc(6,3,5);
		g.ajouterArc(6,5,4);
		*/
		
		ArrayList<Integer> noeuds = new ArrayList<>(); 
		int sommet1 = 1;
		int sommet2 = 7;
		assertEquals(true,dj.estOk(g));
		dj.Djikstra(g, sommet1);
		int v = dj.Liste(sommet1, sommet2, noeuds);
		if(v != 10000 && noeuds.size() > 1) {//si valeur different de 10000 et que la taille de l'arraylist noeuds est superieur à alors 
	    	System.out.println("Dijkstra");//afficher ce message
	    	System.out.println(v);//Afficher la valeur entre les deux sommets
	    	for(int i = noeuds.size()-1; i > -1; --i) {
	    		System.out.print(noeuds.get(i) + " ");//Afficher la liste
	    	}
		}
		else if (v != 0 && v == 10000){//Sinon
			System.out.println("pas de chemin entre " + sommet1 + " et " + sommet2);//Afficher ce message
		}
		if(v == 0) {
			System.out.println("Dijkstra");//afficher ce message
	    	System.out.println(v);//Afficher la valeur entre les deux sommet
	    	System.out.print(noeuds.get(0) + " ");//Afficher la liste
		}
		}
}
