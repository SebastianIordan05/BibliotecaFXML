/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.biblioteca.bibliotecafxml;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Libro;
import model.Prestito;

/**
 * FXML Controller class
 *
 * @author seba2
 */
public class EditbookController implements Initializable {

    private String selectedBook;
    private Libro l;
    private Libro l2;

    @FXML
    private ChoiceBox<String> lstLibri;
    @FXML
    private Button btnBackToOPtion;
    @FXML
    private TextField txtOldTitolo;
    @FXML
    private TextField txtOldAutore;
    @FXML
    private TextField txtOldPrestabile;
    @FXML
    private TextField txtNewTitolo;
    @FXML
    private TextField txtNewAutore;
    @FXML
    private Button btnConfirmChange;
    @FXML
    private CheckBox chkNewPreatabile;

    @FXML
    private void switchToPrimary() throws IOException, Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) lstLibri.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void showOldInfo(String book) {
        l = Libro.books.get(book);

        if (Prestito.prestiti.containsKey(l.getTitolo())) {
            new Alert(Alert.AlertType.ERROR, "You can't edit a book that is in loan to someone!").showAndWait();

            txtOldTitolo.setText("");
            txtOldAutore.setText("");
            txtOldPrestabile.setText("");

            lstLibri.getSelectionModel().select(-1);

            return;
        }

        txtOldTitolo.setText(l.getTitolo());
        txtOldAutore.setText(l.getAutore());
        txtOldPrestabile.setText("" + l.isIsPrestabile());
    }

    @FXML
    private void editBook() {
        String titolo = txtNewTitolo.getText().trim().isEmpty() ? txtOldTitolo.getText() : txtNewTitolo.getText();
        String autore = txtNewAutore.getText().trim().isEmpty() ? txtOldAutore.getText() : txtNewAutore.getText();

        l2 = new Libro(titolo, autore, chkNewPreatabile.isSelected());
        l2.setCodice(l.getCodice());

        Libro.books.remove(l.getTitolo());
        Libro.books.put(l2.getTitolo(), l2);

        ButtonType btnOK = new ButtonType("Yes");
        ButtonType btnNO = new ButtonType("No");

        Alert a = new Alert(Alert.AlertType.INFORMATION, "Done!\nOld book: " + l.toString() + "\nNew book: " + l2.toString()
                + "\n\nDo you want to edit another book?");

        a.getButtonTypes().setAll(btnOK, btnNO);

        a.showAndWait().ifPresentOrElse(result -> {
            if (result == btnNO) {
                try {
                    switchToPrimary();
                } catch (Exception ex) {
                }
            } else if (result == btnOK) {
                txtNewTitolo.setText("");
                txtNewAutore.setText("");
                chkNewPreatabile.setSelected(false);
                txtOldTitolo.setText("");
                txtOldAutore.setText("");
                txtOldPrestabile.setText("");
                
                Platform.runLater(() -> {
                    lstLibri.getSelectionModel().clearSelection();
                });
            }
        }, () -> {
            System.out.println("No button was clicked");
        });
    }

    @FXML
    private void getSelectedBook() {
        selectedBook = lstLibri.getSelectionModel().getSelectedItem();
        System.out.println("selectedBook: " + selectedBook);
        showOldInfo(selectedBook);
    }

    private void initializeLstBooks() {
        for (Libro libro : Libro.books.values()) {
            if (libro != null) {
                lstLibri.getItems().add(libro.getTitolo());
            }
        }
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeLstBooks();

        txtOldTitolo.setDisable(true);
        txtOldAutore.setDisable(true);
        txtOldPrestabile.setDisable(true);
    }

}
