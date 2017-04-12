import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class GUIBattleShip extends Application {
	private GridPane pane;
	private Button exit,reset;
	private Button[][] board;
	Font font1 = Font.font("Arial",FontWeight.BOLD,40);
	Font font2 = Font.font("Arial",FontWeight.BOLD,14);
	
	@Override
	public void start(Stage primaryStage){
		pane = new GridPane();
		board = new Button[8][8];
		for (int i=0; i<8; i++){
			for (int j=0; j<8; j++){
				board[i][j] = new Button();
				board[i][j].setText("  ");
				board[i][j].setFont(font1);
				pane.add(board[i][j],i,j+1);
				board[i][j].setOnAction(this::processButtonPressed);
			}
		}
		reset = new Button("RESET");
		exit = new Button("EXIT");
		exit.setFont(font2);
		reset.setFont(font2);
		pane.add(reset,0,0);
		pane.add(exit,2,0);
		exit.setOnAction(this::processButtonPressed);
		reset.setOnAction(this::processButtonPressed);
		pane.setPadding(new Insets(.5,.5,.5,.5));
		pane.setAlignment(Pos.CENTER);
		pane.setHgap(5);
		pane.setVgap(5);
		Scene scene = new Scene(pane,800,700);
		primaryStage.setTitle("Battleship");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public void processButtonPressed(ActionEvent e){
		if(e.getSource()==reset){
			pane.getChildren().clear();
			for (int i=0; i<8; i++){
				for (int j=0; j<8; j++){
					board[i][j] = new Button();
					board[i][j].setText("  ");
					board[i][j].setFont(font1);
					pane.add(board[i][j],i,j+1);
					board[i][j].setOnAction(this::processButtonPressed);
					reset = new Button("RESET");
					exit = new Button("EXIT");
					exit.setFont(font2);
					reset.setFont(font2);
					pane.add(reset,0,0);
					pane.add(exit,2,0);
					exit.setOnAction(this::processButtonPressed);
					reset.setOnAction(this::processButtonPressed);
				}
			}
		}
		if (e.getSource()==exit){
			System.exit(0);
		}
		else{
			for (int i=0; i<8; i++){
				for (int j=0; j<8; j++){
					if (e.getSource()==board[i][j]){
						//if hit a ship
						if(hit(shoot,ship)){
							board[i][j].setText("X");
							board[i][j].setDisable(true);
							board[i][j].setStyle("-fx-background-color: red");
						}
						//if not
						else if(!hit(shoot,ship)){
							board[i][j].setText("O");
							board[i][j].setDisable(true);
							board[i][j].setStyle("-fx-background-color: cyan");
						}
					}
				}
			}
		}
	}
	public static void main(String[] args){
		launch(args);
	}
}
