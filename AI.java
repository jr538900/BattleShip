//Class where the AI will play it"s turn
import java.util.Random;
import java.util.ArrayList;
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
	private boolean lostGame;
	private Ship[] myShips;
	private ArrayList<AIshot> shots;
	//Constructor
	public AI(){
		shots = new ArrayList<AIshot>();
		lostGame = false;
		hasHit = false;
		hasDirection = 0;
		guessDirection = 0;
		directionGuess = 1;
		myShips = new Ship[3];
		
	}
   

	
   public String getGuess() {
		return guess;
	}

	public void setGuess(String guess) {
		this.guess = guess;
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
      int i=0;
		while(i<3){
		   if((int)(Math.random()*2)>0){
		   	d="x";
		   }
		   else{
		   	d="y";
		   }
		
			placed=false;
			myShips[i] = shipType(d,i);
			if(myShips[i] == null){}
			else{
				while(!placed){
					x=r.nextInt(8);
					y=r.nextInt(8);
					if(addShip(x, y, myShips[i])){
						placed=true;
						i++;
					}
					//else{
						//System.out.println("Coordinates not Valid");
					//}
				}
			}
		}
	}
	//random shots method
	public AIshot randShoot(User b){
		boolean repeat = true;
		int x=0;
		int y=0;
		while(repeat){
			x = (int)(Math.random()*8);
			y = (int)(Math.random()*8);
         repeat = false;
				for(int i=0; i<shots.size() && !repeat;i++){
					if(shots.get(i).getX()==x && shots.get(i).getY()==y){
						repeat = true;
					}					
				}			
		}  
         
      boolean boo = pShoot(b,x,y);      
		if(boo){                  
         shots.add(new AIshot(x,y,true));
			return shots.get(shots.size()-1);
		}
		else{         
			shots.add(new AIshot(x,y,false));
			return shots.get(shots.size()-1);
		}
	}
	//AI makes a guess depending on previous turns
	public void makeGuess(User b) {
		guessX = r.nextInt(8) + 1;
		guessY = r.nextInt(8) + 1;
		
		//If it hasn't hit a ship before it hits a random square
		if(hasHit == false) {
			//If that location has already been guessed, find a new location
			boolean alreadyHit = true;
			while(alreadyHit) {
				//Finds if the location has already been hit. If yes then find a new location.
				if(!b.getsGrid().getUI().getShots()[guessX][guessY].equals("_")) {
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
				//If it misses it goes back to it's starting point and goes the opposite direction until game end
				if(b.getsGrid().getUI().getShots()[hitX + directionGuess][hitY].equals("O")) {
					directionGuess = 0;
					hasDirection = 3;
				}
				directionGuess++;
			}
			else if(hasDirection == 2) {
				b.getsGrid().attack(hitX, hitY - directionGuess);
				if(b.getsGrid().getUI().getShots()[hitX][hitY - directionGuess].equals("O")) {
					directionGuess = 0;
					hasDirection = 4;
				}
				directionGuess++;
			}
			else if(hasDirection == 3) {
				b.getsGrid().attack(hitX - directionGuess, hitY);
				if(b.getsGrid().getUI().getShots()[hitX - directionGuess][hitY].equals("O")) {
					directionGuess = 0;
					hasDirection = 1;
				}
				directionGuess++;
			}
			else if(hasDirection == 4) {
				b.getsGrid().attack(hitX, hitY + directionGuess);
				if(b.getsGrid().getUI().getShots()[hitX][hitY + directionGuess].equals("O")) {
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
				 if (b.getsGrid().getUI().getShots()[hitX + 1][hitY].equals("X")) {
			 		hasDirection = guessDirection;
			 	}
			 }
			 
			 else if(guessDirection == 2) {
				 b.getsGrid().attack(hitX, hitY - 1);
				 if (b.getsGrid().getUI().getShots()[hitX][hitY - 1].equals("X")) {
			 		hasDirection = guessDirection;
			 	}
			 }
			 
			 else if(guessDirection == 3) {
				 b.getsGrid().attack(hitX - 1, hitY);
				 if (b.getsGrid().getUI().getShots()[hitX - 1][hitY].equals("X")) {
			 		hasDirection = guessDirection;
			 	}
			 }
			 
			 else if(guessDirection == 4) {
				 b.getsGrid().attack(hitX, hitY + 1);
				 if (b.getsGrid().getUI().getShots()[hitX][hitY + 1].equals("X")) {
			 		hasDirection = guessDirection;
			 	}
			}
		}
		
		//Remembers the first location it hit and remembers that it has hit a ship at some point
		if(b.getsGrid().getUI().getShots()[guessX][guessY].equals("X") && hasHit == false) {
			hasHit = true;
			hitX = guessX;
			hitY = guessY;
		}
	}
}
