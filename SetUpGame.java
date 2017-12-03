import java.util.*;

public class SetUpGame{

  public static final char seaSymbol = '~';
  static char[][] userBoard = new char[10][10];
  static char[][] pcBoard = new char[10][10];
  static ArrayList<Integer> size = new ArrayList<>();

  // public void setUpUser (char[][] user_field) {
  public static void main(String[] args) {
      // set up the sea char
      int count = 0;
      boolean fail;
      size.add(2);
      size.add(3);
      size.add(3);
      size.add(4);
      size.add(5);

      for (int i = 0; i < userBoard.length; i++){
        for (int j = 0; j < userBoard[i].length; j++){
          userBoard[i][j] = seaSymbol;
        }
      }

      for (int i = 0; i < pcBoard.length; i++){
        for (int j = 0; j < pcBoard[i].length; j++){
          pcBoard[i][j] = seaSymbol;
        }
      }

      do {
        fail = false;
        Scanner sc = new Scanner(System.in);
        int[][] location = new int[2][2];

        for (int i = 0; i < location.length; i++) {
            System.out.printf("Please enter one end of ship of size %d:%n", size.get(0));
            do {
              System.out.println("x: ");
              location[i][0] = sc.nextInt();
              if (location[i][0] < 1 || location[i][0] > 10) {
                System.out.println("please enter again!");
              }
            } while (location[i][0] < 1 || location[i][0] > 10);
            do {
              System.out.println("y: ");
              location[i][1] = sc.nextInt();
              if (location[i][1] < 1 || location[i][1] > 10) {
                System.out.println("please enter again!");
              }
            } while (location[i][1] < 1 || location[i][1] > 10);
          }

          if (location[1][0] == location[0][0]) {
            int length = location[1][1] - location[0][1] + 1;

            if (length != size.get(0)) {
              System.out.println("please re-enter the location");
              continue;
            }

            for (int k = location[0][1]; k <= location[1][1] ; k++) {
              if (userBoard[k - 1][location[0][0] - 1] == 'o') {
                System.out.println("please re-enter the location");
                fail = true;
                break;
              }
              userBoard[k - 1][location[0][0] - 1] = 'o';
              size.remove(0);
            }

          } else {
            int length = location[1][0] - location[0][0] + 1;
            if (length != size.get(0)) {
              System.out.println("please re-enter the location");
              continue;
            }
            for (int l = location[0][0]; l <= location[1][0] ; l++) {
              if (userBoard[l - 1][location[0][0] - 1] == 'o') {
                System.out.println("please re-enter the location");
                fail = true;
                break;
              }
              userBoard[location[0][1] - 1][l - 1] = 'o';
              size.remove(0);
            }
          }
          printGraph(userBoard, pcBoard);
          count++;
      } while (count < 5 || fail == true);
      // set up the location
      System.out.println("Baby! You are all set! ");

  }


    //PrintGraph method
  public static void printGraph(char[][] userBoard, char[][] pcBoard){

      printSea(pcBoard);

      System.out.println();
      for (int i = 0; i < 25; i++){
        System.out.print("-");
      }
      System.out.println();
      System.out.println();

      printSea(userBoard);
    }

    public static void printSea(char[][] board){
      System.out.print("y:x");
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