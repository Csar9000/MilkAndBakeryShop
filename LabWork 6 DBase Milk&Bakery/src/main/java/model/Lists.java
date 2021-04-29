package model;

import db.Nomenclature;
import db.WareHouse;

import java.util.ArrayList;

public class Lists {
    public  ArrayList<WareHouse> warehouseBakeryList;   //хлебо-булочные изделия
    public  ArrayList<WareHouse> warehouseMilkList;     // молочные изделия
    public ArrayList<WareHouse> generalList;



    public Lists(){
        warehouseBakeryList = new ArrayList<>();
        warehouseMilkList = new ArrayList<>();
        generalList = new ArrayList<>();
    }
    //-----------------------------------------------//
    public void addBakery(WareHouse wb){
        warehouseBakeryList.add(wb);
    }

    public void clearBak(){
        this.warehouseBakeryList = new ArrayList<WareHouse>();
    }

    public void clearMilk(){
        this.warehouseMilkList = new ArrayList<WareHouse>();
    }

    public void clearGeneralTable(){
        this.generalList = new ArrayList<WareHouse>();
    }

    public void addMilk(WareHouse wm){
        warehouseMilkList.add(wm);
    }

    public void addWarehouse(WareHouse w){
        generalList.add(w);
    }
    //-----------------------------------------------//
    public Double getIdAct(WareHouse w) {
        return w.getIdAct();
    }
    public String getProductType(WareHouse w){
        return w.getProductType();
    }

    public String getDate(WareHouse w){
        return w.getDate();
    }

    public int Number(WareHouse w){
        return w.getNumber();
    }
    public int getSoldOut(WareHouse w){
        return w.getSoldOut();
    }

}
