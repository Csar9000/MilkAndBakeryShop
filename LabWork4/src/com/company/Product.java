package com.company;

public abstract class Product {
    protected double price;               //цена продукта
    protected String nameProduct;         //название продукта
    protected String compositionProduct;  //состав продукта

    public Product(){
        price = 0;
        nameProduct = "";
        compositionProduct = "";
    }
    public boolean setPrice(double val) {
        if(val <= 0)
            return false;
        this.price = val;

        return false;
    }

    public double getPrice() {
        return this.price;
    }

    public void setNameProduct(String n) {
        this.nameProduct = n;
    }

    public String getNameProduct() {
        return this.nameProduct;
    }

    public void setCompositionProduct(String c) {
        this.compositionProduct = c;
    }

    public String getCompositionProduct() {
        return compositionProduct;
    }
    public abstract String productCategory();{}
}
