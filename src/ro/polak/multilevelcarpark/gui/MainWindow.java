package ro.polak.multilevelcarpark.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import ro.polak.multilevelcarpark.generic.*;

/*
 * (C) Piotr Polak 2008
 * http://www.polak.ro
 */

/**
 * @author Piotr Polak <piotr@polak.ro>
 *
 */
public class MainWindow extends JFrame {

	public static final  long serialVersionUID = 225354234;
	private int levels;
	private int placesPerRing;
	private JPanel panel;
	protected PlacesMap placesMap;
	protected BusyIndicator armIndicator, elevatorIndicator, systemIndicator, carContainerIndicator;
	protected JLabel currentActionLabel;
	protected JButton getCarButton, insertCarButton;
	protected ArmRotationIndicator armRotationIndicator;
	
	// ----------------------------------------------------------------------
	
	
	public MainWindow(int levels, int placesPerRing)
	{
		super("Multilevel Car Park (by Piotr Polak)");
		this.levels = levels;
		this.placesPerRing = placesPerRing;
	}
	
	
	
	// ----------------------------------------------------------------------
	
	
	public void initComponents()
	{
		try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());} 
		catch (Exception e) {}
		
		this.setBounds(getGraphicsConfiguration().getBounds().width/2-400, getGraphicsConfiguration().getBounds().height/2-200, 800, 400);

		
		/* Building main panel */
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 800, 400);
		
		/*
		 * Buttons
		 */
		getCarButton = new JButton();
		getCarButton.setText("Get car");
		getCarButton.setBounds(680, 325, 100, 25);
		// Action		
		getCarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	getCarButtonActionPerformed(evt);
            }
        });		
		panel.add(getCarButton);
		
		insertCarButton = new JButton();
		insertCarButton.setText("Insert car");
		insertCarButton.setBounds(570, 325, 100, 25);
		
		insertCarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	insertCarButtonActionPerformed(evt);
            }
        });
		panel.add(insertCarButton);
		
		/*
		 * Labels
		 */
		currentActionLabel = new JLabel();
		currentActionLabel.setText("System IDLE");
		//currentActionLabel.setHorizontalAlignment(JLabel.RIGHT);
		currentActionLabel.setBounds(580, 180, 150, 22);
		currentActionLabel.setVisible(true);
		panel.add(currentActionLabel);
	    
		
		/*
		 * Indicators
		 */
		armIndicator = new BusyIndicator("The arm");
		armIndicator.setBounds(580, 20, 140, 24);
		armIndicator.setVisible(true);
		panel.add(armIndicator);
		
		elevatorIndicator = new BusyIndicator("The elevator");
		elevatorIndicator.setBounds(580, 50, 140, 24);
		elevatorIndicator.setVisible(true);
		panel.add(elevatorIndicator);
		
		carContainerIndicator = new BusyIndicator("Car container");
		carContainerIndicator.setBounds(580, 80, 140, 24);
		carContainerIndicator.setVisible(true);
		panel.add(carContainerIndicator);
		
		systemIndicator = new BusyIndicator("The system");
		systemIndicator.setBounds(580, 150, 140, 24);
		systemIndicator.setVisible(true);
		panel.add(systemIndicator);
		
		
		/*
		 * 
		 */
		armRotationIndicator = new ArmRotationIndicator(this.placesPerRing);
		armRotationIndicator.setBounds(450, 20, 100, 100);
		panel.add(armRotationIndicator);
		
		
		
		/* Building map */
		placesMap = new PlacesMap(levels, placesPerRing);
		placesMap.setBounds(10, 10, placesMap.getWidth(), placesMap.getHeight());
		
		placesMap.movePosition(0);
		placesMap.moveLevel(0);
		panel.add(placesMap);
		
		
		
		
		
		/* Adding and setting props */
		this.add(panel);
		this.setLayout(null);		
	    this.setVisible(true);
	    this.setResizable(false);
	    
	    
	    this.addWindowListener(new WindowAdapter() {
	        public void windowClosing(WindowEvent we) {System.exit(0);}});
	}
	
	private void getCarButtonActionPerformed(java.awt.event.ActionEvent evt)
	{
		GetCarPrompt dialog = new GetCarPrompt(this);
		dialog.setVisible(true);
	}
	
	private void insertCarButtonActionPerformed(java.awt.event.ActionEvent evt)
	{
		InsertCarPrompt dialog = new InsertCarPrompt(this);
		dialog.setVisible(true);
	}
	
	protected void insertCar(String carID)
	{
		SimpleDialog dialog = null;
		
		if(carID.isEmpty())
		{
			dialog = new SimpleDialog(this, "Invalid car ID", "Entered car ID is not valid!");
		}
		else
		{
			GUIDriver.messenger.putMessage(1, carID);
		}
		if(dialog != null)
			dialog.setVisible(true);
	}
	
	protected void getCar(String carID)
	{
		SimpleDialog dialog = null;
		
		if(carID.isEmpty())
		{
			dialog = new SimpleDialog(this, "Invalid car ID", "Entered car ID is not valid!");
		}
		else
		{
			GUIDriver.messenger.putMessage(2, carID);
		}
		if(dialog != null)
			dialog.setVisible(true);
	}
	
}
