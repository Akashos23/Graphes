package graphes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PCCBellman implements IBCC{
	private boolean[][] TabNiveau;//Tableau boolean à 2 dimensions contenant les niveaux
	private int [][] sommetDetenu;//Tableau à 2 dimensions contenant les distance entre 2 sommet
	private int [][] sommetPrecedent;//Tableau à 2 dimensions contenant les sommet Précedent
	private Map <Integer, Integer> niveau;//Map contenant pour chaques sommets son niveau
	private Map <Integer, Integer> labels;//Map contenant tous les sommets
	
public PCCBellman(int label) {
		int sommet = 0;
		this.labels = new HashMap<>();
		this.niveau = new HashMap<>();
		this.sommetDetenu = new int[label][label];
		this.sommetPrecedent = new int[label][label];
		this.TabNiveau = new boolean[label][label];
		for(int i = 0; i < label; ++i) {
			this.labels.put(i, i+1);//Ajoutez les sommets
		}
		for(int i =0; i < label; ++i) {
			this.sommetDetenu[sommet][i] = 10000;//Initialiser tous les valeur du tableau à 10000
			this.sommetPrecedent[sommet][i] = 10000;//Initialiser tous les valeur du tableau à 10000
			if(i == this.sommetDetenu.length - 1 && sommet < this.sommetDetenu.length-1) {
				sommet += 1;
				i = -1;
			}
		}
		
	}
public void NiveauBellman(IGraphe g) {
		int sommet = 0;
		int niv = 0;
		int sommet2 =0;
		int compteur = 0;
		for(int i = 0; i < this.labels.size(); ++i) {
			if(g.aArc(this.labels.get(sommet), this.labels.get(i)) == true) {//Si deux sommet son connécté alors
				this.TabNiveau[sommet][i] = true;//Ajoutez true a ces deux sommets
			}
			if(sommet < this.labels.size()-1 && i == this.labels.size()-1) {
				sommet += 1;
				i = -1;
			}
			
		}
		sommet = 0;
		for(int i = 0; i < this.labels.size(); ++i) {
			if(this.TabNiveau[i][sommet] == false) {
				compteur += 1;
			}
			if(compteur == this.labels.size() && this.niveau.get(sommet) == null) {
				this.niveau.put(sommet, niv);//Ajouter le sommet et son niveau à la map
			}
			if(sommet < this.labels.size() - 1 && i == this.labels.size()-1) {
				sommet += 1;
				i = -1;
				compteur = 0;
			}
			if(sommet == this.labels.size()-1 && i == this.labels.size()-1) {
				sommet2 = 0;
				for(int  v = 0; v < this.labels.size(); ++v) {
					if(this.niveau.get(sommet2) != null) {
						this.TabNiveau[sommet2][v] = false;
					}
					if(sommet2 < this.labels.size()-1 && v == this.labels.size()-1) {
						sommet2 += 1;
						v = -1;
					}
					if(sommet2 == this.labels.size()-1 && v == this.labels.size()-1 && this.niveau.size() < this.labels.size()) {
						i = -1;
						sommet = 0;
						compteur = 0;
						niv += 1;
					}
				}
			}
		}
		
	}

public void bellman(IGraphe g, int dep) {
	   dep = dep - 1;
       int sommetImportant = dep;
       int valeur = 0;
       int sommetPrecedent = dep;
       int niv = 1;
       int sommet = 0;
       int n = 0;
       int compteur = 0;
       ArrayList<Integer> interdit = new ArrayList<>();
       for(int i = 0; i < this.labels.size(); ++i) {
    	   if(g.aArc(this.labels.get(sommetImportant), this.labels.get(i)) == true) {
    		   this.sommetDetenu[sommetImportant][i] = g.getValuation(this.labels.get(sommetImportant), this.labels.get(i)) + valeur;//Ajoutez la valeur au tableau pour le sommet donné
    		   this.sommetPrecedent[sommetImportant][i] = sommetImportant;//Ajoutez le sommet précedent au tableaupour le sommet donné
    	   }
    	   if(this.sommetDetenu[sommetPrecedent][i] < this.sommetDetenu[sommetImportant][i]) {//Si la valeur précedent est plus petite alors
   				this.sommetDetenu[sommetImportant][i] = this.sommetDetenu[sommetPrecedent][i];//Remettre la valeur précedente au tableau pour le sommet donné
   				this.sommetPrecedent[sommetImportant][i] = this.sommetPrecedent[sommetPrecedent][i];//Remettre le sommet précedent au tableau pour le sommet donné
   			}
    	   if(i == this.labels.size()-1 && n < this.labels.size()) {
    		   for(int v =0; v < this.niveau.size(); ++v) {
    			   if(this.niveau.get(v) == niv && compteur < 1 && interdit.contains(v) == false) {
    				   sommet = v;//Donner le nouveau sommet 
    				   valeur = this.sommetDetenu[sommetImportant][v];//la variable valeur prend la valeur du sommetPrecedent
    				   compteur += 1;//Mettre le compteur à un 
    			   }
    			   if(compteur < 1 && v == this.niveau.size()-1) {
    				   v = -1;
    				   niv += 1;
    			   }
    			   if(v == this.niveau.size()-1 && n < this.labels.size()) {
    				    i = -1;//i prend la valeur -1 
    				    sommetPrecedent = sommetImportant;//SommetPrecedent prend la valeur du sommetImportant
    				    sommetImportant = sommet;//le sommetImportant prend la nouvelle valeur du sommet
    				    interdit.add(sommetImportant);//Ajouter a l'arraylist la valeur SommetImportant
    				    compteur = 0;//Remettre le compteur à 0
    				    n += 1;//Ajouter un à n
    			   }
    		   }
    	   }
       }
	}
public int chemin(int initiale, int Destination, ArrayList<Integer> chemins) {
		int sommet1 = 0;
		int sommet2 = 0;
		int valeur = 0;
		for(int i = 0; i < this.labels.size(); ++i) {
			if(this.labels.get(i) == initiale) {
				sommet1 = i;//sommet1 prend la valeur du parametre initiale-1
			}
			if(this.labels.get(i) == Destination) {
				sommet2 = i;//sommet2 prend la valeur du parametre Destination-1
			}
		}
		chemins.add(sommet2+1);//On ajoute a l'arraylist le sommet2 + 1
		valeur = this.sommetDetenu[this.sommetDetenu.length-1][Destination-1];//On définit la valeur entre le sommet1 et sommet2 grace au tableau sommetDetenu
		if(sommet2 == sommet1) {
			valeur = 0;
		}
		while(sommet2 != sommet1 && valeur != 10000) {//Tant que sommet1 est different de sommet2 et la valeur est différent de 100 alors
			chemins.add(this.sommetPrecedent[this.sommetPrecedent.length-1][sommet2]+1);//ajoutez le sommet Précedent à l'arraylist chemin
			sommet2 = this.sommetPrecedent[this.sommetPrecedent.length-1][sommet2];//Sommet2 récupere la valeur du sommet Précedent
		}
		return valeur;//Retourner la valeur
}
}
