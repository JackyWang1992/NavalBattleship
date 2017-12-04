package set;

public class UserSetUp{

  private void userStart() {
      size = new ArrayList<>();
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

      for (int i = 0; i < showPCBoard.length; i++){
          for (int j = 0; j < showPCBoard[i].length; j++){
              showPCBoard[i][j] = seaSymbol;
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
                  if (userBoard[location[0][0] - 1][k - 1] == shipSymbol) {
                      System.out.println("please re-enter the location");
                      fail = true;
                      break;
                  }
                  userBoard[location[0][0] - 1][k - 1] = shipSymbol;
              }

          } else {
              int length = location[1][0] - location[0][0] + 1;
              if (length != size.get(0)) {
                  System.out.println("please re-enter the location");
                  continue;
              }
              for (int l = location[0][0]; l <= location[1][0] ; l++) {
                  if (userBoard[l - 1][location[0][1] - 1] == shipSymbol) {
                      System.out.println("please re-enter the location");
                      fail = true;
                      break;
                  }
                  userBoard[l - 1][location[0][1] - 1] = shipSymbol;
              }
          }
          if (fail) {
              continue;
          }
          printGraph(userBoard, pcBoard);
          size.remove(0);
          count++;
      } while (count < shipLen.length || fail == true);
      // set up the location
      System.out.println("Baby! You are all set! ");
      //userAttack();
      //char[][] initialPcBoard, char[][] userBoard, char[][] pcBoard
  }
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

  public boolean robotAttack() {
      int x, y;
      //是否随机打
      if (isRandom) {
          do {
              x = (int)(Math.random() * 10);
              y = (int)(Math.random() * 10);
          } while (userBoard[x][y] == hitSymbol || userBoard[x][y] == missSymbol);

          if (userBoard[x][y] == seaSymbol) {
              userBoard[x][y] = missSymbol;
              printGraph(userBoard, showPCBoard);
              //设置各种参数
              isRandom = true;
              return false;
          } else {
              userBoard[x][y] = hitSymbol;
              printGraph(userBoard, showPCBoard);
              isRandom = false;
              isTry = true;
              countDir = new boolean[4];
              fqprex = x;
              fqprey = y;
              return true;
          }
          //不随机
      } else {
          //尝试四个方向
          if (isTry) {
              int ig = 0;
              //记录方向到第几个
              for (ig = 0; ig < 4; ig++) {
                  if (!countDir[ig]) {
                      break;
                  }
              }
              if (ig == 4) {
                  ig = 3;
              }
              x = fqprex + fqdir[ig][0];
              y = fqprey + fqdir[ig][1];
              //重复的时候随机打
              if (x < 0 || x > 9 || y < 0 || y > 9 || userBoard[x][y] == hitSymbol || userBoard[x][y] == missSymbol) {
                  do {
                      x = (int)(Math.random() * 10);
                      y = (int)(Math.random() * 10);
                  } while (userBoard[x][y] == hitSymbol || userBoard[x][y] == missSymbol);

                  if (userBoard[x][y] == seaSymbol) {
                      userBoard[x][y] = missSymbol;
                      printGraph(userBoard, showPCBoard);
                      isRandom = true;
                      return false;
                  } else {
                      userBoard[x][y] = hitSymbol;
                      printGraph(userBoard, showPCBoard);
                      isRandom = false;
                      isTry = true;
                      countDir = new boolean[4];
                      fqprex = x;
                      fqprey = y;
                      return true;
                  }
                  //不重复找方向
              } else {
                  if (userBoard[x][y] == seaSymbol) {
                      userBoard[x][y] = missSymbol;
                      printGraph(userBoard, showPCBoard);
                      for (int i = 0; i < 4; i++) {
                          if (!countDir[i]) {
                              countDir[i] = true;
                          }
                      }
                      isRandom = false;
                      isTry = true;
                      return false;
                  } else {
                      userBoard[x][y] = hitSymbol;
                      printGraph(userBoard, showPCBoard);
                      //设置参数进入continue模式
                      foundDir = ig;
                      isRandom = false;
                      isTry = false;
                      fqprex = x;
                      fqprey = y;
                      return true;
                  }
              }
              //continue模式
          } else {
              x = fqprex + fqdir[foundDir][0];
              y = fqprey + fqdir[foundDir][1];
              //重复的时候随机打
              if (x < 0 || x > 9 || y < 0 || y > 9 || userBoard[x][y] == hitSymbol || userBoard[x][y] == missSymbol) {
                  do {
                      x = (int)(Math.random() * 10);
                      y = (int)(Math.random() * 10);
                  } while (userBoard[x][y] == hitSymbol || userBoard[x][y] == missSymbol);

                  if (userBoard[x][y] == seaSymbol) {
                      userBoard[x][y] = missSymbol;
                      printGraph(userBoard, showPCBoard);
                      isRandom = true;
                      return false;
                  } else {
                      userBoard[x][y] = hitSymbol;
                      printGraph(userBoard, showPCBoard);
                      isRandom = false;
                      isTry = true;
                      countDir = new boolean[4];
                      fqprex = x;
                      fqprey = y;
                      return true;
                  }
              } else {
                  if (userBoard[x][y] == seaSymbol) {
                      userBoard[x][y] = missSymbol;
                      printGraph(userBoard, showPCBoard);
                      //设置参数继续寻找
                      isRandom = false;
                      isTry = false;
                      fqprex = x;
                      fqprey = y;
                      return false;
                  } else {
                      userBoard[x][y] = hitSymbol;
                      printGraph(userBoard, showPCBoard);
                      //设置参数进入随机模式
                      isRandom = true;
                      return false;
                  }
              }
          }
      }
}
private void setupRobot() {
    //boolean[][] visited = new boolean[10][10];
    int count = 0;
    for (int i = 0; i < shipLen.length; i++) {
        Random rdm = new Random();
        boolean contin = false;
        int x = rdm.nextInt(10);
        int y = rdm.nextInt(10);
        List<Integer> posDir = checkValid(shipLen[i], x, y);

        while (pcBoard[x][y] == shipSymbol && posDir.size() == 0) {

            x = rdm.nextInt(10);
            y = rdm.nextInt(10);
            posDir = checkValid(shipLen[i], x, y);
        }
        int dir = rdm.nextInt(4);
        while (!posDir.contains(dir)) {
            dir = rdm.nextInt(4);
        }

        pcBoard[x][y] = shipSymbol;

        if (dir == 0){

            for (int k = 1; k < shipLen[i]; k++) {
                count++;

                pcBoard[x][y - k] = shipSymbol;
                //visited[x][y - k] = true;

            }


        }
        //down
        else if (dir == 1) {

            for (int k = 1; k < shipLen[i]; k++) {
                count++;

                pcBoard[x][y + k] = shipSymbol;
                //visited[x][y + k] = true;

            }


        }
        //right
        else if (dir == 2) {

            for (int k = 1; k < shipLen[i]; k++) {
                count++;

                pcBoard[x + k][y] = shipSymbol;
                //visited[x + k][y] = true;

            }
        }
        else if(dir == 3){

            for (int k = 1; k < shipLen[i]; k++) {
                count++;

                pcBoard[x - k][y] = shipSymbol;
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

                if (pcBoard[x][y - k] == shipSymbol)
                    return false;

            }
            break;
        }
        //down
        case 1: {
            if (y + shipLen - 1 >= pcBoard.length)
                return false;
            for (int k = 1; k < shipLen; k++) {
                if (pcBoard[x][y + k] == shipSymbol)
                    return false;

            }
            break;
        }
        //right
        case 2: {
            if (x + shipLen - 1 >= pcBoard.length)
                return false;
            for (int k = 1; k < shipLen; k++) {

                if (pcBoard[x + k][y] == shipSymbol)
                    return false;

            }
            break;

        }
        case 3: {
            if (x - shipLen - 1 < 0) return false;
            for (int k = 1; k < shipLen; k++) {

                if (pcBoard[x - k][y] == shipSymbol)
                    return false;

            }
            break;
        }
    }
    return true;
}

