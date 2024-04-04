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
        try {
            if (txtSearch.getText().trim().length() == 0) {
                new Alert(Alert.AlertType.INFORMATION, "Wrong argument!").showAndWait();
                return;
            }

//            int code = Integer.parseInt(txtSearch.getText());
            String code = txtSearch.getText();
            Libro found = Libro.books.get(code);

            if (found == null) {
                // mostro con un alert che il libro con quel codice non esiste 
                new Alert(Alert.AlertType.INFORMATION, "No books found with the code: " + code).showAndWait();
                txtSearch.setText("");
                
                return;
            }
            
            // mostro con un Alert il libro trovato
            new Alert(Alert.AlertType.INFORMATION, "Books found: " + found + ", Book removed!").showAndWait();
//            Libro.books.remove(code);
            Libro.books.replace(code, null);
            txtSearch.setText("");
            
        } catch (NumberFormatException ex) {
        } catch (IllegalArgumentException ex) {
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
