/**
 * 
 */

/**
 * @author JKB
 *
 */
//Ship.java
//This object will represent the boats that both players are aiming to shoot.
public class Ship
{
	//"xDim" determines the dimension along the x axis (of the board). 
	private int xDim;
	//"yDim" determines the dimension along the y axis (or the board).
	private int yDim;
	//"health" is determined by the number of hits the boat has taken.
	private int health;
	
	//Boat constructor sets the dimensions and health.
	public Ship(int x, int y)
	{
		xDim = x;
		yDim = y;
	   
		//The boat's health will match the longer dimension of the boat
		if(xDim == 1)
			health = yDim;
		else if(yDim == 1)
		   health = xDim;
	}
	
	//Get methods
	public int getXDim()
	{
		return xDim;
	}
	public int getYDim()
	{
		return yDim;
	}
	public int getHealth()
	{
		return health;
	}
	
	//This method will be called when the ship is hit.
	public void isHit()
	{
		health--;
	}
	
	//This method determines whether the boat object is still in the game ("alive").
	public boolean isAlive()
	{
		return health > 0;
	}      
}