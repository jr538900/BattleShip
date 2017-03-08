//This board will be shown to users.
public class UIBoard extends Board 
{
	//This object will store ints 1, 0, or -1 depending on whether a square has been hit and contains a boat.
	private int[][] grid;	
	
	//This constructor will create a 10x10 "Board" object, and initilizes all grid squares.
	public UIBoard()
	{
		//Creates a 10x10 "Board" object
		super(10,10);
		grid = new int[getLength()][getWidth()];
		
		//Sets all coordinates to the value 0, which corresponds to "not hit yet"
		for(int i=0; i<grid.length; i++)
			for(int j=0; j<grid[i].length; j++)
				grid[i][j] = 0;		
	}
	
	//This method will take a user input and update the square accordingly.
	public void playOn(int x, int y)
	{
		//Converts x positions to array indices.
		x--;
		y--;
		//The x and y inputs need to be within the board boundaries.
		if(x>=0 && x<grid.length && y>=0 && y<grid.length)
		{
			//The UI grid will be updated.
			/*This method will have to be resolved.
			if(ships.hitsShipOn(x, y))
					grid[x][y] = 1;
			else
					grid[x][y] = -1;*/
		}
			
	} 
}
