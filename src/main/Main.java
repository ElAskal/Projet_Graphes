package main;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import graphe.Arête;
import graphe.Graphe;
import graphe.Sommet;
import algorithmes.BFS;
import algorithmes.Descente;

public class Main {
	
	public static Graphe parse(String path) throws IOException{
		File file = new File(path);
		FileReader fr = new FileReader(file);
		ArrayList<Sommet> sommets = new ArrayList<Sommet>();
		ArrayList<Arête> arêtes = new ArrayList<Arête>();
		int step = 0;		
		int intraStep = 0;
		int poidsTotal = 0;
		Sommet sommet1 = null;
		Sommet sommet2 = null;
		int i=0;
		char c;
		String s = "";
		i=fr.read(); // on lit le caractère et on note son code dans i
		while( i != -1 ){
			c=(char) i;
			switch (step){
				case 0:
					if (s.equals("# nbSommets nbAretes"))
					{
						step++;
						s = "";
					}
					else
					{
						s += c;
					}
					break;
				case 1:
					if (c == ' ')
					{
						if (!s.isEmpty())
						{
							
							int sommet = Integer.parseInt(s);
							for (int x = 1 ; x <= sommet ; x++)
							{
								sommets.add(new Sommet(x));
							}
							step++;						
							s = "";
						}					
					}
					else
					{
						s += c;
					}
					break;
				case 2:
					if (s.endsWith("\n"))
					{
						s = s.substring(0, s.lastIndexOf("\n"));
						poidsTotal = Integer.parseInt(s);
						s = "";
						step++;
					}
					else
					{
						s += c;
					}
					break;
				case 3:
					if (s.endsWith("val"))
					{
						s = "";
						step++;
					}
					else
					{
						s += c;
					}
					break;
				case 4:
					if (c == ' ')
					{				
						if (!s.isEmpty() && intraStep == 0)
						{
							int tmp = Integer.parseInt(s);
							sommet1 = sommets.get(tmp - 1);
							intraStep++;						
							s = "";								
						}
						if (!s.isEmpty() && intraStep == 1)
						{
							int tmp = Integer.parseInt(s);
							sommet2 = sommets.get(tmp - 1);
							sommet1.getVoisins().add(sommet2);
							sommet2.getVoisins().add(sommet1);
							sommet1.setPoids(sommet1.getPoids() + 1);
							sommet2.setPoids(sommet2.getPoids() + 1);
							sommets.set(sommet1.getLabel() - 1, sommet1);
							sommets.set(sommet2.getLabel() - 1, sommet2);
							Arête a = new Arête(sommet1, sommet2);
							arêtes.add(a);
							intraStep++;
						}
					}
					else if (s.endsWith("\n") && c != '#')
					{
						intraStep = 0;
						s = "" + c;
					}
					else if (c == '#')
					{
						step++;
						s = "";
					}
					else
					{
						s += c;
					}
					break;
				default:
					break;
			}
			assert (step == 5);
			i=fr.read(); 
		}
		Graphe g = new Graphe(sommets, arêtes, poidsTotal);
		fr.close();
		return g;
	}
	
	public static void main(String[] args) throws IOException, InvalidArgumentException{
		String path = "Graphes/quatreSommets.txt";
		Graphe g = parse(path);
		Descente d = new Descente();
		d.setDescente(g, d, false);
		System.out.println(d.toString());
	}
}
