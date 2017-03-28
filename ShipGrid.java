//This object will contain the locations of the ships
public class ShipGrid extends Grid {
	private Ship[][] grid;  //Board that the ships will be placed on
   private UIGrid ui;   //Keeps track of the shots fired.
	
	//Constructor creates this ShipBoard object and sets the coordinates to false (empty of ships).
	public ShipGrid()
	{
		super(8,8);
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
               grid[i][y] = s;
         //If the ship's x dimension is 1, it is vertically aligned
         //and we proceed up the column.
         else if(s.getLengthX()==1)
            for(int j=0; j<s.getLengthY(); j++)
               grid[x][j] = s;
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
         //The x, and y - coordinates is within bounds.
         if(y>=0 && y<getY() && x>=0 && x + s.getLengthX()<=getX())
         {
            for(int i=0; i<s.getLengthX(); i++)
               //The square has a ship on it.
               if(grid[i][y]!=null)
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
         if(x>=0 && x<getX() && y>=0 && y + s.getLengthY()<=getY())
         {
            for(int j=0; j<s.getLengthY(); j++)
               //This square has a ship on it.
               if(grid[x][j]!=null)
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
		//done determines whether the attack was sucessfully evaluated
      //attacked determines whether the ship was attacked.
      boolean done = false, attacked = false;
      
      //The attack is within bounds and has not already been carried out.
      if(attemptIsLegal(x, y))
      {
         //The attack is processed successfully.
         done = true;
         //The UI board is updated accordingly.
         if(grid!=null)
            attacked = true;
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
      if(x>=0 && x<getX() && y>=0 && y<getY())
         //There was not already a shot on this square
         if(ui.getShots()[x][y].equals('_'))
            legal = true;
      return legal;     
   }         
}
