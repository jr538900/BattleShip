
public class Ship {
	private int hp;
	private String name;
	private int lengthX;
	private int lengthY;
	public Ship(){}
	public Ship(int lengthX, int lengthY){
		this.lengthX=lengthX;
		this.lengthY=lengthY;
		hp=lengthX*lengthY;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLengthX() {
		return lengthX;
	}
	public void setLengthX(int lengthX) {
		this.lengthX = lengthX;
	}
	public int getLengthY() {
		return lengthY;
	}
	public void setLengthY(int lengthY) {
		this.lengthY = lengthY;
	}
	public void makeHP(){
		hp=lengthX*lengthY;
	}
}
