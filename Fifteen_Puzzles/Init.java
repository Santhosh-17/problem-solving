package Fifteen_Puzzles;

import java.util.Random;

public class Init {

    public static void init() {
        int n = 4;
        int count = 0;
        Game.puzzle = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Game.puzzle[i][j] = count++;
            }
        }

        shuffle(2);
    }

    private static void shuffle(int rounds) {
        while (rounds > 0) {
            Random random = new Random();
            for (int i = Game.puzzle.length - 1; i > 0; i--) {
                for (int j = Game.puzzle[i].length - 1; j > 0; j--) {
                    int m = random.nextInt(i + 1);
                    int n = random.nextInt(j + 1);

                    int temp = Game.puzzle[i][j];
                    Game.puzzle[i][j] = Game.puzzle[m][n];
                    Game.puzzle[m][n] = temp;
                }
            }
            rounds--;
        }
    }
}
