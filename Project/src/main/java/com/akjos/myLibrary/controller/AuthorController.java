package com.akjos.myLibrary.controller;

import com.akjos.myLibrary.models.AuthorModel;
import com.akjos.myLibrary.models.AuthorModelFX;
import com.akjos.myLibrary.tools.DataManager;
import com.akjos.myLibrary.tools.DialogLibrary;
import com.akjos.myLibrary.tools.Dialogs.AuthorDialog;
import com.akjos.myLibrary.tools.Exception.ExceptionMessage;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.time.LocalDate;

public class AuthorController extends TabController {

    @FXML
    public TableView<AuthorModelFX> table;
    @FXML
    public TableColumn<AuthorModelFX, String> name;
    @FXML
    public TableColumn<AuthorModelFX, String> surname;
    @FXML
    public TableColumn<AuthorModelFX, String> nickname;
    @FXML
    public TableColumn<AuthorModelFX, LocalDate> dob;
    @FXML
    public TableColumn<AuthorModelFX, String> nationality;

    private ObservableList<AuthorModelFX> listFX;
    private AuthorModel model = new AuthorModel();

    public void initialize() {
        name.setCellValueFactory(new PropertyValueFactory<AuthorModelFX, String>("name"));
        surname.setCellValueFactory(new PropertyValueFactory<AuthorModelFX, String>("surname"));
        nickname.setCellValueFactory(new PropertyValueFactory<AuthorModelFX, String>("nickname"));
        dob.setCellValueFactory(new PropertyValueFactory<AuthorModelFX, LocalDate>("dob"));
        nationality.setCellValueFactory(new PropertyValueFactory<AuthorModelFX, String>("nationality"));

    }


    @Override
    protected void addRecord() {
        try {
            AuthorModelFX author = new AuthorDialog().getData();
            model.addRecord(author);
            initTable();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void editRecord() {
        AuthorModelFX author = table.getSelectionModel().getSelectedItem();
        if (author != null) {
            try {
                author = new AuthorDialog(author).getData();
                if (author != null) {
                    model.editRecord(author);
                    initTable();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void deleteRecord() throws ExceptionMessage {
        AuthorModelFX author = table.getSelectionModel().getSelectedItem();
        if (author != null) {
            if(!DialogLibrary.ConfirmationDeleteDialog())
                return;
            if(model.deleteAuthor(author));
                initTable();
        }

    }

    public void initTable() {
        listFX = DataManager.loadAuthorList();
        table.setItems(listFX);
    }
}
