package com.akjos.myLibrary.controller;

import com.akjos.myLibrary.models.AuthorModelFX;
import com.akjos.myLibrary.models.BookModel;
import com.akjos.myLibrary.models.BookModelFX;
import com.akjos.myLibrary.models.CategoryModelFX;
import com.akjos.myLibrary.tools.DialogLibrary;
import com.akjos.myLibrary.tools.Dialogs.ViewBookDialog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.Comparator;

public class BooksController extends TabController {

    @FXML
    public TableView<BookModelFX> table;
    @FXML
    public TableColumn<BookModelFX, String> title;
    @FXML
    public TableColumn<BookModelFX, String> series;
    @FXML
    public TableColumn<AuthorModelFX, String> author;
    @FXML
    public TableColumn<CategoryModelFX, String> category;
    @FXML
    public TableColumn<BookModelFX, Integer> rating;
    @FXML
    public ComboBox<Columns> comboBox;
    @FXML
    public RadioButton radioBtAll;
    @FXML
    public RadioButton radioBtFavorites;
    @FXML
    public RadioButton radioBtLastAdded;
    @FXML
    public TextField searchTextField;

    private BookModel model = new BookModel();
    private ObservableList<BookModelFX> mainList;
    private ObservableList<BookModelFX> observableViewList;
    private FilteredList<BookModelFX> filteredList;
    private SortedList<BookModelFX> sortedList;

    @FXML
    public void initialize() {
        comboBox.getItems().add(new Columns(title.getText(), 1));
        comboBox.getItems().add(new Columns(series.getText(), 2));
        comboBox.getItems().add(new Columns(author.getText(), 3));
        comboBox.getItems().add(new Columns(category.getText(), 4));
        comboBox.getSelectionModel().selectFirst();
        mainList = model.getBookList();
        observableViewList = FXCollections.observableArrayList();
        setAllList();
        initRadioMenu();
        initTableView();
        initSearchField();
    }

    private void initRadioMenu() {
        radioBtAll.setOnAction(e -> setAllList());

        radioBtFavorites.setOnAction(e -> setFavoriteList());

        radioBtLastAdded.setOnAction(e -> setDataList());

    }

    private void initSearchField() {
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            String value = newValue.toLowerCase();
            setPredicateList(value);
//            switch (comboBox.getSelectionModel().getSelectedItem().getId()) {
//                case 1:
//                    filteredList.setPredicate(record ->{
//                        if(newValue == null || newValue.isEmpty())
//                            return true;
//                        String lowerCase = newValue.toLowerCase();
//
//                        if(record.getTitle().toLowerCase().contains(lowerCase))
//                            return true;
//                        return false;
//                    });
//                    break;
//                case 2:
//                    filteredList.setPredicate(record ->{
//                        if(newValue == null || newValue.isEmpty())
//                            return true;
//                        String lowerCase = newValue.toLowerCase();
//
//                        if(record.getSeries().toLowerCase().contains(lowerCase))
//                            return true;
//                        return false;
//                    });
//                    break;
//
//            }

        });
        comboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            setPredicateList(searchTextField.getText());
        });
    }

    private void setPredicateList(String value) {
        int id = comboBox.getSelectionModel().getSelectedItem().getId();
        filteredList.setPredicate(record -> {
            if (value == null || value.isEmpty())
                return true;
            switch (id) {
                case 1:
                    if (record.getTitle().toLowerCase().contains(value))
                        return true;
                    break;

                case 2:
                    if (record.getSeries().toLowerCase().contains(value))
                        return true;
                    break;
                case 3:
                    if (record.getAuthor().getSurname().toLowerCase().contains(value))
                        return true;
                    if (record.getAuthor().getName().toLowerCase().contains(value))
                        return true;
                    if ((record.getAuthor().getName().toLowerCase() + " " + record.getAuthor().getSurname().toLowerCase()).contains(value))
                        return true;
                    break;
                case 4:
                    if (record.getCategory().getName().toLowerCase().contains(value))
                        return true;
                    break;
            }
            return false;
        });
    }

    private void setDataList() {
        SortedList<BookModelFX> list = mainList.sorted(Comparator.comparing(BookModelFX::getAddDate));
        observableViewList.clear();
        for (int i = 0; i < 5; i++) {
            observableViewList.add(list.get(i));
        }
    }

    private void setFavoriteList() {
        observableViewList.clear();
        mainList.forEach(record -> {
            if (record.getFavorite()) {
                observableViewList.add(record);
            }
        });
    }

    public void setAllList() {
        observableViewList.clear();
        mainList.forEach(e -> {
            observableViewList.add(e);
        });
    }

    private void checkRadioField() {
        if (radioBtAll.isSelected())
            setAllList();
        if (radioBtFavorites.isSelected())
            setFavoriteList();
        if (radioBtLastAdded.isSelected())
            setDataList();
    }

    private void initTableView() {
        filteredList = new FilteredList<>(observableViewList);
        sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedList);
        title.setCellValueFactory(new PropertyValueFactory<BookModelFX, String>("title"));
        series.setCellValueFactory(new PropertyValueFactory<BookModelFX, String>("series"));
        author.setCellValueFactory(new PropertyValueFactory<AuthorModelFX, String>("author"));
        category.setCellValueFactory(new PropertyValueFactory<CategoryModelFX, String>("category"));
        rating.setCellValueFactory(new PropertyValueFactory<BookModelFX, Integer>("rating"));
        table.setRowFactory(tv -> {
            TableRow<BookModelFX> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    BookModelFX rowData = row.getItem();
                    displayBook(rowData);
                }
            });
            return row;
        });
    }


    private void displayBook(BookModelFX book) {
        if (book != null) {
            int id = sortedList.indexOf(book);
            try {
                new ViewBookDialog(id, sortedList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void refreshListBook() {
        model.initBookList();
    }

    @Override
    protected void addRecord() {
        model.addRecord();
        checkRadioField();
    }

    @Override
    protected void editRecord() {
        BookModelFX record = table.getSelectionModel().getSelectedItem();
        if (record != null) {
            model.editRecord(record);
            checkRadioField();
        }
    }

    @Override
    protected void deleteRecord() {
        BookModelFX record = table.getSelectionModel().getSelectedItem();
        if (record != null) {
            if (!DialogLibrary.ConfirmationDeleteDialog())
                return;
            model.deleteRecord(record);
            checkRadioField();
        }
    }

    private class Columns {
        private int id;
        private String text;

        Columns(String text, int id) {
            this.text = text;
            this.id = id;
        }

        public int getId() {
            return id;
        }

        @Override
        public String toString() {
            return text;
        }
    }
}
