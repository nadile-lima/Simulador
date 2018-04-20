package upe.poli.simulador.graphicinterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import upe.poli.simulador.vectors.PolarCoordinate;
import upe.poli.simulador.vectors.Vector;

import upe.poli.simulador.algorithms.PSO;
import upe.poli.simulador.areacoverage.Coverage;
import upe.poli.simulador.areacoverage.RobotPSO;

public class Canvas extends JPanel implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Coverage coverage;
	
	public static final int PANELHEIGHT = 600;
	public static final int PANELWIDTH = 600;
	
	private static final int NO_DELAYS_PER_YIELD = 16;
	private static final int MAX_FRAMES_SKIPS = 5;
	/*
	 * number of frames with delay of 0ms before the animation thread yield to
	 * other running threads
	 */
	
	private Thread animator;
	private boolean running = false;
	
	private long period;
	private int timeSpent = 0;
	
	private BufferedImage backgroundImage;
	private BufferedImage[] spritesRobots;
	private BufferedImage target;
	private BufferedImage robot;
	
	private long startTime;
	private long previousStartTime;
	
	private Graphics dbg;
	private Image dbImage = null;
	
	public void setCoverage(Coverage coverage){
		this.coverage = coverage;
	}
	
	
	public Canvas(long period){
		
		this.period = period;

		this.timeSpent = 0;
		
		setBackground(Color.white);
		setPreferredSize(new Dimension(PANELWIDTH, PANELHEIGHT));
		
		this.startTime = System.nanoTime();
		
		this.setFocusable(true);
		this.requestFocus(true); 
	}
	
	public int getTimeSpent() {
		return this.timeSpent;
	}

	public long getPeriod() {
		return period;
	}

	public long getPrevStartTime() {
		return previousStartTime;
	}

	public void setPrevStartTime(long prevStartTime) {
		this.previousStartTime = prevStartTime;
	}
	
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getStartTime() {
		return startTime;
	}
	
	public void addNotify(){
		super.addNotify();
	}
	
	public void startThread(){
		
		if (animator == null || !running) {
			animator = new Thread(this);
			animator.start();
		}
	}
	
	public void stopAnimation(){
		running = false;
	}
	
	public void createSpritesRobots(){
		spritesRobots = new BufferedImage[360];
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		for(int i = 0; i < 360; i++){
			try {
				spritesRobots[i] = ImageIO.read(classLoader.getResource("robot_icon_"+(i)+".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public void initialize(){
		try{
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			this.target = ImageIO.read(classLoader.getResource("target_icon.png"));
			this.robot = ImageIO.read(classLoader.getResource("robot_icon.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		long beforeTime, afterTime, timeDiff, sleepTime;
		long overSleepTime = 0L;
		long excess = 0L;
		int noDelays = 0;

		setStartTime(System.nanoTime());
		previousStartTime = getStartTime();
		beforeTime = getStartTime();

		initialize();

		running = true;
		while (running) {
			update(); // game start is updated
			render(); // render to the buffer
			//paintScreen(); // draw buffer to the screen
			repaint();
			afterTime = System.nanoTime();
			timeDiff = afterTime - beforeTime;
			sleepTime = (this.period - timeDiff) - overSleepTime; // time left in this loop

			if (sleepTime > 0) { // there is still time to sleep in this cycle.
				try {
					Thread.sleep(sleepTime / 1000000L); // nano -> micro
				} catch (InterruptedException ex) {
				}
				overSleepTime = (System.nanoTime() - afterTime) - sleepTime;
			} else { // sleepTime <= 0; frame took longer than expected to animate

				excess -= sleepTime; // store excess time value
				overSleepTime = 0L;

				if (++noDelays >= NO_DELAYS_PER_YIELD) {
					Thread.yield(); // give another thread a chance to run
					noDelays = 0;
				}
			}
			beforeTime = System.nanoTime();

			int skips = 0;
			while ((excess > period) && (skips < MAX_FRAMES_SKIPS)) {
				excess -= this.period;
				update();
				skips++;
			}

		}
	} // end of run() method
	
	public void paint(Graphics g){
		super.paint(g);
		
		try{
			if((g != null) && (dbImage != null)){
				g.drawImage(dbImage, 0, 0, null);
				g.drawImage(backgroundImage, 0, 0, null);
				
				drawRobots(g, robot);
			}
			g.dispose();
		} catch (Exception e){
			System.out.println("Graphics context error: " + e);
		}
	}
	
	public void drawRobots(Graphics g, BufferedImage robot){
		
		Vector cartesianPosition;
		PolarCoordinate coordinate;
		Vector pixelPosition;
		double correctionIndex;
		int robotAngle;
		
		for(RobotPSO rPSO : coverage.getSwarm()){
			cartesianPosition = rPSO.getCurrent_position();
			coordinate = new PolarCoordinate(rPSO.getVelocity().getVector());
			robotAngle = coordinate.getThetaAsAngle();
			pixelPosition = cartesianPosition.getTwoDimensionalNormalizedVector(0, PANELWIDTH, 0, PANELHEIGHT, PSO.lowerLimit, PSO.upperLimit);
			correctionIndex = PANELHEIGHT - pixelPosition.getIndex(1);
//			BufferedImage image = this.spritesRobots[robotAngle];
			
			g.drawImage(robot, (int) pixelPosition.getIndex(0), (int) correctionIndex, null);
		}
		
		// draw the target
		cartesianPosition = PSO.target;
		pixelPosition = cartesianPosition.getTwoDimensionalNormalizedVector(0, PANELWIDTH, 0, PANELHEIGHT, PSO.lowerLimit, PSO.upperLimit);
		correctionIndex = PANELHEIGHT - pixelPosition.getIndex(1);
		g.drawImage(this.target, (int) pixelPosition.getIndex(0), (int) correctionIndex, null);
		
	}
	
	private void update(){
		
		this.coverage.simulate();
		
		if(this.coverage.hasFinished())
			stopAnimation();
		
		/*if(this.environment.hasFinished()){
			stopAnimation();
		}*/
	}
	
	private void render(){
		
		if(dbImage == null){	//create buffer
			dbImage = createImage(PANELWIDTH, PANELHEIGHT);
			if(dbImage == null){
				return;
			}else
				dbg = dbImage.getGraphics();
		}
		
		//clean background
		dbg.setColor(Color.white);
		dbg.fillRect(0, 0, PANELWIDTH, PANELHEIGHT);
		
	}

}
