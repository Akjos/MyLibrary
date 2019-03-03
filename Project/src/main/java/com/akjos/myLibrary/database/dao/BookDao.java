package com.akjos.myLibrary.database.dao;

import com.akjos.myLibrary.database.DataBaseCreator;
import com.akjos.myLibrary.database.models.Book;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
    private final ConnectionSource connectionSource;

    public BookDao() {
        this.connectionSource = DataBaseCreator.getConnection();
    }

    public boolean createOrUpdate(Book book) {
        try {
            Dao<Book, Integer> dao = DaoManager.createDao(connectionSource, Book.class);
            dao.createOrUpdate(book);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeConnection();
        }
    }
    public void createOrUpdate(ArrayList<Book> listBook) {
        listBook.forEach(book -> {
            try {
                Dao<Book, Integer> dao = DaoManager.createDao(connectionSource, Book.class);
                dao.createOrUpdate(book);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closeConnection();
            }
        });
    }

    public boolean deleteById(int id) {
        try {
            Dao<Book, Integer> dao = DaoManager.createDao(connectionSource, Book.class);
            dao.deleteById(id);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeConnection();
        }
    }

    public List<Book> loadAll() {
        try {
            Dao<Book, Integer> dao = DaoManager.createDao(connectionSource, Book.class);
            List<Book> list = dao.queryForAll();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeConnection();
        }
    }

    public List<Book> loadBookSeries() {
        try {
            Dao<Book, Integer> dao = DaoManager.createDao(connectionSource, Book.class);
            List<Book> series = dao.queryBuilder().distinct().selectColumns("series").query();
            return series;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeConnection();
        }
    }

    public boolean checkIfCanDelete(int id, String columnName) {
        try {
            Dao<Book, Integer> dao = DaoManager.createDao(connectionSource, Book.class);
            List<Book> value = dao.queryBuilder().where().eq(columnName, id).query();
            return (value.isEmpty());
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeConnection();
        }
    }

    private void closeConnection() {
        try {
            this.connectionSource.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
