package upe.poli.simulador.areacoverage;

import upe.poli.simulador.algorithms.PSO;
import upe.poli.simulador.vectors.Vector;

public class Coverage {

	private int numberOfIterations;
	
	private RobotPSO[] swarm;
	private Vector target;
	
	private int count = 0;
	
	public Coverage(CoverageParameters cP){
		
		this.swarm = new RobotPSO[cP.getSwarmSize()];
		this.target = cP.getTarget();
		
	}
	
	public void initSwarm(){
		
		for(int count=0 ; count<swarm.length ; count++)
			swarm[count] = new RobotPSO();
		
		Vector pos_gBest = PSO.takeBest(swarm);
		
		for (int swarmLength = 0; swarmLength < swarm.length; swarmLength++)
			swarm[swarmLength].setGBest(pos_gBest);
	}
	
	public void initSwarm(double[][] robotPositions){
		
		for(int count=0 ; count<swarm.length ; count++)
			swarm[count] = new RobotPSO(robotPositions[count]);
		
		Vector pos_gBest = PSO.takeBest(swarm);
		
		for(int swarmLength=0 ; swarmLength<swarm.length ; swarmLength++)
			swarm[swarmLength].setGBest(pos_gBest);
	}
	
	public void iterate(){
		for (int swarmLength = 0; swarmLength < swarm.length; swarmLength++) {
			Vector position = swarm[swarmLength].getCurrent_position();
			
			if(!(position.equals(target))){
				PSO.psoFunction(swarm[swarmLength], swarm, target);
				position = swarm[swarmLength].getCurrent_position();
			}
			
			System.out.println("Robô " + swarmLength + ": " + position.getIndex(0) + ", " + position.getIndex(1));
			System.out.println("\n");
		}
	}

	public void simulate(){
		System.out.println("TEMPO: "+ count);
		iterate();
		System.out.println("\n\n");
		count++;
	}
	
	public boolean hasFinished(){
		boolean end = false;
		if (count == getNumberOfIterations())
			end = true;
		
		return end;
	}

	public int getNumberOfIterations() {
		return numberOfIterations;
	}

	public void setNumberOfIterations(int numberOfIterations) {
		this.numberOfIterations = numberOfIterations;
	}

	public RobotPSO[] getSwarm() {
		return swarm;
	}

	public void setSwarm(RobotPSO[] swarm) {
		this.swarm = swarm;
	}

	public Vector getTarget() {
		return target;
	}

	public void setTarget(Vector target) {
		this.target = target;
	}
	
}
