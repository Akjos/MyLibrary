package com.akjos.myLibrary.controller;

import com.akjos.myLibrary.models.AuthorModelFX;
import com.akjos.myLibrary.models.BookModelFX;
import com.akjos.myLibrary.models.CategoryModelFX;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class DialogAddBookController {

    @FXML
    public TextField title;
    @FXML
    public TextField orgTitle;
    @FXML
    public ComboBox series;
    @FXML
    public ComboBox category;
    @FXML
    public ComboBox author;
    @FXML
    public CheckBox onTheShelf;
    @FXML
    public CheckBox favorite;
    @FXML
    public Slider rating;
    @FXML
    public TextArea description;
    @FXML
    public TextField publicationDate;
    @FXML
    public TextField isbn;
    @FXML
    public Button saveBt;
    @FXML
    public Button cancelBt;
    @FXML
    public Button addCategory;
    @FXML
    public Button addAuthor;

    public void initComboBox(ObservableList<AuthorModelFX> authorList, ObservableList<CategoryModelFX> categoryList, ObservableList<String> seriesList) {
        author.setItems(authorList);
        category.setItems(categoryList);
        series.setItems(seriesList);

    }

    public void setBookData(BookModelFX bookModelFX) {
        title.setText(bookModelFX.getTitle());
        orgTitle.setText(bookModelFX.getOriginalTitle());
        series.getSelectionModel().select(bookModelFX.getSeries());
        category.getSelectionModel().select(bookModelFX.getCategory());
        author.getSelectionModel().select(bookModelFX.getAuthor());
        onTheShelf.setSelected(bookModelFX.getOnTheShelf());
        favorite.setSelected(bookModelFX.getFavorite());
        rating.setValue(bookModelFX.getRating());
        description.setText(bookModelFX.getDescription());
        if(bookModelFX.getPublicationDate()!= 0)
            publicationDate.setText(Integer.toString(bookModelFX.getPublicationDate()));
        if (bookModelFX.getISBN() != null)
            isbn.setText(bookModelFX.getISBN());
    }

    public TextField getTitle() {
        return title;
    }

    public void setTitle(TextField title) {
        this.title = title;
    }

    public TextField getOrgTitle() {
        return orgTitle;
    }

    public void setOrgTitle(TextField orgTitle) {
        this.orgTitle = orgTitle;
    }

    public ComboBox getSeries() {
        return series;
    }

    public void setSeries(ComboBox series) {
        this.series = series;
    }

    public ComboBox getCategory() {
        return category;
    }

    public void setCategory(ComboBox category) {
        this.category = category;
    }

    public ComboBox getAuthor() {
        return author;
    }

    public void setAuthor(ComboBox author) {
        this.author = author;
    }

    public CheckBox getOnTheShelf() {
        return onTheShelf;
    }

    public void setOnTheShelf(CheckBox onTheShelf) {
        this.onTheShelf = onTheShelf;
    }

    public CheckBox getFavorite() {
        return favorite;
    }

    public void setFavorite(CheckBox favorite) {
        this.favorite = favorite;
    }

    public Slider getRating() {
        return rating;
    }

    public void setRating(Slider rating) {
        this.rating = rating;
    }

    public TextArea getDescription() {
        return description;
    }

    public void setDescription(TextArea description) {
        this.description = description;
    }

    public TextField getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(TextField publicationDate) {
        this.publicationDate = publicationDate;
    }

    public TextField getIsbn() {
        return isbn;
    }

    public void setIsbn(TextField isbn) {
        this.isbn = isbn;
    }

    public Button getSaveBt() {
        return saveBt;
    }

    public Button getCancelBt() {
        return cancelBt;
    }

    public Button getAddCategory() {
        return addCategory;
    }

    public Button getAddAuthor() {
        return addAuthor;
    }
}
