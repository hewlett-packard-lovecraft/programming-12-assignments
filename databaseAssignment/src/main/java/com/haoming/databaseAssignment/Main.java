package com.haoming.databaseAssignment;

public class Main {
    public static void main(String[] args) {
        DatabaseHandler dbhandler = new DatabaseHandler();

        for (String path : args) {
            Directory dir = new Directory(path);
            dbhandler.addDir(dir);
        }

        dbhandler.showTables();
    }
}
