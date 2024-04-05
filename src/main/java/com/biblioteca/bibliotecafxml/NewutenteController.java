/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Utente;

/**
 * FXML Controller class
 *
 * @author seba2
 */
public class NewutenteController implements Initializable {

    @FXML
    private Button btnCreate;
    @FXML
    private Button btnBackToOption;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtSurname;
    @FXML
    private TextField txtPassword;
    
    @FXML
    private void loadPrimary() throws IOException, Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) txtUsername.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void newUtente() {
        if (txtUsername.getText().trim().length() == 0 || txtName.getText().trim().length() == 0 ||
                txtSurname.getText().trim().length() == 0 || txtPassword.getText().trim().length() == 0) {

            Alert wrongNameOrAuthor = new Alert(Alert.AlertType.ERROR, "Wrong arguments");
            wrongNameOrAuthor.showAndWait();

            return;
        }
        
        Utente u = new Utente(txtSurname.getText(), txtName.getText(), txtPassword.getText(), txtUsername.getText());
        Utente.users.put(txtUsername.getText(), u);
        
        System.out.println("User created: " + u.toString());
        System.out.println("Users: " + Utente.users);
        
        txtUsername.setText("");
        txtName.setText("");
        txtSurname.setText("");
        txtPassword.setText("");
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
