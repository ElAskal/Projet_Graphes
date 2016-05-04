package graphe;

import java.util.ArrayList;

public class Graphe {
	private ArrayList<Sommet> sommets;
	private ArrayList<Arête> arêtes;
	private int poidsTotal;
	
	public Graphe(ArrayList<Sommet> sommets, ArrayList<Arête> arêtes, int poidsTotal) {
		this.sommets = sommets;
		this.arêtes = arêtes;
		this.poidsTotal = poidsTotal;
	}

	public ArrayList<Sommet> getSommets() {
		return sommets;
	}

	public void setSommets(ArrayList<Sommet> sommets) {
		this.sommets = sommets;
	}

	public ArrayList<Arête> getArêtes() {
		return arêtes;
	}

	public void setArêtes(ArrayList<Arête> arêtes) {
		this.arêtes = arêtes;
	}

	public int getPoidsTotal() {
		return poidsTotal;
	}

	public void setPoidsTotal(int poidsTotal) {
		this.poidsTotal = poidsTotal;
	}
	
	
}
