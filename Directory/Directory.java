package Directory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Directory {
    Map<String, Directory> subDirectories;
    ArrayList<String> files;
    String name;
    Directory parent;
    String fullPath = "";

    public Directory(String name, Directory parent) {
        subDirectories = new LinkedHashMap<>();
        files = new ArrayList<>();
        this.name = name;
        this.parent = parent;
    }

    public void makeDir(String dirName, Directory parent) {
        Directory dir = new Directory(dirName, parent);
        dir.fullPath = setFullPath(parent, dir);
        subDirectories.put(dirName, dir);
        System.out.println(dirName + " is added!");
    }

    public Directory changeDir(String dirName) {
        return subDirectories.get(dirName);
    }

    public void createFile(String fileName) {
        files.add(fileName);
        System.out.println(fileName + " is added!");
    }

    public void showSubDirectories() {
        for (String str : subDirectories.keySet()) {
            System.out.println(str);
        }
        for (String str : files) {
            System.out.println(str);
        }
    }

    public String setFullPath(Directory parent, Directory newDir) {

        if (parent == null) {
            return "root";
        } else {
            return parent.fullPath + "/" + newDir.name;
        }
    }

    public String getFullpath() {
        return fullPath;
    }

}