package com.luuva.model;

import java.io.Serializable;

/**
 * Created by luuva on 3/9/2018.
 */

public class Category implements Serializable {
    private int id;
    private String name_cat;
    private String picture;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_cat() {
        return name_cat;
    }

    public void setName_cat(String name_cat) {
        this.name_cat = name_cat;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Category(int id, String name_cat, String picture) {
        this.id = id;
        this.name_cat = name_cat;
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name_cat='" + name_cat + '\'' +
                ", picture=" + picture +
                '}';
    }
}
