/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author seba2
 */
public class Causale implements Serializable {
    
    public static int lastCodice = 0;
    private final int codice;
    
    private final String motivazione;
    private final Libro libro;
    private final boolean prestito;
    private final Date date;
    
    final public static String FILE_PATH = "./.causali";
    public final static Map<Integer, Causale> causali = loadCausali(new File(FILE_PATH));

    public Causale(String motivazione, Libro libro, boolean prestito) {
        this.motivazione = Objects.requireNonNull(motivazione, "motivazione non specificata");
        this.libro = Objects.requireNonNull(libro, "libro non specificato");
        this.prestito = Objects.requireNonNull(prestito, "prestito non specificato");
        date = new Date();
        codice = ++lastCodice;
    }

    public String getMotivazione() {
        return motivazione;
    }

    public Libro getLibro() {
        return libro;
    }

    public static int getLastCodice() {
        return lastCodice;
    }

    public int getCodice() {
        return codice;
    }

    @Override
    public String toString() {
        return (prestito ? "Prestito del libro: " + libro.getTitolo() + ", terminato con causale: " :
                "Libro (" + libro.getTitolo() + ") rimosso dalla biblioteca con motivazione: ") + motivazione + ", in data: " + date;
    }
    
    private static Map<Integer, Causale> loadCausali(final File f) {
        try {
            if (!f.exists()) {
                f.createNewFile();
                return new HashMap<>();
            }

            if (!f.canRead()) {
                return new HashMap<>();
            }

            final ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(f));
            final Map<Integer, Causale> c = (Map<Integer, Causale>) inputStream.readObject();

            return c;

        } catch (final IOException | ClassNotFoundException ex) {
        }

        return new HashMap<>();

    }

    public static void saveCausali(final Map<Integer, Causale> causali, final File f) {
        try {
            if (!f.exists()) {
                f.createNewFile();
            }

            if (!f.canWrite()) {
                return;
            }

            Iterator<Map.Entry<Integer, Causale>> iterator = Causale.causali.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Integer, Causale> entry = iterator.next();
                if (entry.getValue() == null) {
                    iterator.remove();
                }
            }

            final ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(f));
            outputStream.writeObject(causali);
        } catch (final IOException ex) {
        }
    }
}
