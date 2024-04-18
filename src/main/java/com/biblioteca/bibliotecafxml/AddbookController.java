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
import javafx.scene.control.CheckBox;
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
    private CheckBox chkIsPrestabile;
    
    @FXML
    private void switchToPrimary() throws IOException, Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) txtBook.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void checkAndAdd(final ActionEvent e) {
        if (txtBook.getText().trim().length() == 0 || txtBookAuthor.getText().trim().length() == 0) {

            Alert wrongNameOrAuthor = new Alert(Alert.AlertType.ERROR, "Wrong Name or Author");
            wrongNameOrAuthor.showAndWait();

            return;
        }
        
        Libro l = new Libro(txtBook.getText(), txtBookAuthor.getText(), chkIsPrestabile.isSelected());
        
        Libro.books.put(txtBook.getText(), l);
        
        ButtonType btnOK = new ButtonType("Yes");
        ButtonType btnNO = new ButtonType("No");

        Alert a = new Alert(Alert.AlertType.INFORMATION, "Book added!\nDo you want to add another book?");

        a.getButtonTypes().setAll(btnOK, btnNO);

        a.showAndWait().ifPresentOrElse(result -> {
            if (result == btnNO) {
                try {
                    switchToPrimary();
                } catch (Exception ex) {
                }
            } else if (result == btnOK) {
                txtBook.setText("");
                txtBookAuthor.setText("");
                chkIsPrestabile.setSelected(false);
            }
        }, () -> {
            System.out.println("No button was clicked");
        });
    }
}
