package print;

public class PrintGraph{
  public static void printGraph(char[][] userBoard, char[][] pcBoard){
      //print out the game board for PC
      printSea(pcBoard);
      
      System.out.println();
      for (int i = 0; i < 25; i++){
          System.out.print("-");
      }
      System.out.println();
      System.out.println();
      //print out the game board for User
      printSea(userBoard);
      System.out.println();
      System.out.println();
  }
  //Print game board with x, y coordinates in the range of [1, 10]
  public static void printSea(char[][] board){
      System.out.print("x:y");
      for (int i = 1; i <= 10; i++){
          System.out.print(i);
          System.out.print(" ");
      }
      System.out.println();
      for (int i = 0; i < board.length; i++){
          if ( i == 9) {
              System.out.print(i + 1);
              System.out.print(" ");
          } else {
              System.out.print(i + 1);
              System.out.print(" ");
              System.out.print(" ");
          }

          for (int j = 0; j < board[i].length; j++){
              System.out.print(board[i][j]);
              System.out.print(" ");
          }
          System.out.println();
      }
  }
}
