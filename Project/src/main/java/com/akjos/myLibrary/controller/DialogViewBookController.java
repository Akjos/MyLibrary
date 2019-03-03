package com.akjos.myLibrary.controller;

import com.akjos.myLibrary.models.BookModelFX;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.util.ResourceBundle;

public class DialogViewBookController {
    private ResourceBundle bundle = ResourceBundle.getBundle("bundles.message");

    public Label orgTitle;
    public Label title;
    public Label series;
    public Label rating;
    public Label favorite;
    public Label onTheShelf;
    public Label category;
    public Label author;
    public Text description;
    public Label addDate;
    public Label publicationDate;
    public Label isbn;
    public Button previousBt;
    public Button nextBt;

    public Button getPreviousBt() {
        return previousBt;
    }

    public Button getNextBt() {
        return nextBt;
    }

    public void setBook(BookModelFX bookFX) {
        title.setText(bookFX.getTitle());
        orgTitle.setText(bookFX.getOriginalTitle());
        series.setText(bookFX.getSeries());
        rating.setText(Integer.toString(bookFX.getRating()));
        if (bookFX.getFavorite())
            favorite.setText(bundle.getString("dialog.viewBook.yes"));
        else
            favorite.setText(bundle.getString("dialog.viewBook.no"));
        if (bookFX.getOnTheShelf())
            onTheShelf.setText(bundle.getString("dialog.viewBook.yes"));
        else
            onTheShelf.setText(bundle.getString("dialog.viewBook.no"));
        category.setText(bookFX.getCategory().getName());
        author.setText(bookFX.getAuthor().getSurname() + " " + bookFX.getAuthor().getName());
        description.setText(bookFX.getDescription());
        addDate.setText(String.valueOf(bookFX.getAddDate()));
        if (bookFX.getPublicationDate() != 0)
            publicationDate.setText(String.valueOf(bookFX.getPublicationDate()));
        else
            publicationDate.setText("");
        isbn.setText(bookFX.getISBN());
    }
}
