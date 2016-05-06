package algorithmes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

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
	
	public void setTabou(Graphe G, Tabou t) throws InvalidArgumentException{
		t = tabou(G);
	}

	@SuppressWarnings("unchecked")
	public Tabou tabou(Graphe G) throws InvalidArgumentException{
		solOpt = init(G); // Voisinage de la sol courante = 1 swap de sommets
		Random rand = new Random();
		class1.add(class1.size(), class2.remove(rand.nextInt(class2.size())));
		class2.add(class2.size(), class1.remove(rand.nextInt(class1.size())));
		int step = 0;
		Queue<ObjetTabou> tabou = new LinkedList<ObjetTabou>();
		int solCheck = 0;
		do{
			step++;
			ArrayList<Sommet> class1optloc = new ArrayList<Sommet>();
			ArrayList<Sommet> class2optloc = new ArrayList<Sommet>();
			solCheck = solOpt;
			int solOptLoc = Integer.MAX_VALUE; // solOptLoc est la meilleure valeur de calculSol() pour le voisinage considéré.
			for(int i = 0; i < class1.size(); i++){
				for(int j = 0; j < class1.size(); j++){
					step++;
					class1.add(class1.size(), class2.remove(i));
					class2.add(class2.size(), class1.remove(j));
					ObjetTabou ot = new ObjetTabou(class1, class2);
					if(!(tabou.contains(ot))){
						tabou.add(ot);
						int solAct = calculSol();
						if (solAct < solOptLoc){
							solOptLoc = solAct;
							class1optloc = ((ArrayList<Sommet>) class1.clone());
							class2optloc = ((ArrayList<Sommet>) class2.clone());
						}
						if(step >= MAX_STEP)
							return new Tabou(class1opt, class2opt, solOpt);
					}
				}
			}
			/* Si solOptLoc a été modifiée, alors soit le voisinage contient une meilleure
			 * sol que solOpt, soit solOpt est meilleure. Dans les deux cas, on doit changer
			 * solOpt d'après Tabou (sortie d'un minimum local) */
			if(solOptLoc != Integer.MAX_VALUE){
				solOpt = solOptLoc;
				setClass1opt((ArrayList<Sommet>) class1optloc.clone());
				setClass2opt((ArrayList<Sommet>) class2optloc.clone());
			}
		}
		while(step < MAX_STEP && solCheck != solOpt);
		return new Tabou(class1opt, class2opt, solOpt);
	}
}
