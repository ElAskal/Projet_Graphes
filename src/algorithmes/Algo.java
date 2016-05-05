package algorithmes;

import java.util.ArrayList;
import java.util.Iterator;

import graphe.Graphe;
import graphe.Sommet;
import main.InvalidArgumentException;

public class Algo {
	protected static int MAX_STEP = 1000; // Peut être adapté en fonction du graphe avec setMaxStep() 
	private static final double SAME_APPROX_PLUS = 0.6; // Notion d'à peu près équitable
	private static final double SAME_APPROX_MOINS = 0.4; // Notion d'à peu près équitable
	ArrayList<Sommet> class1 = new ArrayList<Sommet>();
	ArrayList<Sommet> class2 = new ArrayList<Sommet>();
	ArrayList<Sommet> class1opt = new ArrayList<Sommet>();
	ArrayList<Sommet> class2opt = new ArrayList<Sommet>();
	int solOpt = Integer.MAX_VALUE;
	
	public Algo(ArrayList<Sommet> class1opt,
			ArrayList<Sommet> class2opt, int solOpt) {
		super();
		this.class1opt = class1opt;
		this.class2opt = class2opt;
		this.solOpt = solOpt;
	}
	
	public Algo(){
		super();
	}

	public int init(Graphe G) throws InvalidArgumentException{
		if(G.getSommets().size() < 2)
			throw new InvalidArgumentException("Graphe vide ou à un seul sommet");
		clean();
		setMaxStep(G);
		Iterator<Sommet> it = G.getSommets().iterator();
		while(it.hasNext()){
			Sommet s = it.next();
			if(s.getLabel() <= G.getSommets().size() / 2)
				class1.add(s);
			else
				class2.add(s);
		}
		class1opt = class1;
		class2opt = class2;
		return calculSol();
	}
	
	public boolean estEquilibre(Graphe G) throws InvalidArgumentException{
		if(G.getSommets().size() < 2)
			throw new InvalidArgumentException("Graphe vide ou à un seul sommet");
		if(class1.size() == class2.size() 
				|| (Math.max(class1.size(), class2.size()) / G.getSommets().size() < Algo.SAME_APPROX_PLUS
				&& Math.min(class1.size(), class2.size()) / G.getSommets().size() > Algo.SAME_APPROX_MOINS))
			return true;
		return false;
	}
	
	public int calculSol(){
		int sol = 0;
		for(int i = 0; i < class1.size(); i++){
			Sommet s = class1.get(i);
			Iterator<Sommet> it = s.getVoisins().iterator();
			while(it.hasNext()){
				Sommet t = it.next();
				if(class2.contains(t)){
					sol++;
				}
			}
		}
		return sol;
	}
	
	public void setClass1opt(ArrayList<Sommet> class1opt) {
		this.class1opt = class1opt;
	}

	public void setClass2opt(ArrayList<Sommet> class2opt) {
		this.class2opt = class2opt;
	}

	public void setMaxStep(Graphe G){
		MAX_STEP = (int) Math.pow(G.getSommets().size(), 3);
	}
	
	public void clean(){
		class1.clear();
		class2.clear();
		class1opt.clear();
		class2opt.clear();
	}
	
	public String toString(){
		return class1opt.toString() + class2opt.toString() + solOpt;
	}
}
