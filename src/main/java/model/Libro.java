package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author emanuele
 */
public class Libro implements Comparable<Libro>, Serializable {

    public static int lastCodice = 0;
    private int codice;

    private final String titolo;
    private final String autore;
    private final boolean isPrestabile;

    final public static String FILE_PATH = "./.books";
    public final static Map<String, Libro> books = loadBooks(new File(FILE_PATH));

    public Libro(String titolo, String autore, boolean isPrestabile) {
        this.titolo = Objects.requireNonNull(titolo, "titolo non specificato");
        this.autore = Objects.requireNonNull(autore, "autore non specificato");
        this.isPrestabile = Objects.requireNonNull(isPrestabile, "non specificato se il libro è prestabile");
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

    public boolean isIsPrestabile() {
        return isPrestabile;
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
        return "Titolo: " + titolo + ", Autore: " + autore + ", Codice: " + codice;
    }

    @Override
    public int compareTo(Libro o) {
        return Integer.compare(codice, o.codice);
    }

    private static Map<String, Libro> loadBooks(final File f) {
        try {
            if (!f.exists()) {
                f.createNewFile();
                return new HashMap<>();
            }

            if (!f.canRead()) {
                return new HashMap<>();
            }

            final ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(f));
            final Map<String, Libro> book = (Map<String, Libro>) inputStream.readObject();

            Libro.lastCodice += book.size();
            return book;

        } catch (final IOException | ClassNotFoundException ex) {
        }

        return new HashMap<>();

    }

    public static void saveBooks(final Map<String, Libro> books, final File f) {
        try {
            if (!f.exists()) {
                f.createNewFile();
            }

            if (!f.canWrite()) {
                return;
            }

            Iterator<Map.Entry<String, Libro>> iterator = Libro.books.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Libro> entry = iterator.next();
                if (entry.getValue() == null) {
                    iterator.remove();
                }
            }

            int i = 0;
            for (Libro libro : Libro.books.values()) {
                i++;
                if (libro != null) {
                    libro.codice = i;
                }
            }

            final ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(f));
            outputStream.writeObject(books);
        } catch (final IOException ex) {
        }
    }
}
