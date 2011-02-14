/**
 * @author Maclir
 * 
 */
public class Maze
{
	private boolean [][] map;
	private boolean [][] mapVisited;
	
	public void setMapVisited (int x, int y)
	{
		this.mapVisited[x][y] = true;
	}

	public int getMapSizeX ()
	{
		return map.length;
	}
	
	public int getMapSizeY ()
	{
		return map[0].length;
	}

	public boolean checkMapAv (int x, int y)
	{
		if (!((0 <= x && x < getMapSizeX()) && (0 <= y && y < getMapSizeY())))
			return false;
		
		return !(map[x][y] || mapVisited[x][y]);
	}

	public void setMap (boolean [][] map)
	{
		this.map = map;
		this.mapVisited = new boolean[map.length][map[0].length];
	}

	public boolean [][] getMap ()
	{
		return map;
	}
}