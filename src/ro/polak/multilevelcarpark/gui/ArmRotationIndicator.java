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
public class ArmRotationIndicator extends JPanel {

	
	// ----------------------------------------------------------------------
	
	
	private Icon state[];
	private int currentPos = 0;
	
	
	// ----------------------------------------------------------------------
	
	
	public ArmRotationIndicator(int nOfPlaces)
	{
		super();
		this.setLayout(null);
		
		state = new Icon[nOfPlaces];
		
		for(int i=0; i<nOfPlaces; i++)
		{
			try {
				state[i] = new Icon("arm/rot"+(i+1)+".gif");
				state[i].setVisible(false);
				state[i].setBounds(0, 0, 100, 100);
				this.add(state[i]);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}
		this.movePosition(0);
	}
	
	
	// ----------------------------------------------------------------------
	
	
	public void movePosition(int index)
	{
		state[currentPos].setVisible(false);
		state[index].setVisible(true);
		currentPos = index;
	}
}
