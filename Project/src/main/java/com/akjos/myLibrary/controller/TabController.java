package com.akjos.myLibrary.controller;

import com.akjos.myLibrary.tools.Exception.ExceptionMessage;

public abstract class TabController {
    protected abstract void addRecord();

    protected abstract void editRecord();

    protected abstract void deleteRecord() throws ExceptionMessage;

}
