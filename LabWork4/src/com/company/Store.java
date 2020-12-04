package com.company;

import java.util.ArrayList;
import java.util.Scanner;

//Магазин
public class Store {
    private ArrayList<BakeryProductClass> bakery = null;   //хлебо-булочные изделия
    private ArrayList<MilkProductClass> milk = null;        //молочные продуктов

    public Store(){
        bakery = new ArrayList<>();
        milk   = new ArrayList <>();
    }

    public void Start(){
        Scanner sc = new Scanner(System.in);
        int number = 0;
        int productNum = 0;

        do{
            System.out.print("\n\nВыберете пункт:\n");
            System.out.print("1. Вывод всех продукты.\n");
            System.out.print("2. Вывод мучных продуктов.\n");
            System.out.print("3. Вывод молочных продуктов.\n");
            System.out.print("4. Добавить хлебобулочный продукт.\n");
            System.out.print("5. Добавить молочный продукт.\n");
            System.out.print("6. Найти молочный продукт.\n");
            System.out.print("7. Найти хлебобулочный продукт.\n");
            System.out.print("8. Удалить молочный продукт.\n");
            System.out.print("9. Удалить хлебобулочный продукт.\n");
            System.out.print("10. Выход из программы.\n");

            number = Main.userInput("Введите число: ");
            while((number < 1) || (number > 10)){
                number = Main.userInput("Введите число из предложенного списка ");
            }

            switch(number){
                case 1:{
                    this.outputBakeryProduct();
                    this.outputMilksProduct();
                    break;
                }
                case 2:{
                    this.outputBakeryProduct();
                    break;
                }
                case 3:{
                    this.outputMilksProduct();
                    break;
                }
                case 4:{
                    this.addBakeryProduct();
                    break;
                }
                case 5:{
                    this.addMilkProduct();
                    break;
                }
                case 6:{
                    productNum = Main.userInput("Введите номер молочного продукта в списке: ");
                    this.findMilkProduct(productNum);
                    break;
                }
                case 7:{
                    productNum = Main.userInput("Введите номер хлебобулочного продукта в списке: ");
                    this.findBakeryProduct(productNum);
                    break;
                }
                case 8:{
                    productNum = Main.userInput("Введите номер молочного продукта в списке: ");
                    this.removeMilkProduct(productNum);
                    break;
                }
                case 9:{
                    productNum = Main.userInput("Введите номер хлебобулочного продукта в списке: ");
                    this.removeBakeryProduct(productNum);
                    break;
                }
            }

        }while(number != 10);
    }


    public void findMilkProduct(int productNum) {
        if(milk.size() == 0){
            System.out.print("Молочных продуктов нет\n\n");
            return;
        }
        System.out.print("\n");
            if (milk.size()>=productNum) {
                System.out.println(productNum + ")" + " Название продукта: " + milk.get(productNum).getNameProduct());
                System.out.println("Состав продукта: " + milk.get(productNum).getCompositionProduct());
                System.out.println("Форма продукта: " + milk.get(productNum).getStorageFormProduct());
                System.out.println("Цена продукта($): " + milk.get(productNum).getPrice());
                System.out.print("\n\n");
            }
            else {System.out.print("Молочного продукта под номером  "+(productNum)+"  не существует!"); Start();}

    }
    public void findBakeryProduct(int productNum) {
        if(bakery.size() == 0){
            System.out.print("Хлебо-булочных издений нет\n\n");
            return;
        }
        System.out.print("\n");
        if (bakery.size()>=productNum) {
            System.out.println(productNum + ")" + " Название продукта: " + bakery.get(productNum).getNameProduct());
            System.out.println("Состав продукта: " + bakery.get(productNum).getCompositionProduct());
            System.out.println("Форма продукта: " + bakery.get(productNum).getFormProduct());
            System.out.println("Цена продукта($): " + bakery.get(productNum).getPrice());
            System.out.print("\n\n");
        }
        else {System.out.print("Хлебобулочного изделия под номером "+(productNum)+" не существует!");Start();}

    }
    public void removeMilkProduct(int productNum) {
        if(milk.size() == 0){
            System.out.print("Молочных продуктов нет\n\n");
            return;
        }
        if (milk.size()>=productNum){
            System.out.println("Запись под номером "+productNum+"'"+milk.get(productNum).getNameProduct()+"'"+" успешно удалена");
            milk.remove(productNum);
        }
        else {System.out.print("Молочного продукта под номером  "+(productNum)+"  не существует!");Start();}
    }

