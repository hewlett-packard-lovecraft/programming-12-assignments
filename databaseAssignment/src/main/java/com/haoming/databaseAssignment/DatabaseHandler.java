package com.haoming.databaseAssignment;

import java.sql.*;

public class DatabaseHandler {
    private static final String DB_url = "jdbc:derby:database/DerbyDemo;create=true";
    private static Connection connection = null;
    private static Statement stmt = null;

    public DatabaseHandler() {
        createConnection();
    }

    public void addDir(Directory directory) {
        addDirTable(directory);

        for (RetrievedFile file : directory.fileList) {
            addFile(file, directory.dirName.toUpperCase());
        }

    }

    public void addDirTable(Directory dir) {
        String table_name = dir.dirName.toUpperCase();

        String st = "CREATE TABLE " + table_name + " ( "
                + "file_name varchar(255), "
                + "file_path varchar(255), "
                + "extension varchar(255), "
                + "size_in_bytes bigint,"
                + ")";

        execAction(st);
    }

    public void addFile(RetrievedFile file, String dirName) {
        if(file.isFlagged())
            return;

        String st = "INSERT INTO " + dirName + " VALUES (" +
                "'" + file.name + "," +
                "'" + file.path + "," +
                "'" + file.extension + "," +
                "'" + file.sizeInBytes + "," +
                ")";

        execAction(st);
    }

    public boolean execAction(String qu) {
        try {
            stmt = connection.createStatement();
            stmt.execute(qu);
            return true;

        } catch (SQLException e) {
            System.err.println("exception at execQuery " + e.getLocalizedMessage());
            return false;
        }
    }

    public ResultSet execQuery(String query) {
        ResultSet resultSet;

        try {
            stmt = connection.createStatement();
            resultSet = stmt.executeQuery(query);
        } catch (SQLException e) {
            System.err.println("exception at execQuery " + e.getLocalizedMessage());
            return null;
        }

        return resultSet;
    }

    private void createConnection() {
        try {
            connection = DriverManager.getConnection(DB_url);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
