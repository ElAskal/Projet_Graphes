package algorithmes;

import java.util.ArrayList;
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
	
	public long setDescente(Graphe G, Descente d, boolean meta) throws InvalidArgumentException{
		long chrono1 = java.lang.System.currentTimeMillis() ; 
		d = descente(G, meta);
		long chrono2 = java.lang.System.currentTimeMillis() ; 
		return (chrono2 - chrono1);
	}

	@SuppressWarnings("unchecked")
	public Descente descente(Graphe G, boolean meta) throws InvalidArgumentException{
		solOpt = init(G); // Voisinage de la sol courante = 1 swap de sommets
		if(!meta) // On peut vouloir arrêter la descente pour comparer les résultats avec les autres algos
			MAX_STEP = Integer.MAX_VALUE;
		int solCheck = solOpt;
		int step = 0;
		do{
			solCheck = solOpt;
			for(int i = 0; i < class1.size(); i++){
				for(int j = 0; j < class1.size(); j++){
					step++;
					class1.add(class1.size(), class2.remove(i));
					class2.add(class2.size(), class1.remove(j));
					int solAct = calculSol();
					if (solAct < solOpt){
						solOpt = solAct;
						setClass1opt((ArrayList<Sommet>) class1.clone());
						setClass2opt((ArrayList<Sommet>) class2.clone());
					}
					if(step >= MAX_STEP)
						return new Descente(class1opt, class2opt, solOpt);
				}
			}
		}
		while(solCheck != solOpt && step < MAX_STEP);
		return new Descente(class1opt, class2opt, solOpt);
	}
}
