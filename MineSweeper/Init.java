package MineSweeper;

import java.util.Random;

public class Init {

    public static void initialize() {
        Random random = new Random();
        int count = 0;
        int n = Settings.rows;
        int m = Settings.columns;
        
        Game.mineField = new int[n][m];
        Game.displayField = new char[n][m];
        Game.check_matrix = new boolean[n][m];
        int flags = Settings.getFlagCount();

        while (count < flags) {
            int row = random.nextInt(n);
            int column = random.nextInt(m);

            if (Game.mineField[row][column] != -2) {

                Game.mineField[row][column] = -2;

                if (row > 0 && column > 0 && Game.mineField[row - 1][column - 1] >= 0) {
                    Game.mineField[row - 1][column - 1] += 1;
                }
                if (row > 0 && Game.mineField[row - 1][column] >= 0) {
                    Game.mineField[row - 1][column] += 1;
                }
                if (row > 0 && column < m - 1 && Game.mineField[row - 1][column + 1] >= 0) {
                    Game.mineField[row - 1][column + 1] += 1;
                }
                if (column < m - 1 && Game.mineField[row][column + 1] >= 0) {
                    Game.mineField[row][column + 1] += 1;
                }
                if (column > 0 && Game.mineField[row][column - 1] >= 0) {
                    Game.mineField[row][column - 1] += 1;
                }
                if (row < n - 1 && Game.mineField[row + 1][column] >= 0) {
                    Game.mineField[row + 1][column] += 1;
                }
                if (row < n - 1 && column > 0 && Game.mineField[row + 1][column - 1] >= 0) {
                    Game.mineField[row + 1][column - 1] += 1;
                }
                if (row < n - 1 && column < m - 1 && Game.mineField[row + 1][column + 1] >= 0) {
                    Game.mineField[row + 1][column + 1] += 1;
                }
                count++;
            }
        }

        for (int row = 0; row < Game.n; row++) {
            for (int column = 0; column < Game.m; column++) {
                Game.displayField[row][column] = '-';
            }
        }
    }

}