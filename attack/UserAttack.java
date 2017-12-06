package attack;
import game.NavalBattleship;
import print.PrintGraph;

import java.util.Scanner;
/**
    User starts attack in this method
    It will not return anything
  */
public class UserAttack{
  public void userAttack() {
      Scanner sc = new Scanner(System.in);
      boolean isBoat = true;
      while (isBoat){
          //Ask for the postion user wants to attack
          System.out.println("Please enter the x coordinate you want to attack :\n");
          int x = sc.nextInt() - 1;
          System.out.println("Please enter the y coordinate you want to attack :\n");
          int y = sc.nextInt() - 1;

          //If user has hit the enemy ship, one more chance to attack will be given.
          if (NavalBattleship.pcBoard[x][y] == NavalBattleship.shipSymbol){
              NavalBattleship.pcBoard[x][y] = NavalBattleship.hitSymbol;
              NavalBattleship.showPCBoard[x][y] = NavalBattleship.hitSymbol;
              System.out.println("You have hit PC's ship! You are going to hit again.");
          //If user has missed the enemy ship, no more chance to attack will be given in this round
          } else if (NavalBattleship.pcBoard[x][y] == NavalBattleship.seaSymbol){
              NavalBattleship.pcBoard[x][y] = NavalBattleship.missSymbol;
              NavalBattleship.showPCBoard[x][y] = NavalBattleship.missSymbol;
              isBoat = false;
              System.out.println("You didin't hit PC's shit. It's PC's turn.");
          }
          //If all enemy ships are hit, game is over
          if (NavalBattleship.isEnd(NavalBattleship.pcBoard)){
              break;
          }
          //Print the game board after every attak round.
          PrintGraph.printGraph(NavalBattleship.userBoard, NavalBattleship.showPCBoard);
      }
  }
}
