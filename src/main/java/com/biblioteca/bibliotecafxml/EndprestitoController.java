/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.biblioteca.bibliotecafxml;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Prestito;

/**
 *
 * @author seba2
 */
public class EndprestitoController implements Initializable {

    private String selectedBook;
    private String selectedCausale;

    @FXML
    private ChoiceBox<String> chbLibro;
    @FXML
    private ChoiceBox<String> chbCausale;
    @FXML
    private Button btnBackToOption;
    @FXML
    private Button btnEndPrestito;
    @FXML
    private TextField txtPassword;
    @FXML
    private TextField txtUtente;

    @FXML
    private void switchToPrimary() throws IOException, Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) chbLibro.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void switchToPrestiti() throws IOException, Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("listprestiti.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnBackToOption.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void endPrestito() {
        System.out.println("selectedBook: " + selectedBook + ", selectedCausale: " + selectedCausale);
        if (selectedBook == null || selectedCausale == null) {
            new Alert(Alert.AlertType.ERROR, "Wrong arguments!").showAndWait();
        } else {
            Prestito p = Prestito.prestiti.get(selectedBook);
            if (p != null) {
                if (p.getUtente().getPassword().equals(txtPassword.getText())) {
                    Prestito.prestiti.replace(selectedBook, null);
                    System.out.println(Prestito.prestiti);
                    new Alert(Alert.AlertType.INFORMATION, "Loan terminated with success!").showAndWait();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Wrong password!").showAndWait();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Loan already terminated!").showAndWait();
            }

        }
    }

    @FXML
    private void getSelectedCausale() {
        selectedCausale = chbCausale.getSelectionModel().getSelectedItem();
        System.out.println("selectedUser: " + selectedCausale);
    }

    @FXML
    private void getSelectedBook() {
        selectedBook = chbLibro.getSelectionModel().getSelectedItem();
        Prestito p = Prestito.prestiti.get(selectedBook);
        txtUtente.setText(p.getUtente().getNome() + " " + p.getUtente().getCognome());
        System.out.println("selectedBook: " + selectedBook);
    }

    private void initializeLstBooks() {
        for (Prestito prestito : Prestito.prestiti.values()) {
            if (prestito != null) {
                chbLibro.getItems().add(prestito.getLibro().getTitolo());
            }
        }
    }

    private void initializeLstCausali() {
        chbCausale.getItems().addAll("Libro restituito in anticipo", "Libro perso/danneggiato e ripagato");
    }

    /**
     * Initializes the controller
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeLstBooks();
        initializeLstCausali();
    }

}
