package algorithmes;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
	private static final int infinity = Integer.MAX_VALUE;
	private int distance[]; // Nombre d'arêtes du plus court chemin entre le sommet source et le sommet considéré.
	private int parent[]; // Sommet parent du sommet considéré
	private boolean exploré[]; // Vrai s'il y a un chemin entre le sommet source et le sommet considéré.

	public void bfs(Graphe G, int s){
		Queue<Integer> q = new LinkedList<Integer>();
		for(int v = 1; v < G.nbSommets; v++){
			distance[v] = infinity;
		}
		distance[s] = 0;
		exploré[s] = true;
		q.add(s);
		while(!(q.isEmpty())){
			int w = q.remove();
			Iterator<Integer> it = G.voisins(w).iterator();
			while(it.hasNext()){
				int x = it.next();
				if (!exploré[x]){
					exploré[x] = true;
					parent[x] = w;
					distance[x] = distance[w] + 1;
					q.add(x);
				}
			}
		}
	}
}
