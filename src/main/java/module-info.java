module com.biblioteca.bibliotecafxml {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.biblioteca.bibliotecafxml to javafx.fxml;
    exports com.biblioteca.bibliotecafxml;
}
