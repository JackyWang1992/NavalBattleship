package attack;
import game.NavalBattleship;
import print.PrintGraph;
public class RobotAttack{
  int x, y;
  public boolean robotAttack() {
      //是否随机打
      if (NavalBattleship.isRandom) {
          return randomHit();
          //不随机
      } else {
          //尝试四个方向
          if (NavalBattleship.isTry) {
              return tryFourDirection();
              //continue模式
          } else {
              return continueMode();
          }
      }
    }

    public boolean randomHit() {
      do {
          x = (int)(Math.random() * 10);
          y = (int)(Math.random() * 10);
      } while (NavalBattleship.userBoard[x][y] == NavalBattleship.hitSymbol || NavalBattleship.userBoard[x][y] == NavalBattleship.missSymbol);

      if (NavalBattleship.userBoard[x][y] == NavalBattleship.seaSymbol) {
          NavalBattleship.userBoard[x][y] = NavalBattleship.missSymbol;
          PrintGraph.printGraph(NavalBattleship.userBoard, NavalBattleship.showPCBoard);
          //设置各种参数
          NavalBattleship.isRandom = true;
          return false;
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

    public int determineDirection() {
      int ig = 0;
      //记录方向到第几个
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

    public boolean tryFourDirection() {
      int ig = determineDirection();
      x = NavalBattleship.fqprex + NavalBattleship.fqdir[ig][0];
      y = NavalBattleship.fqprey + NavalBattleship.fqdir[ig][1];
      //重复的时候随机打
      if (x < 0 || x > 9 || y < 0 || y > 9 || NavalBattleship.userBoard[x][y] == NavalBattleship.hitSymbol || NavalBattleship.userBoard[x][y] == NavalBattleship.missSymbol) {
          return randomHit();
          //不重复找方向
      } else {
          if (NavalBattleship.userBoard[x][y] == NavalBattleship.seaSymbol) {
              NavalBattleship.userBoard[x][y] = NavalBattleship.missSymbol;
              PrintGraph.printGraph(NavalBattleship.userBoard, NavalBattleship.showPCBoard);
              updateDirection();
              NavalBattleship.isRandom = false;
              NavalBattleship.isTry = true;
              return false;
          } else {
              NavalBattleship.userBoard[x][y] = NavalBattleship.hitSymbol;
              PrintGraph.printGraph(NavalBattleship.userBoard, NavalBattleship.showPCBoard);
              //设置参数进入continue模式
              NavalBattleship.foundDir = ig;
              NavalBattleship.isRandom = false;
              NavalBattleship.isTry = false;
              NavalBattleship.fqprex = x;
              NavalBattleship.fqprey = y;
              return true;
          }
      }
    }

    public void updateDirection() {
      for (int i = 0; i < 4; i++) {
          if (!NavalBattleship.countDir[i]) {
              NavalBattleship.countDir[i] = true;
          }
      }
    }

    public boolean continueMode() {
      x = NavalBattleship.fqprex + NavalBattleship.fqdir[NavalBattleship.foundDir][0];
      y = NavalBattleship.fqprey + NavalBattleship.fqdir[NavalBattleship.foundDir][1];
      //重复的时候随机打
      if (x < 0 || x > 9 || y < 0 || y > 9 || NavalBattleship.userBoard[x][y] == NavalBattleship.hitSymbol || NavalBattleship.userBoard[x][y] == NavalBattleship.missSymbol) {
          return randomHit();
      } else {
          if (NavalBattleship.userBoard[x][y] == NavalBattleship.seaSymbol) {
              NavalBattleship.userBoard[x][y] = NavalBattleship.missSymbol;
              PrintGraph.printGraph(NavalBattleship.userBoard, NavalBattleship.showPCBoard);
              //设置参数继续寻找
              NavalBattleship.isRandom = false;
              NavalBattleship.isTry = false;
              NavalBattleship.fqprex = x;
              NavalBattleship.fqprey = y;
              return false;
          } else {
              NavalBattleship.userBoard[x][y] = NavalBattleship.hitSymbol;
              PrintGraph.printGraph(NavalBattleship.userBoard, NavalBattleship.showPCBoard);
              //设置参数进入随机模式
              NavalBattleship.isRandom = true;
              return false;
          }
      }
    }
}
