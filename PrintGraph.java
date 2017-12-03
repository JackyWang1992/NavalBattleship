public class PrintGraph{
  public static final char seaSymbol = '~';
  public static void main(String[] args){
    //test cases
    char[][] userBoard = new char[10][10];
    for (int i = 0; i < userBoard.length; i++){
      for (int j = 0; j < userBoard[i].length; j++){
        userBoard[i][j] = seaSymbol;
      }
    }
    userBoard[4][3] = 'o';
    userBoard[5][3] = 'o';
    userBoard[6][3] = 'o';
    userBoard[8][2] = 'o';
    userBoard[8][3] = 'o';
    userBoard[8][4] = 'o';
    userBoard[5][7] = 'o';
    userBoard[5][8] = 'o';
    userBoard[5][9] = 'o';
    char[][] pcBoard = new char[10][10];
    for (int i = 0; i < pcBoard.length; i++){
      for (int j = 0; j < pcBoard[i].length; j++){
        pcBoard[i][j] = seaSymbol;
      }
    }
    printGraph(userBoard, pcBoard);
  }

  //PrintGraph method
  public static void printGraph(char[][] userBoard, char[][] pcBoard){
    System.out.print("   ");
    for (int i = 1; i <= 10; i++){
      System.out.print(i);
      System.out.print(" ");
    }
    System.out.println();
    for (int i = 0; i < pcBoard.length; i++){
        System.out.print(i + 1);
        System.out.print(" ");
        System.out.print(" ");
        for (int j = 0; j < pcBoard[i].length; j++){
            System.out.print(pcBoard[i][j]);
            System.out.print(" ");
        }
        System.out.println();
    }
    System.out.println();
    for (int i = 0; i < 25; i++){
      System.out.print("-");
    }
    System.out.println();
    System.out.println();
    System.out.print("   ");
    for (int i = 1; i <= 10; i++){
      System.out.print(i);
      System.out.print(" ");
    }
    System.out.println();
    for (int i = 0; i < userBoard.length; i++){
        System.out.print(i + 1);
        System.out.print(" ");
        System.out.print(" ");
        for (int j = 0; j < userBoard[i].length; j++){
            System.out.print(userBoard[i][j]);
            System.out.print(" ");
        }
        System.out.println();
    }
  }
}
