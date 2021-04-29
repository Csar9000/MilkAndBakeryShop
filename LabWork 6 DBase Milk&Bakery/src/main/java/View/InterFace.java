package View;



import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import Controller.Controller;


public class InterFace implements ItemListener {
    public static boolean NullEx = false;


    public static boolean btnAddBakHasBeenClicked = false;
    public static boolean btnAddMilkHasBeenClicked = true;


    public static JPanel card1CenterPanelBakery = new JPanel(new GridLayout(1,2));
    public static JPanel card1CenterPanelMilk = new JPanel(new GridLayout(1,2));

    public static JButton bakeryButton = new JButton("Хлеб");
    public static JButton milkButton = new JButton("Молоко");

    public static JButton card2bakeryButton = new JButton("Хлеб");
    public static JButton card2milkButton = new JButton("Молоко");

    public static JTextField bakeryName = new JTextField("");
    public static JTextField bakeryComp = new JTextField("");
    public static JTextField extensionDataBakery = new JTextField("");
    public static JTextField bakeryPrice = new JTextField("");

    public static JTextField milkName = new JTextField("");
    public static JTextField milkComp = new JTextField("");
    public static JTextField extensionDataMilk = new JTextField("");
    public static JTextField milkPrice = new JTextField("");

    public static JTextField DateMilk = new JTextField("");
    public static JTextField NumberMilk = new JTextField("");

    public static JTextField DateBakery = new JTextField("");
    public static JTextField NumberBakery = new JTextField("");

    public static JTable table;
    public static JTable tableNomen;

    public static JButton searchNomen = new JButton("Найти");
    public static JButton remove = new JButton("Продать");
    public static JButton removeAct = new JButton("Удалить");

    public static JButton createAct= new JButton("Составить акт");
    public static JButton finishAct= new JButton("Закончить акт");


    public static JPanel card2 = new JPanel(new BorderLayout());

    public static boolean createNewAct = false;
    public static int checkClick = 0;

    public static JButton GeneralTable =  new JButton("Основная таблица");


    public static int choice = 0;

    private static JPanel cards;


    public static JTextField filter= new JTextField("");
    public static JTextField countToSale = new JTextField("");
    public static JTextField Nomenfilter= new JTextField("");

    final static String BUTTONPANEL = "Панель добавления продуктов";
    final static String TEXTPANEL = "Склад";


    /**
     * Метод определения интерфейса панели
     *
     * @param container - панель содержимого
     */

