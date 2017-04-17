//Class where the AI will play it"s turn
import java.util.Random;
public class AI extends User {
	
	Random r = new Random();
	private int guessX;
	private int guessY;
	private String guess;
	private boolean hasHit;
	private int hasDirection;
	private int hitX;
	private int hitY;
	private int guessDirection;
	private int directionGuess;
	private ShipGrid sGrid;
	private Grid gGrid;
	private Ship[] myShips;
	private boolean lostGame;
	//Constructor
	public AI(){
		gGrid= new Grid();
		sGrid = new ShipGrid();
		lostGame = false;
		myShips = new Ship[3];
		hasHit = false;
		hasDirection = 0;
		guessDirection = 0;
		directionGuess = 1;
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
		
		//If it hasn"t hit a ship before it hits a random square
		if(hasHit == false) {
			//If that location has already been guessed, find a new location
			boolean alreadyHit = true;
			while(alreadyHit == true) {
				//Finds if the location has already been hit. If yes then find a new location.
				if(b.getsGrid().getUI().getShots()[guessX][guessY] != "~") {
					guessX = r.nextInt(8) + 1;
					guessY = r.nextInt(8) + 1;
				}
				else {
					alreadyHit = false;
				}
			}
			b.getsGrid().attack(guessX, guessY);
		}
		
		//If it has hit a ship before and has found the direction it is facing
		else if(hasHit == true && hasDirection > 0) {
			if(hasDirection == 1) {
				//Hits until moving one block at a time until it misses
				b.getsGrid().attack(hitX + directionGuess, hitY);
				//If it misses it goes back to it"s starting point and goes the opposite direction until game end
				if(b.getsGrid().getUI().getShots()[hitX + directionGuess][hitY] == "O") {
					directionGuess = 0;
					hasDirection = 3;
				}
				directionGuess++;
			}
			else if(hasDirection == 2) {
				b.getsGrid().attack(hitX, hitY - directionGuess);
				if(b.getsGrid().getUI().getShots()[hitX][hitY - directionGuess] == "O") {
					directionGuess = 0;
					hasDirection = 4;
				}
				directionGuess++;
			}
			else if(hasDirection == 3) {
				b.getsGrid().attack(hitX - directionGuess, hitY);
				if(b.getsGrid().getUI().getShots()[hitX - directionGuess][hitY] == "O") {
					directionGuess = 0;
					hasDirection = 1;
				}
				directionGuess++;
			}
			else if(hasDirection == 4) {
				b.getsGrid().attack(hitX, hitY + directionGuess);
				if(b.getsGrid().getUI().getShots()[hitX][hitY + directionGuess] == "O") {
					directionGuess = 0;
					hasDirection = 2;
				}
				directionGuess++;
			}
		}
		
		//If it has only hit a ship and needs to find the direction the ship is facing
		//1 - right, 2 - down, 3 - left, 4 - up
		else {
			guessDirection += 1;
			 if(guessDirection == 1) {
				 b.getsGrid().attack(hitX + 1, hitY);
				 if (b.getsGrid().getUI().getShots()[hitX + 1][hitY] == "X") {
			 		hasDirection = guessDirection;
			 	}
			 }
			 
			 else if(guessDirection == 2) {
				 b.getsGrid().attack(hitX, hitY - 1);
				 if (b.getsGrid().getUI().getShots()[hitX][hitY - 1] == "X") {
			 		hasDirection = guessDirection;
			 	}
			 }
			 
			 else if(guessDirection == 3) {
				 b.getsGrid().attack(hitX - 1, hitY);
				 if (b.getsGrid().getUI().getShots()[hitX - 1][hitY] == "X") {
			 		hasDirection = guessDirection;
			 	}
			 }
			 
			 else if(guessDirection == 4) {
				 b.getsGrid().attack(hitX, hitY + 1);
				 if (b.getsGrid().getUI().getShots()[hitX][hitY + 1] == "X") {
			 		hasDirection = guessDirection;
			 	}
			}
		}
		
		//Remembers the first location it hit and remembers that it has hit a ship at some point
		if(b.getsGrid().getUI().getShots()[guessX][guessY] == "X" && hasHit == false) {
			hasHit = true;
			hitX = guessX;
			hitY = guessY;
		}
	}
}
