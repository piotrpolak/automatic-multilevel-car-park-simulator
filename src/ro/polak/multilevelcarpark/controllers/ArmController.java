package ro.polak.multilevelcarpark.controllers;

import ro.polak.multilevelcarpark.exceptions.IllegalOperationException;
import ro.polak.multilevelcarpark.generic.*;

/*
 * (C) Piotr Polak 2008
 * http://www.polak.ro
 */

/**
 * ContainerArm is the element responsabile for rotating the
 * CarContainer around the ArmLift axis
 * 
 * @author Piotr Polak <piotr@polak.ro>
 *
 */
public class ArmController extends BusyElement {
	
	
	/**
	 * A CarContainer instance assigned to the ContainerArm.
	 */
	public CarContainerController carContainerController;
	
	
	/**
	 * The current position of the ContainerArm.
	 * The current position is the positive integer:
	 *     - minimal value is 0 (initial position)
	 *     - maximal value is numberOfPlacesPerParkingRing-1
	 */
	private int currentPosition;
	
	
	/**
	 * Boolean value indicating if the arm is extender.
	 * The arm should be extended in order to:
	 *    - place the car on the selected parking ring
	 *    - receive the car from the "get car gate"
	 *    - place the car in the "give car gate"
	 */
	private boolean isExtended;
	
	
	/**
	 * A reference to a parent controller.
	 * Used for checking if the parent controller is free.
	 */
	private ElevatorController parent;
	
		
	/**
	 * The default constructor
	 * Constructs the carContainer, places the arm at the "get car gate"
	 */
	public ArmController(ElevatorController parent)
	{
		this.parent = parent;
		this.carContainerController = new CarContainerController(this);
		this.isExtended = true;     // "Get car gate"
		this.currentPosition = 0;   // "Get car gate"
		this.cost = 500;
	}
	
	
	/**
	 * Rotated arm to the requested position
	 * 
	 * @param position
	 */
	public void rotateToPosition(int position) throws IllegalOperationException
	{
		if(parent.isBusy()) throw new IllegalOperationException("Parent element in use");
		
		CarPark.gui.indicatorArmSetBusy(true);
		
		if(position>currentPosition)
		{
			// rotating
			while(currentPosition!=position)
			{
				// Sleep Thread
				this.makeBusy();
				// Update GUI
				CarPark.gui.moveToPosition(++currentPosition);
			}
		}
		else if(position<currentPosition)
		{
			// rotating
			while(currentPosition!=position)
			{
				// Sleep Thread
				this.makeBusy();
				// Update GUI
				CarPark.gui.moveToPosition(--currentPosition);
			}
		}
		
		CarPark.gui.indicatorArmSetBusy(false);
	}
	
	
	/**
	 * Extends the arm
	 * 
	 * @throws IllegalOperationException
	 */
	public void extend() throws IllegalOperationException
	{
		if(parent.isBusy()) throw new IllegalOperationException("Parent element in use");
		if(this.isExtended) throw new IllegalOperationException("Unable to extend arm");
		
		this.isExtended = true;
		
		this.makeBusy();
		
		CarPark.gui.armSetIsExtended(true);
	}
	
	
	/**
	 * Shrinks the arm
	 * 
	 * @throws IllegalOperationException
	 */
	public void shrink() throws IllegalOperationException
	{
		if(parent.isBusy()) throw new IllegalOperationException("Parent element in use");
		if(!this.isExtended) throw new IllegalOperationException("Unable to shrink arm");
		
		this.isExtended = false;
		
		this.makeBusy();
		
		CarPark.gui.armSetIsExtended(false);
	}
	
	
	/**
	 * Tells whenever extended
	 * 
	 * @return isExtended
	 */
	public boolean isExtended()
	{
		return isExtended;
	}
}
