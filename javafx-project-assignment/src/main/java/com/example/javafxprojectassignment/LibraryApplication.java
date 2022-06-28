package com.example.javafxprojectassignment;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LibraryApplication extends Application {

    /**
     * SetupTables
     *
     * @param conn - DatabaseHandler instance
     *              on program start, create the USER and BOOK tables if they don't exist
     */
    public boolean setupTables(DatabaseHandler conn) {
        String st1 =
                String.join("\n",
                        "CREATE TABLE LIBRARY_USERS ( ",
                        "id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), ",
                        "name varchar(55) NOT NULL, ",
                        "CONSTRAINT primary_key PRIMARY KEY (id) ",
                        ")"
                );

        String st2 =
                String.join("\n",
                        "CREATE BOOKS  ( ",
                        "id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), ",
                        "title VARCHAR(50) NOT NULL, ",
                        "author VARCHAR(50) NOT NULL, ",
                        "user_id INTEGER ",
                        "CONSTRAINT book_pk PRIMARY KEY (id) ",
                        "CONSTRAINT book_fk  FOREIGN KEY (user_id) REFERENCES User ",
                        ")"
                );

        try {

            if (!conn.tableExists("LIBRARY_USERS")) {
                System.out.println("LIBRARY_USERS table doesn't exist, creating table");
                conn.execAction(st1);
            }

            if (!conn.tableExists("BOOKS")) {
                System.out.println("BOOKS table doesn't exist, creating table");
                conn.execAction(st2);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LibraryApplication.class.getResource("library-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),  600, 800);

        LibraryController controller = fxmlLoader.getController();

        // after window loads, initialize the database, create required tables, and pass DB connection to controller
        DatabaseHandler conn = new DatabaseHandler();
        System.out.println("Connected to database");
        setupTables(conn);
        controller.conn = conn;

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setOnCloseRequest(e -> controller.handleShutdown());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}