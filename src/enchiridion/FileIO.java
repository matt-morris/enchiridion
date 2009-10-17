/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package enchiridion;

import java.io.*;
import java.util.*;

/**
 *
 * @author Alex
 */
public class FileIO {
	FileInputStream fis;

	public FileInputStream read (String filepath)
	{
		try
		{
			fis = new FileInputStream(filepath);
		}
		catch (FileNotFoundException e)
		{
			System.out.println("File not found");
		}
		catch (IOException e)
		{
			System.out.println("I/O error");
		}

		return fis;
	}
}
