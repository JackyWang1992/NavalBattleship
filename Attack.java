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
