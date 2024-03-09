package model;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Objects;

/**
 *
 * @author emanuele
 */
public class Prestito implements Comparable<Prestito> {

    private final Libro libro;
    private final Utente utente;
    private final LocalDateTime inizio;
    private LocalDateTime fine = null;

    public Prestito(Libro libro, Utente utente) {
        this.libro = Objects.requireNonNull(libro, "libro non specificato");
        this.utente = Objects.requireNonNull(utente, "utente non specificato");
        inizio = LocalDateTime.now();
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
    public LocalDateTime getInizio() {
        return inizio;
    }

    /**
     * Get the value of fine
     *
     * @return the value of fine
     */
    public LocalDateTime getFine() {
        return fine;
    }

    /**
     * Set the value of fine
     *
     * @param fine new value of fine
     */
    public void setFine(LocalDateTime fine) {
        this.fine = fine;
    }

    /**
     * Get the value of inCorso
     *
     * @return the value of inCorso
     */
    public boolean isInCorso() {
        return fine == null;
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

    private static final Comparator<LocalDateTime> nullSafeLocalDateTime = Comparator.nullsLast(Comparator.naturalOrder());
    private static final Comparator<Prestito> byLibroAndFine = Comparator.comparing(Prestito::getLibro).thenComparing(Prestito::getFine, nullSafeLocalDateTime);

    @Override
    public int compareTo(Prestito o) {
        return byLibroAndFine.compare(this, o);
    }

}
