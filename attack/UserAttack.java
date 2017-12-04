package attack;

public class UserAttack{
  private void userAttack() {
      Scanner sc = new Scanner(System.in);
      boolean isBoat = true;
      while (isBoat){
          System.out.println("Please enter the x coordinate you want to attack :\n");
          int x = sc.nextInt() - 1;
          System.out.println("Please enter the y coordinate you want to attack :\n");
          int y = sc.nextInt() - 1;


          if (pcBoard[x][y] == shipSymbol){
              pcBoard[x][y] = hitSymbol;
              showPCBoard[x][y] = hitSymbol;
          } else if (pcBoard[x][y] == seaSymbol){
              pcBoard[x][y] = missSymbol;
              showPCBoard[x][y] = missSymbol;
              isBoat = false;

          }
          if (isEnd(pcBoard)){
              break;
          }
          printGraph(userBoard, showPCBoard);
      }
  }
}
