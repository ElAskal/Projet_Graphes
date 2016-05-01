package algorithmes;

import java.util.ArrayList;
import java.util.Iterator;

public class Descente {
	private static final int MAX_STEP = 1000;
	private static final double SAME_APPROX = 1.2;
	ArrayList<Integer> class1 = new ArrayList<Integer>();
	ArrayList<Integer> class2 = new ArrayList<Integer>();
	
	public void descente_init(Graphe G) throws InvalidArgumentException{
		if(G.getSommets.size() < 2)
			throw new InvalidArgumentException("Graphe à un seul sommet");
		Iterator<Integer> it = G.getSommets.iterator();
		class1.add(it.next()); // Problème de division par 0
		class2.add(it.next());
		while(it.hasNext()){
			if(class1.size() == class2.size() || Math.max(class1.size(), class2.size()) / Math.min(class1.size(), class2.size()) <= SAME_APPROX)
				class1.add(it.next());
			else
				class2.add(it.next());
		}	
	}
	
	public void descente(Graphe G, int start){
		descente_init(G);
		
	}
}
