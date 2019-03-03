package com.akjos.myLibrary.models;

import com.akjos.myLibrary.database.dao.AuthorDao;
import com.akjos.myLibrary.database.dao.BookDao;
import com.akjos.myLibrary.tools.Exception.ExceptionMessage;
import com.akjos.myLibrary.tools.ModelConverter;

import java.util.ResourceBundle;

public class AuthorModel {
    private ResourceBundle bundle = ResourceBundle.getBundle("bundles.message");

    public void addRecord(AuthorModelFX author) {
        if (author != null) {
            AuthorDao dao = new AuthorDao();
            dao.createOrUpdate(ModelConverter.authorFxToDb(author));
        }
    }

    public void editRecord(AuthorModelFX author) {
        AuthorDao dao = new AuthorDao();
        dao.createOrUpdate(ModelConverter.authorFxToDb(author));
    }

    public boolean deleteAuthor(AuthorModelFX author) throws ExceptionMessage {
        if (new BookDao().checkIfCanDelete(author.getId(), "author_id")) {
            AuthorDao dao = new AuthorDao();
            dao.deleteById(author.getId());
            return true;
        } else {
            throw new ExceptionMessage(bundle.getString("dialog.error.author.delete"));
        }
    }
}