package com.luuva.model;

import java.io.Serializable;

/**
 * Created by NhuongPH on 4/5/2018.
 */

public class Payment implements Serializable {
    private String pay;
    private int icon;

    public Payment(String pay, int icon) {
        this.pay = pay;
        this.icon = icon;
    }

    public String getPay() {
        return pay;
    }

    public int getIcon() {
        return icon;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
