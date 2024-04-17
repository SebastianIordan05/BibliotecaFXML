package com.biblioteca.bibliotecafxml;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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

        ButtonType btnOK = new ButtonType("Yes");
        ButtonType btnNO = new ButtonType("No");

        Alert a = new Alert(Alert.AlertType.INFORMATION, "Book found with the name: " + c + "!\nDo you want to search another book?");

        a.getButtonTypes().setAll(btnOK, btnNO);

        a.showAndWait().ifPresentOrElse(result -> {
            if (result == btnNO) {
                try {
                    loadPrimary();
                } catch (Exception ex) {
                }
            }
        }, () -> {
            System.out.println("No button was clicked");
        });
        
        
        txtParam.setText("");
    }
}
