package ro.polak.multilevelcarpark.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;

/*
 * (C) Piotr Polak 2008
 * http://www.polak.ro
 */

/**
 * @author Piotr Polak <piotr@polak.ro>
 *
 */
public class BusyIndicator extends JPanel {

	
	// ----------------------------------------------------------------------
	
	
	private JLabel label;
	private String text;
	private Icon busyIcon;
	private Icon freeIcon;
	
	
	// ----------------------------------------------------------------------
	
	
	public BusyIndicator(String text)
	{
		super();
		this.label = new JLabel();
		this.label.setBounds(30, 2, 100, 22);
		this.label.setText("Undefined");
		this.add(label);
		this.text = text;
		
		this.add(label);
		
		
		try {
			this.busyIcon = new Icon("busy.gif");
			this.freeIcon = new Icon("free.gif");
			this.busyIcon.setBounds(0, 0, 24, 24);
			this.freeIcon.setBounds(0, 0, 24, 24);
			this.busyIcon.setVisible(false);
			this.freeIcon.setVisible(false);
			
			this.add(busyIcon);
			this.add(this.freeIcon);
		} catch(Exception e) { e.printStackTrace(); }
		
		this.setLayout(null);
		this.setBusy(false);
	}
	
	
	// ----------------------------------------------------------------------
	
	
	public void setBusy(boolean isBusy)
	{
		if(isBusy)
		{
			this.busyIcon.setVisible(true);
			this.freeIcon.setVisible(false);
			this.label.setText(text+" is in use");
		}
		else
		{
			this.freeIcon.setVisible(true);
			this.busyIcon.setVisible(false);
			this.label.setText(text+" is idle");
		}
	}
}
