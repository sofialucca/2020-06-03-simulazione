package it.polito.tdp.PremierLeague.model;

public class Adiacenti {

	private Player p1;
	private Player p2;
	private double delta;
	
	public Adiacenti(Player p1, Player p2, double delta) {
		super();
		this.p1 = p1;
		this.p2 = p2;
		this.delta = delta;
	}

	public Player getP1() {
		return p1;
	}

	public void setP1(Player p1) {
		this.p1 = p1;
	}

	public Player getP2() {
		return p2;
	}

	public void setP2(Player p2) {
		this.p2 = p2;
	}

	public double getDelta() {
		return delta;
	}

	public void setDelta(double delta) {
		this.delta = delta;
	}
	
	
}
