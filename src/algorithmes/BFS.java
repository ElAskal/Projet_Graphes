package algorithmes;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import graphe.Graphe;
import graphe.Sommet;

public class BFS extends Algo{
	private static final int infinity = Integer.MAX_VALUE;
	private int distance[]; // Nombre d'arêtes du plus court chemin entre le sommet source et le sommet considéré.
	private boolean exploré[]; // Vrai s'il y a un chemin entre le sommet source et le sommet considéré.
	private int bestSol;

	public void bfs(Graphe G, Sommet s){
		Queue<Sommet> q = new LinkedList<Sommet>();
		Stack<Sommet> stack = new Stack<Sommet>();
		exploré[s.getLabel() - 1] = true;
		q.add(s);
		stack.add(s);
		while(!(q.isEmpty()) && !(stack.isEmpty())){
			Sommet w ;
			if (!q.isEmpty())
			{
				w = q.remove();
				stack.add(w);
			}
			else
			{
				w = stack.pop();
				q.add(w);
			}
			if (exploré[w.getLabel() - 1] == false)
			{
				class1.add(w);
				exploré[w.getLabel() - 1] = true;
			}
			else
			{
				class2.add(w);
				exploré[w.getLabel()- 1] = false;
			}
			if (calculSol() < bestSol)
			{
				if(class1.contains(w) || class2.contains(w))
				{
					if(estEquilibre(G) && class1.size() + class2.size() == G.getSommets().size() )
					{
						class1opt = class1;
						class2opt = class2;
						bestSol = calculSol();
					}
					if (class1.contains(s))
						{
							class1.remove(w.getLabel() - 1);
						}
					else
						{
							class2.remove(w.getLabel() - 1);
						}
					}
				}
				else 
				{
					Iterator<Sommet> it2 = w.getVoisins().iterator();
					while(it2.hasNext())
					{
						Sommet x = it2.next();
						if (!class1.contains(x) && !class2.contains(x))
						{
							x.setParent(w);
							q.add(x);
						}
					}
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


