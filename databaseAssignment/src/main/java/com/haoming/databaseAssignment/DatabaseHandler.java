package com.haoming.databaseAssignment;

import java.sql.*;
import java.util.Arrays;

public class DatabaseHandler {
    private static final String DB_url = "jdbc:derby:database/derbydemo;create=true";
    private static Connection connection = null;
    private static Statement stmt = null;

    public DatabaseHandler() {
        createConnection();
    }

    public void showTables() {
        ResultSet resultSet = execQuery("select st.tablename  from sys.systables st LEFT OUTER join sys.sysschemas ss on (st.schemaid = ss.schemaid) where ss.schemaname ='APP'");

        try {
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            final int columnCount = resultSetMetaData.getColumnCount();

            while (resultSet.next()) {
                Object[] values = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    values[i - 1] = resultSet.getObject(i);
                }
                System.out.println(Arrays.toString(values));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
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
