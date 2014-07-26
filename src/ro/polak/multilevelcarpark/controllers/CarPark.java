package ro.polak.multilevelcarpark.controllers;

import ro.polak.multilevelcarpark.exceptions.IllegalOperationException;
import ro.polak.multilevelcarpark.exceptions.NoFreePlacesException;
import ro.polak.multilevelcarpark.exceptions.CarNotFoundException;
import ro.polak.multilevelcarpark.generic.Car;
import ro.polak.multilevelcarpark.generic.CarParkSingleton;
import ro.polak.multilevelcarpark.generic.ParkingRing;
import ro.polak.multilevelcarpark.generic.PlaceIndex;
import ro.polak.multilevelcarpark.gui.GUIDriver;

/*
 * (C) Piotr Polak 2008
 * http://www.polak.ro
 */

/**
 * The controller for the whole parking,
 * contains other controllers
 *
 * @author Piotr Polak <piotr@polak.ro>
 */
public class CarPark extends Thread {

	
	/**
	 * Parking rings, there is one ring per level, each one holding a certain number of cars
	 */
	private ParkingRing parkingRings[];
	
	
	/**
	 * Boolean indicating if the car park is idle
	 */
	private boolean isIdle = true;
	
	
	/**
	 * Building elevator controller
	 * The element moving vertically
	 */
	private ElevatorController elevatorController = new ElevatorController();
	
	
	/**
	 * Gui driver for communicating with the GUI
	 * (Separate threads)
	 */
	public static GUIDriver gui;
	
	
	/**
	 * Default contstructor
	 * 
	 * @param levels
	 * @param placesPerRing
	 */
	public CarPark(int levels, int placesPerRing)
	{
		
		this.parkingRings = new ParkingRing[levels];
		
		CarPark.gui = new GUIDriver(levels, placesPerRing);
		
		/* Building rings */
		for(int i=0; i<levels; i++)
		{
			this.parkingRings[i] = new ParkingRing(placesPerRing);
			
			if(i==0)
				this.parkingRings[i].makeGate();
		}
		
		// Starting the thread
		this.start();
	}
		
	
	/**
	 * Finds and returns requested car
	 * 
	 * @param carID
	 * @return
	 * @throws CarNotFoundException
	 * @throws IllegalOperationException
	 */
	public Car getCar(String carID) throws CarNotFoundException, IllegalOperationException
	{
		PlaceIndex placeIndex = this.getCarPlaceIndex(carID);
		
		
		// Changing GUI indicator
		CarPark.gui.indicatorSystemSetBusy(true);
		
		
		this.elevatorController.armController.shrink();
		this.elevatorController.moveToLevel(placeIndex.level);
		this.elevatorController.armController.rotateToPosition(placeIndex.index);
		this.elevatorController.armController.extend();
		//this.elevatorController.armController.carContainerController.unlock();
		
		// Gets car
		this.elevatorController.armController.carContainerController.putCar(parkingRings[placeIndex.level].parkingPlaces[placeIndex.index].getCar());
		parkingRings[placeIndex.level].parkingPlaces[placeIndex.index].free();
		this.elevatorController.armController.carContainerController.lock();
		
		
		
		// Updating GUI
		CarPark.gui.setPlaceFree(placeIndex.level, placeIndex.index, true);

		this.elevatorController.armController.shrink();
		this.elevatorController.armController.rotateToPosition(0);
		this.elevatorController.moveToLevel(0);
		this.elevatorController.armController.extend();
		this.elevatorController.armController.carContainerController.unlock();
		this.elevatorController.armController.carContainerController.dropCar();
		this.elevatorController.armController.carContainerController.free();
		
		// Updating GUI
		CarPark.gui.indicatorSystemSetBusy(false);
		
		// Action for bringing the car
		return null;
	}
	
	
	/**
	 * Finds the car and return its index
	 * @param carID
	 * @return PlaceIndex
	 * @throws CarNotFoundException
	 */
	private PlaceIndex getCarPlaceIndex(String carID) throws CarNotFoundException
	{
		for(int level=0; level<parkingRings.length; level++)
		{
			for(int index=0; index<parkingRings[level].parkingPlaces.length; index++)
			{
				if(!parkingRings[level].parkingPlaces[index].isFree() && carID.equals(parkingRings[level].parkingPlaces[index].getCar().carID))
				{
					return new PlaceIndex(level, index);
				}
			}
		}
		
		// The car was not found
		throw new CarNotFoundException();
	}

	
	/**
	 * Puts the car on the parking place
	 * 
	 * @param car
	 */
	public void insertCar(String carID) throws NoFreePlacesException, IllegalOperationException
	{
		boolean carAlreadyExists = false;
		
		try
		{
			this.getCarPlaceIndex(carID);
			carAlreadyExists = true;
		}
		catch(CarNotFoundException e)
		{
			carAlreadyExists = false;
		}
		
		if(carAlreadyExists) throw new IllegalOperationException("Car already exists!");
		
		int freePlaceIndex;		

		// Building a car
		Car car = new Car(carID);
		
		// For each ding
		for(int level=0; level<parkingRings.length; level++)
		{
			// Finds the first free parking place on the selected level
			freePlaceIndex = parkingRings[level].getFreeParkingPlace();
			
			if(freePlaceIndex != -1)
			{
				
				/**
				 * All these actions must be performed in a special sequentional order
				 * Each operation can throw an exception when the dependency of actions order is not kept.
				 */
				
				// Changing GUI indicator
				CarPark.gui.indicatorSystemSetBusy(true);
				
				this.elevatorController.armController.carContainerController.putCar(car);
				this.elevatorController.armController.carContainerController.lock();
				this.elevatorController.armController.shrink();
				this.elevatorController.moveToLevel(level);
				this.elevatorController.armController.rotateToPosition(freePlaceIndex);
				this.elevatorController.armController.extend();
				this.elevatorController.armController.carContainerController.unlock();
				
				// Inserting car
				parkingRings[level].parkingPlaces[freePlaceIndex].insertCar(this.elevatorController.armController.carContainerController.dropCar());
				
				this.elevatorController.armController.carContainerController.free();
				
				// Updating GUI
				CarPark.gui.setPlaceFree(level, freePlaceIndex, false);

				this.elevatorController.armController.shrink();
				this.elevatorController.armController.rotateToPosition(0);
				this.elevatorController.moveToLevel(0);
				this.elevatorController.armController.extend();
				
				// Updating GUI
				CarPark.gui.indicatorSystemSetBusy(false);
				
				return;
			}
		}
		
		CarPark.gui.indicatorSystemSetBusy(false);
		
		// no, there is no free space
		throw new NoFreePlacesException();
	}
	

