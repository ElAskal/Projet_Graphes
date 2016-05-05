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
	
	public void setDescente(Graphe G, Descente d, boolean meta) throws InvalidArgumentException{
		d = descente(G, meta);
	}

	public Descente descente(Graphe G, boolean meta) throws InvalidArgumentException{
		solOpt = init(G); // Voisinage de la sol courante = 1 swap de sommets
		if(!meta) // On peut vouloir arrêter la descente pour comparer les résultats avec les autres algos
			MAX_STEP = Integer.MAX_VALUE;
		int solCheck = solOpt;
		int step = 0;
		ArrayList<Sommet> class1save = class1;
		do{
			solCheck = solOpt;
			do{
				class1.add(class1.size(), class2.remove(0));
				class2.add(class2.size(), class1.remove(class1.size() - 2));
				int solAct = calculSol();
				if (solAct < solOpt){
					solOpt = solAct;
					setClass1opt(class1);
					setClass2opt(class2);
					System.out.println(class1opt.toString() + class2opt.toString());
				}
			}
			while(class1save != class1);
			System.out.println(class1opt.toString() + class2opt.toString());
		}
		while(solCheck != solOpt && step < MAX_STEP);
		return new Descente(class1opt, class2opt, solOpt);
	}
	
	public String toString(){
		return class1opt.toString() + class2opt.toString() + solOpt;
	}
	
}
