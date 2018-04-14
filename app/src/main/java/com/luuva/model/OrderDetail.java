package com.luuva.model;

public class OrderDetail {
    private int Id;
    private int ProductId;
    private int Quantity;
    private int Price;
    private int UserId;
    private String ProductName;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public OrderDetail(int id, int productId, int quantity, int price, int userId, String productName) {
        Id = id;
        ProductId = productId;
        Quantity = quantity;
        Price = price;
        UserId = userId;
        ProductName = productName;
    }
}
