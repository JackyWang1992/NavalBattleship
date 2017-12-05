package game;
import java.util.*;
import set.*;
import attack.*;
import print.*;


public class NavalBattleship {
    public static boolean gameContinue = true;
    public static boolean roundContinue = true;
    public static char[][] pcBoard;
    public static char[][] userBoard;
    public static char[][] showPCBoard;
    //private char[][] uI_field;
    public static final char seaSymbol = '~';
    public static final char shipSymbol = 'o';
    public static final char hitSymbol = '*';
    public static final char missSymbol = 'x';

    public static final int[] shipLen = {2, 3, 3, 4, 5};
    // private int[] shipLen = {5};
    // private int totalPoints = 5;
    public static final int totalPoints = 17;


    public static char[][] fqcurRecord;
    public static boolean isRandom;
    public static boolean isTry;
    public static boolean[] countDir;
    public static int[][] fqdir = {{1,0}, {-1, 0}, {0, 1}, {0, -1}};
    public static int fqprex;
    public static int fqprey;
    public static int foundDir;




    public void run() {

        System.out.println("Welcome to Naval Battle Field!");

        while (gameContinue) {

            Scanner scanner = new Scanner(System.in);
            System.out.println("Do you want to start a new game?");
            String input = scanner.nextLine();
            if (input.equals("No.")) {
                break;
            }
            else {
                boolean roundContinue = true;
                userBoard = new char[10][10];
                pcBoard = new char[10][10];
                showPCBoard = new char[10][10];



                fqcurRecord = new char[10][10];
                isRandom = true;
                isTry = true;
                countDir = new boolean[4];

                fqprex = -1;
                fqprey = -1;
                foundDir = 0;


                for (int i = 0; i < pcBoard.length; i++) {
                    for (int j = 0; j < pcBoard[0].length; j++) {
                        pcBoard[i][j] = seaSymbol;
                        userBoard[i][j] = seaSymbol;
                        showPCBoard[i][j] = seaSymbol;
                    }

                }
                //printGraph(userBoard, pcBoard);
                PrintGraph.printGraph(userBoard, pcBoard);
                UserSetUp.userStart();
                BoatSetUp.setupRobot();

                fqcurRecord = userBoard;

                while (roundContinue) {
                    boolean index;
                    UserAttack.userAttack();
                    do {
                        index = RobotAttack.robotAttack();
                        if (index)
                            System.out.println("PC has hit your ship!");
                    }while (index);

                    if (isEnd(userBoard) || isEnd(pcBoard)) {
                        System.out.print("Game Over!");
                        if (isEnd(pcBoard)) {
                            System.out.println("You win!");
                        }
                        else {
                            System.out.println("PC win!");
                        }
                        break;
                    }

                }

            }
        }

    }

    
    public static boolean isEnd(char[][] board) {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == hitSymbol)
                    count++;
            }
        }
        return count == totalPoints ? true : false;
    }

}
