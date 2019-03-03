package com.akjos.myLibrary.models;

import com.akjos.myLibrary.database.dao.BookDao;
import com.akjos.myLibrary.tools.DataManager;
import com.akjos.myLibrary.tools.Dialogs.BookDialog;
import com.akjos.myLibrary.tools.ModelConverter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;

public class BookModel {
    private ObservableList<BookModelFX> listFX = FXCollections.observableArrayList();

    public BookModel() {
        initBookList();
    }

    public void initBookList() {
        listFX.clear();
        listFX.addAll(DataManager.loadBookList());
    }

    public ObservableList<BookModelFX> getBookList() {
        return listFX;
    }

    public void addRecord() {
        try {
            BookDialog dialog = new BookDialog();
            BookModelFX bookFX = dialog.getData();
            if (bookFX != null) {
                BookDao dao = new BookDao();
                Boolean flag = dao.createOrUpdate(ModelConverter.bookFxToDb(bookFX));
                if (flag)
                    initBookList();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editRecord(BookModelFX record) {
        try {
            BookDialog dialog = new BookDialog(record);
            BookModelFX bookFX = dialog.getData();
            if (bookFX != null) {
                BookDao dao = new BookDao();
                Boolean flag = dao.createOrUpdate(ModelConverter.bookFxToDb(bookFX));
                if (flag)
                    initBookList();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteRecord(BookModelFX record) {
        BookDao dao = new BookDao();
        if (dao.deleteById(record.getId()))
            initBookList();

    }
}
