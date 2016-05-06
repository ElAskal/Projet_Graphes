package algorithmes;

import java.util.ArrayList;
import java.util.Iterator;

import graphe.Graphe;
import graphe.Sommet;
import main.InvalidArgumentException;

public class RS extends Algo{
	public RS(ArrayList<Sommet> class1opt, ArrayList<Sommet> class2opt, int solOpt) {
		super(class1opt, class2opt, solOpt);
	}
	
	public RS(){
		super();
	}
	
	public void setRS(Graphe G, RS r) throws InvalidArgumentException{
		r = recuit(G);
	}

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
	
	@SuppressWarnings("unchecked")
	public RS recuit(Graphe G) throws InvalidArgumentException{
		solOpt = init(G); // Voisinage de la sol courante = 1 swap de sommets
		int step = 0;
		int solCheck = 0;
		int temp = init_temp(G);
		do{
			solCheck = solOpt;
			for(int i = 0; i < class1.size(); i++){
				for(int j = 0; j < class1.size(); j++){
					step++;
					class1.add(class1.size(), class2.remove(i));
					class2.add(class2.size(), class1.remove(j));
					int solAct = calculSol();
					if((solAct < solOpt) || (Math.random() <= Math.exp(-((solAct - solOpt) / temp)))){
						solOpt = solAct;
						setClass1opt((ArrayList<Sommet>) class1.clone());
						setClass2opt((ArrayList<Sommet>) class2.clone());
					}
					if(step >= MAX_STEP)
						return new RS(class1opt, class2opt, solOpt);
				}
			}
			temp *= lambda;
			if(temp == 0)
				temp = init_temp(G);
		}
		while(step < MAX_STEP && solCheck != solOpt);
		return new RS(class1opt, class2opt, solOpt);
		
	}
	
	@SuppressWarnings("unchecked")
	public int init_temp(Graphe G) throws InvalidArgumentException{
		if(G.getArêtes().size() == 0)
			throw new InvalidArgumentException("Graphe sans arêtes");
		class1opt = (ArrayList<Sommet>) class1.clone(); // Save init of class
		class2opt = (ArrayList<Sommet>) class2.clone();
		int temp = 0;
		int moyenne = 0;
		int index = 0;
		ArrayList<Sommet> voisins = G.getSommets().get(index).getVoisins();
		while(voisins.size() == 0){
			index++;
			voisins = G.getSommets().get(index).getVoisins();
		}
		int size_v = voisins.size();
		Iterator<Sommet> it = voisins.iterator();
		while(it.hasNext()){
			it.next();
			int pick = (int) (Math.random() * (Math.min(class1.size(), class2.size())));
			class1.add(class1.size(), class2.remove(pick));
			class2.add(class2.size(), class1.remove(0));
			int solAct = calculSol();
			moyenne+= solAct;
		}
		moyenne /= size_v; // Proba = DeltaSol / T, on veut une température qui accepte 50% des solutions au début.
		temp = (moyenne - solOpt) * 2;
		class1 = (ArrayList<Sommet>) class1opt.clone();
		class2 = (ArrayList<Sommet>) class2opt.clone();
		return Math.abs(temp) - 1;
	}
	
}
