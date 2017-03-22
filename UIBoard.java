import java.util.Scanner;

//This board will be shown to users.
public class UIBoard extends Board 
{
	//This object will store ints 1, 0, or -1 depending on whether a square has been hit and contains a boat.
	private int[][] grid;
	private int[] shoot;
	
	//This constructor will create a 8x8 "Board" object, and initilizes all grid squares.
	//The project instruction request to set a 8x8 "Board" instead of 10x10
	public UIBoard()
	{
		//Creates a 8x8 "Board" object
		super(8,8);
		grid = new int[getLength()][getWidth()];
		
		//Sets all coordinates to the value 0, which corresponds to "not hit yet"
		for(int i=0; i<grid.length; i++)
			for(int j=0; j<grid[i].length; j++)
				grid[i][j] = 0;		
	}
	
	//This method will update the UI grid.
	//This method will be called by the ShipBoard class.
	public void update(int x, int y, boolean hit)
	{
		if(hit)
			grid[x][y] = 1;
		else
			grid[x][y] = -1;
	}
	
	//This method will take in the user-defined corner coordinates, length, and direction.
	public boolean isWithinBounds(int cornerX, int cornerY, int length, String dir)
	{
		boolean valid = false;
		if(dir.equals("up"))
			if(cornerX>=1 && cornerY>=1 && cornerX <= grid.length && cornerY + length <= grid[0].length)
				valid = true;
			
		else if(dir.equals("right"))
			if(cornerX>=1 && cornerY>=1 && cornerX + length <= grid.length && cornerY <= grid[0].length)
				valid = true;
		return valid;
	}
	
	//This method can get the coordinate that user inputed
	public static void shoot(int[] shoot){
		Scanner kb = new Scanner(System.in);
		System.out.print("Please enter row:");
		shoot[0] = kb.nextInt();
		System.out.print("Please enter column:");
		shoot[1] = kb.nextInt();
	}
	
	//The hit method will determine if the boat or grid has been hit
	public boolean hit (int[] shoot, Ship ship){
		if (shoot[0]==ship.getX() && shoot[1]==ship.getY()){
			return true;
			if (grid[shoot[0]][shoot[1]]==1)
				return false;
		}
		else
			return false;
	}
	
	//This will print out the board to the user.
	public String toString()
	{
		System.out.println("\t1 \t2 \t3 \t4 \t5");
        	System.out.println();
		for(int i=0; i<grid.length; i++)
			System.out.print((row+1)+"\t");
			for(int j=0; j<grid[i].length; j++)
			{
				//The square has been hit and has no ship.
				if(grid[i][j] == -1)
					result += "*";
				//This square has been hit and contains a ship.
				else if(grid[i][j] == 1)
					result += "X";
				//This square has been missed.
				else
					result += "_";
			}
		return result;
	}
}
