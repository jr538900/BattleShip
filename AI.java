//Class where the AI will play it's turn
import java.util.Random;
import java.util.Scanner;
public class AI extends User {
	
	Random r = new Random();
	private int guessX;
	private int guessY;
	private String guess;
	private boolean hasHit;
	private int lastHitX;
	private int lastHitY;
	private int hasDirection;
	private int hitX;
	private int hitY;
	private int guessDirection;
	private ShipGrid sGrid;
	private Grid gGrid;
	private Ship[] myShips;
	private boolean lostGame;
	//Constructor
	public AI(){
		gGrid= new Grid(8,8);
		sGrid = new ShipGrid();
		lostGame = false;
		myShips = new Ship[3];
		hasHit = false;
		hasDirection = 0;
		guessDirection = 0;
	}
	public String getGuess() {
		return guess;
	}

	public void setGuess(String guess) {
		this.guess = guess;
	}

	public ShipGrid getsGrid() {
		return sGrid;
	}

	public void setsGrid(ShipGrid sGrid) {
		this.sGrid = sGrid;
	}

	public Grid getgGrid() {
		return gGrid;
	}

	public void setgGrid(Grid gGrid) {
		this.gGrid = gGrid;
	}

	public Ship[] getMyShips() {
		return myShips;
	}

	public void setMyShips(Ship[] myShips) {
		this.myShips = myShips;
	}

	public boolean isLostGame() {
		return lostGame;
	}

	public void setLostGame(boolean lostGame) {
		this.lostGame = lostGame;
	}
	public void makeShip(){
		boolean placed;
		int x=0,y=0;
		String d;
		if((int)(Math.random()*2)>0){
			d="x";
		}
		else{
			d="y";
		}
		int i=0;
		while(i<3){
			placed=false;
			myShips[i] = shipType(d,i);
			if(myShips[i] == null){}
			else{
				while(!placed){
					x=r.nextInt(8)+1;
					y=r.nextInt(8)+1;
					if(sGrid.addShip(x, y, myShips[i])){
						placed=true;
						i++;
					}
					else{
						System.out.println("Coordinates not Valid");
					}
				}
			}
		}
	}
	//AI makes a guess depending on previous turns
	public void makeGuess(User b) {
		guessX = r.nextInt(8) + 1;
		guessY = r.nextInt(8) + 1;
		boolean notShot=true;
		while(notShot){
			if(b.getsGrid().attack(guessX, guessY)){
				
			}
			else{
				guessX = r.nextInt(8) + 1;
				guessY = r.nextInt(8) + 1;
			}
		}
		b.getsGrid().attack(guessX, guessY);
		
		/* To be implemented
		
		//If it hasn't hit a ship before it hits a random square
		if(hasHit == false) {
			attack(guessX, guessY)
		}
		
		//If it has hit a ship before and has found the direction it is facing
		else if(hasHit == true && hasDirection > 0) {
			hit in the in the direction of hasDirection
			if(hit nothing on the previous turn) {
				return to the first hit location
				hit in the opposite direction until game end
			}
		}
		
		//If it has only hit a ship and needs to find the direction the ship is facing
		//1 - right, 2 - down, 3 - left, 4 - up
		else if(hasHit == true) {
			guessDirection += 1;
			 
			 if(guessDirection == 1) {
			 	attack(hitX + 1, hitY);
			 }
			 
			 else if(guessDirection == 2) {
			 	attack(hitX, hitY - 1);
			 }
			 
			 else if(guessDirection == 3) {
			 	attack(hitX - 1, hitY);
			 }
			 
			 else if(guessDirection == 4) {
				attack(hitX, hitY + 1);
			}
			
			if(hit in that direction hits a ship) {
				hasDirection = guessDirection;
			}
		}
		
		//If the location it hit on the last turn was a ship
		//Remembers the first location it hit and remembers that it has hit a ship at some point
		if(the location it last guessed was a hit && hasHit == false) {
			hasHit = true;
			hitX = guessX;
			hitY = guessY;
		}
	*/
	}
}
