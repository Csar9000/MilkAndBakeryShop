package com.company.test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;



    class InterFace implements ItemListener {


        public static JPanel card1CenterPanelBakery = new JPanel(new GridLayout(2,2));
        public static JPanel card1CenterPanelMilk = new JPanel(new GridLayout(2,2));

        public static JButton bakeryButton = new JButton("Хлеб");
        public static JButton milkButton = new JButton("Молоко");

        public static JButton card2bakeryButton = new JButton("Хлеб");
        public static JButton card2milkButton = new JButton("Молоко");

        public static JTextField bakeryName = new JTextField("");
        public static JTextField bakeryComp = new JTextField("");
        public static JTextField bakeryForm = new JTextField("");
        public static JTextField bakeryPrice = new JTextField("");

        public static JTextField milkName = new JTextField("");
        public static JTextField milkComp = new JTextField("");
        public static JTextField storageForm = new JTextField("");
        public static JTextField milkPrice = new JTextField("");

        public static JTable table;

        public static JButton search = new JButton("Найти");
        public static JButton remove = new JButton("Удалить");


        public static JPanel card2 = new JPanel(new BorderLayout());


        public static int choice = 0;

        private static JPanel cards;


        public static JTextField filter= new JTextField("");

        final static String BUTTONPANEL = "Панель продуктов";
        final static String TEXTPANEL = "Панель - таблица";

        /**
         * Метод определения интерфейса панели
         *
         * @param container - панель содержимого
         */

        public void createPanelUI(Container container) {


            milkButton.setBorder(BorderFactory.createLineBorder(Color.green));

            filter.setPreferredSize(new Dimension(50,50));
            search.setPreferredSize(new Dimension(100,20));
            remove.setPreferredSize(new Dimension(100,20));


            search.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (filter.getText().trim().length() > 0) {
                        if (Integer.parseInt(InterFace.filter.getText()) <= table.getRowCount()) {
                            table.setRowSelectionInterval(Integer.parseInt(filter.getText()) - 1, Integer.parseInt(filter.getText()) - 1);
                            table.changeSelection(Integer.parseInt(filter.getText()) - 1, Integer.parseInt(filter.getText()) - 1, true, true);
                        }
                        else {JOptionPane.showMessageDialog(null,"Некорректный индекс");}
                    }
                    else {JOptionPane.showMessageDialog(null,"Пустая строка поиска");}
                }
            });

            remove.addActionListener(new Controller.deleteRow());

            bakeryButton.setPreferredSize(new Dimension(50,50));
            milkButton.setPreferredSize(new Dimension(50,50));

            card2bakeryButton.setPreferredSize(new Dimension(50,50));
            card2milkButton.setPreferredSize(new Dimension(50,50));

            JComboBox<String> combobox = new JComboBox<String>(
                    new String[]{BUTTONPANEL, TEXTPANEL});
            combobox.setEditable(false);
            combobox.addItemListener(this);

            JPanel mainPanel = new JPanel();

            mainPanel.add(combobox);

            JPanel card1SouthPanel = new JPanel(new FlowLayout());

            JButton add = new JButton("Добавить");
            JButton clear = new JButton("Очистить поля");


            clear.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(card1CenterPanelBakery.isVisible()) {
                        bakeryName.setText("");
                        bakeryComp.setText("");
                        bakeryForm.setText("");
                        bakeryPrice.setText("");
                    }
                    if(card1CenterPanelMilk.isVisible()){
                        milkName.setText("");
                        milkComp.setText("");
                        storageForm.setText("");
                        milkPrice.setText("");
                    }
                }
            });

            Controller.bakeryButtonAdd b = new Controller.bakeryButtonAdd();
            Controller.milkButtonAdd m = new Controller.milkButtonAdd();

            // Создание "cards".
            JPanel card1 = new JPanel(new BorderLayout());

            add.addActionListener(m);

            bakeryButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    card1.add(card1CenterPanelBakery, BorderLayout.CENTER);
                    card1CenterPanelBakery.setVisible(true);
                    card1CenterPanelMilk.setVisible(false);
                    add.removeActionListener(m);
                    add.addActionListener(b);
                    bakeryButton.setBorder(BorderFactory.createLineBorder(Color.green));
                    milkButton.setBorder(BorderFactory.createLineBorder(null));

                }
            });

            milkButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    card1CenterPanelMilk.setVisible(true);
                    card1CenterPanelBakery.setVisible(false);
                    add.removeActionListener(b);
                    add.addActionListener(m);
                    milkButton.setBorder(BorderFactory.createLineBorder(Color.green));
                    bakeryButton.setBorder(BorderFactory.createLineBorder(null));

                }
            });

            //-------------------------bakeryPanelCenter-----------------//
            JPanel addBakeryName = new JPanel(new GridLayout(2,1));
            addBakeryName.add(new JLabel("Название"));
            addBakeryName.add(bakeryName);

            JPanel addBakeryСomposition = new JPanel(new GridLayout(2,1));
            addBakeryСomposition.add(new JLabel("Состав продукта"));
            addBakeryСomposition.add(bakeryComp);

            JPanel addForm = new JPanel(new GridLayout(2,1));
            addForm.add(new JLabel("Форма"));
            addForm.add(bakeryForm);

            JPanel addBakeryPrice = new JPanel(new GridLayout(2,1));
            addBakeryPrice.add(new JLabel("Цена продукта"));
            addBakeryPrice.add(bakeryPrice);

            card1CenterPanelBakery.add(addBakeryName);
            card1CenterPanelBakery.add(addBakeryСomposition);
            card1CenterPanelBakery.add(addForm);
            card1CenterPanelBakery.add(addBakeryPrice);
            //-------------------------bakeryPanelCenter-----------------//

            //-------------------------milkPanelCenter-----------------//
            JPanel addMilkName = new JPanel(new GridLayout(2,1));
            addMilkName.add(new JLabel("Название"));
            addMilkName.add(milkName);

            JPanel addMilkСomposition = new JPanel(new GridLayout(2,1));
            addMilkСomposition.add(new JLabel("Состав продукта"));
            addMilkСomposition.add(milkComp);

            JPanel addStorageForm = new JPanel(new GridLayout(2,1));
            addStorageForm.add(new JLabel("Форма для хранения"));
            addStorageForm.add(storageForm);

            JPanel addMilkPrice = new JPanel(new GridLayout(2,1));
            addMilkPrice.add(new JLabel("Цена продукта"));
            addMilkPrice.add(milkPrice);

            card1CenterPanelMilk.add(addMilkName);
            card1CenterPanelMilk.add(addMilkСomposition);
            card1CenterPanelMilk.add(addStorageForm);
            card1CenterPanelMilk.add(addMilkPrice);
            //-------------------------milkPanelCenter-----------------//

            card1SouthPanel.add(add);
            card1SouthPanel.add(clear);


            //-------------------------mainButtons-----------------//
            JPanel MainButtons = new JPanel(new GridLayout(1,2));
            MainButtons.add(bakeryButton);
            MainButtons.add(milkButton);
            //-------------------------mainButtons-----------------//

            card1.add(MainButtons,BorderLayout.NORTH);
            card1.add(card1CenterPanelBakery,BorderLayout.CENTER);
            card1.add(card1CenterPanelMilk,BorderLayout.CENTER);
            card1.add(card1SouthPanel,BorderLayout.SOUTH);


            JPanel card2MainButtons = new JPanel(new GridLayout(1,2));
            card2MainButtons.add(card2bakeryButton);
            card2MainButtons.add(card2milkButton);


            JPanel card2SouthButtons = new JPanel(new FlowLayout());
            card2SouthButtons.add(filter);
            card2SouthButtons.add(search);
            card2SouthButtons.add(remove);


            InterFace.card2.add(card2MainButtons,BorderLayout.NORTH);
            card2.add(card2SouthButtons,BorderLayout.SOUTH);

            //--------------------------------------------------Table------------------//
            card2bakeryButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    table = new JTable(new Controller.MyTableBakery());

                    choice = 1;
                    BorderLayout layout = (BorderLayout)card2.getLayout();

                    if(layout.getLayoutComponent(BorderLayout.CENTER)==null){
                        card2.add(table);
                    }

                    card2.remove(layout.getLayoutComponent(BorderLayout.CENTER));

                    card2.add(new JScrollPane(table), BorderLayout.CENTER);
                    card2bakeryButton.setBorder(BorderFactory.createLineBorder(Color.green));
                    card2milkButton.setBorder(BorderFactory.createLineBorder(null));
                    card2.setVisible(true);


                    card2.repaint();
                    card2.validate();

                }
            });

            card2milkButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    choice = 2;


                    table = new JTable(new Controller.MyTableMilk());

                    BorderLayout layout = (BorderLayout)card2.getLayout();


                    if(layout.getLayoutComponent(BorderLayout.CENTER)==null){
                        card2.add(table);
                    }


                    card2.remove(layout.getLayoutComponent(BorderLayout.CENTER));

                    card2.add(new JScrollPane(table), BorderLayout.CENTER);
                    card2milkButton.setBorder(BorderFactory.createLineBorder(Color.green));
                    card2bakeryButton.setBorder(BorderFactory.createLineBorder(null));

                    card2.setVisible(true);

                    card2.repaint();
                    card2.validate();
                }
            });
            //--------------------------------------------------Table------------------//

            // Создаем панель с менеджером расположения CardLayout
            cards = new JPanel(new CardLayout());
            // Добавляем вкладки
            cards.add(card1, BUTTONPANEL);
            cards.add(card2, TEXTPANEL);

            container.add(mainPanel, BorderLayout.PAGE_START);
            container.add(cards, BorderLayout.CENTER);
        }
        // Обработчик события
        public void itemStateChanged(ItemEvent event) {
            CardLayout layout = (CardLayout) (cards.getLayout());
            layout.show(cards, (String) event.getItem());
        }
    }


