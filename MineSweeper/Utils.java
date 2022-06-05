package MineSweeper;

public class Utils {

    public static void printMessage() {
        System.out.println("\n-------------- CONGRATULATIONS !! --------------\n");
        System.out.println("               YOU WON THE GAME                \n\n");
        Main.playAgain();
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
}
