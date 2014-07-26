package ro.polak.multilevelcarpark.controllers;

/*
 * (C) Piotr Polak 2008
 * http://www.polak.ro
 */

/**
 * Arm lift is the element responsabile for changing the level
 * of the ContainerArm, ie adjusting the level
 * 
 * @author Piotr Polak <piotr@polak.ro>
 *
 */
public class ElevatorController extends BusyElement {

	
	
		
	/**
	 * A variable indicating the current level of the ContainerArm (lift box).
	 * The default variable is 0 (ground).
	 */
	private int currentLevel = 0;
	
	
	/**
	 * A ContainerArm attached to the ArmLift.
	 */
	public ArmController armController;
	
	
	/**
	 * The default constructor.
	 * Constructs ContainerArm, places the lift at the ground floor.
	 */
	public ElevatorController()
	{
		// Building an instance of arm controller
		this.armController = new ArmController(this);
		// The cost of the action
		this.cost = 1000;
	}
	
	
	/**
	 * Moves elevator to requested level
	 * 
	 * @param level destination level
	 */
	public void moveToLevel(int level)
	{
		
		if(currentLevel == level) return;
		
		
		CarPark.gui.indicatorElevatorSetBusy(true);
		
		if(level>currentLevel)
		{
			while(currentLevel!=level)
			{
				// Sleep Thread
				this.makeBusy();

				CarPark.gui.moveToLevel(++currentLevel);
			}
		}
		else if(level<currentLevel)
		{
			while(currentLevel!=level)
			{
				// Sleep Thread
				this.makeBusy();
				// Update GUI
				CarPark.gui.moveToLevel(--currentLevel);
			}
		}
		
		CarPark.gui.indicatorElevatorSetBusy(false);
		
	}
	
	
	/**
	 * Returns the current level
	 * @return current level
	 */
	public int getCurrentLevel()
	{
		return currentLevel;
	}
	
}
