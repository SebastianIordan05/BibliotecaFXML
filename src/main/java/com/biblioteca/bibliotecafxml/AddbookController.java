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

    @FXML
    public void checkAndAdd(final ActionEvent e) {
        if (txtBook.getText().trim().length() == 0 || txtBookAuthor.getText().trim().length() == 0) {

            Alert wrongNameOrAuthor = new Alert(Alert.AlertType.ERROR, "Wrong Name or Author");
            wrongNameOrAuthor.showAndWait();

            return;
        }
        
        Libro l = new Libro(txtBook.getText(), txtBookAuthor.getText());
        
        Libro.books.put(txtBook.getText(), l);
        new Alert(Alert.AlertType.INFORMATION, "Book added!").showAndWait();
        
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