    public void createPanelUI(Container container) {




        removeAct.addActionListener(new Controller.deleteAct());

        milkButton.setBorder(BorderFactory.createLineBorder(Color.green));

        filter.setPreferredSize(new Dimension(50,50));
        searchNomen.setPreferredSize(new Dimension(100,20));
        remove.setPreferredSize(new Dimension(100,20));

        finishAct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

               createAct.setBackground(null);
               checkClick = 0;

               double d  = Controller.temp;
               String c = Double.toString(d);
               c = c.substring(0, c.indexOf("."));
               Controller.temp = Double.parseDouble(c);
               Controller.getWarehouseFromDB();
            }
        });

        createAct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Controller.l.clearBak();
                Controller.l.clearMilk();
                Controller.l.clearGeneralTable();
                if(checkClick==0) {
                    if((DateBakery.getText().trim().length() >0)||(DateMilk.getText().trim().length() >0)) {
                        createNewAct = true;
                        createAct.setBackground(Color.GREEN);
                        checkClick++;
                        Controller.temp +=1;
                    }
                    else {JOptionPane.showMessageDialog(null,"Введите дату акта");}
                }

        else{JOptionPane.showMessageDialog(null,"Вы уже составляете акт");}
    }
});
        searchNomen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean b = false;
                int[] y = new int[Controller.l.generalList.size()];
                for(int i =0;i<y.length;i++){
                    y[i] = Integer.parseInt(Controller.l.generalList.get(i).getIdProductType());
                }

                NomenclatureView fr;
                if (Nomenfilter.getText().trim().length() > 0) {
                    try {
                        Controller.findNomenclature(Integer.parseInt(Nomenfilter.getText()));
                        fr = new NomenclatureView("Таблица номенклатуры");
                    }
                    catch (IllegalArgumentException | NullPointerException ex){
                        JOptionPane.showMessageDialog(null,"Некорректный индекс");
                    }

                }
              else {JOptionPane.showMessageDialog(null,"Пустая строка поиска");}
            }


        });

        remove.addActionListener(new Controller.sale());

        bakeryButton.setPreferredSize(new Dimension(50,50));
        milkButton.setPreferredSize(new Dimension(50,50));

        card2bakeryButton.setPreferredSize(new Dimension(50,50));
        card2milkButton.setPreferredSize(new Dimension(50,50));


        GeneralTable.setPreferredSize(new Dimension(100,50));
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
                    extensionDataBakery.setText("");
                    bakeryPrice.setText("");
                }
                if(card1CenterPanelMilk.isVisible()){
                    milkName.setText("");
                    milkComp.setText("");
                    extensionDataMilk.setText("");
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
                btnAddBakHasBeenClicked = true;
                btnAddMilkHasBeenClicked = false;

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

                btnAddBakHasBeenClicked = false;
                btnAddMilkHasBeenClicked = true;

            }
        });



        //-------------------------bakeryPanelCenter-----------------//
        JPanel addBakeryName = new JPanel(new GridLayout(2,1));
        addBakeryName.add(new JLabel("Название"));
        addBakeryName.add(bakeryName);

        JPanel addBakeryСomposition = new JPanel(new GridLayout(2,1));
        addBakeryСomposition.add(new JLabel("Состав продукта"));
        addBakeryСomposition.add(bakeryComp);

        JPanel addExtensionDataBakery= new JPanel(new GridLayout(2,1));
        addExtensionDataBakery.add(new JLabel("Срок годности"));
        addExtensionDataBakery.add(extensionDataBakery);

        JPanel addBakeryPrice = new JPanel(new GridLayout(2,1));
        addBakeryPrice.add(new JLabel("Цена продукта"));
        addBakeryPrice.add(bakeryPrice);


        JPanel addDateBakery  = new JPanel(new GridLayout(2,1));
        addDateBakery.add(new JLabel("Дата привоза"));
        addDateBakery.add(DateBakery);

        JPanel addNumberBakery  = new JPanel(new GridLayout(2,1));
        addNumberBakery.add(new JLabel("Количество"));
        addNumberBakery.add(NumberBakery);


        addDateBakery.setPreferredSize(new Dimension(20,110));
        addNumberBakery.setPreferredSize(new Dimension(50,110));


        Box card1BakeryBoxLeft = Box.createVerticalBox();
        card1BakeryBoxLeft.add(addBakeryName);
        card1BakeryBoxLeft.add(addBakeryСomposition);
        card1BakeryBoxLeft.add(addExtensionDataBakery);
        card1BakeryBoxLeft.add(addBakeryPrice);

        JPanel card1BakeryBoxRight = new JPanel(new BorderLayout());
        Box box = Box.createVerticalBox();
        box.add(addDateBakery);
        box.add(addNumberBakery);
        card1BakeryBoxRight.add(box,BorderLayout.NORTH);

        card1CenterPanelBakery.add(card1BakeryBoxLeft);
        card1CenterPanelBakery.add(card1BakeryBoxRight);
        //-------------------------bakeryPanelCenter-----------------//




        //-------------------------milkPanelCenter-----------------//
        JPanel addMilkName = new JPanel(new GridLayout(2,1));
        addMilkName.add(new JLabel("Название"));
        addMilkName.add(milkName);

        JPanel addMilkСomposition = new JPanel(new GridLayout(2,1));
        addMilkСomposition.add(new JLabel("Состав продукта"));
        addMilkСomposition.add(milkComp);


        JPanel addMilkPrice = new JPanel(new GridLayout(2,1));
        addMilkPrice.add(new JLabel("Цена продукта"));
        addMilkPrice.add(milkPrice);

        JPanel addExtensionDataMilk = new JPanel(new GridLayout(2,1));
        addExtensionDataMilk.add(new JLabel("Срок годности"));
        addExtensionDataMilk.add(extensionDataMilk);

        JPanel addDateMilk  = new JPanel(new GridLayout(2,1));
        addDateMilk.add(new JLabel("Дата привоза"));
        addDateMilk.add(DateMilk);

        JPanel addNumberMilk  = new JPanel(new GridLayout(2,1));
        addNumberMilk.add(new JLabel("Количество"));
        addNumberMilk.add(NumberMilk);

        addDateMilk.setPreferredSize(new Dimension(20,110));
        addNumberMilk.setPreferredSize(new Dimension(20,110));

        Box card1MilkBoxLeft = Box.createVerticalBox();
        card1MilkBoxLeft.add(addMilkName);
        card1MilkBoxLeft.add(addMilkСomposition);
        card1MilkBoxLeft.add(addExtensionDataMilk);
        card1MilkBoxLeft.add(addMilkPrice);

        JPanel card1MilkBoxRight = new JPanel(new BorderLayout());
        Box box1 = Box.createVerticalBox();
        box1.add(addDateMilk);
        box1.add(addNumberMilk);
        card1MilkBoxRight.add(box1,BorderLayout.NORTH);

        card1CenterPanelMilk.add(card1MilkBoxLeft);
        card1CenterPanelMilk.add(card1MilkBoxRight);
        //-------------------------milkPanelCenter-----------------//

        card1SouthPanel.add(add);
        card1SouthPanel.add(clear);
        card1SouthPanel.add(createAct);
        card1SouthPanel.add(finishAct);


        //-------------------------mainButtons-----------------//
        JPanel MainButtons = new JPanel(new GridLayout(1,2));
        MainButtons.add(bakeryButton);
        MainButtons.add(milkButton);
        //-------------------------mainButtons-----------------//

        card1.add(MainButtons,BorderLayout.NORTH);
        card1.add(card1CenterPanelBakery,BorderLayout.CENTER);
        card1.add(card1CenterPanelMilk,BorderLayout.CENTER);
        card1.add(card1SouthPanel,BorderLayout.SOUTH);


        JPanel card2MainButtons = new JPanel(new BorderLayout());
        card2MainButtons.add(GeneralTable,BorderLayout.NORTH);
        JPanel card2ButtonsCont = new JPanel(new GridLayout(1,2));
        card2ButtonsCont.add(card2bakeryButton);
        card2ButtonsCont.add(card2milkButton);
        card2MainButtons.add(card2ButtonsCont);


        Box card2TempVertBox = Box.createVerticalBox();

        Box card2i3 = Box.createVerticalBox();
        card2i3.add(new Label("Введите id Акта"));
        card2i3.add(filter);

        Box card2i4 = Box.createVerticalBox();
        card2i4.add(new Label("Введите количество"));
        card2i4.add(countToSale);

        Box vertBox = Box.createVerticalBox();
        vertBox.add(new Label());
        remove.setPreferredSize(new Dimension(100,30));
        vertBox.add(remove);
        removeAct.setPreferredSize(new Dimension(100,30));
        vertBox.add(removeAct);

        Box card2SomeBox = Box.createHorizontalBox();
        card2SomeBox.add(card2i3);
        card2SomeBox.add(card2i4);
        card2SomeBox.add(vertBox);


        card2TempVertBox.add(card2SomeBox);



        Box card2i5 = Box.createHorizontalBox();
        card2i5.add(new Label("Введите id продукта"));
        card2i5.add(Nomenfilter);
        card2i5.add(searchNomen);

        card2TempVertBox.add(card2i5);

        JPanel card2SouthButtons = new JPanel(new GridLayout());
        card2SouthButtons.add(card2TempVertBox);

        InterFace.card2.add(card2MainButtons,BorderLayout.NORTH);
        card2.add(card2SouthButtons,BorderLayout.SOUTH);

        //--------------------------------------------------Table------------------//
        GeneralTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InterFace.choice = 0;
                //table  = new JTable(new Controller.MyTableFromDBWareHouse());



                JTable table = new JTable(new Controller.MyDefaultTableModel());
                RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
                        new Controller.MyDefaultTableModel());
                table.setRowSorter(sorter);
                JScrollPane pane = new JScrollPane(table);

                BorderLayout layout = (BorderLayout)card2.getLayout();

                if(layout.getLayoutComponent(BorderLayout.CENTER)==null){
                    card2.add(pane);
                }

                card2.remove(layout.getLayoutComponent(BorderLayout.CENTER));

                card2.add(pane, BorderLayout.CENTER);
                GeneralTable.setBorder(BorderFactory.createLineBorder(Color.green));
                card2bakeryButton.setBorder(BorderFactory.createLineBorder(null));
                card2milkButton.setBorder(BorderFactory.createLineBorder(null));
                card2.setVisible(true);


                card2.repaint();
                card2.validate();
            }
        });

        card2bakeryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //table = new JTable(new Controller.MyTableBakery());



                JTable table = new JTable(new Controller.MyTableBakery());
                RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
                        new Controller.MyTableBakery());
                table.setRowSorter(sorter);
                JScrollPane pane = new JScrollPane(table);

                choice = 1;
                BorderLayout layout = (BorderLayout)card2.getLayout();

                if(layout.getLayoutComponent(BorderLayout.CENTER)==null){
                    card2.add(pane);
                }

                card2.remove(layout.getLayoutComponent(BorderLayout.CENTER));

                card2.add(pane, BorderLayout.CENTER);
                card2bakeryButton.setBorder(BorderFactory.createLineBorder(Color.green));
                card2milkButton.setBorder(BorderFactory.createLineBorder(null));
                GeneralTable.setBorder(BorderFactory.createLineBorder(null));
                card2.setVisible(true);


                card2.repaint();
                card2.validate();

            }
        });

        card2milkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                choice = 2;


                //table = new JTable(new Controller.MyTableMilk());


                JTable table = new JTable(new Controller.MyTableMilk());
                RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
                        new Controller.MyTableMilk());
                table.setRowSorter(sorter);
                JScrollPane pane = new JScrollPane(table);

                BorderLayout layout = (BorderLayout)card2.getLayout();


                if(layout.getLayoutComponent(BorderLayout.CENTER)==null){
                    card2.add(pane);
                }


                card2.remove(layout.getLayoutComponent(BorderLayout.CENTER));

                card2.add(pane, BorderLayout.CENTER);
                card2milkButton.setBorder(BorderFactory.createLineBorder(Color.green));
                card2bakeryButton.setBorder(BorderFactory.createLineBorder(null));
                GeneralTable.setBorder(BorderFactory.createLineBorder(null));

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


