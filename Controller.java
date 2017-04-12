
public class Controller {
	private User p1;
	private AI p2;
	private GUIBattleShip gui;
	private boolean gameOver=false;
	public Controller(){
		p1 = new User();
		p2 = new AI();
		gui = new GUIBattleShip();
	}
	public static void main(String[]args){
		Controller Demo = new Controller();
		int turn = 1;
		while(!Demo.isGameOver()){
			Demo.getP1().makeShip();
			Demo.getP2().makeShip();
			System.out.print(Demo.getP1().getsGrid().getUI().toString());
			if(turn==1){
				
				if(Demo.getP2().isLostGame()){
					Demo.setGameOver(true);
				}
				turn=2;
			}
			else{
				
				if(Demo.getP1().isLostGame()){
					Demo.setGameOver(true);
				}
				turn=1;
			}
		}
		if(Demo.getP1().isLostGame()){
			System.out.print("AI wins");
		}
		else{
			System.out.print("You win");
		}
	}
	public User getP1() {
		return p1;
	}
	public void setP1(User p1) {
		this.p1 = p1;
	}
	public AI getP2() {
		return p2;
	}
	public void setP2(AI p2) {
		this.p2 = p2;
	}
	public GUIBattleShip getGui() {
		return gui;
	}
	public void setGui(GUIBattleShip gui) {
		this.gui = gui;
	}
	public boolean isGameOver() {
		return gameOver;
	}
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
}
