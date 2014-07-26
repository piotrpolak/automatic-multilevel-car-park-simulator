package ro.polak.multilevelcarpark.generic;

import ro.polak.multilevelcarpark.controllers.CarPark;

/*
 * (C) Piotr Polak 2008
 * http://www.polak.ro
 */

/**
 * Car park singleton.
 * This is the preffered way to create carpark.
 * It prevents from creating multiple instances of CarPark.
 * 
 * @author Piotr Polak <piotr@polak.ro>
 *
 */
public class CarParkSingleton {

	protected static CarPark carPark = null;
	
	public static CarPark getCarPark()
	{
		if(CarParkSingleton.carPark == null)
			CarParkSingleton.carPark = new CarPark(10, 8);

		return CarParkSingleton.carPark; 
	}
}
