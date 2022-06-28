package com.example.javafxprojectassignment.Model;

import com.example.javafxprojectassignment.DatabaseHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookModel {
    private DatabaseHandler conn;
    public int id = 0;
    public String title = "";
    public String author = "";
    public String borrowerName = "";
    public int user_id = 0;

    /**
     * Class constructor
     *
     * @param bookTitle  book title
     * @param author     book author
     * @param connection database connection
     */
    public BookModel(String bookTitle, String author, DatabaseHandler connection) {
        this.title = bookTitle;
        this.author = author;
        this.conn = connection;

        try {
            create();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create a new row in Book table and find primary key
     *
     */

    private void create() throws SQLException {
        String createStatement = String.join("\n",
                "INSERT INTO BOOKS ( TITLE, AUTHOR)",
                "VALUES (" + title + ", " + author + ")",
                        ""
        );

        try {
            ResultSet rs = conn.execAction(createStatement);

            if (rs.next()) {
                id = rs.getInt("id");
            }

            System.out.println(
                    "Created book with title " + title + " author " + author + " id " + id
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Load book properties from existing entry in database
     * @param id book primary key
     * @param connection db connection
     */
    public BookModel(int id, DatabaseHandler connection) {
        this.id = id;
        this.conn = connection;

        try {
            connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    /**
     * Load book properties from entries in database using primary key
     */
    private void connect() throws SQLException {

        String st = String.join("\n",
                "SELECT * FROM BOOKS WHERE ID=" + this.id
        );


        try {
            ResultSet rs = conn.execQuery(st);

            if (rs.next()) {
                this.title = rs.getString("title");
                this.author = rs.getString("author");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Update the title and author of a book
     * @param title book title
     * @param author book author
     */
    public void update(String title, String author) {
        String st = String.join("\n",
                "UPDATE BOOKS",
                "SET TITLE=" + title + ", AUTHOR=" + author,
                "WHERE ID=" + id
        );

        try {
            conn.execAction(st);

            this.title = title;
            this.author = author;

            System.out.println(
                    "Set title of book with id " + id + " to " + title + " and author to " + author
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Set the userId of the borrower of the book
     * @param user_id
     */
    public void setUser(int user_id) {
        String st = String.join("\n",
                "UPDATE BOOKS",
                "SET USER_ID=" + user_id,
                "WHERE ID=" + id
        );

        try {
            conn.execAction(st);

            this.user_id = user_id;
            setUserName();

            System.out.println(
                    "Set title of book with id " + id + " to " + title + " and author to " + author
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setUserName() {
        String st = String.join("\n",
                "SELECT NAME FROM LIBRARY_USERS WHERE ID=" + user_id
        );

        try {
            ResultSet rs = conn.execQuery(st);

            if (rs.next()) {
                this.borrowerName = rs.getString("name");

                System.out.println("The borrower of " + title + " by " + author +" is " + borrowerName);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
