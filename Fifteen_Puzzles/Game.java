package Fifteen_Puzzles;

import java.util.Scanner;

public class Game {

    static int[][] puzzle = { { 1, 2, 3 }, { 4, 5, 6 }, { 0, 7, 8 } };
    static Scanner scan = new Scanner(System.in);
    static int zerothRow, zerothCol, moves = 0;
    static int n = 4;
    
    public static void startGame() {
        
        Utils.findZerothIndex(puzzle);
        Utils.displayPuzzle(puzzle);
        while (true) {
            Utils.printRules();
            char option = scan.next().charAt(0);
            switch (option) {
                case 'w':
                    moveUp();
                    break;
                case 's':
                    moveDown();
                    break;
                case 'a':
                    moveLeft();
                    break;
                case 'd':
                    moveRight();
                    break;
                case 'x':
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Move!");
            }
            moves++;
            Utils.clearScreen();
            Utils.displayPuzzle(puzzle);
            isCompleted();
        }
    }

    private static void moveUp() {
        if (zerothRow > 0) {
            swap(zerothRow - 1, zerothCol);
            zerothRow = zerothRow - 1;
        }
    }

    private static void moveDown() {
        if (zerothRow < n-1) {
            swap(zerothRow + 1, zerothCol);
            zerothRow = zerothRow + 1;
        }
    }

    private static void moveLeft() {
        if (zerothCol > 0) {
            swap(zerothRow, zerothCol - 1);
            zerothCol = zerothCol - 1;
        }
    }

    private static void moveRight() {
        if (zerothCol < n-1) {
            swap(zerothRow, zerothCol + 1);
            zerothCol = zerothCol + 1;
        }
    }

    private static void isCompleted() {
        if (zerothRow == n-1 && zerothCol == n-1) {
            if (isEqual()) {
                System.out.println("\n\n           Congratulations!        \n");
                System.out.println("\n--------------GAME OVER--------------\n");
                System.out.println("\n          YOU WON THE GAME       \n\n");
                System.out.println("\n      TOTAL NO OF MOVES: " + moves + "      \n\n");

                System.exit(0);
            }
        }
    }

    private static boolean isEqual() {
        int count = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 2 && j == 2) {
                    break;
                }
                if (count != puzzle[i][j]) {
                    return false;
                }
                count++;
            }
        }
        return true;
    }

    private static void swap(int i, int j) {
        puzzle[zerothRow][zerothCol] = puzzle[i][j];
        puzzle[i][j] = 0;
    }
}
