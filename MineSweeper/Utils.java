package MineSweeper;

public class Utils {
    static int winScore = 0;

    public static void printMessage() {
        winScore += 100;
        System.out.println("\n-------------- CONGRATULATIONS !! --------------\n");
        System.out.println("               YOU WON THE GAME                \n");
        System.out.println("                Score = " + winScore);
        Utils.timeTaken();
        Main.playAgain();
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    static void displayMineField(int n, char[][] displayField) {
        System.out.println("\n  --------------------------------- Minesweeper -------------------------------------\n");
        // | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 | 11 | 12 | 13 | 14 | 15 |
        // System.out.println(" | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 |");
        System.out.print("    |");
        for (int i = 0; i < Game.m; i++) {
            if (i < 10) {
                System.out.print("  " + i + " |");
            } else {
                System.out.print(" " + i + " |");
            }
        }
        System.out.println("");
        for (int row = 0; row < n; row++) {
            if (row < 10) {
                System.out.print("  " + row + " | ");
            } else {
                System.out.print(" " + row + " | ");
            }
            // System.out.print(" "+row + " | ");
            for (int col = 0; col < Game.m; col++) {

                System.out.print(" " + displayField[row][col] + " | ");
            }
            System.out.println();
        }
    }

    static void displayBombs(int[][] mineField) {
        clearScreen();
        System.out.println("                  Oops!        \n");
        System.out.println("--------------- GAME OVER ------------\n");
        System.out.println("                 Score = " + winScore);
        Utils.timeTaken();
        // System.out.println(" | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 |");
        System.out.print("    |");
        for (int i = 0; i < Game.m; i++) {
            if (i < 10) {
                System.out.print("  " + i + " |");
            } else {
                System.out.print(" " + i + " |");
            }
        }
        System.out.println("");
        for (int row = 0; row < Game.n; row++) {
            if (row < 10) {
                System.out.print("  " + row + " | ");
            } else {
                System.out.print(" " + row + " | ");
            }
            for (int col = 0; col < Game.m; col++) {
                // System.out.print(mineField[row][col] );
                if (mineField[row][col] == -2) {
                    if (Game.displayField[row][col] == 'F') {
                        System.out.print(" F | ");
                    } else {
                        System.out.print(" B " + "| ");
                    }
                } else {
                    System.out.print(" " + Game.displayField[row][col] + " " + "| ");
                }
            }
            System.out.println();
        }
        System.out.println("\n         You lost the Game :(        \n");
        System.out.println("         Better luck next time!         \n\n");
        Main.playAgain();

    }

    // timer
    static void timeTaken() {
        long timetaken = System.currentTimeMillis() - Game.startTime;

        long minutes = (timetaken / 1000) / 60;

        long seconds = (timetaken / 1000) % 60;

        // Print the output
        System.out.println("TimeTaken : "
                + minutes + " minutes and "
                + seconds + " seconds.");

    }

}
