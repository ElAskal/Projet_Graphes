package algorithmes;

import java.util.ArrayList;
import java.util.Iterator;

public class RS extends Algo{
	private static final double lambda = 0.99;
	/*  s := s0 // état de nos classes
		e := E(s) // E = calculSol, e = solOpt
		k := 0 // step
		tant que k < kmax et e > emax
  			sn := voisin(s)
  			en := E(sn)
  			si en < e ou aléatoire() < P(en - e, temp(k/kmax)) alors
    			s := sn; e := en
  			k := k + 1
		retourne s */
	
	public int recuit(Graphe G, Sommet start) throws InvalidArgumentException{
		if(!class1.contains(start) && !class2.contains(start))
			throw new InvalidArgumentException("Sommet absent du graphe");
		int solOpt = init(G); // Voisinage de la sol courante = 1 swap de sommets
		int step = 0;
		int solCheck = 0;
		int temp = init_temp(solOpt, G, start);
		do{
			solCheck = solOpt;
			ArrayList<Sommet> voisins = start.getVoisins();
			voisins.add(0, start);
			Iterator<Sommet> it = voisins.iterator();
			while(it.hasNext() && step < MAX_STEP){
				step++;
				Sommet s = it.next();
				generateSolVoisine(G, s);
				int solAct = calculSol();
				if((solAct < solOpt) || (Math.random() <= Math.exp(-((solAct - solOpt) / temp)))){
					solOpt = solAct;
					class1opt = class1;
					class2opt = class2;
				}
			}
			start = voisins.get(1);
			temp *= lambda;
		}
		while(step < MAX_STEP && solCheck != solOpt);
		return solOpt;
		
	}
	
	public int init_temp(int solOpt, Graphe G, Sommet start){
		class1opt = class1; // Save init of class
		class2opt = class2;
		int temp = 0;
		int moyenne = 0;
		ArrayList<Integer> results = new ArrayList<Integer>();
		ArrayList<Sommet> voisins = start.getVoisins();
		int size_v = voisins.size();
		Iterator<Sommet> it = voisins.iterator();
		while(it.hasNext()){
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
			int solAct = calculSol();
			results.add(solAct);
			moyenne+= solAct;
		}
		moyenne /= size_v; // Proba = DeltaSol / T, on veut une température qui accepte 50% des solutions au début.
		temp = (solOpt - moyenne) * 2;
		class1 = class1opt;
		class2 = class2opt;
		return temp;
	}
	
}
