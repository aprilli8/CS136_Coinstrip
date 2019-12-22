import java.util.Scanner;
import java.util.Random;

public class CoinStrip{
  private static int[] board;
  private static int currentPlayer;
  private static int numCoins;

  private static Random rand = new Random();
  private static Scanner scan = new Scanner(System.in);

  public CoinStrip(int nc){
    numCoins = nc;
    board = new int[rand.nextInt(3)+10];
  }

  public static void setBoard(){
    for(int i = 0; i < numCoins; i++){
      int assign = rand.nextInt(board.length);
      if(board[assign] == 0){
        board[assign] = 1;
      }
      else{
        i = i-1;
      }
    }

    int n = 0;
    for(int i = 0; i < board.length; i++){
      if(board[i] != 0){
        board[i] = n+1;
        n++;
      }
    }

    if(gameEnd() == true){
      setBoard();
    }
  }

  public static void displayBoard(){
    System.out.print(" ---------------------");
    if(board.length == 11){
      System.out.print("--");
    }else if(board.length == 12){
      System.out.print("----");
    }
    System.out.print("\n| ");
    for(int i = 0; i < board.length; i++){
      System.out.print(board[i] + " ");
    }
    System.out.println("|");
    System.out.print(" ---------------------");
    if(board.length == 11){
      System.out.print("--");
    }else if(board.length == 12){
      System.out.print("----");
    }
  }

  public static void takeATurn(){
    System.out.print("\nEnter the number of the coin you would like to move: ");
    int nc = scan.nextInt();
    System.out.print("Enter the number of spaces you would like to move: ");
    int ns = scan.nextInt();

    int currentIndex = 0;
    for(int i = 0; i < board.length; i++){
      if(board[i] == nc){
        currentIndex = i;
      }
    }
    boolean skipCoin = false;
    for(int i = currentIndex-ns; i < board.length; i++){
      if(board[i] < board[currentIndex] && board[i] != 0){
        skipCoin = true;
      }
    }
    if(currentIndex-ns>=0 && board[currentIndex-ns] == 0 && skipCoin == false){
      board[currentIndex-ns] = board[currentIndex];
      board[currentIndex] = 0;
    }
    else{
      System.out.print("Not a valid option. ");
      takeATurn();
    }

  }

  public static void switchPlayer(){
    if(currentPlayer == 1){
      currentPlayer = 2;
    }
    else{
      currentPlayer = 1;
    }
  }

  public static boolean gameEnd(){
    int tally = 0;
    for(int i = 0; i < numCoins; i++){
      if(board[i] != 0){
        tally++;
      }
    }
    if(tally == numCoins){
      return true;
    }
    else{
      return false;
    }
  }

  public static void main(String[] args){
    System.out.print("Please enter a value (between 3 and 5) for the number of coins in the game: ");
    CoinStrip game = new CoinStrip(scan.nextInt());
    setBoard();
    displayBoard();
    currentPlayer = 2;

    while(gameEnd() == false){
      switchPlayer();
      takeATurn();
      displayBoard();
    }

    System.out.println("\nCongratulations Player " + currentPlayer);
  }
}
