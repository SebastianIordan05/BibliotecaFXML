/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.biblioteca.bibliotecafxml;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.Libro;
import model.Prestito;

/**
 * FXML Controller class
 *
 * @author iordan.sebastian
 */
public class BookslistController implements Initializable {

    @FXML
    private Button btnBackToOption;
    @FXML
    private Button btnBackToSearch;
    @FXML
    private Button btnBackToAdd;
    @FXML
    private ListView<String> lstBooks;

    @FXML
    private void switchToPrimary() throws IOException, Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) lstBooks.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void switchToAdd() throws IOException, Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("addbook.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) lstBooks.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void switchToSearch() throws IOException, Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("search.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) lstBooks.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void switchToRemove(String str) throws IOException, Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("removebook.fxml"));
        Parent root = loader.load();

        RemovebookController rmc = loader.getController();
        rmc.txtSearch.setText(str);

        Stage stage = (Stage) lstBooks.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void showBooks() {
        for (Libro libro : Libro.books.values()) {
            if (libro != null) {
                lstBooks.getItems().add(libro.getTitolo());
            }
        }
    }

    /**
     * Initializes the controller
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showBooks();

        lstBooks.getSelectionModel().selectedItemProperty().addListener((final Observable e) -> {
            String si = lstBooks.getSelectionModel().getSelectedItem();
            Libro l = Libro.books.get(si);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to delete it? \n\n"
                    + "Yes to go to the remove scene. \n"
                    + "No to do nothing. \n");
            
            if (Prestito.prestiti.containsKey(l.getTitolo())) {
                Prestito p = Prestito.prestiti.get(si);
                alert.setHeaderText(l.toString() + "\nBook on loan by " + p.getUtenteName());
            } else 
                alert.setHeaderText(l.toString());

            ButtonType btnOK = new ButtonType("Si");
            ButtonType btnNO = new ButtonType("No");

            alert.getButtonTypes().setAll(btnOK, btnNO);

            alert.showAndWait().ifPresentOrElse(result -> {
                if (result == btnOK) {
                    try {
                        switchToRemove(si);
                    } catch (Exception ex) {
                    }
                } else if (result == btnNO) {
                }
            }, () -> {
                System.out.println("No button was clicked");
            });
        });
    }
}
