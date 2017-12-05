package set;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import game.NavalBattleship;

import javax.print.attribute.standard.MediaSize;

import static print.PrintGraph.printGraph;

public class UserSetUp {


    public void userStart(){
        ArrayList<Integer> size = new ArrayList<>();
        // set up the sea char
        int count = 0;
        boolean fail;
        size.add(2);
        size.add(3);
        size.add(3);
        size.add(4);
        size.add(5);

        for (int i = 0; i < NavalBattleship.userBoard.length; i++){
            for (int j = 0; j < NavalBattleship.userBoard[i].length; j++){
                NavalBattleship.userBoard[i][j] = NavalBattleship.seaSymbol;
            }
        }

        for (int i = 0; i < NavalBattleship.showPCBoard.length; i++){
            for (int j = 0; j < NavalBattleship.showPCBoard[i].length; j++){
                NavalBattleship.showPCBoard[i][j] = NavalBattleship.seaSymbol;
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
                    if (NavalBattleship.userBoard[location[0][0] - 1][k - 1] == NavalBattleship.shipSymbol) {
                        System.out.println("please re-enter the location");
                        fail = true;
                        break;
                    }
                    NavalBattleship.userBoard[location[0][0] - 1][k - 1] = NavalBattleship.shipSymbol;
                }

            } else {
                int length = location[1][0] - location[0][0] + 1;
                if (length != size.get(0)) {
                    System.out.println("please re-enter the location");
                    continue;
                }
                for (int l = location[0][0]; l <= location[1][0] ; l++) {
                    if (NavalBattleship.userBoard[l - 1][location[0][1] - 1] == NavalBattleship.shipSymbol) {
                        System.out.println("please re-enter the location");
                        fail = true;
                        break;
                    }
                    NavalBattleship.userBoard[l - 1][location[0][1] - 1] = NavalBattleship.shipSymbol;
                }
            }
            if (fail) {
                continue;
            }
            printGraph(NavalBattleship.userBoard, NavalBattleship.pcBoard);
            size.remove(0);
            count++;
        } while (count < NavalBattleship.shipLen.length || fail == true);
        // set up the location
        System.out.println("Baby! You are all set! ");
        //userAttack();
        //char[][] initialPcBoard, char[][] userBoard, char[][] pcBoard
    }
    }

    