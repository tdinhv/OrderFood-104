package com.luuva.model;

public class Cart {
    private int idFood;
    private String nameFood;
    private int price;
    private String image;
    private int quantity;
    private int shopId;

    public int getIdFood() {
        return idFood;
    }

    public void setIdFood(int idFood) {
        this.idFood = idFood;
    }

    public String getNameFood() {
        return nameFood;
    }

    public void setNameFood(String nameFood) {
        this.nameFood = nameFood;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public Cart(int idFood, String nameFood, int price, String image, int quantity, int shopId) {
        this.idFood = idFood;
        this.nameFood = nameFood;
        this.price = price;
        this.image = image;
        this.quantity = quantity;
        this.shopId = shopId;
    }
}
