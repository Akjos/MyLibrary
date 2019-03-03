package com.akjos.myLibrary.database.models;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable(tableName = "books")
public class Book {
    @DatabaseField(generatedId = true, canBeNull = false)
    private int id;
    @DatabaseField(canBeNull = false)
    private String title;
    @DatabaseField
    private String originalTitle;
    @DatabaseField
    private String series;
    @DatabaseField(canBeNull = false)
    private int rating;
    @DatabaseField
    private boolean favorite;
    @DatabaseField
    private boolean onTheShelf;
    @DatabaseField(dataType = DataType.LONG_STRING)
    private String description; //Długi string + uwagi
    @DatabaseField
    private Date addDate;
    @DatabaseField
    private int publicationDate;
    @DatabaseField(unique = true)
    private String ISBN;
    @DatabaseField(columnName = "author_id", foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true, canBeNull = false)
    public Author author;
    @DatabaseField(columnName = "category_id", foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true, canBeNull = false)
    private Category category;

    public Book() { }

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

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public boolean isOnTheShelf() {
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

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
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

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
