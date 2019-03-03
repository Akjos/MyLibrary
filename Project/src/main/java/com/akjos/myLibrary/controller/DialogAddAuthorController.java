package com.akjos.myLibrary.controller;

import com.akjos.myLibrary.models.AuthorModelFX;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;


public class DialogAddAuthorController {

    @FXML
    public TextField textName;
    @FXML
    public TextField textSurname;
    @FXML
    public TextField textNickname;
    @FXML
    public TextField textNationality;
    @FXML
    public DatePicker dateDOB;
    @FXML
    public Button saveBt;
    @FXML
    public Button cancelBt;

    @FXML
    public void initialize() {

    }

    public void setAuthorData(AuthorModelFX authorModel) {
        setTextName(authorModel.getName());
        setTextSurname(authorModel.getSurname());
        setTextNickname(authorModel.getNickname());
        setTextNationality(authorModel.getNationality());
        setDateDOB(authorModel.getDob());


    }

    public String getTextName() {
        return textName.getText();
    }

    public String getTextSurname() {
        return textSurname.getText();
    }

    public String getTextNickname() { return textNickname.getText(); }

    public String getTextNationality() {
        return textNationality.getText();
    }

    public LocalDate getDateDOB() {
        return dateDOB.getValue();
    }

    public Button getSaveBt() { return saveBt; }

    public Button getCancelBt() {
        return cancelBt;
    }

    public void setTextName(String text) { this.textName.setText(text); }

    public void setTextSurname(String text) { this.textSurname.setText(text); }

    public void setTextNickname(String text) { this.textNickname.setText(text); }

    public void setTextNationality(String text) { this.textNationality.setText(text); }

    public void setDateDOB(LocalDate date) { this.dateDOB.setValue(date); }

}
