//This board will be a blueprint for other boards used in this game.
public class Board 
{
	//This will determine the dimensions of the board.
	private int length;
	private int width;
	
	//Constructor creates this board object.
	public Board(int l, int w)
	{
		length = l;
		width = w;		
	}
	
	//Get methods will return the board length.
	public int getLength()
	{
		return length;
	}
	//This get method will return the board width.
	public int getWidth()
	{
		return width;
	}
}
