package com.company.test;


import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Controller {

   public static Lists l = new Lists();

    public static class bakeryButtonAdd implements ActionListener {
        public void actionPerformed(ActionEvent e) throws NullPointerException {

            BakeryProductClass bakery = new BakeryProductClass();

            if      (InterFace.bakeryName.getText().trim().length() > 0 &&
                    InterFace.bakeryComp.getText().trim().length() > 0 &&
                    InterFace.bakeryForm.getText().trim().length() > 0 &&
                    InterFace.bakeryPrice.getText().trim().length() > 0){

                bakery.setNameProduct(InterFace.bakeryName.getText());
                bakery.setCompositionProduct(InterFace.bakeryComp.getText());
                bakery.setFormProduct(InterFace.bakeryForm.getText());
                bakery.setPrice(Double.parseDouble(InterFace.bakeryPrice.getText()));

                l.addBakery(bakery);

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
            if      (InterFace.milkName.getText().trim().length() > 0 &&
                    InterFace.milkComp.getText().trim().length() > 0 &&
                    InterFace.storageForm.getText().trim().length() > 0 &&
                    InterFace.milkPrice.getText().trim().length() > 0){
                milk.setNameProduct(InterFace.milkName.getText());
                milk.setCompositionProduct(InterFace.milkComp.getText());
                milk.setStorageFormProduct(InterFace.storageForm.getText());
                milk.setPrice(Double.parseDouble(InterFace.milkPrice.getText()));
                l.addMilk(milk);
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

    public static class deleteRow implements ActionListener {

        public void actionPerformed(ActionEvent e)  {

            if(InterFace.choice!=0){
                if(InterFace.choice==1) {
                    if (InterFace.filter.getText().trim().length() > 0) {
                        if (Integer.parseInt(InterFace.filter.getText()) <= InterFace.table.getRowCount()) {
                            InterFace.table.setRowSelectionInterval(Integer.parseInt(InterFace.filter.getText()) - 1, Integer.parseInt(InterFace.filter.getText()) - 1);
                            l.bakeryList.remove(Integer.parseInt(InterFace.filter.getText())-1);
                        }
                        else {JOptionPane.showMessageDialog(null,"Некорректный индекс");}
                        InterFace.table = new JTable(new MyTableBakery());

                        InterFace.card2.remove(((BorderLayout) InterFace.card2.getLayout()).getLayoutComponent(BorderLayout.CENTER));

                        InterFace.card2.add(new JScrollPane(InterFace.table), BorderLayout.CENTER);
                        InterFace.card2.repaint();
                        InterFace.card2.validate();
                    }  else {JOptionPane.showMessageDialog(null,"Пустая строка поиска");}

                }
                if (InterFace.choice == 2) {
                    if (InterFace.filter.getText().trim().length() > 0) {
                        if (Integer.parseInt(InterFace.filter.getText()) <= InterFace.table.getRowCount()) {
                            InterFace.table.setRowSelectionInterval(Integer.parseInt(InterFace.filter.getText()) - 1, Integer.parseInt(InterFace.filter.getText()) - 1);
                            l.milkList.remove(Integer.parseInt(InterFace.filter.getText()) - 1);
                        }else {JOptionPane.showMessageDialog(null,"Некорректный индекс");}
                        InterFace.table = new JTable(new MyTableMilk());

                        InterFace.card2.remove(((BorderLayout) InterFace.card2.getLayout()).getLayoutComponent(BorderLayout.CENTER));

                        InterFace.card2.add(new JScrollPane(InterFace.table), BorderLayout.CENTER);
                        InterFace.card2.repaint();
                        InterFace.card2.validate();

                    } else {JOptionPane.showMessageDialog(null,"Пустая строка поиска");}
                }
            }
        }
    }

    public static class MyTableBakery extends AbstractTableModel {

        @Override
        public int getRowCount() {
            return l.bakeryList.size();
        }

        @Override
        public int getColumnCount() {
            return 5;
        }


        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return switch (columnIndex) {
                case 0 -> rowIndex+1;
                case 1 -> l.getNameBakery(rowIndex);
                case 2 -> "Состав: " + l.getCompositionBakery(rowIndex);
                case 3 -> l.getFormBakery(rowIndex);
                case 4 -> l.getPriceBakery(rowIndex) + " руб.";
                default -> "Не определена";
            };
        }

        @Override
        public String getColumnName(int cell) {
            return switch (cell) {
                case 0 -> "ID";
                case 1 -> "Навзвание";
                case 2 -> "Состав";
                case 3 -> "Форма";
                case 4 -> "Цена";
                default -> "Other Column";
            };
        }
    }
    public static class MyTableMilk extends AbstractTableModel {

        @Override
        public int getRowCount() {
            return l.milkList.size();
        }

        @Override
        public int getColumnCount() {
            return 5;
        }


        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return switch (columnIndex) {
                case 0 -> rowIndex+1;
                case 1 -> l.getNameMilk(rowIndex);
                case 2 -> "Состав: " + l.getCompositionMilk(rowIndex);
                case 3 -> l.getStorageFormMilk(rowIndex);
                case 4 -> l.getPriceMilk(rowIndex) + " руб.";
                default -> "Не определена";
            };
        }

        @Override
        public String getColumnName(int cell) {
            return switch (cell) {
                case 0 -> "ID";
                case 1 -> "Навзвание";
                case 2 -> "Состав";
                case 3 -> "Форма для хранения";
                case 4 -> "Цена";
                default -> "Other Column";
            };
        }
    }

}
