import java.util.Arrays;
import java.util.Scanner;

public class NavalBattleship {
    public void run() {

        System.out.println("Welcome to Naval Battle Field!");
        boolean gameContinue = true;
        while (gameContinue) {
            Scanner scanner = new Scanner(System.in);

        }

        char[][] user_field = new char[11][11];
        char[][] robot_field = new char[11][11];


        Arrays.fill(user_field, '~');
        Arrays.fill(robot_field, '~');

        userStart();



    }
    public void print() {

    }
    public void userStart() {

    }
}
