//Where the AI will play it's turn
import java.util.Random;
public class AI extends User
{
	
	Random r = new Random();
	private int guessX;
	private int guessY;
	
	//Constructor sets guesses to random numbers
	public AI()
	{
		guessX = r.nextInt(getWidth()+1);
      		guessY = r.nextInt(getLength()+1);
	}
	
	public int getGuessX()
	{
		return guessX;
	}
	public int getGuessY()
	{
		return guessY;
	}
	public void setGuessX(int guessX)
	{
		this.guessX = guessX;
	}
	public void setGuessY(int guessY)
	{
		this.guessY = guessY;
	}
	
	//TODO:  methods to attack a square and set up ships.
}
