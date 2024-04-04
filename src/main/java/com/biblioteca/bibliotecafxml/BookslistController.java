/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.biblioteca.bibliotecafxml;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import model.Libro;

/**
 * FXML Controller class
 *
 * @author iordan.sebastian
 */
public class BookslistController implements Initializable {

//    private Libro l;
    @FXML
    private TextArea txtaBooksList;
    @FXML
    private Button btnBackToOption;
    @FXML
    private Button btnBackToSearch;
    @FXML
    private Button btnBackToAdd;

    @FXML
    private void switchToPrimary() throws IOException, Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) txtaBooksList.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void switchToAdd() throws IOException, Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("addbook.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) txtaBooksList.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void switchToSearch() throws IOException, Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("search.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) txtaBooksList.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void showBooks() {
        StringBuilder str = new StringBuilder();

//        for (int i = 1; i < Libro.books.size() + 1; i++) {
//            Libro l = Libro.books.get(i);
//            if (l != null)
//                str.append(l.toString()).append("\n");
////            else
////                str.append("The book was removed!\n");
//        }
        for (Libro libro : Libro.books.values()) {
            if (libro != null)
//                System.out.println(libro.toString());
                str.append(libro.toString()).append("\n");
        }

        txtaBooksList.setText(str.toString());
    }

    /**
     * Initializes the controller
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showBooks();
    }
}
