package ro.polak.multilevelcarpark.generic;

import ro.polak.multilevelcarpark.exceptions.IllegalOperationException;

/*
 * (C) Piotr Polak 2008
 * http://www.polak.ro
 */

/**
 * Representation of parking place.
 * Each parking ring consists of a certain number of parking places
 * 
 * @author Piotr Polak <piotr@polak.ro>
 *
 */
public class ParkingPlace {

	/**
	 * Car assigned to a position
	 */
	private Car car;
	
	
	/**
	 * Places the car.
	 * @param car a car to be placed
	 * @return false on illegal operation
	 */
	public boolean insertCar(Car car) throws IllegalOperationException
	{
		if(car == null) throw new IllegalOperationException("Car is null");
		this.car = car;                    // Assigning car
		return true;                       // Success!
	}
	
	/**
	 * Returns the current car
	 * @return current car
	 */
	public Car getCar()
	{
		return car;
	}
	
	
	/**
	 * Frees the resources
	 */
	public void free()
	{
		this.car = null;
	}
	
	
	/**
	 * Tells thenever the place is free
	 * 
	 * @return true when place is free
	 */
	public boolean isFree()
	{
		return (this.car == null ? true : false);
	}
}
