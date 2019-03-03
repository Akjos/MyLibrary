package com.akjos.myLibrary.models;

import com.akjos.myLibrary.tools.TypeFX;

public class BooksToReadModelFX extends ModelFX {

    private int id;
    private String title;
    private AuthorModelFX author;

    public BooksToReadModelFX() {super(TypeFX.BooksToRead);}

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

    public AuthorModelFX getAuthor() {
        return author;
    }

    public void setAuthor(AuthorModelFX author) {
        this.author = author;
    }

    public String toString(){
        return getTitle();
    }
}
