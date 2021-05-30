package it.polito.tdp.PremierLeague.model;

public class TestModel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Model m = new Model();
		m.creaGrafo(0.3);
		System.out.println(m.getBestPlayer());
		System.out.println(m.getListaBattuti((m.getBestPlayer())));
		System.out.println(m.getDreamTeam(3));
		System.out.println(m.getGradoTitolarita());
	}

}
