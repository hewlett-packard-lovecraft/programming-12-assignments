package com.example.javafxprojectassignment.Model;

import com.example.javafxprojectassignment.DatabaseHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LibraryUserModel {
    private DatabaseHandler conn;
    public int id;
    public String name = "";
    public ArrayList<BookModel> books;


    /**
     * Class constructor, initializes a LibraryUser and creates a new entry in the database
     *
     * @param name       name
     * @param connection database connection
     */

    public LibraryUserModel(String name, DatabaseHandler connection) {
        this.name = name;
        this.conn = connection;

        try {
            create();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates the book if it does not exist
     *
     * @throws SQLException
     */
    private void create() throws SQLException {
        String createStatement = String.join("\n",
                "INSERT INTO LIBRARY_USERS ( name )",
                "VALUES (" + name + ")"
        );

        try {
            ResultSet rs = conn.execAction(createStatement);
            id = rs.getInt("id");

            System.out.println(
                    "Created user with name " + name + " and id " + id
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Class constructor, initializes a LibraryUser but loads properties from existing entry in database
     *
     * @param id         library user primary key
     * @param connection database connection
     */

    public LibraryUserModel(int id, DatabaseHandler connection) {
        this.conn = connection;
        this.id = id;

        connect();
    }

    /**
     * Copy user properties from existing entry in database
     */
    private void connect() {
        String st = String.join("\n",
                "SELECT * FROM LIBRARY_USERS WHERE ID=" + id
        );

        try {
            ResultSet rs = conn.execQuery(st);

            if (rs.next()) {
                name = rs.getString("name");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Update name
     *
     * @param name
     */
    public void update(String name) {
        String st = String.join("\n",
                "UPDATE LIBRARY_USERS",
                "SET TITLE=" + name,
                "WHERE ID=" + id
        );

        try {
            conn.execAction(st);

            this.name = name;

            System.out.println(
                    "Set name of user with id " + id + " to " + name
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Update list of books with user's primary key as user_id
     *
     * @param user_id
     */
    public void listBorrowedBooks(int user_id) {
        String st = String.join("\n",
                "SELECT ID",
                "WHERE USER_ID=" + user_id
        );

        try {
            ResultSet rs = conn.execQuery(st);
            this.books = new ArrayList<BookModel>();

            while (rs.next()) {
                int bookID = rs.getInt("ID");
                this.books.add(new BookModel(bookID, conn));
            }

            System.out.println(
                    "User " + name + " has borrowed " + this.books.size()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

