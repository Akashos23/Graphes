package graphes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PCDDjikstra implements IPCC{
	private  Map <Integer, Integer> labels;//Map contenant tous les sommets
	private int[][] sommetDetenu;//Tableau à 2 dimensions contenant les distance entre 2 sommet
	private int[][] sommetPrecedent;//Tableau à 2 dimensions contenant les sommet Précedent
	private int val;//valeur
	
	public PCDDjikstra(int label) {
		int sommet = 0;
		this.labels = new HashMap<>();
		for(int i = 0; i < label; ++i) {
			this.labels.put(i, i+1);//Ajoutez les sommets à la map
		}
		this.sommetDetenu = new int[this.labels.size()][this.labels.size()];
		this.sommetPrecedent = new int[this.labels.size()][this.labels.size()];
		for(int i = 0; i < this.sommetDetenu.length; ++i) {
			this.sommetDetenu[sommet][i] = 10000;//Initialiser tous les valeur du tableau à 10000
			this.sommetPrecedent[sommet][i] = 10000;//Initialiser tous les valeur du tableau à 10000
			if(i == this.sommetDetenu.length - 1 && sommet < this.sommetDetenu.length-1) {
				sommet += 1;
				i = -1;
			}
		}
	}
	public boolean estOk(IGraphe g) {
		int sommet = 0;
		for(int i = 0; i < this.labels.size(); ++i) {
			if(g.aArc(this.labels.get(sommet), this.labels.get(i)) && g.getValuation(this.labels.get(sommet), this.labels.get(i)) < 0) {//Si un arc existe entre deux sommets et que sa valeur est inférieur à 0 alors lévé l'exception
				throw new IllegalArgumentException("l'arc entre le sommet " + (sommet+1) + " et " + (i+1) + " est négatif dont la valeur est de " + g.getValuation(this.labels.get(sommet), this.labels.get(i)));
			}
			if(i == this.labels.size() -1 && sommet < this.labels.size() - 1) {
				 sommet += 1;
				 i = -1;
			}
		}
		return true;
		
	}
	public void Djikstra(IGraphe g, int num) {
		num = num - 1;
		int sommet = num;
		int valeur = 0;
		int sommetImportant = num;
		int sommetPrecedent = num;
		int n = 0;
		int item = 0;
		ArrayList <Integer> interdit = new ArrayList<>();//Arraylist contenant les sommetImportant
		interdit.add(sommetImportant);
		for(int i = 0; i < this.labels.size(); ++i) {
			if(g.aArc(this.labels.get(sommetImportant), this.labels.get(i)) == true && interdit.contains(i) == false) {//Si un arc existe entre deux sommet et que ce sommet n'est pas contenu dans l'arraylist alors
				this.sommetDetenu[sommetImportant][i] = g.getValuation(this.labels.get(sommetImportant), this.labels.get(i)) + valeur;//le tableau récupere la valeur entre ces deux sommets
				this.sommetPrecedent[sommetImportant][i] = sommetImportant;//le tableau récupere la valeur du sommetImportant
			}
			if(this.sommetDetenu[sommetPrecedent][i] < this.sommetDetenu[sommetImportant][i] && i != sommetImportant) {//Si la valeur prècedente est plus pétite que la valeur actuelle alors
				this.sommetDetenu[sommetImportant][i] = this.sommetDetenu[sommetPrecedent][i];//le tableau récupere la valeur précedente entre ces deux sommets
				this.sommetPrecedent[sommetImportant][i] = this.sommetPrecedent[sommetPrecedent][i];//le tableau le sommet précedent entre ces deux sommets
			}
			
			if(i == 1) {
				n += 1;
			}
			if(i == this.labels.size()-1 && n < this.labels.size()) {
				i = -1;
				valeur = 10000;
				for(int m = 0; m < this.sommetDetenu.length; ++m) {
					if(this.sommetDetenu[sommetImportant][item] < this.sommetDetenu[sommetImportant][m]) {
						if(this.sommetDetenu[sommetImportant][item] < valeur) {//Récuperer le sommet avec la plus petite valeur
						sommet = item;//Sommet avec la plus petite valeur
						valeur = this.sommetDetenu[sommetImportant][item];//valeur de ce sommet
					}
					}
					if(m == this.sommetDetenu.length -1 && item < this.sommetDetenu.length-1) {
						item += 1;
						m = -1;
					}
					if(m == this.sommetDetenu.length -1 && item == this.sommetDetenu.length-1) {
						sommetPrecedent = sommetImportant;//le sommetPrecedent dévient le sommetImportant(sommet Actuelle)
						sommetImportant = sommet;//le sommetImportant récupere devient le sommet(le nouveau sommet)
						item = 0;//Remettre item à 0
						interdit.add(sommetImportant);//Ajoutez sommetImportant à l'arraylist
					}
				}
			} 
		}

	}
	public int Liste(int a, int b, ArrayList<Integer> noeuds) {
		int sommet1 = 0;
		int sommet2 = 0;
		int n = 0;
		for(int i = 0; i < this.labels.size(); ++i) {
			if(this.labels.get(i) == a) {
				sommet1 = i;//sommet1 prend la valeur du parametre initiale-1
			}
			if(this.labels.get(i) == b) {
				sommet2 = i;//sommet2 prend la valeur du parametre Destination-1
			}
		}
		noeuds.add(sommet2 + 1);//ajoutez à l'arraylist la destination
		for(int i = this.labels.size()-1; i > -1; --i) {
			if(this.sommetDetenu[i][sommet2] != 10000 && n < 1) {//Si la valeur est differente de 10000 et que la variable n est inférieur à 1 alors
				n += 1;//Mettre n à 1
				noeuds.add(this.sommetPrecedent[i][sommet2] + 1);//Ajoutez à la liste le nouveau sommet 
				sommet2 = this.sommetPrecedent[i][sommet2];//la variable sommet2 devient le nouveau sommet
			}
			if(n == 1 && i == 0 && sommet2 != sommet1) {
				i = this.labels.size()-1;
				n = 0;//Remettre la valeur 0 à n
			}
		}
		int sommet = noeuds.get(0)-1;
		this.val = 10100;
		for(int v = 0; v < this.labels.size(); ++v) {
			if(this.sommetDetenu[v][sommet] < this.val) {//si la valeur du tableau est inferieur à val
				this.val = this.sommetDetenu[v][sommet];//valeur récupére la nouvelle valeur 
			}
		}
		if(a == b) {
			this.val = 0;//si le premier sommet est égale au deuxième alors valeur est égale à 0
		}
		return this.val;//rétourner valeur
	}
}
