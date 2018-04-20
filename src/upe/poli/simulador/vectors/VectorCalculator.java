package upe.poli.simulador.vectors;

import java.util.ArrayList;

/**
 *
 * @author Diego Pinheiro
 */
public class VectorCalculator {

    public static Vector toCartesian(PolarCoordinate invasionPolarCoordinate) {
        return new Vector(new double[]{invasionPolarCoordinate.getX(), invasionPolarCoordinate.getY()});
    }
    
    private VectorCalculator(){
        
    }
    
    public static double radianToGrade(double angle){
    	return (180*angle)/Math.PI;
    }
    
    public static Vector getResultCartesian(ArrayList<? extends Vector> vectors){
    	Vector result = new Vector();
        for(Vector v : vectors){
            result.getVector()[0] += v.getVector()[0];
            result.getVector()[1] += v.getVector()[1];    
        }
        return result;
    }
    
    public static PolarCoordinate getResultPolar(ArrayList<PolarCoordinate> vectors){
    	return new PolarCoordinate(getResultCartesian(vectors).getVector());
    }
}
