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
public class Utente implements Comparable<Utente>, Serializable {

    public static int lastCodice = 0;
    private final int codice;
    
    private final String cognome;
    private final String nome;
    private final String password;
    private final String username;
    
    final public static String FILE_PATH = "./.users";
    public final static Map<String, Utente> users = loadUsers(new File(FILE_PATH));

    public Utente(String cognome, String nome, String password, String username) {
        this.cognome = Objects.requireNonNull(cognome, "cognome non specificato");
        this.nome = Objects.requireNonNull(nome, "nome non specificato");
        codice = ++lastCodice;
        this.password = Objects.requireNonNull(password, "password non specificata");
        this.username = Objects.requireNonNull(username, "username non specificato");
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

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
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
        return "Utente " + codice + ": " + cognome + " " + nome + ", password: " + password;
    }

    @Override
    public int compareTo(Utente other) {
        return Integer.compare(codice, other.codice);
    }
    
    private static Map<String, Utente> loadUsers(final File f) {
        try {
            if (!f.exists()) {
                f.createNewFile();
                return new HashMap<>();
            }

            if (!f.canRead()) {
                return new HashMap<>();
            }

            final ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(f));
            final Map<String, Utente> user = (Map<String, Utente>) inputStream.readObject();

            return user;

        } catch (final IOException | ClassNotFoundException ex) {
        }

        return new HashMap<>();

    }

    public static void saveUsers(final Map<String, Utente> users, final File f) {
        try {
            if (!f.exists()) {
                f.createNewFile();
            }

            if (!f.canWrite()) {
                return;
            }

            Iterator<Map.Entry<String, Utente>> iterator = Utente.users.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Utente> entry = iterator.next();
                if (entry.getValue() == null) {
                    iterator.remove();
                }
            }

            final ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(f));
            outputStream.writeObject(users);
        } catch (final IOException ex) {
        }
    }

}
