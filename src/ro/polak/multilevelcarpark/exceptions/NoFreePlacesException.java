package ro.polak.multilevelcarpark.exceptions;

/*
 * (C) Piotr Polak 2008
 * http://www.polak.ro
 */

/**
 *
 * @author Piotr Polak <piotr@polak.ro>
 */
public class NoFreePlacesException extends Exception {
	
	public NoFreePlacesException()
	{
		super("There are no free spaces");
	}

}
