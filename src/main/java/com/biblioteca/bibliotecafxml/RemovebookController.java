/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.biblioteca.bibliotecafxml;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Causale;
import model.Libro;
import model.Prestito;

/**
 * FXML Controller class
 *
 * @author iordan.sebastian
 */
public class RemovebookController implements Initializable {
    
    private String selectedCausale;

    @FXML
    public TextField txtSearch;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnBackToOption;
    @FXML
    private ChoiceBox<String> chCausali;

    @FXML
    private void switchToPrimary() throws IOException, Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) txtSearch.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void searchBooksOnClick(final ActionEvent e) {
        if (txtSearch.getText().trim().length() == 0 || selectedCausale == null) {
            new Alert(Alert.AlertType.ERROR, "Wrong argument!").showAndWait();
            return;
        }

        String c = txtSearch.getText();
        Libro f = Libro.books.get(c);

        if (f == null) {
            new Alert(Alert.AlertType.ERROR, "No books found with the code: " + c).showAndWait();
            txtSearch.setText("");
            return;
        }

        if (Prestito.prestiti.get(f.getTitolo()) != null) {
            new Alert(Alert.AlertType.ERROR, "You cant delete a book that is in loan to someone!").showAndWait();
            return;
        }

        ButtonType btnOK = new ButtonType("Yes");
        ButtonType btnNO = new ButtonType("No");

        Alert a = new Alert(Alert.AlertType.INFORMATION, "Books found: " + f + ", Book removed!\nDo you want to remove another book?");

        a.getButtonTypes().setAll(btnOK, btnNO);

        a.showAndWait().ifPresentOrElse(result -> {
            if (result == btnNO) {
                try {
                    switchToPrimary();
                } catch (Exception ex) {
                }
            }
        }, () -> {
            System.out.println("No button was clicked");
        });

        System.out.println("Book removed: " + f.toString());

//        Libro.books.replace(c, null);
        Libro.books.remove(c);
        Causale ca = new Causale(selectedCausale, f, false);
        Causale.causali.put(ca.getCodice(), ca);
        
        System.out.println("Causale: " + ca.toString());
        
        if (f.getCodice() == Libro.lastCodice) {
            Libro.lastCodice--;
        }

        System.out.println("New Books: " + Libro.books);
        txtSearch.setText("");
    }
    
    @FXML
    private void getSelectedCausale() {
        selectedCausale = chCausali.getSelectionModel().getSelectedItem();
        System.out.println("selectedUser: " + selectedCausale);
    }
    
    private void initializeLstCausali() {
        chCausali.getItems().addAll("LIbro usurato o danneggiato", "Libro perso");
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeLstCausali();
    }

}
