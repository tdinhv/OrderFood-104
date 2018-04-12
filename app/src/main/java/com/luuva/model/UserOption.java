package com.luuva.model;

import java.io.Serializable;

public class UserOption implements Serializable {
    private String name;
    private int picture;

    public UserOption(String name, int picture) {
        this.name = name;
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public int getPicture() {
        return picture;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }
}
