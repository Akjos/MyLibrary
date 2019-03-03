package com.akjos.myLibrary.tools.Dialogs;

import com.akjos.myLibrary.controller.DialogViewBookController;
import com.akjos.myLibrary.models.BookModelFX;
import com.akjos.myLibrary.tools.DialogLibrary;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

public class ViewBookDialog {
    ResourceBundle bundle = ResourceBundle.getBundle("bundles.message");
    private Stage stage = new Stage();
    private DialogViewBookController controller;
    private ObservableList<BookModelFX> list;
    private int id;

    public ViewBookDialog(int id, ObservableList<BookModelFX> list) throws IOException {
        this.list = list;
        this.id = id;
        init();
        controller.setBook(list.get(id));
        initButton();
    }

    private void initButton() {
        Button next = controller.getNextBt();
        next.setOnMouseClicked(event -> {
            id++;
            BookModelFX bookFX;
            try {
                bookFX = list.get(id);
            } catch (IndexOutOfBoundsException e) {
                id = 0;
                bookFX = list.get(id);
            }
            controller.setBook(bookFX);
        });
        Button previous = controller.getPreviousBt();
        previous.setOnMouseClicked(event -> {
            id--;
            BookModelFX bookFX;
            try {
                bookFX = list.get(id);
            } catch (IndexOutOfBoundsException e) {
                id = list.size() - 1;
                bookFX = list.get(id);
            }
            controller.setBook(bookFX);
        });
    }

    private void init() throws IOException {
        FXMLLoader loader = new FXMLLoader(DialogLibrary.class.getResource("/fxml/DialogViewBook.fxml"));
        loader.setResources(bundle);
        Parent parent = loader.load();
        stage.setScene(new Scene(parent));
        controller = loader.getController();
        stage.show();
    }
}
