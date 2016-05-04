package graphe;

import java.util.ArrayList;

public class Sommet {
	private int poids;
	private ArrayList<Sommet> voisins;
	private int label;
	private Sommet parent;
	
	public Sommet(int poids, ArrayList<Sommet> voisins, int label, Sommet parent){
		this.poids = poids;
		this.voisins = voisins;
		this.label = label;
		this.parent = parent;
	}
	
	public Sommet (int label)
	{
		this.label = label;
		this.poids = 0;
		this.voisins = new ArrayList<Sommet>();
		this.parent = null;
	}

	public Sommet getParent() {
		return parent;
	}

	public void setParent(Sommet parent) {
		this.parent = parent;
	}

	public int getPoids() {
		return poids;
	}

	public void setPoids(int poids) {
		this.poids = poids;
	}

	public ArrayList<Sommet> getVoisins() {
		return voisins;
	}

	public void setVoisins(ArrayList<Sommet> voisins) {
		this.voisins = voisins;
	}

	public int getLabel() {
		return label;
	}

	public void setLabel(int label) {
		this.label = label;
	}
	public String toString()
	{
		String s = new String();
		s += this.getLabel(); 
		return s;
	}
}
