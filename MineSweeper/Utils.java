package MineSweeper;

public class Utils {

    public static void printMessage() {
        System.out.println("\n-------------- CONGRATULATIONS !! --------------\n");
        System.out.println("               YOU WON THE GAME                \n");
        Utils.timeTaken();
        Main.playAgain();
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    static void displayMineField(int n, Cell[][] matrix) {
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

                System.out.print(" " + matrix[row][col].displayValue + " | ");
            }
            System.out.println();
        }
    }

    static void displayBombs(Cell[][] matrix) {
        clearScreen();
        System.out.println("                  Oops!        \n");
        System.out.println("--------------- GAME OVER ------------\n");
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
                if (matrix[row][col].originalVal == -2) {
                    if (Game.matrix[row][col].displayValue == 'F') {
                        System.out.print(" F | ");
                    } else {
                        System.out.print(" B " + "| ");
                    }
                } else {
                    System.out.print(" " + Game.matrix[row][col].displayValue + " " + "| ");
                }
            }
            System.out.println();
        }
        System.out.println("\n         You lost the Game :(        \n");
        System.out.println("         Better luck next time!         \n\n");
        Main.playAgain();

    }

    static Cell[][] createMatrix(int n, int m) {
        Cell[][] matrix = new Cell[n][m];
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                matrix[row][col] = new Cell();
            }
        }

        return matrix;
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
