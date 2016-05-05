package algorithmes;

import java.util.ArrayList;
import java.util.Iterator;

import graphe.Graphe;
import graphe.Sommet;
import main.InvalidArgumentException;

public class Algo {
	protected static int MAX_STEP = 1000; // Peut être adapté en fonction du graphe avec setMaxStep() 
	private static final double SAME_APPROX = 0.7;
	ArrayList<Sommet> class1 = new ArrayList<Sommet>();
	ArrayList<Sommet> class2 = new ArrayList<Sommet>();
	ArrayList<Sommet> class1opt = new ArrayList<Sommet>();
	ArrayList<Sommet> class2opt = new ArrayList<Sommet>();
	
	public int init(Graphe G) throws InvalidArgumentException{
		if(G.getSommets().size() < 2)
			throw new InvalidArgumentException("Graphe vide ou à un seul sommet");
		setMaxStep(G);
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
	
	public boolean estEquilibre(Graphe G) throws InvalidArgumentException{
		if(G.getSommets().size() < 2)
			throw new InvalidArgumentException("Graphe vide ou à un seul sommet");
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
	
	public void generateSolVoisine(Graphe G, Sommet s) throws InvalidArgumentException{
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
	}
	
	public void setMaxStep(Graphe G){
		MAX_STEP = (int) Math.pow(G.getSommets().size(), 3);
	}

}
