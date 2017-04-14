//This object will contain the locations of the ships
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
   
   //This will add a ship to a given set of indices, given an x position, y position, and a
   //ship, s.  This will use the Java Coordinate system. 
   public boolean addShip(int x, int y, Ship s)
   {
      //Determines whether the ship object was sucessfully added.
      boolean added = false;
      
      //This ship object needs to be valid in order to place the ship.
      if(shipIsValid(x, y, s))
      {
         //If the ship's y dimension is 1, it is horizontally aligned,
         //and we proceed across the row.
         if(s.getLengthY()==1)
            for(int i=0; i<s.getLengthX(); i++)
               grid[x+i][y] = s;
         //If the ship's x dimension is 1, it is vertically aligned
         //and we proceed up the column.
         else if(s.getLengthX()==1)
            for(int j=0; j<s.getLengthY(); j++)
               grid[x][y+j] = s;
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
         //The x, and y - indices are within bounds.
         if(0<=x && (x + s.getLengthX()-1)<getX() && 0<=y && y<getY())
         {
            for(int i=0; (x+i)<s.getLengthX(); i++)
               //The square has a ship on it.
               if(grid[x+i][y]!=null)
                  valid = false;
         }
         
         //The ship is out of bounds.
         else
            valid = false;         
      }
               
      //The ship is vertical.
      else if(s.getLengthX()==1)
      {
         //The x and y - coordinates are within bounds.
         if(0<=x && x<getX() && 0<=y && (y + s.getLengthY()-1)<=getY())
         {
            for(int j=0; (y+j)<s.getLengthY(); j++)
               //This square has a ship on it.
               if(grid[x][y+j]!=null)
                  valid = false;
         }
         
         //The x and y - coordinates are out of bounds.
         else
            valid = false;
      }
      
      //The ship is neither horizontal nor vertical.
      else
         valid = false;
      return valid;      
   }                                 
                   
	//This will determine whether a given attack will successfully hit a ship.
	public boolean attack(int x, int y)
	{
		//done determines whether the attack was sucessfully evaluated (error checking)
      //attacked determines whether the ship was attacked (part of the game)
      boolean done = false, attacked = false;
      
      //The attack is within bounds and has not already been carried out.
      if(attemptIsLegal(x, y))
      {
         //The attack is processed successfully.
         done = true;
         //The UI board is updated accordingly.
         if(grid!=null)
            attacked = true;
         if(attacked)
            grid[x][y].decreaseHp();   
         
         ui.update(x, y, attacked);
      }
      return done;          
	}
   
   //This will determine whether the attack is within bounds and not already taken.
   public boolean attemptIsLegal(int x, int y)
   {
      //Determines whether the move is legal.
      boolean legal = false;
      
      //This shot is within bounds.
      if(0<=x && x<getX() && 0<=y && y<getY())
         //There was not already a shot on this square
         if(ui.getShots()[x][y].equals("_"))
            legal = true;
      return legal;     
   }
   
   //Thjs tests the class.
   public String toString()
   {
      String result = "";
      for(int j=0; j<9; j++)
      {
        
         for(int i=-1; i<8; i++)
         {
            //Thjs formats the tjtle.
            if(j==8 && i==-1)
               result += "  ";               
            else if(j==8)
               result += "\t" + (char)(i+65);
            else if(i==-1)
               result += j;
            else
            {         
               if(grid[j][i]!=null)
                  result+= "\ts";
               else
                  result+= "\t_";
            }         
         }
         result +="\n";
      }   
      return result;   
   }
   
   public static void main(String []args)
   {
      ShipGrid sGrid = new ShipGrid();
      System.out.println(sGrid);
      if(sGrid.addShip(0,5,new Ship(1,4)))
         System.out.println("Ship added.");
      System.out.println(sGrid);
      if(sGrid.addShip(8,1,new Ship(8,1)))
         System.out.println("Ship added.");
      System.out.println(sGrid);   
   }                                 
}
