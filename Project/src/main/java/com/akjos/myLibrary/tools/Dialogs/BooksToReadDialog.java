package com.akjos.myLibrary.tools.Dialogs;

import com.akjos.myLibrary.models.AuthorModel;
import com.akjos.myLibrary.models.AuthorModelFX;
import com.akjos.myLibrary.models.BooksToReadModelFX;
import com.akjos.myLibrary.tools.DataManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.IOException;
import java.util.Optional;
import java.util.ResourceBundle;

public class BooksToReadDialog {
    private ResourceBundle bundle = ResourceBundle.getBundle("bundles.message");
    private Dialog<BooksToReadModelFX> dialog = new Dialog();
    private BooksToReadModelFX book;
    private TextField textField = new TextField();
    private ComboBox comboBox = new ComboBox();

    public BooksToReadDialog() {
        init();
        book = new BooksToReadModelFX();
    }
    public BooksToReadDialog(BooksToReadModelFX bookFX) {
        init();
        book = bookFX;
        textField.setText(book.getTitle());
        comboBox.getSelectionModel().select(book.getAuthor());
    }

    private void init() {
        dialog.setTitle(bundle.getString("booksToRead.title"));
        Label label = new Label(bundle.getString("dialog.booksToRead.titleLb"));
        label.setPrefWidth(50);
        Label label2 = new Label(bundle.getString("dialog.booksToRead.authorLb"));
        label2.setPrefWidth(50);
        textField.setPrefWidth(150);
        comboBox.setPrefWidth(150);
        Button addAuthorBt = new Button(bundle.getString("dialog.booksToRead.addAuthorBt"));
        addAuthorBt.setFont(new Font(32));
        addAuthorBt.setPadding(new Insets(-15, 0, -10, 0));
        addAuthorBt.setOnAction(event -> {
            AuthorModelFX author = null;
            try {
                author = new AuthorDialog().getData();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            if (author != null) {
                new AuthorModel().addRecord(author);
                comboBox.getItems().clear();
                comboBox.setItems(DataManager.loadAuthorList());
                comboBox.getSelectionModel().selectLast();
            }
        });
        HBox box = new HBox(label, textField);
        HBox box2 = new HBox(label2, comboBox, addAuthorBt);
        box2.setAlignment(Pos.CENTER);
        VBox bigBox = new VBox(box, box2);
        bigBox.setSpacing(10);
        comboBox.setItems(DataManager.loadAuthorList());
        dialog.getDialogPane().setContent(bigBox);

    }

    public BooksToReadModelFX getData() {
        ButtonType saveBt = new ButtonType(bundle.getString("dialog.booksToRead.saveBt"), ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveBt, ButtonType.CLOSE);
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveBt) {
                book.setTitle(textField.getText());
                book.setAuthor((AuthorModelFX) comboBox.getSelectionModel().getSelectedItem());
                if(book.getTitle().isEmpty())
                    return null;
                return book;
            } else
                return null;
        });
        Optional<BooksToReadModelFX> result = dialog.showAndWait();
        if (result.isPresent())
            return result.get();
        else
            return null;
    }
}