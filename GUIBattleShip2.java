import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
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

import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseButton;

import java.util.ArrayList;
public class GUIBattleShip2 extends Application {
	
   private HBox top;
   private Text topText;
   private HBox bottom;
   private Button exit,reset;
   private VBox right;
   //These will have length 2, 3, and 4, respectively.
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
   
   private User p1;
   private AI p2;
   
   //These are other objects that the program will interact with.
   private Grid grid;
   private ShipGrid theirGrid;
   private ShipGrid myGrid;
   //This contains the myShips that are not on the user's grid already.
   //Hopefully, this will be in the "BattleShipGame" class.  
   private ArrayList<Ship> myShips;
   private ArrayList<Ship> theirShips;
   
   private 
   Font font1 = Font.font("Arial",FontWeight.BOLD,40);
	Font font2 = Font.font("Arial",FontWeight.BOLD,20);
   
	@Override
	public void start(Stage primaryStage){ 
      p1 = new User();
      p2 = new AI();
      grid = p1.getgGrid();
      myGrid = p1.getsGrid();
      theirGrid = p2.getsGrid();
      
      //This adds myShips that are not yet placed on the board.      
      myShips = new ArrayList<Ship>();
      theirShips = new ArrayList<Ship>();
               
      //The text in the pane.      
      topText = new Text();
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
      reset.setOnMouseClicked(this::processButtonPressed);
      exit.setOnMouseClicked(this::processButtonPressed);
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
	public void processButtonPressed(MouseEvent e){
		//Pushes the "reset" button.
      if(e.getSource()==reset){         
         setGame();        
		}
      //Pushes the "exit" button.
		else if (e.getSource()==exit){
			System.exit(0);
		}
      //Pushes a button on user's grid, or opponent's grid.
		else{
			for (int i=0; i<grid.getX(); i++){
				for (int j=0; j<grid.getY(); j++){
					if (e.getSource()==theirShipButtons[i][j]){
						//if the ship is hit.
						if(p1.pShoot(p2,i,j)){
							theirShipButtons[i][j].setText("X");
							theirShipButtons[i][j].setDisable(true);
							theirShipButtons[i][j].setStyle("-fx-background-color: red");
							System.out.println(i + "\t" + j);
							determineWinner();
							AITurn();
							determineWinner();
							
						}
						//the ship is not hit.
						else{ 
							theirShipButtons[i][j].setText("O");
							theirShipButtons[i][j].setDisable(true);
							theirShipButtons[i][j].setStyle("-fx-background-color: cyan");
							System.out.println(i + "\t" + j);
							determineWinner();
							AITurn();
							determineWinner();
						}
						
					}
               
					//The user clicked on their shipGrid.
					if (e.getSource()==myShipButtons[i][j]){
						//Left-click will align the ships horizontally.
						if(e.getButton()==MouseButton.PRIMARY){
							if(!myShips.isEmpty() && p1.addShip(i, j, myShips.get(0))){
								//System.out.println(dim>=2 && myGrid.addShip(j, i, myShips.get(0)));
								for(int k=0; k<myShips.get(0).getLengthX(); k++){                                    
									myShipButtons[i+k][j].setStyle("-fx-background-color: GREY");
								}
								myShips.remove(0);
							}
							if(!myShips.isEmpty()) 
								topText.setText("WELCOME TO BATTLESHIP\nCLICK ON YOUR GRID TO PLACE YOUR 1 X " + (myShips.get(0).getLengthX()) + " SHIP");
							else
								startGame();   
						}
					}
                  //Right-click will align the ships vertically.
                  /*if (e.getSource()==myShipButtons[i][j]){
                      //Left-click will align the ships horizontally.
                      if(e.getButton()==MouseButton.SECONDARY){
                    	  int s = myShips.get(0).getLengthX();
                    	  System.out.println(s);
                    	  myShips.get(0).setLengthX(1);
                    	  myShips.get(0).setLengthY(s);
                         if(!myShips.isEmpty() && myGrid.addShip(i, j, myShips.get(0))){
                         //System.out.println(dim>=2 && myGrid.addShip(j, i, myShips.get(0)));
                            for(int k=0; k<myShips.get(0).getLengthY(); k++){                                    
                               myShipButtons[i][j-k].setStyle("-fx-background-color: GREY");
                            }
                            myShips.remove(0);
                         }
                            if(!myShips.isEmpty()) 
                               topText.setText("WELCOME TO BATTLESHIP\nCLICK ON YOUR GRID TO PLACE YOUR 1 X " + (myShips.get(0).getLengthX()) + " SHIP");
                            else
                               startGame();   
                      }*/
                  }
				}//end for loop.
      }//end else   
	}//end method   
   
   //This will reset the game.
   public void setGame(){
      //This clears the panes and adds new buttons to the pane.
      if(!theirShipPane.getChildren().isEmpty())
         theirShipPane.getChildren().clear();
      if(!myShipPane.getChildren().isEmpty())
         myShipPane.getChildren().clear();
      
      //Resets the aggregated objects.
      p1 = new User();
      p2 = new AI();
      grid = p1.getgGrid();
      myGrid = p1.getsGrid();
      theirGrid = p2.getsGrid();
      myShips.clear();
      theirShips.clear();
      
      myShips.add(new Ship(4,1));
      myShips.add(new Ship(3,1));
      myShips.add(new Ship(2,1));
      p2.makeShip();
      //This will store the dimension of the ship that has yet to be placed.
      topText.setText("WELCOME TO BATTLESHIP\nCLICK ON YOUR GRID TO PLACE YOUR 1 X " + myShips.get(0).getLengthX() + " SHIP");
      
      //Adds buttons for their ship, and my ship.      
      for (int i=0; i<grid.getX()+1; i++){
			for (int j=0; j<grid.getY()+1; j++){
				
            //These "if" statements will label each grid.  The "else" statement will actually set the buttons.
            if(i==0 && j==grid.getY()){
               Text label1 = new Text("  ");
               Text label2 = new Text("  ");
               label1.setFont(font2);
               label2.setFont(font2);               
               theirShipPane.add(label1, i, j);
               myShipPane.add(label2, i, j);               
            }
            //Formats the letters.
            else if(i==0){
               Text label1 = new Text("" + (8-j));
               Text label2 = new Text("" + (8-j));
               label1.setFont(font2);
               label2.setFont(font2);              
               theirShipPane.add(label1, i, j);
               myShipPane.add(label2, i, j);               
            }
            //Formats the numbers.
            else if(j==grid.getY()){
               Text label1 = new Text(" " + (char)(i+64));
               Text label2 = new Text(" " + (char)(i+64));
               label1.setFont(font2);
               label2.setFont(font2);               
               theirShipPane.add(label1, i, j);
               myShipPane.add(label2, i, j);               
            }          
            else{
               //Sets up the pane for their myShips.
               //These buttons are initially deactivated, as you are setting up the game.
               theirShipButtons[i-1][j] = new Button("  ");
               theirShipButtons[i-1][j].setPrefHeight(30);
               theirShipButtons[i-1][j].setPrefWidth(30);
               theirShipPane.add(theirShipButtons[i-1][j],i,j);
               theirShipButtons[i-1][j].setOnMouseClicked(this::processButtonPressed);
               theirShipButtons[i-1][j].setDisable(true);            
            
               //These buttons are initially activated as the user places myShips on the board.
               myShipButtons[i-1][j] = new Button("  ");
               myShipButtons[i-1][j].setPrefHeight(30);
               myShipButtons[i-1][j].setPrefWidth(30);
               myShipPane.add(myShipButtons[i-1][j],i,j);
               myShipButtons[i-1][j].setOnMouseClicked(this::processButtonPressed);
               myShipButtons[i-1][j].setDisable(false);
            }
			}         
		}      
   }
   public void AITurn(){
	  for(int i=0;i<1;i++){
		System.out.println(p2.randShoot(p1).toString());
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
            
            determineWinner();
         }
   }      
   
   //There may be more code to in other classes to determine the winner of the Battleship game.
   public void determineWinner(){
	      //The game user has lost.
	      if(p1.hasLost()){
	         topText.setText("YOU LOSE");
	         top.setStyle("-fx-background-color: RED");
	         
	         //Disables all buttons.
	         for(int i=0; i<grid.getX(); i++)
	            for(int j=0; j<grid.getY(); j++){
	               myShipButtons[i][j].setDisable(true);
	               theirShipButtons[i][j].setDisable(true);
	            }   
	         
	      }
	      //The AI has lost.   
	      else if(p2.hasLost()){
	         topText.setText("CONGRATULATIONS! YOU WIN!");
	         top.setStyle("-fx-background-color: YELLOW");
	         
	         //Disables all buttons.
	         for(int i=0; i<grid.getX(); i++)
	            for(int j=0; j<grid.getY(); j++){
	               myShipButtons[i][j].setDisable(true);
	               theirShipButtons[i][j].setDisable(true);
	            }
	      }
	            
	      else{//Nothing happens.
	      }
	   }              
              
	public static void main(String[] args){
		launch(args);
	}
}
