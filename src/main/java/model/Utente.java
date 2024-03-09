package model;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author emanuele
 */
public class Utente implements Comparable<Utente>, Serializable {

    public static int lastCodice = 0;
    private final int codice;
    private final String cognome;
    private final String nome;

    public Utente(String cognome, String nome) {
        this.cognome = Objects.requireNonNull(cognome, "cognome non specificato");
        this.nome = Objects.requireNonNull(nome, "nome non specificato");
        codice = ++lastCodice;
    }

    /**
     * Get the value of cognome
     *
     * @return the value of cognome
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Get the value of nome
     *
     * @return the value of nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Get the value of codice
     *
     * @return the value of codice
     */
    public int getCodice() {
        return codice;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + this.codice;
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
        final Utente other = (Utente) obj;
        return this.codice == other.codice;
    }

    @Override
    public String toString() {
        return "Utente " + codice + ": " + cognome + " " + nome;
    }

    @Override
    public int compareTo(Utente other) {
        return Integer.compare(codice, other.codice);
    }

}
