package upe.poli.simulador.tests;

import upe.poli.simulador.algorithms.PSO;
import upe.poli.simulador.areacoverage.Coverage;
import upe.poli.simulador.areacoverage.CoverageParameters;
import upe.poli.simulador.graphicinterface.Canvas;
import upe.poli.simulador.vectors.Vector;

public class TestePSO {

	public static void main(String[] args){
		
		double[] goal = {0.0, 0.0};
		int quant_iteration = 15;
		int swarmSize = 5;
		
		Vector target = new Vector(goal);
		
		CoverageParameters cV = new CoverageParameters(50, 50, swarmSize, target);
		
		double[][] robotPositions = new double[swarmSize][PSO.dimension];

		
		Coverage test = new Coverage(cV);
		
		for(int aux=0 ; aux<swarmSize ; aux++){
			robotPositions[aux][0] = Math.random()*Canvas.PANELHEIGHT;
//			System.out.println(robotPositions[aux][0]);
			robotPositions[aux][1] = Math.random()*Canvas.PANELWIDTH;
//			System.out.println(robotPositions[aux][1]);
		}
		test.initSwarm(robotPositions);
		test.setNumberOfIterations(quant_iteration);
		
		while(!(test.hasFinished()))
			test.simulate();
		}
	}

