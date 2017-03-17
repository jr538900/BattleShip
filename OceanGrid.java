//Class that makes the grids
public class OceanGrid extends Grid{
	//Instanced Variables
	private Ship[][] grid;//Board that the ships will be placed on
	private String[][] UI;//may or may not be used in code
	private boolean[][] shots;//as above
	
	public OceanGrid(){//Constructor for all 2d arrays
		grid= new Ship[getX()][getY()];
		UI= new String[getX()][getY()];
		shots= new boolean[getX()][getY()];
	}
	//The method that allows 
	public boolean addShip(int x,int y,Ship s){
		if(!inGrid(x,y)){
			return false;
		}
		if(s.getY()==1){
			for(int i=x;i<=x+s.getX();i++){
				if(grid[i][y]!=null){
					return false;
				}
			}
			for(int i=x;i<=x+s.getX();i++){
				grid[i][y]=s;
			}
			return true;
		}
		else{
			for(int i=y;i<=y+s.getY();i++){
				if(grid[x][i]!=null){
					return false;
				}
			}
			for(int i=y;i<=y+s.getY();i++){
				grid[x][i]=s;
			}
			return true;
		}
		
		
	}
}
