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
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.Causale;

/**
 * FXML Controller class
 *
 * @author seba2
 */
public class ListcausaliController implements Initializable {

    @FXML
    private ListView<String> lstCausali;
    @FXML
    private Button btnBackToOption;
    
    @FXML
    private void switchToPrimary() throws IOException, Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnBackToOption.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    private void showCausali() {
        for (Causale c : Causale.causali.values()) {
            if (c != null) {
                lstCausali.getItems().add(c.toString());
                System.out.println(c.toString());
            }
        }
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showCausali();
    }    
    
}
