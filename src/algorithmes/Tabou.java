package algorithmes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import graphe.Graphe;
import graphe.ObjetTabou;
import graphe.Sommet;
import main.InvalidArgumentException;

public class Tabou extends Algo{
	public Tabou(ArrayList<Sommet> class1opt, ArrayList<Sommet> class2opt, int solOpt) {
		super(class1opt, class2opt, solOpt);
	}
	
	public Tabou(){
		super();
	}
	
	public void setTabou(Graphe G, Sommet s, Tabou t) throws InvalidArgumentException{
		t = tabou(G, s);
	}

	public Tabou tabou(Graphe G, Sommet start) throws InvalidArgumentException{
		if(!G.getSommets().contains(start))
			throw new InvalidArgumentException("Sommet absent du graphe");
		int solOpt = init(G); // Voisinage de la sol courante = 1 swap de sommets
		int step = 0;
		Queue<ObjetTabou> tabou = new LinkedList<ObjetTabou>();
		int solCheck = 0;
		do{
			ArrayList<Sommet> class1optloc = new ArrayList<Sommet>();
			ArrayList<Sommet> class2optloc = new ArrayList<Sommet>();
			solCheck = solOpt;
			ArrayList<Sommet> voisins = start.getVoisins();
			voisins.add(0, start);
			Iterator<Sommet> it = voisins.iterator();
			int solOptLoc = Integer.MAX_VALUE; // solOptLoc est la meilleure valeur de calculSol() pour le voisinage considéré.
			while(it.hasNext() && step < MAX_STEP){
				step++;
				Sommet s = it.next();
				generateSolVoisine(G, s);
				ObjetTabou ot = new ObjetTabou(class1, class2);
				if(!(tabou.contains(ot))){
					tabou.add(ot);
					int solAct = calculSol();
					if(solAct < solOptLoc){
						solOptLoc = solAct;
						class1optloc = class1;
						class2optloc = class2;
					}
				}
			}
			/* Si solOptLoc a été modifiée, alors soit le voisinage contient une meilleure
			 * sol que solOpt, soit solOpt est meilleure. Dans les deux cas, on doit changer
			 * solOpt d'après Tabou (sortie d'un minimum local) */
			if(solOptLoc != Integer.MAX_VALUE){  
				solOpt = solOptLoc;				 
				class1opt = class1optloc;        
				class2opt = class2optloc;
			}
			start = voisins.get(1);
		}
		while(step < MAX_STEP && solCheck != solOpt);
		return new Tabou(class1opt, class2opt, solOpt);
	}
}
