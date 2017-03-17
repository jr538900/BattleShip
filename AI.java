//Where the AI will play it's turn
import java.util.Random;
public class AI extends UIBoard{
	
	Random r = new Random();
	private int num1;
	private int num2;
	
	//Constructor
	public AI(){
		int num1 = r.nextInt(getWidth()+1);
      int num2 = r.nextInt(getLength()+1);
	}
}
