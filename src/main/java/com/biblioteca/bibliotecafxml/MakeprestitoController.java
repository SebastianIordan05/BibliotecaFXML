/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.biblioteca.bibliotecafxml;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import model.Libro;
import model.Prestito;
import model.Utente;

/**
 * FXML Controller class
 *
 * @author seba2
 */
public class MakeprestitoController implements Initializable {
    
    private String selectedUser;
    private String selectedBook;
    private LocalDate date1; // inizio
    private LocalDate date2; // fine

    @FXML
    private ChoiceBox<String> lstUtenti;
    @FXML
    private ChoiceBox<String> lstLibri;
    @FXML
    private DatePicker dateInizio;
    @FXML
    private DatePicker dateFine;
    @FXML
    private Button btnNewPrestito;
    @FXML
    private Button btnBackToOption;
    
    @FXML
    private void switchToPrimary() throws IOException, Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) lstUtenti.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void getSelectedUser() {
        selectedUser = lstUtenti.getSelectionModel().getSelectedItem();
        System.out.println("selectedUser: " + selectedUser);
    }
    
    @FXML
    private void getSelectedBook() {
        selectedBook = lstLibri.getSelectionModel().getSelectedItem();
        System.out.println("selectedBook: " + selectedBook);
    }
    
    @FXML
    private void setEndDate() {
        date2 = dateFine.getValue();
        System.out.println("End date: " + date2);
    }
    
    @FXML
    private void makePrestito() {
        if (selectedUser != null || selectedBook != null || date2 != null) {
            Libro l = Libro.books.get(selectedBook);
            Utente u = Utente.users.get(selectedUser);
            Prestito p = new Prestito(l, u, date2);
            
            Prestito.prestiti.put(u.getUsername(), p);
            
            System.out.println("Prestito done: " + p.toString());
            System.out.println("Prestiti: " + Prestito.prestiti);
        }
    }
    
    private void initializeLstUser() {
        for (Utente utente : Utente.users.values()) {
            if (utente != null) {
                lstUtenti.getItems().add(utente.getUsername());
            }
        }
    }
    
    private void initializeLstBooks() {
        for (Libro libro : Libro.books.values()) {
            if (libro != null) {
                lstLibri.getItems().add(libro.getTitolo());
            }
        }
    }
    
    private void initializeDatePicker() {
        dateInizio.setValue(LocalDate.now());
        date1 = dateInizio.getValue();
        System.out.println("Start date: " + date1);
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeLstUser();
        initializeLstBooks();
        initializeDatePicker();
    }    
    
}
