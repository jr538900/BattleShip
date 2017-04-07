//User class that keeps track of the users stuff
import java.util.Scanner;
public class User {
	//instanced variables
	private ShipGrid sGrid;
	private Grid gGrid;
	private Ship[] myShips;
	private boolean lostGame;
	public User(){
		gGrid= new Grid(8,8);
		sGrid = new ShipGrid();
		lostGame = false;
		myShips = new Ship[3];
	}
	//Get and set Methods
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
	public Ship[] getShip1() {
		return myShips;
	}
	public void setShip1(Ship[] myShips) {
		this.myShips = myShips;
	}
	public boolean isLostGame() {
		return lostGame;
	}
	public void setLostGame(boolean lostGame) {
		this.lostGame = lostGame;
	}
	//Class that checks if the player has lost the game
	public void shipsUp(){
		int ships =0;
		for(int i=0;i<3;i++){
			if(myShips[i].getHp()>=0){
				ships++;
			}
		}
		if(ships==0){
			lostGame = true;
		}
	}
	//Class that takes in a string for direction and a counter for the ship type and returns a ship object
	public Ship shipType(String d,int i){
		int x=0,y=0;
		if(d.equalsIgnoreCase("x")){
			y=1;
			x=i+2;
		}
		if(d.equalsIgnoreCase("y")){
			y=i+2;
			x=1;
		}
		if(x!=0&&y!=0){
			Ship nShip = new Ship(x,y);
			return nShip;
		}
		return null;
	}
	//Class that allows the player to place their ships on the board
	public void makeShip(){
		Scanner input = new Scanner(System.in);
		boolean placed;
		int x=0,y=0;
		String d;
		int i=0;
		while(i<3){
			placed=false;
			System.out.print("Enter X for a Horizontal Ship or Y for a Vertical Ship: ");
			d=input.next();
			myShips[i] = shipType(d,i);
			if(myShips[i] == null){
				System.out.println("Not a valid Ship direction");
			}
			else{
				while(!placed){
					System.out.print("Enter Coordinates to place Ship(x y): ");
					x=input.nextInt();
					y=input.nextInt();
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
}
