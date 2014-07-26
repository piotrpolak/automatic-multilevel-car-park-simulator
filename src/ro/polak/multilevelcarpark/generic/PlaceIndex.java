package ro.polak.multilevelcarpark.generic;

/*
 * (C) Piotr Polak 2008
 * http://www.polak.ro
 */

/**
 * @author Piotr Polak <piotr@polak.ro>
 *
 */
public class PlaceIndex {

	public int level = -1;
	public int index = -1;
	
	public PlaceIndex()
	{

	}
	
	public PlaceIndex(int level, int index)
	{
		this.level = level;
		this.index = index;
	}
}
