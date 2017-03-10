
public class Ship {
	private int hp;
	private String name;
	private int x;
	private int y;
	public Ship(){}
	public Ship(int x, int y){
		this.x=x;
		this.y=y;
		hp=x*y;
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
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
}
