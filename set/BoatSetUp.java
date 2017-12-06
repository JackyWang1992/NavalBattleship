package set;

import game.NavalBattleship;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BoatSetUp{

    /**
     setupRobot() will generate the ship locations and setup ships of PC
     */
    public void setupRobot() {
      for (int i = 0; i < NavalBattleship.shipLen.length; i++) {
          Random rdm = new Random();
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
          putShip(dir, i, x, y);
      }
  }

    /**
     putShip(dir, i, x, y) will put the ship from (x,y)
     @param dir an integer indicates the direction to set boat
     @param i an integer indicates which ship is now placing
     @param x the x coordinate of the head of the ship
     @param y the y coordinate of the head of the ship
     @return
     */
  private void putShip(int dir, int i, int x, int y) {
      NavalBattleship.pcBoard[x][y] = NavalBattleship.shipSymbol;
      //up
      if (dir == 0){
          for (int k = 1; k < NavalBattleship.shipLen[i]; k++) NavalBattleship.pcBoard[x][y - k] = NavalBattleship.shipSymbol;
      }
      //down
      else if (dir == 1) {
          for (int k = 1; k < NavalBattleship.shipLen[i]; k++) NavalBattleship.pcBoard[x][y + k] = NavalBattleship.shipSymbol;
      }
      //right
      else if (dir == 2) {
          for (int k = 1; k < NavalBattleship.shipLen[i]; k++) NavalBattleship.pcBoard[x + k][y] = NavalBattleship.shipSymbol;
      }
      //left
      else if(dir == 3){
          for (int k = 1; k < NavalBattleship.shipLen[i]; k++) NavalBattleship.pcBoard[x - k][y] = NavalBattleship.shipSymbol;
      }
  }
    /**
     checkValid(shipLen, x, y) will return whether the coordinate of the head of the ship is valid
     @param shipLen an integer indicates the current ship length
     @param x the x coordinate of the head of the ship
     @param y the y coordinate of the head of the ship
     @return a list of valid directions
     */
  private List<Integer> checkValid(int shipLen, int x, int y) {
      List<Integer> list = new ArrayList<>();
      for (int dir = 0; dir < 4; dir++) {
          if (checkDir(dir, shipLen, x, y))
              list.add(dir);
      }

      return list;
  }
    /**
     checkValid(shipLen, x, y) will return whether the coordinate of the head of the ship is valid
     @param shipLen an integer indicates the current ship length
     @param x the x coordinate of the head of the ship
     @param y the y coordinate of the head of the ship
     @return a boolean indicates whether the input direction is valid
     */
  private boolean checkDir(int dir, int shipLen, int x, int y) {
      switch (dir) {
          //go up
          case 0: {
              return checkUp(shipLen, x, y);
          }
          //down
          case 1: {
              return checkDown(shipLen, x, y);
          }
          //right
          case 2: {
              return checkLeft(shipLen, x, y);
          }
          case 3: {
              return checkRight(shipLen, x, y);
          }
      }
      return true;
  }
    /**
     checkUp(shipLen, x, y) will return whether the coordinate of the head of the ship is valid to place it going up
     @param shipLen an integer indicates the current ship length
     @param x the x coordinate of the head of the ship
     @param y the y coordinate of the head of the ship
     @return a boolean indicates whether the input coordinate of the head of the ship is valid to place it going up
     */
  private boolean checkUp(int shipLen, int x, int y) {
      if (y - shipLen - 1 < 0) return false;
      for (int k = 1; k < shipLen; k++) {
          if (NavalBattleship.pcBoard[x][y - k] == NavalBattleship.shipSymbol) return false;
      }
      return true;
  }
    /**
     checkDown(shipLen, x, y) will return whether the coordinate of the head of the ship is valid to place it going down
     @param shipLen an integer indicates the current ship length
     @param x the x coordinate of the head of the ship
     @param y the y coordinate of the head of the ship
     @return a boolean indicates whether the input coordinate of the head of the ship is valid to place it going down
     */
  private boolean checkDown(int shipLen, int x, int y) {
      if (y + shipLen - 1 >= NavalBattleship.pcBoard.length) return false;
      for (int k = 1; k < shipLen; k++) {
          if (NavalBattleship.pcBoard[x][y + k] == NavalBattleship.shipSymbol) return false;
      }
      return true;
  }
    /**
     checkUp(shipLen, x, y) will return whether the coordinate of the head of the ship is valid to place it going left
     @param shipLen an integer indicates the current ship length
     @param x the x coordinate of the head of the ship
     @param y the y coordinate of the head of the ship
     @return a boolean indicates whether the input coordinate of the head of the ship is valid to place it going left
     */
  private boolean checkLeft(int shipLen, int x, int y) {
      if (x + shipLen - 1 >= NavalBattleship.pcBoard.length) return false;
      for (int k = 1; k < shipLen; k++) {
          if (NavalBattleship.pcBoard[x + k][y] == NavalBattleship.shipSymbol) return false;
      }
      return true;
  }
    /**
     checkUp(shipLen, x, y) will return whether the coordinate of the head of the ship is valid to place it going right
     @param shipLen an integer indicates the current ship length
     @param x the x coordinate of the head of the ship
     @param y the y coordinate of the head of the ship
     @return a boolean indicates whether the input coordinate of the head of the ship is valid to place it going right
     */
  private boolean checkRight(int shipLen, int x, int y) {
      if (x - shipLen - 1 < 0) return false;
      for (int k = 1; k < shipLen; k++) {
          if (NavalBattleship.pcBoard[x - k][y] == NavalBattleship.shipSymbol) return false;
      }
      return true;
  }

}
