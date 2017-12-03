/*
                  玩家攻击电脑

              1 2 3 4 5 6 7 8 9 10
            1  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
            2  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
            3  ~ ~ ~ * ~ ~ ~ ~ ~ ~
            4  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
            5  ~ ~ x ~ ~ ~ ~ ~ ~ ~
            6  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
            7  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
            8  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
            9  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
            10 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~

            -------------------------

             1 2 3 4 5 6 7 8 9 10
            1  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
            2  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
            3  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
            4  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
            5  ~ ~ ~ o ~ ~ ~ ~ ~ ~
            6  ~ ~ ~ o ~ ~ ~ o ~ ~
            7  ~ ~ ~ o ~ ~ ~ o ~ ~
            8  ~ ~ ~ ~ ~ ~ ~ o ~ ~
            9  ~ ~ o o o ~ ~ o ~ ~
            10 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
*/


public class UserAttack(char[][] initialPcBoard, char[][] userBoard, char[][] pcBoard){
    Scanner sc = new Scanner(System.in);
    boolean isBoat = true;
    while (isBoat){
      System.out.println("Please enter the x coordinate you want to attack :\n");
      int x = sc.nextInt();
      System.out.println("Please enter the y coordinate you want to attack :\n");
      int y = sc.nextInt();
      sc.close();
      for (int i = 0; i < pcBoard.length; i++){
        for (int j = 0; j < pcBoard[0].length; j++){
          if (initialPcBoard[x][y] == shipSymbol){
            pcBoard[x][y] = hitSymbol;
          } else if (initialPcBoard[x][y] == seaSymbol){
            pcBoard[x][y] = missSymbol;
            isBoat = false;
            if (isEnd()){
              break;
            }
          }
        }
      }
      printGraph(userBoard, pcBoard);
    }
}
