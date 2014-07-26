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
public class PlacesMap extends JPanel {

	private int levels;
	private int placesPerLevel;
	private ParkingRing pRing[];
	private JLabel levelLabel[];
	private JLabel placeLabel[];
	private JPanel levelicon;
	private JLabel posicon;
	
	protected ArmStateIndicator armStateIndicator;
	
	
	
	// ---- 
	int levelHeight = 30;
	int levelYOffset = 20;
	int levelXOffset = 50;
	int labelWidth = 100;
	
	
	// ----------------------------------------------------------------------
	
	
	public PlacesMap(int levels, int placesPerLevel)
	{
		super();
		this.levels = levels;
		this.placesPerLevel = placesPerLevel;
		this.initComponents();
	}
	
	
	// ----------------------------------------------------------------------
	
	
	public void initComponents()
	{
		
		pRing = new ParkingRing[levels];
		levelLabel = new JLabel[levels];
		placeLabel = new JLabel[placesPerLevel];
		
		try {
			posicon = new Icon("up.gif");
			posicon.setBounds(levelXOffset, levelHeight*levels+12, 100, 100);
			this.add(posicon);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		try {
			levelicon = new JPanel();
			levelicon.setLayout(null);
			levelicon.setBounds(0, 0, 150, 40);
			
			
			armStateIndicator = new ArmStateIndicator();
			armStateIndicator.setBounds(30, 0, 100, 40);
			levelicon.add(armStateIndicator);
			
			Icon icon = new Icon("left.gif");
			icon.setBounds(0, 0, 24, 24);
			levelicon.add(icon);
			
			levelicon.setBounds(levelXOffset+placesPerLevel*30, levelYOffset, 24, 150);
			this.add(levelicon);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		
		
		this.setLayout(null);
		
		/*
		 * Initializing position indexes
		 */
		for(int i =0; i<placesPerLevel; i++)
		{
			this.placeLabel[i] = new JLabel();
			this.placeLabel[i].setText("#"+i);
			this.placeLabel[i].setBounds(levelXOffset+i*30+2, 0, labelWidth, 20);
			this.add(this.placeLabel[i]);
		}
	
		/*
		 * Initializing levels' labels and parking rings
		 */
		for(int i =0; i<levels; i++)
		{
			this.pRing[i] = new ParkingRing(placesPerLevel);
			this.pRing[i].setBounds(levelXOffset, levelHeight*i+levelYOffset, placesPerLevel*30, levelHeight);
			this.levelLabel[i] = new JLabel();
			this.levelLabel[i].setText("Level #"+i);
			this.levelLabel[i].setBounds(0, levelHeight*i+levelYOffset, labelWidth, 20);
			this.add(this.levelLabel[i]);
			this.add(this.pRing[i]);
		}
		
	    this.setVisible(true);
	}
	
	
	// ----------------------------------------------------------------------
	
	
	public void setPlaceFree(int level, int index, boolean isFree)
	{
		if(index>=placesPerLevel || level>=this.levels) return;
		
		this.pRing[level].setPlaceFree(index, isFree);		
	}
	
	
	// ----------------------------------------------------------------------
	
	public int getHeight()
	{
		return levelHeight*levels+levelYOffset+50;
	}
	
	public int getWidth()
	{
		return levelXOffset*placesPerLevel*30+150;
	}
	
	public void movePosition(int index)
	{
		if(index>=placesPerLevel) return;
		
		posicon.setBounds(levelXOffset+30*index, levelHeight*levels+12, 24, 24);
	}
	
	public void moveLevel(int index)
	{
		if(index>=levels) return;
		
		levelicon.setBounds(levelXOffset+placesPerLevel*30, levelYOffset+30*index, 150, 40);
	}
	
}
