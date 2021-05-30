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
	private List<Player> listaOttima;
	private double gradoTitolarita;
	private int nGiocatori;
	
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
	
	public List<Player> getDreamTeam(int k){
		this.listaOttima = new ArrayList<>();
		this.gradoTitolarita = 0;
		nGiocatori = k;
		List<Player> listaDisponibili = new ArrayList<>(grafo.vertexSet());
		List<Player> parziale = new ArrayList<>();
		cerca(parziale, listaDisponibili, k);
		return listaOttima;
	}

	private void cerca(List<Player> parziale, List<Player> listaDisponibili, int k) {
		double gradoParziale = calcoloGradoTitolarita(parziale);
		if(parziale.size() == k) {
			if(gradoParziale > this.gradoTitolarita) {
				gradoTitolarita = gradoParziale;
				this.listaOttima = new ArrayList<>(parziale);
			}
			return;
		}
		
		for(Player p:listaDisponibili) {
			if(!parziale.contains(p)) {
				parziale.add(p);
				List<Player> nuovaLista = new ArrayList<>(listaDisponibili);
				nuovaLista.removeAll(Graphs.successorListOf(grafo, p));
				nuovaLista.remove(p);
				cerca(parziale, nuovaLista, k);
				parziale.remove(p);
			}
		}
	}
	
	private double calcoloGradoTitolarita(List<Player> parziale) {
		double risultato = 0;
		for(Player p: parziale) {
			for(DefaultWeightedEdge e : grafo.outgoingEdgesOf(p)) {
				risultato += grafo.getEdgeWeight(e);
			}
			for(DefaultWeightedEdge e : grafo.incomingEdgesOf(p)) {
				risultato -= grafo.getEdgeWeight(e);
			}
		}
		return risultato;
		
	}
	
	public int getGradoTitolarita() {
		return (int) this.gradoTitolarita;
	}
}
