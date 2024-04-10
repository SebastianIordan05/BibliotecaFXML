/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.biblioteca.bibliotecafxml;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Libro;
import model.Prestito;

/**
 * FXML Controller class
 *
 * @author iordan.sebastian
 */
public class RemovebookController implements Initializable {

    @FXML
    public TextField txtSearch;
    @FXML
    private Button btnDelete;

    @FXML
    private void switchToPrimary() throws IOException, Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) txtSearch.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void searchBooksOnClick(final ActionEvent e) {
        if (txtSearch.getText().trim().length() == 0) {
            new Alert(Alert.AlertType.ERROR, "Wrong argument!").showAndWait();
            return;
        }

        String c = txtSearch.getText();
        Libro f = Libro.books.get(c);

        if (f == null) {
            new Alert(Alert.AlertType.ERROR, "No books found with the code: " + c).showAndWait();
            txtSearch.setText("");
            return;
        }
        
        if (Prestito.prestiti.get(f.getTitolo()) != null) {
            new Alert(Alert.AlertType.ERROR, "You cant delete a book that is in loan to someone!").showAndWait();
            return;
        }

        new Alert(Alert.AlertType.INFORMATION, "Books found: " + f + ", Book removed!").showAndWait();
        System.out.println("Book removed: " + f.toString());
        
        Libro.books.replace(c, null);
        if (f.getCodice() == Libro.lastCodice)
            Libro.lastCodice--;
        
        System.out.println("New Books: " + Libro.books);
        txtSearch.setText("");
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
