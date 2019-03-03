package com.akjos.myLibrary.tools.Dialogs;

import com.akjos.myLibrary.controller.DialogAddBookController;
import com.akjos.myLibrary.models.*;
import com.akjos.myLibrary.tools.DataManager;
import com.akjos.myLibrary.tools.DialogLibrary;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class BookDialog {
    ResourceBundle bundle = ResourceBundle.getBundle("bundles.message");
    private Dialog<BookModelFX> dialog = new Dialog();
    private DialogAddBookController controller;
    private BookModelFX bookFX;

    public BookDialog() throws IOException {
        init();
        bookFX = new BookModelFX();


    }

    public BookDialog(BookModelFX bookFX) throws IOException {
        init();
        this.bookFX = bookFX;
        controller.setBookData(this.bookFX);

    }

    public BookDialog(BooksToReadModelFX book) throws IOException {
        init();
        this.bookFX = new BookModelFX();
        this.bookFX.setTitle(book.getTitle());
        if (book.getAuthor() != null)
            this.bookFX.setAuthor(book.getAuthor());
        controller.setBookData(this.bookFX);

    }

    private void init() throws IOException {
        FXMLLoader loader = new FXMLLoader(DialogLibrary.class.getResource("/fxml/DialogAddBook.fxml"));
        loader.setResources(bundle);
        Parent parent = loader.load();
        dialog.getDialogPane().setContent(parent);
        controller = loader.getController();
        controller.initComboBox(DataManager.loadAuthorList(), DataManager.loadCategoryList(), DataManager.loadSeriesList());
    }

    public BookModelFX getData() {
        Button saveBt = controller.getSaveBt();
        Button cancelBt = controller.getCancelBt();
        Button addCategory = controller.getAddCategory();
        Button addAuthor = controller.getAddAuthor();

        addAuthor.setOnAction(e -> {
            AuthorModelFX author = null;
            try {
                author = new AuthorDialog().getData();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            if (author != null) {
                new AuthorModel().addRecord(author);
                ComboBox box = controller.getAuthor();
                box.getItems().clear();
                box.setItems(DataManager.loadAuthorList());
                box.getSelectionModel().selectLast();
            }
        });
        addCategory.setOnAction(e -> {
            String category = DialogLibrary.categoryDialogLoadData("");
            if (!category.isEmpty()) {
                CategoryModelFX categoryModel = new CategoryModelFX(category);
                new CategoryModel().addCategory(categoryModel);
                ComboBox comboBox = controller.getCategory();
                comboBox.getItems().clear();
                comboBox.setItems(DataManager.loadCategoryList());
                comboBox.getSelectionModel().selectLast();
            }
        });

        saveBt.setOnAction(e -> {
            if (validForm()) {
                DialogLibrary.errorFieldDialog(bundle.getString("dialog.error.book.text"));
                return;
            }
            bookFX.setTitle(controller.getTitle().getText());
            bookFX.setOriginalTitle(controller.getOrgTitle().getText());
            Object seriesItem = controller.getSeries().getSelectionModel().getSelectedItem();
            if ((seriesItem != null))
                bookFX.setSeries(seriesItem.toString());
            bookFX.setCategory((CategoryModelFX) controller.getCategory().getSelectionModel().getSelectedItem());
            bookFX.setAuthor((AuthorModelFX) controller.getAuthor().getSelectionModel().getSelectedItem());
            bookFX.setRating((int) controller.getRating().getValue());
            bookFX.setFavorite(controller.getFavorite().isSelected());
            bookFX.setOnTheShelf(controller.getOnTheShelf().isSelected());
            bookFX.setDescription(controller.getDescription().getText());
            if (controller.getIsbn().getText().isEmpty())
                bookFX.setISBN(null);
            else
                bookFX.setISBN(controller.getIsbn().getText());
            String publicationDate = controller.getPublicationDate().getText();
            if (!publicationDate.isEmpty())
                bookFX.setPublicationDate(Integer.parseInt(publicationDate));
            bookFX.setAddDate(LocalDate.now());

            dialog.setResult(bookFX);
        });

        cancelBt.setOnAction(e -> dialog.close());

        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        Node closeBt = dialog.getDialogPane().lookupButton(ButtonType.CLOSE);
        closeBt.setVisible(false);
        dialog.setResultConverter(dialogButton -> null);
        Optional<BookModelFX> result = dialog.showAndWait();
        if (result.isPresent())
            return result.get();
        return null;
    }

    private Boolean validForm() {
        return ((controller.getTitle().getText().isEmpty())
                || (controller.getAuthor().getSelectionModel().getSelectedItem() == null)
                || (controller.getCategory().getSelectionModel().getSelectedItem() == null));

    }
}
