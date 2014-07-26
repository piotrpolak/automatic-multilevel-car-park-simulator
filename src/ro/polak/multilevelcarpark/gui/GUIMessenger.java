package ro.polak.multilevelcarpark.gui;

/*
 * (C) Piotr Polak 2008
 * http://www.polak.ro
 */

/**
 * @author Piotr Polak <piotr@polak.ro>
 *
 */
public class GUIMessenger {

	private int code = -1;
	private String param = null;
	private boolean hasMessage = false;	
	
	public void putMessage(int code, String param)
	{
		this.hasMessage = true;
		this.code = code;
		this.param = param;
	}
	
	
	public boolean hasMessage()
	{
		boolean toRet = hasMessage;
		if(hasMessage) hasMessage = false;
		return toRet;
	}
	
	public int getCode()
	{
		return code;
	}
	
	public String getParam()
	{
		return param;
	}
}
