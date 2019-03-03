package com.akjos.myLibrary.tools.Dialogs;

import com.akjos.myLibrary.controller.DialogAddAuthorController;
import com.akjos.myLibrary.models.AuthorModelFX;
import com.akjos.myLibrary.tools.DialogLibrary;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

import java.io.IOException;
import java.util.Optional;
import java.util.ResourceBundle;

public class AuthorDialog {
    private ResourceBundle bundle = ResourceBundle.getBundle("bundles.message");
    private Dialog<AuthorModelFX> dialog = new Dialog();
    private DialogAddAuthorController controller;
    private AuthorModelFX authorFX;

    public AuthorDialog() throws IOException {
        init();
        authorFX = new AuthorModelFX();
    }

    public AuthorDialog(AuthorModelFX authorModel) throws IOException {
        init();
        this.authorFX = authorModel;
        controller.setAuthorData(authorFX);
    }

    private void init() throws IOException {

        FXMLLoader loader = new FXMLLoader(AuthorDialog.class.getResource("/fxml/DialogAddAuthor.fxml"));
        loader.setResources(bundle);
        Parent parent = loader.load();
        dialog.getDialogPane().setContent(parent);
        controller = loader.getController();
    }

    public AuthorModelFX getData() {
        Button saveBt = controller.getSaveBt();
        Button cancelBt = controller.getCancelBt();

        saveBt.setOnAction(e -> {
            if (validForm()){
                DialogLibrary.errorFieldDialog(bundle.getString("dialog.error.author.text"));
                return;
            }
            authorFX.setName(controller.getTextName());
            authorFX.setSurname(controller.getTextSurname());
            authorFX.setNickname(controller.getTextNickname());
            authorFX.setName(controller.getTextName());
            authorFX.setDob(controller.getDateDOB());
            authorFX.setNationality(controller.getTextNationality());
            dialog.setResult(authorFX);
        });

        cancelBt.setOnAction(e -> {
            dialog.close();
        });

        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        Node closeBt = dialog.getDialogPane().lookupButton(ButtonType.CLOSE);
        closeBt.setVisible(false);
        dialog.setResultConverter(dialogButton -> null);
        Optional<AuthorModelFX> result = dialog.showAndWait();
        if (result.isPresent())
            return result.get();
        return null;
    }

    private boolean validForm() {
        return (controller.getTextName().isEmpty()
                || controller.getTextSurname().isEmpty());
    }
}