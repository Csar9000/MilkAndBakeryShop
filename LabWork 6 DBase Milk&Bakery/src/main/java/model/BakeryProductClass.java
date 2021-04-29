package model;

public class BakeryProductClass extends Product{
    private String form;

    public BakeryProductClass(){
        super();
        form = "";
    }
    public void setExtensionDate(String f){
        this.form = f;
    }

    public String getExtensionDate(){
        return this.form;
    }

}
