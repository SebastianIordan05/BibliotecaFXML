package com.biblioteca.bibliotecafxml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import model.Libro;

/**
 * JavaFX App
 *
 * @author iordan.sebastian
 */
public class App extends Application {

    private static Scene scene;
    final private static String FILE_PATH = "./.books"; // path del file
    public final static Map<Integer, Libro> books = loadBooks(new File(FILE_PATH)); // all'avvio del programma carica dal dile FILE_PATH i precedenti Libri serializzati

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
//        System.out.println("FILE_PATH: " + FILE_PATH);
        stage.setScene(scene);
        stage.show();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    // serializzazione dei libri aggiunti nel file FILE_PATH alla chiusura del programma
    @Override
    public void stop() {
        saveBooks(books, new File(FILE_PATH));
        System.out.println("Books saved on exit: " + books);
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static void main(String[] args) {
        System.out.println("Books saved on last exit: " + books);
        launch();
    }

    // deserializzazione dei Libri da un file 
    private static Map<Integer, Libro> loadBooks(final File f) {
        try {
            // controllo se il file esiste, se no lo creo
            if (!f.exists()) {
                f.createNewFile();
                return new HashMap<>();
            }
            
            // controllo se il file è leggibile
            if (!f.canRead()) {
                return new HashMap<>();
            }
            
            // leggo da file i Libri serializzati
            final ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(f));
            final Map<Integer, Libro> book = (Map<Integer, Libro>) inputStream.readObject();
            
            // assegno al libro creato il suo codice identificativo
            Libro.lastCodice += book.size();
            return book;

        } catch (final IOException | ClassNotFoundException ex) {
        }

        return new HashMap<>();

    }

    // serializzazione dei Libri in un file 
    private static void saveBooks(final Map<Integer, Libro> books, final File f) {
        try {
            // controllo se il file esiste, se no lo creo
            if (!f.exists()) {
                f.createNewFile();
            }
            
            // controllo se il file è leggibile
            if (!f.canWrite()) {
                return;
            }
            
            // serializzo i Libri su file
            final ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(f));
            outputStream.writeObject(books);
        } catch (final IOException ex) {
        }
    }
}
