package com.akjos.myLibrary.models;

import com.akjos.myLibrary.database.dao.BookDao;
import com.akjos.myLibrary.database.dao.CategoryDao;
import com.akjos.myLibrary.database.models.Category;
import com.akjos.myLibrary.tools.DataManager;
import com.akjos.myLibrary.tools.DialogLibrary;
import com.akjos.myLibrary.tools.Dialogs.BookDialog;
import com.akjos.myLibrary.tools.Exception.ExceptionMessage;
import com.akjos.myLibrary.tools.ModelConverter;
import javafx.scene.control.TreeItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CategoryModel {

    private TreeItem<String> root = new TreeItem<>();
    private ArrayList<CategoryModelFX> categoryList = new ArrayList<>();
    private ResourceBundle bundle = ResourceBundle.getBundle("bundles.message");

    public CategoryModel() {
        initCategoryRoot();
    }


    private void initCategoryRoot() {
        root.getChildren().clear();
        this.categoryList.clear();
        CategoryDao dao = new CategoryDao();
        List<Category> categoryList = dao.loadAll();
        categoryList.forEach(category -> {
            CategoryModelFX categoryFX = ModelConverter.categoryDbToFx(category);
            this.categoryList.add(categoryFX);
            TreeItem categoryTreeItem = new TreeItem(categoryFX);
            category.getBooks().forEach(book -> {
                categoryTreeItem.getChildren().add(new TreeItem(ModelConverter.bookDbToFX(book)));
            });
            root.getChildren().add(categoryTreeItem);
        });
    }

    public String loadDialogData() {
        return DialogLibrary.categoryDialogLoadData("");
    }

    public void addCategory(CategoryModelFX record) {
        Category category = new Category();
        category.setName(record.getName());
        CategoryDao dao = new CategoryDao();
        Boolean flag = dao.createOrUpdate(category);
        if (flag)
            initCategoryRoot();
    }

    public void addRecord() {
        String name = loadDialogData();
        if (!name.isEmpty())
            addCategory(new CategoryModelFX(name));
    }

    public void editRecord(CategoryModelFX record) {
        String name = DialogLibrary.categoryDialogLoadData(record.getName());
        if (!name.isEmpty()) {
            record.setName(name);
            CategoryDao dao = new CategoryDao();
            dao.createOrUpdate(ModelConverter.categoryFxToDb(record));
            initCategoryRoot();
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
                    initCategoryRoot();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void deleteRecord(CategoryModelFX record) throws ExceptionMessage {
        if (new BookDao().checkIfCanDelete(record.getId(), "category_id")) {
            CategoryDao categoryDao = new CategoryDao();
            categoryDao.deleteById(record.getId());
            initCategoryRoot();
        } else {
            throw new ExceptionMessage(bundle.getString("dialog.error.category.delete"));
        }
    }

    public void deleteRecord(BookModelFX record) {
        BookDao dao = new BookDao();
        if(dao.deleteById(record.getId()))
            initCategoryRoot();
    }

    public TreeItem<String> getRoot() {
        return root;
    }
}
