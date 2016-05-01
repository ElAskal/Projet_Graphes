package algorithmes;

import java.util.ArrayList;
import java.util.Iterator;

public class Descente {
	private static final int MAX_STEP = 1000;
	private static final double SAME_APPROX = 0.7;
	ArrayList<Sommet> class1 = new ArrayList<Sommet>();
	ArrayList<Sommet> class2 = new ArrayList<Sommet>();
	
	public int descente_init(Graphe G) throws InvalidArgumentException{
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
		if(class1.size() == class2.size() || Math.max(class1.size(), class2.size()) / G.getSommets().size() > SAME_APPROX)
			return true;
		return false;
	}
	
	public int descente(Graphe G, Sommet start) throws InvalidArgumentException{
		if(!class1.contains(start) && !class2.contains(start))
			throw new InvalidArgumentException("Sommet absent du graphe");
		int solOpt = descente_init(G); // Voisinage de la sol courante = 1 swap de sommets
		int solAct, solCheck = solOpt;
		int step = 0;
		do{
			solCheck = solOpt;
			ArrayList<Sommet> voisins = start.getVoisins();
			voisins.add(0, start);
			Iterator<Sommet> it = voisins.iterator();
			while(it.hasNext() && step < MAX_STEP){
				step++;
				Sommet s = it.next();
				if(class1.contains(s)){
					class1.remove(s);
					class2.add(s);
					if(!estEquilibre(G)){
						class1.add(class2.remove(0));
					}						
				}
				else{
					class2.remove(s);
					class1.add(s);
					if(!estEquilibre(G)){
						class2.add(class1.remove(0));
					}
				}
				solAct = calculSol();
				if (solAct < solOpt)
				solOpt = solAct;
			}
			start = voisins.get(1);
		}
		while(solCheck != solOpt && step < MAX_STEP);
		return solOpt;	
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
