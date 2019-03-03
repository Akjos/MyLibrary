package com.akjos.myLibrary.controller;

import com.akjos.myLibrary.models.BooksToReadModel;
import com.akjos.myLibrary.models.BooksToReadModelFX;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.util.ResourceBundle;

public class BooksToReadController extends TabController {

    @FXML
    public ListView listView;

    private BooksToReadModel model;
    private ObservableList observableList;
    private ResourceBundle bundle = ResourceBundle.getBundle("bundles.message");


    public void initialize() {
        init();

    }

    public void init() {

        model = new BooksToReadModel();
        observableList = model.getBooksList();
        observableList.addListener((ListChangeListener) c -> init());
        ObservableList<HBox> boxList = FXCollections.observableArrayList();
        observableList.forEach(e -> {
            ListElement book = new ListElement((BooksToReadModelFX) e);
            book.getButton().setOnAction(event -> {
                if (model.addBook((BooksToReadModelFX) e)) {
                    model.deleteRecord((BooksToReadModelFX) e);
                    init();
                }
            });
            boxList.addAll(book);
        });
        listView.setItems(boxList);
    }

    @Override
    protected void addRecord() {
        model.addRecord();
    }

    @Override
    protected void editRecord() {
        ListElement element = (ListElement) listView.getSelectionModel().getSelectedItem();
        if (element != null)
            model.editRecord(element.getBook());
    }

    @Override
    protected void deleteRecord() {
        ListElement element = (ListElement) listView.getSelectionModel().getSelectedItem();
        if (element != null)
            model.deleteRecord(element.getBook());
    }

    private class ListElement extends HBox {
        protected BooksToReadModelFX book;
        protected Button button;

        ListElement(BooksToReadModelFX book) {
            this.book = book;
            Label title = new Label(book.getTitle() + "     " + bundle.getString("booksToRead.record.authorLb") + (book.getAuthor() != null ? book.getAuthor().getSurname() : "Unknown"));
            button = new Button(bundle.getString("booksToRead.record.button"));
            title.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(title, Priority.ALWAYS);
            this.getChildren().addAll(title, button);
        }

        public Button getButton() {
            return button;
        }

        public BooksToReadModelFX getBook() {
            return book;
        }
    }
}
