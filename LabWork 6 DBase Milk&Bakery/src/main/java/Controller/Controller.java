package Controller;


import View.InterFace;
import View.NomenclatureView;
import db.HibernateUtil;
import db.Nomenclature;
import db.WareHouse;
import model.BakeryProductClass;
import model.Lists;
import model.MilkProductClass;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class Controller {
public static double temp;


public static Nomenclature nomen;
   public static Lists l = new Lists();

   public static void addBakery(BakeryProductClass b, String date,int number,int soldOut){
       temp+=0.001;

       double scale = Math.pow(10, 3);
       temp = Math.ceil(temp * scale) / scale;

       Session session = HibernateUtil.getSession();
       Transaction transaction = session.beginTransaction();

       Nomenclature nomen = new Nomenclature(b);

       WareHouse wareHouse = new WareHouse(temp,date,number,soldOut, nomen);

       nomen.getWareHouse().add(wareHouse);

       session.save(nomen);

       transaction.commit();
       session.close();
   }

    public static void addMilk(MilkProductClass m, String date, int number, int soldOut){
        temp+=0.001;

        double scale = Math.pow(10, 3);
        temp = Math.ceil(temp * scale) / scale;

        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        Nomenclature nomen = new Nomenclature(m);

        WareHouse wareHouse = new WareHouse(temp,date,number,soldOut, nomen);

        nomen.getWareHouse().add(wareHouse);

        session.save(nomen);

        transaction.commit();
        session.close();
    }


    public static void updateWareHouse(Double idAct, int SoldOut) {
        Session session = HibernateUtil.getSession();
        Session session2 = HibernateUtil.getSession();

        l.clearMilk();
        l.clearBak();
        l.clearGeneralTable();

        Transaction tx = null;
        int y=0;
        int generalSoldOut =0;
        int numberFromWareHouse=0;
        int remain=0;
        try {
            List<WareHouse> wareHouses;

            Query qu = session2.createQuery("from WareHouse where IdAct =:param");
            qu.setParameter("param", idAct);
            wareHouses = qu.getResultList();

            for (WareHouse h : wareHouses) {
                    numberFromWareHouse = h.getNumber();
                    y = h.getSoldOut();
                    remain = h.getRemain();
            }

            JOptionPane.showMessageDialog(null,numberFromWareHouse);

            if(wareHouses==null){
                JOptionPane.showMessageDialog(null,"Error");
            }



            session2.close();

            if (remain>0) {

                generalSoldOut = y + SoldOut;


                tx = session.beginTransaction();
                WareHouse wareHouse =
                        (WareHouse) session.get(WareHouse.class, idAct);
                wareHouse.setSoldOut(y + SoldOut);
                WareHouse wareHouse1 =
                        (WareHouse) session.get(WareHouse.class, idAct);
                wareHouse1.setRemain(numberFromWareHouse - generalSoldOut);
                session.update(wareHouse);
                session.update(wareHouse1);
                tx.commit();

            }else {JOptionPane.showMessageDialog(null,"Товара не осталось!");}

            } catch(HibernateException e){
                if (tx != null) tx.rollback();
                e.printStackTrace();
            } finally{
                session.close();
            }



    }


   public static void findNomenclature(int value){
       List <Nomenclature>nomens;
        Session session = HibernateUtil.getSession();
        Query qu = session.createQuery("from Nomenclature where idProductType =:parametr");
        qu.setParameter("parametr",value);
        nomens = qu.getResultList();
        for (Nomenclature nomen1: nomens) {
            nomen = nomen1;
        }
        if(nomen == null){
            throw new NullPointerException();
        }
        session.close();
   }



   public static void getWarehouseFromDB(){
       List <WareHouse> wareHouses;

       Session session = HibernateUtil.getSession();
       Query qu0 = session.createQuery("from WareHouse");
       wareHouses = qu0.getResultList();
       l.generalList.addAll(wareHouses);
       session.close();


       Session session2 = HibernateUtil.getSession();
       Query qu1 = session2.createQuery("from WareHouse where ProductType =:parametr");
       qu1.setParameter("parametr","Хлебобулочное изделие");
       wareHouses = qu1.getResultList();
       l.warehouseBakeryList.addAll(wareHouses);
       session2.close();


       Session session3 = HibernateUtil.getSession();
       Query qu2 = session3.createQuery("from WareHouse where ProductType =:parametr");
       qu2.setParameter("parametr","Молочный продукт");
       wareHouses = qu2.getResultList();
       l.warehouseMilkList.addAll(wareHouses);
       session3.close();


       session.close();
   }

    public static class bakeryButtonAdd implements ActionListener {
        public void actionPerformed(ActionEvent e) throws NullPointerException {


            BakeryProductClass bakery = new BakeryProductClass();

            if     (InterFace.bakeryName.getText().trim().length() > 0 &&
                    InterFace.bakeryComp.getText().trim().length() > 0 &&
                    InterFace.extensionDataBakery.getText().trim().length() > 0 &&
                    InterFace.bakeryPrice.getText().trim().length() > 0 &&
                    InterFace.DateBakery.getText().trim().length()>0 &&
                    InterFace.NumberBakery.getText().trim().length()>0){

                bakery.setNameProduct(InterFace.bakeryName.getText());
                bakery.setCompositionProduct(InterFace.bakeryComp.getText());
                bakery.setExtensionDate(InterFace.extensionDataBakery.getText());
                bakery.setPrice(Double.parseDouble(InterFace.bakeryPrice.getText()));

                addBakery(bakery,InterFace.DateBakery.getText(),Integer.parseInt(InterFace.NumberBakery.getText()),0);

                JOptionPane.showMessageDialog(null,"Продукт добавлен");

                InterFace.table = new JTable(new MyTableBakery());

                if(((BorderLayout) InterFace.card2.getLayout()).getLayoutComponent(BorderLayout.CENTER)!=null) {
                    InterFace.card2.remove(((BorderLayout) InterFace.card2.getLayout()).getLayoutComponent(BorderLayout.CENTER));
                    InterFace.card2.add(new JScrollPane(InterFace.table), BorderLayout.CENTER);


                    InterFace.card2bakeryButton.setBorder(BorderFactory.createLineBorder(Color.green));
                    InterFace.card2milkButton.setBorder(BorderFactory.createLineBorder(null));

                    InterFace.card2.repaint();
                    InterFace.card2.validate();

                }

            }else {JOptionPane.showMessageDialog(null,"Заполните все значения продукта");}

        }
    }

    public static class milkButtonAdd implements ActionListener {
        public void actionPerformed(ActionEvent e)  {

            MilkProductClass milk = new MilkProductClass();
            if     (InterFace.milkName.getText().trim().length() > 0 &&
                    InterFace.milkComp.getText().trim().length() > 0 &&
                    InterFace.extensionDataMilk.getText().trim().length() > 0 &&
                    InterFace.milkPrice.getText().trim().length() > 0 &&
                    InterFace.DateMilk.getText().trim().length()>0 &&
                    InterFace.NumberMilk.getText().trim().length()>0){

                milk.setNameProduct(InterFace.milkName.getText());
                milk.setCompositionProduct(InterFace.milkComp.getText());
                milk.setExtensionDate(InterFace.extensionDataMilk.getText());
                milk.setPrice(Double.parseDouble(InterFace.milkPrice.getText()));

                addMilk(milk,InterFace.DateMilk.getText(),Integer.parseInt(InterFace.NumberMilk.getText()),0);


                JOptionPane.showMessageDialog(null,"Продукт добавлен");

                InterFace.table = new JTable(new MyTableMilk());




                if(((BorderLayout) InterFace.card2.getLayout()).getLayoutComponent(BorderLayout.CENTER)!=null){
                    InterFace.card2.remove(((BorderLayout) InterFace.card2.getLayout()).getLayoutComponent(BorderLayout.CENTER));

                    InterFace.card2.add(new JScrollPane(InterFace.table), BorderLayout.CENTER);

                    InterFace.card2milkButton.setBorder(BorderFactory.createLineBorder(Color.green));
                    InterFace.card2bakeryButton.setBorder(BorderFactory.createLineBorder(null));

                    InterFace.card2.repaint();
                    InterFace.card2.validate();

                }

            }else {JOptionPane.showMessageDialog(null,"Заполните все значения продукта");}
        }
    }

    public static class deleteAct implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {


            if(InterFace.choice==0) {
                if (InterFace.filter.getText().trim().length() > 0) {


                    double scale = Math.pow(10, 3);
                    double result = Math.ceil(Double.parseDouble(InterFace.filter.getText()) * scale) / scale;



                    Session session = HibernateUtil.getSession();
                    session.beginTransaction();


                    List<WareHouse> wareHouses;
                    Query qu = session.createQuery("from WareHouse where IdAct =:param");
                    qu.setParameter("param", result);
                    wareHouses = qu.getResultList();

                    for (WareHouse h : wareHouses) {
                        session.delete(h);
                    }

                    session.flush();

                    session.getTransaction().commit();
                    session.close();

                    l.clearGeneralTable();
                    l.clearBak();
                    l.clearMilk();

                    getWarehouseFromDB();


                    JTable table = new JTable(new MyDefaultTableModel());
                    RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
                            new Controller.MyDefaultTableModel());
                    table.setRowSorter(sorter);
                    JScrollPane pane = new JScrollPane(table);

                    //InterFace.table = new JTable(new MyTableFromDBWareHouse());

                    InterFace.card2.remove(((BorderLayout) InterFace.card2.getLayout()).getLayoutComponent(BorderLayout.CENTER));

                    InterFace.card2.add(pane, BorderLayout.CENTER);


                    InterFace.card2.repaint();
                    InterFace.card2.validate();

                }  else {JOptionPane.showMessageDialog(null,"Пустая строка поиска");}

            }
            if(InterFace.choice==1) {
                if (InterFace.filter.getText().trim().length() > 0) {

                    double scale = Math.pow(10, 3);
                    double result = Math.ceil(Double.parseDouble(InterFace.filter.getText()) * scale) / scale;


                    Session session = HibernateUtil.getSession();
                    Query q = session.createQuery("delete from WareHouse  where idAct =:param");
                    q.setParameter("param", result);

                    l.clearGeneralTable();
                    l.clearBak();
                    l.clearMilk();

                    getWarehouseFromDB();

                    //InterFace.table = new JTable(new MyTableBakery());

                    JTable table = new JTable(new MyTableBakery());
                    RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
                            new Controller.MyTableBakery());
                    table.setRowSorter(sorter);
                    JScrollPane pane = new JScrollPane(table);

                    InterFace.card2.remove(((BorderLayout) InterFace.card2.getLayout()).getLayoutComponent(BorderLayout.CENTER));

                    InterFace.card2.add(pane, BorderLayout.CENTER);


                    InterFace.card2.repaint();
                    InterFace.card2.validate();

                }  else {JOptionPane.showMessageDialog(null,"Пустая строка поиска");}

            }
            if (InterFace.choice == 2) {
                if (InterFace.filter.getText().trim().length() > 0) {

                    double scale = Math.pow(10, 3);
                    double result = Math.ceil(Double.parseDouble(InterFace.filter.getText()) * scale) / scale;


                    Session session = HibernateUtil.getSession();
                    Query q = session.createQuery("delete from WareHouse  where idAct =:param");
                    q.setParameter("param", result);

                    l.clearGeneralTable();
                    l.clearBak();
                    l.clearMilk();

                    getWarehouseFromDB();

                    //InterFace.table = new JTable(new MyTableMilk());

                    JTable table = new JTable(new MyTableMilk());
                    RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
                            new Controller.MyTableMilk());
                    table.setRowSorter(sorter);
                    JScrollPane pane = new JScrollPane(table);

                    InterFace.card2.remove(((BorderLayout) InterFace.card2.getLayout()).getLayoutComponent(BorderLayout.CENTER));

                    InterFace.card2.add(pane, BorderLayout.CENTER);


                    InterFace.card2.repaint();
                    InterFace.card2.validate();

                }  else {JOptionPane.showMessageDialog(null,"Пустая строка поиска");}
            }


        }
    }

    public static class sale implements ActionListener {

        public void actionPerformed(ActionEvent e)  {

            if(InterFace.choice==0) {
                if (InterFace.filter.getText().trim().length() > 0) {


                    double scale = Math.pow(10, 3);
                    double result = Math.ceil(Double.parseDouble(InterFace.filter.getText()) * scale) / scale;

                    if(InterFace.countToSale.getText().trim().length()>0) {
                        updateWareHouse(result, Integer.parseInt(InterFace.countToSale.getText()));


                        Controller.getWarehouseFromDB();

                        InterFace.table = new JTable(new MyDefaultTableModel());

                        InterFace.card2.remove(((BorderLayout) InterFace.card2.getLayout()).getLayoutComponent(BorderLayout.CENTER));

                        InterFace.card2.add(new JScrollPane(InterFace.table), BorderLayout.CENTER);
                    }
                    else {JOptionPane.showMessageDialog(null,"Пустая строка количества подукта");}


                    InterFace.card2.repaint();
                    InterFace.card2.validate();

                }  else {JOptionPane.showMessageDialog(null,"Пустая строка поиска");}

            }
                if(InterFace.choice==1) {
                    if (InterFace.filter.getText().trim().length() > 0) {

                        double scale = Math.pow(10, 3);
                        double result = Math.ceil(Double.parseDouble(InterFace.filter.getText()) * scale) / scale;

                        updateWareHouse(result,Integer.parseInt(InterFace.countToSale.getText()));

                        Controller.getWarehouseFromDB();

                        InterFace.table = new JTable(new MyTableBakery());

                        InterFace.card2.remove(((BorderLayout) InterFace.card2.getLayout()).getLayoutComponent(BorderLayout.CENTER));

                        InterFace.card2.add(new JScrollPane(InterFace.table), BorderLayout.CENTER);


                        InterFace.card2.repaint();
                        InterFace.card2.validate();

                    }  else {JOptionPane.showMessageDialog(null,"Пустая строка поиска");}

                }
                if (InterFace.choice == 2) {
                    if (InterFace.filter.getText().trim().length() > 0) {

                        double scale = Math.pow(10, 3);
                        double result = Math.ceil(Double.parseDouble(InterFace.filter.getText()) * scale) / scale;

                        updateWareHouse(result,Integer.parseInt(InterFace.countToSale.getText()));

                        Controller.getWarehouseFromDB();

                        InterFace.table = new JTable(new MyTableMilk());

                        InterFace.card2.remove(((BorderLayout) InterFace.card2.getLayout()).getLayoutComponent(BorderLayout.CENTER));

                        InterFace.card2.add(new JScrollPane(InterFace.table), BorderLayout.CENTER);


                        InterFace.card2.repaint();
                        InterFace.card2.validate();

                    }  else {JOptionPane.showMessageDialog(null,"Пустая строка поиска");}
                }

        }
    }

