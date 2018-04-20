package upe.poli.simulador.graphicinterface;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JSplitPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

import upe.poli.simulador.algorithms.PSO;
import upe.poli.simulador.areacoverage.Coverage;
import upe.poli.simulador.areacoverage.CoverageParameters;
import upe.poli.simulador.vectors.Vector;
import java.awt.Button;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;

public class MainFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextField dimensionTextField;
	private JTextField numberOfRobotsTextField;
	private JTextField cognitiveAccelerationTextField;
	private JTextField maximumVelocityTextField;
	private JTextField inertiaTextField;
	private JTextField socialAccelerationTextField;
	private JTextField numberOfIterationsTextField;
	
	private CoverageParameters cP;
	private Coverage testCoverage;
	
	private Vector target = new Vector();
	
	public MainFrame(long period) {
		setTitle("Swarm Simulator");
		
		final Canvas canvas;
		
		canvas = new Canvas(period);
		
		JSplitPane splitPaneMainSeparator = new JSplitPane();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(splitPaneMainSeparator, GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(splitPaneMainSeparator, GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JPanel parametersPanel = new JPanel();
		splitPaneMainSeparator.setLeftComponent(parametersPanel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JPanel buttonsPanel = new JPanel();
		GroupLayout gl_parametersPanel = new GroupLayout(parametersPanel);
		gl_parametersPanel.setHorizontalGroup(
			gl_parametersPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_parametersPanel.createSequentialGroup()
					.addGroup(gl_parametersPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_parametersPanel.createSequentialGroup()
							.addGap(10)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE))
						.addComponent(buttonsPanel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_parametersPanel.setVerticalGroup(
			gl_parametersPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_parametersPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 467, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
					.addComponent(buttonsPanel, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
		);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		
		JScrollPane environmentScrollPane = new JScrollPane();
		environmentScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		environmentScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JScrollPane robotsParametersScrollPanel = new JScrollPane();
		robotsParametersScrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		robotsParametersScrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JScrollPane iterationScrollPanel = new JScrollPane();
		iterationScrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		iterationScrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(iterationScrollPanel, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE)
						.addComponent(robotsParametersScrollPanel, GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
						.addComponent(environmentScrollPane, GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(environmentScrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(robotsParametersScrollPanel, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(iterationScrollPanel, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(84, Short.MAX_VALUE))
		);
		
		JPanel iterationPanel = new JPanel();
		iterationScrollPanel.setViewportView(iterationPanel);
		
		JLabel lblIteration = new JLabel("Iteration");
		
		JLabel lblNumberOfIterations = new JLabel("Number of iterations");
		lblNumberOfIterations.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		numberOfIterationsTextField = new JTextField();
		numberOfIterationsTextField.setText("500");
		numberOfIterationsTextField.setColumns(10);
		
		JButton btnGenerate = new JButton("Generate target");
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				target = cP.generateTargetPosition();
			}
		});
		GroupLayout gl_iterationPanel = new GroupLayout(iterationPanel);
		gl_iterationPanel.setHorizontalGroup(
			gl_iterationPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_iterationPanel.createSequentialGroup()
					.addGroup(gl_iterationPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_iterationPanel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_iterationPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblIteration)
								.addGroup(gl_iterationPanel.createSequentialGroup()
									.addComponent(lblNumberOfIterations)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(numberOfIterationsTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_iterationPanel.createSequentialGroup()
							.addGap(87)
							.addComponent(btnGenerate)))
					.addContainerGap(151, Short.MAX_VALUE))
		);
		gl_iterationPanel.setVerticalGroup(
			gl_iterationPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_iterationPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblIteration)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_iterationPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNumberOfIterations)
						.addComponent(numberOfIterationsTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnGenerate, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(31, Short.MAX_VALUE))
		);
		iterationPanel.setLayout(gl_iterationPanel);
		
		JPanel robotParameterPanel = new JPanel();
		robotsParametersScrollPanel.setViewportView(robotParameterPanel);
		
		JLabel lblRobots = new JLabel("Robots");
		
		JLabel lblMaximumVelocity = new JLabel("Maximum velocity");
		lblMaximumVelocity.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lblInertia = new JLabel("Inertia");
		lblInertia.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lblCognitiveAceleration = new JLabel("Cognitive acceleration");
		lblCognitiveAceleration.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lblSocialAceleration = new JLabel("Social aceleration");
		lblSocialAceleration.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		cognitiveAccelerationTextField = new JTextField();
		cognitiveAccelerationTextField.setText("0.8");
		cognitiveAccelerationTextField.setColumns(10);
		
		maximumVelocityTextField = new JTextField();
		maximumVelocityTextField.setText("10.0");
		maximumVelocityTextField.setColumns(10);
		
		inertiaTextField = new JTextField();
		inertiaTextField.setText("1.0");
		inertiaTextField.setColumns(10);
		
		socialAccelerationTextField = new JTextField();
		socialAccelerationTextField.setText("0.5");
		socialAccelerationTextField.setColumns(10);
		GroupLayout gl_robotParameterPanel = new GroupLayout(robotParameterPanel);
		gl_robotParameterPanel.setHorizontalGroup(
			gl_robotParameterPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_robotParameterPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_robotParameterPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblRobots)
						.addGroup(gl_robotParameterPanel.createSequentialGroup()
							.addGroup(gl_robotParameterPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCognitiveAceleration)
								.addComponent(lblMaximumVelocity)
								.addComponent(lblInertia)
								.addComponent(lblSocialAceleration))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_robotParameterPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(cognitiveAccelerationTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(socialAccelerationTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(inertiaTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(maximumVelocityTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(90, Short.MAX_VALUE))
		);
		gl_robotParameterPanel.setVerticalGroup(
			gl_robotParameterPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_robotParameterPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblRobots)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_robotParameterPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMaximumVelocity)
						.addComponent(maximumVelocityTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_robotParameterPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblInertia)
						.addComponent(inertiaTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_robotParameterPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCognitiveAceleration)
						.addComponent(cognitiveAccelerationTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_robotParameterPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSocialAceleration)
						.addComponent(socialAccelerationTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(206, Short.MAX_VALUE))
		);
		robotParameterPanel.setLayout(gl_robotParameterPanel);
		
		JPanel environmentPanel = new JPanel();
		environmentScrollPane.setViewportView(environmentPanel);
		
		JLabel lblEnvironmentParameters = new JLabel("Enviroment");
		
		JLabel lblDimensions = new JLabel("Dimensions");
		lblDimensions.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lblNumberOfRobots = new JLabel("Number of Robots");
		lblNumberOfRobots.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		dimensionTextField = new JTextField();
		dimensionTextField.setEditable(false);
		dimensionTextField.setText("2");
		dimensionTextField.setColumns(10);
		
		numberOfRobotsTextField = new JTextField();
		numberOfRobotsTextField.setText("10");
		numberOfRobotsTextField.setColumns(10);
		GroupLayout gl_environmentPanel = new GroupLayout(environmentPanel);
		gl_environmentPanel.setHorizontalGroup(
			gl_environmentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_environmentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_environmentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_environmentPanel.createSequentialGroup()
							.addComponent(lblDimensions)
							.addGap(44)
							.addComponent(dimensionTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblEnvironmentParameters)
						.addGroup(gl_environmentPanel.createSequentialGroup()
							.addComponent(lblNumberOfRobots)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(numberOfRobotsTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(56, Short.MAX_VALUE))
		);
		gl_environmentPanel.setVerticalGroup(
			gl_environmentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_environmentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblEnvironmentParameters)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_environmentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDimensions)
						.addComponent(dimensionTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_environmentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNumberOfRobots)
						.addComponent(numberOfRobotsTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(15, Short.MAX_VALUE))
		);
		environmentPanel.setLayout(gl_environmentPanel);
		panel.setLayout(gl_panel);
		
		JButton btnSimulate = new JButton("Simulate");
		btnSimulate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					simulate(canvas);
				} catch (Exception e) {
					e.printStackTrace();
				}				
				// Simulate canvas
			}

			
		});
		
		JButton btnClearAll = new JButton("Clear all");
		btnClearAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clearAllFields();
			}
		});
		
		JButton btnDefaultValues = new JButton("Default values");
		btnDefaultValues.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTextFieldDefaultParameters();
				loadDefaultParameters();
			}
		});
		
		JButton btnStop = new JButton("Stop");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				stopAnimationAction(canvas);
				canvas.stopAnimation();
			}
		});
		
		GroupLayout gl_buttonsPanel = new GroupLayout(buttonsPanel);
		gl_buttonsPanel.setHorizontalGroup(
			gl_buttonsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_buttonsPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnSimulate)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnClearAll)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnDefaultValues)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnStop)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_buttonsPanel.setVerticalGroup(
			gl_buttonsPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_buttonsPanel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_buttonsPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSimulate)
						.addComponent(btnClearAll)
						.addComponent(btnDefaultValues)
						.addComponent(btnStop))
					.addContainerGap())
		);
		buttonsPanel.setLayout(gl_buttonsPanel);
		parametersPanel.setLayout(gl_parametersPanel);
		
		JPanel canvasPanel = new JPanel();
		splitPaneMainSeparator.setRightComponent(canvasPanel);
		GroupLayout gl_canvasPanel = new GroupLayout(canvasPanel);
		gl_canvasPanel.setHorizontalGroup(
			gl_canvasPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_canvasPanel.createSequentialGroup()
					.addGap(9)
					.addComponent(canvas, GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_canvasPanel.setVerticalGroup(
			gl_canvasPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_canvasPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(canvas, GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE)
					.addContainerGap())
		);
		GroupLayout gl_canvas = new GroupLayout(canvas);
		gl_canvas.setHorizontalGroup(
				gl_canvas.createParallelGroup(Alignment.LEADING)
				.addGap(0, 600, Short.MAX_VALUE)
				);
		gl_canvas.setVerticalGroup(
				gl_canvas.createParallelGroup(Alignment.TRAILING)
				.addGap(0, 600, Short.MAX_VALUE)
				);
		canvas.setLayout(gl_canvas);
		canvasPanel.setLayout(gl_canvasPanel);
		getContentPane().setLayout(groupLayout);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnOption = new JMenu("Options");
		menuBar.add(mnOption);
		
		JMenuItem mntmRobotsPosition = new JMenuItem("Robots position");
		mntmRobotsPosition.setFont(new Font("Dialog", Font.PLAIN, 12));
		mnOption.add(mntmRobotsPosition);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmQuit = new JMenuItem("Quit");
		mntmQuit.setFont(new Font("Dialog", Font.PLAIN, 12));
		mnNewMenu.add(mntmQuit);
		mntmQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				closeAction();
			}
		});
		
	}
	
	private void closeAction(){
		System.exit(0);
	}
	
	private void clearAllFields(){
		this.numberOfRobotsTextField.setText("");
		
		this.maximumVelocityTextField.setText("");
		this.inertiaTextField.setText("");
		this.cognitiveAccelerationTextField.setText("");
		this.socialAccelerationTextField.setText("");
		
		this.numberOfIterationsTextField.setText("");
//		this.targetPositionTextField.setText("");
	}
	
	private void simulate(Canvas canvas) throws Exception{
		loadTextFieldParameters();
		viewSimulate(canvas);
	}
	
	private void viewSimulate(Canvas canvas){
		try {
			
			canvas.setCoverage(this.testCoverage);
			canvas.startThread();
		} catch (Exception e) {
//			this.tbeEstatisticaSimulacao.setText(e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void loadTextFieldParameters() throws Exception{
		
		int dimension;
		double inertia;
		double cognitiveAcceleration;
		double socialAcceleration;
		
		int maxIterationNumber;
		int numberOfRobots;
		double maximumVelocity;
			
		dimension = 2;
		
		numberOfRobots = Integer.parseInt(numberOfRobotsTextField.getText());
		maximumVelocity = Double.parseDouble(maximumVelocityTextField.getText());
		inertia = Double.parseDouble(inertiaTextField.getText());
		cognitiveAcceleration = Double.parseDouble(cognitiveAccelerationTextField.getText());
		socialAcceleration = Double.parseDouble(socialAccelerationTextField.getText());
		maxIterationNumber = Integer.parseInt(numberOfIterationsTextField.getText());
		
		PSO.psoParameters(dimension, inertia, cognitiveAcceleration, socialAcceleration);
		PSO.maximumVelocity = maximumVelocity;
		
		cP = new CoverageParameters(50, 50, numberOfRobots, target);
		
		testCoverage = new Coverage(cP);
		testCoverage.setNumberOfIterations(maxIterationNumber);
		testCoverage.initSwarm();

	}
	
//	private double[] getTargetPosition(){
//		
//		String aux = targetPositionTextField.getText();
//		
//		double[] position = new double[2];
//		
//		for(int count = 0; count < aux.length(); count++){
//			if(aux.charAt(count) == ','){
//				position[0] = (new Double(aux.substring(0, count).trim())).doubleValue();
//				position[1] = (new Double(aux.substring(count+1, aux.length()).trim())).doubleValue();
//			}
//		}
//		
//		return position;
//	}
	
	private void loadDefaultParameters(){

		int dimension;
		double inertia;
		double cognitiveAcceleration;
		double socialAcceleration;
		
		int maxIterationNumber;
		int numberOfRobots;
		double maximumVelocity;
		
		dimension = 2;

//		maximumVelocity = 5;
//		inertia = 1;
//		cognitiveAcceleration = 1;
//		socialAcceleration = 3;
//		maxIterationNumber = 50;

		numberOfRobots = Integer.parseInt(numberOfRobotsTextField.getText());
		maximumVelocity = Double.parseDouble(maximumVelocityTextField.getText());
		inertia = Double.parseDouble(inertiaTextField.getText());
		cognitiveAcceleration = Double.parseDouble(cognitiveAccelerationTextField.getText());
		socialAcceleration = Double.parseDouble(socialAccelerationTextField.getText());
		maxIterationNumber = Integer.parseInt(numberOfIterationsTextField.getText());
//		target = new Vector(getTargetPosition());
		
		PSO.psoParameters(dimension, inertia, cognitiveAcceleration, socialAcceleration);
		PSO.maximumVelocity = maximumVelocity;
		
		cP = new CoverageParameters(50, 50, numberOfRobots, target);
		testCoverage = new Coverage(cP);
		testCoverage.setNumberOfIterations(maxIterationNumber);
		testCoverage.initSwarm();
	}
	
	private void setTextFieldDefaultParameters(){

		this.maximumVelocityTextField.setText(PSO.maximumVelocity + "");
		this.inertiaTextField.setText(PSO.inertiaWeight + "");
		this.cognitiveAccelerationTextField.setText(PSO.cognitive + "");
		this.socialAccelerationTextField.setText(PSO.social + "");
		this.numberOfRobotsTextField.setText(cP.getSwarmSize() + "");
		this.numberOfIterationsTextField.setText(testCoverage.getNumberOfIterations() + "");
//		this.targetPositionTextField.setText(cP.getTarget() + "");
	}
}
