package MineSweeper;

import java.util.Scanner;

public class Main {
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        
        while (true) {
            Game.clearScreen();
            System.out.println("\n----- Welcome to MineSweeper Game ----\n");
            System.out.println("1. Play Game ");
            System.out.println("2. Settings ");
            System.out.println("3. Exit ");
            System.out.print("\nEnter your option (1 / 2 / 3) :");
            int option = scan.nextInt();
            scan.nextLine();

            switch (option) {
                case 1:
                    Game.isStarted = true;
                    Game.showSettings();
                    Game.confirmStart();
                    break;
                case 2:
                    Settings.viewSettings();
                    break;

                case 3:
                    System.out.println("\nThank you ! visit again\n");
                    scan.close();
                    System.exit(0);
                    break;

                default:
                    break;
            }

        }

    }

    public static void playAgain() {
        System.out.print("\nDo You want to play again? (Y-yes) or (N - no) : ");
        char wannaPlay = scan.next().charAt(0);
        switch (wannaPlay) {
            case 'y':
            case 'Y':
                Game.isStarted = true;
                Game.confirmStart();
                break;

            case 'n':
            case 'N':
                System.out.println("\nThank you ! visit again\n");
                scan.close();
                System.exit(0);
                break;

            default:
                System.out.println("Enter Valid Option!");
                break;
        }
    }
}
