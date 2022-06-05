package MineSweeper;

public class Game {

    static int openedField = 0; // openedField
    static String msg = "";
    static boolean isStarted = true;
    static int n = Settings.rows;
    static int m = Settings.columns;

    static long startTime;

    // static int[][] mineField;
    // static char[][] displayField;
    // static boolean[][] check_matrix;
    static Cell[][] matrix;

    public static void confirmStart() {
        System.out.print("\nPress 'y' to start the game : ");
        Character ch = Main.scan.next().charAt(0);

        if (ch == 'y' || ch == 'Y') {
            Utils.clearScreen();
            Init.initialize();
            setRowCOl(n, m);

            int start_index[] = best_start(matrix);
            matrix[start_index[0]][start_index[1]].displayValue = 'X';

            Game.startGame(start_index);
        }
    }

    private static void startGame(int[] start_index) {

        while (true) {
            Utils.displayMineField(n, matrix);
            System.out.println("\n" + msg);
            msg = "";
            System.out.println("No. of flags remaining: " + Settings.flagCount);
            int row = 0, col = 0;
            char operation = 'a';
            if (isStarted) {
                System.out.println("\nEnter S to start:");

                // time starts
                startTime = System.currentTimeMillis();

                operation = Main.scan.next().charAt(0);
                row = start_index[0];
                col = start_index[1];
                isStarted = false;
            } else {
                System.out.println("\nEnter Operation : (Open - o / Flag - f / Unflag - u / Exit - x)");
                operation = Main.scan.next().charAt(0);
                if (operation == 'X' || operation == 'x') {
                    return;
                }
                System.out.println("Enter row, col :");
                row = Main.scan.nextInt();
                col = Main.scan.nextInt();
            }

            switch (Character.toLowerCase(operation)) {
                case 'o':
                case 's':
                    openMine(matrix, row, col);
                    break;
                case 'f':
                    setFlag(matrix, row, col);
                    break;
                case 'u':
                    unFlag(matrix, row, col);
                    break;
                default:
                    System.out.println("Invalid Operation!");
            }
            Utils.clearScreen();
            if (Settings.flagCount == 0) {
                openedField = getopenedField(matrix);
                if (openedField == Settings.totalField) {
                    Utils.displayMineField(n, matrix);
                    Utils.printMessage();
                }

            }
        }

    }

    public static void setRowCOl(int row, int col) {
        n = row;
        m = col;
    }

    private static int getopenedField(Cell[][] matrix) {
        int count = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                if (matrix[row][col].state == true) {
                    count++;
                }
            }
        }
        return count;
    }

    private static void openMine(Cell[][] matrix,
            int row, int col) {

        if (matrix[row][col].displayValue == 'F') {
            msg = "Its flagged!";
            return;
        }

        if (matrix[row][col].originalVal == 0) {
            openEmptySpaces(matrix, row, col);
            return;
        }

        if (matrix[row][col].originalVal == -2) {
            Utils.displayBombs(matrix);
        }

        if (matrix[row][col].originalVal > 0 && matrix[row][col].state == false) {
            matrix[row][col].state = true;
            matrix[row][col].displayValue = Integer.toString(matrix[row][col].originalVal).charAt(0);
            openedField++;
            return;
        }

        if (matrix[row][col].originalVal > 0 && matrix[row][col].state == true) {
            msg = "It's already Opened!";
        }

    }

    private static void setFlag(Cell[][] matrix, int row, int col) {

        if (matrix[row][col].displayValue == '-') {
            matrix[row][col].displayValue = 'F';
            Settings.flagCount--;
        } else if (matrix[row][col].displayValue == 'F') {
            msg = "It's already flagged!";
        } else {
            msg = "It's already Opened!";
        }
    }

    private static void unFlag(Cell[][] matrix, int row, int col) {

        if (matrix[row][col].displayValue == 'F') {
            matrix[row][col].displayValue = '-';
            Settings.flagCount++;
        } else {
            msg = "It's not Flagged!";
        }

    }

    public static void openEmptySpaces(Cell[][] matrix, int row,
            int col) {
        if (row == -1 || col == -1 || row >= Settings.rows || col >= Settings.columns) {
            return;
        }

        if (matrix[row][col].originalVal != 0) {
            matrix[row][col].state = true;
            matrix[row][col].displayValue = Integer.toString(matrix[row][col].originalVal).charAt(0);

        }

        if (matrix[row][col].state == false && matrix[row][col].originalVal == 0) {
            matrix[row][col].state = true;
            matrix[row][col].displayValue = ' ';

            // displaymatrix[row][col] = Integer.toString(grid[row][col]).charAt(0);

            openEmptySpaces(matrix, row + 1, col); // down
            openEmptySpaces(matrix, row, col + 1); // right
            openEmptySpaces(matrix, row, col - 1); // left
            openEmptySpaces(matrix, row - 1, col); // up

            openEmptySpaces(matrix, row + 1, col + 1); // downright
            openEmptySpaces(matrix, row - 1, col + 1); // upright
            openEmptySpaces(matrix, row - 1, col - 1); // upleft
            openEmptySpaces(matrix, row + 1, col - 1); // downleft
        }
    }

    public static int[] best_start(Cell[][] matrix) {

        int index[] = new int[2];
        int max = 0;
        int n = Settings.rows;
        int m = Settings.columns;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                int count = 0;
                if (matrix[row][col].originalVal == 0) {
                    if (row > 0 && col > 0 && matrix[row - 1][col - 1].originalVal == 0) {
                        count += 1;
                    }
                    if (row > 0 && matrix[row - 1][col].originalVal == 0) {
                        count += 1;
                    }
                    if (row > 0 && col < m - 1 && matrix[row - 1][col + 1].originalVal == 0) {
                        count += 1;
                    }
                    if (col < m - 1 && matrix[row][col + 1].originalVal == 0) {
                        count += 1;
                    }
                    if (col > 0 && matrix[row][col - 1].originalVal == 0) {
                        count += 1;
                        ;
                    }
                    if (row < n - 1 && matrix[row + 1][col].originalVal == 0) {
                        count += 1;
                    }
                    if (row < n - 1 && col > 0 && matrix[row + 1][col - 1].originalVal == 0) {
                        count += 1;
                    }
                    if (row < n - 1 && col < m - 1 && matrix[row + 1][col + 1].originalVal == 0) {
                        count += 1;
                    }
                    if (count > max) {
                        max = count;
                        index[0] = row;
                        index[1] = col;
                    }
                }
            }
        }
        return index;
    }

}