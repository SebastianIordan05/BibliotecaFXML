package com.biblioteca.bibliotecafxml;

import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import model.Causale;
import model.Libro;
import model.Prestito;
import model.Utente;

/**
 * JavaFX App
 *
 * @author iordan.sebastian
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"));
        stage.setScene(scene);
        stage.show();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    @Override
    public void stop() {
        Libro.saveBooks(Libro.books, new File(Libro.FILE_PATH));
        System.out.println("Books saved on exit: " + Libro.books);
        Utente.saveUsers(Utente.users, new File(Utente.FILE_PATH));
        System.out.println("Users saved on exit: " + Utente.users);
        Prestito.savePrestiti(Prestito.prestiti, new File(Prestito.FILE_PATH));
        System.out.println("Prestiti saved on exit: " + Prestito.prestiti);
        Causale.saveCausali(Causale.causali, new File(Causale.FILE_PATH));
        System.out.println("Causali saved on exit: " + Causale.causali);
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static void main(String[] args) {
        System.out.println("Books saved on last exit: " + Libro.books);
        System.out.println("Users saved on last exit: " + Utente.users);
        System.out.println("Prestiti saved on last exit: " + Prestito.prestiti);
        System.out.println("Causali saved on last exit: " + Causale.causali);
        launch();
    }
}
