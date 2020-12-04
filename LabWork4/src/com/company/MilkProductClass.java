package com.company;

public class MilkProductClass extends Product{

    private String storageForm;     //форма для хранения молочных продуктов

    public MilkProductClass(){
        super();
        storageForm = "";
    }
    public void setStorageFormProduct(String f){
        this.storageForm = f;
    }

    public String getStorageFormProduct(){
        return this.storageForm;
    }

    @Override
    public String productCategory() {
        return "\nМолочные продукты: \n";
    }
}
