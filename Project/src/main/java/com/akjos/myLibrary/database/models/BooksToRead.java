package com.akjos.myLibrary.database.models;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName = "booksToRead")
public class BooksToRead {
    @DatabaseField(generatedId = true, canBeNull = false)
    private int id;
    @DatabaseField(canBeNull = false)
    private String title;
    @DatabaseField(columnName = "author_id", foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true)
    public Author author;

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

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
