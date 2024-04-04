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
public class AddbookController {

    @FXML
    private TextField txtBook;
    @FXML
    private TextField txtBookAuthor;
    @FXML
    private Button btnAdd;

    // controllo se c'è scritto qualcosa nelle due TextField, se si creo un nuovo libro e lo aggiungo wrongNameOrAuthor Map<Integer, Libro> books
    @FXML
    public void checkAndAdd(final ActionEvent e) {
        if (txtBook.getText().trim().length() == 0 || txtBookAuthor.getText().trim().length() == 0) {

            Alert wrongNameOrAuthor = new Alert(Alert.AlertType.ERROR, "Wrong Name or Author");
            wrongNameOrAuthor.showAndWait();

            return;
        }
        
        // creo un nuovo libro
        Libro libro = new Libro(txtBook.getText(), txtBookAuthor.getText());
        
        // aggiungo il libro creato alla map: Map<String, Libro>
        Libro.books.put(txtBook.getText(), libro);

        
        txtBook.setText("");
        txtBookAuthor.setText("");
    }
    
    @FXML
    private void switchToPrimary() throws IOException, Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) txtBook.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
