package ro.polak.multilevelcarpark.gui;

import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/*
 * (C) Piotr Polak 2008
 * http://www.polak.ro
 */

/**
* @author Piotr Polak <piotr@polak.ro>
*
*/
public class Icon extends JLabel {

	public Icon(String path, String text) throws Exception
	{
		super(new ImageIcon(ImageIO.read(new File(path)), text));
	}
	
	public Icon(String path) throws Exception
	{
		this(path, "");
	}
}
