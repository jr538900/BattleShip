
public class AIshot {
	private boolean hit;
	private int x,y;
	public AIshot(int x,int y, boolean hit){	
	this.x=x;
	this.y=y;
	this.hit=hit;
	}
	public boolean isHit() {
		return hit;
	}
	public void setHit(boolean hit) {
		this.hit = hit;
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
	public String toString(){
		return "("+x+","+y+")";
	}
	public boolean sameshot(int x,int y){
		if(this.x==x&&this.y==y){
			return true;
		}
		return false;
	}
}
