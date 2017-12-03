import java.util.*;

public class NavalBattleship {
    private boolean gameContinue = true;
    private boolean roundContinue = true;
    private char[][] robot_field;
    private char[][] user_field;
    private char seaSymbol = '~';
    private char shipSymbol = 'o';
    private char hitSymbol = '*';
    private char missSymbol = 'x';
    private int[] shipLen = {2, 3, 3, 4, 5};



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
                user_field = new char[10][10];
                robot_field = new char[10][10];

                for (int i = 0; i < robot_field.length; i++) {
                    for (int j = 0; j < robot_field[0].length; j++) {
                        robot_field[i][j] = seaSymbol;
                        user_field[i][j] = seaSymbol;
                    }

                }
                printGraph(user_field,robot_field);
                userStart();
                setupRobot();

                printGraph(user_field,robot_field);
                while (roundContinue) {
                    //userAttack();
                    //robotAttack();
                    break;
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

            while (robot_field[x][y] == shipSymbol && posDir.size() == 0) {

                x = rdm.nextInt(10);
                y = rdm.nextInt(10);
                posDir = checkValid(shipLen[i], x, y);
            }
            int dir = rdm.nextInt(4);
            while (!posDir.contains(dir)) {
                dir = rdm.nextInt(4);
            }

            robot_field[x][y] = shipSymbol;

            if (dir == 0){

                for (int k = 1; k < shipLen[i]; k++) {
                    count++;

                    robot_field[x][y - k] = shipSymbol;
                    //visited[x][y - k] = true;

                }


            }
            //down
            else if (dir == 1) {

                for (int k = 1; k < shipLen[i]; k++) {
                    count++;

                    robot_field[x][y + k] = shipSymbol;
                    //visited[x][y + k] = true;

                }


            }
            //right
            else if (dir == 2) {

                for (int k = 1; k < shipLen[i]; k++) {
                    count++;

                    robot_field[x + k][y] = shipSymbol;
                    //visited[x + k][y] = true;

                }
            }
            else if(dir == 3){

                for (int k = 1; k < shipLen[i]; k++) {
                    count++;

                    robot_field[x - k][y] = shipSymbol;
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

                    if (robot_field[x][y - k] == shipSymbol)
                        return false;

                }
                break;
            }
            //down
            case 1: {
                if (y + shipLen - 1 >= robot_field.length)
                    return false;
                for (int k = 1; k < shipLen; k++) {
                    if (robot_field[x][y + k] == shipSymbol)
                        return false;

                }
                break;
            }
            //right
            case 2: {
                if (x + shipLen - 1 >= robot_field.length)
                    return false;
                for (int k = 1; k < shipLen; k++) {

                    if (robot_field[x + k][y] == shipSymbol)
                        return false;

                }
                break;

            }
            case 3: {
                if (x - shipLen - 1 < 0) return false;
                for (int k = 1; k < shipLen; k++) {

                    if (robot_field[x - k][y] == shipSymbol)
                        return false;

                }
                break;
            }
        }
        return true;
    }

    private static void printGraph(char[][] userBoard, char[][] pcBoard){
        System.out.print("   ");
        for (int i = 1; i <= 10; i++){
            System.out.print(i);
            System.out.print(" ");
        }
        System.out.println();
        for (int i = 0; i < pcBoard.length; i++){
            System.out.print(i + 1);
            System.out.print(" ");
            System.out.print(" ");
            for (int j = 0; j < pcBoard[i].length; j++){
                System.out.print(pcBoard[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
        for (int i = 0; i < 25; i++){
            System.out.print("-");
        }
        System.out.println();
        System.out.println();
        System.out.print("   ");
        for (int i = 1; i <= 10; i++){
            System.out.print(i);
            System.out.print(" ");
        }
        System.out.println();
        for (int i = 0; i < userBoard.length; i++){
            System.out.print(i + 1);
            System.out.print(" ");
            System.out.print(" ");
            for (int j = 1; j < userBoard[i].length; j++){
                System.out.print(userBoard[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }


    private void userStart() {

    }
    private void userAttack() {

    }
    private void robotAttack() {
        char[][] visited = new char[10][10];
        List<int[]> tryFirst = new ArrayList<>();
        List<int[]> hitShip = new ArrayList<>();
        Random rdm = new Random();
        if (hitShip.size() == 0) {
            int x = rdm.nextInt(10);
            int y = rdm.nextInt(10);
            if (robot_field[x][y] == shipSymbol) {

            }
        }

    }
    private void helper() {

    }


}
