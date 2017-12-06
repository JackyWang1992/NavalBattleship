package game;

/**
 Launcher is a class that used to launch the NavalBattleShip game
 */
public class Launcher {
    /**
    A launcher of the NavalBattleShip game
     @param args the command line arguments which are ignored
     */
    public static void main (String[] args) {
        NavalBattleship game = new NavalBattleship();
        game.run();

    }

}
