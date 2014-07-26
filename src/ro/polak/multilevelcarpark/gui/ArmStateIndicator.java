package ro.polak.multilevelcarpark.gui;

import javax.swing.JPanel;

/*
 * (C) Piotr Polak 2008
 * http://www.polak.ro
 */

/**
 * @author Piotr Polak <piotr@polak.ro>
 *
 */
public class ArmStateIndicator extends JPanel{

	
	// ----------------------------------------------------------------------
	
	
	private Icon car, bar, bar_extended, catcher_close, catcher_open, catcher_close_extended, catcher_open_extended;
	private boolean isExtended, isOpen;
	
	
	// ----------------------------------------------------------------------
	
	
	public ArmStateIndicator()
	{
		super();
		this.setLayout(null);
		
		isExtended = isOpen = true;
		
		try {
			car = new Icon("car.gif");
			bar = new Icon("bar/bar.gif");
			bar_extended = new Icon("bar/bar_extended.gif");
			catcher_close = new Icon("bar/catcher_close.gif");
			catcher_open = new Icon("bar/catcher_open.gif");
			catcher_close_extended = new Icon("bar/catcher_close_extended.gif");
			catcher_open_extended = new Icon("bar/catcher_open_extended.gif");
			

			bar.setVisible(false);
			bar.setBounds(0, 0, 100, 40);
			this.add(bar);
			
			bar_extended.setVisible(true);
			bar_extended.setBounds(0, 0, 100, 40);
			this.add(bar_extended);
			
			catcher_close.setVisible(false);
			catcher_close.setBounds(-28, 0, 100, 40);
			this.add(catcher_close);
			
			catcher_open.setVisible(false);
			catcher_open.setBounds(0, 0, 100, 40);
			this.add(catcher_open);
			
			catcher_close_extended.setVisible(false);
			catcher_close_extended.setBounds(0, 0, 100, 40);
			this.add(catcher_close_extended);
			
			catcher_open_extended.setVisible(true);
			catcher_open_extended.setBounds(0, 0, 100, 40);
			this.add(catcher_open_extended);
			
			car.setVisible(false);
			car.setBounds(65, 16, 24, 24);
			this.add(car);
			
		} catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	// ----------------------------------------------------------------------
	
	
	public void setExtended(boolean isExtended)
	{
		bar.setVisible(!isExtended);
		bar_extended.setVisible(isExtended);

		if(isExtended)
		{
			this.car.setBounds(65, 16, 24, 24);
			catcher_close_extended.setVisible(!isOpen);
			catcher_open_extended.setVisible(isOpen);
			catcher_close.setVisible(false);
			catcher_open.setVisible(false);
		}
		else
		{
			this.car.setBounds(20, 16, 24, 24);
			catcher_close.setVisible(!isOpen);
			catcher_open.setVisible(isOpen);
			catcher_close_extended.setVisible(false);
			catcher_open_extended.setVisible(false);
		}
		
		this.isExtended = isExtended;
		
	}
	
	
	// ----------------------------------------------------------------------
	
	
	public void setHasCar(boolean hasCar)
	{
		if(!isOpen) return;
		this.car.setVisible(hasCar);
		
	}
	
	public void setOpen(boolean isOpen)
	{
		this.isOpen = isOpen;
		
		
		if(this.isExtended)
		{
			catcher_close_extended.setVisible(!isOpen);
			catcher_open_extended.setVisible(isOpen);
			
			catcher_close.setVisible(false);
			catcher_open.setVisible(false);
		}
		else
		{
			catcher_close.setVisible(!isOpen);
			catcher_open.setVisible(isOpen);
			
			catcher_close_extended.setVisible(false);
			catcher_open_extended.setVisible(false);
		}
	}
}
