package it.polito.tdp.PremierLeague.model;

import java.util.HashMap;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.PremierLeague.db.PremierLeagueDAO;

public class Model {

	private Graph<Player,DefaultWeightedEdge> grafo;
	private Map<Integer,Player> idMap;
	private PremierLeagueDAO dao;
	
	public Model() {
		dao = new PremierLeagueDAO();
	}
	
	public void creaGrafo(double x) {
		grafo = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		idMap = new HashMap<>();
		
		dao.setGiocatori(idMap, x);
		Graphs.addAllVertices(grafo, idMap.values());
		
		
	}
}
