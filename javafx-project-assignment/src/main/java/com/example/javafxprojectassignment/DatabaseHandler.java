package com.example.javafxprojectassignment;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseHandler implements AutoCloseable {
    private static final String DB_url = "jdbc:derby:database/DerbyDemo";
    private static Connection connection = null;
    private static Statement stmt = null;

    /**
     * Class constructor, connects to database on initialization
     */
    public DatabaseHandler() {
        createConnection();
    }

    /**
     * This method checks if a table exists
     *
     * @param TableName A table name
     * @return boolean
     * @throws SQLException
     */
    public boolean tableExists(String TableName) {
        if (connection != null) {
            try {
                DatabaseMetaData dbmd = connection.getMetaData();
                ResultSet rs = dbmd.getTables(null, null, TableName, null);

                if (rs.next()) {
                    System.out.println("Table " + TableName + " exists");
                    return true;
                }

                System.out.println("Table " + TableName + " does not exist");
                return false;

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * This method checks if a row with a given id exists in a given table
     *
     * @param TableName A table name
     * @return boolean
     * @throws SQLException
     */
    public boolean rowExists(String TableName, int id) throws SQLException {
        if (connection != null) {
            DatabaseMetaData dbmd = connection.getMetaData();
            try {
                ResultSet rs = dbmd.getTables(null, null, TableName.toUpperCase(), null);

                if (rs.next()) {
                    System.out.println("Table " + rs.getString(TableName) + "exists");

                    String stmt =
                            "SELECT id " + "\n" +
                                    "FROM " + TableName.toUpperCase() + "\n" +
                                    "WHERE id=" + id;

                    rs = execQuery(stmt);

                    return rs.next();
                } else {
                    System.out.println("Table " + TableName + " does not exist");
                    return false;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public ArrayList<Integer> getPrimaryKeys(String tableName) {
        String st = String.join("\n",
                "SELECT ID FROM " + tableName
        );

        try {
            ResultSet rs = execQuery(st);
            ArrayList<Integer> primaryKeys = new ArrayList<>();

            while (rs.next()) {
                primaryKeys.add(rs.getInt("ID"));
            }

            return primaryKeys;
        } catch (SQLException e) {
            e.printStackTrace();
            return new  ArrayList<Integer>();
        }
    }

    /**
     * Execute an SQL action query
     *
     * @param qu The SQL statement to be executed
     * @return boolean value
     */
    public ResultSet execAction(String qu) {
        try {
            stmt = connection.createStatement();
            stmt.execute(qu);
            return stmt.getResultSet();

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Execute an SQL select query
     *
     * @param query The SQL statement to be executed
     * @return ResultSet
     */
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

    /**
     * Close database connection
     */

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create database connection
     */

    private void createConnection() {
        try {
            connection = DriverManager.getConnection(DB_url);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
