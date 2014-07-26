package ro.polak.multilevelcarpark.exceptions;

/*
 * (C) Piotr Polak 2008
 * http://www.polak.ro
 */

/**
 * 
 * @author Piotr Polak <piotr@polak.ro>
 */
public class IllegalOperationException extends Exception {
	
	public IllegalOperationException()
	{
		super("Illegal operation");
	}
	
	public IllegalOperationException(String message)
	{
		super(message);
	}

}
