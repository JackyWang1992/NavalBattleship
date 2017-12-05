package attack;
import game.NavalBattleship;
import print.PrintGraph;
public class RobotAttack{
  int x, y;

  /**
    robot will start attack in this method
    @return whether your ships are hitted this time
  */
  public boolean robotAttack() {
      //whether enter choose a random point to hit
      if (NavalBattleship.isRandom) {
          return randomHit();
      //do not enter in random mode
      } else {
          //first point is hitted, try four directions of this point
          if (NavalBattleship.isTry) {
              return tryFourDirection();
          // we've already found one direction and enter in continue mode
          } else {
              return continueMode();
          }
      }
    }

    /**
      choose a random point to hit
      @return wether the chosen point is hitted
    */
    public boolean randomHit() {
      do {
          //choose a randon point
          x = (int)(Math.random() * 10);
          y = (int)(Math.random() * 10);
          //if we've already hitted the chose point, try another one
      } while (NavalBattleship.userBoard[x][y] == NavalBattleship.hitSymbol || NavalBattleship.userBoard[x][y] == NavalBattleship.missSymbol);

      //the point is hitted
      if (NavalBattleship.userBoard[x][y] == NavalBattleship.seaSymbol) {
          NavalBattleship.userBoard[x][y] = NavalBattleship.missSymbol;
          PrintGraph.printGraph(NavalBattleship.userBoard, NavalBattleship.showPCBoard);
          //enter in random mode next time
          NavalBattleship.isRandom = true;
          return false;
      //the point is missed
      } else {
          NavalBattleship.userBoard[x][y] = NavalBattleship.hitSymbol;
          PrintGraph.printGraph(NavalBattleship.userBoard, NavalBattleship.showPCBoard);
          NavalBattleship.isRandom = false;
          NavalBattleship.isTry = true;
          NavalBattleship.countDir = new boolean[4];
          NavalBattleship.fqprex = x;
          NavalBattleship.fqprey = y;
          return true;
      }
    }

    /**
      find which direction we need to try
      @return the index of direction we need to try
    */
    public int determineDirection() {
      int ig = 0;
      //find the direction we need to try and update this record
      for (ig = 0; ig < 4; ig++) {
          if (!NavalBattleship.countDir[ig]) {
              break;
          }
      }
      if (ig == 4) {
          ig = 3;
      }
      return ig;
    }

    /**
      try four directions
      @return wether the chosen point is hitted
    */
    public boolean tryFourDirection() {
      int ig = determineDirection();
      x = NavalBattleship.fqprex + NavalBattleship.fqdir[ig][0];
      y = NavalBattleship.fqprey + NavalBattleship.fqdir[ig][1];
      //if we've already tried this point, try another one
      if (x < 0 || x > 9 || y < 0 || y > 9 || NavalBattleship.userBoard[x][y] == NavalBattleship.hitSymbol || NavalBattleship.userBoard[x][y] == NavalBattleship.missSymbol) {
          return randomHit();
      //enter in tryFourDirection mode
      } else {
          //hitted
          if (NavalBattleship.userBoard[x][y] == NavalBattleship.seaSymbol) {
              NavalBattleship.userBoard[x][y] = NavalBattleship.missSymbol;
              PrintGraph.printGraph(NavalBattleship.userBoard, NavalBattleship.showPCBoard);
              updateDirection();
              NavalBattleship.isRandom = false;
              NavalBattleship.isTry = true;
              return false;
          //missed
          } else {
              NavalBattleship.userBoard[x][y] = NavalBattleship.hitSymbol;
              PrintGraph.printGraph(NavalBattleship.userBoard, NavalBattleship.showPCBoard);
              //enter in continueMode
              NavalBattleship.foundDir = ig;
              NavalBattleship.isRandom = false;
              NavalBattleship.isTry = false;
              NavalBattleship.fqprex = x;
              NavalBattleship.fqprey = y;
              return true;
          }
      }
    }

    /**
      update directions we need to try next time
    */
    public void updateDirection() {
      for (int i = 0; i < 4; i++) {
          if (!NavalBattleship.countDir[i]) {
              NavalBattleship.countDir[i] = true;
          }
      }
    }

    /**
      try next point in the direction we've already found
      @return whether the chosen point is hitted
    */
    public boolean continueMode() {
      x = NavalBattleship.fqprex + NavalBattleship.fqdir[NavalBattleship.foundDir][0];
      y = NavalBattleship.fqprey + NavalBattleship.fqdir[NavalBattleship.foundDir][1];
      //if we've already tried this point, try another one
      if (x < 0 || x > 9 || y < 0 || y > 9 || NavalBattleship.userBoard[x][y] == NavalBattleship.hitSymbol || NavalBattleship.userBoard[x][y] == NavalBattleship.missSymbol) {
          return randomHit();
      } else {
          if (NavalBattleship.userBoard[x][y] == NavalBattleship.seaSymbol) {
              NavalBattleship.userBoard[x][y] = NavalBattleship.missSymbol;
              PrintGraph.printGraph(NavalBattleship.userBoard, NavalBattleship.showPCBoard);
              //try next point in this direction
              NavalBattleship.isRandom = false;
              NavalBattleship.isTry = false;
              NavalBattleship.fqprex = x;
              NavalBattleship.fqprey = y;
              return false;
          } else {
              NavalBattleship.userBoard[x][y] = NavalBattleship.hitSymbol;
              PrintGraph.printGraph(NavalBattleship.userBoard, NavalBattleship.showPCBoard);
              //enter in random mode
              NavalBattleship.isRandom = true;
              return false;
          }
      }
    }
}