    public void removeBakeryProduct(int productNum) {
        if(bakery.size() == 0){
            System.out.print("Хлебобулочных издений нет\n\n");
            return;
        }
        if (bakery.size()>=productNum){
            System.out.println("Запись под номером "+productNum+"'"+bakery.get(productNum).getNameProduct()+"'"+" успешно удалена");
            bakery.remove(productNum);
        }
        else {System.out.print("Хлебобулочного изделия под номером  "+(productNum)+"  не существует!");Start();}
    }



    public void outputBakeryProduct(){
        if(bakery.size() == 0){
            System.out.print("Хлебо-булочных издений нет\n\n");
            return;
        }

        for(int i = 0; i < bakery.size(); i++){
            System.out.println(bakery.get(i).productCategory());
            System.out.println(i+ ")"+" Название продукта: " + bakery.get(i).getNameProduct());
            System.out.println("Состав продукта: " + bakery.get(i).getCompositionProduct());
            System.out.println("Форма продукта: " + bakery.get(i).getFormProduct());
            System.out.println("Цена продукта($): " + bakery.get(i).getPrice());
            System.out.print("\n\n");
        }

        System.out.println("\nОбщее количество хлебо-булочных изделий: " + bakery.size() + "\n\n");
    }

    public void outputMilksProduct(){
        if(milk.size() == 0){
            System.out.print("Молочных продуктов нет\n\n");
            return;
        }

        for(int i = 0; i < milk.size(); i++){
            System.out.println(milk.get(i).productCategory());
            System.out.println(i+ ")"+" Название продукта: " + milk.get(i).getNameProduct());
            System.out.println("Состав продукта: " + milk.get(i).getCompositionProduct());
            System.out.println("Форма для хранения продукта: " + milk.get(i).getStorageFormProduct());
            System.out.println("Цена продукта($): " + milk.get(i).getPrice());
            System.out.print("\n\n");
        }

        System.out.println("\nОбщее количество молочных продуктов: " + milk.size() + "\n\n");
    }

    public void addBakeryProduct(){
        System.out.print("\n\nДобавление мучного изделия... \n\n");
        BakeryProductClass product = new BakeryProductClass();

        Scanner sc = new Scanner(System.in);
        System.out.print("Введите название продукта: ");
        product.setNameProduct(sc.nextLine());

        System.out.print("Введите состав продукта: ");
        product.setCompositionProduct(sc.nextLine());

        System.out.print("Введите форму продукта: ");
        product.setFormProduct(sc.nextLine());

        System.out.print("Введите цену продукта($): ");
        product.setPrice(sc.nextDouble());

        bakery.add(product);
        System.out.print("\nПродукт добавлен в магазин.");
    }

    public void addMilkProduct(){
        System.out.print("\n\nДобавление молочного продукта... \n\n");

        MilkProductClass product = new MilkProductClass();

        Scanner sc = new Scanner(System.in);
        System.out.print("Введите название продукта: ");
        product.setNameProduct(sc.nextLine());

        System.out.print("Введите состав продукта: ");
        product.setCompositionProduct(sc.nextLine());

        System.out.print("Введите форму для хранения продукта: ");
        product.setStorageFormProduct(sc.nextLine());

        System.out.print("Введите цену продукта($): ");
        product.setPrice(sc.nextDouble());

        milk.add(product);
        System.out.print("\nПродукт добавлен в магазин.");
    }
}
