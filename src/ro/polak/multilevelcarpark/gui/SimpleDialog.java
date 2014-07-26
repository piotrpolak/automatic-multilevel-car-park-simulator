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
public class SimpleDialog extends JDialog {

	private JLabel dMessage;
	private JButton okButton;
	private MainWindow owner;
	
	public SimpleDialog(MainWindow owner, String title, String message)
	{
		super(owner, title, true);
		this.setBounds(getGraphicsConfiguration().getBounds().width/2-160, getGraphicsConfiguration().getBounds().height/2-85, 320, 170);
		
		this.owner = owner;
		
		okButton = new JButton("OK");
		okButton.setBounds(130, 100, 60, 25);
		okButton.setFocusable(true);
		
		okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	close();
            }
        });
		
		
		dMessage = new JLabel(message);
		dMessage.setBounds(40, 20, 200, 80);
		dMessage.setVisible(true);
		
		
		this.add(okButton);
		this.add(dMessage);
		this.setLayout(null);
		this.setResizable(false);
	}
	
	public void close()
	{
		this.dispose();
	}
	
}
