package com.biblioteca.bibliotecafxml;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 *
 * @author iordan.sebastian
 */
public class PrimaryController {

    @FXML
    private Button btnSearch;
    @FXML
    private Button btnSearch1;

    @FXML
    private void switchToSearch(final ActionEvent e) {
        try {
            App.setRoot("search");
        } catch (final IOException ex) {
        }
    }

    @FXML
    private void switchToAdd(final ActionEvent e) {
        try {
            App.setRoot("addbook");
        } catch (final IOException ex) {
        }

    }
}