	/**
	 * The main method of the thread
	 * It loops waiting for messages from GUI
	 */
	public void run()
	{
		while(true)
		{
			this.isIdle = true;
			
			// While it does not have a message
			while(!GUIDriver.messenger.hasMessage())
			{
				try
				{
					// Sleeps 100 millis so that we don't "eat" the CPU
					// The delay is insignificant
					Thread.sleep(100);
				}
				catch(InterruptedException e)
				{
					e.printStackTrace();
				}
			}
			
			/*
			 * Action for inserting the car
			 */
			if(GUIDriver.messenger.getCode() == 1)
			{
				this.isIdle = false;

				try
				{
					this.insertCar(GUIDriver.messenger.getParam());
				}
				
				// Displaying errors
				catch(IllegalOperationException e){gui.simpleDialog("System Illegal Operation", "ERROR: "+e.getMessage());}
				catch(NoFreePlacesException e){gui.simpleDialog("No free spaces", "All the places are occupied");}
				
				
			}
			
			/*
			 * Action for geting the car
			 */
			else if(GUIDriver.messenger.getCode() == 2)
			{
				this.isIdle = false;
				
				try
				{
					this.getCar(GUIDriver.messenger.getParam());
				}
				
				// Displaying errors
				catch(IllegalOperationException e){gui.simpleDialog("System Illegal Operation", e.getMessage());}
				catch(CarNotFoundException e){gui.simpleDialog("404 Car Not Found", "There is no such car in the park");}
				
				
			}
		}
	}
	
	
	/**
	 * The main method running the park
	 * @param args
	 */
	public static void main(String args[])
	{
		CarParkSingleton.getCarPark();
	}
	
}
