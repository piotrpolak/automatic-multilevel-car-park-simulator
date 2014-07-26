package ro.polak.multilevelcarpark.controllers;

import ro.polak.multilevelcarpark.exceptions.IllegalOperationException;
import ro.polak.multilevelcarpark.generic.*;

/*
 * (C) Piotr Polak 2008
 * http://www.polak.ro
 */

/**
 * CarCointainer is the element placed at the end of CointarnerArm
 * providing a mechanism to load/unload a car into it
 * 
 * @author Piotr Polak <piotr@polak.ro>
 *
 *
 */
public class CarContainerController extends BusyElement {

	
	/**
	 * A boolean indicating if the container is locked
	 * A containter must be locked each time the care is placed in it
	 * A container CAN NOT move if it is not locked
	 */
	private boolean isLocked = false;
	
	
	/**
	 * Object holding the current car of the container
	 */
	private Car car;
	
	
	/**
	 * A reference to a parent controller.
	 * Used for checking if the parent controller is free.
	 */
	private ArmController parent;
	
	
	/**
	 * The default constructor
	 * @param parent
	 */
	public CarContainerController(ArmController parent)
	{
		this.parent = parent;
	}
	

	/**
	 * Locks the container
	 * 
	 * @throws IllegalOperationException
	 */
	public void lock() throws IllegalOperationException
	{
		if(parent.isBusy()) throw new IllegalOperationException("Parent element in use");	
		if(this.isLocked) throw new IllegalOperationException("Unable to lock the container");
		
		CarPark.gui.indicatorCarContainerSetBusy(true);
		
		this.makeBusy();
		
		this.isLocked = true;
		
		CarPark.gui.armSetOpen(false);
		CarPark.gui.indicatorCarContainerSetBusy(false);
	}
	
	
	/**
	 * Unlocks the container
	 * 
	 * @throws IllegalOperationException
	 */
	public void unlock() throws IllegalOperationException
	{
		if(parent.isBusy()) throw new IllegalOperationException("Parent element in use");
		if(!this.isLocked) throw new IllegalOperationException("Unable to unlock the container");
		
		CarPark.gui.indicatorCarContainerSetBusy(true);
		
		this.makeBusy();
		
		this.isLocked = false;
		
		CarPark.gui.armSetOpen(true);
		CarPark.gui.indicatorCarContainerSetBusy(false);
	}
	
	
	/**
	 * Tells whenever the container contains a car
	 */
	public boolean hasCar()
	{
		if(car == null) return false;		
		return true;
	}
	
	
	/**
	 * Puts the car in the container
	 * 
	 * @param car
	 * @throws IllegalOperationException
	 */
	public void putCar(Car car) throws IllegalOperationException
	{
		if(parent.isBusy()) throw new IllegalOperationException("Parent element in use");
		if(car == null) throw new IllegalOperationException("Car is null");
		if(this.hasCar()) throw new IllegalOperationException("Container has already a car");
		
		this.car = car;
		
		CarPark.gui.armCatcherHasCar(true);
	}
	
	
	/**
	 * Drops the car
	 * 
	 * @return car
	 * @throws IllegalOperationException
	 */
	public Car dropCar() throws IllegalOperationException
	{
		if(parent.isBusy()) throw new IllegalOperationException("Parent element in use");
		if(!this.hasCar()) throw new IllegalOperationException("No car in the container");
		if(this.isLocked) throw new IllegalOperationException("Unable to drop the car (LOCKED)");

		CarPark.gui.indicatorCarContainerSetBusy(true);
		
		this.makeBusy();
				
		CarPark.gui.armCatcherHasCar(false);

		CarPark.gui.indicatorCarContainerSetBusy(false);
		return this.car;
	}
	
	
	/**
	 * Frees the resources
	 */
	public void free()
	{
		this.car = null;
	}
}