private void userStart() {
    size = new ArrayList<>();
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

    for (int i = 0; i < showPCBoard.length; i++){
        for (int j = 0; j < showPCBoard[i].length; j++){
            showPCBoard[i][j] = seaSymbol;
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
                if (userBoard[location[0][0] - 1][k - 1] == shipSymbol) {
                    System.out.println("please re-enter the location");
                    fail = true;
                    break;
                }
                userBoard[location[0][0] - 1][k - 1] = shipSymbol;
            }

        } else {
            int length = location[1][0] - location[0][0] + 1;
            if (length != size.get(0)) {
                System.out.println("please re-enter the location");
                continue;
            }
            for (int l = location[0][0]; l <= location[1][0] ; l++) {
                if (userBoard[l - 1][location[0][1] - 1] == shipSymbol) {
                    System.out.println("please re-enter the location");
                    fail = true;
                    break;
                }
                userBoard[l - 1][location[0][1] - 1] = shipSymbol;
            }
        }
        if (fail) {
            continue;
        }
        printGraph(userBoard, pcBoard);
        size.remove(0);
        count++;
    } while (count < shipLen.length || fail == true);
    // set up the location
    System.out.println("Baby! You are all set! ");
    //userAttack();
    //char[][] initialPcBoard, char[][] userBoard, char[][] pcBoard
}
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

