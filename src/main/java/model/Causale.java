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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author seba2
 */
public class Causale {
    
    private final String motivazione;
    private final Libro libro;
    private final boolean prestito;
    
    final public static String FILE_PATH = "./.causali";
    public final static Map<String, Causale> causali = loadCausali(new File(FILE_PATH));

    public Causale(String motivazione, Libro libro, boolean prestito) {
        this.motivazione = motivazione;
        this.libro = libro;
        this.prestito = prestito;
    }

    public String getMotivazione() {
        return motivazione;
    }

    public Libro getLibro() {
        return libro;
    }

    @Override
    public String toString() {
        return (prestito ? "Prestito del libro: " + libro.getTitolo() + ", terminato con causale: " :
                "Libro (" + libro.getTitolo() + ") rimosso dalla biblioteca con motivazione: ") + motivazione;
    }

    
    
    private static Map<String, Causale> loadCausali(final File f) {
        try {
            if (!f.exists()) {
                f.createNewFile();
                return new HashMap<>();
            }

            if (!f.canRead()) {
                return new HashMap<>();
            }

            final ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(f));
            final Map<String, Causale> c = (Map<String, Causale>) inputStream.readObject();

            return c;

        } catch (final IOException | ClassNotFoundException ex) {
        }

        return new HashMap<>();

    }

    public static void saveCausali(final Map<String, Causale> users, final File f) {
        try {
            if (!f.exists()) {
                f.createNewFile();
            }

            if (!f.canWrite()) {
                return;
            }

            Iterator<Map.Entry<String, Causale>> iterator = Causale.causali.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Causale> entry = iterator.next();
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
