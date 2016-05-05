package graphe;

import java.util.ArrayList;

public class ObjetTabou {
	private ArrayList<Sommet> classe1;
	private ArrayList<Sommet> classe2;
	
	public ObjetTabou(ArrayList<Sommet> classe1, ArrayList<Sommet> classe2){
		this.classe1 = classe1;
		this.classe2 = classe2;
	}

	public ArrayList<Sommet> getClasse1() {
		return classe1;
	}

	public void setClasse1(ArrayList<Sommet> classe1) {
		this.classe1 = classe1;
	}

	public ArrayList<Sommet> getClasse2() {
		return classe2;
	}

	public void setClasse2(ArrayList<Sommet> classe2) {
		this.classe2 = classe2;
	}
	
	public void setClasses(ArrayList<Sommet> classe1, ArrayList<Sommet> classe2){
		this.classe1 = classe1;
		this.classe2 = classe2;
	}

}