public boolean robotAttack() {
    int x, y;
    //是否随机打
    if (isRandom) {
        do {
            x = (int)(Math.random() * 10);
            y = (int)(Math.random() * 10);
        } while (userBoard[x][y] == hitSymbol || userBoard[x][y] == missSymbol);

        if (userBoard[x][y] == seaSymbol) {
            userBoard[x][y] = missSymbol;
            printGraph(userBoard, showPCBoard);
            //设置各种参数
            isRandom = true;
            return false;
        } else {
            userBoard[x][y] = hitSymbol;
            printGraph(userBoard, showPCBoard);
            isRandom = false;
            isTry = true;
            countDir = new boolean[4];
            fqprex = x;
            fqprey = y;
            return true;
        }
        //不随机
    } else {
        //尝试四个方向
        if (isTry) {
            int ig = 0;
            //记录方向到第几个
            for (ig = 0; ig < 4; ig++) {
                if (!countDir[ig]) {
                    break;
                }
            }
            if (ig == 4) {
                ig = 3;
            }
            x = fqprex + fqdir[ig][0];
            y = fqprey + fqdir[ig][1];
            //重复的时候随机打
            if (x < 0 || x > 9 || y < 0 || y > 9 || userBoard[x][y] == hitSymbol || userBoard[x][y] == missSymbol) {
                do {
                    x = (int)(Math.random() * 10);
                    y = (int)(Math.random() * 10);
                } while (userBoard[x][y] == hitSymbol || userBoard[x][y] == missSymbol);

                if (userBoard[x][y] == seaSymbol) {
                    userBoard[x][y] = missSymbol;
                    printGraph(userBoard, showPCBoard);
                    isRandom = true;
                    return false;
                } else {
                    userBoard[x][y] = hitSymbol;
                    printGraph(userBoard, showPCBoard);
                    isRandom = false;
                    isTry = true;
                    countDir = new boolean[4];
                    fqprex = x;
                    fqprey = y;
                    return true;
                }
                //不重复找方向
            } else {
                if (userBoard[x][y] == seaSymbol) {
                    userBoard[x][y] = missSymbol;
                    printGraph(userBoard, showPCBoard);
                    for (int i = 0; i < 4; i++) {
                        if (!countDir[i]) {
                            countDir[i] = true;
                        }
                    }
                    isRandom = false;
                    isTry = true;
                    return false;
                } else {
                    userBoard[x][y] = hitSymbol;
                    printGraph(userBoard, showPCBoard);
                    //设置参数进入continue模式
                    foundDir = ig;
                    isRandom = false;
                    isTry = false;
                    fqprex = x;
                    fqprey = y;
                    return true;
                }
            }
            //continue模式
        } else {
            x = fqprex + fqdir[foundDir][0];
            y = fqprey + fqdir[foundDir][1];
            //重复的时候随机打
            if (x < 0 || x > 9 || y < 0 || y > 9 || userBoard[x][y] == hitSymbol || userBoard[x][y] == missSymbol) {
                do {
                    x = (int)(Math.random() * 10);
                    y = (int)(Math.random() * 10);
                } while (userBoard[x][y] == hitSymbol || userBoard[x][y] == missSymbol);

                if (userBoard[x][y] == seaSymbol) {
                    userBoard[x][y] = missSymbol;
                    printGraph(userBoard, showPCBoard);
                    isRandom = true;
                    return false;
                } else {
                    userBoard[x][y] = hitSymbol;
                    printGraph(userBoard, showPCBoard);
                    isRandom = false;
                    isTry = true;
                    countDir = new boolean[4];
                    fqprex = x;
                    fqprey = y;
                    return true;
                }
            } else {
                if (userBoard[x][y] == seaSymbol) {
                    userBoard[x][y] = missSymbol;
                    printGraph(userBoard, showPCBoard);
                    //设置参数继续寻找
                    isRandom = false;
                    isTry = false;
                    fqprex = x;
                    fqprey = y;
                    return false;
                } else {
                    userBoard[x][y] = hitSymbol;
                    printGraph(userBoard, showPCBoard);
                    //设置参数进入随机模式
                    isRandom = true;
                    return false;
                }
            }
        }
    }


}
