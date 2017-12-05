package set;

import game.NavalBattleship;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BoatSetUp{
  public void setupRobot() {
      //boolean[][] visited = new boolean[10][10];
      int count = 0;
      for (int i = 0; i < NavalBattleship.shipLen.length; i++) {
          Random rdm = new Random();
          boolean contin = false;
          int x = rdm.nextInt(10);
          int y = rdm.nextInt(10);
          List<Integer> posDir = checkValid(NavalBattleship.shipLen[i], x, y);

          while (NavalBattleship.pcBoard[x][y] == NavalBattleship.shipSymbol && posDir.size() == 0) {

              x = rdm.nextInt(10);
              y = rdm.nextInt(10);
              posDir = checkValid(NavalBattleship.shipLen[i], x, y);
          }
          int dir = rdm.nextInt(4);
          while (!posDir.contains(dir)) {
              dir = rdm.nextInt(4);
          }

          NavalBattleship.pcBoard[x][y] = NavalBattleship.shipSymbol;

          if (dir == 0){

              for (int k = 1; k < NavalBattleship.shipLen[i]; k++) {
                  count++;

                  NavalBattleship.pcBoard[x][y - k] = NavalBattleship.shipSymbol;
                  //visited[x][y - k] = true;

              }


          }
          //down
          else if (dir == 1) {

              for (int k = 1; k < NavalBattleship.shipLen[i]; k++) {
                  count++;

                  NavalBattleship.pcBoard[x][y + k] = NavalBattleship.shipSymbol;
                  //visited[x][y + k] = true;

              }


          }
          //right
          else if (dir == 2) {

              for (int k = 1; k < NavalBattleship.shipLen[i]; k++) {
                  count++;

                  NavalBattleship.pcBoard[x + k][y] = NavalBattleship.shipSymbol;
                  //visited[x + k][y] = true;

              }
          }
          else if(dir == 3){

              for (int k = 1; k < NavalBattleship.shipLen[i]; k++) {
                  count++;

                  NavalBattleship.pcBoard[x - k][y] = NavalBattleship.shipSymbol;
                  //visited[x - k][y] = true;

              }

          }
      }

  }
  private List<Integer> checkValid(int shipLen, int x, int y) {
      List<Integer> list = new ArrayList<>();
      for (int dir = 0; dir < 4; dir++) {
          if (checkDir(dir, shipLen, x, y))
              list.add(dir);
      }

      return list;
  }
  private boolean checkDir(int dir, int shipLen, int x, int y) {
      switch (dir) {
          //go up
          case 0: {
              if (y - shipLen - 1 < 0) return false;
              for (int k = 1; k < shipLen; k++) {

                  if (NavalBattleship.pcBoard[x][y - k] == NavalBattleship.shipSymbol)
                      return false;

              }
              break;
          }
          //down
          case 1: {
              if (y + shipLen - 1 >= NavalBattleship.pcBoard.length)
                  return false;
              for (int k = 1; k < shipLen; k++) {
                  if (NavalBattleship.pcBoard[x][y + k] == NavalBattleship.shipSymbol)
                      return false;

              }
              break;
          }
          //right
          case 2: {
              if (x + shipLen - 1 >= NavalBattleship.pcBoard.length)
                  return false;
              for (int k = 1; k < shipLen; k++) {

                  if (NavalBattleship.pcBoard[x + k][y] == NavalBattleship.shipSymbol)
                      return false;

              }
              break;

          }
          case 3: {
              if (x - shipLen - 1 < 0) return false;
              for (int k = 1; k < shipLen; k++) {

                  if (NavalBattleship.pcBoard[x - k][y] == NavalBattleship.shipSymbol)
                      return false;

              }
              break;
          }
      }
      return true;
  }

}
