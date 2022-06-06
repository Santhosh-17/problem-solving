package MineSweeper;

import java.util.Arrays;
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

                for(int i = -1 ;i < 2;i++){
                    for(int j = -1;j<2;j++){
                        try {
                            if(Game.mineField[row+i][column+j] >=0 ){
                                Game.mineField[row+i][column+j] += 1;
                            }
                        } catch (Exception e) {}
                    }
                }
                count++;
            }
        }

        for (int row = 0; row < Game.n; row++) {
            for (int column = 0; column < Game.m; column++) {
                Game.displayField[row][column] = '-';
            }
        }

        System.out.println(Arrays.deepToString(Game.mineField));
    }

}