package it.polito.tdp.PremierLeague.model;

public class PlayerNumero implements Comparable<PlayerNumero>{

	private Player p;
	private double delta;
	
	public PlayerNumero(Player p, double delta) {
		super();
		this.p = p;
		this.delta = delta;
	}

	public Player getP() {
		return p;
	}

	public void setP(Player p) {
		this.p = p;
	}

	public double getDelta() {
		return delta;
	}

	public void setDelta(double delta) {
		this.delta = delta;
	}

	@Override
	public String toString() {
		return p + " | " + delta;
	}

	@Override
	public int compareTo(PlayerNumero o) {
		// TODO Auto-generated method stub
		return (int) (o.delta - this.delta);
	}
	
	
}
