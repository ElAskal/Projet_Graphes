package algorithmes;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
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
	
	public void setBFS(Graphe G, BFS b) throws InvalidArgumentException, IOException{
		b = bfs(G);
	}
	
	public boolean estParfaitementEquilibre()
	{
		int i = class1.size() - class2.size();
		return (i >= -1) && (i <= 1);
	}

	@SuppressWarnings("unchecked")
	public BFS bfs(Graphe G) throws InvalidArgumentException, IOException{
		clean();
		Queue<Sommet> q = new LinkedList<Sommet>();
		Stack<Sommet> stack = new Stack<Sommet>();
		LinkedHashMap<Integer, Boolean> estDansClass1 = new LinkedHashMap<Integer, Boolean>();
		Iterator<Sommet> it = G.getSommets().iterator();
		while(it.hasNext())
			estDansClass1.put(it.next().getLabel(), false);
		Iterator<Sommet> parcours = G.getSommets().iterator();
		while (parcours.hasNext())
		{
			Sommet s = parcours.next();
		q.add(s);
		stack.add(s);
		while(!(stack.isEmpty()) || !(q.isEmpty())){
			Sommet w ;
			if (!q.isEmpty())
			{
				w = q.remove();
				if (!stack.isEmpty() && stack.peek() != w)
				{
					stack.add(w);
				}
			}
			else
			{
				w = stack.pop();
			}
				if(class1.contains(w) || class2.contains(w))
				{
					System.out.println("Ping");
					if(estParfaitementEquilibre() && (class1.size() + class2.size() == G.getSommets().size()) && (calculSol() < solOpt))
					{
						setClass1opt((ArrayList<Sommet>) class1.clone());
						setClass2opt((ArrayList<Sommet>) class2.clone());
						solOpt = calculSol();
					}
					if (class1.contains(w))
					{
						class1.remove(w);
						q.add(w);
					}
					else
					{
						System.out.println("Pong");
						class2.remove(w);
					}
				}			
				else 
				{
					if (estDansClass1.get(w.getLabel()) == false)
					{
						class1.add(w);
						estDansClass1.put(w.getLabel(), true);
					}
					else
					{
						class2.add(w);
						estDansClass1.put(w.getLabel(), false);
					}
					Iterator<Sommet> it2 = w.getVoisins().iterator();
					while(it2.hasNext())
					{
						Sommet x = it2.next();
						if (!class1.contains(x) && !class2.contains(x) && !q.contains(x))
						{
							x.setParent(w);
							q.add(x);
						}
					}
					System.out.println(calculSol());
				}
				System.out.println(class1.toString());
				System.out.println(class2.toString());			
				System.out.println(class1opt.toString());
				System.out.println(class2opt.toString());
				System.out.println(q.toString());
				System.out.println(stack.toString());
			System.out.println(solOpt);
			System.out.println("\n _____\n");
		}
		}
		return new BFS(class1opt, class2opt, solOpt);
	}
	
	public String toString()
	{
		return class1opt.toString() + " \n" + class2opt.toString() + "\n" + solOpt;
	}
}


