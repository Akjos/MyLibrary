package com.akjos.myLibrary.database.dao;

import com.akjos.myLibrary.database.DataBaseCreator;
import com.akjos.myLibrary.database.models.Category;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {

    private final ConnectionSource connectionSource;

    public CategoryDao() {
        this.connectionSource = DataBaseCreator.getConnection();
    }

    public boolean createOrUpdate(Category category) {
        try {
            Dao<Category, Integer> dao = DaoManager.createDao(connectionSource, Category.class);
            dao.createOrUpdate(category);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeConnection();
        }
    }

    public void createOrUpdate(ArrayList<Category> categoryList) {
        categoryList.forEach(category -> {
            try {
                Dao<Category, Integer> dao = DaoManager.createDao(connectionSource, Category.class);
                dao.createOrUpdate(category);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closeConnection();
            }
        });

    }

    public boolean deleteById(int id) {
        try {
            Dao<Category, Integer> dao = DaoManager.createDao(connectionSource, Category.class);
            dao.deleteById(id);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeConnection();
        }
    }

    public List<Category> loadAll() {
        try {
            Dao<Category, Integer> dao = DaoManager.createDao(connectionSource, Category.class);
            List<Category> list = dao.queryForAll();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
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
