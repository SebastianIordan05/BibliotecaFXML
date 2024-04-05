package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author emanuele
 */
public class Prestito implements Comparable<Prestito>, Serializable {

    private final Libro libro;
    private final Utente utente;
    private final LocalDate inizio;
    private final LocalDate fine;
    
    final public static String FILE_PATH = "./.prestiti";
    public final static Map<String, Prestito> prestiti = loadPrestiti(new File(FILE_PATH));

    public Prestito(Libro libro, Utente utente, LocalDate fine) {
        this.libro = Objects.requireNonNull(libro, "libro non specificato");
        this.utente = Objects.requireNonNull(utente, "utente non specificato");
        inizio = LocalDate.now();
        this.fine = Objects.requireNonNull(fine, "utente non specificato");
    }

    /**
     * Get the value of libro
     *
     * @return the value of libro
     */
    public Libro getLibro() {
        return libro;
    }

    /**
     * Get the value of utente
     *
     * @return the value of utente
     */
    public Utente getUtente() {
        return utente;
    }

    /**
     * Get the value of inizio
     *
     * @return the value of inizio
     */
    public LocalDate getInizio() {
        return inizio;
    }

    /**
     * Get the value of fine
     *
     * @return the value of fine
     */
    public LocalDate getFine() {
        return fine;
    }

//    /**
//     * Set the value of fine
//     *
//     * @param fine new value of fine
//     */
//    public void setFine(LocalDateTime fine) {
//        this.fine = fine;
//    }

    /**
     * Get the value of inCorso
     *
     * @return the value of inCorso
     */
    public boolean isInCorso() {
        return fine == null;
    }
    
    private static Map<String, Prestito> loadPrestiti(final File f) {
        try {
            if (!f.exists()) {
                f.createNewFile();
                return new HashMap<>();
            }

            if (!f.canRead()) {
                return new HashMap<>();
            }

            final ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(f));
            final Map<String, Prestito> prestito = (Map<String, Prestito>) inputStream.readObject();

            return prestito;

        } catch (final IOException | ClassNotFoundException ex) {
        }

        return new HashMap<>();

    }

    public static void savePrestiti(final Map<String, Prestito> prestiti, final File f) {
        try {
            if (!f.exists()) {
                f.createNewFile();
            }

            if (!f.canWrite()) {
                return;
            }

            Iterator<Map.Entry<String, Prestito>> iterator = Prestito.prestiti.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Prestito> entry = iterator.next();
                if (entry.getValue() == null) {
                    iterator.remove();
                }
            }

            final ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(f));
            outputStream.writeObject(prestiti);
        } catch (final IOException ex) {
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.libro);
        hash = 67 * hash + Objects.hashCode(this.fine);
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
        final Prestito other = (Prestito) obj;
        if (!Objects.equals(this.libro, other.libro)) {
            return false;
        }
        if (!Objects.equals(this.fine, other.fine)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Prestito di " + libro + " ad " + utente + ", " + inizio + "/" + (fine == null ? "in corso" : fine);
    }

    private static final Comparator<LocalDate> nullSafeLocalDate = Comparator.nullsLast(Comparator.naturalOrder());
    private static final Comparator<Prestito> byLibroAndFine = Comparator.comparing(Prestito::getLibro).thenComparing(Prestito::getFine, nullSafeLocalDate);

    @Override
    public int compareTo(Prestito o) {
        return byLibroAndFine.compare(this, o);
    }
    
    public String prestito() {
        return "Il libro: " + libro.getTitolo() + " è preso in prestito da: " + utente.getNome() 
                + ", dal giorno: " + inizio + " al giorno " + fine;
    }

}
