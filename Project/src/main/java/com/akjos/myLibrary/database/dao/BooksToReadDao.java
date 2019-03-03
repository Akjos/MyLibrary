package com.akjos.myLibrary.database.dao;

import com.akjos.myLibrary.database.DataBaseCreator;
import com.akjos.myLibrary.database.models.BooksToRead;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class BooksToReadDao {

    private final ConnectionSource connectionSource;

    public BooksToReadDao() { this.connectionSource = DataBaseCreator.getConnection(); }

    public boolean createOrUpdate(BooksToRead book) {
        try {
            Dao<BooksToRead, Integer> dao = DaoManager.createDao(connectionSource, BooksToRead.class);
            dao.createOrUpdate(book);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeConnection();
        }
    }

    public boolean deleteById(int id) {
        try {
            Dao<BooksToRead, Integer> dao = DaoManager.createDao(connectionSource, BooksToRead.class);
            dao.deleteById(id);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeConnection();
        }
    }

    public List<BooksToRead> loadAll() {
        try {
            Dao<BooksToRead, Integer> dao = DaoManager.createDao(connectionSource, BooksToRead.class);
            List<BooksToRead> list = dao.queryForAll();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeConnection();
        }
    }

    public void closeConnection() {
        try {
            this.connectionSource.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
