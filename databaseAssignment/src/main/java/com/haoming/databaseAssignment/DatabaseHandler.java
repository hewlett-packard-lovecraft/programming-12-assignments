package com.haoming.databaseAssignment;

import java.sql.*;
import java.util.Arrays;

public class DatabaseHandler {
    private static final String DB_url = "jdbc:derby:database/DerbyDemo;create=true";
    private static Connection connection = null;
    private static Statement stmt = null;

    public DatabaseHandler() {
        createConnection();
    }

    public void showTables() {
        try {
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet tableNames = databaseMetaData.getTables(null, null, null, new String[]{"TABLE"});

            while (tableNames.next()) {
                String table_name = tableNames.getString("TABLE_NAME");
                ResultSet tableValues = execQuery("SELECT * FROM " + table_name);

                while (tableValues.next()) {

                    System.out.println(
                            "Table: " + table_name + "\n" +
                            "file_name " + tableValues.getString("file_path") + "\n" +
                                    "file_name " + tableValues.getString("file_name") + "\n" +
                                    "file_path " + tableValues.getString("file_path") + "\n" +
                                    "extension " + tableValues.getString("extension") + "\n" +
                                    "size_in_bytes " + tableValues.getString("size_in_bytes") + "\n"
                    );
                }

            }

            tableNames.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void addDir(Directory directory) {
        addDirTable(directory);

        for (RetrievedFile file : directory.fileList) {
            addFile(file, directory.dirName);
        }

    }

    public void addDirTable(Directory dir) {
        String table_name = dir.dirName;
        System.out.println("Adding table " + table_name);

        String st = "CREATE TABLE " + table_name + " ( "
                + "file_name varchar(255), "
                + "file_path varchar(255), "
                + "extension varchar(255), "
                + "size_in_bytes bigint "
                + ")";

        System.out.println(st);

        execAction(st);
    }

    public void addFile(RetrievedFile file, String dirName) {
        System.out.println("Adding '" + file.name + "' to table " + dirName);

        if(file.isFlagged()) {
            System.out.println("One or more fields are empty");
            return;
        }

        String st = "INSERT INTO " + dirName + " VALUES ( " +
                "'" + file.name + "', " +
                "'" + file.path + "', " +
                "'" + file.extension + "', " +
                " " + file.sizeInBytes + " " +
                ")";

        System.out.println(st);

        execAction(st);
    }

    public boolean execAction(String qu) {
        try {
            stmt = connection.createStatement();
            stmt.execute(qu);
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ResultSet execQuery(String query) {
        ResultSet resultSet;

        try {
            stmt = connection.createStatement();
            resultSet = stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
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
