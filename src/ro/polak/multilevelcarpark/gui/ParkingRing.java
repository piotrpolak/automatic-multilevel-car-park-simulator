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
public class ParkingRing extends JPanel{

	private int numberOfPlaces;
	private JButton place[];
	private Icon car[];
	
	public ParkingRing(int nOfPlaces)
	{
		this.numberOfPlaces = nOfPlaces;
		place = new JButton[nOfPlaces];
		car = new Icon[nOfPlaces];
		this.initComponents();
	}
	
	public void initComponents()
	{	
		int s = 30;
		int h = 22;
		for(int i=0; i<this.numberOfPlaces; i++)
		{
			this.place[i] = new JButton();
			this.place[i].setBackground(new java.awt.Color(0, 255, 0));
			this.place[i].setBounds(s*i+2, 0, h, h);
			this.place[i].setEnabled(false);
			
			this.add(this.place[i]);
			
			
			try { 
				this.car[i] = new Icon("car.gif");
				this.car[i].setBounds(s*i+2, 0, h, h);
				this.car[i].setVisible(false);
				this.add(this.car[i]);
				
			} catch(Exception e) {e.printStackTrace();}
			
			this.setPlaceFree(i, true);
			
		}
		this.setLayout(null);
	}
	
	public void setPlaceFree(int index, boolean isFree)
	{
		if(index>=numberOfPlaces) return;
		
		if(isFree)
		{
			this.place[index].setVisible(true);			
			this.car[index].setVisible(false);
		}
		else
		{
			this.place[index].setVisible(false);
			this.car[index].setVisible(true);
		}
		
	}
}
