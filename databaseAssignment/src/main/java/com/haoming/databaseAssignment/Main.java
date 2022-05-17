package com.haoming.databaseAssignment;

public class Main {
    public static void main(String[] args) {
        DatabaseHandler dbhandler = new DatabaseHandler();

        for (String path : args) {
            Directory dir = new Directory(path);
            //dir.dirName.replaceAll("[^a-zA-Z0-9]", "_"); // replace invalid chars with valid char

            dbhandler.addDir(dir);
        }

        dbhandler.showTables();
    }
}
