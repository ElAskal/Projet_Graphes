package algorithmes;

import java.util.ArrayList;
import java.util.Iterator;

public class Algo {
	protected static final int MAX_STEP = 1000; // Adapter MAX_STEP au nombre de sommets du graphe ? 
	private static final double SAME_APPROX = 0.7;
	ArrayList<Sommet> class1 = new ArrayList<Sommet>();
	ArrayList<Sommet> class2 = new ArrayList<Sommet>();
	ArrayList<Sommet> class1opt = new ArrayList<Sommet>();
	ArrayList<Sommet> class2opt = new ArrayList<Sommet>();
	
	public int init(Graphe G) throws InvalidArgumentException{
		if(G.getSommets().size() < 2)
			throw new InvalidArgumentException("Graphe à un seul sommet");
		Iterator<Sommet> it = G.getSommets().iterator();
		while(it.hasNext()){
			Sommet s = it.next();
			if(s.getLabel() % 2 == 1)
				class1.add(s);
			else
				class2.add(s);
		}
		return calculSol();
	}
	
	public boolean estEquilibre(Graphe G){
		if(class1.size() == class2.size() || Math.max(class1.size(), class2.size()) / G.getSommets().size() > Algo.SAME_APPROX)
			return true;
		return false;
	}
	
	public int calculSol(){
		int sol = 0;
		for(int i = 0; i < class1.size(); i++){
			Sommet s = class1.get(i);
			Iterator<Sommet> it = s.getVoisins().iterator();
			while(it.hasNext()){
				if(class2.contains(it.next()))
					sol++;
			}
		}
		return sol;
	}

}
