//battleShipGame.java

import java.util.Random;
import java.util.Scanner;

public class battleShipGame {

    public static void main(String[] args) {
        //initialises the board, which is a 2-dimensional array, with 8 rows, 8 columns
        //assign every cell a value of -1 meaning those cells haven't been visited yet
        int[][] board = new int[8][8];
        initBoard(board);

        //initialise ships, as ships[i][j]
        //maybe change to Ship[][] ships so that it uses Ship object?
        int[][] ships = new int[3][2];
        initShips(ships);

        //initialise shoot, attempts, and shotHit to 0
        int[] shoot = new int[2];
        int attempts=0,
            shotHit=0;


        System.out.println();

        do{
            //shows the Board in each attempt
            //shoot in each attempt, and increment the value of attempts accordingly
            showBoard(board);
            shoot(shoot);
            attempts++;

            //check if shot hit a ship or not
            if(hit(shoot,ships)){
                //hit a ship, therefore we increase the shotHit
                //rest of the implementation takes place in hint() function
                hint(shoot,ships,attempts);
                shotHit++;
            }
            else
                hint(shoot,ships,attempts);

            //updates the board/grid accordingly
            changeboard(shoot,ships,board);


        }
        while(shotHit!=3);

        //when shotHit becomes 3 = gotten all the enemy's ships and win
        System.out.println("\n\n\nBattleship Java game finished! You hit 3 ships in "+attempts+" attempts");
        showBoard(board);
    }

    //assign every cell on the board a value of -1, which means those cells are empty
    public static void initBoard(int[][] board){
        for(int row=0 ; row < 5 ; row++ )
            for(int column=0 ; column < 5 ; column++ )
                board[row][column]=-1;
    }

    public static void showBoard(int[][] board){
        //gives 5 tabs and a new line for proper allignment when printing 8x8 board
        System.out.println("\t1 \t2 \t3 \t4 \t5");
        System.out.println();

        for(int row=0 ; row < 5 ; row++ ){
            System.out.print((row+1)+"");
            for(int column=0 ; column < 5 ; column++ ){
                if(board[row][column]==-1){
                    //this is just water, no ship, no attempt
                    System.out.print("\t"+"~");
                }else if(board[row][column]==0){
                    //this is the position where shots have been fired previously
                    System.out.print("\t"+"*");
                }else if(board[row][column]==1){
                    //this is the position where a ship/part of a ship is present
                    System.out.print("\t"+"X");
                }

            }
            System.out.println();
        }

    }

    public static void initShips(int[][] ships){
        Random random = new Random();

        for(int ship=0 ; ship < 3 ; ship++){
            //randomly assign the x and y postions of the ship
            ships[ship][0]=random.nextInt(5);
            ships[ship][1]=random.nextInt(5);

            //checks if postions of the ships are clashing
            //if they are clashing, keep on assigning x,y random values until there is no more clash
            for(int last=0 ; last < ship ; last++){
                if( (ships[ship][0] == ships[last][0])&&(ships[ship][1] == ships[last][1]) )
                    do{ //assign different x,y as current x,y clashing with other ship
                        ships[ship][0]=random.nextInt(5);
                        ships[ship][1]=random.nextInt(5);
                    }while( (ships[ship][0] == ships[last][0])&&(ships[ship][1] == ships[last][1]) );
            }

        }
    }

    public static void shoot(int[] shoot){
        Scanner input = new Scanner(System.in);
        //shoot[0] is the x coordinate
        //shoot[1] is the y coordinate
        //where (x,y) is the position we want to shoot at in the grid
        System.out.print("Row: ");
        shoot[0] = input.nextInt();
        shoot[0]--;

        System.out.print("Column: ");
        shoot[1] = input.nextInt();
        shoot[1]--;

    }

//this function tells us that whether shot hit a ship or not
    public static boolean hit(int[] shoot, int[][] ships){

        for(int ship=0 ; ship<ships.length ; ship++){
            if( shoot[0]==ships[ship][0] && shoot[1]==ships[ship][1]){
                System.out.printf("You hit a ship located in (%d,%d)\n",shoot[0]+1,shoot[1]+1);
                return true;
            }
        }
        return false;
    }

    //this function performs the required actions depending upon whether the shot hit a ship or not

    public static void hint(int[] shoot, int[][] ships, int attempt){
        int row=0,
            column=0;

        //loop updates the row/column according to the shot fired and makes sure no repetition takes place
        for(int line=0 ; line < ships.length ; line++){
            if(ships[line][0]==shoot[0])
                row++;
            if(ships[line][1]==shoot[1])
                column++;
        }

        System.out.printf("\nHint %d: \nRow %d -> %d ships\n" +
                                 "Column %d -> %d ships\n",attempt,shoot[0]+1,row,shoot[1]+1,column);
    }

    //updates the board according to whether the shots hit or miss
    public static void changeboard(int[] shoot, int[][] ships, int[][] board){
        if(hit(shoot,ships))
            //the shot hit a ship
            board[shoot[0]][shoot[1]]=1;
        else
            //the shot missed
            board[shoot[0]][shoot[1]]=0;
    }
}