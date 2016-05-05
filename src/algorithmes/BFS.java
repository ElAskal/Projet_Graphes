package algorithmes;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import graphe.Graphe;
import graphe.Sommet;
import main.InvalidArgumentException;

public class BFS extends Algo{
	public BFS(ArrayList<Sommet> class1opt, ArrayList<Sommet> class2opt, int solOpt) {
		super(class1opt, class2opt, solOpt);
	}
	
	public BFS(){
		super();
	}
	
	public void setBFS(Graphe G, Sommet s, BFS b) throws InvalidArgumentException{
		b = bfs(G, s);
	}

	private boolean exploré[]; // Vrai s'il y a un chemin entre le sommet source et le sommet considéré.

	public BFS bfs(Graphe G, Sommet s) throws InvalidArgumentException{
		clean();
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
			if (calculSol() < solOpt)
			{
				if(class1.contains(w) || class2.contains(w))
				{
					if(estEquilibre(G) && class1.size() + class2.size() == G.getSommets().size() )
					{
						class1opt = class1;
						class2opt = class2;
						solOpt = calculSol();
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
		return new BFS(class1opt, class2opt, solOpt);
	}
}


