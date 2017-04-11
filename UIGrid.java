import java.util.Scanner;

//This board will be shown to users.
public class UIGrid extends Grid 
{
	//This will keep track of the shots taken and the ships on the grid.
	private char[][] shots;
	
   //This constructor will create a 8x8 "Board" object, and initilizes all grid squares.
	public UIGrid(int x, int y)
	{
		shots = new char[getX()][getY()];
      		
		//Sets all coordinates to the value 0, which corresponds to "not hit yet"
		for(int i=0; i<getX(); i++)
			for(int j=0; j<getY(); j++)
				shots[i][j] = '_';		
	}
	
   public char[][] getShots()
   {
      return shots;
   }
   public void setShots(char[][] shots)
   {
      this.shots = shots;
   }
      
	//This method will update the UI grid.
	//This method will be called by the ShipBoard class.
	public void update(int x, int y, boolean hit)
	{
		if(hit)
			shots[x][y] = 'X';
		else
			shots[x][y] = 'O';
	}
	
	//This will print out the board to the user.
	public String toString()
	{
		String result = "\t1 \t2 \t3 \t4 \t5";
        	result += "\n";
		for(int i=0; i<shots.length; i++)
      {
			result += (i+1)+"\t";
			for(int j=0; j<shots[i].length; j++)
			{
				result += shots[i][j];            
			}
      }   
		return result;
	}      
}
