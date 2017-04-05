//Class where the AI will play it's turn
import java.util.Random;
public class AI extends ShipGrid {
	
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
	//Constructor
	public AI(){
		hasHit = false;
		hasDirection = 0;
		guessDirection = 0;
	}
	
	//AI makes a guess depending on previous turns
	public void makeGuess() {
		guessX = r.nextInt(8) + 1;
		guessY = r.nextInt(8) + 1;
		attack(guessX, guessY);
		
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
	
	public int getGuessX() {
		return guessX;
	}
	
	public int getGuessY() {
		return guessY;
	}
	
	public void setGuessX(int guessX) {
		this.guessX = guessX;
	}
	
	public void setGuessY(int guessY) {
		this.guessY = guessY;
	}
}

