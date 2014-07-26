package ro.polak.multilevelcarpark.exceptions;

/*
 * (C) Piotr Polak 2008
 * http://www.polak.ro
 */

/**
 * 
 * @author Piotr Polak
 */
public class CarNotFoundException extends Exception {
	
	public CarNotFoundException()
	{
		super("No such car");
	}

}
