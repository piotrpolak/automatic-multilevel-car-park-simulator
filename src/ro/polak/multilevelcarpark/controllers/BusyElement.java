package ro.polak.multilevelcarpark.controllers;

/*
 * (C) Piotr Polak 2008
 * http://www.polak.ro
 */

/**
 * Busy element class.
 * Used for indicating the state of blockable elements,
 * Used for solving controllers dependencies.
 * 
 * @author Piotr Polak <piotr@polak.ro>
 *
 */
public abstract class BusyElement {

	
	/**
	 * Boolean indicating the state of the element
	 */
	private boolean isBusy;
	
	
	/**
	 * The cost of the action.
	 * Time delay in millis
	 */
	protected int cost = 500;
	
	
	/**
	 * Makes the element busy (sleeps it) and then automatically frees it.
	 */
	public void makeBusy()
	{
		this.isBusy = true;
		
		try {Thread.sleep(cost);} catch(InterruptedException e) {}
		
		this.makeFree();
	}


	/**
	 * Makes the element free
	 */
	public void makeFree()
	{
		this.isBusy = false;
	}

	
	/**
	 * Tells whenever the element is busy
	 */
	public boolean isBusy()
	{
		return isBusy;
	}
}
