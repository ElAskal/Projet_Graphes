package algorithmes;

import java.util.ArrayList;
import java.util.Iterator;

import graphe.Graphe;
import graphe.Sommet;
import main.InvalidArgumentException;

public class Descente extends Algo{
	
	public Descente(ArrayList<Sommet> class1opt,
			ArrayList<Sommet> class2opt, int solOpt) {
		super(class1opt, class2opt, solOpt);
	}
	
	public Descente(){
		super();
	}
	
	public void setDescente(Graphe G, Sommet s, Descente d, boolean meta) throws InvalidArgumentException{
		d = descente(G, s, meta);
	}

	public Descente descente(Graphe G, Sommet start, boolean meta) throws InvalidArgumentException{
		if(!G.getSommets().contains(start))
			throw new InvalidArgumentException("Sommet absent du graphe");
		solOpt = init(G); // Voisinage de la sol courante = 1 swap de sommets
		if(!meta) // On peut vouloir arrêter la descente pour comparer les résultats avec les autres algos
			MAX_STEP = Integer.MAX_VALUE;
		int solCheck = solOpt;
		int step = 0;
		do{
			solCheck = solOpt;
			ArrayList<Sommet> voisins = start.getVoisins();
			voisins.add(0, start);
			Iterator<Sommet> it = voisins.iterator();
			while(it.hasNext() && step < MAX_STEP){ // Descente n'est pas une métaheuristique, MAX_STEP à virer ?
				step++;
				Sommet s = it.next();
				generateSolVoisine(G, s);
				int solAct = calculSol();
				if (solAct < solOpt){
					solOpt = solAct;
					class1opt = class1;
					class2opt = class2;
				}
			}
			start = voisins.get(1);
		}
		while(solCheck != solOpt && step < MAX_STEP);
		return new Descente(class1opt, class2opt, solOpt);
	}
	
}
