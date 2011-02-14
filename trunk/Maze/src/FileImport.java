import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author Maclir
 * 
 */
public class FileImport
{
	/**
	 * Returns a string of the file location after browsing with GUI
	 * 
	 * @param text
	 * @return
	 */
	public static String fileChooser (String text) // Choose file for checking
	{
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("."));
		FileFilter filter = new FileNameExtensionFilter("*.txt", "txt");
		chooser.addChoosableFileFilter(filter);
		chooser.setDialogTitle("Select the " + text + " file . . . ");
		// disable the "All files" option.
		chooser.setAcceptAllFileFilterUsed(false);

		int ret = chooser.showDialog(null, "Use file");

		if (ret == JFileChooser.APPROVE_OPTION)
		{
			try
			{
				return chooser.getSelectedFile().getCanonicalPath().toString();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}

		return "M1-Mazetest.txt"; // Default file name

	}

	/**
	 * Using paths chosen with fileChooser
	 * 
	 * @param textPath
	 * @return
	 */
	public static boolean[][] readFile (String textPath)
	{
		File openFile = new File(textPath);
		String line = null;
		
		ArrayList <String> lines = new ArrayList <String>(); 
		boolean [][] maze = null;
		String [][] mazeString = null;
		
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader(openFile));
			while ((line = reader.readLine()) != null)
			{
				// create ArrayList with each Book using Book Class
				lines.add(line);
			}
			
			maze = new boolean[lines.size()][lines.get(0).length()];
			mazeString = new String[lines.size()][lines.get(0).length()];
			
			for (int i = 0; i < lines.size(); i++)
				mazeString[i] = lines.get(i).split("");
			
			for (int i = 0; i < maze.length; i++)
				for (int j = 0; j < maze[0].length; j++)
					if (mazeString[i][j].equals("1"))
						maze[i][j] = true;
					else if (mazeString[i][j].equals("0"))
						maze[i][j] = false;
			
			return maze;
			
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
		maze = null;
		return maze;
	}
}
