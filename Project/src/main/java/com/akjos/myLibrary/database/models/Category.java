package com.akjos.myLibrary.database.models;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "category")
public class Category {

    @DatabaseField(generatedId = true, canBeNull = false)
    private int id;
    @DatabaseField(unique = true, canBeNull = false)
    private String name;
    @ForeignCollectionField(columnName = "book_id")
    private ForeignCollection<Book> books;

    public Category() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }

    public ForeignCollection<Book> getBooks() { return books; }

    public void setBooks(ForeignCollection<Book> books) { this.books = books; }
}
