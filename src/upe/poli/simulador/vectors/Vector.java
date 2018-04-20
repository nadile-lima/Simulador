package upe.poli.simulador.vectors;

import java.util.Random;


public class Vector extends Object{

    private double[] vector;

    public Vector(){
    	this(new double[]{0,0});
    }
    public Vector(double[] vector) {
//        for (int i = 0; i < vector.length; i++) {
//            if ((vector[i] < 0.000001 && vector[i] > 0) || (vector[i] > -0.000001 && vector[i] < 0)) {
//                vector[i] = 0;
//            }
//        }
        this.vector = vector;
    }
    

    public double getIndex(int i) {
        return this.vector[i];
    }
    
    public void setIndex(int i, double value){
    	this.vector[i] = value;
    }
    
    public void setRandomVector(){
    	Random random;
    	
    	random = new Random();
    	
        for (int i = 0; i < this.vector.length; i++) {
            this.vector[i] = random.nextDouble();
        }

    }

    public Vector minus(Vector vector) {

        double[] acumulateResult;

        acumulateResult = new double[this.getDimension()];

        for (int i = 0; i < getDimension(); i++) {
            acumulateResult[i] = this.vector[i] - vector.vector[i];
        }

        return new Vector(acumulateResult);
    }

    public Vector times(double factor) {
        double[] cumulativeResult;

        cumulativeResult = new double[getDimension()];

        for (int i = 0; i < this.getDimension(); i++) {
            cumulativeResult[i] = this.vector[i] * factor;
        }

        return new Vector(cumulativeResult);
    }

    public Vector plus(Vector vector) {

        double[] acumulateResult;

        acumulateResult = new double[this.getDimension()];

        for (int i = 0; i < getDimension(); i++) {
            acumulateResult[i] = this.vector[i] + vector.vector[i];
        }

        return new Vector(acumulateResult);
    }

    public int getDimension() {
        return this.vector.length;
    }

    public double calculateDistance(Vector vector) {

        double acumulateDistance;

        acumulateDistance = 0;

        for (int i = 0; i < this.vector.length; i++) {
            double diference;

            diference = vector.vector[i] - this.vector[i];

            acumulateDistance += Math.pow(diference, 2);
        }

        return Math.sqrt(acumulateDistance);

    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Vector) {
            Vector other = (Vector) obj;
            boolean result = true;
            if (this.vector.length == other.vector.length) {
                for (int i = 0; i < this.vector.length; i++) {
                    if (!(this.vector[i] == other.vector[i])) {
                        result = false;
                    }
                }
            } else {
                result = false;
            }
            return result;
        }
        return super.equals(obj);
    }

    @Override
    public String toString() {
        String result;

        result = "";
        for (int i = 0; i < this.getDimension(); i++) {
            result += this.vector[i] + " ";
        }

        return result;
    }

    public double getModule() {
        double module;

        module = 0;

        for (int i = 0; i < this.getDimension(); i++) {
            module += Math.pow(this.vector[i], 2);
        }
        module = Math.sqrt(module);

        return module;
    }

    public Vector getMinimumVectorFactors(double other) {
        double[] minimumFactors;

        minimumFactors = new double[getDimension()];

        for (int i = 0; i < this.getDimension(); i++) {
            minimumFactors[i] = this.vector[i];
            if (Math.abs(minimumFactors[i]) > Math.abs(other)) {
                if (minimumFactors[i] > 0) {
                    minimumFactors[i] = Math.abs(other);
                } else if (minimumFactors[i] < 0) {
                    minimumFactors[i] = -1 * Math.abs(other);
                }

            }
        }
        return new Vector(minimumFactors);
    }

    public boolean isGreaterThan(double value) {
        double thisVectorModule;
   

        thisVectorModule = this.getModule();


        return thisVectorModule > value;
    }

    public Vector getDenormalizedVector(double a, double b, double min, Vector maxVector) {

        double[] denormalizedVector;

        denormalizedVector = new double[this.getDimension()];
        for (int i = 0; i < this.getDimension(); i++) {
            denormalizedVector[i] = denormalize(a, b, min, maxVector.vector[i], this.vector[i]);
        }

        return new Vector(denormalizedVector);
    }

    public static double normalize(double a, double b, double min, double max, double number) {
        return ((b - a) / (max - min)) * (number - min) + a;
    }

    public static double denormalize(double a, double b, double min, double max, double number) {
        if((b - a) == 0){
        	return 0;
        }
    	return min + (1 / (b - a)) * (number - a) * (max - min);
    }
    
    public Vector getTwoDimensionalNormalizedVector(int minDimensionOne, int maxDimensionOne, int minDimensionTwo, int maxDimensionTwo, double lowerLimit, double upperLimit){
		double[] normalizedVector;
		
		normalizedVector = new double[2];
		normalizedVector[0] = normalize(minDimensionOne, maxDimensionOne, lowerLimit, upperLimit, this.vector[0]);
		normalizedVector[1] = normalize(minDimensionTwo, maxDimensionTwo, lowerLimit, upperLimit, this.vector[1]);
		
		return new Vector(normalizedVector);
	}

    public Vector getDenormalizedVector(double a, double b, double lowerLimit, double upperLimit) {
        double[] denormalizedVector;

        denormalizedVector = new double[this.getDimension()];
        for (int i = 0; i < this.getDimension(); i++) {
            denormalizedVector[i] = denormalize(a, b, lowerLimit, upperLimit, this.vector[i]);
        }

        return new Vector(denormalizedVector);
    }

    public double[] getVector() {
        return this.vector;
    }

	public Vector normalize(double maximumVelocity) {
//		PolarCoordinate polar = new PolarCoordinate(vector);
//		double radius = polar.getRadius() / Math.abs(polar.getRadius());
//		radius *= maximumVelocity;
		double module = getModule();
		if(module != 0){
			Vector normalized = this.times(maximumVelocity/module);
			return normalized;			
		}
//		return new PolarCoordinate(radius, polar.getTheta());
		return this;
	}

	public Vector ortogonal() {
		return new Vector(new double[]{getVector()[1],-getVector()[0]});
	}
	public Vector inverse() {
		double[] resutl = new double[this.vector.length];
		for(int i= 0; i < this.vector.length; i++){
			resutl[i] = this.vector[i] * -1;
		}
		return new Vector(resutl);
	}
	
//	public Vector clone() throws CloneNotSupportedException{
//		return (Vector) super.clone();
//
//	}
	
	
	
}
