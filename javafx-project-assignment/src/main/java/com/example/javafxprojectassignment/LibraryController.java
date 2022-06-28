package com.example.javafxprojectassignment;

import com.example.javafxprojectassignment.Model.BookModel;
import com.example.javafxprojectassignment.Model.LibraryUserModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.ResourceBundle;
import java.net.URL;
import java.util.stream.Stream;

public class LibraryController implements Initializable {
    @FXML
    public TableView libraryUserTable;
    @FXML
    private TableView<java.util.stream.Stream<Object>> bookTable;
    public TableColumn bookTableViewIDCol;
    public TableColumn bookTableViewTitleCol;
    public TableColumn bookTableViewAuthorCol;
    public TableColumn bookTableViewUserIDCol;
    public TableColumn bookTableViewBorrowerNameCol;

    public DatabaseHandler conn;

    public ArrayList<BookModel> bookModels = new ArrayList<>();
    public ArrayList<LibraryUserModel> libraryUserModels = new ArrayList<>();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        if(conn != null) {
            System.out.println("Connected to database");
        } else {
            conn = new DatabaseHandler();
            System.out.println("Connected to database");
        }

        // parse db
        ArrayList<Integer> bookIDs = conn.getPrimaryKeys("BOOKS");
        ArrayList<Integer> libraryUserIDs = conn.getPrimaryKeys("LIBRARY_USERS");

        bookIDs.forEach(id -> {
            bookModels.add(new BookModel(id, conn));
        });

        libraryUserIDs.forEach(id -> {
            libraryUserModels.add(new LibraryUserModel(id, conn));
        });

        populateTableViews();
    }

    private void populateTableViews() {
        bookTableViewIDCol.setCellFactory(new PropertyValueFactory<BookModel, Integer>("id"));
        bookTableViewTitleCol.setCellFactory(new PropertyValueFactory<BookModel, String>("title"));
        bookTableViewUserIDCol.setCellFactory(new PropertyValueFactory<BookModel, Integer>("user_id"));
        bookTableViewBorrowerNameCol.setCellFactory(new PropertyValueFactory<BookModel, String>("borrowerName"));

        //bookTable.getItems().addAll(new BookModel(1, conn), new BookModel(2, conn));
        bookTable.getItems().addAll((Stream<Object>) bookModels);
    }




    public void handleShutdown() {
        System.out.println("Program stopped. Closing connections...");
        conn.close();
    }




}