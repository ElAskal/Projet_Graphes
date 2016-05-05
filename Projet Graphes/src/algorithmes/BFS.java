package algorithmes;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import graphe.Graphe;
import graphe.Sommet;

public class BFS {
	private static final int infinity = Integer.MAX_VALUE;
	private int distance[]; // Nombre d'arêtes du plus court chemin entre le sommet source et le sommet considéré.
	private boolean exploré[]; // Vrai s'il y a un chemin entre le sommet source et le sommet considéré.

	public void bfs(Graphe G, Sommet s){
		Queue<Sommet> q = new LinkedList<Sommet>();
		Iterator<Sommet> it = G.getSommets().iterator();
		while(it.hasNext()){
			distance[it.next().getLabel()] = infinity;
		}
		distance[s.getLabel()] = 0;
		exploré[s.getLabel()] = true;
		q.add(s);
		while(!(q.isEmpty())){
			Sommet w = q.remove();
			Iterator<Sommet> it2 = w.getVoisins().iterator();
			while(it2.hasNext()){
				Sommet x = it2.next();
				if (!exploré[x.getLabel()]){
					exploré[x.getLabel()] = true;
					x.setParent(w);
					distance[x.getLabel()] = distance[w.getLabel()] + 1;
					q.add(x);
				}
			}
		}
	}
	
	/* Donc ça en fait c'est le code de BFS pour le voyageur de commerce.
	 * Notre problème à nous c'est de partitionner un graphe en deux classes "équitables",
	 * sachant que la somme des poids des sommets n'étant pas dans la même classe (notée sp)
	 * doit être minimale. Pour cela, on a déjà plusieurs fonctions (cf Algo.java) :
	 *     - init nous génère deux classes équitables.
	 *     - calculSol calcule sp en fonction des classes
	 *     - generateSolVoisine génère une solution voisine à celle actuelle (swap de nos classes, inutile ici)
	 * Ton but est de parcourir tout le graphe (méthode exacte) et d'énumérer TOUTES les solutions possibles (voir sujet, print des arraylist class ?).
	 * Toute l'astuce étant que comme le poids des arêtes est toujours 1, on peut tenter de travailler directement 
	 * avec les arêtes plutôt que les listes de voisins (les deux sont possibles, toi qui vois ce que tu préfères).
	 * Wikipédia : Parcours en largeur + code en haut + harcèlement sur Skype pour t'aider.
	 */
}
