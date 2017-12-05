package attack;
import game.NavalBattleship;
import print.PrintGraph;

import java.util.Scanner;

public class UserAttack{
  public void userAttack() {
      Scanner sc = new Scanner(System.in);
      boolean isBoat = true;
      while (isBoat){
          System.out.println("Please enter the x coordinate you want to attack :\n");
          int x = sc.nextInt() - 1;
          System.out.println("Please enter the y coordinate you want to attack :\n");
          int y = sc.nextInt() - 1;


          if (NavalBattleship.pcBoard[x][y] == NavalBattleship.shipSymbol){
              NavalBattleship.pcBoard[x][y] = NavalBattleship.hitSymbol;
              NavalBattleship.showPCBoard[x][y] = NavalBattleship.hitSymbol;
          } else if (NavalBattleship.pcBoard[x][y] == NavalBattleship.seaSymbol){
              NavalBattleship.pcBoard[x][y] = NavalBattleship.missSymbol;
              NavalBattleship.showPCBoard[x][y] = NavalBattleship.missSymbol;
              isBoat = false;

          }
          if (NavalBattleship.isEnd(NavalBattleship.pcBoard)){
              break;
          }
          PrintGraph.printGraph(NavalBattleship.userBoard, NavalBattleship.showPCBoard);
      }
  }
}
