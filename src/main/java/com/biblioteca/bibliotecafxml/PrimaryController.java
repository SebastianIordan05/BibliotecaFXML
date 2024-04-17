package com.biblioteca.bibliotecafxml;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author iordan.sebastian
 */
public class PrimaryController {

    @FXML
    private Button btnSearch;
    @FXML
    private Button btnSearch1;
    @FXML
    private Button btnBooksList;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnNewUtente;
    @FXML
    private Button btnNewPrestito;
    @FXML
    private Button btnPrestiti;
    @FXML
    private Button btnEndPrestiti;
    
    @FXML
    private void switchToSearch() throws IOException, Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("search.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnSearch.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void switchToAdd() throws IOException, Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("addbook.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnSearch.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void switchToList() throws IOException, Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("bookslist.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnSearch.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void switchToDelete() throws IOException, Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("removebook.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnSearch.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void switchToNewUtente() throws IOException, Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("newutente.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnSearch.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void switchToNewPrestito() throws IOException, Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("makeprestito.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnSearch.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void switchToPrestiti() throws IOException, Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("listprestiti.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnSearch.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void switchToEndPrestiti() throws IOException, Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("endprestito.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnSearch.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void switchToCausali() throws IOException, Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("listcausali.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnSearch.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
