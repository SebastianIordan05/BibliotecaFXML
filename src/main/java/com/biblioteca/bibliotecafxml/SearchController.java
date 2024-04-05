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

    @FXML
    private void loadPrimary() throws IOException, Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) txtParam.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void searchBooksOnClick(final ActionEvent e) {
        if (txtParam.getText().trim().length() == 0) {
            new Alert(Alert.AlertType.ERROR, "Wrong argument!").showAndWait();
            return;
        }

        String c = txtParam.getText();
        Libro f = Libro.books.get(c);

        if (f == null) {
            Alert bookNotFound = new Alert(Alert.AlertType.ERROR, "No books found with the code: " + c);
            bookNotFound.showAndWait();
            txtParam.setText("");
            return;
        }

        Alert bookFound = new Alert(Alert.AlertType.INFORMATION, "Books found: " + f);
        bookFound.showAndWait();
        txtParam.setText("");
    }
}
