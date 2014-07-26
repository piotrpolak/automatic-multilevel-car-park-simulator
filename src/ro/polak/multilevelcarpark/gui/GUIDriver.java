package ro.polak.multilevelcarpark.gui;

/*
 * (C) Piotr Polak 2008
 * http://www.polak.ro
 */

/**
 * GUI Driver acts as the simple both side
 * link for the controller and GUI
 * 
 * GUI Driver is implemented as a Thread, it does not
 * block when the controller waits (sleeps)
 * 
 * @author Piotr Polak <piotr@polak.ro>
 *
 */
public class GUIDriver extends Thread {

	
	// ----------------------------------------------------------------------
	
	
	private static MainWindow mainWindow;
	public static GUIMessenger messenger = new GUIMessenger();

	
	// ----------------------------------------------------------------------
	
	
	public GUIDriver(int levels, int placesPerLevel)
	{
		GUIDriver.mainWindow = new MainWindow(levels, placesPerLevel);
		GUIDriver.mainWindow.initComponents();
	}
	
	
	// ----------------------------------------------------------------------
	
	
	public void moveToLevel(int index)
	{
		GUIDriver.mainWindow.placesMap.moveLevel(index);
	}
	
	
	// ----------------------------------------------------------------------
	
	
	public void moveToPosition(int index)
	{
		GUIDriver.mainWindow.placesMap.movePosition(index);
		GUIDriver.mainWindow.armRotationIndicator.movePosition(index);
	}
	
	
	// ----------------------------------------------------------------------
	
	
	public void setPlaceFree(int level, int index, boolean isFree)
	{
		GUIDriver.mainWindow.placesMap.setPlaceFree(level, index, isFree);
	}
	
	
// ----------------------------------------------------------------------
	
	
	public void indicatorArmSetBusy(boolean isBusy)
	{
		GUIDriver.mainWindow.armIndicator.setBusy(isBusy);
		
		if(isBusy)
			GUIDriver.mainWindow.currentActionLabel.setText("STATE: Arm working");
	}
	
	// ----------------------------------------------------------------------
	
	
	public void indicatorCarContainerSetBusy(boolean isBusy)
	{
		GUIDriver.mainWindow.carContainerIndicator.setBusy(isBusy);
		
		if(isBusy)
			GUIDriver.mainWindow.currentActionLabel.setText("STATE: Car container working");
	}
	
	
	// ----------------------------------------------------------------------
	
	
	public void indicatorElevatorSetBusy(boolean isBusy)
	{
		GUIDriver.mainWindow.elevatorIndicator.setBusy(isBusy);
		
		if(isBusy)
			GUIDriver.mainWindow.currentActionLabel.setText("STATE: Elevator working");

	}
	
	
	// ----------------------------------------------------------------------
	
	
	public void indicatorSystemSetBusy(boolean isBusy)
	{
		GUIDriver.mainWindow.systemIndicator.setBusy(isBusy);
		GUIDriver.mainWindow.getCarButton.setEnabled(!isBusy);
		GUIDriver.mainWindow.insertCarButton.setEnabled(!isBusy);
		
		if(!isBusy)
		{
			GUIDriver.mainWindow.currentActionLabel.setText("");
		}
	}
	
	
	// ----------------------------------------------------------------------
	
	
	public void armCatcherHasCar(boolean hasCar)
	{
		GUIDriver.mainWindow.placesMap.armStateIndicator.setHasCar(hasCar);
	}
	

	// ----------------------------------------------------------------------
	
	
	public void armSetIsExtended(boolean isExtended)
	{
		GUIDriver.mainWindow.placesMap.armStateIndicator.setExtended(isExtended);
	}
	
	
	// ----------------------------------------------------------------------
	
	
	public void armSetOpen(boolean isOpen)
	{
		GUIDriver.mainWindow.placesMap.armStateIndicator.setOpen(isOpen);
	}
	
	
	// ----------------------------------------------------------------------
	
	
	public void simpleDialog(String title, String message)
	{
		SimpleDialog dialog = new SimpleDialog(GUIDriver.mainWindow, title, message);
		dialog.setVisible(true);
	}
	
}
