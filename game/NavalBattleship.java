package game;
import java.util.*;
import set.*;
import attack.*;
import static print.PrintGraph.printGraph;

/**
 NavalBattleShip is a game about ship battles. It allows you to put your ships in some positions horizontally or vertically,
 and the user needs to guess the positions of the opponent’s ships. The program will tell you if you hit a ship or not.
 Player who hits all of the ships of opponent will win. Anyone who hits the ship have the chance to continue attack.
 */

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


    /**
     * run() will start the whole game NavalBattleship, user can play one round by one round
     */
    public void run() {
        System.out.println("Welcome to Naval Battle Field!");
        while (gameContinue) {
            gameContinue = playOneGame();
        }
    }

    /**
     playOneGame() will start a round of NavalBattleship,
     @param
     @return a boolean indicate whether user want to play another round
     */
    private static boolean playOneGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to start a new game?");
        String input = scanner.nextLine();
        if (input.equals("No.") || input.equals("n") || input.equals("no")) return false;
        else {
            boolean roundContinue = true;
            initialize();
            for (int i = 0; i < pcBoard.length; i++) {
                for (int j = 0; j < pcBoard[0].length; j++) {
                    pcBoard[i][j] = seaSymbol;
                    userBoard[i][j] = seaSymbol;
                    showPCBoard[i][j] = seaSymbol;
                }
            }
            printGraph(userBoard, pcBoard);
            setupShip();
            printGraph(userBoard, pcBoard);
            fqcurRecord = userBoard;
            while (roundContinue) {
                roundContinue = playOneStep();
            }
        }
        return true;
    }
    /**
     playOneStep() returns the boolean indicate whether the game is over
     @param
     @return a boolean indicate whether the game is over
     */
    private static boolean playOneStep() {
        boolean index;
        UserAttack userAttack = new UserAttack();
        userAttack.userAttack();
        do {
            RobotAttack robotAttack = new RobotAttack();
            index = robotAttack.robotAttack();
            if (index) {
                System.out.println("PC has hit your ship! PC is going to hit again.");
                System.out.println();
            }
            else {
                System.out.println("PC didn't hit your ship. It's your turn.");
                System.out.println();
            }
        }while (index);
        if (isEnd(userBoard) || isEnd(pcBoard)) {
            System.out.print("Game Over!");
            if (isEnd(pcBoard)) System.out.println("You win!");
            else System.out.println("PC win!");
            return false;
        }
        return true;
    }

    /**
     * setupShip() will setup both user's and PC's ships
     */
    private static void setupShip() {
        UserSetUp userSetUp = new UserSetUp();
        userSetUp.userStart(); //call
        BoatSetUp boatSetUp = new BoatSetUp();
        boatSetUp.setupRobot();
    }
    /**
     * initialize() will initialize the variables that will be used
     */
    private static void initialize() {
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
    }


    /**
     isEnd(board) returns a boolean indicates whether this round of the game is end
     @param board an two dimensions array of char that represents the see with ships
     @return a boolean indicates whether this round of the game is end
     */
    public static boolean isEnd(char[][] board) {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == hitSymbol)
                    count++;
            }
        }
        return (count == totalPoints);
    }

}
