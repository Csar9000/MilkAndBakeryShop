package com.company.test;

import java.util.ArrayList;

public class Lists {
    public  ArrayList<BakeryProductClass> bakeryList;   //хлебо-булочные изделия
    public  ArrayList<MilkProductClass> milkList;     // молочные изделия

    public Lists(){
        bakeryList = new ArrayList<>();
        milkList = new ArrayList<>();
    }

    public void addBakery(BakeryProductClass b){
        bakeryList.add(b);
    }

    public void addMilk(MilkProductClass m){
        milkList.add(m);
    }

    public String getNameMilk(int rowIndex) {
        return this.milkList.get(rowIndex).getNameProduct();
    }
    public String getCompositionMilk(int rowIndex) {
        return this.milkList.get(rowIndex).getCompositionProduct();
    }
    public String getStorageFormMilk(int rowIndex){
        return this.milkList.get(rowIndex).getStorageFormProduct();
    }
    public String getPriceMilk(int rowIndex){
        return Double.toString(this.milkList.get(rowIndex).getPrice());
    }
    public String getNameBakery(int rowIndex){
        return this.bakeryList.get(rowIndex).getNameProduct();
    }
    public String getCompositionBakery(int rowIndex){
        return this.bakeryList.get(rowIndex).getCompositionProduct();
    }
    public String getFormBakery(int rowIndex){
        return this.bakeryList.get(rowIndex).getFormProduct();
    }
    public String getPriceBakery(int rowIndex){
        return Double.toString(this.bakeryList.get(rowIndex).getPrice());
    }
}
