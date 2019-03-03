package com.akjos.myLibrary.models;

import com.akjos.myLibrary.tools.TypeFX;

public abstract class ModelFX {
    TypeFX type;

    public ModelFX(TypeFX type){
        this.type = type;
    }

    public TypeFX getType() {
        return type;
    }
}
