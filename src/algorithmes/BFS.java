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
}
