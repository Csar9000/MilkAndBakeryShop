package db;

import View.InterFace;
import model.BakeryProductClass;
import model.MilkProductClass;

import javax.persistence.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "nomenclature")
public class Nomenclature {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int idProductType;
    @Column
    private String NameProduct;
    @Column
    private String CompositionProduct;
    @Column
    private String ExtensionData;
    @Column
    private double Price;

    @OneToMany(mappedBy = "nomenclature",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WareHouse> wareHouse;


    public Nomenclature(BakeryProductClass bakery){
        this.NameProduct = bakery.getNameProduct();
        this.CompositionProduct = bakery.getCompositionProduct();
        this.ExtensionData =bakery.getExtensionDate();
        this. Price = bakery.getPrice();
        this.wareHouse = new ArrayList<>();
    }

    public Nomenclature(MilkProductClass milk){
        this.NameProduct = milk.getNameProduct();
        this.CompositionProduct = milk.getCompositionProduct();
        this.ExtensionData =   milk.getExtensionDate();
        this.Price = milk.getPrice();
        this.wareHouse = new ArrayList<>();
    }

    public Nomenclature(){

    }


    public String getExtensionData() {
        return ExtensionData;
    }

    public void setExtensionData(String extensionData) {
        ExtensionData = extensionData;
    }

    public String getCompositionProduct() {
        return CompositionProduct;
    }

    public void setCompositionProduct(String compositionProduct) {
        CompositionProduct = compositionProduct;
    }

    public String getNameProduct() {
        return NameProduct;
    }

    public void setNameProduct(String nameProduct) {
        NameProduct = nameProduct;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public List<WareHouse> getWareHouse() {
        return wareHouse;
    }

    public void setWareHouse(List<WareHouse> wareHouse) {
        this.wareHouse = wareHouse;
    }

    public int getIdProductType(){
        return this.idProductType;
    }
}
