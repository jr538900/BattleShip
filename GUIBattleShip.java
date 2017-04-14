import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.DragEvent;
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

import java.util.ArrayList;

public class GUIBattleShip extends Application {
	
   private HBox top;
   private Text topText;
   private HBox bottom;
	private Button exit,reset;
   private VBox right;
   //These myShips will have length 2, 3, and 4, respectively.
   private Text theirShipText;
   private Rectangle theirShip2GUI;
   private Rectangle theirShip3GUI;
   private Rectangle theirShip4GUI;
   private Text myShipText;
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
   private ShipGrid theirGrid;
   private ShipGrid myGrid;
   private int dim;
   //This contains the myShips that are not on the user's grid already.  
   private ArrayList<Ship> myShips;
   private ArrayList<Ship> theirShips;
   
   private 
   Font font1 = Font.font("Arial",FontWeight.BOLD,40);
	Font font2 = Font.font("Arial",FontWeight.BOLD,20);
   
	@Override
	public void start(Stage primaryStage){
      
      grid = new Grid();
		myGrid = new ShipGrid();
      theirGrid = new ShipGrid();
      
      //This adds myShips that are not yet placed on the board.      
      myShips = new ArrayList<Ship>();
      myShips.add(new Ship(1,4));
      myShips.add(new Ship(1,3));
      myShips.add(new Ship(1,2));
      theirShips = new ArrayList<Ship>();
      theirShips.add(new Ship(1,4));
      theirShips.add(new Ship(1,3));
      theirShips.add(new Ship(1,2));
         
      //The text in the pane.      
      topText = new Text("WELCOME TO BATTLESHIP\nCLICK ON YOUR GRID TO PLACE YOUR 1 X " + dim + " SHIP");
      topText.setFont(font1);      
      //The top pane of the GUI box.  
      top = new HBox();
      top.setAlignment(Pos.CENTER);
      top.setStyle("-fx-background-color: BLUE");
      top.setPadding(new Insets(10,10,10,10));
      top.getChildren().addAll(topText);
      
      //This stores the AI's myShips the user shoots at.     
		theirShipPane = new GridPane();
      theirShipPane.setPadding(new Insets(10,10,10,10));
		theirShipPane.setAlignment(Pos.CENTER);
		//This stores the user's myShips that AI shoots at.      
      myShipPane = new GridPane();
      myShipPane.setPadding(new Insets(10,10,10,50));
		myShipPane.setAlignment(Pos.CENTER);
      
      //The buttons that the user and AI shoots at.
		theirShipButtons = new Button[grid.getX()][grid.getY()];
      myShipButtons = new Button[grid.getX()][grid.getY()];
		
      //Sets the game by adding buttons to theirGridPane and myGridPane.
      setGame();
      
      
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
      theirShipText = new Text("OPPONENT'S SHIPS");
      theirShipText.setFont(font2);
      theirShipText.setVisible(true);
            
      //Creates the rectangles for opponent's ships.
      theirShip2GUI = new Rectangle(2*30, 30);      
      theirShip3GUI = new Rectangle(3*30, 30);      
      theirShip4GUI = new Rectangle(4*30, 30);
      theirShip2GUI.setFill(Color.BLUE);
      theirShip3GUI.setFill(Color.BLUE);
      theirShip4GUI.setFill(Color.BLUE);
      theirShip2GUI.setStroke(Color.BLACK);
      theirShip3GUI.setStroke(Color.BLACK);
      theirShip4GUI.setStroke(Color.BLACK);
      //Creates the rectangles for our myShips.
      myShipText = new Text("MY SHIPS");
      myShipText.setFont(font2);
      myShipText.setVisible(true);
      myShip2GUI = new Rectangle(2*30, 30);
      myShip3GUI = new Rectangle(3*30, 30);
      myShip4GUI = new Rectangle(4*30, 30);
      myShip2GUI.setFill(Color.BLUE);
      myShip3GUI.setFill(Color.BLUE);
      myShip4GUI.setFill(Color.BLUE);      
      myShip2GUI.setStroke(Color.BLACK);
      myShip3GUI.setStroke(Color.BLACK);
      myShip4GUI.setStroke(Color.BLACK);
      
      //The pane that keeps track of the myShips.
      right = new VBox();
      right.setAlignment(Pos.CENTER);
      right.setPadding(new Insets(10,10,10,10));
      right.setSpacing(5);
      right.getChildren().addAll(theirShipText, theirShip2GUI, theirShip3GUI, theirShip4GUI,
                                 myShipText, myShip2GUI, myShip3GUI, myShip4GUI); 
            
      //This will put all panes together.
      border = new BorderPane();
      border.setCenter(theirShipPane);
      border.setLeft(myShipPane);
      border.setRight(right);
      border.setTop(top);
      border.setBottom(bottom);
      
      //This will set the scene for the game.
		Scene scene = new Scene(border,1000,500);
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
						//if the ship is hit.
                  if(theirGrid.attack(i,j)){
							theirShipButtons[i][j].setText("X");
							theirShipButtons[i][j].setDisable(true);
							theirShipButtons[i][j].setStyle("-fx-background-color: red");
                     
                  //determineWinner();
						}
						//the ship is not hit.
						else{ 
							theirShipButtons[i][j].setText("O");
							theirShipButtons[i][j].setDisable(true);
							theirShipButtons[i][j].setStyle("-fx-background-color: cyan");
						}
					}
               if (e.getSource()==myShipButtons[i][j]){
                  if(dim>=2 && myGrid.addShip(i, j, myShips.get(0))){
                  System.out.println(dim>=2 && myGrid.addShip(i, j, myShips.get(0)));
                     for(int k=0; k<dim; k++){                                                
                        myShipButtons[i+k][j].setStyle("-fx-background-color: GREY");
                        topText.setText("WELCOME TO BATTLESHIP\nCLICK ON YOUR GRID TO PLACE YOUR 1 X " + (dim-2) + " SHIP");						      
                     }
                     myShips.remove(0);
                     if(myShips.isEmpty())
                        startGame();
                  }                  
               dim--;
               System.out.println("GUI: (" + i + ", " + j + ")");               
               System.out.println("Ship: \n");
               System.out.println(myGrid);                    
					}
				}
			}//end for loop (searching for source of event)
		}//end else
	}//end method   
   
   //This will reset the game.
   public void setGame(){
      //This clears the panes and adds new buttons to the pane.
      if(!theirShipPane.getChildren().isEmpty())
         theirShipPane.getChildren().clear();
      if(!myShipPane.getChildren().isEmpty())
         myShipPane.getChildren().clear();
      
      grid = new Grid();
		myGrid = new ShipGrid();
      theirGrid = new ShipGrid();
      
      //This will store the dimension of the ship that has yet to be placed.
      dim = 4;
      topText.setText("WELCOME TO BATTLESHIP\nCLICK ON YOUR GRID TO PLACE YOUR 1 X " + dim + " SHIP");
      
      //Adds buttons for their ship, and my ship.      
      for (int i=0; i<grid.getX(); i++){
			for (int j=0; j<grid.getY(); j++){
				//Sets up the pane for their myShips.
            //These buttons are initially deactivated, as you are setting up the game.
            theirShipButtons[i][j] = new Button("  ");
            theirShipButtons[i][j].setPrefHeight(30);
            theirShipButtons[i][j].setPrefWidth(30);
				theirShipPane.add(theirShipButtons[i][j],i,j);
            theirShipButtons[i][j].setOnAction(this::processButtonPressed);
            theirShipButtons[i][j].setDisable(true);            
            
            //These buttons are initially activated as the user places myShips on the board.
            myShipButtons[i][j] = new Button("  ");
            myShipButtons[i][j].setPrefHeight(30);
            myShipButtons[i][j].setPrefWidth(30);
				myShipPane.add(myShipButtons[i][j],i,j);
				myShipButtons[i][j].setOnAction(this::processButtonPressed);
            myShipButtons[i][j].setDisable(false);
			}
		}
   }
   
   //This will start the game once the myShips have been set.
   public void startGame(){
      for(int i=0; i<grid.getX(); i++)
         for(int j=0; j<grid.getY(); j++)
         {
            //This activates the buttons for their myShips, and de-activates the buttons for our myShips.
            theirShipButtons[i][j].setDisable(false);
            myShipButtons[i][j].setDisable(true);
            topText.setText("Attack Ship");                        
         }
   }      
              
	public static void main(String[] args){
		launch(args);
	}
}
