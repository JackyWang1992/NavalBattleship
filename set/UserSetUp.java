package set;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import game.NavalBattleship;

import javax.print.attribute.standard.MediaSize;

import print.PrintGraph;
/**
  this class setup the userBoard
  put 5 different ships with length: 2, 3, 3, 4, 5
  then print the "all set" message
*/
public class UserSetUp {
    private static ArrayList<Integer> size;
    private static Scanner sc;

    /**
      this method start the userSetUp
      finally will tell you "you are all set"
    */
    public void userStart(){
        size = new ArrayList<>();
        // set up the sea char
        int count = 0;
        boolean fail;
        size.add(2);
        size.add(3);
        size.add(3);
        size.add(4);
        size.add(5);
        // call the setUpSea method for userBoard and showPCBoard
        setUpSea(NavalBattleship.userBoard);

        setUpSea(NavalBattleship.showPCBoard);
        // start to put five different ships
        do {
            fail = false;
            sc = new Scanner(System.in);
            int[][] location = new int[2][2];
            location = setUpLocation(location);
            fail = setUpOneShip(NavalBattleship.userBoard, location, fail);
            if (fail) {
                continue;
            }
            PrintGraph.printGraph(NavalBattleship.userBoard, NavalBattleship.pcBoard);
            size.remove(0);
            count++;
        } while (count < NavalBattleship.shipLen.length || fail == true);
        // print the final information
        System.out.println("Baby! You are all set! ");
    }

    /**
      this method setUpSea put seaSymbol in a char array
      make the array like the sea
      @param board a char array to put the seaSymbol "~""
    */
    public static void setUpSea(char[][] board){
      for (int i = 0; i < board.length; i++){
          for (int j = 0; j < board[i].length; j++){
              board[i][j] = NavalBattleship.seaSymbol;
          }
      }
    }
    /**
     this method set the location of one ship and check if the input is legal
     we have to prevent the user to enter non-integer or integers which too
     big or too small
     @param location the integer 2D array to put the coordinate of two ends of ship
     @return the 2D array of location which represent the ship's location
    */
    public static int[][] setUpLocation(int[][] location){

      for (int i = 0; i < location.length; i++) {
          if (i == 0) {
            System.out.printf("Please enter the head of ship of size %d:%n", size.get(0));
          } else {
            System.out.printf("Please enter the tail of ship of size %d:%n", size.get(0));
          }
          boolean isContinue;
          do {
              isContinue = false;
              System.out.println("x: ");
              try {
                location[i][0] = sc.nextInt();
              } catch (Exception e) {
                sc.next();
                isContinue = true;
              }
              if (location[i][0] < 1 || location[i][0] > 10) {
                  System.out.println("please enter again!");
              }
          } while (location[i][0] < 1 || location[i][0] > 10 || isContinue);
          do {
              System.out.println("y: ");
              try {
                location[i][1] = sc.nextInt();
              } catch (Exception e) {
                sc.next();
                isContinue = true;
              }
              if (location[i][1] < 1 || location[i][1] > 10) {
                  System.out.println("please enter again!");
              }
          } while (location[i][1] < 1 || location[i][1] > 10 || isContinue == true);
      }
      return location;
    }

    /**
      this method put the ship into the userBoard, which set ships by change seaSymbol
      to shipSymbol in the userBoard.
      @param userBoard the 2D char array which represent users' sea
      @param location the 2D integer array which represent the ship's location
      @param fail the boolean which decide whether to input again
      @return the boolean which decide whether to input again
    */
    public static boolean setUpOneShip(char[][] userBoard, int[][] location, boolean fail){

      if (location[1][0] == location[0][0]) {
          int length = location[1][1] - location[0][1] + 1;
          if (length != size.get(0)) {
              System.out.println("please re-enter the location");
              fail = true;
              return fail;
          }

          for (int k = location[0][1]; k <= location[1][1] ; k++) {
              if (userBoard[location[0][0] - 1][k - 1] == NavalBattleship.shipSymbol) {
                  System.out.println("please re-enter the location");
                  fail = true;
                  return fail;
              }
              userBoard[location[0][0] - 1][k - 1] = NavalBattleship.shipSymbol;
          }

      } else {
          int length = location[1][0] - location[0][0] + 1;
          if (length != size.get(0)) {
              System.out.println("please re-enter the location");
              fail = true;
              return fail;
          }
          for (int l = location[0][0]; l <= location[1][0] ; l++) {
              if (userBoard[l - 1][location[0][1] - 1] == NavalBattleship.shipSymbol) {
                  System.out.println("please re-enter the location");
                  fail = true;
                  return fail;
              }
              userBoard[l - 1][location[0][1] - 1] = NavalBattleship.shipSymbol;
          }
      }
      return fail;
    }

  }
