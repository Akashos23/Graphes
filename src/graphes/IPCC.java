package graphes;

import java.util.ArrayList;

public interface IPCC {
	public boolean estOk(IGraphe g);//Verifier que le graphe ne contient pas de valeur négatif
	
	public void Djikstra(IGraphe g, int SommetInitiale);//Algorithme de Djikstra
	
	public int Liste(int SommetInitiale, int SommetFinale, ArrayList<Integer> noeuds);//Réalisation du chemin entre la source et destination et de la valeur entre les 2 sommets
		
}
