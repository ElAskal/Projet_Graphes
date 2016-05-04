package algorithmes;

import java.util.ArrayList;
import java.util.Iterator;

public class Descente extends Algo{
	
	public int descente(Graphe G, Sommet start) throws InvalidArgumentException{
		if(!class1.contains(start) && !class2.contains(start))
			throw new InvalidArgumentException("Sommet absent du graphe");
		int solOpt = init(G); // Voisinage de la sol courante = 1 swap de sommets
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
		return solOpt;	// TODO : Récup class1opt & class2opt
	}
	
}
