package com.akjos.myLibrary.controller;

import com.akjos.myLibrary.models.BookModelFX;
import com.akjos.myLibrary.models.CategoryModel;
import com.akjos.myLibrary.models.CategoryModelFX;
import com.akjos.myLibrary.models.ModelFX;
import com.akjos.myLibrary.tools.DialogLibrary;
import com.akjos.myLibrary.tools.Exception.ExceptionMessage;
import com.akjos.myLibrary.tools.TypeFX;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class CategoryController extends TabController {
    private CategoryModel model;

    @FXML
    private TreeView<String> treeView;

    public void init() {
        model = new CategoryModel();
        treeView.setRoot(model.getRoot());
    }


    @Override
    protected void addRecord() {
        model.addRecord();
    }

    @Override
    protected void editRecord() {
        TreeItem record = treeView.getSelectionModel().getSelectedItem();
        if (record != null) {
            ModelFX modelFX = (ModelFX) record.getValue();
            if (modelFX.getType() == TypeFX.Category)
                model.editRecord((CategoryModelFX) modelFX);
            else
                model.editRecord((BookModelFX) modelFX);
        }
    }

    @Override
    protected void deleteRecord() throws ExceptionMessage {
        TreeItem record = treeView.getSelectionModel().getSelectedItem();
        if (record != null) {
            if (!DialogLibrary.ConfirmationDeleteDialog())
                return;
            ModelFX modelFX = (ModelFX) record.getValue();
            if (modelFX.getType() == TypeFX.Category)
                model.deleteRecord((CategoryModelFX) modelFX);
            else
                model.deleteRecord((BookModelFX) modelFX);
        }
    }
}