//    public static class MyTableFromDBWareHouse extends AbstractTableModel{
//
//        @Override
//        public int getRowCount() {
//            return l.generalList.size();
//        }
//
//        @Override
//        public int getColumnCount() {
//            return 7;
//        }
//
//        @Override
//        public Object getValueAt(int rowIndex, int columnIndex) {
//            return switch (columnIndex) {
//                case 0 -> Double.toString(l.generalList.get(rowIndex).getIdAct());
//                case 1 -> l.generalList.get(rowIndex).getProductType();
//                case 2 -> l.generalList.get(rowIndex).getDate();
//                case 3 -> l.generalList.get(rowIndex).getNumber();
//                case 4 -> l.generalList.get(rowIndex).getSoldOut();
//                case 5 -> l.generalList.get(rowIndex).getRemain();
//                case 6 -> l.generalList.get(rowIndex).getIdProductType();
//                default -> "Не определена";
//            };
//        }
//
//        @Override
//        public String getColumnName(int cell) {
//            return switch (cell) {
//                case 0 -> "ID акта";
//                case 1 -> "Тип продукта";
//                case 2 -> "Дата привоза";
//                case 3 -> "Количество";
//                case 4 -> "Продано";
//                case 5 -> "Остаток";
//                case 6 -> "ID вида продукта";
//                default -> "Other Column";
//            };
//        }
//
//    }

    public static class MyTableFromDBWNomenclature extends AbstractTableModel{
        @Override
        public int getRowCount() {
            return 1;
        }

        @Override
        public int getColumnCount() {
            return 5;
        }


        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
                return switch (columnIndex) {
                    case 0 -> nomen.getIdProductType();
                    case 1 -> nomen.getNameProduct();
                    case 2 -> "Состав: " + nomen.getCompositionProduct();
                    case 3 -> nomen.getExtensionData() + " дней";
                    case 4 -> nomen.getPrice() + " руб.";
                    default -> throw new IllegalStateException("Unexpected value: " + columnIndex);

                };
        }

        @Override
        public String getColumnName(int cell) {
            return switch (cell) {
                case 0 -> "ID продукта";
                case 1 -> "Название";
                case 2 -> "Состав";
                case 3 -> "Срок годности";
                case 4 -> "Цена за шт.";
                default -> "Other Column";
            };
        }
    }


    public static class MyTableBakery extends DefaultTableModel {

        @Override
        public int getRowCount() {
            return l.warehouseBakeryList.size();
        }

        @Override
        public int getColumnCount() {
            return 7;
        }


        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return switch (columnIndex) {
                case 0 -> Double.toString(l.warehouseBakeryList.get(rowIndex).getIdAct());
                case 1 -> l.warehouseBakeryList.get(rowIndex).getProductType();
                case 2 -> l.warehouseBakeryList.get(rowIndex).getDate();
                case 3 -> l.warehouseBakeryList.get(rowIndex).getNumber();
                case 4 -> l.warehouseBakeryList.get(rowIndex).getSoldOut();
                case 5 -> l.warehouseBakeryList.get(rowIndex).getRemain();
                case 6 -> l.warehouseBakeryList.get(rowIndex).getIdProductType();
                default -> "Не определена";
            };
        }


        @Override
        public String getColumnName(int cell) {
            return switch (cell) {
                case 0 -> "ID акта";
                case 1 -> "Тип продукта";
                case 2 -> "Дата привоза";
                case 3 -> "Количество";
                case 4 -> "Продано";
                case 5 -> "Остаток";
                case 6 -> "ID вида продукта";
                default -> "Other Column";
            };
        }
    }

    public static class MyTableMilk extends DefaultTableModel {

        public MyTableMilk() {
        }

        @Override
        public int getColumnCount() {
            return 7;
        }

        public int getRowCount(){
            return l.warehouseMilkList.size();
        }

        @Override
        public String getColumnName(int column) {
            return switch (column) {
                case 0 -> "ID акта";
                case 1 -> "Тип продукта";
                case 2 -> "Дата привоза";
                case 3 -> "Количество";
                case 4 -> "Продано";
                case 5 -> "Остаток";
                case 6 -> "ID вида продукта";
                default -> "Other Column";
            };
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return true;
        }

        @Override
        public Object getValueAt(int rowIndex, int column) {
            return switch (column) {
                case 0 -> Double.toString(l.warehouseMilkList.get(rowIndex).getIdAct());
                case 1 -> l.warehouseMilkList.get(rowIndex).getProductType();
                case 2 -> l.warehouseMilkList.get(rowIndex).getDate();
                case 3 -> l.warehouseMilkList.get(rowIndex).getNumber();
                case 4 -> l.warehouseMilkList.get(rowIndex).getSoldOut();
                case 5 -> l.warehouseMilkList.get(rowIndex).getRemain();
                case 6 -> l.warehouseMilkList.get(rowIndex).getIdProductType();
                default -> "Не определена";
            };
        }
    }


    public static class MyDefaultTableModel extends DefaultTableModel {


        public MyDefaultTableModel() {
        }



        @Override
        public int getColumnCount() {
            return 7;
        }

        public int getRowCount(){
            return l.generalList.size();
        }

        @Override
        public String getColumnName(int column) {
            return switch (column) {
                case 0 -> "ID акта";
                case 1 -> "Тип продукта";
                case 2 -> "Дата привоза";
                case 3 -> "Количество";
                case 4 -> "Продано";
                case 5 -> "Остаток";
                case 6 -> "ID вида продукта";
                default -> "Other Column";
            };
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return true;
        }

        @Override
        public Object getValueAt(int rowIndex, int column) {
            return switch (column) {
                case 0 -> Double.toString(l.generalList.get(rowIndex).getIdAct());
                case 1 -> l.generalList.get(rowIndex).getProductType();
                case 2 -> l.generalList.get(rowIndex).getDate();
                case 3 -> l.generalList.get(rowIndex).getNumber();
                case 4 -> l.generalList.get(rowIndex).getSoldOut();
                case 5 -> l.generalList.get(rowIndex).getRemain();
                case 6 -> l.generalList.get(rowIndex).getIdProductType();
                default -> "Не определена";
            };
        }
    }

}
