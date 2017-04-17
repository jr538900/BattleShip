/*
//battleShipGame.java

import java.util.Random;
import java.util.Scanner;
import java.ShipGrid;
import java.Grid;
import java.Ship;

public class battleShipGame {

        // name of the player/game
        private String name;

        // user will place boats on userShip whereas ai will place boats on aiShip
        private ShipGrid userShip;
        private ShipGrid aiShip;

        UIBoard board;

        // initialise the ships
        Ship[] ships = new Ship[3];

        // userTurn becomes true whenever the it's the user's turn
        // after user takes the turn, it becomes false
        private boolean userTurn;

        // opponent of AI type
        private AI opponent;

        public void setUpGame()
        {
          userTurn = false;

          initShips (ships);
          // in ShipGrid?

          userShip = new ShipGrid();
          AIShip = new ShipGrid();

          // initialise the board, 2-dimensional array, with 8 rows, 8 columns
          // assign every cell on the board a value of -1 (or any default value), which means those cells
          // just contain water for the timebeing, or are empty in other words
          // this function is present in UIGrid file
          board = new UIBoard();

          // initialise the AI opponent
          opponent = new AI();

          name = "player1";

          // initialise shoot, and attempts, shothit to 0
          int[] shoot = new int[2];
          int attempts = 0,
              shotHit = 0;

        }

        public boolean gameIsFinished()
        {
          // becomes true when 3 of the shots hit, 3 ships sink of either player
          return (this.shotHit == 3);
        }

        // get methods

        public String getName()
        {
          return this.name;
        }

        public AI getOpponent()
        {
          return this.opponent;
        }

        public ShipGrid getUserShip()
        {
          return this.userShip;
        }

        public ShipGrid getAIShip()
        {
          return this.AIShip;
        }

        // set methods

        public void setName(String name)
        {
          this.name = name;
        }

        public void setOpponent(AI opponent)
        {
          this.opponent = opponent;
        }

        public void setUserShip(ShipGrid userShip)
        {
          this.userShip = userShip;
        }

        public void setAIShip(ShipGrid AIShip)
        {
          this.AIShip = AIShip;
        }

        //constructor
        public battleShipGame()
        {

        do{
            // show the Board in each attempts
            // shoot in each attempt, and increment the value of attempts accordingly
            showBoard(board);
            shoot(shoot);
            attempts++;

            // check if our shot hit a ship or not
            if(hit(shoot,ships)){
                // we hit a ship, therefore we increase the shotHit
                // rest of the implementation takes place in hint() function
                hint(shoot,ships,attempts);
                shotHit++;
            }
            else
                hint(shoot,ships,attempts);

            // updates the board/grid accordingly
            changeboard(shoot,ships,board);

        }
        while(!gameIsFinished());

        // when shotHit becomes 3, you've gotten all the enemy's ships and you win
        System.out.println("\n\n\nBattleship Java game finished! You hit 3 ships in "+attempts+" attempts");
        showBoard(board);
      }

    public static void showBoard(int[][] board){
        // giving 8 tabs and a new line for proper allignment when printing 8x8 board
        System.out.println("\t1 \t2 \t3 \t4 \t5 \t6 \t7 \t8");
        System.out.println();

        for(int row=0 ; row < 8 ; row++ ){
            System.out.print((row+1)+"");
            for(int column=0 ; column < 8 ; column++ ){
                if(board[row][column]==-1){
                    // this is just water, no ship, no attempt
                    System.out.print("\t"+"~");
                }else if(board[row][column]==0){
                    // this is the position where shots have been fired previously
                    System.out.print("\t"+"*");
                }else if(board[row][column]==1){
                    // this is the position where, a ship/part of a ship, is present
                    System.out.print("\t"+"X");
                }

            }
            System.out.println();
        }

    }

    // in ShipGrid?
    public static void initShips(Ship[] ships){
        Random random = new Random();

        for(int ship = 0 ; ship < 3 ; ship++){
            // Initialise each ship using Ship(lengthX, lengthY) constructor
            // length of the ships are 1,3,4
            // width is 1
            if (ship == 0)
              ships[ship] = new Ship(1, 1);
            else
              ships[ship] = new Ship(1, ship + 2);

            // checks if postions of the ships are clashing
            // if they are clashing, keep on assigning x,y random values until there is no more clash

            // position the ship on userGrid only if valid
            // these is the user's ships
            do {
              int positionX = random.nextInt(5);
              int positionY = random.nextInt(5);
            }
            while (!userGrid.shipIsValid(positionX, positionY, ships[ship])){
            userGrid.add(positionX, positionY, ships[ship]);

            // position the ship on aiGrid only if valid
            // these are the ai's ships

            do {
              int positionX = random.nextInt(5);
              int positionY = random.nextInt(5);
            }
            }
            while (!aiGrid.shipIsValid(positionX, positionY, ships[ship]))

            aiGrid.add(positionX, positionY, ships[ship]);

            }

        }
    }

    public static void shoot(int[] shoot){
        Scanner input = new Scanner(System.in);
        // shoot[0] is the x coordinate
        // shoot[1] is the y coordinate
        // where (x,y) is the position we want to
        // shoot at in the grid
        System.out.print("Row: ");
        shoot[0] = input.nextInt();
        shoot[0]--;

        System.out.print("Column: ");
        shoot[1] = input.nextInt();
        shoot[1]--;

    }

    // function tells whether your shot hit a ship or not
    public static boolean hit(int[] shoot, Ship[] ships){

        for(int ship=0 ; ship < ships.length ; ship++){
            if ((ships[ship].lengthX <= abs(shoot[0] - getShipPositionX(ships[ship]))) && (ships[ship].lengthY <= abs(shoot[1] - getShipPositionY(ships[ship])))){
                System.out.printf("You hit a ship located in (%d,%d)\n",getShipPositionX(ships[ship]), getShipPositionY(ships[ship]));
                return true;
            }
        }
        return false;
    }

    public static void hint(int[] shoot, Ship[] ships, int attempt){
        int row=0,
            column=0;

        // this loops updates the row/column according to the shot fired
        // and makes sure no repetition takes place
        for(int line=0 ; line < ships.length ; line++){
            if (hit, shoot, ships)
            {
              row++;
              column++;
            }
        }

        System.out.printf("\nHint %d: \nRow %d -> %d ships\n" +
                                 "Column %d -> %d ships\n",attempt,shoot[0]+1,row,shoot[1]+1,column);
    }

    // update the board according to whether the shots hit or miss...in UI grid?
    public static void changeboard(int[] shoot, int[][] ships, int[][] board){
        if(hit(shoot,ships))
            // the shot hit a ship
            board[shoot[0]][shoot[1]]=1;
        else
            // the shot missed
            board[shoot[0]][shoot[1]]=0;
    }
}
*/