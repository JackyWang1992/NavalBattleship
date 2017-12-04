package print;

public class printGraph{
  public static void printGraph(char[][] userBoard, char[][] pcBoard){

      printSea(pcBoard);

      System.out.println();
      for (int i = 0; i < 25; i++){
          System.out.print("-");
      }
      System.out.println();
      System.out.println();

      printSea(userBoard);
      System.out.println();
      System.out.println();
  }

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
