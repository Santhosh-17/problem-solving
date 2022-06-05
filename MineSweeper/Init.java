package MineSweeper;

import java.util.Random;

public class Init {

    public static void initialize() {
        Random random = new Random();
        int count = 0;
        int n = Settings.rows;
        int m = Settings.columns;

        // Game.matrix = new int[n][m];
        // Game.displayField = new char[n][m];
        // Game.check_matrix = new boolean[n][m];
        Game.matrix = Utils.createMatrix(n, m);

        int flags = Settings.getFlagCount();

        while (count < flags) {
            int row = random.nextInt(n);
            int col = random.nextInt(m);

            if (Game.matrix[row][col].originalVal != -2) {

                Game.matrix[row][col].originalVal = -2;

                if (row > 0 && col > 0 && Game.matrix[row - 1][col - 1].originalVal >= 0) {
                    Game.matrix[row - 1][col - 1].originalVal += 1;
                }
                if (row > 0 && Game.matrix[row - 1][col].originalVal >= 0) {
                    Game.matrix[row - 1][col].originalVal += 1;
                }
                if (row > 0 && col < m - 1 && Game.matrix[row - 1][col + 1].originalVal >= 0) {
                    Game.matrix[row - 1][col + 1].originalVal += 1;
                }
                if (col < m - 1 && Game.matrix[row][col + 1].originalVal >= 0) {
                    Game.matrix[row][col + 1].originalVal += 1;
                }
                if (col > 0 && Game.matrix[row][col - 1].originalVal >= 0) {
                    Game.matrix[row][col - 1].originalVal += 1;
                }
                if (row < n - 1 && Game.matrix[row + 1][col].originalVal >= 0) {
                    Game.matrix[row + 1][col].originalVal += 1;
                }
                if (row < n - 1 && col > 0 && Game.matrix[row + 1][col - 1].originalVal >= 0) {
                    Game.matrix[row + 1][col - 1].originalVal += 1;
                }
                if (row < n - 1 && col < m - 1 && Game.matrix[row + 1][col + 1].originalVal >= 0) {
                    Game.matrix[row + 1][col + 1].originalVal += 1;
                }
                count++;
            }
        }

        // for (int row = 0; row < Game.n; row++) {
        // for (int col = 0; col < Game.m; col++) {
        // Game.displayField[row][col] = '-';
        // }
        // }
    }

}