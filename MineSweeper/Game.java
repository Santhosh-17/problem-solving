package MineSweeper;

public class Game {

    static int openedField = 0; // openedField
    static String msg = "";
    static boolean isStarted = true;
    static int n = Settings.rows;
    static int m = Settings.columns;
    static long startTime;

    static int[][] mineField;
    static char[][] displayField;
    static boolean[][] check_matrix;

    public static void confirmStart() {
        System.out.print("\nPress 'y' to start the game : ");
        Character ch = Character.toLowerCase(Main.scan.next().charAt(0));

        if (ch == 'y') {
            Utils.clearScreen();
            Init.initialize();
            setRowCOl(n, m);

            int start_index[] = best_start(mineField);
            displayField[start_index[0]][start_index[1]] = 'X';

            Game.startGame(start_index);
        }
    }

    private static void startGame(int[] start_index) {

        while (true) {
            Utils.displayMineField(n, displayField);
            System.out.println("\n" + msg);
            msg = "";
            System.out.println("No. of flags remaining: " + Settings.flagCount);
            int row = 0, col = 0;
            char operation = 'a';
            if (isStarted) {
                System.out.println("\nEnter S to start:");
                operation = Main.scan.next().charAt(0);

                // Start Time
                startTime = System.currentTimeMillis();
                row = start_index[0];
                col = start_index[1];
                isStarted = false;
            } else {
                System.out.println("\nEnter Operation : (Open - o / Flag - f / Unflag - u / Exit - x)");
                operation = Character.toLowerCase(Main.scan.next().charAt(0));
                if (operation == 'x') {
                    return;
                }
                System.out.println("Enter row, col :");
                row = Main.scan.nextInt();
                col = Main.scan.nextInt();
            }

            switch (operation) {
                case 'o':
                case 's':
                    openMine(mineField, displayField, check_matrix, row, col);
                    break;
                case 'f':
                    setFlag(displayField, row, col);
                    break;
                case 'u':
                    unFlag(displayField, row, col);
                    break;
                default:
                    System.out.println("Invalid Operation!");
            }
            Utils.clearScreen();
            if (Settings.flagCount == 0) {
                openedField = getopenedField(displayField);
                if (openedField == Settings.totalField) {
                    Utils.displayMineField(n, displayField);
                    Utils.printMessage();
                }

            }
        }

    }

    public static void setRowCOl(int row, int col) {
        n = row;
        m = col;
    }

    private static int getopenedField(char[][] matrix) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] != '-' || matrix[i][j] != 'F') {
                    count++;
                }
            }
        }
        return count;
    }

    private static void openMine(int[][] mineField,
            char[][] displayField,
            boolean[][] check_matrix,
            int row, int col) {

        if (displayField[row][col] == 'F') {
            msg = "Its flagged!";
            return;
        }

        if (mineField[row][col] == 0) {
            openEmptySpaces(displayField, check_matrix, mineField, row, col);
            return;
        }

        if (mineField[row][col] == -2) {
            Utils.displayBombs(mineField);
        }

        if (mineField[row][col] > 0 && check_matrix[row][col] == false) {
            check_matrix[row][col] = true;
            displayField[row][col] = Integer.toString(mineField[row][col]).charAt(0);
            openedField++;
            return;
        }

        if (mineField[row][col] > 0 && check_matrix[row][col] == true) {
            msg = "It's already Opened!";
        }

    }

    private static void setFlag(char[][] displayArray, int row, int col) {

        if (displayArray[row][col] == '-') {
            displayArray[row][col] = 'F';
            Settings.flagCount--;
        } else if (displayArray[row][col] == 'F') {
            msg = "It's already flagged!";
        } else {
            msg = "It's already Opened!";
        }
    }

    private static void unFlag(char[][] displayArray, int row, int col) {

        if (displayArray[row][col] == 'F') {
            displayArray[row][col] = '-';
            Settings.flagCount++;
        } else {
            msg = "It's not Flagged!";
        }

    }

    public static void openEmptySpaces(char[][] displaymatrix, boolean[][] matrix, int[][] grid, int i,
            int j) {
        if (i == -1 || j == -1 || i >= Settings.rows || j >= Settings.columns) {
            return;
        }

        if (grid[i][j] != 0) {
            matrix[i][j] = true;
            displaymatrix[i][j] = Integer.toString(grid[i][j]).charAt(0);

        }

        if (matrix[i][j] == false && grid[i][j] == 0) {
            matrix[i][j] = true;
            displaymatrix[i][j] = ' ';

           for(int rowIndex = -1 ;rowIndex < 2;rowIndex++){
            for(int colIndex = -1;colIndex<2;colIndex++){

                try {
                    openEmptySpaces(displaymatrix, matrix, grid, i+rowIndex, j+colIndex);
                } catch (Exception e) {}

            }
        }

        }
    }

    public static int[] best_start(int mineField[][]) {

        int index[] = new int[2];
        int max = 0;
        int n = Settings.rows;
        int m = Settings.columns;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                int count = 0;
                if (mineField[row][col] == 0) {
                    if (row > 0 && col > 0 && mineField[row - 1][col - 1] == 0) {
                        count += 1;
                    }
                    if (row > 0 && mineField[row - 1][col] == 0) {
                        count += 1;
                    }
                    if (row > 0 && col < m - 1 && mineField[row - 1][col + 1] == 0) {
                        count += 1;
                    }
                    if (col < m - 1 && mineField[row][col + 1] == 0) {
                        count += 1;
                    }
                    if (col > 0 && mineField[row][col - 1] == 0) {
                        count += 1;
                        ;
                    }
                    if (row < n - 1 && mineField[row + 1][col] == 0) {
                        count += 1;
                    }
                    if (row < n - 1 && col > 0 && mineField[row + 1][col - 1] == 0) {
                        count += 1;
                    }
                    if (row < n - 1 && col < m - 1 && mineField[row + 1][col + 1] == 0) {
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