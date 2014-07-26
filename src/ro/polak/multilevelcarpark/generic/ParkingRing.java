package ro.polak.multilevelcarpark.generic;

/*
 * (C) Piotr Polak 2008
 * http://www.polak.ro
 */

/**
 * Representation of parking ring
 * 
 * @author Piotr Polak <piotr@polak.ro>
 *
 */
public class ParkingRing {	
	
	
	/**
	 * A vector of parking places
	 */
	public ParkingPlace[] parkingPlaces;
	
	
	/**
	 * Position for gate (if case)
	 * 0 - no gate
	 * 1 - gate at pos 0
	 */
	private int isGate = 0;
	
	
	/**
	 * Creates parking ring of the given capacity
	 * @param capacity capacity of the parking ring
	 */
	public ParkingRing(int capacity)
	{	
		this.parkingPlaces = new ParkingPlace[capacity];
		
		// Initializing parking places
		for(int i=0; i<capacity; i++)
			this.parkingPlaces[i] = new ParkingPlace();
	}
	
	
	/**
	 * The default constructor
	 */
	public ParkingRing()
	{
		this(8);
	}
	
	
	/**
	 * Returns the index of the first found parking place
	 * 
	 * @return -1 if place not found
	 */
	public int getFreeParkingPlace()
	{
		for(int i=this.isGate; i<this.parkingPlaces.length; i++)
		{
			if(this.parkingPlaces[i].isFree())
			{
				return i;
			}
		}
		
		// No parking place
		return -1;
	}
	
	
	/**
	 * This makes the ring "having" the gate as the first place
	 */
	public void makeGate()
	{
		this.isGate = 1;
	}
	
}
