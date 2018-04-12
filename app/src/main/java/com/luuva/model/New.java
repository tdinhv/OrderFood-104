package com.luuva.model;

/**
 * Created by My PC on 3/10/2018.
 */

public class New {
    private String newName;
    private String preview;
    private int color;


    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }
    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
    public New(String newName, String preview, int color) {
        this.newName = newName;
        this.preview = preview;
        this.color = color;
    }
}
