package ro.polak.multilevelcarpark.generic;

/*
 * (C) Piotr Polak 2008
 * http://www.polak.ro
 */

/**
 * Car representation
 * 
 * @author Piotr Polak <piotr@polak.ro>
 *
 */
public class Car {

	public String carID;
	
	/**
	 * Builds a car
	 * 
	 * @param carID car plates, whatever
	 */
	public Car(String carID)
	{
		this.carID = carID;
	}
}
