package ro.polak.multilevelcarpark.gui;

import javax.swing.*;

/*
 * (C) Piotr Polak 2008
 * http://www.polak.ro
 */

/**
 * @author Piotr Polak <piotr@polak.ro>
 *
 */
public class GetCarPrompt extends JDialog {

	private JTextField input;
	private JLabel label;
	private JButton okButton;
	private MainWindow owner;
	
	public GetCarPrompt(MainWindow owner)
	{
		super(owner, "Get a car", true);
		this.setBounds(getGraphicsConfiguration().getBounds().width/2-160, getGraphicsConfiguration().getBounds().height/2-60, 320, 120);
		
		this.owner = owner;
		
		okButton = new JButton("OK");
		okButton.setBounds(230, 30, 60, 25);
		
		
		okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	getCar();
            }
        });
		
	
		label = new JLabel("Car ID (plates)");
		label.setBounds(20, 10, 200, 25);
		
		input = new JTextField("", 1);
		input.setBounds(20, 30, 200, 25);
		input.setEditable(true);
		input.setFocusable(true);
		
		
		this.add(okButton);
		this.add(input);
		this.add(label);
		this.setLayout(null);
		this.setResizable(false);
	}
	
	public void getCar()
	{
		owner.getCar(input.getText().toLowerCase().replaceAll(" ", ""));
		this.dispose();
	}
	
}
