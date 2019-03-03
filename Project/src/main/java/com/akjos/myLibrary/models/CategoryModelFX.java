package com.akjos.myLibrary.models;

import com.akjos.myLibrary.tools.TypeFX;

public class CategoryModelFX extends ModelFX {

    private int id;
    private String name;

    public CategoryModelFX() {
        super(TypeFX.Category);
    }

    public CategoryModelFX(String name) {
        super(TypeFX.Category);
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return getName();
    }

}
