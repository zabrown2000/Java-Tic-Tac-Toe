import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();  //this is a global (so all methods know it) array list of integers for player positions
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

    public static void main(String[] args) {
        char [] [] gameBoard = { {' ', '|', ' ', '|', ' '}, 
                                 {'-', '+', '-', '+', '-'},
                                 {' ', '|', ' ', '|', ' '},
                                 {'-', '+', '-', '+', '-'},
                                 {' ', '|', ' ', '|', ' '}, };
          
        printGameBoard(gameBoard);
        
        
       
        while (true) { //adding a loop/true statement in so there will be more than 1 round-cpu will always be waiting for player to make next move
            Scanner scan = new Scanner(System.in); //scans position user will choose
            System.out.println("Enter your placement (1-9):"); //choosing place on the board (9 spots)
            int playerPos = scan.nextInt(); //did this after made cpuPos to make it clearer
            //need to put something in so cpu can't place a piece over player's
            while(playerPositions.contains(playerPos) || cpuPositions.contains(playerPositions)) { //have to keep asking until get right pos, while they don't input right pos, the user plays either player or cpu pos
                System.out.println("Position taken! Enter a correct Position");
                playerPos = scan.nextInt(); //now scans to see what new postion was requested
            } 
            
            placePiece(gameBoard, playerPos, "player"); //now can call on method, and this time using user "player"
       
            String result = checkWinner();
            if(result.length() > 0) {
                System.out.println(result);
                break; 
            }
            Random rand = new Random(); //for pos, need to randomize it or add some AI (add in some libraries)
            int cpuPos = rand.nextInt(9) + 1; //now saying want pos of cpu user to be random, from 1-9
            while(playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) { //need to do same thing for cpu
                //now will keep asking cpu to mak another position if one randomly chosen is taken
                cpuPos = rand.nextInt(9) + 1;
            } 
            placePiece(gameBoard, cpuPos, "cpu");//this is all good now, but now we need to add computer and have it go more than once
                                  
            printGameBoard(gameBoard); //now after you choose placement, it puts an x in that spot

            result = checkWinner(); //won't work until add the winning pos to the array lists on top, after they play a piece
            if(result.length() > 0) {
                System.out.println(result);
                break; //breaks out of loop in case computer can't find a pos
            }
            System.out.println(result); //now it takes the winner which is stored in result and prints it
        } //placing all these in infinite loop-until create something to end it-checkWinner method
        

        

        /* switch(pos) { //now creating a case to assign x/o to each place
            case 1: //in the case that the position chosen is 1
                gameBoard[0][0] = 'x'; //position of this case is 0,0 because it's in the first row, first column (quote), asign it x
                break;
            case 2: 
                gameBoard[0][2] = 'x'; //row 0, 3rd element/column-counting starts with 0 in arrays
                break;
            case 3: 
                gameBoard[0][4] = 'x'; 
                break;
            case 4: 
                gameBoard[2][0] = 'x'; //now move down 2 rows -2nd row is - and +
                break;
            case 5: 
                gameBoard[2][2] = 'x'; 
                break;
            case 6: 
                gameBoard[2][4] = 'x'; 
                break;
            case 7: 
                gameBoard[4][0] = 'x'; 
                break;
            case 8: 
                gameBoard[4][2] = 'x'; 
                break;
            case 9: 
                gameBoard[4][4] = 'x'; 
                break;
            
        } making this into a method - too messy to put in main method*/ 
       
    }   
    public static void printGameBoard (char [] [] gameBoard) {
        for (char [] row : gameBoard) { /*for each array in each row in each game board */
            for (char c : row) { /*for each char called c, inside the row, will print c */ 
                 System.out.print(c); /*took away the ln so it would print nicer */
            } 
            System.out.println(); 
    }
}

public static void placePiece(char[][] gameBoard, int pos, String user) { //it needs to know it's taking in gameboard, position, and the user-player or computer (x or o)
    
    //now gonna say if user is player, symbol will be x, if cpu, it will be o
    char symbol = ' '; //now can change in switch case to print out symbol, which will depend on user
    if(user.equals("player")) { //using .equals because it's a string, can't use ==
        symbol = 'X';
        playerPositions.add(pos); //if player makes a move, add that to the int pos so game can keep track if something is winning or not
    } else if(user.equals("cpu")) {
        symbol = 'O';
        cpuPositions.add(pos);
    }
    
    switch(pos) { //now creating a case to assign x/o to each place
        case 1: //in the case that the position chosen is 1
            gameBoard[0][0] = symbol; //position of this case is 0,0 because it's in the first row, first column (quote), asign it x
            break;
        case 2: 
            gameBoard[0][2] = symbol; //row 0, 3rd element/column-counting starts with 0 in arrays
            break;
        case 3: 
            gameBoard[0][4] = symbol; 
            break;
        case 4: 
            gameBoard[2][0] = symbol; //now move down 2 rows -2nd row is - and +
            break;
        case 5: 
            gameBoard[2][2] = symbol; 
            break;
        case 6: 
            gameBoard[2][4] = symbol; 
            break;
        case 7: 
            gameBoard[4][0] = symbol; 
            break;
        case 8: 
            gameBoard[4][2] = symbol; 
            break;
        case 9: 
            gameBoard[4][4] = symbol; 
            break;
        default: //always need to add this at end of switch
            break; 
        
    }
}
public static String checkWinner() { //will need loop to end, so have to tell computer when game is over
    //have to determine what all winning moves are and where they would be located in the array
    List topRow = Arrays.asList(1, 2, 3); //these 3 lines are for the rows winning moves
    List midRow = Arrays.asList(4, 5, 6);
    List botRow = Arrays.asList(7, 8, 9);
    List leftCol = Arrays.asList(1, 4, 7); //these 3 lines are for the columns winning moves
    List midCol = Arrays.asList(2, 5, 8);
    List rightCol = Arrays.asList(3, 6, 9);
    List cross1 = Arrays.asList(1, 5, 9);  //these are for the diagnal winning moves
    List cross2 = Arrays.asList(7, 5, 3);

    List<List> winning = new ArrayList<List>(); //putting all winning conditions in 1 list so it's a lot cleaner
    winning.add(topRow);
    winning.add(midRow);
    winning.add(botRow);
    winning.add(leftCol);
    winning.add(midCol);
    winning.add(rightCol);
    winning.add(cross1);
    winning.add(cross2);

    for (List l : winning) {  //creating for loop creating list in winning list
        if(playerPositions.containsAll(l)) { //if player pos has a method called contains all with a collection, lists act like collections so can use them here
            return "Congradulations you won!";
        }  else if(cpuPositions.contains(l)) { //if cpu has one of the postions it loops through
            return "CPU wins! Sorry :(";
        }  else if(playerPositions.size() + cpuPositions.size() == 9) { //now checking for a tie, if board is full and none of winning pos are taken
            return "CAT!";
        }
    } 
    
    return "";
}

}