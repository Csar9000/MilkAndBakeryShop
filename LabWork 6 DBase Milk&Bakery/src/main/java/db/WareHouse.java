package db;

import View.InterFace;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.*;

@Entity
@Table(name = "warehouse")
public class WareHouse {
    @Id
    private double idAct;
    @Column
    private String ProductType;
    @Column
    private String Date;
    @Column
    private int number;
    @Column
    private int soldOut;
    @Column
    private int remain;
    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.ALL})
    @JoinColumn(name = "ProductType_id")
    private Nomenclature nomenclature;

    public WareHouse (){

    }

    public WareHouse(double IdAct, String Date, int number, int soldOut, Nomenclature nomenclature) {
        this.idAct = IdAct;
        this.Date = Date;
        this.number = number;
        this.soldOut = soldOut;
        this.remain = number-soldOut;
        if(InterFace.btnAddBakHasBeenClicked) {
            this.ProductType = "Хлебобулочное изделие";
        }else if(InterFace.btnAddMilkHasBeenClicked){
            this.ProductType = "Молочный продукт";
        }
        this.nomenclature = nomenclature;

    }


    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSoldOut() {
        return soldOut;
    }

    public void setSoldOut(int soldOut) {
        this.soldOut = soldOut;
    }


    public Nomenclature getNomenclature() {
        return nomenclature;
    }

    public void setNomenclature(Nomenclature nomenclature) {
        this.nomenclature = nomenclature;
    }

    public double getIdAct() {
        double scale = Math.pow(10, 3);
        idAct = Math.ceil(idAct * scale) / scale;
        return idAct;
    }

    public void setIdAct(double idAct) {
        this.idAct = idAct;
    }

    public String getProductType() {
        return ProductType;
    }

    public void setProductType(String productType) {
        ProductType = productType;
    }

    public String getIdProductType(){
        return Integer.toString(nomenclature.getIdProductType());
    }

    public int getRemain() {
        return remain;
    }

    public void setRemain(int remain) {
        this.remain = remain;
    }
}

