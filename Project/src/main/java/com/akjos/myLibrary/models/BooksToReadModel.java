package com.akjos.myLibrary.models;

import com.akjos.myLibrary.database.dao.BookDao;
import com.akjos.myLibrary.database.dao.BooksToReadDao;
import com.akjos.myLibrary.database.models.BooksToRead;
import com.akjos.myLibrary.tools.Dialogs.BookDialog;
import com.akjos.myLibrary.tools.Dialogs.BooksToReadDialog;
import com.akjos.myLibrary.tools.ModelConverter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.List;

public class BooksToReadModel {
    private ObservableList<BooksToReadModelFX> booksToReadList = FXCollections.observableArrayList();

    public BooksToReadModel() {
        initBookList();
    }

    public void initBookList() {
        BooksToReadDao dao = new BooksToReadDao();
        List<BooksToRead> list = dao.loadAll();
        booksToReadList.clear();
        list.forEach(book -> booksToReadList.add(ModelConverter.booksToReadToBooksToReadFx(book)));
    }


    public ObservableList getBooksList() {
        return booksToReadList;
    }

    public void addRecord() {
        BooksToReadModelFX book = new BooksToReadDialog().getData();
        if (book != null) {
            BooksToReadDao dao = new BooksToReadDao();
            dao.createOrUpdate(ModelConverter.booksToReadFxToBooksToRead(book));
            initBookList();
        }
    }

    public void editRecord(BooksToReadModelFX selectedItem) {
        new BooksToReadDialog(selectedItem).getData();
        new BooksToReadDao().createOrUpdate(ModelConverter.booksToReadFxToBooksToRead(selectedItem));
        initBookList();
    }

    public void deleteRecord(BooksToReadModelFX selectedItem) {
        if (new BooksToReadDao().deleteById(selectedItem.getId()))
            initBookList();
    }

    public boolean addBook(BooksToReadModelFX record) {
        try {
            BookModelFX book = new BookDialog(record).getData();
            if(book != null) {
                new BookDao().createOrUpdate(ModelConverter.bookFxToDb(book));
                return true;
            }
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
