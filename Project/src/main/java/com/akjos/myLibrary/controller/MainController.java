package com.akjos.myLibrary.controller;

import com.akjos.myLibrary.tools.DialogLibrary;
import com.akjos.myLibrary.tools.Exception.ExceptionMessage;
import javafx.fxml.FXML;
import javafx.scene.control.TabPane;

public class MainController {

    @FXML
    private TabPane mainTabPane;
    @FXML
    private BooksController booksController;
    @FXML
    private AuthorController authorController;
    @FXML
    private CategoryController categoryController;
    @FXML
    private BooksToReadController booksToReadController;

    private TabController tabController;

    public void initialize() {
        tabController = booksController;
        mainTabPane.getSelectionModel().selectedItemProperty().addListener(

                //Wymyślić lepszy sposób na określanie który controller jest potrzebny
                (ov, t, t1) -> {
                    switch (t1.getId()) {
                        case "booksC":
                            tabController = booksController;
                            booksController.refreshListBook();
                            booksController.setAllList();
                            break;
                        case "authorC":
                            tabController = authorController;
                            authorController.initTable();
                            break;
                        case "categoryC":
                            tabController = categoryController;
                            categoryController.init();
                            break;
                        case "toReadC":
                            tabController = booksToReadController;
                            break;
                        default:
                            System.out.println("No Change Controller");

                    }
                    //Przemyśleć czy tu każdorazowo nie odpalać tabController.init() żeby zaktualizować widoki
                });

    }

    @FXML
    public void searchBtOnAction() {
    }

    @FXML
    public void addBtOnAction() {
        tabController.addRecord();
    }

    @FXML
    public void editBtOnAction() {
        tabController.editRecord();
    }

    @FXML
    public void deleteBtOnAction() {
        try {
            tabController.deleteRecord();
        } catch (ExceptionMessage e) {
            DialogLibrary.errorFieldDialog(e.getMessage());
        }
    }
}
