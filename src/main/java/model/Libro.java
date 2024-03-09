package model;

import java.io.Serializable;
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

}
