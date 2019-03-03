package com.akjos.myLibrary.database.dao;

import com.akjos.myLibrary.database.DataBaseCreator;
import com.akjos.myLibrary.database.models.Author;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao {
    private final ConnectionSource connectionSource;

    public AuthorDao() {
        this.connectionSource = DataBaseCreator.getConnection();
    }

    public boolean createOrUpdate(Author author) {
        try {
            Dao<Author, Integer> dao = DaoManager.createDao(connectionSource, Author.class);
            dao.createOrUpdate(author);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeConnection();
        }
    }
    public void createOrUpdate(ArrayList<Author> author) {
        author.forEach(record ->{
            try {
                Dao<Author, Integer> dao = DaoManager.createDao(connectionSource, Author.class);
                dao.createOrUpdate(record);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closeConnection();
            }
        });
    }

    public boolean deleteById(int id) {
        try {
            Dao<Author, Integer> dao = DaoManager.createDao(connectionSource, Author.class);
            dao.deleteById(id);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeConnection();
        }
    }

    public List<Author> loadAll() {
        try {
            Dao<Author, Integer> dao = DaoManager.createDao(connectionSource, Author.class);
            List<Author> list = dao.queryForAll();
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
