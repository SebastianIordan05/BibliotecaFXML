package com.biblioteca.bibliotecafxml;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Libro;

/**
 *
 * @author iordan.sebastian
 */
public class SearchController {

    @FXML
    private TextField txtParam;
    @FXML
    private Button btnSearch;

    // controllo se c'è scritto qualcosa nella TextFied, se si mi salvo il codice e poi mi salvo il libro assegnato a quel codice, con un Alert lo mostro a schermo
    @FXML
    private void searchBooksOnClick(final ActionEvent e) {
        try {
            if (txtParam.getText().trim().length() == 0) {
                throw new IllegalArgumentException();
            }

//            int code = Integer.parseInt(txtParam.getText());
            String code = txtParam.getText();
            Libro found = Libro.books.get(code);

            if (found == null) {
                // mostro con un alert che il libro con quel codice non esiste 
                Alert bookNotFound = new Alert(Alert.AlertType.INFORMATION, "No books found with the code: " + code);
                bookNotFound.showAndWait();
                txtParam.setText("");
                return;
            }
            
            // mostro con un Alert il libro trovato
            Alert bookFound = new Alert(Alert.AlertType.INFORMATION, "Books found: " + found);
            bookFound.showAndWait();

            return;
        } catch (NumberFormatException ex) {
        } catch (IllegalArgumentException ex) {
        }
        
        // mostro con un alert che il codice inserito è sbagliato
        Alert wrongCode = new Alert(Alert.AlertType.ERROR, "Wrong code!");
        wrongCode.showAndWait();

        txtParam.setText("");
    }
    
    @FXML
    private void loadPrimary() throws IOException, Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) txtParam.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
