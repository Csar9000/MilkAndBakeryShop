package com.company;

public class BakeryProductClass extends Product{
    private String form;        //форма мучного продукта

    public BakeryProductClass(){
        super(); //вызов базового конструктора
        form = "";
    }
    public void setFormProduct(String f){
        this.form = f;
    }

    public String getFormProduct(){
        return this.form;
    }

    @Override
    public String productCategory() {
        return "\nХлебо-булочные изделия: \n";
    }
}
