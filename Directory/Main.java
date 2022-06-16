package Directory;
import java.util.*;

public class Main {
    static Scanner scan = new Scanner(System.in);
    static Directory pwd;

    public static void main(String[] args) {
        Directory root = new Directory("root", null);
        pwd = root;
        pwd.fullPath = pwd.setFullPath(null, root);

        while (true) {
            System.out.print("\nsanthosh@ubuntu:/"+pwd.fullPath+">");
            String cmd = scan.next().toLowerCase();
            String input = "";
            switch (cmd) {
                case "mkdir":
                    input = scan.next();
                    pwd.makeDir(input, pwd);
                    break;
                case "cd":
                    input = scan.next();
                    if (input.equals("/")) {
                        pwd = root;
                    } else if (input.equals("..")) {
                        pwd = pwd.parent;
                    } else {
                        Directory prePwd = pwd;
                        pwd = pwd.changeDir(input);
                        if (pwd == null) {
                            System.out.println("no sub dir");
                            pwd = prePwd;
                            break;
                        }
                    }
                    System.out.println("Directory Changed!");
                    break;
                case "pwd":
                    System.out.println(pwd.getFullpath());
                    break;
                case "touch":
                    input = scan.next();
                    pwd.createFile(input);
                    break;
                case "ls":
                    pwd.showSubDirectories();
                    break;
                case "tree":
                    System.out.println(" .");
                    showFullTree(root);
                    break;
                case "exit":
                    System.exit(0);
                case "cls":
                case "clear":
                    clearScreen();
                    break;
                default:
                    System.out.println("Invalid cmd!");
                    break;
            }
        }
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void showFullTree(Directory rootPtr) {
        for (String i : rootPtr.subDirectories.keySet()) {
            Directory dir = rootPtr.subDirectories.get(i);
            System.out.println(dir.name);
            if (dir.subDirectories.size() != 0) {
                showFullTree(dir); 
            }
        }
    }

}
