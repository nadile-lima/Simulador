package upe.poli.simulador.vectors;

/**
 * 
 * @author Diego Pinheiro
 */
public class PolarCoordinate extends Vector {

	// private double radius;
	// private double theta;
	public PolarCoordinate(double radius, double theta) {
		super(
				new double[] { radius * Math.cos(adjust(theta)),
						radius * Math.sin(adjust(theta)) });
		// this.radius = radius;
		// this.theta = theta;
	}

	public PolarCoordinate(double[] vector) {
		super(vector);

	}

	public double getRadius() {
		double radius;
		double theta;
		double x;
		double y;
		x = getX();
		y = getY();
		theta = getTheta();
		Quadrant q = getQuadrant();
		radius = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
//		if(theta >= 0){
//			if(q == Quadrant.Third){
//				radius *= -1;
//			}
//		}else{
//			if(q == Quadrant.Fourth){
//				radius *= -1;
//			}
//		}

//		if (theta > Math.PI) {
//			radius *= -1;
//		}
		return radius;
	}

	public double getTheta() {
		Quadrant quadrant;
		double tan;
		double angle;
		double x;
		double y;
		x = getX();
		y = getY();
		quadrant = getQuadrant();
		if (x == 0) {
			if (y >= 0) {
				angle = Math.PI/2;
			} else {
				angle = 3 * Math.PI / 2;
			}
		} else {
			tan = y / x;
			angle = Math.atan(tan);
			if(angle == 0){
				if(quadrant == Quadrant.Second){
					angle = Math.PI;
				}
			}else if(angle > 0){
				if(quadrant==Quadrant.Third){
					angle += Math.PI;
				}
			}else{
				angle = adjust(angle);
				if(quadrant == Quadrant.Second){
					angle -= Math.PI;
				}
			}
			//			if (tan >= 0) {
			//				if (quadrant == Quadrant.First) {
			//					angle = angle;
			//				} else if (quadrant == Quadrant.Third) {
			//					angle += Math.PI;
			//				}
			//			} else if (tan < 0) 2{
			//				if (quadrant == Quadrant.Second) {
			//					angle += Math.PI;
			//				} else if (quadrant == Quadrant.Fourth) {
			//					angle += 2 * Math.PI;
			//				}
			//			}
		}
		//		return adjust(angle);
		return angle;
	}

	public double getX() {
		return getVector()[0];
		// return this.radius * Math.cos(this.theta);
	}

	public double getY() {
		return getVector()[1];
		// return this.radius * Math.sin(this.theta);
	}

	public static double adjust(double angle) {
		double newAngle = angle;
		//
		while ((newAngle >  2* Math.PI) || (newAngle < 0)) {

			if (newAngle > 2* Math.PI) {
				newAngle = angle - (2 * Math.PI);
			}
			if (newAngle < 0) {
				newAngle = angle + (2 * Math.PI);
			}

		}

		return newAngle;
	}

	public Quadrant getQuadrant() {
		double x;
		double y;
		x = getX();
		y = getY();
		Quadrant result;
		result = Quadrant.First;
		if (x >= 0 && y >= 0) {
			result = Quadrant.First;
		} else if (x < 0 && y >= 0) {
			result = Quadrant.Second;
		} else if (x <= 0 && y < 0) {
			result = Quadrant.Third;
		} else if (x >= 0 && y < 0) {
			result = Quadrant.Fourth;
		}
		return result;
	}
	
	@Override
	public String toString() {
		return "("+ this.getRadius() + " , " + getThetaAsAngle() + "º)";
	}
	
	public int getThetaAsAngle(){
		double theta = getTheta();
		int angle = (int) ((int) (theta * 90)/(Math.PI/2));
//		if(angle >= 0){
//			if(getQuadrant() == Quadrant.Third){
//				angle += 180;
//			}
//		}else{
//			angle += 360;
//			if(getQuadrant() == Quadrant.Second){
//				angle -= 180;
//			}
//		}
		return angle;
		
	}
	
	public PolarCoordinate turn(double degress){
		PolarCoordinate result;
		result = new PolarCoordinate(getRadius(), getTheta() + degress);
		return result;
	}
}
