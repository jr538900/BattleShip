import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GUIBattleShip extends Application {
	
   private HBox top;
   private Text topText;
   private HBox bottom;
	private Button exit,reset;
   
   private VBox right;
   //These ships will have length 2, 3, and 4, respectively.
   private Text theirShips;
   private Rectangle theirShip2GUI;
   private Rectangle theirShip3GUI;
   private Rectangle theirShip4GUI;
   private Text myShips;
   private Rectangle myShip2GUI;
   private Rectangle myShip3GUI;
   private Rectangle myShip4GUI;
   
   //Panes for box.
   private GridPane theirShipPane;
   private GridPane myShipPane;
   private Button[][] theirShipButtons;
   private Button[][] myShipButtons;
      
   private BorderPane border;
   
   //These are other objects that the program will interact with.
   private Grid grid;
   private ShipGrid sGrid;  
   private Group root;
   
   private 
   Font font1 = Font.font("Arial",FontWeight.BOLD,40);
	Font font2 = Font.font("Arial",FontWeight.BOLD,20);
   
	@Override
	public void start(Stage primaryStage){
      
      grid = new Grid();
		sGrid = new ShipGrid();
      root = new Group();
      
      //This stores the AI's ships the user shoots at.     
		theirShipPane = new GridPane();
      theirShipPane.setPadding(new Insets(10,10,10,10));
		theirShipPane.setAlignment(Pos.CENTER);
		//This stores the user's ships that AI shoots at.      
      myShipPane = new GridPane();
      myShipPane.setPadding(new Insets(10,10,10,100));
		myShipPane.setAlignment(Pos.CENTER);
      
      //The buttons that the user and AI shoots at.
		theirShipButtons = new Button[grid.getX()][grid.getY()];
      myShipButtons = new Button[grid.getX()][grid.getY()];
		
      //Sets the game by adding buttons to theirGridPane and myGridPane.
      setGame();     
		
      //The text in the pane.      
      topText = new Text("BATTLESHIP");
      topText.setFont(font1);      
      //The top pane of the GUI box.  
      top = new HBox();
      top.setAlignment(Pos.CENTER);
      top.setStyle("-fx-background-color: BLUE");
      top.setPadding(new Insets(10,10,10,10));
      top.getChildren().addAll(topText);
      
      //The reset and exit buttons.
      reset = new Button("RESET");
      exit = new Button("EXIT");
      reset.setOnAction(this::processButtonPressed);
      exit.setOnAction(this::processButtonPressed);
      //The bottom pane of the GUI box.
      bottom = new HBox();
      bottom.setAlignment(Pos.CENTER);
      bottom.setPadding(new Insets(10,10,10,10));
      bottom.setSpacing(20);
      bottom.getChildren().addAll(reset, exit);
      
      //These are the ships to be hit.
      theirShips = new Text("OPPONENT'S SHIPS");
      theirShips.setFont(font2);
      theirShips.setVisible(true);
      
      //Creates the rectangles
      theirShip2GUI = new Rectangle(2*30, 30);      
      theirShip3GUI = new Rectangle(3*30, 30);      
      theirShip4GUI = new Rectangle(4*30, 30);
      theirShip2GUI.setFill(Color.RED);
      theirShip3GUI.setFill(Color.RED);
      theirShip4GUI.setFill(Color.RED);
      myShips = new Text("MY SHIPS");
      myShips.setFont(font2);
      myShips.setVisible(true);
      myShip2GUI = new Rectangle(2*30, 30);
      myShip3GUI = new Rectangle(3*30, 30);
      myShip4GUI = new Rectangle(4*30, 30);      
      //Activates rectangles for this user's ship.
      myShip2GUI.setOnMouseDragged(this::processMouseDragged);
      myShip3GUI.setOnMouseDragged(this::processMouseDragged);
      myShip4GUI.setOnMouseDragged(this::processMouseDragged);
      
      //The pane that keeps track of the ships.
      right = new VBox();
      right.setAlignment(Pos.CENTER);
      right.setPadding(new Insets(10,10,10,10));
      right.setSpacing(10);
      right.getChildren().addAll(theirShips, theirShip2GUI, theirShip3GUI, theirShip4GUI,
                                 myShips, myShip2GUI, myShip3GUI, myShip4GUI); 
            
      //This will put all panes together.
      border = new BorderPane();
      border.setCenter(theirShipPane);
      border.setLeft(myShipPane);
      border.setRight(right);
      border.setTop(top);
      border.setBottom(bottom);
      
      //This will set the scene for the game.
		Scene scene = new Scene(border,1100,700);
      scene.setOnMouseDragged(this::processMouseDragged);
		primaryStage.setTitle("Battleship");
		primaryStage.setScene(scene);
		primaryStage.show();      
      
	}
	public void processButtonPressed(ActionEvent e){
		if(e.getSource()==reset){         
         setGame();        
		}
		else if (e.getSource()==exit){
			System.exit(0);
		}
		else{
			for (int i=0; i<grid.getX(); i++){
				for (int j=0; j<grid.getY(); j++){
					if (e.getSource()==theirShipButtons[i][j]){
						//if hit a ship
						if(sGrid.attack(i,j)){
							theirShipButtons[i][j].setText("X");
							theirShipButtons[i][j].setDisable(true);
							theirShipButtons[i][j].setStyle("-fx-background-color: red");
						}
						//if not
						else{ //if(!hit(shoot,ship))
							theirShipButtons[i][j].setText("O");
							theirShipButtons[i][j].setDisable(true);
							theirShipButtons[i][j].setStyle("-fx-background-color: cyan");
						}
					}
               if (e.getSource()==myShipButtons[i][j]){
						//if hit a ship
						//if(hit(shoot,ship)){
							myShipButtons[i][j].setText("X");
							myShipButtons[i][j].setDisable(true);
							myShipButtons[i][j].setStyle("-fx-background-color: red");
						//}
						//if not
						//else if(!hit(shoot,ship)){
							myShipButtons[i][j].setText("O");
							myShipButtons[i][j].setDisable(true);
							myShipButtons[i][j].setStyle("-fx-background-color: cyan");
						//}
					}
				}
			}//end for loop
		}//end else
	}//end method
   
   //This will handle a dragged mouse event.
   public void processMouseDragged(MouseEvent e){
      double clickX = e.getX();
      double clickY = e.getY();
      //The x and y positions of the click are within bounds of the ship2 rectangle object. 
      if(myShip2GUI.getX()<=clickX && clickX<=(myShip2GUI.getX()+myShip2GUI.getHeight()) &&
         myShip2GUI.getY()<=clickY && clickY<=(myShip2GUI.getY()+myShip2GUI.getHeight()))
         //This will reset the position of the ship with length 2
         //based on the new coordinates clickX and clickY.
         //setShipPosition(myShip2GUI, clickX, clickY);
         myShip2GUI.setX(e.getX());
      
      if(myShip3GUI.getX()<=clickX && clickX<=(myShip3GUI.getX()+myShip3GUI.getHeight()) &&
         myShip3GUI.getY()<=clickY && clickY<=(myShip3GUI.getY()+myShip3GUI.getHeight()))
         //Sets the position of the ship with length 3.
         //setShipPosition(myShip3GUI, clickX, clickY);
         myShip3GUI.setX(e.getX());
         
      if(myShip4GUI.getX()<=clickX && clickX<=(myShip4GUI.getX()+myShip4GUI.getHeight()) &&
         myShip4GUI.getY()<=clickY && clickY<=(myShip4GUI.getY()+myShip4GUI.getHeight()))
         //Sets the position of the ship with length 4.
         //setShipPosition(myShip4GUI, clickX, clickY);
         myShip4GUI.setX(e.getX());
               
   }//end method
   
   //This will update the position of a "ship" (rectangle object) based on x and y.
   /*public void setShipPosition(Rectangle r, double x, double y){
      //Searches through "my ships" for the button corresponding the the mouse drop.
      for(int i=0; i<grid.getX(); i++)
         for(int j=0; j<grid.getY(); j++)
            //If the x coordinate of the click agrees with a button's x coordinate,
            //and the y coordinate of the click agrees with the button's y coordinate,
            //the rectangle object is set to a new position.
            if(myShipButtons[i][j].getMinX()==x && myShipButtons[i][j].getY()==y){
               r.setX(myShipButtons[i][j].getX());
               r.setY(myShipButtons[i][j].getY());
            }
   }*/                           
    
   //This will reset the game.
   public void setGame(){
         
      if(!theirShipPane.getChildren().isEmpty())
         theirShipPane.getChildren().clear();
      if(!myShipPane.getChildren().isEmpty())
         myShipPane.getChildren().clear();
      
      //Adds buttons for their ship, and my ship.      
      for (int i=0; i<grid.getX(); i++){
			for (int j=0; j<grid.getY(); j++){
				theirShipButtons[i][j] = new Button("  ");
            theirShipButtons[i][j].setPrefHeight(30);
            theirShipButtons[i][j].setPrefWidth(30);
				theirShipPane.add(theirShipButtons[i][j],i,j+1);
            theirShipButtons[i][j].setOnAction(this::processButtonPressed);
            
            myShipButtons[i][j] = new Button("  ");
            myShipButtons[i][j].setPrefHeight(30);
            myShipButtons[i][j].setPrefWidth(30);
				myShipPane.add(myShipButtons[i][j],i,j+1);
				myShipButtons[i][j].setOnAction(this::processButtonPressed);
			}
		}
   }
   //This method will change the orientation of the ship object.
   //public void setShips(){
   
      
         
	public static void main(String[] args){
		launch(args);
	}
}
