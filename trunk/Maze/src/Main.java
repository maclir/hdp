import java.awt.Point;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JOptionPane;

/**
 * @author Maclir
 * 
 */
public class Main
{
	private static Maze maze = new Maze();

	public static void main (String args[])
	{
		String filePath;
		boolean [][] mazeMap = null;

		// asking for the maze file until a correct file has been submitted
		while (mazeMap == null)
		{
			filePath = FileImport.fileChooser("maze");
			mazeMap = FileImport.readFile(filePath);
		}

		// setting the map of the maze and starting and ending position in the
		// maze instance
		maze.setMap(mazeMap);

		// calculating the route
		ArrayList<String> answer = findAnswer();

		boolean cont = true;

		while (cont)
		{
			for (int i = 0; i < answer.size(); i++)
				System.out.println((i + 1) + ": " + answer.get(i));
			
			answer = findAnswer();
			
			cont = askContinue();
		}

	}

	private static ArrayList<String> findAnswer ()
	{
		int x = 0;
		int y = 0;
		Point last;
		Stack<Point> answer = new Stack<Point>();
		ArrayList<String> answerList = new ArrayList<String>();

		while (x != 5 || y != 5)
		{
			maze.setMapVisited(x, y);

			if (maze.checkMapAv(x + 1, y))
			{
				answer.push(new Point(x + 1, y));
				answerList.add("Go East to " + (x + 1) + ", " + y);
				x++;
			} else if (maze.checkMapAv(x, y + 1))
			{
				answer.push(new Point(x, y + 1));
				answerList.add("Go South to " + x + ", " + (y + 1));
				y++;
			} else if (maze.checkMapAv(x - 1, y))
			{
				answer.push(new Point(x - 1, y));
				answerList.add("Go West to " + (x - 1) + ", " + y);
				x--;
			} else if (maze.checkMapAv(x, y - 1))
			{
				answer.push(new Point(x, y - 1));
				answerList.add("Go South to " + x + ", " + (y - 1));
				y--;
			} else if (answer.isEmpty())
			{
				// no possible answer.
				answerList.clear();
				answerList.add("no possible solution");
				return answerList;
			} else
			{
				// reached a dead-end go back a step
				last = answer.pop();
				answerList.remove(answerList.size() - 1);

				x = last.x;
				y = last.y;
			}
		}

		return answerList;
	}

	private static boolean askContinue ()
	{
		boolean answer = false;
		String [] options = { "Yes", "No" };

		int response = JOptionPane.showOptionDialog(null, "Do you want to get another route options?" // Message
				, "Another route" // Title
				, JOptionPane.YES_NO_OPTION // Option type
				, JOptionPane.PLAIN_MESSAGE // messageType
				, null // Icon (none)
				, options // Buttons text from array
				, "No" // Default button label
		);
		switch (response)
		{
			case 0:
				answer = true;
				break;
			case 1:
				answer = false;
				break;
			case -1:
				break;
			default:
				// Unforeseen problems (won't end up here usually)
				JOptionPane.showMessageDialog(null, "Unexpected response " + response);
		}
		return answer;
	}
}