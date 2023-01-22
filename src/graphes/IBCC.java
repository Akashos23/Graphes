package graphes;

import java.util.ArrayList;

public interface IBCC {
	public void NiveauBellman(IGraphe g);//Definir les niveaux du graphes
	
	public void bellman(IGraphe g, int sommetInitiale);//Alghorithme de bellman
	
	public int chemin(int sommetInitiale, int sommetFinale, ArrayList<Integer> chemins);//Realisation du chemin entre sommetInitiale et sommetFinale et retourne la valeur entre les deux sommets
	
	
}
