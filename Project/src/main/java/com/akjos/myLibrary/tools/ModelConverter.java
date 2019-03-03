package com.akjos.myLibrary.tools;

import com.akjos.myLibrary.database.models.Author;
import com.akjos.myLibrary.database.models.Book;
import com.akjos.myLibrary.database.models.BooksToRead;
import com.akjos.myLibrary.database.models.Category;
import com.akjos.myLibrary.models.AuthorModelFX;
import com.akjos.myLibrary.models.BookModelFX;
import com.akjos.myLibrary.models.BooksToReadModelFX;
import com.akjos.myLibrary.models.CategoryModelFX;

public class ModelConverter {

    public static Category categoryFxToDb(CategoryModelFX modelFX) {
        Category record = new Category();
        record.setId(modelFX.getId());
        record.setName(modelFX.getName());
        return record;
    }

    public static CategoryModelFX categoryDbToFx(Category record) {
        CategoryModelFX modelFX = new CategoryModelFX();
        modelFX.setId(record.getId());
        modelFX.setName(record.getName());
        return modelFX;
    }

    public static Author authorFxToDb(AuthorModelFX authorFX) {
        Author author = new Author();
        author.setId(authorFX.getId());
        author.setName(authorFX.getName());
        author.setSurname(authorFX.getSurname());
        author.setNickname(authorFX.getNickname());
        author.setNationality(authorFX.getNationality());
        author.setDob(DateConverter.convertToDate(authorFX.getDob()));
        return author;
    }

    public static AuthorModelFX authorDbToFx(Author authorDb) {
        AuthorModelFX author = new AuthorModelFX();
        author.setId(authorDb.getId());
        author.setName(authorDb.getName());
        author.setSurname(authorDb.getSurname());
        author.setNickname(authorDb.getNickname());
        author.setNationality(authorDb.getNationality());
        author.setDob(DateConverter.convertToLocaldate(authorDb.getDob()));
        author.setName(authorDb.getName());
        return author;
    }

    public static Book bookFxToDb(BookModelFX bookFX) {
        Book book = new Book();
        book.setId(bookFX.getId());
        book.setTitle(bookFX.getTitle());
        book.setOriginalTitle(bookFX.getOriginalTitle());
        book.setSeries(bookFX.getSeries());
        book.setRating(bookFX.getRating());
        book.setFavorite(bookFX.getFavorite());
        book.setOnTheShelf(bookFX.getOnTheShelf());
        book.setDescription(bookFX.getDescription());
        if (bookFX.getPublicationDate() != 0)
            book.setPublicationDate(bookFX.getPublicationDate());
        book.setISBN(bookFX.getISBN());
        book.setAddDate(DateConverter.convertToDate(bookFX.getAddDate()));
        if (bookFX.getAuthor() != null)
            book.setAuthor(authorFxToDb(bookFX.getAuthor()));
        if (bookFX.getCategory() != null)
            book.setCategory(categoryFxToDb(bookFX.getCategory()));
        return book;
    }

    public static BookModelFX bookDbToFX(Book book) {
        BookModelFX bookFX = new BookModelFX();
        bookFX.setId(book.getId());
        bookFX.setTitle(book.getTitle());
        bookFX.setOriginalTitle(book.getOriginalTitle());
        bookFX.setSeries(book.getSeries());
        bookFX.setRating(book.getRating());
        bookFX.setFavorite(book.isFavorite());
        bookFX.setOnTheShelf(book.isOnTheShelf());
        bookFX.setDescription(book.getDescription());
        bookFX.setPublicationDate(book.getPublicationDate());
        bookFX.setISBN(book.getISBN());
        bookFX.setAddDate(DateConverter.convertToLocaldate(book.getAddDate()));
        bookFX.setAuthor(authorDbToFx(book.getAuthor()));
        bookFX.setCategory(categoryDbToFx(book.getCategory()));
        return bookFX;
    }

    public static BooksToReadModelFX booksToReadToBooksToReadFx(BooksToRead book) {
        BooksToReadModelFX bookFx = new BooksToReadModelFX();
        bookFx.setId(book.getId());
        bookFx.setTitle(book.getTitle());
        if(book.getAuthor() != null) {
            bookFx.setAuthor(authorDbToFx(book.getAuthor()));
        }
        return bookFx;
    }

    public static BooksToRead booksToReadFxToBooksToRead(BooksToReadModelFX bookFX) {
        BooksToRead book = new BooksToRead();
        book.setId(bookFX.getId());
        book.setTitle(bookFX.getTitle());
        if (bookFX.getAuthor() != null)
            book.setAuthor(authorFxToDb(bookFX.getAuthor()));
        return book;
    }
}
