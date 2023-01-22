package graphes.ihm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import graphes.IGraphe;
import graphes.IPCC;
import graphes.PCCBellman;
import graphes.PCDDjikstra;
import graphes.types.GrapheLA;
import graphes.IBCC;

public class GraphImporter {
	public static void main(String[] args) throws NumberFormatException, FileNotFoundException, IOException {
		Arc df = new Arc();
		IGraphe g = importer("C:\\Graphes D2O\\Graphes\\src\\graphe\\sc\\g-10-8.txt", df);
		System.out.print(g);
		System.out.println("debut fin : "+ df.getSource() + " ==> "+ df.getDestination());
		verifierGraphes();
		/*
		 //Algotithme de Dijkstra 
		ArrayList<Integer> noeuds = new ArrayList<>();//Création de l'arraylist noeuds
		IPCC dj = new PCDDjikstra(g.getNbSommets());//interface
		int sommetInitiale = df.getSource();//source
		int sommetFinale = df.getDestination();//Destination
		dj.estOk(g);//Verifier que le graphe ne contient pas de valeur négatif
		dj.Djikstra(g, sommetInitiale);//Algorithme de Djikstra
	    int v = dj.Liste(sommetInitiale, sommetFinale, noeuds);//Réalisation du chemin entre la source et destination et de la valeur entre les 2 sommets
	    if(v != 10000 && noeuds.size() > 1) {//si valeur different de 10000 et que la taille de l'arraylist noeuds est superieur à alors 
	    	System.out.println("Dijkstra");//afficher ce message
	    	System.out.println(v);//Afficher la valeur entre les deux sommets
	    	for(int i = noeuds.size()-1; i > -1; --i) {
	    		System.out.print(noeuds.get(i) + " ");//Afficher la liste
	    	}
		}
 		if (v == 0) {
			System.out.println("Dijkstra");//afficher ce message
	    	System.out.println(v);//Afficher la valeur entre les deux sommet
	    	System.out.print(noeuds.get(0) + " ");//Afficher la liste
		}
		else if (v != 0 && v == 10000){//Sinon
			System.out.println("pas de chemin entre " + sommetInitiale + " et " + sommetFinale);//Afficher ce message
		}*/
		
		//Algorithme de bellman
		ArrayList<Integer> noeuds = new ArrayList<>();//Création de l'arraylist noeuds
		IBCC bj = new PCCBellman(g.getNbSommets());//interface
		int sommetInitiale = df.getSource();//source
		int sommetFinale = df.getDestination();//Destination
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
		if(v == 10000){//Sinon
			System.out.println("pas de chemin entre " + sommetInitiale + " et " + sommetFinale);//Afficher ce message
		}
		if(v == 0) {
			System.out.println("Bellman sans circuit");//afficher ce message
			System.out.println(v);//Afficher la valeur entre les deux sommets
			System.out.print(noeuds.get(0) + " ");//Afficher la liste
		}
		
	}

public static void verifierGraphes() throws FileNotFoundException {
	IGraphe g;
	Arc df = new Arc(); 
	String dirStr = System.getProperty("user.dir")+ "\\graphe\\sc";
	System.out.println("Working Directory = " + dirStr);
	File dir = new File(dirStr);
	  File[] directoryListing = dir.listFiles();
	  if (directoryListing != null) {
	    for (File child : directoryListing) {
	      System.out.println(child);
	      g = importer(child, df);
	      System.out.println(g.getNbSommets() + " sommets");
	      System.out.println("debut et fin du chemin à trouver : "+ df.getSource()+ " ==> "+ df.getDestination()+"\n");
	    }
	  } else {
	    System.out.println("Erreur : "+ dirStr + " n'est pas un réperoire");
	  }
}

	public static Arc parse(String string) {
		String[] parts = string.split(" ");
		int source, valuation, destination;
		try {  
			source = Integer.parseInt(parts[0]);
			valuation = Integer.valueOf(parts[1]);
			destination = Integer.parseInt(parts[2]);
		} catch (Exception e) {
			throw new IllegalArgumentException(string + " n'est pas un arc");
		}
		Arc a = new Arc(source, valuation, destination);
		return a;
	}
	private static IGraphe importer(File file, Arc df) throws FileNotFoundException {
		
		Scanner sc = new Scanner(file);
		String line;
		IGraphe g;
		if (! sc.hasNextLine()) {
			sc.close();
    		throw new IllegalArgumentException("Pas de graphe dans "+ file);
		}
		line = sc.nextLine();
		int nbNodes = Integer.parseInt(line.trim());
		g = new GrapheLA(nbNodes);
		Arc a;
		while (sc.hasNextLine()) {
			line = sc.nextLine();
			a = parse(line);
			if (sc.hasNextLine())
				g.ajouterArc(a.getSource(),  a.getValuation(), a.getDestination());
			else {// la derniere ligne n'est pas un arc mais le debut et la fin du chemin à trouver
				df.set(a);
			}
		}
		sc.close();
		return g;		
	}
	private static IGraphe importer(String filepath, Arc df) 
								throws  NumberFormatException, IOException, FileNotFoundException {
		File file = new File(filepath);
		return importer(file, df);
      }
}
