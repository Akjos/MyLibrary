package com.akjos.myLibrary.tools;

import com.akjos.myLibrary.database.dao.AuthorDao;
import com.akjos.myLibrary.database.dao.BookDao;
import com.akjos.myLibrary.database.dao.CategoryDao;
import com.akjos.myLibrary.database.models.Author;
import com.akjos.myLibrary.database.models.Book;
import com.akjos.myLibrary.database.models.Category;
import com.akjos.myLibrary.models.AuthorModelFX;
import com.akjos.myLibrary.models.BookModelFX;
import com.akjos.myLibrary.models.CategoryModelFX;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class DataManager {

    public static ObservableList<AuthorModelFX> loadAuthorList() {
        ObservableList<AuthorModelFX> authorList = FXCollections.observableArrayList();
        AuthorDao dao = new AuthorDao();
        List<Author> list = dao.loadAll();
        list.forEach(author -> {
            authorList.add(ModelConverter.authorDbToFx(author));
        });
        return authorList;
    }

    public static ObservableList<CategoryModelFX> loadCategoryList() {
        CategoryDao dao = new CategoryDao();
        List<Category> categoryList = dao.loadAll();
        ObservableList<CategoryModelFX> categoryFXList = FXCollections.observableArrayList();
        categoryList.forEach(category -> {
            CategoryModelFX modelFX = ModelConverter.categoryDbToFx(category);
            categoryFXList.add(modelFX);
        });
        return categoryFXList;
    }

    public static ObservableList<String> loadSeriesList() {
        ObservableList<String> list = FXCollections.observableArrayList();
        BookDao dao = new BookDao();
        List<Book> listSeries = dao.loadBookSeries();
        listSeries.forEach(e -> {
            if (e.getSeries() != null) {
                if (!e.getSeries().isEmpty())
                    list.add(e.getSeries());
            }
        });
        return list;
    }

    public static ObservableList<BookModelFX> loadBookList() {
        ObservableList<BookModelFX> listFX = FXCollections.observableArrayList();
        BookDao dao = new BookDao();
        List<Book> list = dao.loadAll();
        list.forEach(book -> {
            BookModelFX bookFX = ModelConverter.bookDbToFX(book);
            listFX.add(bookFX);
        });
        return listFX;
    }
}
