package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author emanuele
 */
public class Libro implements Comparable<Libro>, Serializable {

    public static int lastCodice = 0;
    private final int codice;

    private final String titolo;
    private final String autore;
    
    final public static String FILE_PATH = "./.books"; // path del file
    public final static Map<Integer, Libro> books = loadBooks(new File(FILE_PATH)); // all'avvio del programma carica dal dile FILE_PATH i precedenti Libri serializzati

    public Libro(String titolo, String autore) {
        this.titolo = Objects.requireNonNull(titolo, "titolo non specificato");
        this.autore = Objects.requireNonNull(autore, "autore non specificato");
        codice = ++lastCodice;
    }

    /**
     * Get the value of codice
     *
     * @return the value of codice
     */
    public int getCodice() {
        return codice;
    }

    /**
     * Get the value of titolo
     *
     * @return the value of titolo
     */
    public String getTitolo() {
        return titolo;
    }

    /**
     * Get the value of autore
     *
     * @return the value of autore
     */
    public String getAutore() {
        return autore;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.codice;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Libro other = (Libro) obj;
        if (this.codice != other.codice) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Libro (" + codice + ") \"" + titolo + "\" di " + autore;
    }

    @Override
    public int compareTo(Libro o) {
        return Integer.compare(codice, o.codice);
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
    public static void saveBooks(final Map<Integer, Libro> books, final File f) {
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
