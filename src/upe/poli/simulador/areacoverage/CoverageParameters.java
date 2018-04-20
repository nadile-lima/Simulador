package upe.poli.simulador.areacoverage;

import upe.poli.simulador.algorithms.PSO;
import upe.poli.simulador.vectors.Vector;

public class CoverageParameters {

	private int row;
	private int column;
	private int swarmSize;
	private Vector target;
	
	public CoverageParameters(int row, int column, int swarmSize, Vector target){
		this.row = row;
		this.column = column;
		this.swarmSize = swarmSize;
		this.target = target;
	}

	public Vector generateTargetPosition(){
		double[] position = new double[PSO.dimension];
		Vector vec;
		
		for(int count=0 ; count < position.length ; count++)
			position[count] = Math.random()*PSO.upperLimit;
		
		vec = new Vector(position);
		return vec;
	}
	
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getSwarmSize() {
		return swarmSize;
	}

	public void setSwarmSize(int swarmSize) {
		this.swarmSize = swarmSize;
	}

	public Vector getTarget() {
		return target;
	}

	public void setTarget(Vector goal) {
		this.target = goal;
	}
	
	
}
