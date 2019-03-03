package com.akjos.myLibrary.tools;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;
import java.util.ResourceBundle;


public class DialogLibrary {
    private static ResourceBundle bundle = ResourceBundle.getBundle("bundles.message");

    public static String categoryDialogLoadData(String textInput) {
        TextInputDialog dialog = new TextInputDialog(textInput);
        dialog.setTitle(bundle.getString("dialog.loadData.title"));
        dialog.setContentText(bundle.getString("dialog.loadData.content"));
        Optional<String> result = dialog.showAndWait();
        return result.orElse("");
    }

    public static void errorFieldDialog(String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(bundle.getString("dialog.error.title"));
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    public static Boolean ConfirmationDeleteDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(bundle.getString("dialog.confirmation.title"));
        alert.setHeaderText(null);
        alert.setContentText(bundle.getString("dialog.confirmation.text"));

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            return true;
        } else {
            return false;
        }
    }

}
