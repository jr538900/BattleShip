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
	public static void showBoard(int[][] board){
        // giving 8 tabs and a new line for proper allignment when printing 8x8 board
        System.out.println("\t1 \t2 \t3 \t4 \t5 \t6 \t7 \t8");
        System.out.println();

        for(int row=0 ; row < 8 ; row++ ){
            System.out.print((row+1)+"");
            for(int column=0 ; column < 8 ; column++ ){
                if(board[row][column]==-1){
                    // this is just water, no ship, no attempt
                    System.out.print("\t"+"~");
                }else if(board[row][column]==0){
                    // this is the position where shots have been fired previously
                    System.out.print("\t"+"*");
                }else if(board[row][column]==1){
                    // this is the position where, a ship/part of a ship, is present
                    System.out.print("\t"+"X");
                }

            }
            System.out.println();
        }

    }
}
