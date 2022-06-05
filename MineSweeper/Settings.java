package MineSweeper;

public class Settings {

    static int rows = 9;
    static int columns = 9;
    static String mode = "Easy";
    static int flagCount = 10;
    static int totalField = 71;
    
    static int n = rows;

    public static void viewSettings() {

        System.out.println();
        System.out.println("\n---------- Settings ----------\n");
        System.out.println("Current Mode :" + mode);
        System.out.println("");
        System.out.println("1.Change Mode");
        System.out.println("2.Back to Main Menu");
        System.out.println("\n");

        System.out.print("Enter your option (1 / 2) :");
        int option = Main.scan.nextInt();

        switch (option) {
            case 1:
                changeMode();
                break;

            case 2:
                break;

            default:
                System.out.println("Enter valid Option!");
                break;
        }

    }

    public static int getFlagCount(){
        return flagCount;
    }

    private static void changeMode() {

        System.out.println("\nChoose any one of the Following:");
        System.out.println("\n");
        System.out.println("1.Easy");
        System.out.println("2.Medium");
        System.out.println("3.Hard");
        System.out.println("\n");
        System.out.print("Enter your option (1 / 2 / 3 ) :");
        int option = Main.scan.nextInt();

        switch (option) {
            case 1:
                mode = "Easy";
                rows = 9;
                columns = 9;
                n = rows;
                flagCount = 10;
                break;

            case 2:
                mode = "Medium";
                rows = 16;
                columns = 16;
                flagCount = 40;
                break;

            case 3:
                mode = "Hard";
                rows = 30;
                columns = 16;
                flagCount = 99;
                break;

            default:
                System.out.println("Enter valid Option!");
                break;
        }
        totalField = (rows*columns) - flagCount;
        Game.setRowCOl(rows, columns);
    }

}