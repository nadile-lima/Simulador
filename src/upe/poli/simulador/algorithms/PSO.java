package upe.poli.simulador.algorithms;

import upe.poli.simulador.areacoverage.RobotPSO;
import upe.poli.simulador.vectors.Vector;

public class PSO {

	public static int dimension = 2;
	
	public static double inertiaWeight = 0.5;
	public static double cognitive = 1.0;
	public static double social = 0.6;
	public static double maximumVelocity = 10;
	
	public static double upperLimit = 10000;
	public static double lowerLimit = 0;
	
	private static RobotPSO[] swarm;
	
	public static Vector target = new Vector();
	
	public static void psoFunction(RobotPSO robot, RobotPSO[] swarm2, Vector goal){
		/*
		 *  takeBest
		 *  updateVelocity
		 *  updateRobot
		 * */
		target = goal;
		swarm = swarm2;
		updateRobot(robot);
		updateGBest();
		// TODO verify if gBest is updated
		
	}
	
	public static void psoParameters(int dim, double inertia, double cognitiveComponent, double socialComponent){
		dimension = dim;
		inertiaWeight = inertia;
		cognitive = cognitiveComponent;
		social = socialComponent;
	}
	
	private static double fitness(Vector position){
		
		double path = 0;
		/** Calculates the path from one position to the cell goal */
		for(int count=0 ; count<dimension ; count++)
			path += Math.pow(target.getIndex(count)-position.getIndex(count), 2);
		
		return path;
	}
	public static Vector takeBest(RobotPSO[] swarm){
		/** Identificste which robot is closer to the cell goal */
		
		double bestFitness = fitness(swarm[0].getCurrent_position());
		int indexBest = 0;
		
		for (int swarmLength = 0; swarmLength < swarm.length; swarmLength++) {
			double fit = fitness(swarm[swarmLength].getCurrent_position());
			if(fit<bestFitness){
				bestFitness = fit;
				indexBest = swarmLength;
			}
		}
		
		return swarm[indexBest].getCurrent_position();
	}
	
	private static void updateGBest(){
		
		Vector posGBest = takeBest(swarm);
		for(int count=0 ; count<swarm.length ; count++){
			if(fitness(posGBest) < fitness(swarm[count].getGBest())){
				swarm[count].setGBest(posGBest);
			}
		}
	}
	
	private static Vector updateVelocity(RobotPSO robot){
		
		Vector currentPosition = robot.getCurrent_position();
		Vector pBest = robot.getPBest();
		Vector currentVelocity = robot.getVelocity();
		Vector gBest = robot.getGBest();
		Vector newVelocity = new Vector();
		
		double newVel = 0;
		
		for(int d=0 ; d<dimension ; d++){
			
			newVel = inertiaWeight*currentVelocity.getIndex(d) + cognitive*Math.random()*(pBest.getIndex(d) - currentPosition.getIndex(d)) + social*Math.random()*(gBest.getIndex(d) - currentPosition.getIndex(d));
			
			if(newVel > maximumVelocity)
				newVel = maximumVelocity;
						
			newVelocity.setIndex(d, newVel);
		}
		
		return newVelocity;
	}
	
	private static void updateRobot(RobotPSO robot){
		
		Vector newPosition = new Vector();
		Vector currentPosition = robot.getCurrent_position();
		
		robot.setVelocity(updateVelocity(robot));
		
		Vector currentVelocity = robot.getVelocity();
		
		for(int d=0 ; d<dimension ; d++){
			newPosition.setIndex(d, (currentPosition.getIndex(d) + currentVelocity.getIndex(d)));
		}
		
		if(newPosition.getIndex(0) > upperLimit)
			newPosition.setIndex(0, upperLimit);
		
		if(newPosition.getIndex(0) < lowerLimit)
			newPosition.setIndex(0, lowerLimit);
		
		if(newPosition.getIndex(1) > upperLimit)
			newPosition.setIndex(1, upperLimit);
		
		if(newPosition.getIndex(1) < lowerLimit)
			newPosition.setIndex(1, lowerLimit);
		
		robot.setCurrent_position(newPosition);
		
		// Before update pBest, verify if the fitness for this position is better than pBest
		// yes -> update
		// no -> no change
		if(fitness(robot.getCurrent_position()) < fitness(robot.getPBest()))
			robot.setPBest(robot.getCurrent_position());
		
		System.out.println("fitness: " + fitness(robot.getGBest()));
	}

	public RobotPSO[] getSwarm() {
		return swarm;
	}

	public static void setSwarm(RobotPSO[] new_swarm) {
		swarm = new_swarm;
	}
	
	
}
