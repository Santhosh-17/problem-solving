package Fifteen_Puzzles;

public class Utils {
    public static void printRules() {
        System.out.println("Press w for UP");
        System.out.println("Press s for DOWN");
        System.out.println("Press a for LEFT");
        System.out.println("Press d for RIGHT");
        System.out.println("Press x for EXIT");
    }

    public static void findZerothIndex(int[][] puzzle) {
        for (int i = 0; i < puzzle.length; i++) {
            for (int j = 0; j < puzzle.length; j++) {
                if (puzzle[i][j] == 0) {
                    Game.zerothRow = i;
                    Game.zerothCol = j;
                }
            }
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void displayPuzzle(int[][] puzzle) {
        System.out.println("\n------------ 15 Puzzles ------------\n");
        for (int i = 0; i < puzzle.length; i++) {
            System.out.print(" | ");
            for (int j = 0; j < puzzle.length; j++) {
                if (puzzle[i][j] > 9) {
                    System.out.print(""+puzzle[i][j] + " | ");
                } else {
                    System.out.print(" " + puzzle[i][j] + " | ");
                }
            }
            System.out.println();
        }
        System.out.println();
        ;
    }
}
