package it.polito.tdp.PremierLeague.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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
		
		for(Adiacenti a : dao.getAdiacenti(idMap)) {
			Graphs.addEdge(grafo, a.getP1(), a.getP2(), a.getDelta());
		}
		
		System.out.println("#vertici: " + grafo.vertexSet().size());
		System.out.println("#archi: " + grafo.edgeSet().size());
	}
	
	public int getVertexSize() {
		return grafo.vertexSet().size();
	}
	
	public int getEdgeSize() {
		return grafo.edgeSet().size();
	}
	
	public Player getBestPlayer() {
		double migliore = 0;
		Player bestPlayer = null;
		for(Player p: grafo.vertexSet()) {
			int nBattuti = grafo.outDegreeOf(p);
			if(nBattuti > migliore) {
				migliore = nBattuti;
				bestPlayer = p;
			}
		}
		
		return bestPlayer;
	}
	
	public List<PlayerNumero> getListaBattuti(Player p){
		List<PlayerNumero> result = new ArrayList<>();
		for(DefaultWeightedEdge e: grafo.outgoingEdgesOf(p)) {
			result.add(new PlayerNumero(grafo.getEdgeTarget(e), grafo.getEdgeWeight(e)));
		}
		Collections.sort(result);
		return result;
	}
}
