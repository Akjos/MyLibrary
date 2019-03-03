package com.akjos.myLibrary.models;

import com.akjos.myLibrary.tools.TypeFX;

import java.time.LocalDate;

public class BookModelFX extends ModelFX {
    private int id;
    private String title;
    private String originalTitle;
    private String series;
    private int rating;
    private boolean favorite;
    private boolean onTheShelf;
    private String description; //Długi string + uwagi
    private LocalDate addDate;
    private int publicationDate;
    private String ISBN;
    private CategoryModelFX category;
    private AuthorModelFX author;

    public BookModelFX() {
        super(TypeFX.Book);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public boolean getOnTheShelf() {
        return onTheShelf;
    }

    public void setOnTheShelf(boolean onTheShelf) {
        this.onTheShelf = onTheShelf;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getAddDate() {
        return addDate;
    }

    public void setAddDate(LocalDate addDate) {
        this.addDate = addDate;
    }

    public int getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(int publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public CategoryModelFX getCategory() {
        return category;
    }

    public void setCategory(CategoryModelFX category) {
        this.category = category;
    }

    public AuthorModelFX getAuthor() {
        return author;
    }

    public void setAuthor(AuthorModelFX author) {
        this.author = author;
    }

    public String toString() {
        return ("'" + title + "' || " + author);
    }
}
