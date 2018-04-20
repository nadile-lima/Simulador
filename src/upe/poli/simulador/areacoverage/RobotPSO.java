package upe.poli.simulador.areacoverage;

import upe.poli.simulador.algorithms.PSO;
import upe.poli.simulador.vectors.Vector;

public class RobotPSO {

	private Vector current_position;
	private Vector pBest = new Vector();
	private Vector gBest = new Vector();
	private Vector velocity;
	
	public RobotPSO(){
		initialize();
	}
	
	public RobotPSO(double pos[]){
		initialize(pos);
	}
	
	private void initialize(){
		
		double vel[] = new double[2];
		double[] current_pos = new double[2];
		
		for (int dim = 0; dim < PSO.dimension; dim++) {
			vel[dim] = Math.random();
		}
		
		current_pos[0] = Math.random()*PSO.upperLimit;
		current_pos[1] = Math.random()*PSO.upperLimit;
		
		this.velocity = new Vector(vel);
		this.current_position = new Vector(current_pos);
		
		setPBest(current_position);
		
	}
	
	private void initialize(double pos[]){

		double[] vel = new double[2];
		
		for (int dim = 0; dim < PSO.dimension; dim++) {
			vel[dim] = Math.random();
		}
		
		this.velocity = new Vector(vel);
		this.current_position = new Vector(pos);
		
		setPBest(current_position);
		
	}
	
	public Vector getCurrent_position() {
		return current_position;
	}
	public void setCurrent_position(Vector current_position) {
		this.current_position = current_position;
	}
	public Vector getPBest() {
		return pBest;
	}
	public void setPBest(Vector pBest) {
		this.pBest = pBest;
	}
	public Vector getGBest() {
		return gBest;
	}
	public void setGBest(Vector gBest) {
		this.gBest = gBest;
	}
	public Vector getVelocity() {
		return velocity;
	}
	public void setVelocity(Vector velocity) {
		this.velocity = velocity;
	}
	
	
}
