package model;

public class MilkProductClass extends Product{

    private String storageForm;

    public MilkProductClass(){
        super();
        storageForm = "";
    }


    public void setExtensionDate(String f){
        this.storageForm = f;
    }

    public String getExtensionDate(){
        return this.storageForm;
    }

}
