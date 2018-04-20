package upe.poli.simulador.areacoverage;

import upe.poli.simulador.algorithms.PSO;

public class Robot {

	private int[] current_position = new int[PSO.dimension];
	private int[] pBest = new int[PSO.dimension];
	private int[] gBest = new int[PSO.dimension];
	private int[] velocity = new int[PSO.dimension];
	
	public void initialize(){
		//TODO
	}
	
	public int[] getCurrent_position() {
		return current_position;
	}
	public void setCurrent_position(int[] current_position) {
		this.current_position = current_position;
	}
	public int[] getPBest() {
		return pBest;
	}
	public void setPBest(int[] pBest) {
		this.pBest = pBest;
	}
	public int[] getGBest() {
		return gBest;
	}
	public void setGBest(int[] gBest) {
		this.gBest = gBest;
	}
	public int[] getVelocity() {
		return velocity;
	}
	public void setVelocity(int[] velocity) {
		this.velocity = velocity;
	}
	
	
}
