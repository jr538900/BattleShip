//This object will contain the locations of the ships
//Please keep in mind that this code is based on zero-based indexing.
//That is, the specified coordinates should range from 0 to x-1 and
//0 to y-1 for x and y, respectively. Java's default coordinate system 
//is also used here.

public class ShipGrid extends Grid {
	private Ship[][] grid;  //Board that the ships will be placed on
   private UIGrid ui;   //Keeps track of the shots fired.
	
	//Constructor creates this ShipBoard object and sets the coordinates to false (empty of ships).
	public ShipGrid()
	{
		grid = new Ship[getX()][getY()];
      ui = new UIGrid(getX(), getY());		
	}
	public void setGrid(Ship[][] grid)
   {
      this.grid = grid;
   }
   public void setUI(UIGrid ui)
   {
      this.ui = ui;
   }
   public Ship[][] getGrid()
   {
      return grid;
   }
   public UIGrid getUI()
   {
      return ui;
   }  
   
   public boolean addShip(int x, int y, Ship s)
   {
      //Determines whether the ship object was sucessfully added.
      boolean added = false;
      
      //This ship object needs to be valid in order to place the ship.
      if(shipIsValid(x, y, s))
      {
         //If the ship's y dimension is 1, it is horizontally aligned
         //and we proceed across the row.
         if(s.getLengthY()==1)
            for(int i=0; i<s.getLengthX(); i++)
               grid[x+i][y] = s;
         //If the ship's x dimension is 1, it is vertically aligned
         //and we proceed up the column.
         else if(s.getLengthX()==1)
            for(int j=0; j<s.getLengthY(); j++)
               grid[x][y-j] = s;
         //ship was added successfully.
         added = true;      
      }
      
      //ship was not added successfully.
      else
         added = false;
      return added;
   }
   
   //This method will determine whether the ship is within bounds and is placed on only empty ships.
   public boolean shipIsValid(int x, int y, Ship s)
   {
      boolean valid = true;
           
      //The ship is horizontal.
      if(s.getLengthY()==1)
      {
         //The x, and y - coordinates are within bounds.
    	   if(y>=0 && y<getY() && x>=0 && x + s.getLengthX()<=getX())
         {
            for(int i=0; i<s.getLengthX(); i++){
               //The square has a ship on it.
               if(grid[x+i][y]!=null){
                  valid = false;
               }
            }
         }
         
         //The ship is out of bounds.
         else
            valid = false;         
      }
               
      //The ship is vertical.
      else if(s.getLengthX()==1)
      {
         //The x and y - coordinates are within bounds.
         if(x>=0 && x<getX() && y<=getY() && y - (s.getLengthY()-1)>=0)
         {
            for(int j=0; j<s.getLengthY(); j++)
               //This square has a ship on it.
               if(grid[x][y-j]!=null)
                  valid = false;
         }
         
         //The x and y - coordinates are out of bounds.
         else
            valid = false;
      }
      return valid;      
   }                                 
                   
	//This will determine whether a given attack will successfully hit a ship.
	public boolean attack(int x, int y)
	{
      //attacked determines whether the ship was attacked successfully.
		boolean attacked = false;
      
      //The attack is within bounds and has not already been carried out.
      if(attemptIsLegal(x, y))
      {
         //The UI board is updated accordingly.
         if(grid[x][y]!=null){
            attacked = true;
            grid[x][y].decreaseHp();
         }
         ui.update(x, y, attacked);
      }
      if(ui.usedUpShot(x, y)){
         attacked = false;
      }   
      return attacked;          
	}
   
   //This will determine whether the attack is within bounds and not already taken.
   public boolean attemptIsLegal(int x, int y)
   {
      //Determines whether the move is legal.
      boolean legal = false;
      
      //This shot is within bounds.
      if(x>=0 && x<getX() && y>=0 && y<getY())
         //There was not already a shot on this square
         if(ui.getShots()[x][y].equals("_"))
            legal = true;
      return legal;     
   }
   
   //This tests the class.
   //The code is based on zero based indexing, but the output is more "user-friendly".
   public String toString()
   {
      String result = "";
      //This prints the coordinates of the grid in reading order.
      for(int j=0; j<=getY(); j++)
      {
         //We vary x while keeping y temporarily constant.
         for(int i=-1; i<getX(); i++)
         {
            //Formats the labels of the grid.
            if(i==-1 && j==getY())
               result += "\n \t";
            else if(i==-1)
               result += (getY()-j) + "\t";
            else if(j==getY())
               result += (char)(i+65) + "\t";
            //Formats the coordinates themselves.   
            else
            {         
               if(grid[i][j] != null)
                  result += "s\t";
               else
                  result += "_\t";
            }      
         }
         result += "\n";        
      }
      return result;                              
   }
   
   public static void main(String []args)
   {
      ShipGrid sGrid = new ShipGrid();
      if(!sGrid.addShip(2,3, new Ship(1,4)))
         System.out.println("Error");
        
      if(!sGrid.addShip(1, 3,  new Ship(4,1)))
    	  System.out.println("Error");
      
      System.out.println(sGrid);   
   }                                 
}
