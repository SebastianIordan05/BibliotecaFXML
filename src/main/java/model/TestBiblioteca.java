package model;

/**
 *
 * @author emanuele
 */
public class TestBiblioteca {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Hello world!!!");
        Utente utente = new Utente("a", "b");
        System.out.println(utente);
        Libro libro = new Libro("d", "c");
        System.out.println(libro);
    }

}
