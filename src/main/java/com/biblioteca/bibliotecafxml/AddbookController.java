package com.biblioteca.bibliotecafxml;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
        
        // aggiungo il libro creato alla map: Map<Integer, Libro>
        App.books.put(libro.getCodice(), libro);
        
        txtBook.setText("");
        txtBookAuthor.setText("");
    }

    @FXML
    private void switchToPrimary(final ActionEvent e) {
        try {
            App.setRoot("primary");
        } catch (final IOException ex) {
        }
    }
}
