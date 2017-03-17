//This object will contain the locations of the ships
public class ShipBoard extends Board {
	//Each array element will determine whether there is a ship on this coordinate.
	private boolean[][] coord;
	
	//Constructor creates this ShipBoard object and sets the coordinates to false (empty of ships).
	public ShipBoard()
	{
		super(8,8);
		coord = new boolean[getLength()][getWidth()];
		for(int i=0; i<coord.length; i++)
			for(int j=0; j<coord[i].length; j++)
				coord[i][j] = false;
	}
	
	//This will determine whether a given position has a ship on it.
	public boolean hitsShipOn(int x, int y)
	{
		return coord[x][y];		
	}
}
