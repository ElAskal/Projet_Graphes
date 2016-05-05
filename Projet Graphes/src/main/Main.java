package main;

import java.io.File;
import java.io.IOException;


import algorithmes.Graphe;
import algorithmes.Algo;
import algorithmes.BFS;

public class Main {
	
	public static void main(String[] args) throws IOException
	{
		String path = "../../Graphes/quatreSommets.txt";
		graphe.Graphe g = Parser.parse(path);
		BFS.bfs(g, g.getSommets().get(0));
	}
}
